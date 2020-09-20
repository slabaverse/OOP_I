package dataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import UI_FX.Alert_FX;

public class UtilBD {
    private static Connection conection;

    public static Connection getConection() {
	try {

	    if (conection == null)
		openConection();

	    if (conection.isClosed())
		openConection();

	} catch (SQLException e) {
	    Alert_FX.error("{ ERROR: COULDN'T OPEN DATABASE CONECTION }");
	}

	return conection;
    }

    private static void openConection() {
	try {
	    Class.forName("org.sqlite.JDBC");
	    conection = DriverManager.getConnection("jdbc:sqlite:banco.sqlite");
	} catch (SQLException e) {
	    Alert_FX.error("{ ERROR: COULDN'T OPEN DATABASE CONECTION }");
	} catch (ClassNotFoundException e2) {
	    Alert_FX.error("{ ERROR: PROBLEM WITH SQL LIB }");
	}
    }

    public static void closeConection() {
	if (conection == null)
	    return;

	try {
	    if (!conection.isClosed())
		conection.close();
	} catch (SQLException e) {
	    Alert_FX.error("{ ERROR: COULDN'T CREATE DATABASE CONNECTION }");
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
	    createPost(stm);
	    createUserMarketplace(stm);
	    createUserGameEvents(stm);
	    createUserDevEvents(stm);
	    createUserPost(stm);
	    stm.executeUpdate("PRAGMA foreign_keys=ON");
	    stm.close();
	} catch (SQLException e) {
	    Alert_FX.error("{ ERROR: COULDN'T CREATE DATABASE }");
	    System.out.println(e);
	}
    }

    private static void createUser(Statement stm) throws SQLException {
	stm.executeUpdate("DROP TABLE IF EXISTS User");
	stm.executeUpdate("CREATE TABLE User (id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,"
		+ "username VARCHAR(50) NOT NULL, " + " password VARCHAR(50) NOT NULL," + " name VARCHAR(150) NOT NULL,"
		+ " birthdate VARCHAR(10) NOT NULL," + " relationship VARCHAR (50) NOT NULL);");
	stm.executeUpdate("INSERT INTO User (username, password, name, birthdate, relationship) "
		+ "VALUES('@adam','123','Adam Slabadack','05/08/1989','Married')");
	stm.executeUpdate("INSERT INTO User (username, password, name, birthdate, relationship) "
		+ "VALUES('@angela','456','Angela Stupp','04/03/1988','Married')");
    }

    private static void createMarketplace(Statement stm) throws SQLException {
	stm.executeUpdate("DROP TABLE IF EXISTS Marketplace");
	stm.executeUpdate("CREATE TABLE Marketplace (id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,"
		+ "username VARCHAR(50) NOT NULL," + "product VARCHAR(100) NOT NULL," + "price DOUBLE,"
		+ "description VARCHAR(300) NOT NULL, "
		+ "CONSTRAINT username FOREIGN KEY (username) REFERENCES User (username) ON UPDATE CASCADE ON DELETE CASCADE);");
	stm.executeUpdate("INSERT INTO Marketplace (username, product, price, description) "
		+ "VALUES('@adam', 'PlayStation 5', 4500.00,'New Sony console released in 2020')");
    }

    private static void createGameEvents(Statement stm) throws SQLException {
	stm.executeUpdate("DROP TABLE IF EXISTS GameEvents");
	stm.executeUpdate("CREATE TABLE GameEvents (id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,"
		+ "eventName VARCHAR(150) NOT NULL," + "eventDate VARCHAR(10) NOT NULL,"
		+ "eventLocal VARCHAR(150) NOT NULL," + "eventDescription VARCHAR(300) NOT NULL,"
		+ "gameName VARCHAR(150) NOT NULL);");
		//+ "CONSTRAINT username FOREIGN KEY (username) REFERENCES User (username) ON UPDATE CASCADE ON DELETE CASCADE);");
	stm.executeUpdate(
		"INSERT INTO GameEvents (eventName, eventDate, eventLocal, eventDescription, GameName) "
			+ "VALUES('CSGO Championship','10/11/2020','Alianz Arena - München, DE',"
			+ "'The great Deutsch Counter Strike Championship is finally here!','Counter Strike Global Offensive')");
	stm.executeUpdate(
		"INSERT INTO GameEvents (eventName, eventDate, eventLocal, eventDescription, GameName) "
			+ "VALUES('The Crazy FortNite','14/11/2020','Alianz Arena - München, DE',"
			+ "'A FortNite event, join with your squad!','FortNite')");
    }

    private static void createDevEvents(Statement stm) throws SQLException {
	stm.executeUpdate("DROP TABLE IF EXISTS DevEvents");
	stm.executeUpdate("CREATE TABLE DevEvents (id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,"
		+ "username VARCHAR(50)," + "eventname VARCHAR(50) NOT NULL," + "eventdate VARCHAR(10) NOT NULL,"
		+ "eventlocal VARCHAR(50) NOT NULL," + "eventdescription VARCHAR(300) NOT NULL, "
		+ "CONSTRAINT username FOREIGN KEY (username) REFERENCES User (username) ON UPDATE CASCADE ON DELETE CASCADE);");
	stm.executeUpdate("INSERT INTO DevEvents (username, eventname, eventdate, eventlocal, eventdescription) "
		+ "VALUES('@angela','Google Hackathon','08/05/2021','GooglePlex - San Francisco, US',"
		+ "'Come and show your skills to the world!')");
	stm.executeUpdate("INSERT INTO DevEvents (username, eventname, eventdate, eventlocal, eventdescription) "
		+ "VALUES('@adam','Microsoft Development Day','21/08/2021','Santa Catarina Federal University - Florianópolis, BR',"
		+ "'Great names of Microsoft to explain the future of programing')");
    }

