package entities;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Post {

    private Integer idPost;
    private User user;
    private String content;

    LocalDateTime now = LocalDateTime.now();

    DateTimeFormatter formatterData = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    String date = formatterData.format(now);

    DateTimeFormatter formatterHora = DateTimeFormatter.ofPattern("HH:mm:ss");
    String time = formatterHora.format(now);

    public Post() {
	super();
    }

    public Post(User user, Integer idPost, String content) {
	super();
	this.user = user;
	this.idPost = idPost;
	this.content = content;
    }

    public Post(Integer idPost) {
	super();
	this.idPost = idPost;
    }

    public Post(Integer idPost, User user, String content) {
	super();
	this.idPost = idPost;
	this.user = user;
	this.content = content;
    }

    public Post(Integer idPost, String content) {
	super();
	this.idPost = idPost;
	this.content = content;
    }

    public Post(String content) {
	super();
	this.content = content;
    }

    public Post(User user, String content) {
	this.user = user;
	this.content = content;
    }

    public User getUser() {
	return user;
    }

    public Integer getIdPost() {
	return idPost;
    }

    public void setIdPost(Integer idPost) {
	this.idPost = idPost;
    }

    public String getContent() {
	return content;
    }

    public void setContent(String content) {
	this.content = content;
    }
}