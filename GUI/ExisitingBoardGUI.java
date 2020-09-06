
package GUI;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import DataBase.DataBase;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class ExisitingBoardGUI implements EventHandler<ActionEvent> {

	private Stage primaryStage;
	private Styling styling = new Styling();
	private Button backBtn;

	private ComboBox<String> boxA, boxB, boxSummer;
	private Label titlelbl;
	private DataBase dataBase = new DataBase();

	public ExisitingBoardGUI(Stage primaryStage) {

		this.primaryStage = primaryStage;
		Screen();
	}

	private void Screen() {
		titlelbl = new Label("Existing Inlays In The System");

		backBtn = new Button("Back");
		backBtn.setOnAction(this);

		

		boxA = new ComboBox<>();
		boxA.setPromptText("Semester A");

		boxA.setOnAction(this);
		boxB = new ComboBox<>();
		boxB.setPromptText("Semester B");
		boxB.setOnAction(this);
		boxSummer = new ComboBox<>();
		boxSummer.setPromptText("Summer Semester");
		boxSummer.setOnAction(this);
		HBox hb = new HBox(boxA, boxB, boxSummer);

		styling.comboStyling(boxA);
		styling.comboStyling(boxB);
		styling.comboStyling(boxSummer);
		styling.Btnstyling(backBtn);
		styling.lblTitleStyling(titlelbl);
		

		AnchorPane anchor = new AnchorPane();
		anchor.getChildren().addAll(titlelbl, hb, backBtn);

		AnchorPane.setLeftAnchor(backBtn, 20d);
		AnchorPane.setTopAnchor(backBtn, 20d);


		AnchorPane.setRightAnchor(titlelbl, 220d);
		AnchorPane.setTopAnchor(titlelbl, 30d);

		hb.setLayoutX(340);
		hb.setLayoutY(250);
		hb.setAlignment(Pos.CENTER);
		hb.setSpacing(70);

		BackgroundFill background_fill;
		background_fill = new BackgroundFill(Color.WHITESMOKE, CornerRadii.EMPTY, Insets.EMPTY);
		Background background = new Background(background_fill);
		anchor.setBackground(background);
		styling.AnchorStyling(anchor);
		
		boxA.getItems().addAll(dataBase.searchFilesFromDataBase("a"));
		boxB.getItems().addAll(dataBase.searchFilesFromDataBase("b"));
		boxSummer.getItems().addAll(dataBase.searchFilesFromDataBase("c"));

		Scene scene = new Scene(anchor, 1200, 650);
		primaryStage.setResizable(false);
		primaryStage.setScene(scene);
		primaryStage.show();
		
		

	}

	@Override
	public void handle(ActionEvent event) {
		if (event.getSource() == backBtn) {
			try {
				ExamSchedulerGUI restart = new ExamSchedulerGUI();
				restart.start(primaryStage);
			} catch (Exception e) {
				Logger.getLogger(ExisitingBoardGUI.class.getName()).log(Level.SEVERE, null, e);
			}
		}
		
		if (event.getSource() == boxA) {
			try {
				dataBase.loadRecordFromDataBase(boxA.getValue().toString(), "a");
				new DisplayExistingCalendarGUI(primaryStage , "a");
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
		if (event.getSource() == boxB) {
			try {
				dataBase.loadRecordFromDataBase(boxB.getValue().toString(), "b");
				new DisplayExistingCalendarGUI(primaryStage , "b");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (event.getSource() == boxSummer) {
			try {
				dataBase.loadRecordFromDataBase(boxSummer.getValue().toString(), "c");
				new DisplayExistingCalendarGUI(primaryStage , "c");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	
}
