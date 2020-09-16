package entities;

public class DevEvents extends Events {

    public DevEvents(User user, Integer eventId, String eventName, String eventDate, String eventLocal,
	    String eventDescription) {
	super(user, eventId, eventName, eventDate, eventLocal, eventDescription);
    }
    
    public DevEvents(User user, String eventName, String eventDate, String eventLocal,
	    String eventDescription) {
	super(user, eventName, eventDate, eventLocal, eventDescription);
    }

    public DevEvents(User user, Integer eventId, String eventName, String eventDescription) {
	super(user, eventId, eventName, eventDescription);
    }

    public DevEvents(Integer id, User user, String eventName, String eventDate, String eventLocal,
	    String eventDescription) {
	super(user, id, eventName, eventDate, eventLocal, eventDescription);
    }

  

}