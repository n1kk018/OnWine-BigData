package fr.afcepf.atod.onwine.bigdata.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import fr.afcepf.atod.onwine.bigdata.dao.api.ProductRepositoryCustom;
import fr.afcepf.atod.onwine.bigdata.domain.Product;

public interface ProductRepository extends MongoRepository<Product, String>, ProductRepositoryCustom {
    List<Product> findByProductVintage(String productVintage);
    List<Product> findByproductType(String productType);
    List<Product> findByProductVarietal(String productvarietal);
}
