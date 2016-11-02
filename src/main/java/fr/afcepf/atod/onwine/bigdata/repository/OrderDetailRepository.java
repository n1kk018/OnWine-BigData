package fr.afcepf.atod.onwine.bigdata.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import fr.afcepf.atod.onwine.bigdata.domain.OrderDetail;

public interface OrderDetailRepository extends MongoRepository<OrderDetail, String> {

}
