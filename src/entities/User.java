package entities;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import UI_FX.Alert_FX;

public class User {

    private Integer id;
    private String username;
    private String password;
    private String name;
    private String birthdate;
    private String relationship;

    private List<Marketplace> marketplace;
    private List<Post> posts;
    private List<Follow> followers;
    private List<DevEvents> dev;
    private List<GameEvents> game;

    LocalDateTime now = LocalDateTime.now();

    DateTimeFormatter formatterData = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    String date = formatterData.format(now);

    DateTimeFormatter formatterHora = DateTimeFormatter.ofPattern("HH:mm:ss");
    String time = formatterHora.format(now);

    public User() {
	super();
    }

    public User(Integer id, String username, String password, String name, String birthdate, String relationship) {
	super();
	this.id = id;
	this.username = username;
	this.password = password;
	this.name = name;
	this.birthdate = birthdate;
	this.relationship = relationship;
	this.marketplace = new ArrayList<>();
	this.posts = new ArrayList<>();
	this.followers = new ArrayList<>();
	this.dev = new ArrayList<>();
	this.game = new ArrayList<>();
    }
    
    public User(Integer id, String username, String name, String birthdate, String relationship) {
	super();
	this.id = id;
	this.username = username;
	this.name = name;
	this.birthdate = birthdate;
	this.relationship = relationship;
	this.marketplace = new ArrayList<>();
	this.posts = new ArrayList<>();
	this.followers = new ArrayList<>();
	this.dev = new ArrayList<>();
	this.game = new ArrayList<>();
    }
    
    public User(String username, String password, String name, String birthdate, String relationship) {
	super();
	this.username = username;
	this.password = password;
	this.name = name;
	this.birthdate = birthdate;
	this.relationship = relationship;
	this.marketplace = new ArrayList<>();
	this.posts = new ArrayList<>();
	this.followers = new ArrayList<>();
	this.dev = new ArrayList<>();
	this.game = new ArrayList<>();
    }

    public User(String username, String birthdate, String relationship) {
	this.username = username;
	this.birthdate = birthdate;
	this.relationship = relationship;
	this.marketplace = new ArrayList<>();
	this.posts = new ArrayList<>();
	this.followers = new ArrayList<>();
	this.dev = new ArrayList<>();
	this.game = new ArrayList<>();
    }
    
    public User(String username) {
	this.username = username;
    }
    
    public User(Integer id, String username) {
	super();
	this.id = id;
	this.username = username;
    }

    public User(String username, String password) {
	super();
	this.username = username;
	this.password = password;
    }
    
    public User(Integer id, String username, String password) {
	super();
	this.id = id;
	this.username = username;
	this.password = password;
    }

    public Integer getId() {
	return id;
    }

    public void setId(Integer id) {
	this.id = id;
    }

    public String getUsername() {
	return username;
    }

    public void setUsername(String username) {
	this.username = username;
    }

    public String getPassword() {
	return password;
    }

