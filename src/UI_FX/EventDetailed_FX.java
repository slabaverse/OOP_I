package UI_FX;

import entities.GameEvents;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class EventDetailed_FX extends Application {

    private Stage stage;
    private ImageView events;
    private Text eventUser;
    private Text eventName;
    private Text eventDate;
    private Text eventLocal;
    private Text eventDescription;
    private Text gameName;
    Pane pane;
    private GameEvents gameEvents;

    public EventDetailed_FX(GameEvents gameEvents) {
	this.gameEvents = gameEvents;
    }

    @Override
    public void start(Stage stage) throws Exception {
	this.stage = stage;

	initComponents();
	configLayout();

	Scene scene = new Scene(pane);

	stage.setScene(scene);
	stage.getIcons().add(new Image("/img/events.png"));
	stage.setTitle("Skynet");
	stage.setResizable(false);
	stage.show();
    }

    private void initComponents() {

	Image image = new Image("/img/events.png");
	events = new ImageView(image);

	eventUser = new Text("AN EVENT BY {" + gameEvents.getUser().getUsername() + "}");
	eventUser.setFont(new Font(16));
	eventUser.setFill(Color.CORNFLOWERBLUE);

	eventName = new Text("{" + gameEvents.getEventName() + "}");
	eventName.setFont(new Font(28));
	eventName.setFill(Color.CORNFLOWERBLUE);

	eventDate = new Text("SAVE THE DATE {" + gameEvents.getEventDate() + "}");
	eventDate.setFont(new Font(16));
	eventDate.setFill(Color.CORNFLOWERBLUE);

	eventLocal = new Text("LOCAL {" + gameEvents.getEventLocal() + "}");
	eventLocal.setFont(new Font(16));
	eventLocal.setFill(Color.CORNFLOWERBLUE);

	eventDescription = new Text("{" + gameEvents.getEventDescription() + "}");
	eventDescription.setFont(new Font(16));
	eventDescription.setFill(Color.CORNFLOWERBLUE);

	gameName = new Text("MAIN GAME {" + gameEvents.getGameName() + "}");
	gameName.setFont(new Font(16));
	gameName.setFill(Color.CORNFLOWERBLUE);

	pane = new AnchorPane();
	pane.styleProperty().set("-fx-background-color: #052336");

	pane.getChildren().addAll(events, eventUser, eventName, eventDate, eventLocal, eventDescription, gameName);
    }

    private void configLayout() {
	pane.setPrefSize(800, 413);

	events.setLayoutX(400);
	events.setLayoutY(0);
	events.setFitHeight(506);
	events.setFitWidth(413);

	eventUser.setLayoutX(10);
	eventUser.setLayoutY(40);
	eventUser.prefHeight(20);
	eventUser.prefWidth(200);

	eventName.setLayoutX(10);
	eventName.setLayoutY(105);
	eventName.prefHeight(20);
	eventName.prefWidth(200);

	eventDate.setLayoutX(10);
	eventDate.setLayoutY(135);
	eventDate.prefHeight(20);
	eventDate.prefWidth(200);

	eventLocal.setLayoutX(10);
	eventLocal.setLayoutY(195);
	eventLocal.prefHeight(20);
	eventLocal.prefWidth(200);

	eventDescription.setLayoutX(10);
	eventDescription.setLayoutY(425);
	eventDescription.prefHeight(20);
	eventDescription.prefWidth(200);

	gameName.setLayoutX(10);
	gameName.setLayoutY(225);
	gameName.prefHeight(20);
	gameName.prefWidth(200);

    }

}
