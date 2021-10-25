package com.example.service;

import com.example.entity.ESEntity;
import com.example.entity.Person;
import com.example.utils.ESUtil;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author PF.Tian
 * @since 2021/10/17
 */
@Service
@Slf4j
public class ESRestService {

    /**
     * 往索引添加数据
     *
     * @return
     */
    public String add() {
        String Id = null;
        String index = "person";
        String id = "10001";
        try {
            XContentBuilder builder = XContentFactory.jsonBuilder()
                    .startObject()
                    .field("id", "10001")
                    .field("name", "张三")
                    .field("age", "28")
                    .field("country", "中国")
                    .field("addr", "广东深圳")
                    .field("data", "2020-01-15 20:47:20")
                    .field("birthday", "1992-01-01")
                    .endObject();
            Id = ESUtil.addData(builder, index, id);
        } catch (IOException e) {
            log.error("索引:{},id:{},添加数据失败", index, id);
        }
        return Id;
    }

    /**
     * 更新指定id的文档数据
     *
     * @return
     */
    public String update() {
        String Id = null;
        String index = "person";
        String id = "10001";
        try {
            XContentBuilder builder = XContentFactory.jsonBuilder()
                    .startObject()
                    .field("id", "10001")
                    .field("name", "李四")
                    .field("age", "30")
                    .field("country", "中国")
                    .field("addr", "广东深圳")
                    .field("data", "2020-01-15 20:47:20")
                    .field("birthday", "1990-01-01")
                    .endObject();
            Id = ESUtil.updateData(builder, index, id);
        } catch (IOException e) {
            log.error("索引:{},id:{},添加数据失败", index, id);
        }
        return Id;
    }

    /**
     * 批量插入数据
     *
     * @return
     */
    public String insertBatch() {
        String index = "person";
        List<ESEntity> entityList = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            Person person = new Person();
            String s = Integer.toString(i + 1);
            ESEntity esEntity = new ESEntity();
            person.setId(s);
            person.setName("张三" + s);
            person.setAge(30);
            person.setAddr("广东省深圳市" + s);
            person.setCountry("中国");
            person.setBirthday("1990-01-01");
            person.setData("2020-01-16 12:00:00");
            esEntity.setData(person);
            esEntity.setId(s);
            entityList.add(esEntity);
        }
        return ESUtil.insertBatch(index, entityList);
    }

    /**
     * 根据条件删除
     */
    public void delete() {
        String index = "person";
        QueryBuilder queryBuilder = QueryBuilders.matchQuery("age", "30");
        ESUtil.deleteByQuery(index, queryBuilder);

    }

    /**
     * 根据id删除文档
     *
     * @return
     */
    public String deleteById() {
        String s;
        String index = "person";
        String id = "1001";
        s = ESUtil.deleteById(index, id);
        return s;
    }

    /**
     * 根据条件查询
     *
     * @return
     */
    public List searchData() {
        List<Map<String, Object>> list = new ArrayList<>();
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        String[] fields = {"name", "addr", "birthday", "id"};
        //需要返回和不返回的字段，可以是数组也可以是字符串
        sourceBuilder.fetchSource(fields, null);
        //设置根据哪个字段进行排序查询
        sourceBuilder.sort(new FieldSortBuilder("birthday").order(SortOrder.DESC));
        BoolQueryBuilder builder = QueryBuilders.boolQuery();
        //添加查询条件
        builder.must(QueryBuilders.matchQuery("country", "中国"));
        list = ESUtil.SearchDataPage("person", 1, 10, sourceBuilder, builder);
        return list;
    }
}