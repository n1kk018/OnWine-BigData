package fr.afcepf.atod.onwine.bigdata.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import fr.afcepf.atod.onwine.bigdata.domain.Adress;

public interface AdressRepository extends MongoRepository<Adress, String> {

}
