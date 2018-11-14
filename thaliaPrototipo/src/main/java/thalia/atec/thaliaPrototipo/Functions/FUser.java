package thalia.atec.thaliaPrototipo.Functions;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;

import thalia.atec.thaliaPrototipo.Service.LoginRepository;
import thalia.atec.thaliaPrototipo.Service.UserRepository;
import thalia.atec.thaliaPrototipo.model.Login;
import thalia.atec.thaliaPrototipo.model.User;
 
public class FUser {                   //Funcoes pro USER  

	@Autowired
	UserRepository userRep;
	
	@Autowired
	LoginRepository loginRep;
	
	
	
	
	public FUser() {        
		super();
	}




	public String login(String email,String password) {
		
		String hash = UUID.randomUUID().toString();
		Optional<Login> login = loginRep.findByEmailAndPassword(email, password);
		if(login.get()!=null) {
			Optional<User> u = userRep.findByEmail(login.get().getEmail());
			u.get().getHashes().add(hash);
			userRep.save(u.get());
			return hash;
		}
		
		return null;
		
	}
	
	
	
}
