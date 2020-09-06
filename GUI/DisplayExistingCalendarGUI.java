
package GUI;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import DataBase.DataBase;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class DisplayExistingCalendarGUI implements EventHandler<ActionEvent> {

	private Stage primaryStage;
	private Button next, previous, homeBtn, btnBack, deleteBtn;
	private Label titlelbl;
	private Styling styling = new Styling();

	private CalendarView calendarView;

	private String semester;

	public DisplayExistingCalendarGUI(Stage primaryStage, String asemester) {

		this.primaryStage = primaryStage;
		this.semester = asemester;

		Screen();
	}

	private void Screen() {

		homeBtn = new Button("Home");
		homeBtn.setOnAction(this);
		btnBack = new Button("Back");
		btnBack.setOnAction(this);
		deleteBtn = new Button("Delete Inlay");
		deleteBtn.setOnAction(this);
		styling.Btnstyling(btnBack);
		styling.Btnstyling(homeBtn);

		String errors = "";
		if (Inlay.rank > 0) {
			errors = "errors - " + Inlay.rank;
		} else {
			errors = "no errors!";
		}

		titlelbl = new Label(Inlay.Name + " - " + errors);

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

		root.setLayoutX(140);
		root.setLayoutY(70);

		AnchorPane.setRightAnchor(titlelbl, 400d);
		AnchorPane.setTopAnchor(titlelbl, 1d);

		AnchorPane.setLeftAnchor(btnBack, 20d);
		AnchorPane.setTopAnchor(btnBack, 20d);

		AnchorPane.setRightAnchor(homeBtn, 20d);
		AnchorPane.setTopAnchor(homeBtn, 20d);

		AnchorPane.setRightAnchor(deleteBtn, 15d);
		AnchorPane.setBottomAnchor(deleteBtn, 20d);

		AnchorPane anchor = new AnchorPane();
		anchor.getChildren().addAll(titlelbl, root, homeBtn, btnBack, deleteBtn);
		anchor.setBackground(background);

		styling.AnchorStyling(anchor);
		styling.lblTitleStyling(titlelbl);
		styling.Btnstyling(homeBtn);
		styling.Btnstyling(deleteBtn);
		styling.borderStyling(root);

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
		
		if (event.getSource() == btnBack) {
			Inlay.setallNull();
			new ExisitingBoardGUI(primaryStage);
		}
		if (event.getSource() == homeBtn) {
			Inlay.setallNull();
			goToHome();
		}
		if (event.getSource() == deleteBtn) {
			alertConfirmDelete();

		}
	}

	private void alertConfirmDelete() {
		DataBase db = new DataBase();
		Alert alert = new Alert(Alert.AlertType.WARNING);
		alert.setTitle("Reset System");
		alert.setContentText("Are you sure?");
		ButtonType okButton = new ButtonType("Yes", ButtonBar.ButtonData.YES);
		ButtonType noButton = new ButtonType("No", ButtonBar.ButtonData.NO);
		alert.getButtonTypes().setAll(okButton, noButton);
		alert.showAndWait().ifPresent(type -> {
			if (type == okButton) {
				db.deleteSpecificTable(semester, Inlay.Name);
				Inlay.setallNull();
				new ExisitingBoardGUI(primaryStage);
			}
		});

	}

	private void goToHome() {
		try {
			ExamSchedulerGUI restart = new ExamSchedulerGUI();
			restart.start(primaryStage);
		} catch (Exception e) {
			Logger.getLogger(DisplayExistingCalendarGUI.class.getName()).log(Level.SEVERE, null, e);
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
			for (int j = 1; j < 6; j++) {
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
}
