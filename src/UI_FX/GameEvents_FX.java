package UI_FX;

import dataBase.GameEventsDAO;
import entities.GameEvents;
import entities.User;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class GameEvents_FX extends Application {

    private Stage stage;
    private User loggedUser;
    private Label lblEvents;
    private Button addBtn;
    private Button editBtn;
    private Button removeBtn;
    private Button detailsBtn;
    private Pane pane;

    private TableView<GameEvents> table = new TableView<GameEvents>();
    private ObservableList<GameEvents> tableData;
    private TableColumn eventId;
    private TableColumn eventName;
    private TableColumn eventDate;

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
    
    public GameEvents_FX(User loggedUser) {
	this.loggedUser = loggedUser;
    }


    private void initComponents() {

	lblEvents = new Label("GAME EVENTS");
	lblEvents.setFont(new Font(40));
	lblEvents.styleProperty().set("-fx-text-fill: #4169E1");
	
	addBtn = new Button("ADD");
	addBtn.setOnAction(openAddScreen());
	addBtn.styleProperty().set(
		"-fx-text-fill: #FFFFFF; -fx-border-color: #4169E1; -fx-border-radius: 0; -fx-background-color: #4169E1;");

	editBtn = new Button("EDIT");
	editBtn.setOnAction(openEditScreen());
	editBtn.styleProperty().set(
		"-fx-text-fill: #FFFFFF; -fx-border-color: #4169E1; -fx-border-radius: 0; -fx-background-color: #4169E1;");

	removeBtn = new Button("REMOVE");
	removeBtn.setOnAction(removeEvent());
	removeBtn.styleProperty().set(
		"-fx-text-fill: #FFFFFF; -fx-border-color: #4169E1; -fx-border-radius: 0; -fx-background-color: #4169E1;");

	detailsBtn = new Button("SHOW MORE");
	detailsBtn.setOnAction(openDetailsScreen());
	detailsBtn.styleProperty().set(
		"-fx-text-fill: #FFFFFF; -fx-border-color: #4169E1; -fx-border-radius: 0; -fx-background-color: #4169E1;");

	table.setEditable(true);

	updateData();

	eventId = new TableColumn("ID");
	eventId.setMinWidth(90);
	eventId.setCellValueFactory(new PropertyValueFactory<GameEvents, Integer>("eventId"));

	eventName = new TableColumn("EVENT");
	eventName.setMinWidth(298);
	eventName.setCellValueFactory(new PropertyValueFactory<GameEvents, String>("eventName"));

	eventDate = new TableColumn("DATE");
	eventDate.setMinWidth(298);
	eventDate.setCellValueFactory(new PropertyValueFactory<GameEvents, String>("eventDate"));

	table.getColumns().addAll(eventId, eventName, eventDate);
	table.styleProperty().set("-fx-selection-bar: #58FAF4;");

	pane = new AnchorPane();
	pane.styleProperty().set("-fx-background-color: #FFFFFF");

	pane.getChildren().addAll(lblEvents, addBtn, editBtn, removeBtn, detailsBtn, table);
    }

    private void configLayout() {
	pane.setPrefSize(1000, 640);
	
	lblEvents.setLayoutX(350);
	lblEvents.setLayoutY(30);

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

	detailsBtn.setLayoutX(325);
	detailsBtn.setLayoutY(580);
	detailsBtn.setPrefHeight(20);
	detailsBtn.setPrefWidth(150);

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
		    new AddEvent_FX(loggedUser).start(stage);
		} catch (Exception e) {
		    Alert_FX.error("WHERE'S THE ADD EVENT SCREEN? ");
		}
	    }
	};
    }
    

    private EventHandler<ActionEvent> openEditScreen() {
		return new EventHandler<ActionEvent>() {
	    @Override
	    public void handle(ActionEvent event) {
		try {
		    if (table.getSelectionModel().isEmpty()) {
			Alert_FX.alert("WICH EVENT YOU WANNA UPDATE?.");
			return;
		    }

		    GameEvents events = new GameEventsDAO()
			    .get(table.getSelectionModel().getSelectedItem().getEventId());
		    new EditEvents_FX(events, loggedUser).start(stage);
		} catch (Exception e) {
		    System.out.println(e);
		    Alert_FX.error("HMM, I COULDN'T OPEN EDIT SCREEN");
		}
	    }
	};
    }

    private EventHandler<ActionEvent> openDetailsScreen() {
	return new EventHandler<ActionEvent>() {
	    @Override
	    public void handle(ActionEvent event) {
		try {
		    if (table.getSelectionModel().isEmpty()) {
			Alert_FX.alert("WICH EVENTS YOU WANNA SEE?");
			return;
		    }

		    GameEvents events = new GameEventsDAO()
			    .get(table.getSelectionModel().getSelectedItem().getEventId());
		    new EventDetailed_FX(events).start(new Stage());
		} catch (Exception e) {
		    Alert_FX.error("HMM, I COULDN'T OPEN EVENT DETAILS SCREEN");
		}
	    }
	};
    }

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
