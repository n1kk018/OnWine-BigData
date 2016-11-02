package fr.afcepf.atod.onwine.bigdata.rest;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.afcepf.atod.onwine.bigdata.dto.ChartFormatter;
import fr.afcepf.atod.onwine.bigdata.dto.KeyValDTO;
import fr.afcepf.atod.onwine.bigdata.repository.OrderRepository;

@Component
@RestController
@RequestMapping("/orders")
@Produces(MediaType.APPLICATION_JSON)
@CrossOrigin
public class OrderMetrics {
    @Inject
    private OrderRepository orderRepository; 
    
    @GET
    @RequestMapping("/currencies")
    public List<KeyValDTO> currencyAggregate() {
        return orderRepository.aggregateOrderByCurrency();
    }
    
    @GET
    @RequestMapping("/currenciesChartData")
    public ChartFormatter currenciesChartData() {
        return orderRepository.getCurrencieschartData();
    }
    
    @GET
    @RequestMapping("/countries")
    public List<KeyValDTO> countryAggregate() {
        return orderRepository.aggregateOrderByCountry();
    }
    
    @GET
    @RequestMapping("/countriesChartData")
    public ChartFormatter countriesChartData() {
        return orderRepository.getCountrieschartData();
    }
    
    @GET
    @RequestMapping("/types")
    public List<KeyValDTO> typesAggregate() {
        return orderRepository.aggregateByTypes();
    }
    
    @GET
    @RequestMapping("/typesChartData")
    public ChartFormatter typesChartData() {
        return orderRepository.getTypesChartData();
    }
    
    @GET
    @RequestMapping("/topProducts")
    public List<KeyValDTO> topProductsAggregate() {
        return orderRepository.aggregateTopProducts();
    }
    
    @GET
    @RequestMapping("/topProductsChartData")
    public ChartFormatter topProductsChartData() {
        return orderRepository.topProductsChartData();
    }
    
}
