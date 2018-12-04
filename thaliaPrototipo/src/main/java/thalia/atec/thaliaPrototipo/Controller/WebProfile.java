package thalia.atec.thaliaPrototipo.Controller;

import java.awt.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import thalia.atec.thaliaPrototipo.Functions.FPost;
import thalia.atec.thaliaPrototipo.Functions.FUser;
import thalia.atec.thaliaPrototipo.Service.LoginRepository;
import thalia.atec.thaliaPrototipo.Service.UserRepository;
import thalia.atec.thaliaPrototipo.model.Login;
import thalia.atec.thaliaPrototipo.model.User;

public class WebProfile {
	
	@Autowired
	FPost  fpost;
	
	@Autowired
	FUser fuser;
	
	@Autowired
	LoginRepository logingrep;
	
	@Autowired
	UserRepository userRepo;
	

@PostMapping("/editProfile")	
public String NewSearch(Model page, HttpSession session) {
	
	
	page.addAttribute("User",(User)session.getAttribute("User"));
	
	
	
	
	
	

	  return "redirect:/";
}

	

@PostMapping("/newpassword")
public String newpassword(@RequestParam("password") String password ,@RequestParam("newpassword") String newpassword,Model page,HttpSession session){

	
	System.out.println("new pass");
	

	page.addAttribute("User",(User)session.getAttribute("User"));
	
	
	page.addAttribute("frag", "password");

	User u = (User)session.getAttribute("User");
	

		
		if(fuser.login(u.getEmail(),password)!=null){
						
			
			fuser.sendEmailNovaPassword(u.getEmail(),newpassword);
			
			System.out.println("Mudou pass"+newpassword);
			return "redirect:/index";
			
		
		
	}
	System.out.println("Não Mudou "+newpassword);
	 return "redirect:/feed?main=perfil&frag=password";
	
}


}



