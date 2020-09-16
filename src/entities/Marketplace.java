package entities;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Marketplace {

    private Integer id;
    private User user;
    private String product;
    private Double price;
    private String description;

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
    }

    public Marketplace(Integer id, String product, Double price, String description) {
	super();
	this.id = id;
	this.product = product;
	this.price = price;
	this.description = description;
    }

    public Marketplace(User user, String product, Double price, String description) {
	super();
	this.user = user;
	this.product = product;
	this.price = price;
	this.description = description;
    }

    public Marketplace(Integer id) {
	super();
	this.id = id;
    }

    public Marketplace(Integer id, User user, String product, Double price, String description) {
	super();
	this.id = id;
	this.user = user;
	this.product = product;
	this.price = price;
	this.description = description;
    }

    public Marketplace(String product, Double price, String description) {
	super();
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
}