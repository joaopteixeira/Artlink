package thalia.atec.thaliaPrototipo.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import thalia.atec.thaliaPrototipo.model.Post;

public interface PostRepository extends MongoRepository<Post, String> {
	
	
	Optional<Post> findById(String id);
	List<Post> findByIduser(String iduser);
	
	List<Post> findByCreatorDistrict(String district);
	List<Post> findByCreatorCategoryDescriptionAndCreatorCategorySubCategoryDescription(String discription,String subCategoryDiscription);
	List<Post> findByCreatorCategoryDescription(String discription);

}
