package thalia.atec.thaliaPrototipo.Controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import thalia.atec.thaliaPrototipo.Functions.FPost;
import thalia.atec.thaliaPrototipo.Functions.FUser;
import thalia.atec.thaliaPrototipo.Service.UserRepository;
import thalia.atec.thaliaPrototipo.Util.DateUtil;
import thalia.atec.thaliaPrototipo.model.Comment;
import thalia.atec.thaliaPrototipo.model.Media;
import thalia.atec.thaliaPrototipo.model.Post;
import thalia.atec.thaliaPrototipo.model.User;

@Controller
public class WebHome {
	
	@Autowired
	FPost  fpost;
	
	@Autowired
	UserRepository userrep;;
	
	@GetMapping("/index")
	public String index() {
		

		return "index.html";
	}
	
	@GetMapping("/feed")
	public String feed(Model page,@RequestParam("frag") String frag,HttpSession session){
		

		
		User u = (User)session.getAttribute("User");
		
		Optional<User> user = userrep.findById(u.getId());
		
		

		if(user.isPresent()) {
			
			page.addAttribute("User",user.get());
			page.addAttribute("frag",frag);
			session.setAttribute("User", user.get());
			
			
			
		if(frag.compareTo("feed")==0) {
			

			System.out.println(user.get().getId());
			page.addAttribute("posts",fpost.getPost(user.get().getId(), 0, 0));
			
			
			
			return "feedmain.html";
		
			}
			
		  if(frag.compareTo("chat")==0) {

				page.addAttribute("User",(User)session.getAttribute("User"));
				
				
			}
			
		  
		  if(frag.compareTo("friends")==0) {

			    page.addAttribute("User",(User)session.getAttribute("User"));
				
		  }
			
		  
		  

			return "feedmain.html";
		}
		
		return "redirect:/index";
		
	}
	
////	@GetMapping("/newpost")
//	public String feed(@RequestParam("content") String content, HttpSession session){
//		
//		
//		User u = (User)session.getAttribute("User");
//		
//		Post p = new Post();
//		
//		
//	    LocalDateTime date =  LocalDateTime.now();  
//
//		
//		String username = u.getUsername() ;
//		
//		String iduser;
//		
//		Media media;
//		
//		
//	    p.setContent(content);
//		p.setUsername(username);
//		p.setDate(date.toString());
//
//		
//	
//		
//		 fpost.newPost(p);
//		
//		 return "redirect:/feed";
//	}
//	
	
	
@PostMapping("/newcomment")
public String newComment(Model page,@RequestParam("content") String content,@RequestParam("idpost") String idpost, HttpSession session){
	
	
	User u = (User)session.getAttribute("User");
	
	DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");

	// Get the date today using Calendar object.
	Date today = Calendar.getInstance().getTime();        
	// Using DateFormat format method we can create a string 
	// representation of a date with the defined format.
	String reportDate = df.format(today);
	
	Post p = fpost.getPost(idpost);

	
	p.getComments().add(new Comment(u.getId(), content, reportDate));
	
	fpost.savePost(p);
	
	
	   page.addAttribute("User",(User)session.getAttribute("User"));

	
	//String user = session.getAttribute();
	
	
	
	
	
	
  return "redirect:/feed";
}

	
	
	
}
