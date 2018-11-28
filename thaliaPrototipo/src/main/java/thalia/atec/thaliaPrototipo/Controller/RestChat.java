package thalia.atec.thaliaPrototipo.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import thalia.atec.thaliaPrototipo.Functions.FChat;
import thalia.atec.thaliaPrototipo.model.Chat;
import thalia.atec.thaliaPrototipo.model.Mensagem;

@RestController
@RequestMapping("mchat")
public class RestChat {
	
	
	@Autowired
	FChat fchat;
	
	@GetMapping("/get")
	public ResponseEntity<?> getChat(@RequestParam(name="hash",defaultValue="") String hash){
		
		List<Chat> chats = fchat.getChatsById(hash);
		
		if(!chats.isEmpty()) {
			return new ResponseEntity<List<Chat>>(chats,HttpStatus.OK);
		}
		
		
		return new ResponseEntity<>("null",HttpStatus.OK);
	}
	
	
	@GetMapping("/getchatbyid")
	public ResponseEntity<?> getChatById(@RequestParam("idchat") String idchat,@RequestParam(name="hash",defaultValue="") String hash){
		
		Chat c =fchat.getChatById(hash,idchat);
		
		if(c!=null) {
			return new ResponseEntity<Chat>(c,HttpStatus.OK);
		}
		
		return new ResponseEntity<>("null",HttpStatus.OK);
		
	}
	
	
	@GetMapping("/createchat")
	public ResponseEntity<?> createChat(@RequestParam(name="hash",defaultValue="") String hash,@RequestParam("iduser") String iduser){
		
		Chat c = fchat.createChat(hash, iduser);
		
		if(c!=null) {
			return new ResponseEntity<Chat>(c,HttpStatus.ACCEPTED);
		}
		
		
		return new ResponseEntity<>("null",HttpStatus.ACCEPTED);
		
		
		
		
	}
	
	
	@RequestMapping(value="/sendmsg",method=RequestMethod.GET)
	public ResponseEntity<?> sendMsg(@RequestParam(name="hash",defaultValue="") String hash,@RequestParam("idchat") String idchat,@RequestParam("msg") String msg){
		
		Chat chat =fchat.sendMsg(hash, idchat, msg);
		if(chat!=null) {
			return new ResponseEntity<Chat>(chat,HttpStatus.OK);
		}else {
			return new ResponseEntity<>("null",HttpStatus.OK);
		}
		
		
		
		
	}
	
	
}
