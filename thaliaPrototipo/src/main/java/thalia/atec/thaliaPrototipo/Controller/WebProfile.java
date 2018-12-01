package thalia.atec.thaliaPrototipo.Controller;

import javax.servlet.http.HttpSession;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import thalia.atec.thaliaPrototipo.model.User;

public class WebProfile {
	
	
	

@PostMapping("/editProfile")	
public String NewSearch(Model page, HttpSession session) {
	
	
	page.addAttribute("User",(User)session.getAttribute("User"));
	
	
	
	
	
	

	  return "redirect:/";
}

	

}
