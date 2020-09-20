package UI_FX;

import entities.GameEvents;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class EditEvents_FX extends Application {
    private Stage stage;
	private Label nameLbl;
	private Label birthLbl;
	private Label nationalityLbl;
	private Label sexLbl;
	private Label heightLbl;
	private TextField nameTxt;
	private TextField birthTxt;
	private TextField nationalityTxt;
	private TextField heightTxt;
	private Button saveBtn;
	private Button cancelBtn;
	private Pane pane;
	private GameEvents gameEv;
	
	public EditEvents_FX(GameEvents gameEv) {
		this.gameEv = gameEv;
	}
	
//	@Override
//	public void start(Stage stage) throws Exception {
//		this.stage = stage;
//		
//		initComponents();
//		configLayout();
//		
//		Scene scene = new Scene(pane);
//		saveBtn.requestFocus();
//		
//		stage.setScene(scene);
//		stage.getIcons().add(new Image("/img/fatflix-icon.png"));
//		stage.setTitle("Fatflix");
//		stage.setResizable(false);
//		stage.show();
//	}
//	
//	private void initComponents() {
//		nameLbl = new Label("Name");
//		nameLbl.styleProperty().set("-fx-text-fill: #FFF");
//		
//		birthLbl = new Label("Birth Date");
//		birthLbl.styleProperty().set("-fx-text-fill: #FFF");
//		
//		nationalityLbl = new Label("Nationality");
//		nationalityLbl.styleProperty().set("-fx-text-fill: #FFF");
//		
//		sexLbl = new Label("Sex");
//		sexLbl.styleProperty().set("-fx-text-fill: #FFF");
//		
//		heightLbl = new Label("Height");
//		heightLbl.styleProperty().set("-fx-text-fill: #FFF");
//		
//		nameTxt = new TextField();
//		nameTxt.setText(actor.getName());
//		nameTxt.setPromptText("Type the actor's name");
//		nameTxt.styleProperty().set("-fx-text-fill: #32FF6B; -fx-border-color: #32FF6B; -fx-border-radius: 5; -fx-background-color: #3E3E3E;");
//		
//		birthTxt = new TextField();
//		birthTxt.setText(actor.getBirthDate());
//		birthTxt.setPromptText("Type the actor's birth date");
//		birthTxt.styleProperty().set("-fx-text-fill: #32FF6B; -fx-border-color: #32FF6B; -fx-border-radius: 5; -fx-background-color: #3E3E3E;");
//		
//		nationalityTxt = new TextField();
//		nationalityTxt.setText(actor.getNationality());
//		nationalityTxt.setPromptText("Type the actor's nationality");
//		nationalityTxt.styleProperty().set("-fx-text-fill: #32FF6B; -fx-border-color: #32FF6B; -fx-border-radius: 5; -fx-background-color: #3E3E3E;");
//		
//		heightTxt = new TextField();
//		heightTxt.setText(String.valueOf(actor.getHeight()));
//		heightTxt.setPromptText("Enter the actor's height");
//		heightTxt.styleProperty().set("-fx-text-fill: #32FF6B; -fx-border-color: #32FF6B; -fx-border-radius: 5; -fx-background-color: #3E3E3E;");
//		
//		maleRadio = new RadioButton("Male");
//		maleRadio.setToggleGroup(sexGroup);
//		maleRadio.setOnAction(setMale());
//		maleRadio.styleProperty().set("-fx-text-fill: #FFF");
//		
//		femaleRadio = new RadioButton("Female");
//		femaleRadio.setToggleGroup(sexGroup);
//		femaleRadio.setOnAction(setFemale());
//		femaleRadio.styleProperty().set("-fx-text-fill: #FFF");
//		
//		if (actor.getSex().contentEquals("M")) {
//			maleRadio.setSelected(true);
//		} else if (actor.getSex().contentEquals("F")) {
//			femaleRadio.setSelected(true);
//		}
//		
//		saveBtn = new Button("Save");
//		saveBtn.setOnAction(save());
//		saveBtn.styleProperty().set("-fx-text-fill: #3E3E3E; -fx-border-color: #32FF6B; -fx-border-radius: 5; -fx-background-color: #32FF6B;");
//		
//		cancelBtn = new Button("Cancel");
//		cancelBtn.setOnAction(cancel());
//		cancelBtn.styleProperty().set("-fx-text-fill: #32FF6B; -fx-border-color: #32FF6B; -fx-border-radius: 5; -fx-background-color: #3E3E3E;");
//		
//		pane = new AnchorPane();
//		pane.styleProperty().set("-fx-background-color: #3E3E3E");
//		
//		pane.getChildren().addAll(nameLbl, birthLbl, nationalityLbl, sexLbl, heightLbl, nameTxt, birthTxt, nationalityTxt, heightTxt, 
//				maleRadio, femaleRadio, saveBtn, cancelBtn);
//	}
//	
//	private void configLayout() {
//		pane.setPrefSize(400, 260);
//		
//		nameLbl.setLayoutX(20);
//		nameLbl.setLayoutY(20);
//		
//		nameTxt.setLayoutX(20);
//		nameTxt.setLayoutY(40);
//		nameTxt.setPrefHeight(20);
//		nameTxt.setPrefWidth(pane.getPrefWidth() - 40);
//		
//		birthLbl.setLayoutX(20);
//		birthLbl.setLayoutY(80);
//		
//		birthTxt.setLayoutX(20);
//		birthTxt.setLayoutY(100);
//		birthTxt.setPrefHeight(20);
//		birthTxt.setPrefWidth((pane.getPrefWidth() - 60) / 2);
//		
//		nationalityLbl.setLayoutX(210);
//		nationalityLbl.setLayoutY(80);
//		
//		nationalityTxt.setLayoutX(210);
//		nationalityTxt.setLayoutY(100);
//		nationalityTxt.setPrefHeight(20);
//		nationalityTxt.setPrefWidth((pane.getPrefWidth() - 60) / 2);
//		
//		heightLbl.setLayoutX(20);
//		heightLbl.setLayoutY(140);
//		
//		heightTxt.setLayoutX(20);
//		heightTxt.setLayoutY(160);
//		heightTxt.setPrefHeight(20);
//		heightTxt.setPrefWidth((pane.getPrefWidth() - 60) / 2);
//		
//		sexLbl.setLayoutX(210);
//		sexLbl.setLayoutY(140);
//		
//		maleRadio.setLayoutX(210);
//		maleRadio.setLayoutY(163);
//		maleRadio.setPrefHeight(20);
//		maleRadio.setPrefWidth((pane.getPrefWidth() - 60) / 4);
//		
//		femaleRadio.setLayoutX(305);
//		femaleRadio.setLayoutY(163);
//		femaleRadio.setPrefHeight(20);
//		femaleRadio.setPrefWidth((pane.getPrefWidth() - 60) / 4);
//		
//		saveBtn.setLayoutX(20);
//		saveBtn.setLayoutY(210);
//		saveBtn.setPrefHeight(20);
//		saveBtn.setPrefWidth((pane.getPrefWidth() - 60) / 2);
//		
//		cancelBtn.setLayoutX(210);
//		cancelBtn.setLayoutY(210);
//		cancelBtn.setPrefHeight(20);
//		cancelBtn.setPrefWidth((pane.getPrefWidth() - 60) / 2);
//		
//	}
//	
//	private EventHandler<ActionEvent> setMale() {
//		return new EventHandler<ActionEvent>() {
//			@Override
//			public void handle(ActionEvent event) {
//				if (femaleRadio.isSelected()) {
//					femaleRadio.setSelected(false);
//				}
//				maleRadio.setSelected(true);
//			}
//		};
//	}
//	
//	private EventHandler<ActionEvent> setFemale() {
//		return new EventHandler<ActionEvent>() {
//			@Override
//			public void handle(ActionEvent event) {
//				if (maleRadio.isSelected()) {
//					maleRadio.setSelected(false);
//				}
//				femaleRadio.setSelected(true);
//			}
//		};
//	}
//	
//	private EventHandler<ActionEvent> save() {
//		return new EventHandler<ActionEvent>() {
//			@Override
//			public void handle(ActionEvent event) {
//				try {
//					if (nameTxt.getText().isEmpty()) {
//						Alert_FX.alert("Name field is empty");
//						return;
//					}
//					if (birthTxt.getText().isEmpty()) {
//						Alert_FX.alert("Birth date field is empty");
//						return;
//					}
//					if (nationalityTxt.getText().isEmpty()) {
//						Alert_FX.alert("Nationality field is empty");
//						return;
//					}
//					if (heightTxt.getText().isEmpty()) {
//						Alert_FX.alert("Height field is empty");
//						return;
//					} else if (heightTxt.getText().contains(",")) {
//						Alert_FX.alert("Please use '.' instead of ',' for the height");
//						return;
//					}
//					if (!maleRadio.isSelected() && !femaleRadio.isSelected()) {
//						Alert_FX.alert("Selecet a sex for the actor");
//						return;
//					}
//					
//					String sex = "";
//					
//					if (maleRadio.isSelected()) {
//						sex = "M";
//					} else if (femaleRadio.isSelected()) {
//						sex = "F";
//					}
//					
//					new ProtagonistDAO().add(
//							new Protagonist(nameTxt.getText(), birthTxt.getText(), nationalityTxt.getText(), sex, Float.valueOf(heightTxt.getText())));
//					
//					openActorWindow();
//				} catch (Exception e) {
//					Alert_FX.error("Inable to save the actor!");
//				}
//			}
//		};
//	}
//
//	private void openActorWindow() {
//		try {
//			new ActorFX().start(stage);
//		} catch (Exception e) {
//			Alert_FX.error("Inable to open the actor window");
//		}
//	}
//	
//	private EventHandler<ActionEvent> cancel() {
//		return new EventHandler<ActionEvent>() {
//			@Override
//			public void handle(ActionEvent event) {
//				try {
//					new ActorFX().start(stage);
//				} catch (Exception e) {
//					Alert_FX.error("Inable to open the actor's window!");
//				}
//			}
//		};
//	}

}
