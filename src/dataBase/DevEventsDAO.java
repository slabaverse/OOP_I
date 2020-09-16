package dataBase;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entities.DevEvents;

public class DevEventsDAO implements InterfaceDAO<DevEvents> {

    @Override
    public void add(DevEvents devEvents) {
	try {
	    String sql = "INSERT INTO DevEvents (username, eventName, eventDate, eventLocal, eventDescription) VALUES ('"
		    + devEvents.getUser().getUsername() + "', '" 
		    + devEvents.getEventName() + "', '"
		    + devEvents.getEventDate() + "', '" 
		    + devEvents.getEventLocal() + "', '"
		    + devEvents.getEventDescription() + "')";
	    UtilBD.updateDB(sql);

	    sql = "INSERT INTO UserDevEvents (user_fk, devevents_fk) VALUES (" + devEvents.getUser().getId() + ","
		    + getLastId() + ");";
	    UtilBD.updateDB(sql);

	} catch (SQLException e) {
	    System.err.println("{ COULDN'T ADD THIS DEV EVENT }");
	}
    }

    @Override
    public void update(DevEvents devEvents) {
	try {
	    String sql = "UPDATE DevEvents SET eventName = '" + devEvents.getEventName() + "', " + "eventDate = '"
		    + devEvents.getEventDate() + "', " + "eventLocal = '" + devEvents.getEventLocal() + "', "
		    + "eventDescription = '" + devEvents.getEventDescription() + "' " + "WHERE id = "
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

	    sql = "DELETE FROM UserDevEvents WHERE devevents_fk = '" + devEvents.getEventId() + "'";
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
		retrn.add(new DevEvents(id, new UserDAO().getByName(username), eventName, eventDate, eventLocal,
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
		retrn = new DevEvents(idDe, new UserDAO().getByName(username), eventName, eventDate, eventLocal,
			eventDescription);
	    }
	    resultSet.getStatement().close();
	} catch (SQLException e) {
	    System.err.println("{ IMPOSSIBLE TO VIEW A DEV EVENT }");
	}
	return retrn;
    }

    public int getLastId() {
	int id = 0;
	try {
	    String sql = "SELECT MAX(id) as id FROM DevEvents;";
	    ResultSet resultSet = UtilBD.consultDB(sql);
	    while (resultSet.next()) {
		id = resultSet.getInt("id");
	    }

	    resultSet.getStatement().close();
	} catch (SQLException e) {
	    System.err.println("{ UNABLE TO DO TI }");
	}

	return id;
    }
}
