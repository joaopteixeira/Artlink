package thalia.atec.thaliaPrototipo.Service;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import thalia.atec.thaliaPrototipo.model.Category;

public interface CategoryRepository extends MongoRepository<Category, String> {

	Optional<Category> findByDescription(String description);
	
}
