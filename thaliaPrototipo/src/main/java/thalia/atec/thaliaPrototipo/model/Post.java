package thalia.atec.thaliaPrototipo.model;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;

public class Post {

	@Autowired
	String id;
	
	String title,
	date,
	content;
	
	int likes,dislikes;
	
	User creator;
	
	ArrayList<Comment> comments;
	
	Media media;

	public Post(String title, String date, String content, int likes, int dislikes, User creator, Media media) {
		super();
		this.title = title;
		this.date = date;
		this.content = content;
		this.likes = likes;
		this.dislikes = dislikes;
		this.creator = creator;
		this.media = media;
		this.comments = new ArrayList<>();
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

	public int getLikes() {
		return likes;
	}

	public void setLikes(int likes) {
		this.likes = likes;
	}

	public int getDislikes() {
		return dislikes;
	}

	public void setDislikes(int dislikes) {
		this.dislikes = dislikes;
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
