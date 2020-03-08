package com.dmall.component.elasticsearch;

import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.ReflectUtil;
import com.alibaba.fastjson.JSON;
import com.dmall.common.dto.ResponsePage;
import com.dmall.component.elasticsearch.entity.*;
import com.dmall.component.elasticsearch.exception.ESErrorEnum;
import com.dmall.component.elasticsearch.exception.ESException;
import com.google.common.collect.Lists;
import io.searchbox.client.JestClient;
import io.searchbox.core.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.index.query.*;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ReflectionUtils;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

/**
 * @description: es公共操作类
 * @author: created by hang.yu on 2019/11/6 22:56
 */
@Slf4j
public class ESDao {

    @Autowired
    private JestClient jestClient;

    @Autowired
    private DMallElasticSearchProperties esProperties;

    /**
     * 新增或更新文档
     */
    public <T> void saveOrUpdate(T t) {
        Field idField = ReflectionUtils.findField(t.getClass(), "id", Long.class);
        if (idField == null) {
            throw new ESException(ESErrorEnum.NO_ID_FIELD);
        }
        Long id = (Long) ReflectionUtils.getField(idField, t);
        saveOrUpdate(t, esProperties.getIndexName(), esProperties.getTypeName(), id);
    }

    /**
     * 新增或更新文档
     *
     * @param t         文档对象
     * @param indexName 索引名称
     * @param typeName  类型名称
     * @param id        id
     */
    public <T> void saveOrUpdate(T t, String indexName, String typeName, Long id) {
        basicCheck(indexName, typeName);
        Index index = new Index.Builder(t).index(indexName).type(typeName).id(String.valueOf(id)).build();
        try {
            DocumentResult execute = jestClient.execute(index);
            if (!execute.isSucceeded()) {
                throw new ESException(String.valueOf(execute.getResponseCode()), execute.getErrorMessage());
            }
            log.info("ESDao.save successful，{} ", id);
        } catch (Exception e) {
            log.error("ESDao.save error,indexName:{},type:{},id:{},do:{}", indexName, typeName, id, JSON.toJSONString(t), e);
            throw new ESException();
        }
    }

    /**
     * 删除文档
     *
     * @param id id
     */
    public void delete(Long id) {
        delete(esProperties.getIndexName(), esProperties.getTypeName(), id);
    }

    /**
     * 删除文档
     *
     * @param indexName 索引名称
     * @param typeName  类型名称
     * @param id        id
     */
    public void delete(String indexName, String typeName, Long id) {
        basicCheck(indexName, typeName);
        checkId(id);
        Delete index = new Delete.Builder(String.valueOf(id)).index(indexName).type(typeName).build();
        try {
            DocumentResult execute = jestClient.execute(index);
            if (!execute.isSucceeded()) {
                throw new ESException(String.valueOf(execute.getResponseCode()), execute.getErrorMessage());
            }
        } catch (IOException e) {
            log.error("ESDao.delete error,indexName:{},type:{},id:{},", indexName, typeName, id, e);
            throw new ESException();
        }
    }


    /**
     * 通过search对象搜索
     */
    public <T> ResponsePage<T> search(ESSearch<T> esSearch) {
        if (esSearch == null) {
            throw new ESException(ESErrorEnum.NO_SEARCH);
        }
        basicCheck(esSearch.getIndexName(), esSearch.getTypeName());
        return search(esSearch.getIndexName(), esSearch.getTypeName(), esSearch.getSearchFields(), esSearch.getFilterFields(),
                esSearch.getRangeField(), esSearch.getHighLightField(), esSearch.getEsPage(), esSearch.getSortField(), esSearch.getClazz());

    }

