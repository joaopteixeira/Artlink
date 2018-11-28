package thalia.atec.thaliaPrototipo.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import thalia.atec.thaliaPrototipo.Functions.FUser;
import thalia.atec.thaliaPrototipo.Service.PostRepository;
import thalia.atec.thaliaPrototipo.Service.UserRepository;
import thalia.atec.thaliaPrototipo.model.Country;
import thalia.atec.thaliaPrototipo.model.District;
import thalia.atec.thaliaPrototipo.model.Media;
import thalia.atec.thaliaPrototipo.model.Post;
import thalia.atec.thaliaPrototipo.model.User;

@RestController
@RequestMapping("muser")
public class RestUser {

	
	@Autowired
	UserRepository urep;
	
	
	@Autowired
	PostRepository prep;
	
	@Autowired
	FUser fuser;
	
	
	
	
	@GetMapping("/registry")
	public ResponseEntity<String> registry(@ModelAttribute User user,@RequestParam(name="password",defaultValue="") String password){
		
		
		String status = fuser.Registry(user, password);
		
		
		
		if(status.compareTo("Registado")==0) {
			
			return new ResponseEntity<String>("Registado",HttpStatus.ACCEPTED);
			
		}
		
		return new ResponseEntity<String>("nRegistado",HttpStatus.ACCEPTED);
		
		
		
	}
	@GetMapping("/getuser")
	public ResponseEntity<?> getuser(@RequestParam(name="hash",defaultValue="") String hash){
		
		
		Optional<User> user = urep.findByHashes(hash);
		
		if(user.isPresent()) {
			
			return new ResponseEntity<User>(user.get(),HttpStatus.ACCEPTED);
		}
		
		
		return new ResponseEntity<String>("null",HttpStatus.ACCEPTED);
		
		
		
	}
	
	@GetMapping("/getcountry")
	public ResponseEntity<List<Country>> getcountry(){
		
		
		return new ResponseEntity<List<Country>>(fuser.getCountry(),HttpStatus.OK);
	}
	
	/*
	
	@RequestMapping("/mockupdata")
	public void mockupdata() {
	
		User u = new User();
        u.setFirstname("João");
        u.setLastname("Vilares");
        u.setPathimage("https://media.licdn.com/dms/image/C4D03AQHZzUy4bh7AuQ/profile-displayphoto-shrink_800_800/0?e=1547683200&v=beta&t=jiwpA-lq0RmmwHKM7gaQVeIoqKIqU1DQHQH5opCzzdI");
       prep
        
        Media m = new Media(Media.IMAGE,"https://g.foolcdn.com/image/?url=https%3A%2F%2Fg.foolcdn.com%2Feditorial%2Fimages%2F502052%2Fbitcoin4.jpg&w=700&op=resize");

        User u1 = new User();
        u1.setFirstname("Sergio");
        u1.setLastname("Figueiredo");
        u1.setPathimage("https://media.licdn.com/dms/image/C5603AQGmHWvUiLqI-A/profile-displayphoto-shrink_800_800/0?e=1547683200&v=beta&t=v6-mJlyXfZz81dbDBQjA0oOvkyWiuvVXPU26hq_3OXo");
        
        Media m1 = new Media(Media.IMAGE,"https://improvephotography.com/wp-content/uploads/2018/07/Sunrise-in-Rhodes-by-Rick-McEvoy-Photography-001.jpg");


        User u2 = new User();
        u2.setFirstname("José");
        u2.setLastname("Nascimento");
        u2.setPathimage("https://media.licdn.com/dms/image/C4D03AQGYeUV6j-OWEw/profile-displayphoto-shrink_800_800/0?e=1547683200&v=beta&t=rLzTOp1NNBeQXhpn4RXAob6vfzSW4cnI9OZ31R3aOUs");
        
        Media m2 = new Media(Media.IMAGE,"https://boygeniusreport.files.wordpress.com/2017/05/water.jpg?quality=98&strip=all&w=782");
		
        urep.save(u);
        urep.save(u1);
        urep.save(u2);
        
        
        
	} */
}
