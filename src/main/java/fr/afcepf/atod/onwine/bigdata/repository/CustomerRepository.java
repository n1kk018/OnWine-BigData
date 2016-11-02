package fr.afcepf.atod.onwine.bigdata.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import fr.afcepf.atod.onwine.bigdata.dao.api.CustomerRepositoryCustom;
import fr.afcepf.atod.onwine.bigdata.domain.Customer;

public interface CustomerRepository extends MongoRepository<Customer, String>, CustomerRepositoryCustom {

}
