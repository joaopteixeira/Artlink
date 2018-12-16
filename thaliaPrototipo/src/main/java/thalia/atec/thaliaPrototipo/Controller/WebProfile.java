package thalia.atec.thaliaPrototipo.Controller;

import java.awt.List;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockMultipartFile;
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
	String hash = (String)session.getAttribute("hash");
	
	System.out.println(password);
	System.out.println(newpassword);

		String status = fuser.changePassword(hash, password, newpassword);
		
		if(status.compareTo("aceite")==0){
						
			
			fuser.sendEmailNovaPassword(u.getEmail(),newpassword);
			
			System.out.println("Mudou pass: "+newpassword);
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
		String fileName="";
		try {
			
			InputStream in = new ByteArrayInputStream(file.getBytes());
			BufferedImage image1 = ImageIO.read(in);
			//BufferedImage image = fuser.re();
			BufferedImage image = fuser.resizeImage(image1, 0);
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ImageIO.write(image, FilenameUtils.getExtension(file.getOriginalFilename()), baos );
			baos.flush();
			byte[] imageInByte = baos.toByteArray();
			baos.close();
			
			//MockMultipartFile mockMultipartFile = 
			
			MultipartFile multipartFile = new MockMultipartFile(file.getOriginalFilename(), imageInByte);
			
			
			
			fileName = filestorageservice.storeFile(multipartFile,FilenameUtils.getExtension(file.getOriginalFilename()));
		} catch (IllegalStateException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	

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



