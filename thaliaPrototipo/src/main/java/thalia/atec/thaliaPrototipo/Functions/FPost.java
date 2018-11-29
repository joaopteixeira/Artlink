package thalia.atec.thaliaPrototipo.Functions;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import thalia.atec.thaliaPrototipo.Service.PostRepository;
import thalia.atec.thaliaPrototipo.Service.UserRepository;
import thalia.atec.thaliaPrototipo.model.Comment;
import thalia.atec.thaliaPrototipo.model.Notify;
import thalia.atec.thaliaPrototipo.model.Post;
import thalia.atec.thaliaPrototipo.model.User;
import thalia.atec.thaliaPrototipo.model.Watch;
@Service("fpost")
public class FPost {
	
	
	@Autowired
	PostRepository prep;
	
	@Autowired
	UserRepository urep;
	
	@Autowired
	FNotify fnotify;
	
	
	
	
	 public String newPost(Post post){
		 
		 Optional<User> u = urep.findById(post.getIduser());
			boolean check=false;
			if(u.isPresent()) {
				
						check=true;
			}
			
			if(check) {
				DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");

				// Get the date today using Calendar object.
				Date today = Calendar.getInstance().getTime();        
				// Using DateFormat format method we can create a string 
				// representation of a date with the defined format.
				String reportDate = df.format(today);

				post.setDate(reportDate);
				
				prep.save(post);
				
				
				
				return "adicionado";
			}
		
		return "nadicionado";		 
	}
	 
	 /*
	 
	 public void savePost(Optional<Post> p) {
		 
		 prep.save(p);
	 }
	 
	 public Page<Post> getPostWatching(User user){
		 int cont=0;
		 
		 
		 for(Post p:prep.findAll()) {
			 
			 for(Watch w:user.getWatching()) {
				 if(p.getIduser().compareTo(w.getIduser())==0) {
					 aux.add(p);
					 
				 }
			 }
		 }
		 
		 return aux;
		 
	 }*/
	 
	 public User getuserbyid(String id) {
		 return urep.findById(id).get();
	 }
	 
	 
 public List<Post> getPost(String iduser,int page,int size){
		 
		 
		 Optional<Post> watching = prep.findById(iduser);
		 Optional<User> user = urep.findById(iduser);
		 ArrayList<Post> aux = new ArrayList<>();
		 boolean check=false;
		 int range=20;
		 
		 
		 
		 
		 
		 for(Post p:prep.findAll()) {
			 check=false;
			 
			 for(Watch w:user.get().getWatching()) {
				 if(p.getIduser().compareTo(w.getIduser())==0) {
					 Optional<User> u1 = urep.findById(w.getIduser());
					 p.setUserwatched(u1.get().getWatched().size());
					 p.setUsername(u1.get().getFirstname()+" "+u1.get().getLastname());
					 p.setUserimage(u1.get().getPathimage());
					 ArrayList<Comment> comments = new ArrayList<>();
					 for(Comment c:p.getComments()) {
						 Optional<User> usercom = urep.findById(c.getIduser());
						 c.setUsername(usercom.get().getFirstname()+" "+usercom.get().getLastname());
						 c.setImguser(usercom.get().getPathimage());
						 comments.add(c);
					 }
					 p.setComments(comments);
					 
					 aux.add(p);
					 check=true;
				 }
			 }
			 
			 
			 if(!check) {
				 List<User> userofmydistrict = urep.findByDistrict(user.get().getDistrict());
				 for(User u:userofmydistrict) {
					 if(p.getIduser().compareTo(u.getId())==0) {
						
						 p.setUserwatched(u.getWatched().size());
						 p.setUsername(u.getFirstname()+" "+u.getLastname());
						 p.setUserimage(u.getPathimage());
						 ArrayList<Comment> comments = new ArrayList<>();
						 for(Comment c:p.getComments()) {
							 Optional<User> usercom = urep.findById(c.getIduser());
							 c.setUsername(usercom.get().getFirstname()+" "+usercom.get().getLastname());
							 c.setImguser(usercom.get().getPathimage());
							 comments.add(c);
						 }
						 p.setComments(comments);
						 aux.add(p);
					 }
				 }
				 
				 
			 }
			 
			 
			 
			 
		 }
		 
		 
			 ArrayList<Post> aux2 = (ArrayList<Post>) prep.findAll();
			 boolean jata=false;
			 
			 for(Post p1:aux2) {
				 jata=false;
				 for(Post p2:aux) {
					 if(p1.getId().compareTo(p2.getId())==0) {
						 jata=true;
						 break;
					 }
				 }
				 if(!jata) {
					 Optional<User> u1 = urep.findById(p1.getIduser());
					 p1.setUserwatched(u1.get().getWatched().size());
					 p1.setUsername(u1.get().getFirstname()+" "+u1.get().getLastname());
					 p1.setUserimage(u1.get().getPathimage());
					 ArrayList<Comment> comments = new ArrayList<>();
					 for(Comment c:p1.getComments()) {
						 Optional<User> usercom = urep.findById(c.getIduser());
						 c.setUsername(usercom.get().getFirstname()+" "+usercom.get().getLastname());
						 c.setImguser(usercom.get().getPathimage());
						 comments.add(c);
					 }
					 p1.setComments(comments);
					 aux.add(p1);
				 }
				 
				 
			 }
			 
			 
			 //sort
			 
			 Collections.sort(aux, new Comparator<Post>() {
                 @Override
                 public int compare(Post o1, Post o2) {

                     Date d1 = null;
                     Date d2 = null;
                     DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
                     try {
                         d1 = df.parse(o1.getDate());
                         d2 = df.parse(o2.getDate());

                     } catch (ParseException e) {
                         e.printStackTrace();
                     }

                     return d2.compareTo(d1);
                 }
             });
			 
			 
			 
		 
		 
			 int qtd = aux.size()-size;
			 if(qtd==aux.size())
				 qtd=0;
		 
		 
		
		 
		 
		 if(aux.size()==0) {
			 return aux;
		 }else {
			 aux.get(((range*page+qtd)>(aux.size()-1))?(aux.size()-1):(range*page+qtd)).setTitle(String.valueOf(aux.size()));
			 return aux.subList(((range*page+qtd)>(aux.size()-1))?(aux.size()-1):(range*page+qtd), ((page*range+range)>(aux.size()-1))?(aux.size()-1):(page*range+range));
		 }
		 
		 
	 }
	 
	 
	 
