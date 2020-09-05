package entities;

public class Comment {

    private Integer id;
    private User user;
    private String text;

    public Comment() {
	super();
    }

    public Comment(Integer id, User user, String text) {
	super();
	this.id = id;
	this.user = user;
	this.text = text;
    }

    public Comment(Integer id, String text) {
	super();
	this.id = id;
	this.text = text;
    }

    public Integer getId() {
	return id;
    }

    public void setId(Integer id) {
	this.id = id;
    }

    public User getUser() {
	return user;
    }

    public void setUser(User user) {
	this.user = user;
    }

    public String getText() {
	return text;
    }

    public void setText(String text) {
	this.text = text;
    }
}