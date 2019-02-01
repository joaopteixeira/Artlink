
package thalia.atec.thaliaPrototipo.Service;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import thalia.atec.thaliaPrototipo.model.Event;

public interface EventRepository extends MongoRepository<Event, String> {
	
	List<Event> findByDistrictName(String name);
	List<Event> findByIduser(String iduser);
	
	

}
