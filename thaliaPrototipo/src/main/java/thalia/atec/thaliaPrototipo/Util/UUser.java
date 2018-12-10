package thalia.atec.thaliaPrototipo.Util;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import thalia.atec.thaliaPrototipo.Functions.FUser;
import thalia.atec.thaliaPrototipo.Service.UserRepository;
import thalia.atec.thaliaPrototipo.model.User;

public class UUser {
	
	
	@Autowired
	UserRepository urep;
	
	
	public String getuser(String id) {
		
		
		Optional<User> user = urep.findById(id);
		
		
		String fullname = user.get().getFirstname()+user.get().getLastname();
		
		return fullname;
	}

}
