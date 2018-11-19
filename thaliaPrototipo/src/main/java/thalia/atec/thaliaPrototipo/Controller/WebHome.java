package thalia.atec.thaliaPrototipo.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebHome {

	
	@GetMapping("/index")
	public String index() {
		
		int views=0;
		
		System.out.printf("Views: ", views);
		
		views=+1;
		
		return "index.html";
	}
	
}
