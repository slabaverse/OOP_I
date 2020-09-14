package dataBase;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entities.Comment;
import entities.Post;

public class PostDAO implements InterfaceDAO<Post> {

    @Override
    public void add(Post post) {
	try {
	    String sql = "INSERT INTO Post (username, content) VALUES ('" + post.getUser().getUsername() + "',"
		    + post.getContent() + "')";
	    UtilBD.updateDB(sql);

	} catch (SQLException e) {
	    System.err.println("{ COULDN'T ADD THIS POST }");
	}
    }

    @Override
    public void update(Post post) {
	try {
	    String sql = "UPDATE Post SET " + "content = '" + post.getContent() +  " "
		    + "WHERE id = " + post.getIdPost() + ";";
	    UtilBD.updateDB(sql);
	} catch (SQLException e) {
	    System.err.println("{ COULDN'T SET POST }");
	}
    }

    @Override
    public void remove(Post post) {
	try {
	    String sql = "DELETE FROM Post WHERE id = '" + post.getIdPost() + "'";
	    UtilBD.updateDB(sql);
	} catch (SQLException e) {
	    System.err.println("{ COULDN'T REMOVE THIS POST }");
	}
    }


    @Override
    public List<Post> all() {
	List<Post> retrn = new ArrayList<Post>();
	try {
	    String sql = "SELECT id, username, content FROM Post";
	    ResultSet resultSet = UtilBD.consultDB(sql);
	    while (resultSet.next()) {
		Integer id = resultSet.getInt("id");
		String username = resultSet.getString("username");
		String content = resultSet.getString("content");
		Post post = new Post(id, new UserDAO().getByName(username), content);
		post.setComment(getComments(post));
		retrn.add(post);
	    }
	    resultSet.getStatement().close();
	} catch (SQLException e) {
	    System.err.println("{ COULDN'T LIST POSTS }");
	}
	return retrn;
    }
    
    public Post get(Integer id) {
	Post retrn = null;
	try {
	    String sql = "SELECT id, username, content FROM Post WHERE id = '" + id + "'";
	    ResultSet resultSet = UtilBD.consultDB(sql);
	    while (resultSet.next()) {
		Integer id_1 = resultSet.getInt("id");
		String username = resultSet.getString("username");
		String content = resultSet.getString("content");
		retrn = new Post(id_1, new UserDAO().getByName(username), content);
	    }
	    resultSet.getStatement().close();
	} catch (SQLException e) {
	    System.err.println("{ IMPOSSIBLE TO CONSULT POSTS AT DB }");
	}
	return retrn;
    }
    
    public List<Comment> getComments(Post post) {
	List<Comment> comments = new ArrayList<Comment>();
	try {
	    String sql = "SELECT comment_fk FROM CommentPost WHERE post_fk = " + post.getIdPost() + ";";
	    ResultSet resultSet = UtilBD.consultDB(sql);
	    while (resultSet.next()) {
		sql = "SELECT username, content FROM Comment WHERE id = " + resultSet.getInt("comment_fk") + ";";
		ResultSet commentSet = UtilBD.consultDB(sql);
		String username = commentSet.getString("username");
		String content = commentSet.getString("content");
		comments.add(new Comment(username, content));
		commentSet.getStatement().close();
	    }
	    resultSet.getStatement().close();
	} catch (SQLException e) {
	    System.err.println("{ COULDN'T SHOW COMMENTS }");
	}
	return comments;
    }

    public void addComment(Post post) {
	try {
	    CommentDAO comment = new CommentDAO();
	    for (int i = 0; i < post.getComments().size(); i++) {
		comment.add(post.getComments().get(i));
		int id = comment.getLastId();
		String sql = "INSERT INTO CommentPost (comment_fk, post_fk) VALUES (" + id + ", "
			+ post.getIdPost() + ");";
		UtilBD.updateDB(sql);
	    }

	} catch (SQLException e) {
	    System.err.println("{ IMPOSSIBLE TO VIEW POSTS }");
	}
    }
}
