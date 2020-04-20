package com.dmall.component.elasticsearch;

import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.ReflectUtil;
import cn.hutool.core.util.StrUtil;
import com.dmall.common.constants.Constants;
import com.dmall.common.dto.ResponsePage;
import com.dmall.common.enums.component.ESErrorEnum;
import com.dmall.common.model.exception.ComponentException;
import com.dmall.common.util.FieldUtil;
import com.dmall.common.util.JsonUtil;
import com.dmall.component.elasticsearch.entity.*;
import com.google.common.collect.Lists;
import io.searchbox.client.JestClient;
import io.searchbox.core.*;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.index.query.*;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @description: es公共操作类
 * @author: created by hang.yu on 2019/11/6 22:56
 */
@Slf4j
public class ESDao<T> {

    @Autowired
    private JestClient jestClient;

    @Autowired
    private DMallElasticSearchProperties esProperties;

    /**
     * 新增或更新文档
     */
    public void saveOrUpdate(T t) {
        Long id = getIdValue(t);
        saveOrUpdate(t, esProperties.getIndexName(), esProperties.getTypeName(), id);
    }

    /**
     * 新增或更新文档
     */
    public void saveOrUpdate(T t, String indexName, String typeName) {
        Long id = getIdValue(t);
        saveOrUpdate(t, indexName, typeName, id);
    }

    /**
     * 新增或更新文档
     *
     * @param t         文档对象
     * @param indexName 索引名称
     * @param typeName  类型名称
     * @param id        id
     */
    public void saveOrUpdate(T t, String indexName, String typeName, Long id) {
        if (t == null) {
            throw new ComponentException(ESErrorEnum.STORE_OBJECT_NULL);
        }
        basicCheck(indexName, typeName, id);
        try {
            Index index = new Index.Builder(t).index(indexName).type(typeName).id(String.valueOf(id)).build();
            DocumentResult execute = jestClient.execute(index);
            if (!execute.isSucceeded()) {
                log.error("ESDao saveOrUpdate fail,{}", JsonUtil.toJson(execute));
                throw new ComponentException(ESErrorEnum.BASIC_ERROR);
            }
            log.info("ESDao saveOrUpdate successful，{} ", id);
        } catch (IOException e) {
            log.error("ESDao saveOrUpdate error,", e);
            throw new ComponentException(ESErrorEnum.BASIC_ERROR);
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
        basicCheck(indexName, typeName, id);
        Delete index = new Delete.Builder(String.valueOf(id)).index(indexName).type(typeName).build();
        try {
            log.info("delete from es, index:{},type:{},id:{}", indexName, typeName, id);
            DocumentResult execute = jestClient.execute(index);
            if (!execute.isSucceeded()) {
                log.error("ESDao delete fail,{}", JsonUtil.toJson(execute));
                throw new ComponentException(ESErrorEnum.BASIC_ERROR);
            }
        } catch (IOException e) {
            log.error("ESDao delete error,", e);
            throw new ComponentException(ESErrorEnum.BASIC_ERROR);
        }
    }

    /**
     * 通过search对象搜索
     */
    public ResponsePage<T> search(ESSearch<T> esSearch) {
        if (esSearch == null) {
            throw new ComponentException(ESErrorEnum.NO_SEARCH);
        }
        checkEs(esSearch.getIndexName(), esSearch.getTypeName());
        return search(esSearch.getIndexName(), esSearch.getTypeName(), esSearch.getSearchFields(), esSearch.getFilterFields(),
                esSearch.getRangeField(), esSearch.getHighLightField(), esSearch.getEsPage(), esSearch.getSortField(), esSearch.getClazz());

    }

    /**
     * 搜索的核心方法
     *
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
    private ResponsePage<T> search(String indexName, String typeName, List<SearchField> searchFields, List<FilterField> filterFields,
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
        log.info("query dsl:\n{}", dslStr);
        Search search = new Search.Builder(dslStr).addIndex(indexName).addType(typeName).build();

        SearchResult execute = null;
        try {
            execute = jestClient.execute(search);
            if (!execute.isSucceeded()) {
                log.error("ESDao search fail,{}", JsonUtil.toJson(execute));
                throw new ComponentException(ESErrorEnum.BASIC_ERROR);
            }
        } catch (IOException e) {
            log.error("ESDao search error,", e);
            throw new ComponentException(ESErrorEnum.BASIC_ERROR);
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
        return new ResponsePage(execute.getTotal(), result);
    }

    /**
     * 基本校验
     */
    private void basicCheck(String indexName, String typeName, Long id) {
        checkEs(indexName, typeName);
        if (id == null) {
            throw new ComponentException(ESErrorEnum.NO_ID);
        }
    }

    /**
     * 索引名和类型名不能为空
     */
    private void checkEs(String indexName, String typeName) {
        if (StrUtil.isBlank(indexName)) {
            throw new ComponentException(ESErrorEnum.NO_INDEX_NAME);
        }
        if (StrUtil.isBlank(typeName)) {
            throw new ComponentException(ESErrorEnum.NO_TYPE_NAME);
        }
    }

    /**
     * 获取对象的id值
     */
    private Long getIdValue(Object result) {
        EsId esId = FieldUtil.findAnnotationField(result, EsId.class);
        Long id = null;
        if (esId != null) {
            String value = esId.value();
            id = (Long) ReflectUtil.getFieldValue(result, value);
        } else {
            id = (Long) ReflectUtil.getFieldValue(result, Constants.ID);
        }
        return id;
    }
}
