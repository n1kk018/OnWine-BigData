package fr.afcepf.atod.onwine.bigdata;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

import com.mongodb.BasicDBList;
import com.mongodb.DBObject;
import com.mongodb.util.JSON;
import fr.afcepf.atod.onwine.bigdata.domain.Adress;
import fr.afcepf.atod.onwine.bigdata.domain.Country;
import fr.afcepf.atod.onwine.bigdata.domain.Customer;
import fr.afcepf.atod.onwine.bigdata.domain.Order;
import fr.afcepf.atod.onwine.bigdata.domain.OrderDetail;
import fr.afcepf.atod.onwine.bigdata.domain.Product;
import fr.afcepf.atod.onwine.bigdata.repository.CountryRepository;
import fr.afcepf.atod.onwine.bigdata.repository.CustomerRepository;
import fr.afcepf.atod.onwine.bigdata.repository.OrderRepository;
import fr.afcepf.atod.onwine.bigdata.repository.ProductRepository;
import fr.afcepf.atod.onwine.ws.soap.CurrenciesWSException_Exception;
import fr.afcepf.atod.onwine.ws.soap.CurrencyConverterService;
/*import fr.afcepf.atod.onwine.ws.soap.DeliveriesWSException_Exception;
import fr.afcepf.atod.onwine.ws.soap.DeliveryCalculatorService;
import fr.afcepf.atod.onwine.ws.soap.DeliveryQuantity;*/
import fr.afcepf.atod.onwine.ws.soap.ICurrencyConverter;
/*import fr.afcepf.atod.onwine.ws.soap.IDeliveryCalculator;
import fr.afcepf.atod.onwine.ws.soap.ServiceTax;
import fr.afcepf.atod.onwine.ws.soap.ServiceTaxBeanService;
import fr.afcepf.atod.onwine.ws.soap.TaxWSException_Exception;*/

@Component
@EnableAutoConfiguration
@ComponentScan("fr.afcepf.atod.onwine.bigdata")
public class TestData implements CommandLineRunner {
    @Autowired
    private MongoTemplate mongoTemplate;
    @Autowired
    private ProductRepository productRepo;
    @Autowired
    private OrderRepository orderRepo;
    @Autowired
    private CountryRepository countryRepo;
    @Autowired
    private CustomerRepository customerRepo;
    
    private static final int NB_ORDER = 10000;
    private SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
    
    /*public static void main(String[] args) {
        SpringApplication.run(TestData.class, args);
    }*/
    
