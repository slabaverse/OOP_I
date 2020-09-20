package program;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import UI_FX.Alert_FX;
import UI_FX.Login_FX;
import dataBase.DevEventsDAO;
import dataBase.FollowDAO;
import dataBase.GameEventsDAO;
import dataBase.MarketplaceDAO;
import dataBase.PostDAO;
import dataBase.UserDAO;
import dataBase.UtilBD;
import entities.DevEvents;
import entities.Follow;
import entities.GameEvents;
import entities.Marketplace;
import entities.Post;
import entities.User;
import javafx.application.Application;

public class Main {

    public static void main(String[] args) {

	UtilBD.initBD();

	Application.launch(Login_FX.class);

	UtilBD.closeConection();

	List<User> users = new ArrayList<>();

	DevEventsDAO devEventsDAO = new DevEventsDAO();
	FollowDAO followDAO = new FollowDAO();
	GameEventsDAO gameEventsDAO = new GameEventsDAO();
	MarketplaceDAO marketplaceDAO = new MarketplaceDAO();
	PostDAO postDAO = new PostDAO();
	UserDAO userDAO = new UserDAO();

	int i = 0, choose = 0, tempChoose = 0;
	User loggedUser = null;

	Scanner sc = new Scanner(System.in);

	while (true) {
	    System.out.println("███████╗██╗  ██╗██╗   ██╗███╗   ██╗███████╗████████╗    ██╗    ██╗██╗ ");
	    System.out.println("██╔════╝██║ ██╔╝╚██╗ ██╔╝████╗  ██║██╔════╝╚══██╔══╝   ██╔╝   ██╔╝╚██╗");
	    System.out.println("███████╗█████╔╝  ╚████╔╝ ██╔██╗ ██║█████╗     ██║      ██║   ██╔╝  ██║");
	    System.out.println("╚════██║██╔═██╗   ╚██╔╝  ██║╚██╗██║██╔══╝     ██║      ██║  ██╔╝   ██║");
	    System.out.println("███████║██║  ██╗   ██║   ██║ ╚████║███████╗   ██║      ╚██╗██╔╝   ██╔╝");
	    System.out.println("╚══════╝╚═╝  ╚═╝   ╚═╝   ╚═╝  ╚═══╝╚══════╝   ╚═╝       ╚═╝╚═╝    ╚═╝ ");
	    System.out.println("                   {SKYNET(/) - CONNECTING DEV'S}");
	    System.out.println();
	    do {
		System.out.println("{1} NEW ACCOUNT ~ {2} LOGIN");
		System.out.print(":~$ ");
		choose = sc.nextInt();
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		switch (choose) {
		case 1:
		    sc.nextLine();
		    System.out.print("USERNAME:~$ ");
		    String username = sc.nextLine();
		    System.out.print("PASSWORD:~$ ");
		    String password = sc.nextLine();
		    System.out.print("FULL NAME:~$ ");
		    String name = sc.nextLine();
		    System.out.print("BIRTHDATE (dd/MM/yyyy):~$ ");
		    String birthdate = sc.nextLine();
		    System.out.print("RELATIONSHIP:~$ ");
		    String relationship = sc.nextLine();
		    System.out.println();
		    User newUser = new User(username, password, name, birthdate, relationship);
		    userDAO.add(newUser);
		    break;
		case 2:
		    sc.nextLine();
		    do {
			System.out.print("USERNAME:~$ ");
			String usernameCheck = sc.nextLine();
			System.out.print("PASSWORD:~$ ");
			String passwordCheck = sc.nextLine();
			System.out.println();
			List<User> userList = userDAO.all();

			for (i = 0; i < userList.size(); i++) {
			    if (userList.get(i).getUsername().contentEquals(usernameCheck)
				    && userList.get(i).getPassword().contentEquals(passwordCheck)) {
				loggedUser = userList.get(i);
				break;
			    }
			}
		    } while (loggedUser == null);
		}
	    } while (loggedUser == null);

	    do {
		System.out.println();
		System.out.println("███████╗██╗  ██╗██╗   ██╗███╗   ██╗███████╗████████╗    ██╗    ██╗██╗ ");
		System.out.println("██╔════╝██║ ██╔╝╚██╗ ██╔╝████╗  ██║██╔════╝╚══██╔══╝   ██╔╝   ██╔╝╚██╗");
		System.out.println("███████╗█████╔╝  ╚████╔╝ ██╔██╗ ██║█████╗     ██║      ██║   ██╔╝  ██║");
		System.out.println("╚════██║██╔═██╗   ╚██╔╝  ██║╚██╗██║██╔══╝     ██║      ██║  ██╔╝   ██║");
		System.out.println("███████║██║  ██╗   ██║   ██║ ╚████║███████╗   ██║      ╚██╗██╔╝   ██╔╝");
		System.out.println("╚══════╝╚═╝  ╚═╝   ╚═╝   ╚═╝  ╚═══╝╚══════╝   ╚═╝       ╚═╝╚═╝    ╚═╝ ");
		System.out.println("                   {SKYNET(/) - CONNECTING DEV'S}");
		System.out.println();
		System.out.println("                    {LOGGED AS " + loggedUser.getUsername() + "}");
		System.out.println();
		System.out.println();
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println();
		System.out.println();
		System.out.println("{ " + loggedUser.getUsername() + "'s POSTS }");
		List<Post> showPosts = userDAO.getPost(loggedUser);
		for (i = 0; i < showPosts.size(); i++) {
		    System.out.println("# " + showPosts.get(i).getIdPost() + " - " + showPosts.get(i).getContent());
		}
		System.out.println();
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println("{1} USER SETTINGS");
		System.out.println("{2} POSTS");
		System.out.println("{3} MARKETPLACE");
		System.out.println("{4} FRIENDS");
		System.out.println("{5} EVENTS");
		System.out.println("{6} LOGOUT");
		System.out.print(":~$ ");
		choose = sc.nextInt();
		System.out.println();
		if (choose == 1) {
		    System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		    System.out.println("{SET USER INFORMATIONS}");
		    System.out.println();
		    sc.nextLine();
		    System.out.print("USERNAME:~$ ");
		    loggedUser.setUsername(sc.nextLine());
		    System.out.print("PASSWORD:~$ ");
		    loggedUser.setPassword(sc.nextLine());
		    System.out.print("FULL NAME:~$ ");
		    loggedUser.setName(sc.nextLine());
		    System.out.print("BIRTHDATE (dd/MM/yyyy):~$ ");
		    loggedUser.setBirthdate(sc.nextLine());
		    System.out.print("RELATIONSHIP:~$ ");
		    loggedUser.setRelationship(sc.nextLine());
		    System.out.println();
		    userDAO.update(loggedUser);
		}
		if (choose == 2) {
		    System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		    System.out.println("{POSTS}");
		    System.out.println();
		    System.out.println("{1} WHATS ON YOUR MIND?");
		    System.out.println("{2} LIST YOUR POSTS");
		    System.out.println("{3} LIST ALL POSTS");
		    System.out.println("{4} MAIN MENU");
		    System.out.print(":~$ ");
		    System.out.println();
		    tempChoose = sc.nextInt();
		    System.out.println();
		    switch (tempChoose) {
		    case 1:
			sc.nextLine();
			System.out.print("WHATS ON YOUR MIND?:~$ ");
			String content = sc.nextLine();
			Post newPost = new Post(loggedUser, content);
			postDAO.add(newPost);
			break;
		    case 2:
			if (userDAO.getPost(loggedUser).isEmpty()) {
			    Alert_FX.info("{YOU DON'T HAVE ANY POST YET}");
			} else {
			    List<Post> postList = userDAO.getPost(loggedUser);
			    for (i = 0; i < postList.size(); i++) {
				System.out.println("#" + (i + 1) + " - " + postList.get(i).getContent());
				System.out.println();
			    }
			    do {
				System.out.println();
				System.out.println("{1} EDIT POST ~ {2} REMOVE POST ~ {3} MAIN MENU");
				tempChoose = sc.nextInt();
				System.out.println();
				switch (tempChoose) {
				case 1:
				    sc.nextLine();
				    System.out.println(
					    "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
				    System.out.print("SELECT THE POST NUMBER THAT YOU WANT TO EDIT:~$ ");
				    Integer postNum = sc.nextInt();
				    postNum--;
				    System.out.println();
				    sc.nextLine();
				    System.out.print("WRITE A NEW CONTENT:~$ ");
				    postList.get(postNum).setContent(sc.nextLine());
				    postDAO.update(postList.get(postNum));
				    break;
				case 2:
				    sc.nextLine();
				    System.out.print("SELECT THE POST NUMBER THAT YOU WANT TO REMOVE:~$ ");
				    Integer postNumRemove = sc.nextInt();
				    sc.nextLine();
				    postNumRemove--;
				    postDAO.remove(postList.get(postNumRemove));
				    Alert_FX.info("{ POST DELETED }");
				    break;
				case 3:
				    break;
				}
			    } while (tempChoose != 3);
			}
			break;
		    case 3:
			List<Post> allPost = postDAO.all();
			for (i = 0; i < allPost.size(); i++) {
			    System.out.println("# " + (i + 1) + " by " + allPost.get(i).getUser().getUsername());
			    System.out.println("~: " + allPost.get(i).getContent());
			}
			break;
		    case 4:
			break;
		    }
		}
		if (choose == 3) {
		    System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		    System.out.println("{MARKETPLACE}");
		    System.out.println();
		    System.out.println("{1} NEW SALE");
		    System.out.println("{2} LIST YOUR SALES");
		    System.out.println("{3} LIST ALL SALES");
		    System.out.println("{4} MAIN MENU");
		    System.out.print(":~$ ");
		    tempChoose = sc.nextInt();
		    System.out.println();
		    switch (tempChoose) {
		    case 1:
			System.out.println(
				"~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			sc.nextLine();
			System.out.print("PRODUCT:~$ ");
			String product = sc.nextLine();
			System.out.print("PRICE ($):~$ ");
			Double price = sc.nextDouble();
			sc.nextLine();
			System.out.print("PRODUCT DESCRIPTION:~$ ");
			String description = sc.nextLine();
			Marketplace newMkt = new Marketplace(loggedUser, product, price, description);
			marketplaceDAO.add(newMkt);
			break;
		    case 2:
			if (userDAO.getMarketplace(loggedUser).isEmpty()) {
			    Alert_FX.info("{YOU DON'T HAVE ANY PRODUCTS YET}");
			} else {
			    List<Marketplace> mktList = userDAO.getMarketplace(loggedUser);
			    for (i = 0; i < mktList.size(); i++) {
				System.out.println("#" + (i + 1) + " - " + mktList.get(i).getProduct() + " - R$ "
					+ mktList.get(i).getPrice());
				System.out.println(mktList.get(i).getDescription());
				System.out.println();
			    }
			    do {
				System.out.println();
				System.out.println("{1} EDIT PRODUCT ~ {2} REMOVE PRODUCT ~ {3} MAIN MENU");
				tempChoose = sc.nextInt();
				System.out.println();
				switch (tempChoose) {
				case 1:
				    sc.nextLine();
				    System.out.println(
					    "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
				    System.out.print("SELECT A PRODUCT NUMBER THAT YOU WANT TO EDIT:~$ ");
				    Integer mkttNum = sc.nextInt();
				    mkttNum--;
				    System.out.println();
				    sc.nextLine();
				    System.out.print("WRITE A NEW PRODUCT NAME:~$ ");
				    mktList.get(mkttNum).setProduct(sc.nextLine());
				    marketplaceDAO.update(mktList.get(mkttNum));
				    System.out.print("WRITE A NEW PRODUCT PRICE:~$ ");
				    mktList.get(mkttNum).setPrice(sc.nextDouble());
				    marketplaceDAO.update(mktList.get(mkttNum));
				    sc.nextLine();
				    System.out.print("WRITE A NEW PRODUCT DESCRIPTION:~$ ");
				    mktList.get(mkttNum).setDescription(sc.nextLine());
				    marketplaceDAO.update(mktList.get(mkttNum));
				    break;
				case 2:
				    sc.nextLine();
				    System.out.print("SELECT A PRODUCT NUMBER THAT YOU WANT TO REMOVE:~$ ");
				    Integer mktNumRemove = sc.nextInt();
				    sc.nextLine();
				    mktNumRemove--;
				    marketplaceDAO.remove(mktList.get(mktNumRemove));
				    Alert_FX.info("{ PRODUCT DELETED }");
				    break;
				case 3:
				    break;
				}
			    } while (tempChoose != 3);
			}
			break;
		    case 3:
			List<Marketplace> allMkt = marketplaceDAO.all();
			for (i = 0; i < allMkt.size(); i++) {
			    System.out.println("# " + (i + 1) + " by " + allMkt.get(i).getUser().getUsername());
			    System.out.println("~: " + allMkt.get(i).getProduct() + " R$ " + allMkt.get(i).getPrice());
			    System.out.println(allMkt.get(i).getDescription());
			}
			break;
		    case 4:
			break;
		    }
		}
		if (choose == 4) {
		    System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		    System.out.println("{YOUR FRIENDS}");
		    System.out.println();
		    List<User> followedUsers = userDAO.getFollowed(loggedUser);
		    for (i = 0; i < followedUsers.size(); i++) {
			System.out.println("#" + (i + 1) + " " + followedUsers.get(i).getUsername());
		    }
		    System.out.println();
		    System.out.println("{1} LIST ALL PEOPLE");
		    System.out.println("{2} UNFOLLOW FRIEND");
		    System.out.println("{3} MAIN MENU");
		    System.out.print(":~$ ");
		    tempChoose = sc.nextInt();
		    System.out.println();
		    switch (tempChoose) {
		    case 1:
			do {
			    System.out.println();
			    List<User> allUsers = userDAO.all();

			    for (i = 0; i < allUsers.size(); i++) {
				System.out.println("#: " + (i + 1) + " " + allUsers.get(i).getName());
			    }
			    System.out.println();
			    System.out.println("{1} FOLLOW NEW FRIEND");
			    System.out.println("{2} MAIN MENU");
			    tempChoose = sc.nextInt();
			    switch (tempChoose) {
			    case 1:
				System.out.println(
					"~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
				sc.nextLine();
				System.out.print("FRIEND NUMBER:~$ ");
				Integer friendNumber = sc.nextInt();
				friendNumber--;

				Follow follow = new Follow(allUsers.get(friendNumber), loggedUser);
				followDAO.add(follow);
				break;
			    case 2:
				break;
			    }
			} while (tempChoose != 2);
			break;
		    case 2:
			System.out.println();
			sc.nextLine();
			System.out.print("FRIEND NUMBER:~$ ");
			Integer friendNameRemove = sc.nextInt();
			friendNameRemove--;

			Follow follow = new Follow(followedUsers.get(friendNameRemove), loggedUser);
			followDAO.remove(follow);

			break;
		    case 3:
			break;
		    }
		}
		if (choose == 5) {
		    System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		    System.out.println("{EVENTS}");
		    System.out.println();
		    System.out.println("{1} NEW EVENT");
		    System.out.println("{2} LIST YOUR DEV EVENTS");
		    System.out.println("{3} LIST YOUR GAME EVENTS");
		    System.out.println("{4} LIST ALL EVENTS");
		    System.out.println("{5} MAIN MENU");
		    System.out.print(":~$ ");
		    tempChoose = sc.nextInt();
		    System.out.println();
		    switch (tempChoose) {
		    case 1:
			System.out.println(
				"~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			System.out.println("{EVENT TYPE}");
			System.out.println();
			System.out.println("{1} DEV EVENT");
			System.out.println("{2} GAME EVENT");
			System.out.println("{3} MAIN MENU");
			System.out.println(":~$ ");
			tempChoose = sc.nextInt();
			sc.nextLine();
			switch (tempChoose) {
			case 1:
			    System.out.println(
				    "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			    System.out.print("EVENT'S NAME:~$ ");
			    String devEventName = sc.nextLine();
			    System.out.print("EVENT'S DATE (dd/MM/yyyy):~$ ");
			    String devEventDate = sc.nextLine();
			    System.out.print("EVENT'S LOCAL:~$ ");
			    String devEventLocal = sc.nextLine();
			    System.out.print("EVENT'S DESCRIPTION:~$ ");
			    String devEventDescription = sc.nextLine();
			    DevEvents newDevEvent = new DevEvents(loggedUser, devEventName, devEventDate, devEventLocal,
				    devEventDescription);
			    devEventsDAO.add(newDevEvent);
			    tempChoose = 0;
			    break;
			case 2:

			    System.out.println(
				    "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			    System.out.print("EVENT'S NAME:~$ ");
			    String gameEventName = sc.nextLine();
			    System.out.print("EVENT'S DATE (dd/MM/yyyy):~$ ");
			    String gameEventDate = sc.nextLine();
			    System.out.print("EVENT'S LOCAL:~$ ");
			    String gameEventLocal = sc.nextLine();
			    System.out.print("EVENT'S DESCRIPTION:~$ ");
			    String gameEventDescription = sc.nextLine();
			    System.out.print("EVENT'S MAIN GAME:~$ ");
			    String gameName = sc.nextLine();
			    GameEvents newGameEvent = new GameEvents(loggedUser, gameEventName, gameEventDate,
				    gameEventLocal, gameEventDescription, gameName);
			    gameEventsDAO.add(newGameEvent);
			    tempChoose = 0;
			    break;
			case 3:
			    tempChoose = 0;
			    break;
			}
			break;
		    case 2:
			if (userDAO.getDevEvents(loggedUser).isEmpty()) {
			    Alert_FX.info("{YOU DON'T HAVE ANY DEV EVENT YET}");
			} else {
			    List<DevEvents> devList = userDAO.getDevEvents(loggedUser);
			    for (i = 0; i < devList.size(); i++) {
				System.out.println("#" + (i + 1) + " { " + devList.get(i).getEventName() + " } " + " - "
					+ devList.get(i).getEventDate() + " - " + devList.get(i).getEventLocal());
				System.out.println(devList.get(i).getEventDescription());
				System.out.println();
			    }
			    do {
				System.out.println();
				System.out.println("{1} EDIT EVENT ~ {2} REMOVE EVENT ~ {3} MAIN MENU");
				tempChoose = sc.nextInt();
				System.out.println();
				switch (tempChoose) {
				case 1:
				    sc.nextLine();
				    System.out.println(
					    "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
				    System.out.print("SELECT A DEV EVENT NUMBER THAT YOU WANT TO EDIT:~$ ");
				    Integer devNum = sc.nextInt();
				    devNum--;
				    System.out.println();
				    sc.nextLine();
				    System.out.print("WRITE A NEW DEV EVENT NAME:~$ ");
				    devList.get(devNum).setEventName(sc.nextLine());
				    devEventsDAO.update(devList.get(devNum));
				    System.out.print("WRITE A NEW DEV EVENT DATE:~$ ");
				    devList.get(devNum).setEventDate(sc.nextLine());
				    devEventsDAO.update(devList.get(devNum));
				    System.out.print("WRITE A NEW DEV EVENT LOCAL:~$ ");
				    devList.get(devNum).setEventLocal(sc.nextLine());
				    devEventsDAO.update(devList.get(devNum));
				    System.out.print("WRITE A NEW DEV EVENT DESCRIPTION:~$ ");
				    devList.get(devNum).setEventDescription(sc.nextLine());
				    devEventsDAO.update(devList.get(devNum));
				    break;
				case 2:
				    sc.nextLine();
				    System.out.print("SELECT A DEV EVENT NUMBER THAT YOU WANT TO REMOVE:~$ ");
				    Integer devNumRemove = sc.nextInt();
				    sc.nextLine();
				    devNumRemove--;
				    devEventsDAO.remove(devList.get(devNumRemove));
				    Alert_FX.info("{ EVENT DELETED }");
				    break;
				case 3:
				    break;
				}
			    } while (tempChoose != 3);
			}
			break;
		    case 3:
			if (userDAO.getGameEvents(loggedUser).isEmpty()) {
			    Alert_FX.info("{YOU DON'T HAVE ANY GAME EVENT YET}");
			} else {
			    List<GameEvents> gameList = userDAO.getGameEvents(loggedUser);
			    for (i = 0; i < gameList.size(); i++) {
				System.out.println("#" + (i + 1) + " { " + gameList.get(i).getEventName() + " } "
					+ " - " + gameList.get(i).getEventDate() + " - "
					+ gameList.get(i).getEventLocal());
				System.out.println(gameList.get(i).getEventDescription());
				System.out.println(" { MAIN GAME: " + gameList.get(i).getGameName() + " } ");
				System.out.println();
			    }
			    do {
				System.out.println();
				System.out.println("{1} EDIT EVENT ~ {2} REMOVE EVENT ~ {3} MAIN MENU");
				tempChoose = sc.nextInt();
				System.out.println();
				switch (tempChoose) {
				case 1:
				    sc.nextLine();
				    System.out.println(
					    "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
				    System.out.print("SELECT A GAME EVENT NUMBER THAT YOU WANT TO EDIT:~$ ");
				    Integer gameNum = sc.nextInt();
				    gameNum--;
				    System.out.println();
				    sc.nextLine();
				    System.out.print("WRITE A NEW GAME EVENT NAME:~$ ");
				    gameList.get(gameNum).setEventName(sc.nextLine());
				    gameEventsDAO.update(gameList.get(gameNum));
				    System.out.print("WRITE A NEW GAME EVENT DATE:~$ ");
				    gameList.get(gameNum).setEventDate(sc.nextLine());
				    gameEventsDAO.update(gameList.get(gameNum));
				    System.out.print("WRITE A NEW GAME EVENT LOCAL:~$ ");
				    gameList.get(gameNum).setEventLocal(sc.nextLine());
				    gameEventsDAO.update(gameList.get(gameNum));
				    System.out.print("WRITE A NEW GAME EVENT DESCRIPTION:~$ ");
				    gameList.get(gameNum).setEventDescription(sc.nextLine());
				    gameEventsDAO.update(gameList.get(gameNum));
				    System.out.print("WRITE A NEW MAIN GAME NAME:~$ ");
				    gameList.get(gameNum).setEventDescription(sc.nextLine());
				    gameEventsDAO.update(gameList.get(gameNum));
				    break;
				case 2:
				    sc.nextLine();
				    System.out.print("SELECT A GAME EVENT NUMBER THAT YOU WANT TO REMOVE:~$ ");
				    Integer gameNumRemove = sc.nextInt();
				    sc.nextLine();
				    gameNumRemove--;
				    gameEventsDAO.remove(gameList.get(gameNumRemove));
				    Alert_FX.info("{ EVENT DELETED }");
				    break;
				case 3:
				    break;
				}
			    } while (tempChoose != 3);
			}
			break;
		    case 4:
			System.out.println(
				"~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			System.out.println("{EVENT TYPE}");
			System.out.println();
			System.out.println("{1} DEV EVENT");
			System.out.println("{2} GAME EVENT");
			System.out.println("{3} MAIN MENU");
			System.out.println(":~$ ");
			tempChoose = sc.nextInt();
			sc.nextLine();
			switch (tempChoose) {
			case 1:
			    System.out.println("{DEV EVENTS}");
			    List<DevEvents> allDevEvents = devEventsDAO.all();
			    for (i = 0; i < allDevEvents.size(); i++) {
				System.out.println("# " + (i + 1) + " { " + allDevEvents.get(i).getEventName() + " } ");
				System.out.println(allDevEvents.get(i).getEventDate() + " - "
					+ allDevEvents.get(i).getEventLocal());
				System.out.println("~: " + allDevEvents.get(i).getEventDescription());
			    }
			    break;
			case 2:
			    System.out.println("{GAME EVENTS}");
			    List<GameEvents> allGameEvents = gameEventsDAO.all();
			    for (i = 0; i < allGameEvents.size(); i++) {
				System.out
					.println("# " + (i + 1) + " { " + allGameEvents.get(i).getEventName() + " } ");
				System.out.println(allGameEvents.get(i).getEventDate() + " - "
					+ allGameEvents.get(i).getEventLocal());
				System.out.println(" MAIN GAME{ " + allGameEvents.get(i).getGameName() + " } ");
				System.out.println("~: " + allGameEvents.get(i).getEventDescription());
			    }
			    break;
			case 3:
			    break;
			}
		    case 5:
			break;
		    }
		}
		if (choose == 6) {
		    loggedUser = null;
		    break;
		}
	    } while (true);

	}

    }
}
