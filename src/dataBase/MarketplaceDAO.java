package dataBase;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import UI_FX.Alert_FX;
import entities.Marketplace;

public class MarketplaceDAO implements InterfaceDAO<Marketplace> {

    @Override
    public void add(Marketplace marketplace) {
	try {
	    String sql = "INSERT INTO Marketplace (username, product, price, description) VALUES ('"
		    + marketplace.getUser().getUsername() + "','" + marketplace.getProduct() + "', "
		    + marketplace.getPrice() + ",'" + marketplace.getDescription() + "')";
	    UtilBD.updateDB(sql);

	    sql = "INSERT INTO UserMarketplace (user_fk, mkt_fk) VALUES (" + marketplace.getUser().getId() + ","
		    + getLastId() + ");";
	    UtilBD.updateDB(sql);

	} catch (SQLException e) {
	    Alert_FX.error("{ COULDN'T ADD THIS PRODUCT }");
	}
    }

    @Override
    public void update(Marketplace marketplace) {
	try {
	    String sql = "UPDATE Marketplace SET product = '" + marketplace.getProduct() + "', " + "price = "
		    + marketplace.getPrice() + ", " + "description = '" + marketplace.getDescription() + "' "
		    + "WHERE id = " + marketplace.getId() + ";";
	    UtilBD.updateDB(sql);
	} catch (SQLException e) {
	    Alert_FX.error("{ COULDN'T SET PRODUCT }");
	}
    }

    @Override
    public void remove(Marketplace marketplace) {
	try {
	    String sql = "DELETE FROM Marketplace WHERE id = '" + marketplace.getId() + "'";
	    UtilBD.updateDB(sql);

	    sql = "DELETE FROM UserMarketplace WHERE mkt_fk = '" + marketplace.getId() + "'";
	    UtilBD.updateDB(sql);
	} catch (SQLException e) {
	    Alert_FX.error("{ COULDN'T REMOVE THIS PRODUCT }");
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
		retrn.add(new Marketplace(id, new UserDAO().getByName(username), product, price, description));
	    }
	    resultSet.getStatement().close();
	} catch (SQLException e) {
	    Alert_FX.error("{ COULDN'T LIST THE PRODUCTS }");
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
		retrn = new Marketplace(id_1, new UserDAO().getByName(username), product, price, description);
	    }
	    resultSet.getStatement().close();
	} catch (SQLException e) {
	    Alert_FX.error("{ IMPOSSIBLE TO CONSULT A PRODUCT AT DB }");
	}
	return retrn;
    }

    public int getLastId() {
	int id = 0;
	try {
	    String sql = "SELECT MAX(id) as id FROM Marketplace;";
	    ResultSet resultSet = UtilBD.consultDB(sql);
	    while (resultSet.next()) {
		id = resultSet.getInt("id");
	    }

	    resultSet.getStatement().close();
	} catch (SQLException e) {
	    Alert_FX.error("{ UNABLE TO DO TI }");
	}

	return id;
    }
}
