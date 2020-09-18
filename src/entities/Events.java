package entities;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public abstract class Events {

    private User user;
    private Integer eventId;
    private String eventName;
    private String eventDate;
    private String eventLocal;
    private String eventDescription;

    LocalDateTime now = LocalDateTime.now();

    DateTimeFormatter formatterData = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    String date = formatterData.format(now);

    DateTimeFormatter formatterHora = DateTimeFormatter.ofPattern("HH:mm:ss");
    String time = formatterHora.format(now);

    public Events(User user, Integer eventId, String eventName, String eventDate, String eventLocal,
	    String eventDescription) {
	super();
	this.user = user;
	this.eventId = eventId;
	this.eventName = eventName;
	this.eventDate = eventDate;
	this.eventLocal = eventLocal;
	this.eventDescription = eventDescription;
    }

    public Events(User user, String eventName, String eventDate, String eventLocal, String eventDescription) {
	super();
	this.user = user;
	this.eventName = eventName;
	this.eventDate = eventDate;
	this.eventLocal = eventLocal;
	this.eventDescription = eventDescription;
    }

    public Events(User user, Integer eventId, String eventName, String eventDescription) {
	super();
	this.user = user;
	this.eventId = eventId;
	this.eventName = eventName;
	this.eventDescription = eventDescription;
    }

    public Events(String eventName) {
	this.eventName = eventName;
    }

    public Events(Integer eventId, String eventName) {
	this.eventId = eventId;
	this.eventName = eventName;
    }

    public User getUser() {
	return user;
    }

    public Integer getEventId() {
	return eventId;
    }

    public void setEventId(Integer eventId) {
	this.eventId = eventId;
    }

    public String getEventName() {
	return eventName;
    }

    public void setEventName(String eventName) {
	this.eventName = eventName;
    }

    public String getEventDate() {
	return eventDate;
    }

    public void setEventDate(String eventDate) {
	this.eventDate = eventDate;
    }

    public String getEventLocal() {
	return eventLocal;
    }

    public void setEventLocal(String eventLocal) {
	this.eventLocal = eventLocal;
    }

    public String getEventDescription() {
	return eventDescription;
    }

    public void setEventDescription(String eventDescription) {
	this.eventDescription = eventDescription;
    }

}