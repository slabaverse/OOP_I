package dataBase;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entities.Post;

public class PostDAO implements InterfaceDAO<Post> {

    @Override
    public void add(Post post) {
	try {
	    String sql = "INSERT INTO Post (username, content) VALUES ('" + post.getUser().getUsername() + "','"
		    + post.getContent() + "')";
	    UtilBD.updateDB(sql);

	    sql = "INSERT INTO UserPost (user_fk, post_fk) VALUES (" + post.getUser().getId() + "," + getLastId()
		    + ");";
	    UtilBD.updateDB(sql);

	} catch (SQLException e) {
	    System.err.println("{ COULDN'T ADD THIS POST }");
	}
    }

    @Override
    public void update(Post post) {
	try {
	    String sql = "UPDATE Post SET content = '" + post.getContent() + "'" + "WHERE id = " + post.getIdPost()
		    + ";";
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

	    sql = "DELETE FROM UserPost WHERE post_fk = '" + post.getIdPost() + "'";
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

    public int getLastId() {
	int id = 0;
	try {
	    String sql = "SELECT MAX(id) as id FROM Post;";
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
