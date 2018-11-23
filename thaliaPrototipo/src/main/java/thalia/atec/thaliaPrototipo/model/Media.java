package thalia.atec.thaliaPrototipo.model;

public class Media {
	
	public static final int IMAGE=0,VIDEO=1,SOUND=2;
	
	int typemedia;
	
	String pathfile;

	public Media(int typemedia, String pathfile) {
		super();
		this.typemedia = typemedia;
		this.pathfile = pathfile;
	}

	public int getTypemedia() {
		return typemedia;
	}

	public void setTypemedia(int typemedia) {
		this.typemedia = typemedia;
	}

	public String getPathfile() {
		return pathfile;
	}

	public void setPathfile(String pathfile) {
		this.pathfile = pathfile;
	}

	
	
	

}
