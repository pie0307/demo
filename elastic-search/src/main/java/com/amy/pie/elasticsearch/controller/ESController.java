package com.amy.pie.elasticsearch.controller;

import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.index.query.InnerHitBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;
import org.elasticsearch.search.aggregations.metrics.cardinality.CardinalityAggregationBuilder;
import org.elasticsearch.search.aggregations.metrics.cardinality.InternalCardinality;
import org.elasticsearch.search.collapse.CollapseBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

/**
 * Author : liuby.
 * Description :
 * Date : Created in 2019/1/16 11:33
 */
@RestController
public class ESController {
    @Resource
    private TransportClient client;


    @PostConstruct
    void init() {
        SearchRequestBuilder searchRequestBuilder = client.prepareSearch("inv_statistics");
        searchRequestBuilder.setSearchType(SearchType.DFS_QUERY_THEN_FETCH);

        //聚合查询
        TermsAggregationBuilder territoryCode = AggregationBuilders.terms("territoryCode").field("territoryCode");
        TermsAggregationBuilder productLine = AggregationBuilders.terms("productLine").field("productLine");
        searchRequestBuilder.addAggregation(territoryCode.subAggregation(productLine));
        SearchResponse response1 = searchRequestBuilder.setSize(0).execute().actionGet();
        Terms terms = response1.getAggregations().get("territoryCode");
        if (terms != null) {
            for (Terms.Bucket entry : terms.getBuckets()) {
                System.out.println(entry.getKey() + "---------" + String.valueOf(entry.getDocCount()));
                Terms productLine1 = entry.getAggregations().get("productLine");
                for (Terms.Bucket b : productLine1.getBuckets()) {
                    System.out.println(b.getKey() + "---------" + String.valueOf(b.getDocCount()));
                }
            }
        }

        //近似聚合
        CardinalityAggregationBuilder field = AggregationBuilders.cardinality("territoryCode").field("territoryCode");
        SearchResponse response = client.prepareSearch("inv_statistics_v1").setTypes("house_detail").setSize(0).addAggregation(field).get();
        InternalCardinality ca = response.getAggregations().get("territoryCode");
        long total = ca.getValue();
        System.out.println("total " + total);

        InnerHitBuilder hitBuilder = new InnerHitBuilder().setSize(2).setName("deptName");
        CollapseBuilder collapse = new CollapseBuilder("territoryCode").setInnerHits(hitBuilder);
        SearchResponse searchResponse = client.prepareSearch("inv_statistics_v1").setTypes("house_detail").setCollapse(collapse)
                .addSort("territoryCode", SortOrder.DESC).setFrom(0).setSize(100).get();

        SearchHit[] hits = searchResponse.getHits().getHits();
        for (SearchHit hit : hits) {
            System.out.println(hit.getSourceAsMap());
        }
    }
}
