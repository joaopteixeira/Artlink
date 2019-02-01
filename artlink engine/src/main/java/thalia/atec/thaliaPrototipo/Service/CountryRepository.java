package thalia.atec.thaliaPrototipo.Service;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import thalia.atec.thaliaPrototipo.model.Country;

public interface CountryRepository extends MongoRepository<Country, String> {
	
	
	Optional<Country> findByName(String name);
	
}
