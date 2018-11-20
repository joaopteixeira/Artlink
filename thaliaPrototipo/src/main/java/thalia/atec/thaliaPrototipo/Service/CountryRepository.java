package thalia.atec.thaliaPrototipo.Service;

import org.springframework.data.mongodb.repository.MongoRepository;

import thalia.atec.thaliaPrototipo.model.Country;

public interface CountryRepository extends MongoRepository<Country, String> {

}
