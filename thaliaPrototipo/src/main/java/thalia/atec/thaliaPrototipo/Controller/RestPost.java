package thalia.atec.thaliaPrototipo.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.annotation.MultipartConfig;

import org.bson.BsonDocument;
import org.bson.Document;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.conversions.Bson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.repository.query.QueryByExampleExecutor;
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


import com.mongodb.util.JSON;

import thalia.atec.thaliaPrototipo.Functions.FPost;
import thalia.atec.thaliaPrototipo.Service.FileStorageService;
import thalia.atec.thaliaPrototipo.Service.PostRepository;
import thalia.atec.thaliaPrototipo.Service.UserRepository;
import thalia.atec.thaliaPrototipo.UploadFile.UploadFileResponse;
import thalia.atec.thaliaPrototipo.model.Comment;
import thalia.atec.thaliaPrototipo.model.Post;
import thalia.atec.thaliaPrototipo.model.User;


@RestController
@RequestMapping("mposts")
public class RestPost {

	@Autowired
    private FileStorageService fileStorageService;
	

	
	@Autowired
	UserRepository urep;
	
	@Autowired
	FPost ffpost;
	
	@RequestMapping("/get")
	public ResponseEntity<?> getPosts(@RequestParam("iduser") String iduser,@RequestParam("page") String page,@RequestParam("size") String size) {
		
		Optional<User> user = urep.findById(iduser);
		
		try {
			
			if(user.isPresent()) {
				List<Post> aux = ffpost.getPost(iduser, Integer.valueOf(page),Integer.valueOf(size));
				
				return new ResponseEntity<>((aux.size()==0?"null":aux),HttpStatus.OK);
			}
			
		}catch(NumberFormatException e) {
			return new ResponseEntity<String>("null",HttpStatus.OK);
		}
		
		return new ResponseEntity<String>("null",HttpStatus.OK);
		
		
	}
	

	
	
	@GetMapping("/addcomment")
	public ResponseEntity<?> addComment(@RequestParam("hash") String hash,@RequestParam("idpost") String idpost,@RequestParam("content") String content){
		
		Post p = ffpost.addComment(idpost, hash, content);
		
		if(p!=null) {
			return new ResponseEntity<>(p,HttpStatus.ACCEPTED);
		}
		
		return new ResponseEntity<>("null",HttpStatus.OK);
		
		
		
	}
	
	@GetMapping("/getcomment")
	public ResponseEntity<?> getComment(@RequestParam("hash") String hash,@RequestParam("idcomment") String idcomment){
		
		Comment c = ffpost.getComment(hash, idcomment);
		
		if(c!=null) {
			return new ResponseEntity<>(c,HttpStatus.ACCEPTED);
		}
		
		return new ResponseEntity<>("null",HttpStatus.OK);
		
		
		
	}
	
	@GetMapping("/getcommentbysize")
	public ResponseEntity<?> getCommentBySize(@RequestParam("hash") String hash,@RequestParam("idpost") String idpost,@RequestParam("size") String size){
		
		Post p = ffpost.getPostBySizeComment(hash, idpost, Integer.valueOf(size));
		
		if(p!=null) {
			return new ResponseEntity<>(p.getComments(),HttpStatus.ACCEPTED);
		}
		
		return new ResponseEntity<>("null",HttpStatus.OK);
		
		
		
	}
	
	
	
	@GetMapping("/getcomments")
	public ResponseEntity<?> getComments(@RequestParam("hash") String hash,@RequestParam("idpost") String idpost){
		
		List<Comment> comments = ffpost.getComments(idpost, hash);
		
		if(comments!=null) {
			return new ResponseEntity<>(comments,HttpStatus.ACCEPTED);
		}
		
		return new ResponseEntity<>("null",HttpStatus.OK);
		
		
		
	}
	
	
	@PostMapping("/addpost")
	public ResponseEntity<String> addPost(@RequestBody Post post){
		
		return new ResponseEntity<>(ffpost.newPost(post),HttpStatus.ACCEPTED);
		
	}
	
	@GetMapping("/like")
	public ResponseEntity<String> like(@RequestParam("id_post") String id_post, @RequestParam("id_user") String id_user){
		
		ffpost.like(id_user, id_post);
		
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