    private static void createFollow(Statement stm) throws SQLException {
	stm.executeUpdate("DROP TABLE IF EXISTS Follow");
	stm.executeUpdate("CREATE TABLE Follow (follower_fk INTEGER NOT NULL," + "followed_fk INTEGER NOT NULL, "
		+ "CONSTRAINT follower_fk FOREIGN KEY (follower_fk) REFERENCES User (id) ON UPDATE CASCADE ON DELETE CASCADE, "
		+ "CONSTRAINT followed_fk FOREIGN KEY (followed_fk) REFERENCES User (id) ON UPDATE CASCADE ON DELETE CASCADE);");
	stm.executeUpdate("INSERT INTO Follow (follower_fk, followed_fk) " + "VALUES(1,2)");
    }

    private static void createPost(Statement stm) throws SQLException {
	stm.executeUpdate("DROP TABLE IF EXISTS Post");
	stm.executeUpdate("CREATE TABLE Post (id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," + "username VARCHAR(50),"
		+ "content VARCHAR(300) NOT NULL, "
		+ "CONSTRAINT username_fk FOREIGN KEY (username) REFERENCES User (username) ON UPDATE CASCADE ON DELETE CASCADE);");
	stm.executeUpdate("INSERT INTO Post (username, content) "
		+ "VALUES('@adam','Primeiro post, Skynet bombando')");
	stm.executeUpdate("INSERT INTO Post (username, content) "
		+ "VALUES('@angela','Qual a bora de hoje?')");

    }

    private static void createUserMarketplace(Statement stm) throws SQLException {
	stm.executeUpdate("DROP TABLE IF EXISTS UserMarketplace");
	stm.executeUpdate("CREATE TABLE UserMarketplace (user_fk INTEGER NOT NULL, mkt_fk INTEGER NOT NULL, "
		+ "CONSTRAINT user_fk FOREIGN KEY (user_fk) REFERENCES User (id) ON UPDATE CASCADE ON DELETE CASCADE, "
		+ "CONSTRAINT mkt_fk FOREIGN KEY (mkt_fk) REFERENCES Marketplace (id) ON UPDATE CASCADE ON DELETE CASCADE);");
	stm.executeUpdate("INSERT INTO UserMarketplace (user_fk, mkt_fk) VALUES (1,1)");
    }

    private static void createUserGameEvents(Statement stm) throws SQLException {
	stm.executeUpdate("DROP TABLE IF EXISTS UserGameEvents");
	stm.executeUpdate("CREATE TABLE UserGameEvents (user_fk INTEGER NOT NULL," + "gameevents_fk INTEGER NOT NULL, "
		+ "CONSTRAINT user_fk FOREIGN KEY (user_fk) REFERENCES User (id) ON UPDATE CASCADE ON DELETE CASCADE, "
		+ "CONSTRAINT gameevents_fk FOREIGN KEY (gameevents_fk) REFERENCES GameEvents (id) ON UPDATE CASCADE ON DELETE CASCADE);");
	stm.executeUpdate("INSERT INTO UserGameEvents (user_fk, gameevents_fk) VALUES (1,1)");
	stm.executeUpdate("INSERT INTO UserGameEvents (user_fk, gameevents_fk) VALUES (2,2)");
    }

    private static void createUserDevEvents(Statement stm) throws SQLException {
	stm.executeUpdate("DROP TABLE IF EXISTS UserDevEvents");
	stm.executeUpdate("CREATE TABLE UserDevEvents (user_fk INTEGER NOT NULL," + "devevents_fk INTEGER NOT NULL, "
		+ "CONSTRAINT user_fk FOREIGN KEY (user_fk) REFERENCES User (id) ON UPDATE CASCADE ON DELETE CASCADE, "
		+ "CONSTRAINT devevents_fk FOREIGN KEY (devevents_fk) REFERENCES DevEvents (id) ON UPDATE CASCADE ON DELETE CASCADE);");
	stm.executeUpdate("INSERT INTO UserDevEvents (user_fk, devevents_fk) VALUES (1,1)");
    }

    private static void createUserPost(Statement stm) throws SQLException {
	stm.executeUpdate("DROP TABLE IF EXISTS UserPost");
	stm.executeUpdate("CREATE TABLE UserPost (user_fk INTEGER NOT NULL, post_fk INTEGER NOT NULL, "
		+ "CONSTRAINT user_fk FOREIGN KEY (user_fk) REFERENCES User (id) ON UPDATE CASCADE ON DELETE CASCADE, "
		+ "CONSTRAINT post_fk FOREIGN KEY (post_fk) REFERENCES Post (id) ON UPDATE CASCADE ON DELETE CASCADE);");
    }

    public static void updateDB(String sql) throws SQLException {
	Connection bd = UtilBD.getConection();
	Statement stm = bd.createStatement();
	stm.executeUpdate(sql);
	stm.close();
    }

    public static ResultSet consultDB(String sql) throws SQLException {
	Connection bd = UtilBD.getConection();
	Statement stm = bd.createStatement();
	ResultSet retorno = stm.executeQuery(sql);
	return retorno;
    }
}