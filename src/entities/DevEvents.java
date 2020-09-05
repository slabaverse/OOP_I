package entities;

public class DevEvents extends Events {

    public DevEvents(User user, Integer eventId, String eventName, String eventDate, String eventLocal, String eventDescription) {
	super(user, eventId, eventName, eventDate, eventLocal, eventDescription);
    }

    public DevEvents(User user, Integer eventId, String eventName, String eventDescription) {
	super(user, eventId, eventName, eventDescription);
    }

    @Override
    public void addComment(Integer id, User user, String text) {
	comments.add(new Comment(id, user, text));
    }

    @Override
    public void editComment(Integer id, String text) {
	for (int i = 0; i < comments.size(); i++) {
	    if (comments.get(i).getId() == id) {
		comments.get(i).setText(text);
	    }
	}
    }

    @Override
    public void removeComment(Integer id) {
	for (int i = 0; i < comments.size(); i++) {
	    if (comments.get(i).getId() == id) {
		comments.remove(i);
	    }
	}
	System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
	System.out.println("{COMMENT DELETED}");
	System.out.println();
    }

    @Override
    public void showComments() {
	System.out.println();
	for (int i = 0; i < comments.size(); i++) {
	    System.out.println("Comment #" + comments.get(i).getId() + " by " + comments.get(i).getUser().getName()
		    + " on " + date + " at " + time);
	    System.out.println(comments.get(i).getText());
	    System.out.println();
	}
    }

}