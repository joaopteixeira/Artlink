package thalia.atec.thaliaPrototipo.Functions;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import thalia.atec.thaliaPrototipo.Service.UserRepository;
import thalia.atec.thaliaPrototipo.model.User;
import thalia.atec.thaliaPrototipo.model.Watch;


@Service("ffeed")
public class FFeed {
	
	@Autowired
	UserRepository userRep;

	
	public List<User> getFriends(String iduser) {
		
		List<User> users = new ArrayList<>();
	
		Optional<User> u = userRep.findById(iduser);	
		
		for(Watch w : u.get().getWatching()) {
			
			users.add(userRep.findById(w.getIduser()).get());
			
			
		}
		
		
		
		
		return users;
	}

}
