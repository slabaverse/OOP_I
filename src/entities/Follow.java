package entities;

public class Follow {
    
    private User follow;
    private User follower;
    
    public Follow(User follow, User follower) {
	super();
	this.follow = follow;
	this.follower = follower;
    }

    public Follow(User follow) {
	super();
	this.follow = follow;
    }

    public User getFollow() {
        return follow;
    }

    public void setFollow(User follow) {
        this.follow = follow;
    }

    public User getFollower() {
        return follower;
    }

    public void setFollower(User follower) {
        this.follower = follower;
    }

}
