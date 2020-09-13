package dataBase;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entities.Follow;
import entities.Post;

public class FollowDAO implements InterfaceDAO<Follow> {

    @Override
    public void add(Follow follow) {
	try {
	    String sql = "INSERT INTO Follow (username, birthdate, relationship) VALUES ('"
		    + follow.getUser().getUsername() + "'," + follow.getBirthdate() + "'," + follow.getRelationship()
		    + "')";
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
	    String sql = "DELETE FROM Follow WHERE username = '" + follow.getUser().getUsername() + "'";
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
		String birthdate = resultSet.getString("birthdate");
		String relationship = resultSet.getString("relationship");
		retrn.add(new Follow(new UserDAO().get(username).getUser(), birthdate, relationship));
	    }
	    resultSet.getStatement().close();
	} catch (SQLException e) {
	    System.err.println("{ COULDN'T LIST FRIENDS }");
	}
	return retrn;
    }

}
