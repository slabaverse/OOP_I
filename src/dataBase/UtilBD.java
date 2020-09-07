package dataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
			createUserMarketplace(stm);
			createCommentMarketplace(stm);
			createUserGameEvents(stm);
			createCommentGameEvents(stm);
			createUserDevEvents(stm);
			createCommentDevEvents(stm);
			createUserFollow(stm);
			createUserComments(stm);
			createUserPost(stm);
			createCommentPost(stm);
			stm.close();
		} catch (SQLException e) {
		    System.err.println("ERROR: Couldn't Create Database");
		    System.out.println(e);
		}
	}

	private static void createUser(Statement stm) throws SQLException {
		stm.executeUpdate("DROP TABLE IF EXISTS User");
		stm.executeUpdate(
				"CREATE TABLE User (Username VARCHAR(30) NOT NULL PRIMARY KEY,"
				+ " Password VARCHAR(50) NOT NULL,"
				+ " Name VARCHAR(50) NOT NULL,"
				+ " Birthdate VARCHAR(10) NOT NULL,"
				+ " Relationship VARCHAR (30) NOT NULL);");
		stm.executeUpdate(
			"INSERT INTO User "
			+ "VALUES ('@adam','123456','Adam Slabadack','05/08/1989','Single')");
		}
	private static void createMarketplace(Statement stm) throws SQLException {
		stm.executeUpdate("DROP TABLE IF EXISTS Marketplace");
		stm.executeUpdate(
				"CREATE TABLE Marketplace (ID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,"
				+ "Product VARCHAR(10) NOT NULL,"
				+ "Price FLOAT,"
				+ "Description VARCHAR(100) NOT NULL);");
		stm.executeUpdate(
			"INSERT INTO Marketplace (Product, Price, Description) "
			+ "VALUES ('PlayStation 5', 4500.00,'New Sony console released in 2020')");
	}
	private static void createUserMarketplace(Statement stm) throws SQLException {
		stm.executeUpdate("DROP TABLE IF EXISTS UserMarketplace");
		stm.executeUpdate(
				"CREATE TABLE UserMarketplace ("
				+ "user_fk VARCHAR(30) NOT NULL,"
				+ "mkt_fk INTEGER NOT NULL,"	
				+ "CONSTRAINT user_fk FOREIGN KEY (user_fk) REFERENCES User (Username) ON DELETE CASCADE, "
				+ "CONSTRAINT mkt_fk FOREIGN KEY (mkt_fk) REFERENCES Marketplace (ID) ON DELETE CASCADE);");
	}
	private static void createCommentMarketplace(Statement stm) throws SQLException {
		stm.executeUpdate("DROP TABLE IF EXISTS CommentMarketplace");
		stm.executeUpdate(
				"CREATE TABLE CommentMarketplace ("
				+ "comment_fk INTEGER NOT NULL,"
				+ "mkt_fk INTEGER NOT NULL,"	
				+ "CONSTRAINT comment_fk FOREIGN KEY (comment_fk) REFERENCES Comment (ID) ON DELETE CASCADE, "
				+ "CONSTRAINT mkt_fk FOREIGN KEY (mkt_fk) REFERENCES Marketplace (ID) ON DELETE CASCADE);");
	}
	private static void createGameEvents(Statement stm) throws SQLException {
		stm.executeUpdate("DROP TABLE IF EXISTS GameEvents");
		stm.executeUpdate(
				"CREATE TABLE GameEvents (EventID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,"
				+ "EventName VARCHAR(50) NOT NULL,"
				+ "EventDate VARCHAR(10) NOT NULL,"	
				+ "EventLocal VARCHAR(50) NOT NULL,"
				+ "EventDescription VARCHAR(300) NOT NULL,"
				+ "GameName VARCHAR(50) NOT NULL);");
		stm.executeUpdate(
			"INSERT INTO GameEvents (EventName, EventDate, EventLocal, EventDescription, GameName) "
			+ "VALUES ('CSGO Championship','10/11/2020','Alianz Arena - München, DE',"
			+ "'The great Deutsch Counter Strike Championship is finally here!','Counter Strike Global Offensive')");
		stm.executeUpdate(
			"INSERT INTO GameEvents (EventName, EventDate, EventLocal, EventDescription, GameName) "
			+ "VALUES ('The Crazy FortNite','14/11/2020','Alianz Arena - München, DE',"
			+ "'A FortNite event, join with your squad!','FortNite')");
	}
	private static void createUserGameEvents(Statement stm) throws SQLException {
		stm.executeUpdate("DROP TABLE IF EXISTS UserGameEvents");
		stm.executeUpdate(
				"CREATE TABLE UserGameEvents ("
				+ "user_fk VARCHAR(30) NOT NULL,"
				+ "gameevents_fk INTEGER NOT NULL,"	
				+ "CONSTRAINT user_fk FOREIGN KEY (user_fk) REFERENCES User (Username) ON DELETE CASCADE, "
				+ "CONSTRAINT gameevents_fk FOREIGN KEY (gameevents_fk) REFERENCES GameEvents (EventID) ON DELETE CASCADE);");
	}
	private static void createCommentGameEvents(Statement stm) throws SQLException {
		stm.executeUpdate("DROP TABLE IF EXISTS CommentGameEvents");
		stm.executeUpdate(
				"CREATE TABLE CommentGameEvents ("
				+ "comment_fk INTEGER NOT NULL,"
				+ "gameevents_fk INTEGER NOT NULL,"	
				+ "CONSTRAINT comment_fk FOREIGN KEY (comment_fk) REFERENCES Comment (ID) ON DELETE CASCADE, "
				+ "CONSTRAINT gameevents_fk FOREIGN KEY (gameevents_fk) REFERENCES GameEvents (EventID) ON DELETE CASCADE);");
	}	
	private static void createDevEvents(Statement stm) throws SQLException {
		stm.executeUpdate("DROP TABLE IF EXISTS DevEvents");
		stm.executeUpdate(
				"CREATE TABLE DevEvents (EventID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,"
				+ "EventName VARCHAR(50) NOT NULL,"
				+ "EventDate VARCHAR(10) NOT NULL,"	
				+ "EventLocal VARCHAR(50) NOT NULL,"
				+ "EventDescription VARCHAR(300) NOT NULL);");
		stm.executeUpdate(
			"INSERT INTO DevEvents (EventName, EventDate, EventLocal, EventDescription) "
			+ "VALUES('Google Hackathon','08/05/2021','GooglePlex - San Francisco, US',"
			+ "'Come and show your skills to the world!')");
		stm.executeUpdate(
			"INSERT INTO DevEvents (EventName, EventDate, EventLocal, EventDescription) "
			+ "VALUES ('Microsoft Development Day','21/08/2021','Santa Catarina Federal University - Florianópolis, BR',"
			+ "'Great names of Microsoft to explain the future of programing')");
	}
	private static void createUserDevEvents(Statement stm) throws SQLException {
		stm.executeUpdate("DROP TABLE IF EXISTS UserDevEvents");
		stm.executeUpdate(
				"CREATE TABLE UserDevEvents ("
				+ "user_fk VARCHAR(30) NOT NULL,"
				+ "devevents_fk INTEGER NOT NULL,"	
				+ "CONSTRAINT user_fk FOREIGN KEY (user_fk) REFERENCES User (Username) ON DELETE CASCADE, "
				+ "CONSTRAINT devevents_fk FOREIGN KEY (devevents_fk) REFERENCES DevEvents (EventID) ON DELETE CASCADE);");
	}
	private static void createCommentDevEvents(Statement stm) throws SQLException {
		stm.executeUpdate("DROP TABLE IF EXISTS CommentDevEvents");
		stm.executeUpdate(
				"CREATE TABLE CommentDevEvents ("
				+ "comment_fk INTEGER NOT NULL,"
				+ "Devevents_fk INTEGER NOT NULL,"	
				+ "CONSTRAINT comment_fk FOREIGN KEY (comment_fk) REFERENCES Comment (ID) ON DELETE CASCADE, "
				+ "CONSTRAINT Devevents_fk FOREIGN KEY (Devevents_fk) REFERENCES DevEvents (EventID) ON DELETE CASCADE);");
	}
	private static void createFollow(Statement stm) throws SQLException {
		stm.executeUpdate("DROP TABLE IF EXISTS Follow");
		stm.executeUpdate(
				"CREATE TABLE Follow (Username VARCHAR(30) NOT NULL PRIMARY KEY,"
				+ "Birthdate VARCHAR(10) NOT NULL,"
				+ "Relatioship VARCHAR(30) NOT NULL);");
	}
	private static void createUserFollow(Statement stm) throws SQLException {
		stm.executeUpdate("DROP TABLE IF EXISTS UserFollow");
		stm.executeUpdate(
				"CREATE TABLE UserFollow ("
				+ "user_fk VARCHAR(30) NOT NULL,"
				+ "follow_fk VARCHAR(50) NOT NULL,"	
				+ "CONSTRAINT user_fk FOREIGN KEY (user_fk) REFERENCES User (Username) ON DELETE CASCADE, "
				+ "CONSTRAINT follow_fk FOREIGN KEY (follow_fk) REFERENCES Follow (Username) ON DELETE CASCADE);");
	}
	private static void createComments(Statement stm) throws SQLException {
		stm.executeUpdate("DROP TABLE IF EXISTS Comments");
		stm.executeUpdate(
				"CREATE TABLE Comments (ID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,"
				+ "Text VARCHAR(300) NOT NULL);");
	}
	private static void createUserComments(Statement stm) throws SQLException {
		stm.executeUpdate("DROP TABLE IF EXISTS UserComments");
		stm.executeUpdate(
				"CREATE TABLE UserComments ("
				+ "user_fk VARCHAR(30) NOT NULL,"
				+ "comment_fk INTEGER NOT NULL,"	
				+ "CONSTRAINT user_fk FOREIGN KEY (user_fk) REFERENCES User (Username) ON DELETE CASCADE, "
				+ "CONSTRAINT comment_fk FOREIGN KEY (comment_fk) REFERENCES Follow (Comment) ON DELETE CASCADE);");
	}
	private static void createPost(Statement stm) throws SQLException {
		stm.executeUpdate("DROP TABLE IF EXISTS Post");
		stm.executeUpdate(
				"CREATE TABLE Post (ID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,"
				+ "Content VARCHAR(300) NOT NULL);");
	}
	private static void createUserPost(Statement stm) throws SQLException {
		stm.executeUpdate("DROP TABLE IF EXISTS UserPost");
		stm.executeUpdate(
				"CREATE TABLE UserPost ("
				+ "user_fk VARCHAR(30) NOT NULL,"
				+ "post_fk INTEGER NOT NULL,"	
				+ "CONSTRAINT user_fk FOREIGN KEY (user_fk) REFERENCES User (Username) ON DELETE CASCADE, "
				+ "CONSTRAINT post_fk FOREIGN KEY (post_fk) REFERENCES Post (ID) ON DELETE CASCADE);");
	}
	private static void createCommentPost(Statement stm) throws SQLException {
		stm.executeUpdate("DROP TABLE IF EXISTS CommentPost");
		stm.executeUpdate(
				"CREATE TABLE CommentPost ("
				+ "comment_fk INTEGER NOT NULL,"
				+ "post_fk INTEGER NOT NULL,"	
				+ "CONSTRAINT comment_fk FOREIGN KEY (comment_fk) REFERENCES Comment (ID) ON DELETE CASCADE, "
				+ "CONSTRAINT post_fk FOREIGN KEY (post_fk) REFERENCES Post (ID) ON DELETE CASCADE);");
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