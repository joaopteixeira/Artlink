package thalia.atec.thaliaPrototipo.Controller;

import java.awt.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import thalia.atec.thaliaPrototipo.Functions.FPost;
import thalia.atec.thaliaPrototipo.Functions.FUser;
import thalia.atec.thaliaPrototipo.Service.FileStorageService;
import thalia.atec.thaliaPrototipo.Service.LoginRepository;
import thalia.atec.thaliaPrototipo.Service.UserRepository;
import thalia.atec.thaliaPrototipo.UploadFile.UploadFileResponse;
import thalia.atec.thaliaPrototipo.model.Login;
import thalia.atec.thaliaPrototipo.model.User;


@Controller
public class WebProfile {
	
	@Autowired
	FPost  fpost;
	
	@Autowired
	FUser fuser;
	
	@Autowired
	LoginRepository logingrep;
	
	@Autowired
	UserRepository userRepo;
	
	
	@Autowired
	FileStorageService filestorageservice;
	

	@PostMapping("/editprofile")
	public String editprofile(HttpSession session,@RequestParam("firstname") String firstname ,
			@RequestParam("lastname") String lastname,@RequestParam("website") String website,@RequestParam("description") String description,@RequestParam("district") String district,@RequestParam("phonenumber") String phonenumber,@RequestParam("country") String country,Model page ) {
	

	User u = (User)session.getAttribute("User");

	
	u.setFirstname(firstname);
	u.setLastname(lastname);
	u.setDistrict(district);
	u.setCountry(country);
	u.setCountry(phonenumber);
	u.setCountry(website);
	u.setCountry(description);
	userRepo.save(u);
	
	
	
	

	return "redirect:/feed?main=perfil&frag=timeline&personid="+u.getId();
	
	
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
			return "suceso.html";
			
		
		
	}
	System.out.println("Não Mudou "+newpassword);
	 return "redirect:/feed?main=perfil&frag=password";
	
}

@PostMapping("/newphoto")
public String uploadPost(@RequestParam("file") MultipartFile file,@RequestParam("hash") String hash, Model page,HttpSession session){
	
	System.out.println(hash);
	
	Optional<User> user = userRepo.findByHashes(hash);
	
	if(user.isPresent()) {
		String fileName = filestorageservice.storeFile(file);
    	

        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("upload/downloadFile/")
                .path(fileName)
                .toUriString();     
        System.out.println(fileDownloadUri);
        
        user.get().setPathimage(fileDownloadUri);
        
        userRepo.save(user.get());
        
        
        
        page.addAttribute("pathimage", fileDownloadUri);
        
        return "redirect:/feed?main=perfil&&frag=profile&personid="+user.get().getId();
		
	}
	
	
	return "redirect:/feed?main=perfil&&frag=profile&personid="+user.get().getId();

}


}



