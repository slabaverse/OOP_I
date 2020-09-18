package UI_FX;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Main_FX extends Application {
//    private Stage stage;
//    private Pane pane;
    private String loggedUser;
//    private Button btnExit;
//    private Button btnNewGemEvent;
//    private Button btnSetGameEvent;
//    private Button btnExcludeGameEvent;
//    private ListView<String> gameEventList;

    public Main_FX(String loggedUser) {
	if (loggedUser.isBlank())
	    loggedUser = "{ ERROR: MISSING USERNAME }";
	this.loggedUser = loggedUser;
    }

    @Override
    public void start(Stage stage) throws Exception {
	Pane pane = new AnchorPane();
	pane.setPrefSize(340, 480);
//	this.stage = stage;

//	initComponentes();
//
//	configLayout();

	Scene scene = new Scene(pane);
//	btnExit.requestFocus();

	stage.setScene(scene);
	stage.setTitle("{ LOGGES AS " + loggedUser);
	stage.setResizable(false);
	stage.show();
    }
//
//    private void initComponentes() {
//	gameEventList = new ListView<String>();
//	ObservableList<String> items = FXCollections.observableArrayList(genEventList());
//	gameEventList.setItems(items);
//
////	btnNewGemEvent = new Button("NEW GAME EVENT");
////	btnNewGemEvent.setOnAction(openNewEvent());
////
////	btnSetGameEvent = new Button("SET GAME EVENT");
////	btnSetGameEvent.setOnAction(openSetEvent());
//
//	btnExcludeGameEvent = new Button("DELETE GAME EVENT");
//	btnExcludeGameEvent.setOnAction(deleteEvent());
//
//	btnExit = new Button("EXIT");
//	btnExit.setOnAction(exit());
//
//	pane = new AnchorPane();
//	pane.getChildren().addAll(gameEventList, btnNewGemEvent, btnSetGameEvent, btnExcludeGameEvent, btnExit);
//
//    }
//
//    private void configLayout() {
//	pane.setPrefSize(640, 480);
//
//	gameEventList.setLayoutX(10);
//	gameEventList.setLayoutY(10);
//	gameEventList.setPrefHeight(pane.getPrefHeight() - 55);
//	gameEventList.setPrefWidth(pane.getPrefWidth() - 20);
//
//	btnNewGemEvent.setLayoutX(pane.getPrefWidth() - 590);
//	btnNewGemEvent.setLayoutY(pane.getPrefHeight() - 35);
//	btnNewGemEvent.setPrefHeight(20);
//	btnNewGemEvent.setPrefWidth(150);
//
//	btnSetGameEvent.setLayoutX(pane.getPrefWidth() - 430);
//	btnSetGameEvent.setLayoutY(pane.getPrefHeight() - 35);
//	btnSetGameEvent.setPrefHeight(20);
//	btnSetGameEvent.setPrefWidth(150);
//
//	btnExcludeGameEvent.setLayoutX(pane.getPrefWidth() - 270);
//	btnExcludeGameEvent.setLayoutY(pane.getPrefHeight() - 35);
//	btnExcludeGameEvent.setPrefHeight(20);
//	btnExcludeGameEvent.setPrefWidth(150);
//
//	btnExit.setLayoutX(pane.getPrefWidth() - 110);
//	btnExit.setLayoutY(pane.getPrefHeight() - 35);
//	btnExit.setPrefHeight(20);
//	btnExit.setPrefWidth(100);
//    }
//
//    private List<String> genEventList() {
//	List<String> retrn = new ArrayList<String>();
//	List<GameEvents> gameEvents = new GameEventsDAO().all();
//	for (GameEvents gameEvent : gameEvents)
//	    retrn.add(gameEvent.getEventName());
//	return retrn;
//    }
//
//    private EventHandler<ActionEvent> deleteEvent() {
//	return new EventHandler<ActionEvent>() {
//	    @Override
//	    public void handle(ActionEvent event) {
//		if (gameEventList.getSelectionModel().isEmpty()) {
//		    Alert_FX.alert("SELECT AN EVENT TO DELETE");
//		    return;
//		}
//
//		GameEventsDAO dao = new GameEventsDAO();
//		GameEvents gameEvent = dao.getByName(gameEventList.getSelectionModel().getSelectedItem());
//		dao.remove(gameEvent);
//		upgradeList();
//	    }
//	};
//    }
//
//    private void upgradeList() {
//	ObservableList<String> items = FXCollections.observableArrayList(genEventList());
//	gameEventList.setItems(items);
//    }
//
////    private EventHandler<ActionEvent> openSetEvent() {
////	return new EventHandler<ActionEvent>() {
////	    @Override
////	    public void handle(ActionEvent event) {
////		if (gameEventList.getSelectionModel().isEmpty()) {
////		    Alert_FX.alert("SELECT AN EVENT TO UPGRADE");
////		    return;
////		}
////
////		String eventName = gameEventList.getSelectionModel().getSelectedItem();
////		GameEvents gameEvent = new GameEventsDAO().getByName(eventName);
////
////		try {
////		    new AlterarJogoFX(usuarioLogado, jogo).start(stage);
////		} catch (Exception e) {
////		    Alert_XF.erro("Não foi possível iniciar a tela de cadastro de um jogo!");
////		}
////	    }
////	};
////    }
////
////    private EventHandler<ActionEvent> openNewEvent() {
////	return new EventHandler<ActionEvent>() {
////	    @Override
////	    public void handle(ActionEvent event) {
////		try {
////		    new CadastrarJogoFX(usuarioLogado).start(stage);
////		} catch (Exception e) {
////		    Alert_XF.erro("Não foi possível iniciar a tela de cadastro de um jogo!");
////		}
////	    }
////	};
////    }
//
//    private EventHandler<ActionEvent> exit() {
//	return new EventHandler<ActionEvent>() {
//	    @Override
//	    public void handle(ActionEvent event) {
//		try {
//		    new Login_FX().start(stage);
//		} catch (Exception e) {
//		    Alert_FX.error("Não foi possível iniciar a tela de login");
//		}
//	    }
//	};
//    }
}