    @Override
    public void run(String... paramArg0) throws Exception {
        System.out.println("==============Début insertion==============");
        //countryRepo.deleteAll();
        //productRepo.deleteAll();
        orderRepo.deleteAll();
        customerRepo.deleteAll();
        //createCountryCollFromFile();
        //createProductCollFomFile();
        List<Country> countries = countryRepo.findAll();
        List<Product> products = productRepo.findAll();
        List<String> civilities = new ArrayList<String>();
        civilities.add("Mr");
        civilities.add("Mme");
        civilities.add("Mle");
        Long odId = 0L;
        for (Long i = 0L; i < NB_ORDER; i++) {
            int idxCountry = new Random().nextInt(countries.size());
            int idxCivility = new Random().nextInt(civilities.size());
            int nbDetails = (new Random().nextInt(5))+1;
            String currency = countries.get(idxCountry).getCurrency();
            String randomZip = String.format("%05d",new Random().nextInt(99999));
            int randomNumber = (int) (i+((int) Math.random()*10));
            Customer c=null;
            Adress facturation=null;
            Adress livraison=null;
            Timestamp inscription = null;
            if(i<((NB_ORDER*70)/100)) {
                inscription = getRandomDate();
                c = new Customer(i,civilities.get(idxCivility),"John","Doe", inscription);
                facturation = new Adress("rue random"+i, randomNumber, randomZip, "randomCity"+i, countries.get(idxCountry), true);
                livraison = new Adress("rue random"+i, randomNumber, randomZip, "randomCity"+i, countries.get(idxCountry), false);
                c.addAdress(facturation);
                c.addAdress(livraison);
                customerRepo.save(c);
            } else {
                c = customerRepo.getRandomCustomer(NB_ORDER);
                for (Adress a : c.getAdresses()) {
                    if(a.isBilling())
                        facturation = a;
                    else
                        livraison = a;
                }
                inscription = new Timestamp(c.getCreatedAt().getTime());
            }
            Order o = new Order();
            Double subTotal = 0D;
            Integer qtTotal = 0;
            for (int j = 0; j < nbDetails; j++) {
                int quantity = (new Random().nextInt(4))+1;
                int idxProduct = new Random().nextInt(products.size());
                double price = products.get(idxProduct).getPrice();
                if(currency != "EUR") {
                    price = convert(price,currency);
                }
                OrderDetail od = new OrderDetail(++odId,quantity,price,products.get(idxProduct));
                o.addOrderDetail(od);
                subTotal += quantity * products.get(idxProduct).getPrice();
                qtTotal += quantity;
            }
            o.setCurrency(currency);
            o.setCustomer(c);
            Timestamp priseCommande = getRandomDate(formatter.format(inscription));
            o.setCreatedAt(priseCommande);
            Timestamp paiement = (Timestamp) priseCommande.clone();
            paiement.setTime((long)o.getCreatedAt().getTime()+(new Random().nextInt(3600)*1000));
            o.setPaidAt(paiement);
            /*Double shippingfees = getShippingFees(qtTotal, livraison.getCountry().getName());
            if(currency != "EUR") {
                shippingfees = convert(shippingfees,currency);
            }
            o.setShippingFees(shippingfees);*/
            o.setShippingFees(15D);
            /*Double taxes = getTaxes(subTotal, facturation.getCountry().getCode());
            if(currency != "EUR") {
                taxes = convert(taxes,currency);
            }
            o.setTaxes(taxes);*/
            o.setTaxes(10D);
            o.setJpaId(i);
            orderRepo.save(o);
        }
        System.out.println("==============Fin insertion==============");
    }
    
    private void createCountryCollFromFile() throws IOException, URISyntaxException {
        countryRepo.deleteAll();
        BasicDBList countries = (BasicDBList) JSON.parse(new String(Files.readAllBytes(
                Paths.get(getClass().getResource("/json_files/Country.json")
                        .toURI())), 
                StandardCharsets.UTF_8));
        for (Object country : countries) {
            countryRepo.insert(mongoTemplate.getConverter().read(Country.class,(DBObject) country));
        }
    }
    
    private void createProductCollFomFile() throws IOException, URISyntaxException {
        productRepo.deleteAll();
        BasicDBList products = (BasicDBList) JSON.parse(new String(Files.readAllBytes(
                Paths.get(getClass().getResource("/json_files/Product.json")
                        .toURI())), 
                StandardCharsets.UTF_8));
        for (Object product : products) {
            productRepo.insert(mongoTemplate.getConverter().read(Product.class,(DBObject) product));
        }
    }
    
    private Double convert(Double price, String trgtCurrency) throws CurrenciesWSException_Exception {
        ICurrencyConverter client = new CurrencyConverterService().getCurrencyConverterPort();
        return client.convert(price, "EUR", trgtCurrency);
    }
    
    /*private Double getShippingFees(Integer quantity, String pays) throws CurrenciesWSException_Exception, DeliveriesWSException_Exception {
        IDeliveryCalculator client = new DeliveryCalculatorService().getDeliveryCalculatorPort();
        DeliveryQuantity dq = new DeliveryQuantity();
        dq.setQuantity(quantity);
        dq.setSrcCountryName(pays);
        return client.getRateDeliveryTotal(dq);
    }
    
    private double getTaxes(Double totalHt, String cntryISO) throws TaxWSException_Exception {
        ServiceTax client = new ServiceTaxBeanService().getServiceTaxBeanPort();
        return client.calculTax(totalHt, cntryISO);
    }*/
    
    private Timestamp getRandomDate() {
        long offset = Timestamp.valueOf("2016-07-18 00:00:00").getTime();
        long end = (new Date()).getTime();
        long diff = end - offset + 1;
        return new Timestamp(offset + (long)(Math.random() * diff));
    }
    
    private Timestamp getRandomDate(String strdate) {
        long offset = Timestamp.valueOf(strdate).getTime();
        long end = (new Date()).getTime();
        long diff = end - offset + 1;
        return new Timestamp(offset + (long)(Math.random() * diff));
    }
}
