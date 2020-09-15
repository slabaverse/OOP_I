package dataBase;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entities.Follow;

public class FollowDAO implements InterfaceDAO<Follow> {

    @Override
    public void add(Follow follow) {
	try {
	    String sql = "INSERT INTO Follow (followed_fk, follower_fk) VALUES (" + follow.getFollow().getId() + ", " + follow.getFollower().getId() + ");";
	    UtilBD.updateDB(sql);

	} catch (SQLException e) {
	    System.err.println("{ COULDN'T ADD FRIEND }");
	}
    }

    @Override
    public void update(Follow reference) {
	// TODO Auto-generated method stub

    }

    @Override
    public void remove(Follow follow) {
	try {
	    String sql = "DELETE FROM Follow WHERE followed_fk = " + follow.getFollow().getId() + " AND follower_fk = " + follow.getFollower().getId() + ";";
	    UtilBD.updateDB(sql);
	} catch (SQLException e) {
	    System.err.println("{ COULDN'T REMOVE FRIEND }");
	}
    }

    @Override
    public List<Follow> all() {
	List<Follow> retrn = new ArrayList<Follow>();
	try {
	    String sql = "SELECT username, birthdate, relationship FROM Follow";
	    ResultSet resultSet = UtilBD.consultDB(sql);
	    while (resultSet.next()) {
		String username = resultSet.getString("username");
		retrn.add(new Follow(new UserDAO().getByName(username)));
	    }
	    resultSet.getStatement().close();
	} catch (SQLException e) {
	    System.err.println("{ COULDN'T LIST FRIENDS }");
	}
	return retrn;
    }
}
