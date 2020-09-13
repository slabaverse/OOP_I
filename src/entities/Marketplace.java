package entities;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Marketplace {

    private User user;
    private Integer id;
    private String product;
    private Double price;
    private String description;

    private List<Comment> comments;

    LocalDateTime now = LocalDateTime.now();

    DateTimeFormatter formatterData = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    String date = formatterData.format(now);

    DateTimeFormatter formatterHora = DateTimeFormatter.ofPattern("HH:mm:ss");
    String time = formatterHora.format(now);

    public Marketplace() {
	super();
    }

    public Marketplace(User user, Integer id, String product, Double price, String description) {
	super();
	this.user = user;
	this.id = id;
	this.product = product;
	this.price = price;
	this.description = description;
	this.comments = new ArrayList<>();
    }

    public Marketplace(Integer id, String product, Double price, String description) {
	super();
	this.id = id;
	this.product = product;
	this.price = price;
	this.description = description;
	this.comments = new ArrayList<>();
    }

    public Marketplace(Integer id) {
	super();
	this.id = id;
	this.comments = new ArrayList<>();
    }
    
    public Marketplace(Integer id, User user, String product, Double price, String description,
	    List<Comment> commentMarketplace) {
	super();
	this.id = id;
	this.user = user;
	this.product = product;
	this.price = price;
	this.description = description;
	this.comments = new ArrayList<>();
    }

    public Marketplace(Integer id, User user, String product, Double price, String description) {
	super();
	this.id = id;
	this.user = user;
	this.product = product;
	this.price = price;
	this.description = description;
    }


    public User getUser() {
	return user;
    }

    public Integer getId() {
	return id;
    }

    public void setId(Integer id) {
	this.id = id;
    }

    public String getProduct() {
	return product;
    }

    public void setProduct(String product) {
	this.product = product;
    }

    public double getPrice() {
	return price;
    }

    public void setPrice(Double price) {
	this.price = price;
    }

    public String getDescription() {
	return description;
    }

    public void setDescription(String description) {
	this.description = description;
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
	    System.out.println(comments.get(i).getText());
	    System.out.println();
	}
    }
}
