package thalia.atec.thaliaPrototipo.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import thalia.atec.thaliaPrototipo.model.User;

@Controller
@RequestMapping("/webhome")
public class WebHome {

	
	@GetMapping("/index")
	public String index() {
		

		return "index.html";
	}
	
	@GetMapping("/feedmain")
	public String feedMain(@ModelAttribute("User") User u, Model page){
		
		page.addAttribute("User",u);

		return "feedmain.html";
	}
	
	
	
}
