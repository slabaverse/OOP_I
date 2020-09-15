package program;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import dataBase.CommentDAO;
import dataBase.DevEventsDAO;
import dataBase.FollowDAO;
import dataBase.GameEventsDAO;
import dataBase.MarketplaceDAO;
import dataBase.PostDAO;
import dataBase.UserDAO;
import dataBase.UtilBD;
import entities.Comment;
import entities.DevEvents;
import entities.Follow;
import entities.GameEvents;
import entities.Marketplace;
import entities.Post;
import entities.User;

public class Main {

    public static void main(String[] args) {

	UtilBD.initBD();

	UtilBD.closeConection();

	List<User> users = new ArrayList<>();

	CommentDAO commentDAO = new CommentDAO();
	DevEventsDAO devEventsDAO = new DevEventsDAO();
	FollowDAO followDAO = new FollowDAO();
	GameEventsDAO gameEventsDAO = new GameEventsDAO();
	MarketplaceDAO marketplaceDAO = new MarketplaceDAO();
	PostDAO postDAO = new PostDAO();
	UserDAO userDAO = new UserDAO();

	int i = 0, choose = 0, tempChoose = 0;
	User loggedUser = null;
	char wannaComment = 'y';

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
		System.out.println(userDAO.getPost(loggedUser));
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
		    User updateUser = new User(username, password, name, birthdate, relationship);
		    userDAO.update(updateUser);
		    break;
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
			System.out.print("POST CONTENT:~$ ");
			String content = sc.nextLine();
			Post newPost = new Post(loggedUser, content);
			postDAO.add(newPost);
			break;
		    case 2:
			if (userDAO.getPost(loggedUser).isEmpty()) {
			    System.out.println("{YOU DON'T HAVE ANY POST YET}");
			} else {
			    List<Post> postList = userDAO.getPost(loggedUser);
			    for (i = 0; i < postList.size(); i++) {
				System.out.println("Post number# " + (i + 1));
				System.out.println("User: " + postList.get(i).getUser().getUsername());
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
				    postNumRemove--;
				    postDAO.remove(postList.get(postNumRemove));
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
			    System.out.println("Post Number: " + (i + 1));
			    System.out.println("Username: " + allPost.get(i).getUser().getUsername());
			    System.out.println("Content: " + allPost.get(i).getContent());
			    for (int j = 0; j < allPost.get(i).getComments().size(); j++) {
				System.out.println(
					"Username: " + allPost.get(i).getComments().get(j).getUser().getUsername());
				System.out.println("Content: " + allPost.get(i).getComments().get(j).getText());
			    }
			}
			do {
			    System.out.println(
				    "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			    System.out.println("{1} TO MAKE A COMMENT");
			    System.out.println("{2} TO EDIT A COMMENT");
			    System.out.println("{3} TO DELETE A COMMENT");
			    System.out.println("{4} MAIN MENU");
			    System.out.print(":~$ ");
			    tempChoose = sc.nextInt();
			    switch (tempChoose) {
			    case 1:
				System.out.println(
					"~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
				while (wannaComment == 'Y' || wannaComment == 'y') {
				    sc.nextLine();
				    System.out.print("SELECT POST NUMBER THAT YOU WANT TO COMMENT:~$ ");
				    Integer idPostComment = sc.nextInt();
				    idPostComment--;
				    System.out.print("COMMENT TEXT:~$ ");
				    String textComment = sc.nextLine();

				    Post commentPost = allPost.get(idPostComment);
				    List<Comment> commentList = new ArrayList<Comment>();
				    commentList.add(new Comment(commentPost.getUser().getUsername(), textComment));
				    commentPost.setComment(commentList);

				    System.out.println(
					    "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
				    System.out.print("ANOTHER COMMENT (Y/n):~$ ");
				    System.out.println();
				    wannaComment = sc.next().charAt(0);
				}
				break;
			    case 2:
				System.out.println(
					"~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
				System.out.print("SELECT THE POST NUMBER:~$ ");
				Integer idPostEdit = sc.nextInt();
				idPostEdit--;
				allPost.get(idPostEdit); // Pegando o post
				System.out.print("SELECT THE NUMBER OF THE COMMENT:~$ ");
				Integer idCommentEdit = sc.nextInt();
				idCommentEdit--;
				Comment comment = allPost.get(idPostEdit).getComments().get(idCommentEdit); // Pegando o
													    // comentário
				sc.nextLine();
				System.out.print("NEW COMMENT TEXT:~$ ");
				comment.setText(sc.nextLine()); // Atualizando o valor do atributo TEXT

				commentDAO.update(comment); // Atualizando o campo TEXT no BD

				break;
			    case 3:
				sc.nextLine();
				System.out.println(
					"~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
				System.out.print("SELECT THE ID OF THE POST:~$ ");
				Integer idPostDelete = sc.nextInt();
				idPostDelete--;
				allPost.get(idPostDelete); // Pegando o post
				System.out.print("SELECT THE ID OF THE COMMENT:~$ ");
				Integer idCommentDelete = sc.nextInt();
				idCommentDelete--;
				Comment commentDelete = allPost.get(idCommentDelete).getComments().get(idCommentDelete); // Pegando
				// o
				// comentário

				commentDAO.remove(commentDelete); // Deletando comentário

				break;
			    }
			} while (tempChoose != 4);
			wannaComment = 'y';
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
			    System.out.println("{YOU DON'T HAVE PRODUCTS YET}");
			} else {
			    List<Marketplace> mktList = userDAO.getMarketplace(loggedUser);
			    for (i = 0; i < mktList.size(); i++) {
				System.out.println("Product number# " + (i + 1));
				System.out.println("User: " + mktList.get(i).getUser().getUsername());
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
				    Integer mktNum = sc.nextInt();
				    mktNum--;
				    System.out.println();
				    sc.nextLine();
				    System.out.print("EDIT PRODUCT NAME:~$ ");
				    mktList.get(mktNum).setProduct(sc.nextLine());
				    System.out.print("EDIT PRODUCT PRICE:~$ ");
				    mktList.get(mktNum).setPrice(sc.nextDouble());
				    System.out.print("EDIT PRODUCT DESCRIPTION:~$ ");
				    mktList.get(mktNum).setDescription(sc.nextLine());
				    marketplaceDAO.update(mktList.get(mktNum));
				    break;
				case 2:
				    sc.nextLine();
				    System.out.print("SELECT A PRODUCT NUMBER THAT YOU WANT TO REMOVE:~$ ");
				    Integer mktNumRemove = sc.nextInt();
				    mktNumRemove--;
				    marketplaceDAO.remove(mktList.get(mktNumRemove));
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
			    System.out.println("Product Number: " + (i + 1));
			    System.out.println("Username: " + allMkt.get(i).getUser().getUsername());
			    System.out.println("Product: " + allMkt.get(i).getProduct());
			    System.out.println("Price: " + allMkt.get(i).getPrice());
			    System.out.println("Description: " + allMkt.get(i).getDescription());
			    for (int j = 0; j < allMkt.get(i).getComments().size(); j++) {
				System.out.println(
					"Username: " + allMkt.get(i).getComments().get(j).getUser().getUsername());
				System.out.println("Content: " + allMkt.get(i).getComments().get(j).getText());
			    }
			}
			do {
			    System.out.println(
				    "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			    System.out.println("{1} TO MAKE A COMMENT");
			    System.out.println("{2} TO EDIT A COMMENT");
			    System.out.println("{3} TO DELETE A COMMENT");
			    System.out.println("{4} MAIN MENU");
			    System.out.print(":~$ ");
			    tempChoose = sc.nextInt();
			    switch (tempChoose) {
			    case 1:
				System.out.println(
					"~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
				while (wannaComment == 'Y' || wannaComment == 'y') {
				    sc.nextLine();
				    System.out.print("SELECT A PRODUCT NUMBER THAT YOU WANT TO COMMENT:~$ ");
				    Integer idProductComment = sc.nextInt();
				    idProductComment--;
				    System.out.print("COMMENT TEXT:~$ ");
				    String textComment = sc.nextLine();
				    Marketplace commentMkt = allMkt.get(idProductComment);
				    List<Comment> commentList = new ArrayList<Comment>();
				    commentList.add(new Comment(commentMkt.getUser().getUsername(), textComment));
				    commentMkt.setComment(commentList);
				    System.out.println(
					    "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
				    System.out.print("ANOTHER COMMENT (Y/n):~$ ");
				    System.out.println();
				    wannaComment = sc.next().charAt(0);
				}
				break;
			    case 2:
				System.out.println(
					"~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
				System.out.print("SELECT A PRODUCT NUMBER:~$ ");
				Integer idProductEdit = sc.nextInt();
				idProductEdit--;
				allMkt.get(idProductEdit);
				System.out.print("SELECT THE NUMBER OF THE COMMENT:~$ ");
				Integer idCommentEdit = sc.nextInt();
				idCommentEdit--;
				Comment comment = allMkt.get(idProductEdit).getComments().get(idCommentEdit);
				sc.nextLine();
				System.out.print("NEW COMMENT TEXT:~$ ");
				comment.setText(sc.nextLine());
				commentDAO.update(comment);
				break;
			    case 3:
				sc.nextLine();
				System.out.println(
					"~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
				System.out.print("SELECT PRODUCT ID:~$ ");
				Integer idPostDelete = sc.nextInt();
				idPostDelete--;
				allMkt.get(idPostDelete);
				System.out.print("SELECT THE ID OF THE COMMENT:~$ ");
				Integer idCommentDelete = sc.nextInt();
				idCommentDelete--;
				Comment commentDelete = allMkt.get(idCommentDelete).getComments().get(idCommentDelete);
				commentDAO.remove(commentDelete);
				break;
			    }
			} while (tempChoose != 4);
			wannaComment = 'y';
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
			System.out.println("User number: " + (i + 1));
			System.out.println("Username: " + followedUsers.get(i).getUsername());
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
				System.out.println("User number: " + (i + 1));
				System.out.println("Name: " + allUsers.get(i).getName());
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
			    System.out.println("{YOU DON'T HAVE DEV EVENTS YET}");
			} else {
			    List<DevEvents> devList = userDAO.getDevEvents(loggedUser);
			    for (i = 0; i < devList.size(); i++) {
				System.out.println("Event number# " + (i + 1));
				System.out.println("User: " + devList.get(i).getUser().getUsername());
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
				    System.out.print("SELECT AN EVENT NUMBER THAT YOU WANT TO EDIT:~$ ");
				    Integer devNum = sc.nextInt();
				    devNum--;
				    System.out.println();
				    sc.nextLine();
				    System.out.print("EDIT EVENT NAME:~$ ");
				    devList.get(devNum).setEventName(sc.nextLine());
				    System.out.print("EDIT EVENT DATE:~$ ");
				    devList.get(devNum).setEventDate(sc.nextLine());
				    System.out.print("EDIT EVENT LOCAL:~$ ");
				    devList.get(devNum).setEventLocal(sc.nextLine());
				    System.out.print("EDIT EVENT DESCRIPTION:~$ ");
				    devList.get(devNum).setEventDescription(sc.nextLine());
				    devEventsDAO.update(devList.get(devNum));
				    break;
				case 2:
				    sc.nextLine();
				    System.out.print("SELECT AN EVENT NUMBER THAT YOU WANT TO REMOVE:~$ ");
				    Integer devNumRemove = sc.nextInt();
				    devNumRemove--;
				    devEventsDAO.remove(devList.get(devNumRemove));
				    break;
				case 3:
				    break;
				}
			    } while (tempChoose != 3);
			}
			break;
		    case 3:
			if (userDAO.getGameEvents(loggedUser).isEmpty()) {
			    System.out.println("{YOU DON'T HAVE GAME EVENTS YET}");
			} else {
			    List<GameEvents> gameList = userDAO.getGameEvents(loggedUser);
			    for (i = 0; i < gameList.size(); i++) {
				System.out.println("Event number# " + (i + 1));
				System.out.println("User: " + gameList.get(i).getUser().getUsername());
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
				    System.out.print("SELECT AN EVENT NUMBER THAT YOU WANT TO EDIT:~$ ");
				    Integer gameNum = sc.nextInt();
				    gameNum--;
				    System.out.println();
				    sc.nextLine();
				    System.out.print("EDIT EVENT NAME:~$ ");
				    gameList.get(gameNum).setEventName(sc.nextLine());
				    System.out.print("EDIT EVENT DATE:~$ ");
				    gameList.get(gameNum).setEventDate(sc.nextLine());
				    System.out.print("EDIT EVENT LOCAL:~$ ");
				    gameList.get(gameNum).setEventLocal(sc.nextLine());
				    System.out.print("EDIT EVENT DESCRIPTION:~$ ");
				    gameList.get(gameNum).setEventDescription(sc.nextLine());
				    System.out.print("EDIT MAIN GAME NAME:~$ ");
				    gameList.get(gameNum).setEventDescription(sc.nextLine());
				    gameEventsDAO.update(gameList.get(gameNum));
				    break;
				case 2:
				    sc.nextLine();
				    System.out.print("SELECT AN EVENT NUMBER THAT YOU WANT TO REMOVE:~$ ");
				    Integer gameNumRemove = sc.nextInt();
				    gameNumRemove--;
				    gameEventsDAO.remove(gameList.get(gameNumRemove));
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
				System.out.println("Event Number: " + (i + 1));
				System.out.println("Creator: " + allDevEvents.get(i).getUser().getUsername());
				System.out.println("Name: " + allDevEvents.get(i).getEventName());
				System.out.println("Date: " + allDevEvents.get(i).getEventDate());
				System.out.println("Local: " + allDevEvents.get(i).getEventLocal());
				System.out.println("Description: " + allDevEvents.get(i).getEventDescription());
				for (int j = 0; j < allDevEvents.get(i).getComments().size(); j++) {
				    System.out.println("Username: "
					    + allDevEvents.get(i).getComments().get(j).getUser().getUsername());
				    System.out
					    .println("Content: " + allDevEvents.get(i).getComments().get(j).getText());
				}
			    }
			    do {
				System.out.println(
					"~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
				System.out.println("{1} TO MAKE A COMMENT");
				System.out.println("{2} TO EDIT A COMMENT");
				System.out.println("{3} TO DELETE A COMMENT");
				System.out.println("{4} MAIN MENU");
				System.out.print(":~$ ");
				tempChoose = sc.nextInt();
				switch (tempChoose) {
				case 1:
				    System.out.println(
					    "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
				    while (wannaComment == 'Y' || wannaComment == 'y') {
					sc.nextLine();
					System.out.print("SELECT AN EVENT NUMBER THAT YOU WANT TO COMMENT:~$ ");
					Integer idDevEventComment = sc.nextInt();
					idDevEventComment--;
					System.out.print("COMMENT TEXT:~$ ");
					String textComment = sc.nextLine();
					DevEvents commentDev = allDevEvents.get(idDevEventComment);
					List<Comment> commentList = new ArrayList<Comment>();
					commentList.add(new Comment(commentDev.getUser().getUsername(), textComment));
					commentDev.setComment(commentList);
					System.out.println(
						"~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
					System.out.print("ANOTHER COMMENT (Y/n):~$ ");
					System.out.println();
					wannaComment = sc.next().charAt(0);
				    }
				    break;
				case 2:
				    System.out.println(
					    "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
				    System.out.print("SELECT AN EVENT NUMBER:~$ ");
				    Integer idDevEventEdit = sc.nextInt();
				    idDevEventEdit--;
				    allDevEvents.get(idDevEventEdit);
				    System.out.print("SELECT THE NUMBER OF THE COMMENT:~$ ");
				    Integer idCommentEdit = sc.nextInt();
				    idCommentEdit--;
				    Comment comment = allDevEvents.get(idDevEventEdit).getComments().get(idCommentEdit);
				    sc.nextLine();
				    System.out.print("NEW COMMENT TEXT:~$ ");
				    comment.setText(sc.nextLine());
				    commentDAO.update(comment);
				    break;
				case 3:
				    sc.nextLine();
				    System.out.println(
					    "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
				    System.out.print("SELECT DEV EVENT ID:~$ ");
				    Integer idDevEventDelete = sc.nextInt();
				    idDevEventDelete--;
				    allDevEvents.get(idDevEventDelete);
				    System.out.print("SELECT THE ID OF THE COMMENT:~$ ");
				    Integer idCommentDelete = sc.nextInt();
				    idCommentDelete--;
				    Comment commentDelete = allDevEvents.get(idCommentDelete).getComments()
					    .get(idCommentDelete);
				    commentDAO.remove(commentDelete);
				    break;
				}
			    } while (tempChoose != 4);
			    wannaComment = 'y';
			    break;
			case 2:
			    System.out.println("{GAME EVENTS}");
			    List<GameEvents> allGameEvents = gameEventsDAO.all();
			    for (i = 0; i < allGameEvents.size(); i++) {
				System.out.println("Event Number: " + (i + 1));
				System.out.println("Creator: " + allGameEvents.get(i).getUser().getUsername());
				System.out.println("Name: " + allGameEvents.get(i).getEventName());
				System.out.println("Date: " + allGameEvents.get(i).getEventDate());
				System.out.println("Local: " + allGameEvents.get(i).getEventLocal());
				System.out.println("Description: " + allGameEvents.get(i).getEventDescription());
				System.out.println("Main Game: " + allGameEvents.get(i).getGameName());
				for (int j = 0; j < allGameEvents.get(i).getComments().size(); j++) {
				    System.out.println("Username: "
					    + allGameEvents.get(i).getComments().get(j).getUser().getUsername());
				    System.out
					    .println("Content: " + allGameEvents.get(i).getComments().get(j).getText());
				}
			    }
			    do {
				System.out.println(
					"~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
				System.out.println("{1} TO MAKE A COMMENT");
				System.out.println("{2} TO EDIT A COMMENT");
				System.out.println("{3} TO DELETE A COMMENT");
				System.out.println("{4} MAIN MENU");
				System.out.print(":~$ ");
				tempChoose = sc.nextInt();
				switch (tempChoose) {
				case 1:
				    System.out.println(
					    "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
				    while (wannaComment == 'Y' || wannaComment == 'y') {
					sc.nextLine();
					System.out.print("SELECT AN EVENT NUMBER THAT YOU WANT TO COMMENT:~$ ");
					Integer idGameEventComment = sc.nextInt();
					idGameEventComment--;
					System.out.print("COMMENT TEXT:~$ ");
					String textComment = sc.nextLine();
					GameEvents commentGame = allGameEvents.get(idGameEventComment);
					List<Comment> commentList = new ArrayList<Comment>();
					commentList.add(new Comment(commentGame.getUser().getUsername(), textComment));
					commentGame.setComment(commentList);
					System.out.println(
						"~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
					System.out.print("ANOTHER COMMENT (Y/n):~$ ");
					System.out.println();
					wannaComment = sc.next().charAt(0);
				    }
				    break;
				case 2:
				    System.out.println(
					    "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
				    System.out.print("SELECT AN EVENT NUMBER:~$ ");
				    Integer idGameEventEdit = sc.nextInt();
				    idGameEventEdit--;
				    allGameEvents.get(idGameEventEdit);
				    System.out.print("SELECT THE NUMBER OF THE COMMENT:~$ ");
				    Integer idCommentEdit = sc.nextInt();
				    idCommentEdit--;
				    Comment comment = allGameEvents.get(idGameEventEdit).getComments()
					    .get(idCommentEdit);
				    sc.nextLine();
				    System.out.print("NEW COMMENT TEXT:~$ ");
				    comment.setText(sc.nextLine());
				    commentDAO.update(comment);
				    break;
				case 3:
				    sc.nextLine();
				    System.out.println(
					    "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
				    System.out.print("SELECT GAME EVENT ID:~$ ");
				    Integer idGameEventDelete = sc.nextInt();
				    idGameEventDelete--;
				    allGameEvents.get(idGameEventDelete);
				    System.out.print("SELECT THE ID OF THE COMMENT:~$ ");
				    Integer idCommentDelete = sc.nextInt();
				    idCommentDelete--;
				    Comment commentDelete = allGameEvents.get(idGameEventDelete).getComments()
					    .get(idCommentDelete);
				    commentDAO.remove(commentDelete);
				    break;
				}
			    } while (tempChoose != 4);
			    wannaComment = 'y';
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
		    wannaComment = 'y';
		    break;
		}
	    } while (true);
	    sc.close();
	}
    }
}
