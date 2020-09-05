package dataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import entities.User;

public class UtilBD {
	private static Connection conection;

	public static Connection getConection() {
		try {

			if (conection == null)
				openConection();

			if (conection.isClosed())
				openConection();

		} catch (SQLException e) {
			System.err.println("ERROR: Couldn't Open Database Conection");
		}

		return conection;
	}

	private static void openConection() {
		try {
			Class.forName("org.sqlite.JDBC");
			conection = DriverManager.getConnection("jdbc:sqlite:banco.sqlite");
		} catch (SQLException e) {
		    System.err.println("ERROR: Couldn't Open Database Conection");
		} catch (ClassNotFoundException e2) {
		    System.err.println("ERROR: Problem with SQLite lib");
		}
	}

	public static void closeConection() {
		if (conection == null)
			return;

		try {
			if (!conection.isClosed())
				conection.close();
		} catch (SQLException e) {
		    System.err.println("ERROR: Couldn't Close Database Conection");
		}
	}

	public static void initBD() {
		try {
			conection = getConection();
			Statement stm = conection.createStatement();
			createUser(stm);
			createMarketplace(stm);
			createGameEvents(stm);
			createDevEvents(stm);
			createFollow(stm);
			createComments(stm);
			createPost(stm);
			stm.close();
		} catch (SQLException e) {
		    System.err.println("ERROR: Couldn't Create Database");
		}
	}

	private static void createUser(Statement stm) throws SQLException {
		stm.executeUpdate("DROP TABLE IF EXISTS User");
		stm.executeUpdate(
				"CREATE TABLE User (Username VARCHAR(10) NOT NULL PRIMARY KEY,"
				+ " Password VARCHAR(50) NOT NULL,"
				+ " Name VARCHAR(50) NOT NULL,"
				+ " Birthdate VARCHAR(10) NOT NULL,"
				+ " Relationship VARCHAR (30) NOT NULL;");
		stm.executeUpdate(
			"INSERT INTO User"
			+ "VALUES ('adam@gmail.com','123456','Adam Slabadack','05/08/1989','Single')");
	}
	private static void createMarketplace(Statement stm) throws SQLException {
		stm.executeUpdate("DROP TABLE IF EXISTS Marketplace");
		stm.executeUpdate(
				"CREATE TABLE Marketplace (ID INT NOT NULL AUTO_INCREMENT PRIMARY KEY,"
				+ "Product VARCHAR(10) NOT NULL,"
				+ "Price DECIMAL(5,2),"
				+ "Description VARCHAR(100) NOT NULL;");
		stm.executeUpdate(
			"INSERT INTO Marketplace (Product, Price, Description)"
			+ "VALUES ('PlayStation 5', 4500.00,'New Sony console released in 2020')");
	}
	private static void createGameEvents(Statement stm) throws SQLException {
		stm.executeUpdate("DROP TABLE IF EXISTS GameEvents");
		stm.executeUpdate(
				"CREATE TABLE GameEvents (EventID INT NOT NULL AUTO_INCREMENT PRIMARY KEY,"
				+ "EventName VARCHAR(50) NOT NULL,"
				+ "EventDate VARCHAR(10) NOT NULL,"	
				+ "EventLocal VARCHAR(50) NOT NULL,"
				+ "EventDescription VARCHAR(300) NOT NULL,"
				+ "GameName VARCHAR(50) NOT NULL;");
		stm.executeUpdate(
			"INSERT INTO GameEvents (EventName, EventDate, EventLocal, EventDescription, GameName)"
			+ "VALUES ('CSGO Championship','10/11/2020','Alianz Arena - München, DE',"
			+ "'The great Deutsch Counter Strike Championship is finally here!','Counter Strike Global Offensive')");
		stm.executeUpdate(
			"INSERT INTO GameEvents (EventName, EventDate, EventLocal, EventDescription, GameName)"
			+ "VALUES ('The Crazy FortNite','14/11/2020','Alianz Arena - München, DE',"
			+ "'A FortNinte event, join with your squad!','FortNite')");
	}
	private static void createDevEvents(Statement stm) throws SQLException {
		stm.executeUpdate("DROP TABLE IF EXISTS DevEvents");
		stm.executeUpdate(
				"CREATE TABLE DevEvents (EventID INT NOT NULL AUTO_INCREMENT PRIMARY KEY,"
				+ "EventName VARCHAR(50) NOT NULL,"
				+ "EventDate VARCHAR(10) NOT NULL,"	
				+ "EventLocal VARCHAR(50) NOT NULL,"
				+ "EventDescription VARCHAR(300) NOT NULL;");
		stm.executeUpdate(
			"INSERT INTO DevEvents (EventName, EventDate, EventLocal, EventDescription)"
			+ "VALUES('Google Hackathon','08/05/2021','GooglePlex - San Francisco, US',"
			+ "'Come and show your skills to the world!')");
		stm.executeUpdate(
			"INSERT INTO DevEvents (EventName, EventDate, EventLocal, EventDescription)"
			+ "VALUES ('Microsoft Development Day','21/08/2021','Santa Catarina Federal University - Florianópolis, BR',"
			+ "'Great names of Microsoft to explain the future of programing')");
	}
	private static void createFollow(Statement stm) throws SQLException {
		stm.executeUpdate("DROP TABLE IF EXISTS Follow");
		stm.executeUpdate(
				"CREATE TABLE Follow (Name VARCHAR(10) NOT NULL PRIMARY KEY,"
				+ "Birthdate VARCHAR(10) NOT NULL,"
				+ "Relatioship VARCHAR(30) NOT NULL;");
	}

	private static void createComments(Statement stm) throws SQLException {
		stm.executeUpdate("DROP TABLE IF EXISTS Comments");
		stm.executeUpdate(
				"CREATE TABLE Comments (ID INT NOT NULL AUTO_INCREMENT PRIMARY KEY,"
				+ "Text VARCHAR(300) NOT NULL;)");
	}
	private static void createPost(Statement stm) throws SQLException {
		stm.executeUpdate("DROP TABLE IF EXISTS Post");
		stm.executeUpdate(
				"CREATE TABLE Post (ID INT NOT NULL AUTO_INCREMENT PRIMARY KEY,"
				+ "Content VARCHAR(300) NOT NULL;)");
	}
	public static void updateDB(String sql) throws SQLException {
		Connection bd = UtilBD.getConection();
		Statement stm = bd.createStatement();
		stm.executeUpdate(sql);
		System.out.println("Executed: " + sql);
		stm.close();
	}

	public static ResultSet consultDB(String sql) throws SQLException {
		Connection bd = UtilBD.getConection();
		Statement stm = bd.createStatement();
		ResultSet retorno = stm.executeQuery(sql);
		System.out.println("Executed: " + sql);
//		stm.close();
		return retorno;
	}
}