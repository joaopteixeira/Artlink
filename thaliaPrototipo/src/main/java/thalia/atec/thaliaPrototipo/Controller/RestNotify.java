package thalia.atec.thaliaPrototipo.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import thalia.atec.thaliaPrototipo.Functions.FNotify;
import thalia.atec.thaliaPrototipo.Service.UserRepository;
import thalia.atec.thaliaPrototipo.model.Notify;
import thalia.atec.thaliaPrototipo.model.User;

@RestController
@RequestMapping("mnotify")
public class RestNotify {

	
	@Autowired
	UserRepository urep;
	
	@Autowired
	FNotify fnotify;
	
	
	
	
	
	@GetMapping("/get")
	public ResponseEntity<?> notify(@RequestParam(name="hash",defaultValue="") String hash){
		
		Optional<User> user = urep.findByHashes(hash);
		
		if(user.isPresent()) {
			
			List<Notify> aux = fnotify.notify(hash);
			
			if(aux!=null) {
				return new ResponseEntity<List<Notify>>(aux,HttpStatus.OK);
			}
			
			return new ResponseEntity<>("null",HttpStatus.OK);
			
		}
		
	
		return new ResponseEntity<>("null",HttpStatus.OK);
		
	}
	
	@GetMapping("/getall")
	public ResponseEntity<?> notifyAll(@RequestParam(name="hash",defaultValue="") String hash){
		
		Optional<User> user = urep.findByHashes(hash);
		
		if(user.isPresent()) {
			
			List<Notify> aux = fnotify.notifyAll(hash);
			
			if(aux!=null) {
				return new ResponseEntity<List<Notify>>(aux,HttpStatus.OK);
			}
			
			return new ResponseEntity<>("null",HttpStatus.OK);
			
		}
		
	
		return new ResponseEntity<>("null",HttpStatus.OK);
		
	}
	
	
	
	
	
	
	
	
	
}
