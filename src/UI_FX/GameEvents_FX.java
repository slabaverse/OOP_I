package UI_FX;

import dataBase.GameEventsDAO;
import entities.GameEvents;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class GameEvents_FX extends Application {

    private Stage stage;
    private Button addBtn;
    private Button editBtn;
    private Button removeBtn;
    private Pane pane;

    private TableView<GameEvents> table = new TableView<GameEvents>();
    private ObservableList<GameEvents> tableData;
    private TableColumn eventId;
    private TableColumn user;
    private TableColumn eventName;
    private TableColumn eventDate;
    private TableColumn eventLocal;
    private TableColumn eventDescription;
    private TableColumn gameName;

    @Override
    public void start(Stage stage) throws Exception {
	this.stage = stage;

	initComponents();
	configLayout();

	Scene scene = new Scene(pane);

	stage.setScene(scene);
	stage.setTitle("Skynet");
	stage.setResizable(false);
	stage.show();
    }

    private void initComponents() {

	table.setEditable(true);

	updateData();

	eventId = new TableColumn("id");
	eventId.setMinWidth(100);
	eventId.setCellValueFactory(new PropertyValueFactory<GameEvents, Integer>("id"));

	user = new TableColumn("username");
	user.setMinWidth(400);
	user.setCellValueFactory(new PropertyValueFactory<GameEvents, String>("username"));

	eventName = new TableColumn("Nationality");
	eventName.setMinWidth(298);
	eventName.setCellValueFactory(new PropertyValueFactory<GameEvents, String>("eventName"));

	eventDate = new TableColumn("Nationality");
	eventDate.setMinWidth(298);
	eventDate.setCellValueFactory(new PropertyValueFactory<GameEvents, String>("eventDate"));

	eventLocal = new TableColumn("Nationality");
	eventLocal.setMinWidth(298);
	eventLocal.setCellValueFactory(new PropertyValueFactory<GameEvents, String>("eventLocal"));

	eventDescription = new TableColumn("Nationality");
	eventDescription.setMinWidth(298);
	eventDescription.setCellValueFactory(new PropertyValueFactory<GameEvents, String>("eventDescription"));

	gameName = new TableColumn("Nationality");
	gameName.setMinWidth(298);
	gameName.setCellValueFactory(new PropertyValueFactory<GameEvents, String>("gameName"));

	table.getColumns().addAll(eventId, user, eventName, eventDate, eventLocal, eventDescription, gameName);
	table.styleProperty().set("-fx-selection-bar: #32FF6B;");

	addBtn = new Button("ADD");
	addBtn.setOnAction(openAddScreen());
	addBtn.styleProperty().set(
		"-fx-text-fill: #FFFFFF; -fx-border-color: #4169E1; -fx-border-radius: 0; -fx-background-color: #4169E1;");
//
//	editBtn = new Button("EDIT");
//	editBtn.setOnAction(openEditWindow());
//	editBtn.styleProperty().set(
//		"-fx-text-fill: #FFFFFF; -fx-border-color: #4169E1; -fx-border-radius: 0; -fx-background-color: #4169E1;");

	removeBtn = new Button("REMOVE");
	removeBtn.setOnAction(removeEvent());
	removeBtn.styleProperty().set(
		"-fx-text-fill: #FFFFFF; -fx-border-color: #4169E1; -fx-border-radius: 0; -fx-background-color: #4169E1;");

	pane = new AnchorPane();
	pane.styleProperty().set("-fx-background-color: #3E3E3E");

	pane.getChildren().addAll(table, addBtn, editBtn, removeBtn);
    }

    private void configLayout() {
	pane.setPrefSize(1000, 640);

	table.setLayoutX(100);
	table.setLayoutY(150);
	table.setPrefHeight(400);
	table.maxHeight(400);
	table.setPrefWidth(800);

	addBtn.setLayoutX(125);
	addBtn.setLayoutY(580);
	addBtn.setPrefHeight(20);
	addBtn.setPrefWidth(150);

	editBtn.setLayoutX(525);
	editBtn.setLayoutY(580);
	editBtn.setPrefHeight(20);
	editBtn.setPrefWidth(150);

	removeBtn.setLayoutX(725);
	removeBtn.setLayoutY(580);
	removeBtn.setPrefHeight(20);
	removeBtn.setPrefWidth(150);
    }

    private EventHandler<ActionEvent> openAddScreen() {
	return new EventHandler<ActionEvent>() {
	    @Override
	    public void handle(ActionEvent event) {
		try {
		    new AddEvent_FX().start(stage);
		} catch (Exception e) {
		    Alert_FX.error("WHERE'S THE ADD EVENT SCREEN? ");
		}
	    }
	};
    }

//    private EventHandler<ActionEvent> openEditScreen() {
//	return new EventHandler<ActionEvent>() {
//	    @Override
//	    public void handle(ActionEvent event) {
//		try {
//		    if (table.getSelectionModel().isEmpty()) {
//			AlertFX.alert("Select a actor to be updated.");
//			return;
//		    }
//
//		    Protagonist actor = new ProtagonistDAO()
//			    .getById(table.getSelectionModel().getSelectedItem().getId());
//		    new EditActorFX(actor).start(stage);
//		} catch (Exception e) {
//		    AlertFX.error("Inable to open the actor edition window!");
//		}
//	    }
//	};
//    }

    private EventHandler<ActionEvent> removeEvent() {
	return new EventHandler<ActionEvent>() {
	    @Override
	    public void handle(ActionEvent event) {
		if (table.getSelectionModel().isEmpty()) {
		    Alert_FX.alert("SELECT AN EVENT TO BE REMOVED");
		    return;
		}

		GameEventsDAO gameDAO = new GameEventsDAO();
		GameEvents gameEv = gameDAO.get(table.getSelectionModel().getSelectedItem().getEventId());
		gameDAO.remove(gameEv);

		updateData();
	    }
	};
    }

    private void updateData() {
	tableData = FXCollections.observableArrayList(new GameEventsDAO().all());

	table.setItems(tableData);
    }

}
