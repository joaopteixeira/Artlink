package thalia.atec.thaliaPrototipo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import thalia.atec.thaliaPrototipo.Functions.FUser;
import thalia.atec.thaliaPrototipo.Service.UserRepository;
import thalia.atec.thaliaPrototipo.model.User;

@Controller
@RequestMapping("/webhome")
public class WebLogin {
	
	
	
	@Autowired
	UserRepository userRepo;
	
	
	@Autowired
	FUser fuser;


	
	@RequestMapping(value="/registry", method=RequestMethod.POST)
	public String UserRegistry(@ModelAttribute("User") User u,@RequestParam("password") String password, Model page){

		
		fuser.Registry(u,password);
		
	
		
		return "redirect:/index.html";     // vai para feed 
	}
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public String UserLogin(@ModelAttribute("User") User u, Model page, @RequestParam("password") String password){
		
		
		fuser.login(u.getEmail(), password);

		
		page.addAttribute("User",u);
		
		return "redirect:/newsfeed-main.html";   // vai para feed
		
	}
	
	
	
	
	@RequestMapping(value="/resetPassword", method=RequestMethod.GET)
	public String resetPassword(@RequestParam("usermail") String usermail){
		
		
		fuser.sendEmailReset(usermail);
		
		
		
		return "redirect:/index.html";      // vai para feed

		


		
	}
	

}
