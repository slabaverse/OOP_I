package entities;

public class GameEvents extends Events {

    private String gameName;

    public GameEvents(User user, Integer eventId, String eventName, String eventDate, String eventLocal,
	    String eventDescription, String gameName) {
	super(user, eventId, eventName, eventDate, eventLocal, eventDescription);
	this.gameName = gameName;
    }

    public GameEvents(User user, String eventName, String eventDate, String eventLocal, String eventDescription,
	    String gameName) {
	super(user, eventName, eventDate, eventLocal, eventDescription);
	this.gameName = gameName;
    }

    public GameEvents(User user, Integer eventId, String eventName, String eventDescription, String gameName) {
	super(user, eventId, eventName, eventDescription);
	this.gameName = gameName;
    }

    public GameEvents(Integer eventId, User user, String eventName, String eventDate, String eventLocal,
	    String eventDescription, String gameName) {
	super(user, eventId, eventName, eventDate, eventLocal, eventDescription);
	this.gameName = gameName;
    }

    public String getGameName() {
	return gameName;
    }

    public void setGameName(String gameName) {
	this.gameName = gameName;
    }
}
