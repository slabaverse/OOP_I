package dataBase;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entities.Comment;
import entities.Post;

public class CommentDAO implements InterfaceDAO<Comment> {

    @Override
    public void add(Comment comment) {
	try {
	    String sql = "INSERT INTO Comments (username, text) VALUES ('" + comment.getUser().getUsername() + "',"
		    + comment.getText() + "')";
	    UtilBD.updateDB(sql);

	} catch (SQLException e) {
	    System.err.println("{ COULDN'T ADD THIS COMMENT }");
	}
    }

    @Override
    public void update(Comment comment) {
	try {
	    String sql = "UPDATE Comment SET " + "text = '" + comment.getText() + " " + "WHERE id = " + comment.getId()
		    + ";";
	    UtilBD.updateDB(sql);
	} catch (SQLException e) {
	    System.err.println("{ COULDN'T SET COMMENT }");
	}
    }

    @Override
    public void remove(Comment comment) {
	try {
	    String sql = "DELETE FROM Comment WHERE id = '" + comment.getId() + "'";
	    UtilBD.updateDB(sql);
	} catch (SQLException e) {
	    System.err.println("{ COULDN'T REMOVE THIS COMMENT }");
	}
    }

    @Override
    public List<Comment> all() {
	List<Comment> retrn = new ArrayList<Comment>();
	try {
	    String sql = "SELECT id, username, text FROM Comment";
	    ResultSet resultSet = UtilBD.consultDB(sql);
	    while (resultSet.next()) {
		Integer id = resultSet.getInt("id");
		String username = resultSet.getString("username");
		String text = resultSet.getString("text");
		retrn.add(new Comment(id, new UserDAO().getByName(username), text));
	    }
	    resultSet.getStatement().close();
	} catch (SQLException e) {
	    System.err.println("{ COULDN'T LIST COMMENTS }");
	}
	return retrn;
    }
    
    public Comment get(Integer id) {
	Comment retrn = null;
	try {
	    String sql = "SELECT id, username, text FROM Comment WHERE id = '" + id + "'";
	    ResultSet resultSet = UtilBD.consultDB(sql);
	    while (resultSet.next()) {
		Integer id_1 = resultSet.getInt("id");
		String username = resultSet.getString("username");
		String text = resultSet.getString("text");
		retrn = new Comment(id_1, new UserDAO().getByName(username), text);
	    }
	    resultSet.getStatement().close();
	} catch (SQLException e) {
	    System.err.println("{ IMPOSSIBLE TO VIEW COMMENTS }");
	}
	return retrn;
    }

    public int getLastId() {
	int id = 0;
	try {
	    String sql = "SELECT MAX(id) as id FROM Comments;";
	    ResultSet resultSet = UtilBD.consultDB(sql);
	    while (resultSet.next()) {
		id = resultSet.getInt("id");
	    }

	    resultSet.getStatement().close();
	} catch (SQLException e) {
	    System.err.println("Inable to do it");
	}

	return id;
    }

}
