package com.dmall.component.elasticSearch;

import com.alibaba.fastjson.JSON;
import com.dmall.component.elasticSearch.entity.*;
import com.dmall.component.elasticSearch.properties.DMallElasticSearchProperties;
import com.google.common.collect.Lists;
import io.searchbox.client.JestClient;
import io.searchbox.core.*;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.index.query.*;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ReflectionUtils;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.List;

/**
 * @description:
 * @author: created by yuhang on 2019/11/6 22:56
 */
@Slf4j
public class ESDao {

    @Autowired
    private JestClient jestClient;

    @Autowired
    private DMallElasticSearchProperties esProperties;

    public <T> void save(T t) {
        Field idField = ReflectionUtils.findField(t.getClass(), "id", Long.class);
        if (idField == null) {

        }
        Long id = (Long) ReflectionUtils.getField(idField, t);
        save(t, esProperties.getIndexName(), esProperties.getTypeName(), id);
    }

    // 新增对象
    public <T> void save(T t, String indexName, String typeName, Long id) {

        Index index = new Index.Builder(t).index(indexName).type(typeName).id(String.valueOf(id)).build();
        try {
            DocumentResult execute = jestClient.execute(index);
        } catch (IOException e) {
            log.error("ESDao.save error,indexName:{},type:{},id:{},do:{}", indexName, typeName, id,
                    JSON.toJSONString(t), e);
        }
    }

    public void update(Long id) {
        update(esProperties.getIndexName(), esProperties.getTypeName(), id);
    }

    public void update(String indexName, String typeName, Long id) {
        String phone = "";
        Update index = new Update.Builder(phone).index(indexName).type(typeName).id(String.valueOf(id)).build();
        try {
            DocumentResult execute = jestClient.execute(index);
        } catch (IOException e) {
            log.error("ESDao.update error,indexName:{},type:{},id:{},", indexName, typeName, id, e);
        }
    }

    public void delete(Long id) {
        delete(esProperties.getIndexName(), esProperties.getTypeName(), id);
    }

    public void delete(String indexName, String typeName, Long id) {
        Delete index = new Delete.Builder(String.valueOf(id)).index(indexName).type(typeName).build();
        try {
            DocumentResult execute = jestClient.execute(index);
        } catch (IOException e) {
            log.error("ESDao.delete error,indexName:{},type:{},id:{},", indexName, typeName, id, e);
        }
    }

    public <T> List<T> search(ESSearch esSearch){
//        return search(esSearch.getIndexName(),esSearch.getTypeName(),esSearch.getSearchFields(),esSearch.getFilterFields(),esSearch.getHightLightField())
        return null;
    }

    public <T> List<T> search(String indexName, String typeName, List<SearchField> searchFields, List<FilterField> filterFields,
                          String hightLightField, ESPage esPage, SortField sortField, Class<T> clazz) {
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();

        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        // 关键词搜索
        searchFields.forEach(searchField -> {
            MatchQueryBuilder matchQueryBuilder = new MatchQueryBuilder(searchField.getFieldName(), searchField.getFieldValue());
            boolQueryBuilder.must(matchQueryBuilder);
        });


        // 过滤
        filterFields.forEach(filterField -> {
            QueryBuilder termQueryBuilder = new TermsQueryBuilder(filterField.getFieldName(), filterField.getFieldValue());
            boolQueryBuilder.filter(termQueryBuilder);
        });

        searchSourceBuilder.query(boolQueryBuilder);

        // 设置高亮
        HighlightBuilder highlightBuilder = new HighlightBuilder();
        highlightBuilder.field(hightLightField);
        highlightBuilder.preTags("<span style='color:red'>");
        highlightBuilder.postTags("</span>");
        searchSourceBuilder.highlighter(highlightBuilder);

        // 设置分页
        searchSourceBuilder.from(esPage.getFrom());
        searchSourceBuilder.size(esPage.getSize());

        // 排序
        searchSourceBuilder.sort(sortField.getFieldName(), sortField.getSortOrder());

        String dslStr = searchSourceBuilder.toString();

        Search search = new Search.Builder(dslStr).addIndex(indexName).addType(typeName).build();

        SearchResult execute = null;
        try {
            execute = jestClient.execute(search);
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<SearchResult.Hit<T, Void>> hits = execute.getHits(clazz);

        List<T> result = Lists.newArrayList();
        for (SearchResult.Hit<T, Void> hit : hits) {
            T source = hit.source;
            result.add(source);
        }
        return result;
    }
}
