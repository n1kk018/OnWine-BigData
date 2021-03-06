package fr.afcepf.atod.onwine.bigdata.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;

import fr.afcepf.atod.onwine.bigdata.dao.api.ProductRepositoryCustom;
import fr.afcepf.atod.onwine.bigdata.dao.utils.ChartDataFormatter;
import fr.afcepf.atod.onwine.bigdata.domain.Product;
import fr.afcepf.atod.onwine.bigdata.dto.ChartFormatter;
import fr.afcepf.atod.onwine.bigdata.dto.KeyValDTO;

public class ProductRepositoryImpl implements ProductRepositoryCustom {
    
    private final MongoTemplate mongoTemplate;
    
    @Inject
    public ProductRepositoryImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }
    
    @Override
    public List<KeyValDTO> aggregateByTypes() {
        return mongoTemplate.aggregate(Aggregation.newAggregation(
                Aggregation.group("productType")
                .last("productType").as("key")
                .count().as("val")
        ), Product.class,KeyValDTO.class).getMappedResults();
    }
    
    @Override
    public ChartFormatter getTypesChartData() {
        List<KeyValDTO> list = aggregateByTypes();
        return ChartDataFormatter.twoColsChart(list, "Types de vins", "Nombre de références");
    }

}
