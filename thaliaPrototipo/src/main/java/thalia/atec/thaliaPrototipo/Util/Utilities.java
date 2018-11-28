package thalia.atec.thaliaPrototipo.Util;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import thalia.atec.thaliaPrototipo.Functions.FUser;
import thalia.atec.thaliaPrototipo.Service.UserRepository;
import thalia.atec.thaliaPrototipo.model.User;

@Component
public interface Utilities{
	
	
	
	public static String getUsername(String iduser){
	
		
		UUser user = new UUser();
		
		
		
		
		return user.getuser(iduser);
	}



}


