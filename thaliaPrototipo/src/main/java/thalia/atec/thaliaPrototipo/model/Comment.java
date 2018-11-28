package thalia.atec.thaliaPrototipo.model;

public class Comment {
	
	//User user;
	
	String iduser;
	String username;
	String content;
	String imguser;
	String date;

	
	public Comment(String iduser,String username, String content, String date,String imguser) {
		super();
		this.iduser = iduser;
		this.username = username;
		this.imguser = imguser;
		this.content = content;
		this.date = date;
	}
	public String getIduser() {
		return iduser;
	}
	
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getImguser() {
		return imguser;
	}
	public void setImguser(String imguser) {
		this.imguser = imguser;
	}
	public void setIduser(String iduser) {
		this.iduser = iduser;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	
	
	
	

}
