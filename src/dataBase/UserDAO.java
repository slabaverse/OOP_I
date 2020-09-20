package dataBase;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import UI_FX.Alert_FX;
import entities.DevEvents;
import entities.GameEvents;
import entities.Marketplace;
import entities.Post;
import entities.User;

public class UserDAO implements InterfaceDAO<User> {

    @Override
    public void add(User user) {
	try {
	    String sql = "INSERT INTO User (username, password, name, birthdate, relationship) VALUES ('"
		    + user.getUsername() + "','" + user.getPassword() + "','" + user.getName() + "','"
		    + user.getBirthdate() + "','" + user.getRelationship() + "')";
	    UtilBD.updateDB(sql);

	} catch (SQLException e) {
	    Alert_FX.error("{ COULDN'T ADD THIS USER }");
	}
    }

    @Override
    public void update(User user) {
	try {
	    String sql = "UPDATE User SET " + "username = '" + user.getUsername() + "', " + "password = '"
		    + user.getPassword() + "', " + "name = '" + user.getName() + "', " + "birthdate = '"
		    + user.getBirthdate() + "', " + "relationship = '" + user.getRelationship() + "' " + "WHERE id = "
		    + user.getId() + ";";
	    UtilBD.updateDB(sql);
	} catch (SQLException e) {
	    Alert_FX.error("{ COULDN'T SET USER }");
	}
    }