    public void setPassword(String password) {
	this.password = password;
    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public String getBirthdate() {
	return birthdate;
    }

    public void setBirthdate(String birthdate) {
	this.birthdate = birthdate;
    }

    public String getRelationship() {
	return relationship;
    }

    public void setRelationship(String relationship) {
	this.relationship = relationship;
    }

    public List<Marketplace> getMarketplace() {
	return marketplace;
    }

    public List<Post> getPosts() {
	return posts;
    }

    public List<Follow> getFollowers() {
	return followers;
    }

    public List<DevEvents> getDev() {
	return dev;
    }

    public List<GameEvents> getGame() {
	return game;
    }

    // MARKETPLACE

    public void showMarket() {
	for (int i = 0; i < marketplace.size(); i++) {
	    System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
	    System.out.println(
		    "#" + marketplace.get(i).getId() + " - " + marketplace.get(i).getProduct() + " Posted By " + name);
	    System.out.println("Price ($): " + marketplace.get(i).getPrice());
	    System.out.println("Description: " + marketplace.get(i).getDescription());
	    System.out.println();
	}
    }

    public void showYourMarket() {
	for (int i = 0; i < marketplace.size(); i++) {
	    System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
	    System.out.println(
		    "#" + marketplace.get(i).getId() + " - " + marketplace.get(i).getProduct() + " Posted By " + name);
	    System.out.println("Price ($): " + marketplace.get(i).getPrice());
	    System.out.println("Description: " + marketplace.get(i).getDescription());
	    System.out.println();
	}
    }

    public void addProduct(User user, Integer id, String product, Double price, String description) {
	marketplace.add(new Marketplace(user, id, product, price, description));
    }

    public void editProductName(Integer id, String product) {
	for (int i = 0; i < marketplace.size(); i++) {
	    if (marketplace.get(i).getId() == id) {
		marketplace.get(i).setProduct(product);
	    }
	}
    }

    public void editProductPrice(Integer id, Double price) {
	for (int i = 0; i < marketplace.size(); i++) {
	    if (marketplace.get(i).getId() == id) {
		marketplace.get(i).setPrice(price);
	    }
	}
    }

    public void editProductDescription(Integer id, String description) {
	for (int i = 0; i < marketplace.size(); i++) {
	    if (marketplace.get(i).getId() == id) {
		marketplace.get(i).setDescription(description);
	    }
	}
    }

    public void removeProduct(Integer id) {
	for (int i = 0; i < marketplace.size(); i++) {
	    if (marketplace.get(i).getId() == id) {
		marketplace.remove(i);
	    }
	}
	Alert_FX.info("{PRODUCT DELETED}");
    }

    // POSTS

    public void showPosts() {
	System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
	System.out.println("                                                                  {TIMELINE}");
	for (int i = 0; i < posts.size(); i++) {
	    System.out.println();
	    System.out.println("Post #" + posts.get(i).getIdPost() + " by " + name + " on " + date + " at " + time);
	    System.out.println("        " + posts.get(i).getContent());

	}
    }

    public void showYourPosts() {
	for (int i = 0; i < posts.size(); i++) {
	    System.out.println();
	    System.out.println("Post #" + posts.get(i).getIdPost() + " by " + name + " on " + date + " at " + time);
	    System.out.println("        " + posts.get(i).getContent());
	    System.out.println();
	}
    }

    public void addPost(User user, Integer idPost, String content) {
	posts.add(new Post(user, idPost, content));
    }

    public void editPost(Integer idPost, String content) {
	for (int i = 0; i < posts.size(); i++) {
	    if (posts.get(i).getIdPost() == idPost) {
		posts.get(i).setContent(content);
	    }
	}
    }

    public void removePost(Integer idPost) {
	for (int i = 0; i < posts.size(); i++) {
	    if (posts.get(i).getIdPost() == idPost) {
		posts.remove(i);
	    }
	}

	Alert_FX.info("{POST DELETED}");
    }


    // DEV EVENTS

    public void showDevEvents() {
	for (int i = 0; i < dev.size(); i++) {
	    System.out.println();
	    System.out.println("Dev Event #" + dev.get(i).getEventId() + " " + " by " + name + ", created on " + date
		    + " at " + time);
	    System.out.println("We presents to you " + dev.get(i).getEventName() + "!");
	    System.out.println("Save the date " + dev.get(i).getEventDate() + " at the " + dev.get(i).getEventLocal());
	    System.out.println("Info " + dev.get(i).getEventDescription());
	}
    }

    public void showYourDevEvents() {
	for (int i = 0; i < dev.size(); i++) {
	    System.out.println();
	    System.out.println("Dev Event #" + dev.get(i).getEventId() + " " + " by " + name + " created on " + date
		    + " at " + time);
	    System.out.println("We presents to you " + dev.get(i).getEventName() + "!");
	    System.out.println("Save the date " + dev.get(i).getEventDate() + " at the " + dev.get(i).getEventLocal());
	    System.out.println("Info: " + dev.get(i).getEventDescription());
	    System.out.println();
	}
    }

    public void addDevEvent(User user, Integer eventId, String eventName, String eventDate, String eventLocal,
	    String eventDescription) {
	dev.add(new DevEvents(user, eventId, eventName, eventDate, eventLocal, eventDescription));
    }

    public void editDevEventName(Integer eventId, String eventName) {
	for (int i = 0; i < dev.size(); i++) {
	    if (dev.get(i).getEventId() == eventId) {
		dev.get(i).setEventName(eventName);
	    }
	}
    }

    public void editDevEventDate(Integer eventId, String eventDate) {
	for (int i = 0; i < dev.size(); i++) {
	    if (dev.get(i).getEventId() == eventId) {
		dev.get(i).setEventDate(eventDate);
	    }
	}
    }

    public void editDevEventLocal(Integer eventId, String eventLocal) {
	for (int i = 0; i < dev.size(); i++) {
	    if (dev.get(i).getEventId() == eventId) {
		dev.get(i).setEventLocal(eventLocal);
	    }
	}
    }

    public void editDevEventDescription(Integer eventId, String eventDescription) {
	for (int i = 0; i < dev.size(); i++) {
	    if (dev.get(i).getEventId() == eventId) {
		dev.get(i).setEventDescription(eventDescription);
	    }
	}
    }

    public void removeDevEvent(Integer eventId) {
	for (int i = 0; i < dev.size(); i++) {
	    if (dev.get(i).getEventId() == eventId) {
		dev.remove(i);
	    }
	}
	Alert_FX.info("{EVENT DELETED}");
    }

//GAME EVENTS

    public void showGameEvents() {
	System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
	for (int i = 0; i < game.size(); i++) {
	    System.out.println();
	    System.out.println("Game Event #" + game.get(i).getEventId() + " " + game.get(i).getEventName() + " by "
		    + name + ", created on " + date + " at " + time);
	    System.out
		    .println("Save the date " + game.get(i).getEventDate() + " at the " + game.get(i).getEventLocal());
	    System.out.println("Game: " + game.get(i).getGameName());
	    System.out.println("Info: " + game.get(i).getEventDescription());
	    System.out.println();
	}
    }

    public void showYourGameEvents() {
	System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
	for (int i = 0; i < game.size(); i++) {
	    System.out.println();
	    System.out.println("Game Event #" + game.get(i).getEventId() + " " + game.get(i).getEventName() + " by "
		    + name + " created on " + date + " at " + time);
	    System.out
		    .println("Save the date " + game.get(i).getEventDate() + " at the " + game.get(i).getEventLocal());
	    System.out.println("Game: " + game.get(i).getGameName());
	    System.out.println("Info " + game.get(i).getEventDescription());
	    System.out.println();
	}
    }

    public void addGameEvent(User user, Integer eventId, String eventName, String eventDate, String eventLocal,
	    String eventDescription, String gameName) {
	game.add(new GameEvents(user, eventId, eventName, eventDate, eventLocal, eventDescription, gameName));
    }

    public void editGameEventName(Integer eventId, String eventName) {
	for (int i = 0; i < game.size(); i++) {
	    if (game.get(i).getEventId() == eventId) {
		game.get(i).setEventName(eventName);
	    }
	}
    }

    public void editGameEventDate(Integer eventId, String eventDate) {
	for (int i = 0; i < game.size(); i++) {
	    if (game.get(i).getEventId() == eventId) {
		game.get(i).setEventDate(eventDate);
	    }
	}
    }

    public void editGameEventLocal(Integer eventId, String eventLocal) {
	for (int i = 0; i < game.size(); i++) {
	    if (game.get(i).getEventId() == eventId) {
		game.get(i).setEventLocal(eventLocal);
	    }
	}
    }

    public void editGameEventDescription(Integer eventId, String eventDescription) {
	for (int i = 0; i < game.size(); i++) {
	    if (game.get(i).getEventId() == eventId) {
		game.get(i).setEventDescription(eventDescription);
	    }
	}
    }

    public void editGameEventGameName(Integer eventId, String gameName) {
	for (int i = 0; i < game.size(); i++) {
	    if (game.get(i).getEventId() == eventId) {
		game.get(i).setGameName(gameName);
	    }
	}
    }

    public void removeGameEvent(Integer eventId) {
	for (int i = 0; i < game.size(); i++) {
	    if (game.get(i).getEventId() == eventId) {
		game.remove(i);
	    }
	}
	Alert_FX.info("{EVENT DELETED}");
    }
}
