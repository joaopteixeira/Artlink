package thalia.atec.thaliaPrototipo.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import thalia.atec.thaliaPrototipo.Service.PostRepository;
import thalia.atec.thaliaPrototipo.model.Post;


@RestController
@RequestMapping("mposts")
public class RestPost {

	
	@Autowired
	PostRepository prep;
	
	@RequestMapping("/get")
	public ResponseEntity<List<Post>> getPosts() {
		
		
		return new ResponseEntity<List<Post>>(prep.findAll(),HttpStatus.OK);
		
	}
	
}
