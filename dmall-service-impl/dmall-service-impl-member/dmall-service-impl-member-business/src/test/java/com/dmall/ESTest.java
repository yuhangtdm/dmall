package com.dmall;

import com.dmall.component.elasticsearch.ESDao;
import com.dmall.component.elasticsearch.entity.ESSearch;
import com.dmall.component.elasticsearch.entity.SearchField;
import com.google.common.collect.Lists;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @description:
 * @author: created by yuhang on 2019/11/12 0:05
 */
public class ESTest extends BaseTest {

    @Autowired
    private ESDao esDao;

    @Test
    public void insert() {
        User user = new User("李四", 15);
        esDao.saveOrUpdate(user, "test_index", "doc", 3L);
    }

    @Test
    public void delete() {
        esDao.delete("test_idex", "doc", 11L);
    }

    @Test
    public void search() {
        List<SearchField> searchFieldList = Lists.newArrayList();
        searchFieldList.add(new SearchField("username", "李四"));

        ESSearch<User> userESSearch = new ESSearch<>();
        userESSearch.setClazz(User.class);
        userESSearch.setSearchFields(searchFieldList);
        userESSearch.setIndexName("test_index");
        userESSearch.setTypeName("doc");



        List<User> search = esDao.search(ESSearch.<User>builder().clazz(User.class)
                .indexName("").typeName("").searchFields(searchFieldList).build());
        search.forEach(user -> {
            System.out.println(user);
        });
    }
}
