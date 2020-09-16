package dataBase;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entities.GameEvents;

public class GameEventsDAO implements InterfaceDAO<GameEvents> {

    @Override
    public void add(GameEvents gameEvents) {
	try {
	    String sql = "INSERT INTO GameEvents (username, eventName, eventDate, eventLocal, eventDescription, gameName) VALUES ('"
		    + gameEvents.getUser().getUsername() + "', '" 
		    + gameEvents.getEventName() + "', '"
		    + gameEvents.getEventDate() + "', '" 
		    + gameEvents.getEventLocal() + "', '"
		    + gameEvents.getEventDescription() + "', '" 
		    + gameEvents.getGameName() + "')";
	    UtilBD.updateDB(sql);

	    sql = "INSERT INTO UserGameEvents (user_fk, gameevents_fk) VALUES (" + gameEvents.getUser().getId() + ","
		    + getLastId() + ");";
	    UtilBD.updateDB(sql);

	} catch (SQLException e) {
	    System.err.println("{ COULDN'T ADD THIS GAME EVENT }");
	}
    }

    @Override
    public void update(GameEvents gameEvents) {
	try {
	    String sql = "UPDATE GameEvents SET " + "eventName = '" + gameEvents.getEventName() + "', "
		    + "eventDate = '" + gameEvents.getEventDate() + "', " + "eventLocal = '"
		    + gameEvents.getEventLocal() + "', " + "eventDescription = '" + gameEvents.getEventDescription()
		    + "', " + "gameName = '" + gameEvents.getGameName() + " " + "WHERE id = " + gameEvents.getEventId()
		    + ";";
	    UtilBD.updateDB(sql);
	} catch (SQLException e) {
	    System.err.println("{ COULDN'T SET THIS GAME EVENT }");
	}
    }

    @Override
    public void remove(GameEvents gameEvents) {
	try {
	    String sql = "DELETE FROM GameEvents WHERE id = '" + gameEvents.getEventId() + "'";
	    UtilBD.updateDB(sql);

	    sql = "DELETE FROM UserGameEvents WHERE gameevents_fk = '" + gameEvents.getEventId() + "'";
	    UtilBD.updateDB(sql);
	} catch (SQLException e) {
	    System.err.println("{ COULDN'T REMOVE THIS GAME EVENT }");
	}
    }

    @Override
    public List<GameEvents> all() {
	List<GameEvents> retrn = new ArrayList<GameEvents>();
	try {
	    String sql = "SELECT id, username, eventName, eventDate, eventLocal, eventDescription, gameName FROM GameEvents";
	    ResultSet resultSet = UtilBD.consultDB(sql);
	    while (resultSet.next()) {
		Integer id = resultSet.getInt("id");
		String username = resultSet.getString("username");
		String eventName = resultSet.getString("eventName");
		String eventDate = resultSet.getString("eventDate");
		String eventLocal = resultSet.getString("eventLocal");
		String eventDescription = resultSet.getString("eventDescription");
		String gameName = resultSet.getString("gameName");
		retrn.add(new GameEvents(id, new UserDAO().getByName(username), eventName, eventDate, eventLocal,
			eventDescription, gameName));
	    }
	    resultSet.getStatement().close();
	} catch (SQLException e) {
	    System.err.println("{ COULDN'T LIST GAME EVENTS }");
	}
	return retrn;
    }

    public GameEvents get(Integer id) {
	GameEvents retrn = null;
	try {
	    String sql = "SELECT id, username, eventName, eventDate, eventLocal, eventDescription, gameName FROM GameEvents WHERE id = '"
		    + id + "'";
	    ResultSet resultSet = UtilBD.consultDB(sql);
	    while (resultSet.next()) {
		Integer idGe = resultSet.getInt("id");
		String username = resultSet.getString("username");
		String eventName = resultSet.getString("eventName");
		String eventDate = resultSet.getString("eventDate");
		String eventLocal = resultSet.getString("eventLocal");
		String eventDescription = resultSet.getString("eventDescription");
		String gameName = resultSet.getString("gameName");
		retrn = new GameEvents(idGe, new UserDAO().getByName(username), eventName, eventDate, eventLocal,
			eventDescription, gameName);
	    }
	    resultSet.getStatement().close();
	} catch (SQLException e) {
	    System.err.println("{ IMPOSSIBLE TO VIEW A GAME EVENT }");
	}
	return retrn;
    }

    public int getLastId() {
	int id = 0;
	try {
	    String sql = "SELECT MAX(id) as id FROM GameEvents;";
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
