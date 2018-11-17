package thalia.atec.thaliaPrototipo.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import thalia.atec.thaliaPrototipo.Service.PostRepository;
import thalia.atec.thaliaPrototipo.Service.UserRepository;
import thalia.atec.thaliaPrototipo.model.Media;
import thalia.atec.thaliaPrototipo.model.Post;
import thalia.atec.thaliaPrototipo.model.User;

@RestController
@RequestMapping("muser")
public class RestUser {

	
	@Autowired
	UserRepository urep;
	
	
	@Autowired
	PostRepository prep;
	
	
	
	
	@RequestMapping("/mockupdata")
	public void mockupdata() {
	
		User u = new User();
        u.setFirstname("João");
        u.setLastname("Vilares");
        u.setPathimage("https://media.licdn.com/dms/image/C4D03AQHZzUy4bh7AuQ/profile-displayphoto-shrink_800_800/0?e=1547683200&v=beta&t=jiwpA-lq0RmmwHKM7gaQVeIoqKIqU1DQHQH5opCzzdI");
       
        
        Media m = new Media(Media.IMAGE,"https://g.foolcdn.com/image/?url=https%3A%2F%2Fg.foolcdn.com%2Feditorial%2Fimages%2F502052%2Fbitcoin4.jpg&w=700&op=resize");

        User u1 = new User();
        u1.setFirstname("Sergio");
        u1.setLastname("Figueiredo");
        u1.setPathimage("https://media.licdn.com/dms/image/C5603AQGmHWvUiLqI-A/profile-displayphoto-shrink_800_800/0?e=1547683200&v=beta&t=v6-mJlyXfZz81dbDBQjA0oOvkyWiuvVXPU26hq_3OXo");
        
        Media m1 = new Media(Media.IMAGE,"https://improvephotography.com/wp-content/uploads/2018/07/Sunrise-in-Rhodes-by-Rick-McEvoy-Photography-001.jpg");


        User u2 = new User();
        u2.setFirstname("José");
        u2.setLastname("Nascimento");
        u2.setPathimage("https://media.licdn.com/dms/image/C4D03AQGYeUV6j-OWEw/profile-displayphoto-shrink_800_800/0?e=1547683200&v=beta&t=rLzTOp1NNBeQXhpn4RXAob6vfzSW4cnI9OZ31R3aOUs");
        
        Media m2 = new Media(Media.IMAGE,"https://boygeniusreport.files.wordpress.com/2017/05/water.jpg?quality=98&strip=all&w=782");
		
        urep.save(u);
        urep.save(u1);
        urep.save(u2);
        
        Post p = new Post("123", "16-11-218", "O bitcoin esta cada vez mais na moda!!!!", 2, 0, u, m);
        prep.save(p);
        
        Post p1 = new Post("123", "16-11-218", "Qu lindo por do sol!!!!", 10, 0, u1, m1);
        prep.save(p1);
        
        Post p2 = new Post("123", "16-11-218", "Adprp mar!!!!", 2, 0, u2, m2);
        prep.save(p2);
        
        
	}
}