    @Override
    public void remove(User user) {
	try {
	    String sql = "DELETE FROM User WHERE id = '" + user.getId() + "'";
	    UtilBD.updateDB(sql);
	} catch (SQLException e) {
	    Alert_FX.error("{ COULDN'T REMOVE THIS USER }");
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
		String relationship = resultSet.getString("relationship");
		retrn.add(new User(id, username, password, name, birthdate, relationship));
	    }
	    resultSet.getStatement().close();
	} catch (SQLException e) {
	    Alert_FX.error("{ COULDN'T LIST USERS }");
	}
	return retrn;
    }

    public User get(Integer id) {
	User retrn = null;
	try {
	    String sql = "SELECT id, username FROM User WHERE id = " + id + ";";
	    ResultSet resultSet = UtilBD.consultDB(sql);
	    while (resultSet.next()) {
		Integer id2 = resultSet.getInt("id");
		String usrname = resultSet.getString("username");
		retrn = new User(id2, usrname);
	    }
	    resultSet.getStatement().close();
	} catch (SQLException e) {
	    Alert_FX.error("{ IMPOSSIBLE TO VIEW USER}");
	}
	return retrn;
    }

    public User get(User id) {
	User retrn = null;
	try {
	    String sql = "SELECT id, username FROM User WHERE id = " + id + ";";
	    ResultSet resultSet = UtilBD.consultDB(sql);
	    while (resultSet.next()) {
		Integer id2 = resultSet.getInt("id");
		String usrname = resultSet.getString("username");
		retrn = new User(id2, usrname);
	    }
	    resultSet.getStatement().close();
	} catch (SQLException e) {
	    Alert_FX.error("{ IMPOSSIBLE TO VIEW USER}");
	}
	return retrn;
    }

    public User getByName(String username) {
	User retrn = null;
	try {
	    String sql = "SELECT id, username FROM User WHERE username = '" + username + "'";
	    ResultSet resultSet = UtilBD.consultDB(sql);
	    while (resultSet.next()) {
		Integer id2 = resultSet.getInt("id");
		String usrname = resultSet.getString("username");
		retrn = new User(id2, usrname);
	    }
	    resultSet.getStatement().close();
	} catch (SQLException e) {
	    Alert_FX.error("{ IMPOSSIBLE TO VIEW USER}");
	}
	return retrn;
    }
    
	public List<User> getByName2(String username) {
		List<User> result = new ArrayList<User>();
		
		try {
			String sql = "SELECT username, password FROM User "
					+ "WHERE username = '" + username + "';";
			ResultSet resultSet = UtilBD.consultDB(sql);
			
			while (resultSet.next()) {
				String usrname = resultSet.getString("username");
				String password = resultSet.getString("password");
				result.add(new User(usrname, password));
			}
			
			resultSet.getStatement().close();
		} catch (SQLException e) {
		    Alert_FX.error("{ IMPOSSIBLE TO VIEW USER }");
		}
		
		return result;
	}

    public List<Marketplace> getMarketplace(User usr) {
	List<Marketplace> marketplace = new ArrayList<Marketplace>();
	try {
	    String sql = "SELECT mkt_fk FROM UserMarketplace WHERE user_fk = " + usr.getId() + ";";
	    ResultSet resultSet = UtilBD.consultDB(sql);
	    while (resultSet.next()) {
		sql = "SELECT id, username, product, price, description FROM Marketplace WHERE id = "
			+ resultSet.getInt("mkt_fk") + ";";
		ResultSet mktSet = UtilBD.consultDB(sql);
		Integer id = mktSet.getInt("id");
		User user = new User(mktSet.getString("username"));
		String product = mktSet.getString("product");
		Double price = mktSet.getDouble("price");
		String description = mktSet.getString("description");
		marketplace.add(new Marketplace(id, user, product, price, description));
		mktSet.getStatement().close();
	    }
	    resultSet.getStatement().close();
	} catch (SQLException e) {
	    Alert_FX.error("{ COULDN'T SHOW MARKETPLACE }");
	}
	return marketplace;
    }

    public List<Post> getPost(User usr) {
	List<Post> post = new ArrayList<Post>();
	try {
	    String sql = "SELECT post_fk FROM UserPost WHERE user_fk = " + usr.getId() + ";";
	    ResultSet resultSet = UtilBD.consultDB(sql);
	    while (resultSet.next()) {
		sql = "SELECT id, username, content FROM Post WHERE id = " + resultSet.getInt("post_fk") + ";";
		ResultSet postSet = UtilBD.consultDB(sql);
		Integer id = postSet.getInt("id");
		User user = new User(postSet.getString("username"));
		String content = postSet.getString("content");
		post.add(new Post(id, user, content));
		postSet.getStatement().close();
	    }
	    resultSet.getStatement().close();
	} catch (SQLException e) {
	    Alert_FX.error("{ COULDN'T SHOW POSTS }");
	}
	return post;
    }

    public List<DevEvents> getDevEvents(User usr) {
	List<DevEvents> devEvents = new ArrayList<DevEvents>();
	try {
	    String sql = "SELECT devevents_fk FROM UserDevEvents WHERE user_fk = " + usr.getId() + ";";
	    ResultSet resultSet = UtilBD.consultDB(sql);
	    while (resultSet.next()) {
		sql = "SELECT id, username, eventName, eventDate, eventLocal, eventDescription FROM DevEvents WHERE id = "
			+ resultSet.getInt("devevents_fk") + ";";
		ResultSet devSet = UtilBD.consultDB(sql);
		Integer id = devSet.getInt("id");
		User user = new User(devSet.getString("username"));
		String eventName = devSet.getString("eventName");
		String eventDate = devSet.getString("eventDate");
		String eventLocal = devSet.getString("eventLocal");
		String eventDescription = devSet.getString("eventDescription");

		devEvents.add(new DevEvents(id, user, eventName, eventDate, eventLocal, eventDescription));
		devSet.getStatement().close();
	    }
	    resultSet.getStatement().close();
	} catch (SQLException e) {
	    Alert_FX.error("{ COULDN'T SHOW DEV EVENTS }");
	}
	return devEvents;
    }

    public List<GameEvents> getGameEvents(User usr) {
	List<GameEvents> gameEvents = new ArrayList<GameEvents>();
	try {
	    String sql = "SELECT gameevents_fk FROM UserGameEvents WHERE user_fk = " + usr.getId() + ";";
	    ResultSet resultSet = UtilBD.consultDB(sql);
	    while (resultSet.next()) {
		sql = "SELECT id, username, eventName, eventDate, eventLocal, eventDescription, gameName FROM GameEvents WHERE id = "
			+ resultSet.getInt("gameevents_fk") + ";";
		ResultSet gameSet = UtilBD.consultDB(sql);
		Integer id = gameSet.getInt("id");
		User user = new User(gameSet.getString("username"));
		String eventName = gameSet.getString("eventName");
		String eventDate = gameSet.getString("eventDate");
		String eventLocal = gameSet.getString("eventLocal");
		String eventDescription = gameSet.getString("eventDescription");
		String gameName = gameSet.getString("gameName");

		gameEvents.add(new GameEvents(id, user, eventName, eventDate, eventLocal, eventDescription, gameName));
		gameSet.getStatement().close();
	    }
	    resultSet.getStatement().close();
	} catch (SQLException e) {
	    Alert_FX.error("{ COULDN'T SHOW GAME EVENTS }");
	}
	return gameEvents;
    }

    public List<User> getFollowed(User usr) {
	List<User> followed = new ArrayList<User>();
	try {
	    String sql = "SELECT followed_fk FROM Follow WHERE follower_fk = " + usr.getId() + ";";
	    ResultSet resultSet = UtilBD.consultDB(sql);
	    while (resultSet.next()) {
		sql = "SELECT id, username, name, birthdate, relationship FROM User WHERE id = "
			+ resultSet.getInt("followed_fk") + ";";
		ResultSet userSet = UtilBD.consultDB(sql);
		Integer id = userSet.getInt("id");
		String username = userSet.getString("username");
		String name = userSet.getString("name");
		String birthdate = userSet.getString("birthdate");
		String relationship = userSet.getString("relationship");

		followed.add(new User(id, username, name, birthdate, relationship));
		userSet.getStatement().close();
	    }
	    resultSet.getStatement().close();
	} catch (SQLException e) {
	    Alert_FX.error("{ COULDN'T SHOW FOLLOWED }");
	}
	return followed;
    }

    public int getLastId() {
	int id = 0;
	try {
	    String sql = "SELECT MAX(id) as id FROM User;";
	    ResultSet resultSet = UtilBD.consultDB(sql);
	    while (resultSet.next()) {
		id = resultSet.getInt("id");
	    }

	    resultSet.getStatement().close();
	} catch (SQLException e) {
	    Alert_FX.error("{ UNABLE TO DO TI }");
	}

	return id;
    }
}
