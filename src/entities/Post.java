package entities;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Post {

    private User user;
    private Integer idPost;
    private String content;

    private List<Comment> comments;

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
	this.comments = new ArrayList<>();
    }

    public Post(Integer idPost) {
	super();
	this.idPost = idPost;
	this.comments = new ArrayList<>();
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

    public List<Comment> getComments() {
	return comments;
    }

    public void addComment(Integer id, User user, String text) {
	comments.add(new Comment(id, user, text));
    }

    public void editComment(Integer id, String text) {
	for (int i = 0; i < comments.size(); i++) {
	    if (comments.get(i).getId() == id) {
		comments.get(i).setText(text);
	    }
	}
    }

    public void removeComment(Integer id) {
	for (int i = 0; i < comments.size(); i++) {
	    if (comments.get(i).getId() == id) {
		comments.remove(i);
	    }
	}
	System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
	System.out.println("{COMMENT DELETED}");
	System.out.println();
    }

    public void showComments() {
	for (int i = 0; i < comments.size(); i++) {
	    System.out.println("Comment #" + comments.get(i).getId() + " by " + comments.get(i).getUser().getName()
		    + " on " + date + " at " + time);
	    System.out.println("        " + comments.get(i).getText());
	    System.out.println();
	}
    }

}