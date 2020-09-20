package UI_FX;

import dataBase.GameEventsDAO;
import entities.GameEvents;
import entities.User;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class AddEvent_FX {
    private Stage stage;
    private Label lblAddEvents;
    private ImageView game;
    private User loggedUser;
    private TextField eventNameTxt;
    private TextField eventDateTxt;
    private TextField eventLocalTxt;
    private TextField eventDescriptionTxt;
    private TextField gameNameTxt;
    private Button saveBtn;
    private Button cancelBtn;
    private Pane pane;
    
    public AddEvent_FX(User loggedUser) {
	this.loggedUser = loggedUser;
    }
    
    public void start(Stage stage) throws Exception {
	this.stage = stage;

	initComponents();
	configLayout();

	Scene scene = new Scene(pane);
	saveBtn.requestFocus();

	stage.setScene(scene);
	stage.getIcons().add(new Image("/img/game.png"));
	stage.setTitle("Skynet");
	stage.setResizable(false);
	stage.show();
    }
    
    private void initComponents() {
	
	Image image = new Image("/img/game.png");
	game = new ImageView(image);
	
	lblAddEvents = new Label("NEW GAME EVENTS");
	lblAddEvents.setFont(new Font(40));
	lblAddEvents.styleProperty().set("-fx-text-fill: #778899");

	eventNameTxt = new TextField();
	eventNameTxt.setPromptText("EVENT NAME");
	eventNameTxt.styleProperty().set(
		"-fx-text-fill: #778899; -fx-border-color: #4169E1; -fx-border-radius: 0; -fx-background-color: #F8F8FF;");

	eventDateTxt = new TextField();
	eventDateTxt.setPromptText("SAVE THE DATE");
	eventDateTxt.styleProperty().set(
		"-fx-text-fill: #778899; -fx-border-color: #4169E1; -fx-border-radius: 0; -fx-background-color: #F8F8FF;");

	eventLocalTxt = new TextField();
	eventLocalTxt.setPromptText("EVENT LOCAL");
	eventLocalTxt.styleProperty().set(
		"-fx-text-fill: #778899; -fx-border-color: #4169E1; -fx-border-radius: 0; -fx-background-color: #F8F8FF;");

	eventDescriptionTxt = new TextField();
	eventDescriptionTxt.setPromptText("EVENT DESCRIPTION");
	eventDescriptionTxt.styleProperty().set(
		"-fx-text-fill: #778899; -fx-border-color: #4169E1; -fx-border-radius: 0; -fx-background-color: #F8F8FF;");

	gameNameTxt = new TextField();
	gameNameTxt.setPromptText("MAIN GAME NAME");
	gameNameTxt.styleProperty().set(
		"-fx-text-fill: #778899; -fx-border-color: #4169E1; -fx-border-radius: 0; -fx-background-color: #F8F8FF;");

	saveBtn = new Button("SAVE");
	saveBtn.setOnAction(save());
	saveBtn.styleProperty().set(
		"-fx-text-fill: #FFFFFF; -fx-border-color: #4169E1; -fx-border-radius: 0; -fx-background-color: #4169E1;");

	cancelBtn = new Button("CANCEL");
	cancelBtn.setOnAction(cancel());
	cancelBtn.styleProperty().set(
		"-fx-text-fill: #FFFFFF; -fx-border-color: #4169E1; -fx-border-radius: 0; -fx-background-color: #4169E1;");

	pane = new AnchorPane();
	pane.styleProperty().set("-fx-background-color: #F2F8D2");

	pane.getChildren().addAll(lblAddEvents, game, eventNameTxt, eventDateTxt, eventLocalTxt, eventDescriptionTxt, gameNameTxt, saveBtn,
		cancelBtn);
    }

    private void configLayout() {
	pane.setPrefSize(1000, 600);
	
	game.setLayoutX(450);
	
	lblAddEvents.setLayoutX(30);
	lblAddEvents.setLayoutY(30);

	eventNameTxt.setLayoutX(30);
	eventNameTxt.setLayoutY(160);
	eventNameTxt.setPrefHeight(40);
	eventNameTxt.setPrefWidth((pane.getPrefWidth() - 60)/2);

	eventDateTxt.setLayoutX(30);
	eventDateTxt.setLayoutY(220);
	eventDateTxt.setPrefHeight(40);
	eventDateTxt.setPrefWidth((pane.getPrefWidth() - 60)/2);

	eventLocalTxt.setLayoutX(30);
	eventLocalTxt.setLayoutY(280);
	eventLocalTxt.setPrefHeight(40);
	eventLocalTxt.setPrefWidth((pane.getPrefWidth() - 60) / 2);

	eventDescriptionTxt.setLayoutX(30);
	eventDescriptionTxt.setLayoutY(340);
	eventDescriptionTxt.setPrefHeight(40);
	eventDescriptionTxt.setPrefWidth((pane.getPrefWidth() - 60) / 2);

	gameNameTxt.setLayoutX(30);
	gameNameTxt.setLayoutY(400);
	gameNameTxt.setPrefHeight(40);
	gameNameTxt.setPrefWidth((pane.getPrefWidth() - 60) / 2);

	saveBtn.setLayoutX(30);
	saveBtn.setLayoutY(460);
	saveBtn.setPrefHeight(40);
	saveBtn.setPrefWidth((pane.getPrefWidth() - 20) / 4);

	cancelBtn.setLayoutX(30);
	cancelBtn.setLayoutY(520);
	cancelBtn.setPrefHeight(40);
	cancelBtn.setPrefWidth((pane.getPrefWidth() - 20) / 4);

    }

    private EventHandler<ActionEvent> save() {
	return new EventHandler<ActionEvent>() {
	    @Override
	    public void handle(ActionEvent event) {
		try {
		    
		    
		    if (eventNameTxt.getText().isEmpty()) {
			Alert_FX.alert("EVENT WITHOUT NAME?");
			return;
		    }
		    if (eventDateTxt.getText().isEmpty()) {
			Alert_FX.alert("PEOPLE CLAIM FOR A DATE");
			return;
		    }
		    if (eventLocalTxt.getText().isEmpty()) {
			Alert_FX.alert("LOCAL, TO SET MY GPS");
			return;
		    }
		    if (eventDescriptionTxt.getText().isEmpty()) {
			Alert_FX.alert("TELL ME MORE");
			return;
		    } 
		    if (gameNameTxt.getText().isEmpty()) {
			Alert_FX.alert("WICH GAME?");
			return;
		    }

		    new GameEventsDAO().add(new GameEvents(loggedUser , eventNameTxt.getText(), eventDateTxt.getText(),
			    eventLocalTxt.getText(), eventDescriptionTxt.getText(), gameNameTxt.getText()));

		    openEventScreen();
		} catch (Exception e) {
		    Alert_FX.error("I COULDN'T SAVE THIS NEW EVENT");
		}
	    }
	};
    }

    private void openEventScreen() {
	try {
	    new GameEvents_FX().start(stage);
	} catch (Exception e) {
	    Alert_FX.error("WHERE'S THE GAME EVENTS SCREEN?");
	}
    }

    private EventHandler<ActionEvent> cancel() {
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
}
