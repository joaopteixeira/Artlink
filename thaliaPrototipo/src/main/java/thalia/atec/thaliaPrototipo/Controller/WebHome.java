package thalia.atec.thaliaPrototipo.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import thalia.atec.thaliaPrototipo.Functions.FPost;
import thalia.atec.thaliaPrototipo.Functions.FUser;
import thalia.atec.thaliaPrototipo.Service.UserRepository;
import thalia.atec.thaliaPrototipo.Util.DateUtil;
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
	public String feedMain(Model page,@RequestParam("frag") String frag,HttpSession session){
		

		
		User u = (User)session.getAttribute("User");
		
		Optional<User> user = userrep.findById(u.getId());
		
		


		if(user.isPresent()) {
			
				
			session.setAttribute("User", user.get());
			
			
			
		if(frag.compareTo("feed")==0) {
			
			page.addAttribute("User",user.get());
			page.addAttribute("posts",fpost.getPost(u.getId(), 0, 0));
		
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
		
		
	
	
}
