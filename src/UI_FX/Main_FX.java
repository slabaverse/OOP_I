package UI_FX;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Main_FX extends Application {
    private Stage stage;
    private Pane pane;
    private Label lblUser;
    private ImageView profile;
    private String loggedUser;
    private Button btnUserSettings;
    private Button btnPost;
    private Button btnMarketplace;
    private Button btnFriends;
    private Button btnEvents;
    private Button btnExit;

    public Main_FX(String loggedUser) {
	if (loggedUser.isBlank())
	    loggedUser = "ERROR: MISSING USERNAME";
	this.loggedUser = loggedUser;
    }

    @Override
    public void start(Stage stage) throws Exception {
	this.stage = stage;

	initComponentes();

	configLayout();

	Scene scene = new Scene(pane);
	btnExit.requestFocus();

	stage.setScene(scene);
	stage.getIcons().add(new Image("/img/profile.png"));
	stage.setTitle("WELCOME " + loggedUser);
	stage.setResizable(false);
	stage.show();
    }

    private void initComponentes() {

	Image image = new Image("/img/profile.png");
	profile = new ImageView(image);

	lblUser = new Label(loggedUser);
	lblUser.setFont(new Font(40));
	lblUser.styleProperty().set("-fx-text-fill: #4169E1");

	btnUserSettings = new Button("USER SETTINGS");
	btnUserSettings.styleProperty().set(
		"-fx-text-fill: #FFFFFF; -fx-border-color: #4169E1; -fx-border-radius: 0; -fx-background-color: #4169E1;");

	btnPost = new Button("NEW POST");
	btnPost.styleProperty().set(
		"-fx-text-fill: #FFFFFF; -fx-border-color: #4169E1; -fx-border-radius: 0; -fx-background-color: #4169E1;");

	btnMarketplace = new Button("MARKETPLACE");
	btnMarketplace.styleProperty().set(
		"-fx-text-fill: #FFFFFF; -fx-border-color: #4169E1; -fx-border-radius: 0; -fx-background-color: #4169E1;");

	btnFriends = new Button("FRIENDS");
	btnFriends.styleProperty().set(
		"-fx-text-fill: #FFFFFF; -fx-border-color: #4169E1; -fx-border-radius: 0; -fx-background-color: #4169E1;");

	btnEvents = new Button("EVENTS");
	btnEvents.styleProperty().set(
		"-fx-text-fill: #FFFFFF; -fx-border-color: #4169E1; -fx-border-radius: 0; -fx-background-color: #4169E1;");
	btnEvents.setOnAction(openEventScreen());

	btnExit = new Button("EXIT");
	btnExit.styleProperty().set(
		"-fx-text-fill: #FFFFFF; -fx-border-color: #4169E1; -fx-border-radius: 0; -fx-background-color: #4169E1;");
	btnExit.setOnAction(exit());

	pane = new AnchorPane();
	pane.styleProperty().set("-fx-background-color: #FFFFFF");
	pane.getChildren().addAll(lblUser, profile, btnUserSettings, btnPost, btnMarketplace, btnFriends, btnEvents,
		btnExit);

    }

    private void configLayout() {
	pane.setPrefSize(600, 600);

	profile.setLayoutX(190);
	profile.setLayoutY(10);

	lblUser.setLayoutX(30);
	lblUser.setLayoutY(10);

	btnUserSettings.setLayoutX(pane.getPrefWidth() - 580);
	btnUserSettings.setLayoutY(pane.getPrefHeight() - 300);
	btnUserSettings.setPrefHeight(40);
	btnUserSettings.setPrefWidth(250);

	btnPost.setLayoutX(pane.getPrefWidth() - 580);
	btnPost.setLayoutY(pane.getPrefHeight() - 250);
	btnPost.setPrefHeight(40);
	btnPost.setPrefWidth(250);

	btnMarketplace.setLayoutX(pane.getPrefWidth() - 580);
	btnMarketplace.setLayoutY(pane.getPrefHeight() - 200);
	btnMarketplace.setPrefHeight(40);
	btnMarketplace.setPrefWidth(250);

	btnFriends.setLayoutX(pane.getPrefWidth() - 580);
	btnFriends.setLayoutY(pane.getPrefHeight() - 150);
	btnFriends.setPrefHeight(40);
	btnFriends.setPrefWidth(250);

	btnEvents.setLayoutX(pane.getPrefWidth() - 580);
	btnEvents.setLayoutY(pane.getPrefHeight() - 100);
	btnEvents.setPrefHeight(40);
	btnEvents.setPrefWidth(250);

	btnExit.setLayoutX(pane.getPrefWidth() - 580);
	btnExit.setLayoutY(pane.getPrefHeight() - 50);
	btnExit.setPrefHeight(40);
	btnExit.setPrefWidth(250);
    }

    private EventHandler<ActionEvent> openEventScreen() {
	return new EventHandler<ActionEvent>() {
	    @Override
	    public void handle(ActionEvent event) {
		try {
		    new GameEvents_FX().start(stage);
		} catch (Exception e) {
		    Alert_FX.error("WHERE'S THE GAME EVENTS SCREEN?");
		}
	    }
	};
    }

    private EventHandler<ActionEvent> exit() {
	return new EventHandler<ActionEvent>() {
	    @Override
	    public void handle(ActionEvent event) {
		try {
		    new Login_FX().start(stage);
		} catch (Exception e) {
		    Alert_FX.error("WHERE'S THE LOGIN SCREEN?");
		}
	    }
	};
    }
}
