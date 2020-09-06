
package GUI;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import DataBase.DataBase;
import genetic.Allocation;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class ExamBoardGUI implements EventHandler<ActionEvent> {

	private Stage primaryStage;
	private Styling styling = new Styling();
	private Button next, previous, homeBtn, saveBtn, btnConfirm, backBtn, cancelbtn;
	private Label titlelbl;

	private CalendarView calendarView;
	private BorderPane savePane;
	private TextField txtName;
	private Label lblName;
	private Allocation allocation;

	public ExamBoardGUI(Stage primaryStage, Allocation anAllocation) {

		this.primaryStage = primaryStage;
		this.allocation = anAllocation;
		Screen();
	}

	private void Screen() {
		// Save window
		lblName = new Label("Name \n");
		txtName = new TextField();
		txtName.setPromptText("Enter Name");

		btnConfirm = new Button("   Confirm   ");
		btnConfirm.setOnAction(this);

		cancelbtn = new Button("Cancel");
		cancelbtn.setOnAction(this);

		HBox hName = new HBox(lblName, txtName);
		HBox hCancel = new HBox(cancelbtn);
		HBox hConfirm = new HBox(btnConfirm);
		VBox vBox = new VBox(hCancel, hName, hConfirm);
		hCancel.setAlignment(Pos.TOP_RIGHT);
		hConfirm.setAlignment(Pos.BOTTOM_CENTER);
		hName.setSpacing(5);
		vBox.setSpacing(7);

		savePane = new BorderPane(vBox, null, null, null, null);

		savePane.setVisible(false);
		styling.borderStylingWithBorder(savePane);
		styling.lblStyling(lblName);
		styling.Btnstyling(btnConfirm);
		styling.txtStyling(txtName);

		// main window
		String errors = "";
		if (allocation.getRank() > 0) {
			errors = "Errors - " + (int) allocation.getRank() / 2;
		} else {
			errors = "No errors!";
		}

		titlelbl = new Label(Inlay.Name + " - " + errors);
		titlelbl = new Label("Exam Board Semester " + Inlay.semester + " , " + errors);
		homeBtn = new Button("Home");
		homeBtn.setOnAction(this);

		saveBtn = new Button("  Save  ");
		saveBtn.setOnAction(this);
		backBtn = new Button("  Back  ");
		backBtn.setOnAction(this);

		next = new Button("->");
		next.setOnAction(this);
		previous = new Button("<-");
		previous.setOnAction(this);

		calendarView = new CalendarView(1.7, Integer.parseInt(Inlay.year1), Integer.parseInt(Inlay.month1));
		BorderPane root = new BorderPane(calendarView.getView(), null, next, null, previous);

		BackgroundFill background_fill;
		background_fill = new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY);
		Background background = new Background(background_fill);
		root.setBackground(background);

		root.setLayoutX(150);
		root.setLayoutY(70);

		AnchorPane.setRightAnchor(titlelbl, 200d);
		AnchorPane.setTopAnchor(titlelbl, 1d);

		AnchorPane.setRightAnchor(homeBtn, 20d);
		AnchorPane.setTopAnchor(homeBtn, 20d);
		AnchorPane.setLeftAnchor(backBtn, 20d);
		AnchorPane.setTopAnchor(backBtn, 20d);

		AnchorPane.setRightAnchor(saveBtn, 15d);
		AnchorPane.setBottomAnchor(saveBtn, 20d);

		savePane.setLayoutX(505);
		savePane.setLayoutY(275);

		AnchorPane anchor = new AnchorPane();
		anchor.getChildren().addAll(titlelbl, root, saveBtn, homeBtn, savePane, backBtn);
		anchor.setBackground(background);
		savePane.setBackground(background);

		styling.AnchorStyling(anchor);
		styling.lblTitleStyling(titlelbl);
		styling.Btnstyling(homeBtn);
		styling.borderStyling(root);
		styling.Btnstyling(saveBtn);
		styling.Btnstyling(backBtn);

		Scene scene = new Scene(anchor, 1200, 650);
		primaryStage.setResizable(false);
		primaryStage.setScene(scene);
		primaryStage.show();

		updateCross();
		previous.setDisable(true);
		if (Inlay.month1.equals(Inlay.month2)) {
			next.setDisable(true);
		}
	}

	@Override
	public void handle(ActionEvent event) {
		if (event.getSource() == backBtn) {
			new UserInputGUI(primaryStage, Inlay.semester);
		}
		if (event.getSource() == cancelbtn) {
			savePane.setVisible(false);
		}
		if (event.getSource() == homeBtn) {
			goToHome();
		}
		if (event.getSource() == next) {
			styling.gridStyling(calendarView.calendar);
			calendarView.calendar.getChildren().clear();
			next.setDisable(false);
			previous.setDisable(false);
			if (calendarView.nextMonth(1)) {
				next.setDisable(true);
				previous.setDisable(false);
			} else {
				next.setDisable(false);
			}
			updateCross();

		}
		if (event.getSource() == previous) {
			styling.gridStyling(calendarView.calendar);
			calendarView.calendar.getChildren().clear();
			previous.setDisable(false);
			next.setDisable(false);
			if (calendarView.previousMonth(-1)) {
				previous.setDisable(true);
				next.setDisable(false);
			} else {
				previous.setDisable(false);
			}
			updateCross();

		}
		
		
		if (event.getSource() == saveBtn) {
			savePane.setVisible(true);
		}
		if (event.getSource() == btnConfirm) {
			checkName();
		}

	}

	private void checkName() {
		String name = txtName.getText();
		DataBase dataBase = new DataBase();

		if (name.length() < 3) {
			Alert alert = new Alert(Alert.AlertType.WARNING);
			alert.setTitle("Name");
			alert.setHeaderText(null);
			alert.setContentText("Please Enter a valid name with minimum 3 character");
			alert.showAndWait();

		}

		else if (name.matches("[0-9]+[a-zA-Z]*")) {
			Alert alert = new Alert(Alert.AlertType.WARNING);
			alert.setTitle("Name");
			alert.setHeaderText(null);
			alert.setContentText("Tha name can't start with number, Please Enter a valid name ");
			alert.showAndWait();
		}

		else if (dataBase.checkIfTableExist(name)) {
			Alert alert = new Alert(Alert.AlertType.WARNING);
			alert.setTitle("Name");
			alert.setHeaderText(null);
			alert.setContentText("The name is already exist, please Enter new name ");
			alert.showAndWait();

		} else {
			Inlay.Name = name;
			save();
			goToHome();
		}
	}

	private void goToHome() {
		try {
			ExamSchedulerGUI restart = new ExamSchedulerGUI();
			restart.start(primaryStage);
		} catch (Exception e) {
			Logger.getLogger(ExamBoardGUI.class.getName()).log(Level.SEVERE, null, e);
		}
	}

	public void updateCross() {
		ArrayList<?> arr = null;
		Button btn[][] = null;
		TextArea txt[][] = null;
		if (calendarView.getChooser() == 1) {
			arr = Inlay.exceptionalDaysLocation1;
			btn = calendarView.btn1;
			txt = calendarView.txt1;
		}
		if (calendarView.getChooser() == 2) {
			arr = Inlay.exceptionalDaysLocation2;
			btn = calendarView.btn2;
			txt = calendarView.txt2;
		}
		if (calendarView.getChooser() == 3) {
			arr = Inlay.exceptionalDaysLocation3;
			btn = calendarView.btn3;
			txt = calendarView.txt3;
		}
		for (int i = 0; i < 7; i++) {
			for (int j = 1; j < 8; j++) {
				txt[i][j].setEditable(false);
				txt[i][j].setOpacity(1);

				if (i == 6) {
					btn[i][j].setVisible(false);
					txt[i][j].setVisible(false);
				} else {
					btn[i][j].setVisible(false);
				}

				for (int k = 0; k < arr.size(); k++) {
					String temp = i + "" + j;
					if (arr.get(k).equals(temp)) {
						btn[i][j].setVisible(true);
						btn[i][j].setOpacity(1);
						txt[i][j].setOpacity(0);
						txt[i][j].setVisible(false);
					}
				}
			}
		}
	}

	public void save() {
		DataBase dataBase = new DataBase(allocation, Inlay.date1, Inlay.month1, Inlay.year1, Inlay.date2, Inlay.month2,
				Inlay.year2, Inlay.Name, Inlay.semester, Inlay.numOfDaysFirstSecond, Inlay.numOfDaysExam,
				Inlay.constraintsFilePath);

		dataBase.saveResult();

	}
}
