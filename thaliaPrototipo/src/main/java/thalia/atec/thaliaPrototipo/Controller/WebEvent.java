package thalia.atec.thaliaPrototipo.Controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import thalia.atec.thaliaPrototipo.Functions.FCategory;
import thalia.atec.thaliaPrototipo.Functions.FEvent;
import thalia.atec.thaliaPrototipo.model.Category;
import thalia.atec.thaliaPrototipo.model.District;
import thalia.atec.thaliaPrototipo.model.Event;
import thalia.atec.thaliaPrototipo.model.User;

@Controller
@RequestMapping("/wevent")
public class WebEvent {

	@Autowired
	FEvent fevent;
	
	@Autowired
	FCategory fcategory;
	
	
	
	@PostMapping("/newevent")
	public String newEvent(@RequestParam("title") String title,
			@RequestParam("iduser") String iduser,
			@RequestParam("district") String district,
			@RequestParam("description") String description,
			@RequestParam("address") String address,
			@RequestParam("date") String date,
			@RequestParam("category") String category,
			@RequestParam("subcategory") String subcategory,
			@RequestParam("pathimage") String pathimage,
			@RequestParam("username") String username,
			Model page,@RequestParam("personid") String personid,@RequestParam("main") String main,@RequestParam("frag") String frag,HttpSession session) {
		
		
		page.addAttribute("personid","you");
		
		Category cat = new Category(category,"");
		cat.getSubCategory().add(new Category(subcategory,""));
		
		
		District dist = new District(district);
		
		Event ev = new Event(title, iduser, dist, description, address, cat, date, pathimage, username);
		
		fevent.addEvent(ev.getIduser(), ev);
		
		 return "redirect:/feed?main=homepage&personid=you&frag=doevents";
		
	}
	
	@GetMapping("/getEventbyDistrict")
	public String getEventbyDistrict(Model model,@RequestParam("hash") String hash, @RequestParam("district") String district) {
	
		List<Event> ev = fevent.getEventByDistrict(hash, district);
		
		model.addAttribute("Event", ev);
		
		return "redirect:/feed?main=homepage&frag=frageventlist";
	}
	
	
/*	
	@RequestMapping(value="/applyEvent", method=RequestMethod.POST)
	public String resetPassword(){
		
		try {
			
			
			
			fuser.apllyEventEmail(usermail);	
			
		    }catch(Exception e) {			
		    		
		    	return "redirect:/index";   //Caso não meta nenhum que exista!
	     	}
		
				
	return "redirect:/index";      // Deu, enviou volta para o home
	}
	
	*/
}
