package fr.afcepf.atod.onwine.bigdata.rest;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.afcepf.atod.onwine.bigdata.dto.ChartFormatter;
import fr.afcepf.atod.onwine.bigdata.dto.KeyValDTO;
import fr.afcepf.atod.onwine.bigdata.repository.ProductRepository;

@Component
@RestController
@RequestMapping("/products")
@Produces(MediaType.APPLICATION_JSON)
@CrossOrigin
public class ProductMetrics {
    
    @Inject
    private ProductRepository productRepository; 
    
    @GET
    @RequestMapping("/types")
    public List<KeyValDTO> typesAggregate() {
        return productRepository.aggregateByTypes();
    }
    
    @GET
    @RequestMapping("/typesChartData")
    public ChartFormatter typesChartData() {
        return productRepository.getTypesChartData();
    }
}
