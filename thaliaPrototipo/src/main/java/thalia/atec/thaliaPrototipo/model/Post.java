package thalia.atec.thaliaPrototipo.model;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;

public class Post {

	@Id
	String id;
	
	String title,
	date,
	content;
	
	ArrayList<String> likes;
	
	User creator;
	
	ArrayList<Comment> comments;
	
	Media media;
	
	

	

	public Post(String title, String date, String content, User creator) {
		super();
		this.title = title;
		this.date = date;
		this.likes = new ArrayList<>();
		this.content = content;
		this.creator = creator;
		this.comments = new ArrayList<>();
	}
	
	

	public Post() {
		super();
	}



	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	
	
	

	



	public ArrayList<String> getLikes() {
		return likes;
	}



	public void setLikes(ArrayList<String> likes) {
		this.likes = likes;
	}



	public User getCreator() {
		return creator;
	}

	public void setCreator(User creator) {
		this.creator = creator;
	}

	public Media getMedia() {
		return media;
	}

	public void setMedia(Media media) {
		this.media = media;
	}
	
	
	
	
	
}
