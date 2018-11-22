package thalia.atec.thaliaPrototipo.Controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.annotation.MultipartConfig;

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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import thalia.atec.thaliaPrototipo.Functions.FPost;
import thalia.atec.thaliaPrototipo.Service.FileStorageService;
import thalia.atec.thaliaPrototipo.Service.PostRepository;
import thalia.atec.thaliaPrototipo.Service.UserRepository;
import thalia.atec.thaliaPrototipo.UploadFile.UploadFileResponse;
import thalia.atec.thaliaPrototipo.model.Post;
import thalia.atec.thaliaPrototipo.model.User;


@RestController
@RequestMapping("mposts")
public class RestPost {

	@Autowired
    private FileStorageService fileStorageService;
	
	@Autowired
	PostRepository prep;
	
	@Autowired
	UserRepository urep;
	
	@Autowired
	FPost fpost;
	
	@RequestMapping("/get")
	public ResponseEntity<List<Post>> getPosts() {
		
		
		return new ResponseEntity<List<Post>>(prep.findAll(),HttpStatus.OK);
		
	}
	
	@RequestMapping("/getuser")
	public ResponseEntity<List<Post>> getPosts123(@RequestParam("id") String id) {
		
		
		return new ResponseEntity<List<Post>>(fpost.getPost(),HttpStatus.OK);
		
	}
	
	
	@PostMapping("/addpost")
	public ResponseEntity<String> addPost(@RequestBody Post post){
		
		return new ResponseEntity<>(fpost.newPost(post),HttpStatus.ACCEPTED);
		
	}
	
	
	@GetMapping("/like")
	public ResponseEntity<String> like(@RequestParam("id_post") String id_post, @RequestParam("id_user") String id_user){
		
		fpost.like(id_user, id_post);
		
		return new ResponseEntity<String>("",HttpStatus.OK);
	}
	
	
	@PostMapping("/upload")
	public UploadFileResponse uploadPost(@RequestParam("file") MultipartFile file,@RequestParam("hash") String hash){
		
		System.out.println(hash);
		
		Optional<User> user = urep.findByHashes(hash);
		
		if(user.isPresent()) {
			String fileName = fileStorageService.storeFile(file);
	    	

	        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
	                .path("upload/downloadFile/")
	                .path(fileName)
	                .toUriString();     
	        System.out.println(fileDownloadUri);
	        
	        
	        return new UploadFileResponse(fileName, fileDownloadUri,
	                file.getContentType(), file.getSize());
			
		}
		
		
		return null;
		
		
		
	}
	
}
