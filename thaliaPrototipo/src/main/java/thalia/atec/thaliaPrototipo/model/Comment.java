package thalia.atec.thaliaPrototipo.model;

public class Comment {
	
	//User user;
	
	String iduser;
	String content;
	String date;
	
	public Comment(String iduser, String content, String date) {
		super();
		this.iduser = iduser;
		this.content = content;
		this.date = date;
	}
	public String getIduser() {
		return iduser;
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
