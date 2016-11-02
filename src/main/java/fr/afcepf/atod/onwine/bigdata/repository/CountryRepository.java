package fr.afcepf.atod.onwine.bigdata.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import fr.afcepf.atod.onwine.bigdata.domain.Country;

public interface CountryRepository extends MongoRepository<Country, String> {

}
