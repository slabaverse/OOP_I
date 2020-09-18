package UI_FX;

import dataBase.UserDAO;
import entities.User;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Login_FX extends Application {

    private Stage stage;
    private Label lblSkynet;
    private TextField txtUser;
    private PasswordField txtPassword;
    private Button btnLogin;
    private Button btnLogout;
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
	stage.setTitle("Steam login");
	stage.setResizable(false);
	stage.show();
    }

    private void initComponentes() {
	lblSkynet = new Label("SKYNET (/)");

	txtUser = new TextField();
	txtUser.setPromptText("USERNAME");

	txtPassword = new PasswordField();
	txtPassword.setPromptText("PASSWORD");

	btnLogin = new Button("LOGIN");
	btnLogin.setOnAction(login());

	btnLogout = new Button("EXIT");
	btnLogout.setOnAction(exit());

	btnRegister = new Button("NEW USER");
	btnRegister.setOnAction(openRegisterScreen());

	pane = new AnchorPane();

	pane.getChildren().add(lblSkynet);
	pane.getChildren().addAll(txtUser, txtPassword, btnLogin, btnLogout, btnRegister);
    }

    private void configLayout() {
	pane.setPrefSize(320, 180);

	lblSkynet.setLayoutX(10);
	lblSkynet.setLayoutY(10);

	txtUser.setLayoutX(10);
	txtUser.setLayoutY(35);
	txtUser.setPrefHeight(30);
	txtUser.setPrefWidth(pane.getPrefWidth() - 20);

	txtPassword.setLayoutX(10);
	txtPassword.setLayoutY(75);
	txtPassword.setPrefHeight(30);
	txtPassword.setPrefWidth(pane.getPrefWidth() - 20);

	btnLogin.setLayoutX(10);
	btnLogin.setLayoutY(115);
	btnLogin.setPrefHeight(20);
	btnLogin.setPrefWidth((pane.getPrefWidth() - 30) / 2);

	btnLogout.setLayoutX(btnLogin.getPrefWidth() + 20);
	btnLogout.setLayoutY(115);
	btnLogout.setPrefHeight(20);
	btnLogout.setPrefWidth((pane.getPrefWidth() - 30) / 2);

	btnRegister.setLayoutX(10);
	btnRegister.setLayoutY(145);
	btnRegister.setPrefHeight(20);
	btnRegister.setPrefWidth(pane.getPrefWidth() - 20);
    }

    private EventHandler<ActionEvent> login() {
	return new EventHandler<ActionEvent>() {
	    @Override
	    public void handle(ActionEvent event) {
		try {
		    if (txtUser.getText().isBlank()) {
			Alert_FX.alert("INFORM USERNAME");
			return;
		    }
		    if (txtPassword.getText().isBlank()) {
			Alert_FX.alert("INFORM PASSWORD");
			return;
		    }

		    User user_DB = new UserDAO().getByName(txtUser.getText());

		    if (user_DB == null) {
			Alert_FX.alert("INVALID USERNAME OR PASSWORD");
			return;
		    }

		    if (!user_DB.getPassword().contentEquals(txtPassword.getText())) {
			Alert_FX.alert("INVALID USERNAME OR PASSWORD");
			return;
		    }

		    new Main_FX(txtUser.getText()).start(stage);
		} catch (Exception e) {
		    Alert_FX.error("MAIN SCREEN NOT AVALIABLE");
		}
	    }
	};
    }

    private EventHandler<ActionEvent> exit() {
	return new EventHandler<ActionEvent>() {
	    @Override
	    public void handle(ActionEvent event) {
		Platform.exit();
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
		    Alert_FX.error("IMPOSSIBLE TO SHOW REGISTER SCREEN");
		}
	    }
	};
    }
}
