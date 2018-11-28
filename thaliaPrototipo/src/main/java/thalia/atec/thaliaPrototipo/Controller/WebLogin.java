package thalia.atec.thaliaPrototipo.Controller;

import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.apache.commons.mail.EmailException;
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
public class WebLogin {
	
	
	
	@Autowired
	UserRepository userRepo;
	
	
	@Autowired
	FUser fuser;


	
	@RequestMapping(value="/registry", method=RequestMethod.POST)
	public String UserRegistry(@ModelAttribute("User") User u,@RequestParam("password") String password, Model page){

		
		System.out.println("");
		
		fuser.Registry(u,password);
		
	
		
		return "redirect:/index.html";     // 
	}
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public String UserLogin(@ModelAttribute("User") User u, Model page, @RequestParam("password") String password,HttpSession session){
		
		
	//	fuser.login(u.getEmail(), password);

		
		Optional<User> us = userRepo.findByHashes(fuser.login(u.getEmail(), password));
		
		if(us.isPresent()) {
			

			//page.addAttribute("User",us.get());
			session.setAttribute("User", us.get());

			return "redirect:/feed?frag=feed";
			
		}
		
		return "redirect:/index";
	}
	
	
	
	
	
	@RequestMapping(value="/resetPassword", method=RequestMethod.POST)
	public String resetPassword(@RequestParam("usermail") String usermail){
		
		try {
			
			fuser.sendEmailReset(usermail);	
			
		    }catch(Exception e) {			
		    		
		    	return "redirect:/index";   //Caso não meta nenhum que exista!
	     	}
		
				
	return "redirect:/index";      // Deu, enviou volta para o home
	}
	

}
