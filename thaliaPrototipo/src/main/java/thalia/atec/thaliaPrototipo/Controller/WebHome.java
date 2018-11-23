package thalia.atec.thaliaPrototipo.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
import thalia.atec.thaliaPrototipo.model.Post;
import thalia.atec.thaliaPrototipo.model.User;

@Controller
@RequestMapping("/webhome")
public class WebHome {
	
	@Autowired
	FPost  fpost;
	
	@Autowired
	UserRepository userrep;;
	
	@GetMapping("/index")
	public String index() {
		

		return "index.html";
	}
	
	@GetMapping("/feedmain")
	public String feedMain(@RequestParam("iduser") String iduser,Model page){
		

		//List<Post> p = fpost.getPost("asdasd",2);		

		//String iduser = u.getId();
		//List<Post> p = fpost.getPost(iduser, 2);		
		
		Optional<User> user = userrep.findById(iduser);
		
		if(user.isPresent()) {
			
			page.addAttribute("User",user.get());
			page.addAttribute("posts",fpost.getPost(iduser, 0, 0));
			
			return "feedmain.html";
			
		}

		
		
		//page.addAttribute("Post",p);

		return "redirect:/index";
	}
	
	
	
}
