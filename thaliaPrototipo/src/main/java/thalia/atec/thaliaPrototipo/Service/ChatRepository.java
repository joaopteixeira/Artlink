package thalia.atec.thaliaPrototipo.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import thalia.atec.thaliaPrototipo.model.Chat;
import thalia.atec.thaliaPrototipo.model.User;

public interface ChatRepository extends MongoRepository<Chat, String> {

	List<Chat> findByUsersId(String usersid);
	Optional<Chat> findById(String id);
	Optional<Chat> findByUsersIn(List<User> users);
	Optional<Chat> findByUsersIdAndUsersId(String usersid,String usersid1);
	//List<Chat> findByUsersId(String id);
}
