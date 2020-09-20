package UI_FX;

import dataBase.GameEventsDAO;
import entities.GameEvents;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class AddEvent_FX {
    private Stage stage;
    private TextField eventNameTxt;
    private TextField eventDateTxt;
    private TextField eventLocalTxt;
    private TextField eventDescriptionTxt;
    private TextField gameNameTxt;
    private Button saveBtn;
    private Button cancelBtn;
    private Pane pane;

    public void start(Stage stage) throws Exception {
	this.stage = stage;

	initComponents();
	configLayout();

	Scene scene = new Scene(pane);
	saveBtn.requestFocus();

	stage.setScene(scene);
	stage.setTitle("Skynet");
	stage.setResizable(false);
	stage.show();
    }

    private void initComponents() {

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

	saveBtn = new Button("Save");
	saveBtn.setOnAction(save());
	saveBtn.styleProperty().set(
		"-fx-text-fill: #FFFFFF; -fx-border-color: #4169E1; -fx-border-radius: 0; -fx-background-color: #4169E1;");

	cancelBtn = new Button("CANCEL");
	cancelBtn.setOnAction(cancel());
	cancelBtn.styleProperty().set(
		"-fx-text-fill: #FFFFFF; -fx-border-color: #4169E1; -fx-border-radius: 0; -fx-background-color: #4169E1;");

	pane = new AnchorPane();
	pane.styleProperty().set("-fx-background-color: #3E3E3E");

	pane.getChildren().addAll(eventNameTxt, eventDateTxt, eventLocalTxt, eventDescriptionTxt, gameNameTxt, saveBtn,
		cancelBtn);
    }

    private void configLayout() {
	pane.setPrefSize(400, 260);

	eventNameTxt.setLayoutX(22);
	eventNameTxt.setLayoutY(165);
	eventNameTxt.setPrefHeight(50);
	eventNameTxt.setPrefWidth((pane.getPrefWidth() - 60) / 2);

	eventDateTxt.setLayoutX(20);
	eventDateTxt.setLayoutY(100);
	eventDateTxt.setPrefHeight(20);
	eventDateTxt.setPrefWidth((pane.getPrefWidth() - 60) / 2);

	eventLocalTxt.setLayoutX(210);
	eventLocalTxt.setLayoutY(100);
	eventLocalTxt.setPrefHeight(20);
	eventLocalTxt.setPrefWidth((pane.getPrefWidth() - 60) / 2);

	eventDescriptionTxt.setLayoutX(20);
	eventDescriptionTxt.setLayoutY(160);
	eventDescriptionTxt.setPrefHeight(20);
	eventDescriptionTxt.setPrefWidth((pane.getPrefWidth() - 60) / 2);

	gameNameTxt.setLayoutX(210);
	gameNameTxt.setLayoutY(190);
	gameNameTxt.setPrefHeight(20);
	gameNameTxt.setPrefWidth((pane.getPrefWidth() - 60) / 2);

	saveBtn.setLayoutX(20);
	saveBtn.setLayoutY(210);
	saveBtn.setPrefHeight(20);
	saveBtn.setPrefWidth((pane.getPrefWidth() - 60) / 2);

	cancelBtn.setLayoutX(210);
	cancelBtn.setLayoutY(210);
	cancelBtn.setPrefHeight(20);
	cancelBtn.setPrefWidth((pane.getPrefWidth() - 60) / 2);

    }

    private EventHandler<ActionEvent> save() {
	return new EventHandler<ActionEvent>() {
	    @Override
	    public void handle(ActionEvent event) {
		try {
		    
		    if (eventNameTxt.getText().isEmpty()) {
			Alert_FX.alert("Name field is empty");
			return;
		    }
		    if (eventDateTxt.getText().isEmpty()) {
			Alert_FX.alert("Birth date field is empty");
			return;
		    }
		    if (eventLocalTxt.getText().isEmpty()) {
			Alert_FX.alert("Nationality field is empty");
			return;
		    }
		    if (eventDescriptionTxt.getText().isEmpty()) {
			Alert_FX.alert("Height field is empty");
			return;
		    } 
		    if (gameNameTxt.getText().isEmpty()) {
			Alert_FX.alert("Height field is empty");
			return;
		    }

//		    new GameEventsDAO().add(new GameEvents(loggedUser, eventNameTxt.getText(), eventDateTxt.getText(),
//			    eventLocalTxt.getText(), eventDescriptionTxt.getText(), gameNameTxt.getText()));

		    openActorWindow();
		} catch (Exception e) {
		    Alert_FX.error("Inable to save the actor!");
		}
	    }
	};
    }

    private void openActorWindow() {
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
