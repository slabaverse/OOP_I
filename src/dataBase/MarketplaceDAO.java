package dataBase;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entities.Comment;
import entities.Marketplace;

public class MarketplaceDAO implements InterfaceDAO<Marketplace> {

    @Override
    public void add(Marketplace marketplace) {
	try {
	    String sql = "INSERT INTO Marketplace (username, product, price, description) VALUES ('"
		    + marketplace.getUser().getUsername() + "'," + marketplace.getProduct() + ",'"
		    + marketplace.getPrice() + ",'" + marketplace.getDescription() + "')";
	    UtilBD.updateDB(sql);

	} catch (SQLException e) {
	    System.err.println("{ COULDN'T ADD THIS PRODUCT }");
	}
    }

    @Override
    public void update(Marketplace marketplace) {
	try {
	    String sql = "UPDATE Marketplace SET " + "product = '" + marketplace.getProduct() + "', " + "price = '"
		    + marketplace.getPrice() + "', " + "description = " + marketplace.getDescription() + " "
		    + "WHERE id = " + marketplace.getId() + ";";
	    UtilBD.updateDB(sql);
	} catch (SQLException e) {
	    System.err.println("{ COULDN'T SET PRODUCT }");
	}
    }

    @Override
    public void remove(Marketplace marketplace) {
	try {
	    String sql = "DELETE FROM Marketplace WHERE id = '" + marketplace.getId() + "'";
	    UtilBD.updateDB(sql);
	} catch (SQLException e) {
	    System.err.println("{ COULDN'T REMOVE THIS PRODUCT }");
	}
    }

    @Override
    public List<Marketplace> all() {
	List<Marketplace> retrn = new ArrayList<Marketplace>();
	try {
	    String sql = "SELECT id, username, product, price, description FROM Marketplace";
	    ResultSet resultSet = UtilBD.consultDB(sql);
	    while (resultSet.next()) {
		Integer id = resultSet.getInt("id");
		String username = resultSet.getString("username");
		String product = resultSet.getString("product");
		Double price = resultSet.getDouble("price");
		String description = resultSet.getString("description");
		retrn.add(new Marketplace(id, new UserDAO().get(username).getUser(), product, price, description));
	    }
	    resultSet.getStatement().close();
	} catch (SQLException e) {
	    System.err.println("{ COULDN'T LIST THE PRODUCTS }");
	}
	return retrn;
    }

    public Marketplace get(Integer id) {
	Marketplace retrn = null;
	try {
	    String sql = "SELECT id, username, product, price, description FROM Marketplace WHERE id = '" + id + "'";
	    ResultSet resultSet = UtilBD.consultDB(sql);
	    while (resultSet.next()) {
		Integer id_1 = resultSet.getInt("id");
		String username = resultSet.getString("username");
		String product = resultSet.getString("product");
		Double price = resultSet.getDouble("price");
		String description = resultSet.getString("description");
		retrn = new Marketplace(id_1, new UserDAO().get(username).getUser(), product, price, description);
	    }
	    resultSet.getStatement().close();
	} catch (SQLException e) {
	    System.err.println("{ IMPOSSIBLE TO CONSULT A PRODUCT AT DB }");
	}
	return retrn;
    }

    public List<Comment> getComments(Marketplace mkt) {
	List<Comment> comments = new ArrayList<Comment>();
	try {
	    String sql = "SELECT comment_fk FROM CommentMarketplace WHERE mkt_fk = " + mkt.getId() + ";";
	    ResultSet resultSet = UtilBD.consultDB(sql);
	    while (resultSet.next()) {
		sql = "SELECT username, text FROM Comment WHERE id = " + resultSet.getInt("comment_fk") + ";";
		ResultSet commentSet = UtilBD.consultDB(sql);
		String username = commentSet.getString("username");
		String text = commentSet.getString("text");
		comments.add(new Comment(username, text));
		commentSet.getStatement().close();
	    }
	    resultSet.getStatement().close();
	} catch (SQLException e) {
	    System.err.println("{ COULDN'T SHOW COMMENTS }");
	}
	return comments;
    }

    public void addComment(Marketplace marketplace) {
	try {
	    CommentDAO comment = new CommentDAO();
	    for (int i = 0; i < marketplace.getComments().size(); i++) {
		comment.add(marketplace.getComments().get(i));
		int id = comment.getLastId();
		String sql = "INSERT INTO CommentMarketplace (comment_fk, mkt_fk) VALUES (" + id + ", "
			+ marketplace.getId() + ");";
		UtilBD.updateDB(sql);
	    }

	} catch (SQLException e) {
	    System.err.println("{ IMPOSSIBLE TO VIEW PRODUCTS }");
	}
    }
}
