package thalia.atec.thaliaPrototipo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import thalia.atec.thaliaPrototipo.Functions.FUser;
import thalia.atec.thaliaPrototipo.Service.UserRepository;
import thalia.atec.thaliaPrototipo.model.User;

@RestController
@RequestMapping("webhome")
public class WebLogin {
	
	
	
	@Autowired
	UserRepository userRepo;

	
	@RequestMapping(value="/registry", method=RequestMethod.POST)
	public String UserRegistry(@ModelAttribute("User") User u,@RequestParam("password") String password, Model page){
		
		
		new FUser().Registry(u,password);
		
	
		
		return "redirect:/qualquercoisa.html";
	}
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public String UserLogin(@ModelAttribute("User") User u, Model page, @RequestParam("password") String password){
		

		



		page.addAttribute("User",u);
		
		return "redirect:/qualquercoisa.html";
	}
	
	@RequestMapping(value="/edit", method=RequestMethod.POST)
	public String UserEdit(@ModelAttribute("User") User u, Model page){
		

		
	
		
		userRepo.save(u);


		page.addAttribute("User",u);
		
		return "redirect:/qualquercoisa.html";
	}
	

}
