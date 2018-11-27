package thalia.atec.thaliaPrototipo.model;

public class Watch {
	
	
	
	String iduser;
	String data;
	public Watch(String iduser, String data) {
		super();
		this.iduser = iduser;
		this.data = data;
	}

	

	public String getIduser() {
		return iduser;
	}



	public void setIduser(String iduser) {
		this.iduser = iduser;
	}



	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	
	

}
