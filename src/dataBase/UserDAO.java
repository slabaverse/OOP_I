package dataBase;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entities.DevEvents;
import entities.Follow;
import entities.GameEvents;
import entities.Marketplace;
import entities.Post;
import entities.User;

public class UserDAO implements InterfaceDAO<User> {

    @Override
    public void add(User user) {
	try {
	    String sql = "INSERT INTO User (username, password, name, birthdate, relationship) VALUES ('"
		    + user.getUsername() + "'," + user.getPassword() + ",'" + user.getName() + ",'"
		    + user.getBirthdate() + ",'" + user.getRelationship() + "')";
	    UtilBD.updateDB(sql);

	} catch (SQLException e) {
	    System.err.println("{ COULDN'T ADD THIS USER }");
	}
    }

    @Override
    public void update(User user) {
	try {
	    String sql = "UPDATE User SET " + "username = '" + user.getUsername() + "', " + "password = '"
		    + user.getPassword() + "', " + "name = " + user.getName() + "', " + "birthdate = "
		    + user.getBirthdate() + "', " + "relationship = " + user.getRelationship() + " " + "WHERE id = "
		    + user.getId() + ";";
	    UtilBD.updateDB(sql);
	} catch (SQLException e) {
	    System.err.println("{ COULDN'T SET USER }");
	}
    }
    
    @Override
    public void remove(User user) {
	try {
	    String sql = "DELETE FROM User WHERE id = '" + user.getId() + "'";
	    UtilBD.updateDB(sql);
	} catch (SQLException e) {
	    System.err.println("{ COULDN'T REMOVE THIS USER }");
	}
    }

    @Override
    public List<User> all() {
	List<User> retrn = new ArrayList<User>();
	try {
	    String sql = "SELECT id, username, password, name, birthdate, relationship FROM User";
	    ResultSet resultSet = UtilBD.consultDB(sql);
	    while (resultSet.next()) {
		Integer id = resultSet.getInt("id");
		String username = resultSet.getString("username");
		String password = resultSet.getString("password");
		String name = resultSet.getString("name");
		String birthdate = resultSet.getString("birthdate");
		String relationship = resultSet.getString("relatioship");
		retrn.add(new User(id, username, password, name, birthdate, relationship));
	    }
	    resultSet.getStatement().close();
	} catch (SQLException e) {
	    System.err.println("{ COULDN'T LIST USERS }");
	}
	return retrn;
    }

    public User get(String username) {
	User retrn = null;
	try {
	    String sql = "SELECT id, username FROM User WHERE username = '" + username + "'";
	    ResultSet resultSet = UtilBD.consultDB(sql);
	    while (resultSet.next()) {
		Integer id = resultSet.getInt("id");
		String usrname = resultSet.getString("username");
		retrn = new User(id, usrname);
	    }
	    resultSet.getStatement().close();
	} catch (SQLException e) {
	    System.err.println("{ IMPOSSIBLE TO VIEW USER}");
	}
	return retrn;
    }

    public List<Marketplace> getMarketplace(User usr) {
	List<Marketplace> marketplace = new ArrayList<Marketplace>();
	try {
	    String sql = "SELECT user_fk FROM UserMarketplace WHERE mkt_fk = " + usr.getId() + ";";
	    ResultSet resultSet = UtilBD.consultDB(sql);
	    while (resultSet.next()) {
		sql = "SELECT id, username, product, price, description FROM Marketplace WHERE id = "
			+ resultSet.getInt("user_fk") + ";";
		ResultSet mktSet = UtilBD.consultDB(sql);
		Integer id = mktSet.getInt("id");
		User username = mktSet.getString("username");
		String product = mktSet.getString("product");
		String price = mktSet.getString("price");
		String description = mktSet.getString("description");
		marketplace.add(new Marketplace(id, username, product, price, description));
		mktSet.getStatement().close();
	    }
	    resultSet.getStatement().close();
	} catch (SQLException e) {
	    System.err.println("{ COULDN'T SHOW MARKETPLACE }");
	}
	return marketplace;
    }

    public List<Post> getPost(User usr) {
	List<Post> post = new ArrayList<Post>();
	try {
	    String sql = "SELECT user_fk FROM UserPost WHERE post_fk = " + usr.getId() + ";";
	    ResultSet resultSet = UtilBD.consultDB(sql);
	    while (resultSet.next()) {
		sql = "SELECT id, username, content FROM Post WHERE id = " + resultSet.getInt("user_fk") + ";";
		ResultSet postSet = UtilBD.consultDB(sql);
		Integer id = postSet.getInt("id");
		User username = postSet.getString("username");
		String content = postSet.getString("content");

		post.add(new Post(id, username, content));
		postSet.getStatement().close();
	    }
	    resultSet.getStatement().close();
	} catch (SQLException e) {
	    System.err.println("{ COULDN'T SHOW POSTS }");
	}
	return post;
    }

    public List<DevEvents> getDevEvents(User usr) {
	List<DevEvents> devEvents = new ArrayList<DevEvents>();
	try {
	    String sql = "SELECT user_fk FROM UserDevEvents WHERE devevents_fk = " + usr.getId() + ";";
	    ResultSet resultSet = UtilBD.consultDB(sql);
	    while (resultSet.next()) {
		sql = "SELECT id, username, eventName, eventDate, eventLocal, eventDescription FROM DevEvents WHERE id = "
			+ resultSet.getInt("user_fk") + ";";
		ResultSet devSet = UtilBD.consultDB(sql);
		Integer id = devSet.getInt("id");
		User username = devSet.getString("username");
		String eventName = devSet.getString("eventName");
		String eventDate = devSet.getString("eventDate");
		String eventLocal = devSet.getString("eventLocal");
		String eventDescription = devSet.getString("eventDescription");

		devEvents.add(new DevEvents(id, username, eventName, eventDate, eventLocal, eventDescription));
		devSet.getStatement().close();
	    }
	    resultSet.getStatement().close();
	} catch (SQLException e) {
	    System.err.println("{ COULDN'T SHOW DEV EVENTS }");
	}
	return devEvents;
    }

    private List<Follow> followers;

    public List<GameEvents> getGameEvents(User usr) {
	List<GameEvents> gameEvents = new ArrayList<GameEvents>();
	try {
	    String sql = "SELECT user_fk FROM UserGameEvents WHERE gameevents_fk = " + usr.getId() + ";";
	    ResultSet resultSet = UtilBD.consultDB(sql);
	    while (resultSet.next()) {
		sql = "SELECT id, username, eventName, eventDate, eventLocal, eventDescription, gameName  FROM GameEvents WHERE id = "
			+ resultSet.getInt("user_fk") + ";";
		ResultSet gameSet = UtilBD.consultDB(sql);
		Integer id = gameSet.getInt("id");
		User username = gameSet.getString("username");
		String eventName = gameSet.getString("eventName");
		String eventDate = gameSet.getString("eventDate");
		String eventLocal = gameSet.getString("eventLocal");
		String eventDescription = gameSet.getString("eventDescription");
		String gameName = gameSet.getString("gameName");

		gameEvents.add(
			new GameEvents(id, username, eventName, eventDate, eventLocal, eventDescription, gameName));
		gameSet.getStatement().close();
	    }
	    resultSet.getStatement().close();
	} catch (SQLException e) {
	    System.err.println("{ COULDN'T SHOW GAME EVENTS }");
	}
	return gameEvents;
    }

}
