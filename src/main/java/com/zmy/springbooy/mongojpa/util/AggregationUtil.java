package com.zmy.springbooy.mongojpa.util;

import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;

public class AggregationUtil {

    /**
     * 分组-> 获取分组的数目
     *
     * @return
     */
    public static Aggregation getGroupType(ReactiveMongoTemplate reactiveMongoTemplate, String collection, String property) {
        return Aggregation.newAggregation(
                Aggregation.group(property).first(property).as(property),
                Aggregation.project(property)
        );
    }

    /**
     * 分组 -> 统计每个分组的数目 -> 降序
     *
     * @param property
     * @return
     */
    public static Aggregation countGroupByTypeDesc(String property) {
        return Aggregation.newAggregation(
                Aggregation.group(property).count().as("count").first(property).as(property),
                Aggregation.project(property, "count"),
                Aggregation.sort(Sort.Direction.DESC, "count")
        );
    }

    /**
     * 分组 -> 统计每个分组的数目 -> 升序
     *
     * @param property
     * @return
     */
    public static Aggregation countGroupByTypeAsc(String property) {
        return Aggregation.newAggregation(
                Aggregation.group(property).count().as("count").first(property).as(property),
                Aggregation.project(property, "count"),
                Aggregation.sort(Sort.Direction.DESC, "count")
        );
    }
}
