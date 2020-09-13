package dataBase;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entities.Comment;
import entities.DevEvents;

public class DevEventsDAO implements InterfaceDAO<DevEvents> {

    @Override
    public void add(DevEvents devEvents) {
	try {
	    String sql = "INSERT INTO DevEvents (username, eventName, eventDate, eventLocal, eventDescription) VALUES ('"
		    + devEvents.getUser().getUsername() + "'," + devEvents.getEventName() + "',"
		    + devEvents.getEventDate() + "'," + devEvents.getEventLocal() + "',"
		    + devEvents.getEventDescription() + "')";
	    UtilBD.updateDB(sql);

	} catch (SQLException e) {
	    System.err.println("{ COULDN'T ADD THIS DEV EVENT }");
	}
    }

    @Override
    public void update(DevEvents devEvents) {
	try {
	    String sql = "UPDATE DevEvents SET " + "eventName = '" + devEvents.getEventName() + "', " + "eventDate = '"
		    + devEvents.getEventDate() + "', " + "eventLocal = '" + devEvents.getEventLocal() + "', "
		    + "eventDescription = '" + devEvents.getEventDescription() + " " + "WHERE id = "
		    + devEvents.getEventId() + ";";
	    UtilBD.updateDB(sql);
	} catch (SQLException e) {
	    System.err.println("{ COULDN'T SET THIS DEV EVENT }");
	}
    }

    @Override
    public void remove(DevEvents devEvents) {
	try {
	    String sql = "DELETE FROM DevEvents WHERE id = '" + devEvents.getEventId() + "'";
	    UtilBD.updateDB(sql);
	} catch (SQLException e) {
	    System.err.println("{ COULDN'T REMOVE THIS DEV EVENT }");
	}
    }

    @Override
    public List<DevEvents> all() {
	List<DevEvents> retrn = new ArrayList<DevEvents>();
	try {
	    String sql = "SELECT id, username, eventName, eventDate, eventLocal, eventDescription FROM DevEvents";
	    ResultSet resultSet = UtilBD.consultDB(sql);
	    while (resultSet.next()) {
		Integer id = resultSet.getInt("id");
		String username = resultSet.getString("username");
		String eventName = resultSet.getString("eventName");
		String eventDate = resultSet.getString("eventDate");
		String eventLocal = resultSet.getString("eventLocal");
		String eventDescription = resultSet.getString("eventDescription");
		retrn.add(new DevEvents(id, new UserDAO().get(username).getUser(), eventName, eventDate, eventLocal,
			eventDescription));
	    }
	    resultSet.getStatement().close();
	} catch (SQLException e) {
	    System.err.println("{ COULDN'T LIST GAME EVENTS }");
	}
	return retrn;
    }

    public DevEvents get(Integer id) {
	DevEvents retrn = null;
	try {
	    String sql = "SELECT id, username, eventName, eventDate, eventLocal, eventDescription FROM DevEvents WHERE id = '"
		    + id + "'";
	    ResultSet resultSet = UtilBD.consultDB(sql);
	    while (resultSet.next()) {
		Integer idDe = resultSet.getInt("id");
		String username = resultSet.getString("username");
		String eventName = resultSet.getString("eventName");
		String eventDate = resultSet.getString("eventDate");
		String eventLocal = resultSet.getString("eventLocal");
		String eventDescription = resultSet.getString("eventDescription");
		retrn = new DevEvents(idDe, new UserDAO().get(username).getUser(), eventName, eventDate, eventLocal,
			eventDescription);
	    }
	    resultSet.getStatement().close();
	} catch (SQLException e) {
	    System.err.println("{ IMPOSSIBLE TO VIEW A DEV EVENT }");
	}
	return retrn;
    }
    
    public List<Comment> getComments(DevEvents devevents) {
	List<Comment> comments = new ArrayList<Comment>();
	try {
	    String sql = "SELECT comment_fk FROM CommentDevEvents WHERE devevents_fk = " + devevents.getEventId()
		    + ";";
	    ResultSet resultSet = UtilBD.consultDB(sql);
	    while (resultSet.next()) {
		sql = "SELECT username, text FROM Comment WHERE id = " + resultSet.getInt("comment_fk") + ";";
		ResultSet commentSet = UtilBD.consultDB(sql);
		String username = commentSet.getString("username");
		String text = commentSet.getString("text");
		comments.add(new Comment(username, text));
		commentSet.getStatement().close();
	    }
	    resultSet.getStatement().close();
	} catch (SQLException e) {
	    System.err.println("{ COULDN'T SHOW COMMENTS }");
	}
	return comments;
    }

    public void addComment(DevEvents devevents) {
	try {
	    CommentDAO comment = new CommentDAO();
	    for (int i = 0; i < devevents.getComments().size(); i++) {
		comment.add(devevents.getComments().get(i));
		int id = comment.getLastId();
		String sql = "INSERT INTO CommentDevEvents (comment_fk, devevents_fk) VALUES (" + id + ", "
			+ devevents.getEventId() + ");";
		UtilBD.updateDB(sql);
	    }

	} catch (SQLException e) {
	    System.err.println("{ IMPOSSIBLE TO VIEW DEV EVENTS }");
	}
    }
}
