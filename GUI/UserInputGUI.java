
package GUI;

import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import genetic.CalendarDay;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class UserInputGUI implements EventHandler<ActionEvent> {
	private Stage primaryStage;
	private Styling styling = new Styling();
	private Button homeBtn, createNewInlayBtn, backBtn;
	private Label titlelbl;
	private Label lblStartDate, lblEndDate, lblOccupancy, lblException, lblNumberOfDays, lblFirstAndSecond,
			lblFileExams, lblFileConstraints;
	private TextField txtStartDate, txtEndDate, txtOccupancy, txtException, txtNoOfDays, txtFirstAndSecond,
			txtFileExams, txtFileConstraints;
	private Button browseBtn, browseConstraintsBtn;

	private Button helpBtn, confirmBtn, next, previous;
	private Label titleClbl;
	private HBox hConfirm;

	private CalendarView calendarView;
	private Pane pane;
	private HBox hBoxCV;
	private VBox vCalander;
	private Button closeBtn;

	private AnchorPane anchor;
	private BorderPane root;
	private ErrorChecker errorChecker = new ErrorChecker();

	private Inlay inlay;
	private Label totalNumOfDaysLbl;
	private Button okBtn, cancelBtn;
	private VBox totalDayBox;
	private CalendarDay calendarDay;

	public UserInputGUI(Stage primaryStage, String semester) {
		Inlay.semester = semester;
		this.primaryStage = primaryStage;
		Screen();
	}

	private void Screen() {

		titlelbl = new Label("New Exam Inlay");

		homeBtn = new Button("Home");
		homeBtn.setOnAction(this);

		createNewInlayBtn = new Button("Create New Inlay");
		createNewInlayBtn.setOnAction(this);
		createNewInlayBtn.setDisable(false);
		lblStartDate = new Label("Exam period start date");
		txtStartDate = new TextField();
		txtStartDate.setPromptText("dd-MM-YYYY");

		lblEndDate = new Label("Exam period end date");
		txtEndDate = new TextField();
		txtEndDate.setPromptText("dd-MM-YYYY");

		lblOccupancy = new Label("Occupancy");
		txtOccupancy = new TextField();
		txtOccupancy.setPromptText("0-800");

		lblException = new Label("Exception");
		txtException = new TextField();
		txtException.setPromptText("0-500");

		lblNumberOfDays = new Label("Number of days between exams");
		txtNoOfDays = new TextField();
		txtNoOfDays.setPromptText("1-5");

		lblFirstAndSecond = new Label("Number of days between exam periods");
		txtFirstAndSecond = new TextField();
		txtFirstAndSecond.setPromptText("10-15");

		lblFileExams = new Label("File exams data");
		txtFileExams = new TextField();
		txtFileExams.setPromptText("Select path of exams file ");
		browseBtn = new Button("      Browse exams file     ");
		browseBtn.setOnAction(this);
		lblFileConstraints = new Label("File constraints");
		txtFileConstraints = new TextField();
		txtFileConstraints.setPromptText("Select path of constraints file ");
		browseConstraintsBtn = new Button("  Browse constraints file ");
		browseConstraintsBtn.setOnAction(this);

		styling.setFont(lblStartDate);
		styling.setFont(lblEndDate);
		styling.setFont(lblOccupancy);
		styling.setFont(lblException);
		styling.setFont(lblNumberOfDays);
		styling.setFont(lblFirstAndSecond);
		styling.setFont(lblFileExams);
		styling.setFont(lblFileConstraints);

		HBox fileExam = new HBox(txtFileExams, browseBtn);
		fileExam.setSpacing(1);
		HBox fileGrammer = new HBox(txtFileConstraints, browseConstraintsBtn);
		fileExam.setSpacing(1);

		VBox vboxLbls = new VBox(lblStartDate, lblEndDate, lblOccupancy, lblException, lblNumberOfDays,
				lblFirstAndSecond, lblFileExams, lblFileConstraints);
		VBox vboxFields = new VBox(txtStartDate, txtEndDate, txtOccupancy, txtException, txtNoOfDays, txtFirstAndSecond,
				fileExam, fileGrammer);

		vboxLbls.setSpacing(18);
		vboxFields.setSpacing(11);

		HBox hFields = new HBox(vboxLbls, vboxFields);
		hFields.setSpacing(100);

		// Exceptional days window
		helpBtn = new Button(" Help ");
		helpBtn.setOnAction(this);
		closeBtn = new Button("Close PopUp");
		closeBtn.setOnAction(this);
		HBox boxbtns = new HBox(helpBtn, closeBtn);
		boxbtns.setSpacing(397);
		titleClbl = new Label("\t\tPlease mark the exceptional days\n\t\t    when exams cannot be taken");
		confirmBtn = new Button("  Confirm  ");
		confirmBtn.setOnAction(this);
		hConfirm = new HBox(confirmBtn);
		hConfirm.setAlignment(Pos.CENTER_RIGHT);

		next = new Button(" -> ");
		next.setOnAction(this);

		previous = new Button(" <- ");
		previous.setOnAction(this);

		backBtn = new Button("  Back  ");
		backBtn.setOnAction(this);

		vCalander = new VBox(boxbtns, titleClbl);
		vCalander.setVisible(false);
		vCalander.setSpacing(5);

		// main windows
		anchor = new AnchorPane();
		anchor.getChildren().addAll(backBtn, titlelbl, homeBtn, createNewInlayBtn, hFields);

		AnchorPane.setRightAnchor(titlelbl, 400d);
		AnchorPane.setTopAnchor(titlelbl, 25d);

		AnchorPane.setRightAnchor(homeBtn, 20d);
		AnchorPane.setTopAnchor(homeBtn, 20d);

		AnchorPane.setRightAnchor(createNewInlayBtn, 480d);
		AnchorPane.setBottomAnchor(createNewInlayBtn, 20d);

		AnchorPane.setLeftAnchor(backBtn, 20d);
		AnchorPane.setTopAnchor(backBtn, 20d);

		hFields.setLayoutX(260);
		hFields.setLayoutY(180);

		vCalander.setLayoutX(350);
		vCalander.setLayoutY(0);

		BackgroundFill background_fill;
		background_fill = new BackgroundFill(Color.WHITESMOKE, CornerRadii.EMPTY, Insets.EMPTY);
		Background background = new Background(background_fill);
		anchor.setBackground(background);
		vCalander.setBackground(background);

		styling.AnchorStyling(anchor);
		styling.lblTitleStyling(titlelbl);
		styling.Btnstyling(homeBtn);
		styling.MainBtnstyling(createNewInlayBtn);
		styling.txtStyling(txtStartDate);
		styling.txtStyling(txtEndDate);
		styling.txtStyling(txtOccupancy);
		styling.txtStyling(txtException);
		styling.txtStyling(txtNoOfDays);
		styling.txtStyling(txtFirstAndSecond);
		styling.setFontMainTitle(titleClbl);
		styling.Btnstyling(backBtn);
		styling.txtStyling(txtFileExams);
		styling.btnStylingForInput(browseBtn);
		styling.txtStyling(txtFileConstraints);
		styling.btnStylingForInput(browseConstraintsBtn);

		if (Inlay.flagUserInput) {
			setSavedDetails();
		}

		okBtn = new Button("OK");
		okBtn.setOnAction(this);
		cancelBtn = new Button("Cancel");
		cancelBtn.setOnAction(this);

		Scene scene = new Scene(anchor, 1200, 650);
		primaryStage.setResizable(false);
		primaryStage.setScene(scene);
		primaryStage.show();

		closePopUp();
	}

	@Override
	public void handle(ActionEvent event) {
		if (event.getSource() == backBtn) {
			new NewExamBoardGUI(primaryStage);
		}
		if (event.getSource() == homeBtn) {
			try {
				ExamSchedulerGUI restart = new ExamSchedulerGUI();
				restart.start(primaryStage);
			} catch (Exception e) {
				Logger.getLogger(UserInputGUI.class.getName()).log(Level.SEVERE, null, e);
			}
		}
		if (event.getSource() == createNewInlayBtn) {
			methodCreateNewInlay();

		}
		if (event.getSource() == next) {
			calendarView.calendar.getChildren().clear();
			next.setDisable(false);
			previous.setDisable(false);
			if (calendarView.nextMonth(1)) {

				next.setDisable(true);
				previous.setDisable(false);
			} else {
				next.setDisable(false);
			}
			settingAction();
		}
		if (event.getSource() == previous) {
			calendarView.calendar.getChildren().clear();
			previous.setDisable(false);
			next.setDisable(false);
			if (calendarView.previousMonth(-1)) {
				previous.setDisable(true);
				next.setDisable(false);
			} else {
				previous.setDisable(false);
			}
			settingAction();
		}
		if (event.getSource() == previous || event.getSource() == next) {
			styling.gridStyling(calendarView.calendar);

		}
		if (event.getSource() == confirmBtn) {
			calendarDay = createCalenderDay();
		}
		if (event.getSource() == okBtn) {
			new WaitingWindow(primaryStage, calendarDay);
		}

		if (event.getSource() == cancelBtn) {
			closeTotalDays();
		}

		if (event.getSource() == helpBtn) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Exceptional Days");
			alert.setHeaderText(null);
			alert.setContentText("Please mark the exceptional days when exams cannot be taken."
					+ " Saturday is already marked in red");
			alert.showAndWait();
		}

		if (event.getSource() == closeBtn) {
			closePopUp();
		}

		if (event.getSource() == browseBtn) {
			uploadFile(0);
		}

		if (event.getSource() == browseConstraintsBtn) {
			uploadFile(1);
		}

		if (vCalander.isVisible()) {
			for (int i = 0; i < 8; i++) {
				for (int j = 1; j < 8; j++) {
					if (event.getSource() == calendarView.btn1[i][j]) {
						String temp = i + "" + j;
						if (Inlay.exceptionalDaysLocation1.contains(temp)) {
							calendarView.btn1[i][j].setOpacity(0);
							Inlay.exceptionalDaysLocation1.remove(temp);
						} else {
							Inlay.exceptionalDaysLocation1.add(temp);
							calendarView.btn1[i][j].setOpacity(1);
						}
					}
					if (event.getSource() == calendarView.btn2[i][j]) {
						String temp = i + "" + j;
						if (Inlay.exceptionalDaysLocation2.contains(temp)) {
							calendarView.btn2[i][j].setOpacity(0);
							Inlay.exceptionalDaysLocation2.remove(temp);
						} else {
							Inlay.exceptionalDaysLocation2.add(temp);
							calendarView.btn2[i][j].setOpacity(1);
						}
					}
					if (event.getSource() == calendarView.btn3[i][j]) {
						String temp = i + "" + j;
						if (Inlay.exceptionalDaysLocation3.contains(temp)) {
							calendarView.btn3[i][j].setOpacity(0);
							Inlay.exceptionalDaysLocation3.remove(temp);
						} else {
							Inlay.exceptionalDaysLocation3.add(temp);
							calendarView.btn3[i][j].setOpacity(1);
						}
					}
				}
			}
		}
	}

	private void setSavedDetails() {
		txtStartDate.setText(Inlay.startDay);
		txtEndDate.setText(Inlay.endDay);
		txtOccupancy.setText(Inlay.occupation + "");
		txtException.setText(Inlay.exception + "");
		txtNoOfDays.setText(Inlay.numOfDaysExam + "");
		txtFirstAndSecond.setText(Inlay.numOfDaysFirstSecond + "");
		txtFileExams.setText(Inlay.examsFilePath);
		txtFileConstraints.setText(Inlay.constraintsFilePath);
	}

	private void methodCreateNewInlay() {
		String startDate = txtStartDate.getText();
		String endDate = txtEndDate.getText();
		String occupancy = txtOccupancy.getText();
		String exception = txtException.getText();
		String numOfDaysBetweenExam = txtNoOfDays.getText();
		String numOfDaysBetweenPeriods = txtFirstAndSecond.getText();
		String pathExams = txtFileExams.getText();
		String pathGrammer = txtFileConstraints.getText();

		String error = errorChecker.dateChecker(startDate);
		if (error.equals("good")) {
			error = errorChecker.dateChecker(endDate);
			if (error.equals("good")) {
				error = errorChecker.compareBothDates(startDate, endDate);
				if (error.equals("good")) {
					if (errorChecker.intChecker(occupancy, 0, 800)) {
						if (errorChecker.intChecker(exception, 0, 500)) {
							if (errorChecker.intChecker(numOfDaysBetweenExam, 1, 5)) {
								if (errorChecker.intChecker(numOfDaysBetweenPeriods, 10, 15)) {
									error = errorChecker.checkFile(pathExams);
									if (error.equals("good")) {
										error = errorChecker.checkFileGrammer(pathGrammer);
										if (error.equals("good")) {
											showCalendar(startDate, endDate, occupancy, exception, numOfDaysBetweenExam,
													numOfDaysBetweenPeriods, pathExams, pathGrammer);
										} else { // file constraint
											alert("File constraints", error);
										}
									} else { // file exams
										alert("File exams", error);
									}

								} else { // numOfDaysBetweenFirstAndSecond
									alert("Num of days between periods",
											"The number of days between two periods must be integer between 10-15");
								}
							} else { // numOfDaysBetweenExam
								alert("Num of days between exams",
										"The number of days between exams must be integer between 1-5");
							}
						} else { // Exception
							alert("Exception", "Exception value must be integer between 0-500");
						}
					} else { // Occupancy
						alert("Occupancy", "Occupancy value must be integer between 0-800");
					}
				} else { // compare data start end
					alert("Date", error);
				}
			} else { // end date
				alert("Exam Period End Date", error);
			}
		} else { // start date
			alert("Exam Period Start Date", error);
		}
	}

	private void showCalendar(String startDate, String endDate, String occupancy, String exception,
			String numOfDaysBetweenExam, String numOfDaysBetweenPeriods, String pathExams, String pathGrammer) {
		inlay = new Inlay(startDate, endDate, Integer.parseInt(occupancy), Integer.parseInt(exception),
				Integer.parseInt(numOfDaysBetweenExam), Integer.parseInt(numOfDaysBetweenPeriods), pathExams,
				pathGrammer);

		inlay.addtoDate();// **setting dates to static

		calendarView = new CalendarView(0.8, Integer.parseInt(Inlay.year1), Integer.parseInt(Inlay.month1));
		next.setDisable(false);
		previous.setDisable(false);
		root = new BorderPane(calendarView.getView(), null, next, null, previous);

		pane = new Pane(root);
		hBoxCV = new HBox(pane);
		hBoxCV.setAlignment(Pos.CENTER);

		// add calendar
		vCalander.getChildren().add(hBoxCV);

		// add confirm
		vCalander.getChildren().add(hConfirm);
		// show calendar
		anchor.getChildren().add(vCalander);

		createNewInlayBtn.setDisable(true);
		vCalander.setVisible(true);
		previous.setDisable(true);
		if (Inlay.month1.equals(Inlay.month2)) {
			next.setDisable(true);
		}

		styling.borderStyling(root);
		styling.MainBtnstyling(confirmBtn);
		styling.vboxStylingWithBorder(vCalander);
		settingAction();
	}

	private CalendarDay createCalenderDay() {
		CalendarDay calendarDay = inlay.createCalenderDay();
		totalNumOfDaysLbl = new Label("Number of days on which exams can be done : " + calendarDay.getSize());
		HBox buttonHBox = new HBox(okBtn, cancelBtn);
		buttonHBox.setSpacing(10);
		buttonHBox.setAlignment(Pos.CENTER);
		totalDayBox = new VBox(totalNumOfDaysLbl, buttonHBox);
		totalDayBox.setSpacing(20);
		totalDayBox.setLayoutX(340);
		totalDayBox.setLayoutY(200);
		styling.vboxStylingWithBorder(totalDayBox);
		styling.lblStyling(totalNumOfDaysLbl);
		BackgroundFill background_fill = new BackgroundFill(Color.WHITESMOKE, CornerRadii.EMPTY, Insets.EMPTY);
		Background background = new Background(background_fill);
		totalDayBox.setBackground(background);
		confirmBtn.setDisable(true);
		anchor.getChildren().add(totalDayBox);

		return calendarDay;
	}

	private void uploadFile(int typeFile) {
		FileChooser fileChooser = new FileChooser();
		File file = fileChooser.showOpenDialog(primaryStage);
		if (file != null) {
			String fileName = file.getAbsolutePath();
			if (typeFile == 0) {
				txtFileExams.setText(fileName);
			} else {
				txtFileConstraints.setText(fileName);
			}
		}

	}

	private void alert(String title, String message) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle(title);
		alert.setHeaderText(null);
		alert.setContentText(message);
		alert.showAndWait();
	}

	private void settingAction() {
		for (int i = 0; i < 8; i++) {
			for (int j = 1; j < 8; j++) {
				calendarView.btn1[i][j].setOnAction(this);
				calendarView.btn2[i][j].setOnAction(this);
				calendarView.btn3[i][j].setOnAction(this);
			}
		}
	}

	private void closePopUp() {
		vCalander.setVisible(false);
		createNewInlayBtn.setDisable(false);
		Inlay.setallNull();
		vCalander.getChildren().remove(hBoxCV);
		vCalander.getChildren().remove(hConfirm);
		anchor.getChildren().remove(vCalander);
	}

	private void closeTotalDays() {
		totalDayBox.setVisible(false);
		confirmBtn.setDisable(false);
		anchor.getChildren().remove(totalDayBox);
	}

}