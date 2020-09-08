package dataBase;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entities.Comment;
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
	    String sql = "INSERT INTO User VALUES ('" + user.getUsername() + "'," + user.getPassword() + "',"
		    + user.getName() + ",'" + user.getBirthdate() + ",'" + user.getRelationship()
		    + "')";
	    UtilBD.updateDB(sql);

	    for (Marketplace marketplace : user.getMarketplace()) {
		sql = "INSERT INTO UserMarketplace VALUES ('" + user.getId() + "'," + comment.getUser() + ",'"
			+ comment.getText() + "')";
		UtilBD.updateDB(sql);
	    }
	} catch (SQLException e) {
	    System.err.println("{ COULDN'T ADD THIS PRODUCT }");
	}
    }
    
    private List<Marketplace> marketplace;
    private List<Post> posts;
    private List<Follow> followers;
    private List<DevEvents> dev;
    private List<GameEvents> game;

    
    
    
    
    
    

    @Override
    public void update(User reference) {
	// TODO Auto-generated method stub

    }

    @Override
    public void remove(User reference) {
	// TODO Auto-generated method stub

    }
    
    
    
    
    
    
    
    
    

    @Override
    public List<User> all() {
	List<User> retrn = new ArrayList<User>();
	try {
	    String sql = "SELECT ID, Username, Product, Price, Description FROM Marketplace";
	    ResultSet resultSet = UtilBD.consultDB(sql);
	    while (resultSet.next()) {
		String username = resultSet.getString("username");
		String password = resultSet.getString("password");
		String name = resultSet.getString("name");
		String birthdate = resultSet.getString("birthdate");
		String relationship = resultSet.getString("relationship");
		retrn.add(new User(username, password, name, birthdate, relationship));
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
	    String sql = "SELECT ID, Username, Product, Price, Description FROM Marketplace\"; WHERE username = '" + username + "'";
	    ResultSet resultSet = UtilBD.consultDB(sql);
	    while (resultSet.next()) {
		String usrname = resultSet.getString("username");
		String password = resultSet.getString("password");
		String name = resultSet.getString("name");
		String birthdate = resultSet.getString("birthdate");
		String relationship = resultSet.getString("relationship");
		retrn = new User(usrname, password, name, birthdate, relationship);
	    }
	    resultSet.getStatement().close();
	} catch (SQLException e) {
	    System.err.println("{ IMPOSSIBLE TO CONSULT A PRODUCT AT DB }");
	}
	return retrn;
    }
}
