
package GUI;

import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

class NewExamBoardGUI implements EventHandler<ActionEvent> {

	private Stage primaryStage;
	private Styling styling = new Styling();

	private Button backBtn;
	private Button semesterA, semesterB, summerSemester;

	public NewExamBoardGUI(Stage primaryStage) {

		this.primaryStage = primaryStage;
		try {
			Screen();
		} catch (Exception e) {
			Logger.getLogger(NewExamBoardGUI.class.getName()).log(Level.SEVERE, null, e);
		}

	}

	private void Screen() {

		backBtn = new Button("Back");
		backBtn.setOnAction(this);

		BackgroundFill background_fill;
		background_fill = new BackgroundFill(Color.WHITESMOKE, CornerRadii.EMPTY, Insets.EMPTY);
		Background background = new Background(background_fill);

		semesterA = new Button("      Semester A \t ");
		semesterA.setOnAction(this);
		semesterB = new Button("      Semester B \t ");
		semesterB.setOnAction(this);
		summerSemester = new Button("Summer Semester");
		summerSemester.setOnAction(this);

		
		styling.MainBtnstyling(semesterA);
		styling.MainBtnstyling(semesterB);
		styling.MainBtnstyling(summerSemester);
		styling.Btnstyling(backBtn);

		HBox hbox = new HBox(semesterA, semesterB, summerSemester);
		hbox.setBackground(background);
		hbox.setSpacing(50);

		AnchorPane anchor = new AnchorPane();
		anchor.getChildren().addAll(backBtn, hbox);

		AnchorPane.setLeftAnchor(backBtn, 20d);
		AnchorPane.setTopAnchor(backBtn, 20d);
		hbox.setLayoutX(285);
		hbox.setLayoutY(290);

		anchor.setBackground(background);

		styling.AnchorStyling(anchor);

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
				Logger.getLogger(NewExamBoardGUI.class.getName()).log(Level.SEVERE, null, e);
			}
		}

		if (event.getSource() == semesterA) {
			new UserInputGUI(primaryStage, "A");

		}
		if (event.getSource() == semesterB) {
			new UserInputGUI(primaryStage, "B");

		}
		if (event.getSource() == summerSemester) {
			new UserInputGUI(primaryStage, "C");

		}

	}
}