    /**
     * @param indexName      索引名称
     * @param typeName       类型名称
     * @param searchFields   查询的字段
     * @param filterFields   过滤的字段
     * @param highLightField 高亮的字段
     * @param rangeField     范围过滤
     * @param esPage         分页对象
     * @param sortField      排序字段
     * @param clazz          查询的返回对象
     */
    private <T> ResponsePage<T> search(String indexName, String typeName, List<SearchField> searchFields, List<FilterField> filterFields,
                                       RangeField rangeField, String highLightField, ESPage esPage, SortField sortField, Class<T> clazz) {

        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        // 布尔过滤器
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();

        // 过滤
        if (!CollectionUtils.isEmpty(filterFields)) {
            filterFields.forEach(filterField -> {
                QueryBuilder termQueryBuilder = new TermsQueryBuilder(filterField.getFieldName(), filterField.getFieldValues());
                // 多个过滤条件是且的关系 用 must
                boolQueryBuilder.filter(termQueryBuilder);
            });
        }

        // 关键词搜索
        if (!CollectionUtils.isEmpty(searchFields)) {
            searchFields.forEach(searchField -> {
                MatchQueryBuilder matchQueryBuilder = new MatchQueryBuilder(searchField.getFieldName(), searchField.getFieldValue());
                // 多个搜索用或的关系 should
                boolQueryBuilder.should(matchQueryBuilder);
            });
        }

        // 范围
        if (rangeField != null) {
            RangeQueryBuilder rangeQueryBuilder = new RangeQueryBuilder(rangeField.getFieldName());
            rangeQueryBuilder.gte(rangeField.getStartValue());
            rangeQueryBuilder.lte(rangeField.getEndValue());
            boolQueryBuilder.must(rangeQueryBuilder);
        }

        searchSourceBuilder.query(boolQueryBuilder);

        // 设置高亮
        if (highLightField != null) {
            HighlightBuilder highlightBuilder = new HighlightBuilder();
            highlightBuilder.field(highLightField);
            highlightBuilder.preTags("<span style='color:red'>");
            highlightBuilder.postTags("</span>");
            searchSourceBuilder.highlighter(highlightBuilder);
        }

        // 设置分页
        if (esPage != null) {
            searchSourceBuilder.from(esPage.getFrom());
            searchSourceBuilder.size(esPage.getSize());
        }

        // 排序
        if (sortField != null) {
            searchSourceBuilder.sort(sortField.getFieldName(), sortField.getSortOrder());
        }


        String dslStr = searchSourceBuilder.toString();
        log.info("dsl:\n{}", dslStr);
        Search search = new Search.Builder(dslStr).addIndex(indexName).addType(typeName).build();

        SearchResult execute = null;
        try {
            execute = jestClient.execute(search);
        } catch (IOException e) {
            ESSearch<T> esSearch = new ESSearch<>(indexName, typeName, searchFields, filterFields, rangeField, highLightField, esPage, sortField, clazz);
            log.error("ESDao.search search,indexName:{},type:{},param:{}", indexName, typeName, JSON.toJSONString(esSearch), e);
            throw new ESException(String.valueOf(execute.getResponseCode()), execute.getErrorMessage());
        }
        List<SearchResult.Hit<T, Void>> hits = execute.getHits(clazz);

        List<T> result = Lists.newArrayList();
        for (SearchResult.Hit<T, Void> hit : hits) {
            T source = hit.source;
            Map<String, List<String>> highlight = hit.highlight;
            if (MapUtil.isNotEmpty(highlight)) {
                highlight.forEach((k, v) -> {
                    ReflectUtil.setFieldValue(source, k, v.get(0));
                });
            }
            result.add(source);
        }

        return new ResponsePage<T>(execute.getTotal(), result);
    }

    /**
     * 基本校验
     */
    private void basicCheck(String indexName, String typeName) {
        if (StringUtils.isBlank(indexName)) {
            throw new ESException(ESErrorEnum.NO_INDEX_NAME);
        }
        if (StringUtils.isBlank(typeName)) {
            throw new ESException(ESErrorEnum.NO_TYPE_NAME);
        }
    }

    /**
     * 校验id
     */
    private void checkId(Long id) {
        if (id == null) {
            throw new ESException(ESErrorEnum.NO_ID);
        }
    }
}
