package dataBase;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import UI_FX.Alert_FX;
import entities.GameEvents;
import entities.User;

public class GameEventsDAO implements InterfaceDAO<GameEvents> {

    @Override
    public void add(GameEvents gameEvents) {
	try {
	    String sql = "INSERT INTO GameEvents (eventName, eventDate, eventLocal, eventDescription, gameName) VALUES ('"
		    + gameEvents.getEventName() + "', '"
		    + gameEvents.getEventDate() + "', '" + gameEvents.getEventLocal() + "', '"
		    + gameEvents.getEventDescription() + "', '" + gameEvents.getGameName() + "')";
	    System.out.println(sql);
	    UtilBD.updateDB(sql);

	    sql = "INSERT INTO UserGameEvents (user_fk, gameevents_fk) VALUES (" + gameEvents.getUser().getId() + ","
		    + getLastId() + ");";
	    UtilBD.updateDB(sql);

	} catch (SQLException e) {
	    System.out.println(e);
	    Alert_FX.error("{ COULDN'T ADD THIS GAME EVENT }");
	}
    }

    @Override
    public void update(GameEvents gameEvents) {
	try {
	    String sql = "UPDATE GameEvents SET " + "eventName = '" + gameEvents.getEventName() + "', "
		    + "eventDate = '" + gameEvents.getEventDate() + "', " + "eventLocal = '"
		    + gameEvents.getEventLocal() + "', " + "eventDescription = '" + gameEvents.getEventDescription()
		    + "', " + "gameName = '" + gameEvents.getGameName() + "' " + "WHERE id = " + gameEvents.getEventId()
		    + ";";
	    UtilBD.updateDB(sql);
	} catch (SQLException e) {
	    Alert_FX.error("{ COULDN'T SET THIS GAME EVENT }");
	}
    }

    @Override
    public void remove(GameEvents gameEvents) {
	try {
	    String sql = "DELETE FROM GameEvents WHERE id = " + gameEvents.getEventId() + ";";
	    UtilBD.updateDB(sql);

	    sql = "DELETE FROM UserGameEvents WHERE gameevents_fk = " + gameEvents.getEventId() + ";";
	    UtilBD.updateDB(sql);
	} catch (SQLException e) {
	    Alert_FX.error("{ COULDN'T REMOVE THIS GAME EVENT }");
	    System.out.println(e);
	}
    }

    @Override
    public List<GameEvents> all() {
	List<GameEvents> retrn = new ArrayList<GameEvents>();
	try {
	    String sql = "SELECT id, eventName, eventDate, eventLocal, eventDescription, gameName FROM GameEvents";
	    ResultSet resultSet = UtilBD.consultDB(sql);
	    while (resultSet.next()) {
		Integer id = resultSet.getInt("id");
		String eventName = resultSet.getString("eventName");
		String eventDate = resultSet.getString("eventDate");
		String eventLocal = resultSet.getString("eventLocal");
		String eventDescription = resultSet.getString("eventDescription");
		String gameName = resultSet.getString("gameName");

		sql = "SELECT user_fk FROM UserGameEvents WHERE gameevents_fk = " + resultSet.getInt("id") + ";";
		ResultSet gameUserName = UtilBD.consultDB(sql);
		while (gameUserName.next()) {
		    sql = "SELECT username FROM User WHERE id = " + gameUserName.getInt("user_fk") + ";";
		    ResultSet gUserName = UtilBD.consultDB(sql);
		    retrn.add(new GameEvents(new User(gUserName.getString("username")), id, eventName, eventDate,
			    eventLocal, eventDescription, gameName));
		    gUserName.getStatement().close();
		}

		gameUserName.getStatement().close();

	    }

	    resultSet.getStatement().close();
	} catch (SQLException e) {
	    Alert_FX.error("{ COULDN'T LIST GAME EVENTS }");
	}
	return retrn;
    }

    public GameEvents get(Integer id) {
	GameEvents retrn = null;
	try {
	    String sql = "SELECT id, eventName, eventDate, eventLocal, eventDescription, gameName FROM GameEvents WHERE id = "
		    + id + ";";
	    ResultSet resultSet = UtilBD.consultDB(sql);
	    while (resultSet.next()) {
		Integer idGe = resultSet.getInt("id");
		String eventName = resultSet.getString("eventName");
		String eventDate = resultSet.getString("eventDate");
		String eventLocal = resultSet.getString("eventLocal");
		String eventDescription = resultSet.getString("eventDescription");
		String gameName = resultSet.getString("gameName");
		sql = "SELECT user_fk FROM UserGameEvents WHERE gameevents_fk = " + resultSet.getInt("id") + ";";
		ResultSet gameUserName = UtilBD.consultDB(sql);
		while (gameUserName.next()) {
		    sql = "SELECT username FROM User WHERE id = " + gameUserName.getInt("user_fk") + ";";
		    ResultSet gUserName = UtilBD.consultDB(sql);
		    retrn = new GameEvents(new User(gUserName.getString("username")), idGe, eventName, eventDate,
			    eventLocal, eventDescription, gameName);
		    gUserName.getStatement().close();
		}

		gameUserName.getStatement().close();
	    }
	    resultSet.getStatement().close();
	    
	} catch (SQLException e) {
	    Alert_FX.error("{ IMPOSSIBLE TO VIEW A GAME EVENT }");
	}
	return retrn;
    }

    public GameEvents getByName(String eventName) {
	GameEvents retrn = null;
	try {
	    String sql = "SELECT id, username FROM GameEvents WHERE eventName = '" + eventName + "'";
	    ResultSet resultSet = UtilBD.consultDB(sql);
	    while (resultSet.next()) {
		Integer id2 = resultSet.getInt("id");
		String eventName2 = resultSet.getString("eventName");
		retrn = new GameEvents(id2, eventName2);
	    }
	    resultSet.getStatement().close();
	} catch (SQLException e) {
	    System.err.println("{ IMPOSSIBLE TO VIEW GAME EVENT}");
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
	    Alert_FX.error("{ UNABLE TO DO TI }");
	}

	return id;
    }
}
