package thalia.atec.thaliaPrototipo.Controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import thalia.atec.thaliaPrototipo.Functions.FPost;
import thalia.atec.thaliaPrototipo.Functions.FUser;
import thalia.atec.thaliaPrototipo.model.Post;
import thalia.atec.thaliaPrototipo.model.User;

@Controller
@RequestMapping("/webhome")
public class WebHome {
	
	@Autowired
	FPost  fpost;
	
	@GetMapping("/index")
	public String index() {
		

		return "index.html";
	}
	
	@GetMapping("/feedmain")
	public String feedMain(@ModelAttribute("User") User u, Model page){
		
		List<Post> p = fpost.getPost();		
		
	//	p.getClass().getName();
		page.addAttribute("User",u);
		page.addAttribute("Post",p);

		return "feedmain.html";
	}
	
	
	
}
