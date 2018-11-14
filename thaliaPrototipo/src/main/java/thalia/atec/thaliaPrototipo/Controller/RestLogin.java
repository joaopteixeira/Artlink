package thalia.atec.thaliaPrototipo.Controller;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import thalia.atec.thaliaPrototipo.Functions.FUser;
import thalia.atec.thaliaPrototipo.Service.UserRepository;

@RestController
@RequestMapping("mlogin")
public class RestLogin {

	
	@Autowired
	UserRepository userRep;
	
	
	
	@RequestMapping(value="/validate", method=RequestMethod.POST)
	public ResponseEntity<String> getUser(@RequestParam("email") String email,@RequestParam("password") String password){
		
		String hash = new FUser().login(email, password);
		
		if(hash!=null) {
			return new ResponseEntity<String>(hash,HttpStatus.OK);
		}
		
		return null;
		
	}
	
	
	@RequestMapping(value="/registry", method=RequestMethod.POST)
	public ResponseEntity<String> getRegistry(
			
			@RequestParam("firstname") String firstname,
			@RequestParam("lastname") String lastname,
			@RequestParam("email") String email,
			@RequestParam("birthdate") String birthdate,
			@RequestParam("gender") String gender,
			@RequestParam("city") String city,
			@RequestParam("country") String country,
			@RequestParam("type") String type,
			@RequestParam("phonenumber") String phonenumber
	){
		
		
		
		
		
		String hash = new FUser().login(email, password);
		
		if(hash!=null) {
			return new ResponseEntity<String>(hash,HttpStatus.OK);
		}
		
		return null;
		
	}
	
	
}
