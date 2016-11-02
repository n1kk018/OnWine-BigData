package fr.afcepf.atod.onwine.bigdata.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import fr.afcepf.atod.onwine.bigdata.dao.api.OrderRepositoryCustom;
import fr.afcepf.atod.onwine.bigdata.domain.Order;

public interface OrderRepository extends MongoRepository<Order, String>, OrderRepositoryCustom {

}
