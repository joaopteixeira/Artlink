package thalia.atec.thaliaPrototipo.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import thalia.atec.thaliaPrototipo.model.User;

public interface UserRepository extends MongoRepository<User, String> {
	
	
	Optional<User> findById(String id);
	List<User> findByDistrict(String district);
	List<User> findByFirstnameContaining(String firstname);
	List<User> findByCountry(String country);
	Optional<User> findByEmail(String email);
	Optional<User> findByHashes(String hashes);
	

}
