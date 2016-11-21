package fr.afcepf.atod.onwine.bigdata.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import fr.afcepf.atod.onwine.bigdata.dao.api.OrderRepositoryCustom;
import fr.afcepf.atod.onwine.bigdata.domain.Customer;
import fr.afcepf.atod.onwine.bigdata.domain.Order;
import fr.afcepf.atod.onwine.bigdata.domain.Product;

public interface OrderRepository extends MongoRepository<Order, String>, OrderRepositoryCustom {
    List<Order> findByCustomerJpaId(Long id);
}
