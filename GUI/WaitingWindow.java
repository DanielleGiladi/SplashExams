package GUI;

import java.util.logging.Level;
import java.util.logging.Logger;

import genetic.CalendarDay;
import genetic.GeneticEngine;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class WaitingWindow implements EventHandler<ActionEvent> {

	private Stage primaryStage;
	private Styling styling = new Styling();
	private AnchorPane anchor;
	private Button cancelbtn;
	private Label titlelbl;
	private Label errroLbl;
	private GeneticEngine task;

	public Label currentNumOfErrorsLbl;

	private ProgressIndicator progressIndicator;
	private HBox hboxErrors;
	private CalendarDay calendarDay;

	public WaitingWindow(Stage primaryStage, CalendarDay aCalendarDay) {

		this.primaryStage = primaryStage;
		this.calendarDay = aCalendarDay;
		try {
			Screen();
		} catch (Exception e) {
			Logger.getLogger(WaitingWindow.class.getName()).log(Level.SEVERE, null, e);
		}

	}

	private void Screen() throws Exception {

		titlelbl = new Label("Waiting Window");
		styling.lblTitleStyling(titlelbl);

		errroLbl = new Label("Number of errors   ---> ");
		errroLbl.setFont(Font.font("Verdana", FontWeight.NORMAL, 35));

		currentNumOfErrorsLbl = new Label("0");
		currentNumOfErrorsLbl.setFont(Font.font("Verdana", FontWeight.NORMAL, 35));

		cancelbtn = new Button("Stop Calculating");
		cancelbtn.setOnAction(this);
		styling.Btnstyling(cancelbtn);

		hboxErrors = new HBox(errroLbl, currentNumOfErrorsLbl);
		hboxErrors.setSpacing(40);

		progressIndicator = new ProgressIndicator();

		anchor = new AnchorPane();
		anchor.getChildren().addAll(titlelbl, hboxErrors, progressIndicator, cancelbtn);

		AnchorPane.setRightAnchor(titlelbl, 400d);
		AnchorPane.setTopAnchor(titlelbl, 40d);
		AnchorPane.setRightAnchor(hboxErrors, 300d);
		AnchorPane.setTopAnchor(hboxErrors, 200d);
		AnchorPane.setRightAnchor(progressIndicator, 570d);
		AnchorPane.setBottomAnchor(progressIndicator, 200d);
		AnchorPane.setRightAnchor(cancelbtn, 530d);
		AnchorPane.setBottomAnchor(cancelbtn, 100d);

		BackgroundFill background_fill;
		background_fill = new BackgroundFill(Color.WHITESMOKE, CornerRadii.EMPTY, Insets.EMPTY);
		Background background = new Background(background_fill);
		anchor.setBackground(background);
		styling.AnchorStyling(anchor);

		Scene scene = new Scene(anchor, 1200, 650);
		primaryStage.setResizable(false);
		primaryStage.setScene(scene);
		primaryStage.show();

		task = new GeneticEngine(primaryStage, calendarDay);
		progressIndicator.progressProperty().bind(task.progressProperty());
		currentNumOfErrorsLbl.textProperty().bind(task.messageProperty());

		Thread th = new Thread(task);
		th.setDaemon(true);

		th.start();

	}

	@Override
	public void handle(ActionEvent event) {
		if (event.getSource() == cancelbtn) {
			task.cancel();

		}
	}

}
