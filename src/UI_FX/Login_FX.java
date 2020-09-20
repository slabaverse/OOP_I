package UI_FX;

import java.util.List;

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

public class Login_FX extends Application {

    private Stage stage;
    private ImageView logo;
    private TextField txtUser;
    private PasswordField txtPassword;
    private Button btnLogin;
    private Button btnRegister;
    private Pane pane;

    @Override
    public void start(Stage stage) throws Exception {

	this.stage = stage;
	initComponentes();
	configLayout();

	Scene scene = new Scene(pane);
	btnLogin.requestFocus();

	stage.setScene(scene);
	stage.getIcons().add(new Image("/img/logo.png"));
	stage.setTitle("Skynet Login");
	stage.setResizable(false);
	stage.show();
    }

    private void initComponentes() {

	Image image = new Image("/img/logo.png");
	logo = new ImageView(image);

	txtUser = new TextField();
	txtUser.setPromptText("USERNAME");
	txtUser.styleProperty().set(
		"-fx-text-fill: #778899; -fx-border-color: #4169E1; -fx-border-radius: 0; -fx-background-color: #F8F8FF;");

	txtPassword = new PasswordField();
	txtPassword.setPromptText("PASSWORD");
	txtPassword.styleProperty().set(
		"-fx-text-fill: #778899; -fx-border-color: #4169E1; -fx-border-radius: 0; -fx-background-color: #F8F8FF;");

	btnLogin = new Button("LOGIN");
	btnLogin.styleProperty().set(
		"-fx-text-fill: #FFFFFF; -fx-border-color: #4169E1; -fx-border-radius: 0; -fx-background-color: #4169E1;");
	btnLogin.setOnAction(login());

	btnRegister = new Button("NEW USER");
	btnRegister.styleProperty().set(
		"-fx-text-fill: #FFFFFF; -fx-border-color: #4169E1; -fx-border-radius: 0; -fx-background-color: #4169E1;");
	btnRegister.setOnAction(openRegisterScreen());

	pane = new AnchorPane();
	pane.styleProperty().set("-fx-background-color: #FFFFFF");

	pane.getChildren().addAll(logo, txtUser, txtPassword, btnLogin, btnRegister);
    }

    private void configLayout() {
	pane.setPrefSize(800, 500);

	logo.setLayoutX(0);
	logo.setLayoutY(0);
	logo.setFitHeight(500);
	logo.setFitWidth(800);

	txtUser.setLayoutX(22);
	txtUser.setLayoutY(165);
	txtUser.setPrefHeight(50);
	txtUser.setPrefWidth(pane.getPrefWidth() - 500);

	txtPassword.setLayoutX(22);
	txtPassword.setLayoutY(225);
	txtPassword.setPrefHeight(50);
	txtPassword.setPrefWidth(pane.getPrefWidth() - 500);

	btnLogin.setLayoutX(22);
	btnLogin.setLayoutY(295);
	btnLogin.setPrefHeight(45);
	btnLogin.setPrefWidth((pane.getPrefWidth() - 500));

	btnRegister.setLayoutX(22);
	btnRegister.setLayoutY(355);
	btnRegister.setPrefHeight(45);
	btnRegister.setPrefWidth(pane.getPrefWidth() - 500);
    }

    private EventHandler<ActionEvent> login() {
	return new EventHandler<ActionEvent>() {
	    @Override
	    public void handle(ActionEvent event) {
		try {
		    if (txtUser.getText().isBlank()) {
			Alert_FX.alert("WOW, YOU FORGOT YOUR USERNAME");
			return;
		    }
		    if (txtPassword.getText().isBlank()) {
			Alert_FX.alert("WITHOUT PASSWORD? TRY AGAIN BUDY");
			return;
		    }

		    List<User> user_DB = new UserDAO().getByName2(txtUser.getText());

		    if (user_DB.get(0) == null) {
			Alert_FX.alert("I THINK THAT SOMETHING'S NOT RIGHT");
			return;
		    }

		    if (!user_DB.get(0).getPassword().contentEquals(txtPassword.getText())) {
			Alert_FX.alert("I THINK THAT SOMETHING'S NOT RIGHT");
			return;
		    }

		    new Main_FX(txtUser.getText()).start(stage);
		} catch (Exception e) {
		    Alert_FX.error("SORRY, MAIN SCREEN NOT AVALIABLE");
		}
	    }
	};
    }

    private EventHandler<ActionEvent> openRegisterScreen() {
	return new EventHandler<ActionEvent>() {
	    @Override
	    public void handle(ActionEvent event) {
		try {
		    new NewUser_FX().start(stage);
		} catch (Exception e) {
		    Alert_FX.error("WHERE'S THE REGISTRATION SCREEN?");
		}
	    }
	};
    }
}
