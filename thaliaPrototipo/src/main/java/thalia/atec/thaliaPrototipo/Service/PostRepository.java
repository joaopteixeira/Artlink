package thalia.atec.thaliaPrototipo.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import thalia.atec.thaliaPrototipo.model.Post;
import thalia.atec.thaliaPrototipo.model.User;

public interface PostRepository extends MongoRepository<Post, String> {
	
	
	Optional<Post> findById(String id);
	List<Post> findByIduser(String iduser);
	
	Page<Post> findAll(Pageable pageable);
	Optional<User> findByUserid(String id);

}
