package entities;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public abstract class Events {

    private User user;
    private Integer eventId;
    private String eventName;
    private String eventDate;
    private String eventLocal;
    private String eventDescription;

    protected List<Comment> comments;

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
	this.comments = new ArrayList<>();
    }

    public Events(User user, Integer eventId, String eventName, String eventDescription) {
	super();
	this.user = user;
	this.eventId = eventId;
	this.eventName = eventName;
	this.eventDescription = eventDescription;
	this.comments = new ArrayList<>();
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

    public List<Comment> getComments() {
	return comments;
    }

    public abstract void addComment(Integer id, User user, String text);

    public abstract void editComment(Integer id, String text);

    public abstract void removeComment(Integer id);

    public abstract void showComments();

}