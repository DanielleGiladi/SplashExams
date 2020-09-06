package GUI;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import DataBase.DataBase;
import GUI.Styling;

public class ExamSchedulerGUI extends Application implements EventHandler<ActionEvent> {
	
	private Stage primaryStage;
	private Styling styling = new Styling();
	private Button newBoardBtn, existingBoardsBtn , resetBtn;
	private Label titlelbl;
	private DataBase dataBase = new DataBase();

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) {

		this.primaryStage = primaryStage;

		BackgroundFill background_fill;
		background_fill = new BackgroundFill(Color.WHITESMOKE, CornerRadii.EMPTY, Insets.EMPTY);

		titlelbl = new Label("Exam Scheduler System");
		styling.lblTitleStyling(titlelbl);

		newBoardBtn = new Button("Create New \nExam Board ");
		newBoardBtn.setOnAction(this);
		existingBoardsBtn = new Button("View Existing \nExam Boards");
		existingBoardsBtn.setOnAction(this);
		resetBtn = new Button("Reset System");
		resetBtn.setOnAction(this);

		styling.MainBtnstyling(newBoardBtn);
		styling.MainBtnstyling(existingBoardsBtn);
		styling.MainBtnstylingReset(resetBtn);
		resetBtn.setTextFill(Color.web("#8B0000"));
		Background background = new Background(background_fill);
		HBox hBox = new HBox(newBoardBtn, existingBoardsBtn , resetBtn);
		hBox.setAlignment(Pos.CENTER);
		hBox.setBackground(background);
		hBox.setSpacing(50);

		VBox vBox = new VBox(titlelbl, hBox);
		vBox.setAlignment(Pos.CENTER);
		vBox.setSpacing(50);
		styling.vboxStyling(vBox);
		
		

		Scene scene = new Scene(vBox, 1200, 650);

		primaryStage.setResizable(false);
		this.primaryStage.setScene(scene);
		this.primaryStage.show();

		
	}

	@Override
	public void handle(ActionEvent event) {
		if (event.getSource() == newBoardBtn) {
			new NewExamBoardGUI(primaryStage);
		}
		if (event.getSource() == existingBoardsBtn) {
			new ExisitingBoardGUI(primaryStage);

		}
		if (event.getSource() == resetBtn) {
			alertConfirmReset();

		}
		

	}
	
	private void alertConfirmReset() {
		Alert alert = new Alert(Alert.AlertType.WARNING);
		alert.setTitle("Reset System");
		alert.setContentText("Are you sure?");
		ButtonType okButton = new ButtonType("Yes", ButtonBar.ButtonData.YES);
		ButtonType noButton = new ButtonType("No", ButtonBar.ButtonData.NO);
		alert.getButtonTypes().setAll(okButton, noButton);
		alert.showAndWait().ifPresent(type -> {
		        if (type == okButton) {
		        	dataBase.resetSystem();
		        } 
		});
		
	}

}
