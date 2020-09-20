package UI_FX;

import dataBase.UserDAO;
import entities.User;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class NewUser_FX extends Application {

    private Stage stage;
    private Pane pane;
    private ImageView nu;
    private TextField txtUser;
    private PasswordField txtPassword1;
    private PasswordField txtPassword2;
    private TextField txtName;
    private TextField txtBirthdate;
    private TextField txtRelationship;
    private Button btnBack;
    private Button btnCreate;

    @Override
    public void start(Stage stage) throws Exception {
	this.stage = stage;

	initComponentes();

	configLayout();

	Scene scene = new Scene(pane);
	btnBack.requestFocus();

	stage.setScene(scene);
	stage.getIcons().add(new Image("/img/nu.png"));
	stage.setTitle("NEW USER");
	stage.setResizable(false);
	stage.show();
    }

    private void initComponentes() {

	Image image = new Image("/img/nu.png");
	nu = new ImageView(image);

	txtUser = new TextField();
	txtUser.setPromptText("USERNAME: ");
	txtUser.styleProperty().set(
		"-fx-text-fill: #778899; -fx-border-color: #4169E1; -fx-border-radius: 0; -fx-background-color: #F8F8FF;");

	txtPassword1 = new PasswordField();
	txtPassword1.setPromptText("PASSWORD: ");
	txtPassword1.styleProperty().set(
		"-fx-text-fill: #778899; -fx-border-color: #4169E1; -fx-border-radius: 0; -fx-background-color: #F8F8FF;");

	txtPassword2 = new PasswordField();
	txtPassword2.setPromptText("CONFIRM YOUR PASSWORD: ");
	txtPassword2.styleProperty().set(
		"-fx-text-fill: #778899; -fx-border-color: #4169E1; -fx-border-radius: 0; -fx-background-color: #F8F8FF;");

	txtName = new TextField();
	txtName.setPromptText("NAME: ");
	txtName.styleProperty().set(
		"-fx-text-fill: #778899; -fx-border-color: #4169E1; -fx-border-radius: 0; -fx-background-color: #F8F8FF;");

	txtBirthdate = new TextField();
	txtBirthdate.setPromptText("BIRTHDATE (DD/MM/AAA): ");
	txtBirthdate.styleProperty().set(
		"-fx-text-fill: #778899; -fx-border-color: #4169E1; -fx-border-radius: 0; -fx-background-color: #F8F8FF;");

	txtRelationship = new TextField();
	txtRelationship.setPromptText("RELATIONSHIP STATUS: ");
	txtRelationship.styleProperty().set(
		"-fx-text-fill: #778899; -fx-border-color: #4169E1; -fx-border-radius: 0; -fx-background-color: #F8F8FF;");

	btnCreate = new Button("REGISTER");
	btnCreate.styleProperty().set(
		"-fx-text-fill: #FFFFFF; -fx-border-color: #4169E1; -fx-border-radius: 0; -fx-background-color: #4169E1;");
	btnCreate.setOnAction(register());

	btnBack = new Button("BACK");
	btnBack.styleProperty().set(
		"-fx-text-fill: #FFFFFF; -fx-border-color: #4169E1; -fx-border-radius: 0; -fx-background-color: #4169E1;");
	btnBack.setOnAction(back());

	pane = new AnchorPane();
	pane.getChildren().addAll(nu, txtUser, txtPassword1, txtPassword2, txtName, txtBirthdate, txtRelationship,
		btnCreate, btnBack);

    }

    private void configLayout() {
	pane.setPrefSize(760, 828);

	txtUser.setLayoutX(550);
	txtUser.setLayoutY(150);
	txtUser.setPrefHeight(40);
	txtUser.setPrefWidth(pane.getPrefWidth() - 120);

	txtPassword1.setLayoutX(550);
	txtPassword1.setLayoutY(200);
	txtPassword1.setPrefHeight(40);
	txtPassword1.setPrefWidth(pane.getPrefWidth() - 120);

	txtPassword2.setLayoutX(550);
	txtPassword2.setLayoutY(250);
	txtPassword2.setPrefHeight(40);
	txtPassword2.setPrefWidth(pane.getPrefWidth() - 120);
	
	txtName.setLayoutX(550);
	txtName.setLayoutY(300);
	txtName.setPrefHeight(40);
	txtName.setPrefWidth(pane.getPrefWidth() - 120);

	txtBirthdate.setLayoutX(550);
	txtBirthdate.setLayoutY(350);
	txtBirthdate.setPrefHeight(40);
	txtBirthdate.setPrefWidth(pane.getPrefWidth() - 120);

	txtRelationship.setLayoutX(550);
	txtRelationship.setLayoutY(400);
	txtRelationship.setPrefHeight(40);
	txtRelationship.setPrefWidth(pane.getPrefWidth() - 120);

	btnCreate.setLayoutX(550);
	btnCreate.setLayoutY(480);
	btnCreate.setPrefHeight(45);
	btnCreate.setPrefWidth(pane.getPrefWidth() - 120);
	
	btnBack.setLayoutX(550);
	btnBack.setLayoutY(550);
	btnBack.setPrefHeight(45);
	btnBack.setPrefWidth(pane.getPrefWidth() - 120);


    }

    private EventHandler<ActionEvent> back() {
	return new EventHandler<ActionEvent>() {
	    @Override
	    public void handle(ActionEvent event) {
		openLoginWindow();
	    }
	};
    }

    private EventHandler<ActionEvent> register() {
	return new EventHandler<ActionEvent>() {
	    @Override
	    public void handle(ActionEvent event) {
		if (txtUser.getText().isBlank()) {
		    Alert_FX.alert("USERNAME?");
		    return;
		}
		if (txtPassword1.getText().isBlank()) {
		    Alert_FX.alert("PASSWORD?");
		    return;
		}
		if (txtPassword2.getText().isBlank()) {
		    Alert_FX.alert("PASSWORD..AGAAAAIN?");
		    return;
		}
		if (txtName.getText().isBlank()) {
		    Alert_FX.alert("FULL NAME BRO!");
		    return;
		}

		if (!txtPassword1.getText().contentEquals(txtPassword2.getText())) {
		    Alert_FX.alert("PASSWORD != PASSWORD CONFIRMATION");
		    return;
		}
		if (txtBirthdate.getText().isBlank()) {
		    Alert_FX.alert("B-DAY?");
		    return;
		}
		if (txtRelationship.getText().isBlank()) {
		    Alert_FX.alert("RELATIONSHIP?");
		    return;
		}

		new UserDAO().add(new User(txtUser.getText(), txtPassword1.getText(), txtName.getText(), 
			txtBirthdate.getText(), txtRelationship.getText()));

		Alert_FX.info("YOUR SOUL IS OURS, OH I MEAN..WELCOME :)");

		openLoginWindow();
	    }
	};
    }

    private void openLoginWindow() {
	try {
	    new Login_FX().start(stage);
	} catch (Exception e) {
	    Alert_FX.error("WHERE'S THE LOGIN SCREEN?");
	}
    }
}
