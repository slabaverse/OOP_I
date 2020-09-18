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
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class NewUser_FX extends Application {

    private Stage stage;
    private Pane pane;
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
	stage.setTitle("REGISTER A NEW USER");
	stage.setResizable(false);
	stage.show();
    }

    private void initComponentes() {
	txtUser = new TextField();
	txtUser.setPromptText("USERNAME: ");

	txtPassword1 = new PasswordField();
	txtPassword1.setPromptText("PASSWORD: ");

	txtPassword2 = new PasswordField();
	txtPassword2.setPromptText("CONFIORM YOUR PASSWORD: ");

	txtName = new TextField();
	txtName.setPromptText("NAME: ");

	txtBirthdate = new TextField();
	txtBirthdate.setPromptText("BIRTHDATE: ");

	txtRelationship = new TextField();
	txtRelationship.setPromptText("RELATIONSHIP STATUS: ");

	btnCreate = new Button("REGISTER");
	btnCreate.setOnAction(register());

	btnBack = new Button("BACK");
	btnBack.setOnAction(back());

	pane = new AnchorPane();
	pane.getChildren().addAll(txtUser, txtName, txtPassword1, txtPassword2, txtBirthdate, btnCreate, btnBack);

    }

    private void configLayout() {
	pane.setPrefSize(320, 245);

	txtUser.setLayoutX(10);
	txtUser.setLayoutY(10);
	txtUser.setPrefHeight(30);
	txtUser.setPrefWidth(pane.getPrefWidth() - 20);

	txtName.setLayoutX(10);
	txtName.setLayoutY(50);
	txtName.setPrefHeight(30);
	txtName.setPrefWidth(pane.getPrefWidth() - 20);

	txtPassword1.setLayoutX(10);
	txtPassword1.setLayoutY(90);
	txtPassword1.setPrefHeight(30);
	txtPassword1.setPrefWidth(pane.getPrefWidth() - 20);

	txtPassword2.setLayoutX(10);
	txtPassword2.setLayoutY(130);
	txtPassword2.setPrefHeight(30);
	txtPassword2.setPrefWidth(pane.getPrefWidth() - 20);

	txtBirthdate.setLayoutX(10);
	txtBirthdate.setLayoutY(170);
	txtBirthdate.setPrefHeight(30);
	txtBirthdate.setPrefWidth(pane.getPrefWidth() - 20);

	btnCreate.setLayoutX(10);
	btnCreate.setLayoutY(210);
	btnCreate.setPrefHeight(20);
	btnCreate.setPrefWidth((pane.getPrefWidth() - 30) / 2);

	btnBack.setLayoutX(btnCreate.getPrefWidth() + 20);
	btnBack.setLayoutY(210);
	btnBack.setPrefHeight(20);
	btnBack.setPrefWidth((pane.getPrefWidth() - 30) / 2);
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
		    Alert_FX.alert("INFORM YOUR USERNAME");
		    return;
		}
		if (txtName.getText().isBlank()) {
		    Alert_FX.alert("INFORM YOUR NAME");
		    return;
		}
		if (txtPassword1.getText().isBlank()) {
		    Alert_FX.alert("CREATE A PASSWORD");
		    return;
		}
		if (txtPassword2.getText().isBlank()) {
		    Alert_FX.alert("WRITE YOUR PASSWORD AGAIN");
		    return;
		}
		if (!txtPassword1.getText().contentEquals(txtPassword2.getText())) {
		    Alert_FX.alert("DIFFERENT PASSWORDS");
		    return;
		}
		if (txtBirthdate.getText().isBlank()) {
		    Alert_FX.alert("INFORM YOUR BIRTHDATE");
		    return;
		}
		if (txtRelationship.getText().isBlank()) {
		    Alert_FX.alert("INFORM YOUR RELATIONSHIP STATUS");
		    return;
		}

		new UserDAO().add(new User(txtUser.getText(), txtName.getText(), txtPassword1.getText(),
			txtBirthdate.getText(), txtRelationship.getText()));

		Alert_FX.info("USER SUCCESSFULLY ADDED)");

		openLoginWindow();
	    }
	};
    }

    private void openLoginWindow() {
	try {
	    new Login_FX().start(stage);
	} catch (Exception e) {
	    Alert_FX.error("IMPOSSIBLE TO INICIATE LOGIN SCREEN");
	}
    }
}
