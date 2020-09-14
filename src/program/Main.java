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
import entities.Post;
import entities.User;

public class Main {

    public static void main(String[] args) {

	UtilBD.initBD();

	//UtilBD.closeConection();

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
			    for(i=0; i< postList.size(); i++) {
				System.out.println("Post number# " + (i+1));
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
				    postNum --;
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
				    postNumRemove --;
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
			for (i=0; i<allPost.size(); i++) {
			    System.out.println("Post Number: " + (i+1));
			    System.out.println("Username: " + allPost.get(i).getUser().getUsername());
			    System.out.println("Content: " + allPost.get(i).getContent());
			    for(int j=0; j<allPost.get(i).getComments().size(); j++) {
				System.out.println("Username: " + allPost.get(i).getComments().get(j).getUser().getUsername());
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
				    idPostComment --;
				    System.out.print("COMMENT TEXT:~$ ");
				    String textComment = sc.nextLine();
				    Post commentPost = allPost.get(idPostComment);
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
				System.out.print("SELECT THE ID OF THE POST:~$ ");
				Integer idPostEdit = sc.nextInt();
				System.out.print("SELECT THE ID OF THE COMMENT:~$ ");
				Integer idCommentEdit = sc.nextInt();
				sc.nextLine();
				System.out.print("NEW COMMENT TEXT:~$ ");
				String textCommentEdit = sc.nextLine();
				for (i = 0; i < users.size(); i++) {
				    for (int j = 0; j < users.get(i).getPosts().size(); j++) {
					if (users.get(i).getPosts().get(j).getIdPost() == idPostEdit) {
					    users.get(i).getPosts().get(j).editComment(idCommentEdit, textCommentEdit);
					}
				    }
				}
				break;
			    case 3:
				sc.nextLine();
				System.out.println(
					"~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
				System.out.print("SELECT THE ID OF THE POST:~$ ");
				Integer idPostDelete = sc.nextInt();
				System.out.print("SELECT THE ID OF THE COMMENT:~$ ");
				Integer idCommentDelete = sc.nextInt();
				for (i = 0; i < users.size(); i++) {
				    for (int j = 0; j < users.get(i).getPosts().size(); j++) {
					if (users.get(i).getPosts().get(j).getIdPost() == idPostDelete) {
					    users.get(i).getPosts().get(j).removeComment(idCommentDelete);
					}
				    }
				}
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
			System.out.print("PRODUCT'S ID:~$ ");
			Integer productIdAdd = sc.nextInt();
			sc.nextLine();
			System.out.print("PRODUCT:~$ ");
			String product = sc.nextLine();
			System.out.print("PRICE ($):~$ ");
			Double price = sc.nextDouble();
			sc.nextLine();
			System.out.print("PRODUCT DESCRIPTION:~$ ");
			String description = sc.nextLine();
			users.get(loggedUser).addProduct(users.get(loggedUser), productIdAdd, product, price,
				description);
			break;
		    case 2:
			if (users.get(loggedUser).getMarketplace().isEmpty()) {
			    System.out.println("{YOU DON'T HAVE ANY PRODUCT YET}");
			} else {
			    users.get(loggedUser).showMarket();
			    do {
				System.out.println();
				System.out.println("{1} EDIT SALE ~ {2} REMOVE SALE ~ {3} MAIN MENU");
				tempChoose = sc.nextInt();
				System.out.println();
				switch (tempChoose) {
				case 1:
				    sc.nextLine();
				    users.get(loggedUser).showYourMarket();
				    System.out.println(
					    "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
				    System.out.print("SELECT THE ID OF A PRODUCT THAT YOU WANT TO EDIT:~$ ");
				    Integer productId = sc.nextInt();
				    sc.nextLine();
				    System.out.println();
				    System.out.println("SELECT PRODUCT'S INFO THAT YOU WANT TO EDIT");
				    System.out.println("{1} NAME ~ {2} PRICE ~ {3} DESCRIPTION ~ {4} CANCEL");
				    System.out.println(":~$ ");
				    tempChoose = sc.nextInt();
				    sc.nextLine();
				    switch (tempChoose) {
				    case 1:
					System.out.print("NEW PRODUCT NAME:~$ ");
					String editedProduct = sc.nextLine();
					users.get(loggedUser).editProductName(productId, editedProduct);
					tempChoose = 0;
					break;
				    case 2:
					System.out.print("NEW PRODUCT PRICE:~$ ");
					Double editedPrice = sc.nextDouble();
					users.get(loggedUser).editProductPrice(productId, editedPrice);
					tempChoose = 0;
					break;
				    case 3:
					System.out.print("NEW PRODUCT DESCRIPTION:~$ ");
					String editedDescription = sc.nextLine();
					users.get(loggedUser).editProductDescription(productId, editedDescription);
					tempChoose = 0;
					break;
				    case 4:
					tempChoose = 0;
					break;
				    }
				    break;
				case 2:
				    sc.nextLine();
				    users.get(loggedUser).showMarket();
				    System.out.println(
					    "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
				    System.out.print("SELECT THE ID OF A PRODUCT THAT YOU WANT TO REMOVE:~$ ");
				    Integer productRemoveId = sc.nextInt();
				    sc.nextLine();
				    users.get(loggedUser).removeProduct(productRemoveId);
				    break;
				case 3:
				    break;
				}
			    } while (tempChoose != 3);
			}
			break;
		    case 3:
			for (i = 0; i < users.size(); i++) {
			    if (users.get(i).getMarketplace().isEmpty()) {
			    } else {
				users.get(i).showMarket();
				System.out.println();
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
				    System.out.print("SELECT THE ID OF THE PRODUCT THAT YOU WANT TO COMMENT:~$ ");
				    Integer idProductComment = sc.nextInt();
				    System.out.print("COMMENT ID:~$ ");
				    Integer idComment = sc.nextInt();
				    sc.nextLine();
				    System.out.print("COMMENT TEXT:~$ ");
				    String textComment = sc.nextLine();
				    for (i = 0; i < users.size(); i++) {
					for (int j = 0; j < users.get(i).getMarketplace().size(); j++) {
					    if (users.get(i).getMarketplace().get(j).getId() == idProductComment) {
						users.get(i).getMarketplace().get(j).addComment(idComment,
							users.get(loggedUser), textComment);
					    }
					}
				    }
				    System.out.println();
				    System.out.print("ANOTHER COMMENT (Y/n):~$ ");
				    System.out.println();
				    wannaComment = sc.next().charAt(0);
				}
				break;
			    case 2:
				System.out.println(
					"~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
				System.out.print("SELECT THE ID OF THE PRODUCT:~$ ");
				Integer idProductEdit = sc.nextInt();
				System.out.print("SELECT THE ID OF THE COMMENT:~$ ");
				Integer idCommentEdit = sc.nextInt();
				sc.nextLine();
				System.out.print("NEW COMMENT TEXT:~$ ");
				String textCommentEdit = sc.nextLine();
				for (i = 0; i < users.size(); i++) {
				    for (int j = 0; j < users.get(i).getMarketplace().size(); j++) {
					if (users.get(i).getMarketplace().get(j).getId() == idProductEdit) {
					    users.get(i).getMarketplace().get(j).editComment(idCommentEdit,
						    textCommentEdit);
					}
				    }
				}
				break;
			    case 3:
				System.out.println(
					"~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
				sc.nextLine();
				System.out.print("SELECT THE ID OF THE PRODUCT:~$ ");
				Integer idProductDelete = sc.nextInt();
				System.out.print("SELECT THE ID OF THE COMMENT:~$ ");
				Integer idCommentDelete = sc.nextInt();
				for (i = 0; i < users.size(); i++) {
				    for (int j = 0; j < users.get(i).getMarketplace().size(); j++) {
					if (users.get(i).getMarketplace().get(j).getId() == idProductDelete) {
					    users.get(i).getMarketplace().get(j).removeComment(idCommentDelete);
					}
				    }
				}
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
		    users.get(loggedUser).showFollowers();
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
			    for (i = 0; i < users.size(); i++) {
				System.out.println("# " + users.get(i).getName());
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
				System.out.print("FRIEND NAME:~$ ");
				String frienNameAdd = sc.nextLine();
				for (i = 0; i < users.size(); i++) {
				    if (users.get(i).getName().contentEquals(frienNameAdd)) {
					users.get(loggedUser).follow(users.get(i).getName(),
						users.get(i).getBirthdate(), users.get(i).getRelationship());
				    }
				}
				break;
			    case 2:
				break;
			    }
			} while (tempChoose != 2);
			break;
		    case 2:
			System.out.println();
			users.get(loggedUser).showFollowers();
			System.out.println();
			sc.nextLine();
			System.out.print("FRIEND NAME:~$ ");
			String friendNameRemove = sc.nextLine();
			for (i = 0; i < users.size(); i++) {
			   if (users.get(loggedUser).getFollowers().get(i).getName().contentEquals(friendNameRemove)) {
				users.get(loggedUser).unfollow(friendNameRemove);
				break;
			    }
			}
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
			    System.out.print("EVENT'S ID:~$ ");
			    Integer devEventId = sc.nextInt();
			    sc.nextLine();
			    System.out.print("EVENT'S NAME:~$ ");
			    String devEventName = sc.nextLine();
			    System.out.print("EVENT'S DATE (dd/MM/yyyy):~$ ");
			    String devEventDate = sc.nextLine();
			    System.out.print("EVENT'S LOCAL:~$ ");
			    String devEventLocal = sc.nextLine();
			    System.out.print("EVENT'S DESCRIPTION:~$ ");
			    String devEventDescription = sc.nextLine();
			    users.get(loggedUser).addDevEvent(users.get(loggedUser), devEventId, devEventName,
				    devEventDate, devEventLocal, devEventDescription);
			    tempChoose = 0;
			    break;
			case 2:
			    System.out.print("EVENT'S ID:~$ ");
			    Integer gameEventId = sc.nextInt();
			    sc.nextLine();
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
			    users.get(loggedUser).addGameEvent(users.get(loggedUser), gameEventId, gameEventName,
				    gameEventDate, gameEventLocal, gameEventDescription, gameName);
			    tempChoose = 0;
			    break;
			case 3:
			    tempChoose = 0;
			    break;
			}
			break;
		    case 2:
			if (users.get(loggedUser).getDev().isEmpty()) {
			    System.out.println("{YOU DON'T HAVE ANY DEV EVENT YET}");
			} else {
			    users.get(loggedUser).showDevEvents();
			    do {
				System.out.println();
				System.out.println("{1} EDIT EVENT ~ {2} REMOVE EVENT ~ {3} MAIN MENU");
				tempChoose = sc.nextInt();
				System.out.println();
				switch (tempChoose) {
				case 1:
				    sc.nextLine();
				    users.get(loggedUser).showYourDevEvents();
				    System.out.println(
					    "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
				    System.out.print("SELECT THE ID OF A EVENT THAT YOU WANT TO EDIT:~$ ");
				    Integer eventId = sc.nextInt();
				    sc.nextLine();
				    System.out.println();
				    System.out.println("SELECT EVENT'S INFO THAT YOU WANT TO EDIT");
				    System.out
					    .println("{1} NAME ~ {2} DATE ~ {3} LOCAL ~ {4} DESCRIPTION ~ {5} CANCEL");
				    System.out.println(":~$ ");
				    tempChoose = sc.nextInt();
				    sc.nextLine();
				    switch (tempChoose) {
				    case 1:
					System.out.print("NEW EVENT'S NAME:~$ ");
					String editedDevEventName = sc.nextLine();
					users.get(loggedUser).editDevEventName(eventId, editedDevEventName);
					tempChoose = 0;
					break;
				    case 2:
					System.out.print("NEW EVENT'S DATE:~$ ");
					String editedDevEventDate = sc.nextLine();
					users.get(loggedUser).editDevEventDate(eventId, editedDevEventDate);
					tempChoose = 0;
					break;
				    case 3:
					System.out.print("NEW EVENT'S LOCAL:~$ ");
					String editedDevEventLocal = sc.nextLine();
					users.get(loggedUser).editDevEventLocal(eventId, editedDevEventLocal);
					tempChoose = 0;
					break;
				    case 4:
					System.out.print("NEW EVENT'S DESCRIPTION:~$ ");
					String editedDevEventDesc = sc.nextLine();
					users.get(loggedUser).editDevEventDescription(eventId, editedDevEventDesc);
					tempChoose = 0;
					break;
				    case 5:
					tempChoose = 0;
					break;
				    }
				    break;
				case 2:
				    sc.nextLine();
				    users.get(loggedUser).showDevEvents();
				    System.out.println(
					    "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
				    System.out.print("SELECT THE ID OF THE EVENT THAT YOU WANT TO REMOVE~$ ");
				    Integer eventDevRemoveId = sc.nextInt();
				    sc.nextLine();
				    users.get(loggedUser).removeDevEvent(eventDevRemoveId);
				    break;
				case 3:
				    break;
				}
			    } while (tempChoose != 3);
			}
			break;
		    case 3:
			if (users.get(loggedUser).getGame().isEmpty()) {
			    System.out.println("{YOU DON'T HAVE ANY GAME EVENT YET}");
			} else {
			    users.get(loggedUser).showGameEvents();
			    do {
				System.out.println();
				System.out.println("{1} EDIT EVENT ~ {2} REMOVE EVENT ~ {3} MAIN MENU");
				tempChoose = sc.nextInt();
				System.out.println();
				switch (tempChoose) {
				case 1:
				    sc.nextLine();
				    users.get(loggedUser).showYourGameEvents();
				    System.out.println(
					    "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
				    System.out.print("SELECT THE ID OF A EVENT THAT YOU WANT TO EDIT:~$ ");
				    Integer eventId = sc.nextInt();
				    sc.nextLine();
				    System.out.println();
				    System.out.println("SELECT EVENT'S INFO THAT YOU WANT TO EDIT");
				    System.out.println(
					    "{1} NAME ~ {2} DATE ~ {3} LOCAL ~ {4} DESCRIPTION ~ {5} GAME NAME ~ {6} CANCEL");
				    System.out.println(":~$ ");
				    tempChoose = sc.nextInt();
				    sc.nextLine();
				    switch (tempChoose) {
				    case 1:
					System.out.print("NEW EVENT NAME:~$ ");
					String editedGameEventName = sc.nextLine();
					users.get(loggedUser).editGameEventName(eventId, editedGameEventName);
					tempChoose = 0;
					break;
				    case 2:
					System.out.print("NEW EVENT'S DATE:~$ ");
					String editedGameEventDate = sc.nextLine();
					users.get(loggedUser).editGameEventDate(eventId, editedGameEventDate);
					tempChoose = 0;
					break;
				    case 3:
					System.out.print("NEW EVENT'S LOCAL:~$ ");
					String editedGameEventLocal = sc.nextLine();
					users.get(loggedUser).editGameEventLocal(eventId, editedGameEventLocal);
					tempChoose = 0;
					break;
				    case 4:
					System.out.print("NEW EVENT'S DESCRIPTION:~$ ");
					String editedGameEventDesc = sc.nextLine();
					users.get(loggedUser).editGameEventDescription(eventId, editedGameEventDesc);
					tempChoose = 0;
					break;
				    case 5:
					System.out.print("NEW EVENT'S MAIN GAME:~$ ");
					String editedGameEventGame = sc.nextLine();
					users.get(loggedUser).editGameEventGameName(eventId, editedGameEventGame);
					tempChoose = 0;
					break;
				    case 6:
					tempChoose = 0;
					break;
				    }
				    break;
				case 2:
				    sc.nextLine();
				    users.get(loggedUser).showGameEvents();
				    System.out.println(
					    "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
				    System.out.print("SELECT THE ID OF THE EVENT THAT YOU WANT TO REMOVE~$ ");
				    Integer eventGameRemoveId = sc.nextInt();
				    sc.nextLine();
				    users.get(loggedUser).removeGameEvent(eventGameRemoveId);
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
			    for (i = 0; i < users.size(); i++) {
				if (users.get(i).getDev().isEmpty()) {
				} else {
				    users.get(i).showDevEvents();
				    System.out.println();
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
					System.out.print("SELECT THE ID OF THE DEV EVENT THAT YOU WANT TO COMMENT:~$ ");
					Integer idDevComment = sc.nextInt();
					System.out.print("COMMENT ID:~$ ");
					Integer idComment = sc.nextInt();
					sc.nextLine();
					System.out.print("COMMENT TEXT:~$ ");
					String textComment = sc.nextLine();
					for (i = 0; i < users.size(); i++) {
					    for (int j = 0; j < users.get(i).getDev().size(); j++) {
						if (users.get(i).getDev().get(j).getEventId() == idDevComment) {
						    users.get(i).getDev().get(j).addComment(idComment,
							    users.get(loggedUser), textComment);
						}
					    }
					}
					System.out.println();
					System.out.print("ANOTHER COMMENT (Y/n):~$ ");
					System.out.println();
					wannaComment = sc.next().charAt(0);
				    }
				    break;
				case 2:
				    System.out.println(
					    "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
				    System.out.print("SELECT THE ID OF THE DEV EVENT:~$ ");
				    Integer idDevEventEdit = sc.nextInt();
				    System.out.print("SELECT THE ID OF THE COMMENT:~$ ");
				    Integer idCommentEdit = sc.nextInt();
				    sc.nextLine();
				    System.out.print("NEW COMMENT TEXT:~$ ");
				    String textCommentEdit = sc.nextLine();
				    for (i = 0; i < users.size(); i++) {
					for (int j = 0; j < users.get(i).getDev().size(); j++) {
					    if (users.get(i).getDev().get(j).getEventId() == idDevEventEdit) {
						users.get(i).getDev().get(j).editComment(idCommentEdit,
							textCommentEdit);
					    }
					}
				    }
				    break;
				case 3:
				    System.out.println(
					    "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
				    sc.nextLine();
				    System.out.print("SELECT THE ID OF THE DEV EVENT:~$ ");
				    Integer idDevEventDelete = sc.nextInt();
				    System.out.print("SELECT THE ID OF THE COMMENT:~$ ");
				    Integer idCommentDelete = sc.nextInt();
				    for (i = 0; i < users.size(); i++) {
					for (int j = 0; j < users.get(i).getDev().size(); j++) {
					    if (users.get(i).getDev().get(j).getEventId() == idDevEventDelete) {
						users.get(i).getDev().get(j).removeComment(idCommentDelete);
					    }
					}
				    }
				    break;
				case 4:
				    break;
				}
			    } while (tempChoose != 4);
			    wannaComment = 'y';
			    break;
			case 2:
			    System.out.println("{GAME EVENTS}");
			    for (i = 0; i < users.size(); i++) {
				if (users.get(i).getGame().isEmpty()) {
				} else {
				    users.get(i).showGameEvents();
				    System.out.println();
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
					System.out
						.print("SELECT THE ID OF THE GAME EVENT THAT YOU WANT TO COMMENT:~$ ");
					Integer idGameComment = sc.nextInt();
					System.out.print("COMMENT ID:~$ ");
					Integer idComment = sc.nextInt();
					sc.nextLine();
					System.out.print("COMMENT TEXT:~$ ");
					String textComment = sc.nextLine();
					for (i = 0; i < users.size(); i++) {
					    for (int j = 0; j < users.get(i).getGame().size(); j++) {
						if (users.get(i).getGame().get(j).getEventId() == idGameComment) {
						    users.get(i).getGame().get(j).addComment(idComment,
							    users.get(loggedUser), textComment);
						}
					    }
					}
					System.out.println();
					System.out.print("ANOTHER COMMENT (Y/n):~$ ");
					System.out.println();
					wannaComment = sc.next().charAt(0);
				    }
				    break;
				case 2:
				    System.out.println(
					    "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
				    System.out.print("SELECT THE ID OF THE GAME EVENT:~$ ");
				    Integer idGameEventEdit = sc.nextInt();
				    System.out.print("SELECT THE ID OF THE COMMENT:~$ ");
				    Integer idCommentEdit = sc.nextInt();
				    sc.nextLine();
				    System.out.print("NEW COMMENT TEXT:~$ ");
				    String textCommentEdit = sc.nextLine();
				    for (i = 0; i < users.size(); i++) {
					for (int j = 0; j < users.get(i).getGame().size(); j++) {
					    if (users.get(i).getGame().get(j).getEventId() == idGameEventEdit) {
						users.get(i).getGame().get(j).editComment(idCommentEdit,
							textCommentEdit);
					    }
					}
				    }
				    break;
				case 3:
				    System.out.println(
					    "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
				    sc.nextLine();
				    System.out.print("SELECT THE ID OF THE GAME EVENT:~$ ");
				    Integer idGameEventDelete = sc.nextInt();
				    System.out.print("SELECT THE ID OF THE COMMENT:~$ ");
				    Integer idCommentDelete = sc.nextInt();
				    for (i = 0; i < users.size(); i++) {
					for (int j = 0; j < users.get(i).getGame().size(); j++) {
					    if (users.get(i).getGame().get(j).getEventId() == idGameEventDelete) {
						users.get(i).getGame().get(j).removeComment(idCommentDelete);
					    }
					}
				    }
				    break;
				case 4:
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
		    loggedUser = -1;
		    wannaComment = 'y';
		    break;
		}
	    } while (true);
	    sc.close();
	}
    }
}