	 public List<Post> findAllposts() {
		 
		 return prep.findAll();
	 }
	 
	 
	 
	 
	 
	 public Post addComment(String idpost,String hash,String content) {
		 
		 Optional<Post> post = prep.findById(idpost);
		 Optional<User> user = urep.findByHashes(hash);
		 
		 if(post.isPresent() && user.isPresent()) {
			 
			 DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");

				// Get the date today using Calendar object.
				Date today = Calendar.getInstance().getTime();        
				// Using DateFormat format method we can create a string 
				// representation of a date with the defined format.
				String reportDate = df.format(today);
			 Comment c = new Comment(UUID.randomUUID().toString(),user.get().getId(),user.get().getFirstname()+" "+user.get().getLastname(), content, reportDate, "");
			 post.get().getComments().add(c);
			 
			 prep.save(post.get());
			 
			 boolean check=false;
			 ArrayList<String> ids = new ArrayList<>();
			 for(Comment us:post.get().getComments()) {
				 check=false;
				 for(String i:ids) {
					 if(i.compareTo(us.getIduser())==0) {
						 check=true;
					 }
				 }
				if(!check) {
					ids.add(us.getIduser());
					
					Notify n = new Notify(Notify.TPOST, Notify.SUBTPCOMMENT,us.getIduser(), user.get().getId(), user.get().getFirstname()+" "+user.get().getLastname(), user.get().getPathimage(), Notify.NVISTO);
					n.setIdpost(post.get().getId());
					n.setIdcomment(us.getId());
					System.out.println(us.getId());
					
					fnotify.saveNotify(n);
				}
				
			 }
			 
			 
			 
				
			 
			 
		 }
		 
		 return null;
		 
	 }
	 
	 public List<Comment> getComments(String idpost,String hash) {
		 
		 Optional<Post> post = prep.findById(idpost);
		 Optional<User> user = urep.findByHashes(hash);
		 
		 ArrayList<Comment> comments = new ArrayList<>();
		 
		 if(post.isPresent() && user.isPresent()) {
			 
			 for(Comment c:post.get().getComments()) {
				 Optional<User> u = urep.findById(post.get().getIduser());
				 c.setImguser(u.get().getPathimage());
				 c.setUsername(u.get().getFirstname()+" "+u.get().getLastname());
				 comments.add(c);
			 }
			 return comments;
		 }
		 return null;
		 
	 }
	 
	 
	 
	 
	 
	public void like(String id_user,String  id_post) {
		
		System.out.println(id_user+"         ::   "+id_post);
		Optional<Post> p = prep.findById(id_post);
		Optional<User> u = urep.findById(id_user);
				
		boolean check = false;
		
		if(p.isPresent()) {
			System.out.println("p : presente");
			for(String ids:p.get().getLikes()) {
				if(ids.compareTo(id_user)==0) {
					System.out.println("id_user presente");
					check=true;
				}
			}
			
			if(!check && u.isPresent()) {
				p.get().getLikes().add(id_user);
				Optional<User> creator = urep.findById(p.get().getIduser());
				
				Notify n = new Notify(Notify.TPOST, Notify.SUBTPLIKE,p.get().getIduser() , id_user, creator.get().getFirstname()+" "+creator.get().getLastname(), creator.get().getPathimage(), Notify.NVISTO);
				n.setIdpost(id_post);
				
				fnotify.saveNotify(n);
				
				
				
			}else if(u.isPresent()) {
				p.get().getLikes().remove(id_user);
			}
			
			prep.save(p.get());
		}
					
		
	}

	public void savePost(Optional<Post> p) {
		prep.save(p.get());
		
	}
	 

	 

}
