
package GUI;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.time.temporal.ChronoUnit;
import java.time.temporal.WeekFields;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;

import javafx.beans.binding.Bindings;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.css.PseudoClass;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import GUI.Inlay;

public class CalendarView {
	private final ObjectProperty<YearMonth> month = new SimpleObjectProperty<>();

	private final ObjectProperty<Locale> locale = new SimpleObjectProperty<>(Locale.getDefault());

	public int chooser = 1;
	private final BorderPane view;
	public final GridPane calendar;
	public Button[][] btn1 = new Button[9][9];
	public TextArea[][] txt1 = new TextArea[9][9];

	public Button[][] btn2 = new Button[9][9];
	public TextArea[][] txt2 = new TextArea[9][9];

	public Button[][] btn3 = new Button[9][9];
	public TextArea[][] txt3 = new TextArea[9][9];

	public double size;

	public void setSize(double s) {
		this.size = s;
	}

	public void setChooser(int c) {
		this.chooser = c + chooser;
	}

	public int getChooser() {
		return chooser;
	}

	public CalendarView(YearMonth month, double s) {
		setSize(s);
		view = new BorderPane();
		calendar = new GridPane();
		Label header = new Label();
		header.setMaxWidth(Double.MAX_VALUE);
		header.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
		header.setStyle("-fx-alignment: center ;");

		this.month.addListener((obs, oldMonth, newMonth) -> rebuildCalendar());

		this.locale.addListener((obs, oldLocale, newLocale) -> rebuildCalendar());

		view.setTop(header);
		view.setCenter(calendar);

		view.getStylesheets().add(getClass().getResource("calendar.css").toExternalForm());

		setMonth(month);

		header.textProperty()
				.bind(Bindings.createStringBinding(
						() -> this.month.get().format(DateTimeFormatter.ofPattern("MMMM yyyy", locale.get())),
						this.month, this.locale));
	}

	public CalendarView(double s, int year, int month) {
		this(YearMonth.of(year, month), s);
	}

	public boolean nextMonth(int c) {
		setChooser(c);
		if (month.get().plusMonths(1).toString().equals(Inlay.year2 + "-" + Inlay.month2)
				|| month.get().plusMonths(2).toString().equals(Inlay.year2 + "-" + Inlay.month2)) {

			month.set(month.get().plusMonths(1));

		}
		if (month.get().plusMonths(1).toString().equals(Inlay.year2 + "-" + Inlay.month2)) {
			return false;
		}
		return true;
	}

	public boolean previousMonth(int c) {
		setChooser(c);
		if (month.get().minusMonths(1).toString().equals(Inlay.year1 + "-" + Inlay.month1)
				|| month.get().minusMonths(2).toString().equals(Inlay.year1 + "-" + Inlay.month1)) {

			month.set(month.get().minusMonths(1));
		}
		if (month.get().minusMonths(1).toString().equals(Inlay.year1 + "-" + Inlay.month1)) {
			return false;
		}
		return true;
	}

	private void rebuildCalendar() {

		WeekFields weekFields = WeekFields.of(locale.get());

		LocalDate first = month.get().atDay(1);

		int dayOfWeekOfFirst = first.get(weekFields.dayOfWeek());

		// String of the day of the week
		for (int dayOfWeek = 1; dayOfWeek <= 7; dayOfWeek++) {
			LocalDate date = first.minusDays(dayOfWeekOfFirst - dayOfWeek);
			DayOfWeek day = date.getDayOfWeek();
			Label label = new Label(day.getDisplayName(TextStyle.FULL, locale.get()));
			StackPane stack = new StackPane(label);
			StackPane.setAlignment(label, Pos.CENTER);
			stack.setMinHeight(16 * size);
			stack.setMinWidth(60 * size);
			calendar.add(stack, dayOfWeek - 1, 0);
		}

		LocalDate firstDisplayedDate = first.minusDays(dayOfWeekOfFirst - 1);
		LocalDate last = month.get().atEndOfMonth();
		int dayOfWeekOfLast = last.get(weekFields.dayOfWeek());
		LocalDate lastDisplayedDate = last.plusDays(7 - dayOfWeekOfLast);

		PseudoClass beforeMonth = PseudoClass.getPseudoClass("before-display-month");
		PseudoClass afterMonth = PseudoClass.getPseudoClass("after-display-month");

		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				btn1[i][j] = new Button();
				txt1[i][j] = new TextArea();

				btn2[i][j] = new Button();
				txt2[i][j] = new TextArea();

				btn3[i][j] = new Button();
				txt3[i][j] = new TextArea();
			}
		}
		Button btn[][] = null;
		TextArea txt[][] = null;
		ArrayList<?> arr = null;
		Map<LocalDate, String> map = null;
		Map<LocalDate, Integer> amout = null;

		if (chooser == 1) {
			arr = Inlay.exceptionalDaysLocation1;
			map = Inlay.listOfExams1;
			amout = Inlay.amoutStudentsForEachDay1;
			btn = btn1;
			txt = txt1;
		}
		if (chooser == 2) {
			arr = Inlay.exceptionalDaysLocation2;
			map = Inlay.listOfExams2;
			amout = Inlay.amoutStudentsForEachDay2;
			btn = btn2;
			txt = txt2;
		}
		if (chooser == 3) {
			arr = Inlay.exceptionalDaysLocation3;
			map = Inlay.listOfExams3;
			amout = Inlay.amoutStudentsForEachDay3;
			btn = btn3;
			txt = txt3;
		}

		Map<String, LocalDate> location = new HashMap<String, LocalDate>();
		Map<LocalDate, String> days = new HashMap<LocalDate, String>();
		// for to all days in month
		for (LocalDate date = firstDisplayedDate; !date.isAfter(lastDisplayedDate); date = date.plusDays(1)) {
			Label label = new Label(String.valueOf(date.getDayOfMonth()));
			label.getStyleClass().add("calendar-cell");

			label.pseudoClassStateChanged(beforeMonth, date.isBefore(first));
			label.pseudoClassStateChanged(afterMonth, date.isAfter(last));

			GridPane.setHalignment(label, HPos.CENTER);

			int dayOfWeek = date.get(weekFields.dayOfWeek());
			int daysSinceFirstDisplayed = (int) firstDisplayedDate.until(date, ChronoUnit.DAYS);
			int weeksSinceFirstDisplayed = daysSinceFirstDisplayed / 7;

			int col = dayOfWeek - 1;
			int row = weeksSinceFirstDisplayed + 1;

			StackPane stack = new StackPane(label, txt[col][row], btn[col][row]);
			StackPane.setAlignment(label, Pos.TOP_LEFT);
			StackPane.setAlignment(txt[col][row], Pos.CENTER_RIGHT);
			StackPane.setAlignment(btn[col][row], Pos.CENTER);
			stack.setStyle("-fx-padding: 0;");
			stack.setMinHeight(50 * size);
			stack.setMinWidth(70 * size);
			txt[col][row].setMaxWidth(60 * size);
			txt[col][row].setMaxHeight(45 * size);
			txt[col][row].setMinWidth(45 * size);
			txt[col][row].setMinHeight(45 * size);

			location.put(col + "" + row, date);
			days.put(date, col + "-" + row);

			if (getMonth().toString().equals(Inlay.year1 + "-" + Inlay.month1)) {
				if (Integer.parseInt(Inlay.date1) > Integer.parseInt(label.getText())) {
					txt[col][row].setVisible(false);
					label.setTextFill(Color.RED);
					btn[col][row].setVisible(false);
				}
			}
			if (getMonth().toString().equals(Inlay.year2 + "-" + Inlay.month2)) {
				if (Integer.parseInt(Inlay.date2) < Integer.parseInt(label.getText())) {
					txt[col][row].setVisible(false);
					label.setTextFill(Color.RED);
					btn[col][row].setVisible(false);
				}
			}
			if (row == 1) {
				if (15 < Integer.parseInt(label.getText())) {
					txt[col][row].setVisible(false);
					label.setTextFill(Color.RED);
					btn[col][row].setVisible(false);
				}
			}
			if (row == 5 || row == 6) {
				if (15 > Integer.parseInt(label.getText())) {
					txt[col][row].setVisible(false);
					label.setTextFill(Color.RED);
					btn[col][row].setVisible(false);
				}
			}
			txt[col][row].setStyle(" -fx-background-color: white;" + "-fx-padding: 0;" + "-fx-text-fill: black;"
					+ " -fx-font-size: 9;");
			
			// Exceptional days - Saturday
			if (col == 6) {
				txt[col][row].setVisible(false);
				btn[col][row].setVisible(false);
				label.setTextFill(Color.RED);
			} else {
				btn[col][row].setOpacity(0);
			}

			txt[col][row].setOpacity(0);
			btn[col][row].setText("x");
			btn[col][row].setStyle(" -fx-background-color: null;" + "-fx-padding: 4;" + "-fx-text-fill: grey;"
					+ " -fx-font-size: 42;" + " -fx-font-weight: bold;");
			calendar.add(stack, col, row);
			calendar.setStyle(" -fx-grid-lines-visible: true;");

			// Exceptional days - calendar
			for (int i = 0; i < 7; i++) {
				for (int j = 1; j < 8; j++) {
					for (int k = 0; k < arr.size(); k++) {
						String temp = i + "" + j;
						if (arr.get(k).equals(temp)) {
							btn[i][j].setOpacity(1);
						}
					}
				}
			}
		} 

		Iterator<Map.Entry<LocalDate, String>> itr = map.entrySet().iterator();
		while (itr.hasNext()) {
			Map.Entry<LocalDate, String> entry = itr.next();
			String exams = entry.getValue();
			LocalDate date = entry.getKey();
			int amountStudents = amout.get(date);
			String[] colRow = days.get(date).split("-");
			int col = Integer.parseInt(colRow[0]);
			int row = Integer.parseInt(colRow[1]);
			String error = "";
			boolean flag = false;
			if(Inlay.Constraint1.containsKey(date)) {
				error = error + "PeriodsNotTooClose " + Inlay.Constraint1.get(date) + "\n";
				flag=true;
			}
			if(Inlay.Constraint2.containsKey(date)) {
				error = error + "ExamsNotTooClose " + Inlay.Constraint2.get(date) + "\n";
				flag = true;
			}
			if(flag) {
				txt[col][row].setStyle(" -fx-background-color: white;" + "-fx-padding: 0;" + "-fx-text-fill: red;"
						+ " -fx-font-size: 9;");
			}
			txt[col][row].setText(error + "Students: " + amountStudents + "\nExams:\n" + exams);
			txt[col][row].setVisible(true);

		}

		if (chooser == 1) {
			Inlay.locationByDate1 = location;
			Inlay.dateByLocation1 = days;
		}
		if (chooser == 2) {
			Inlay.locationByDate2 = location;
			Inlay.dateByLocation2 = days;
		}
		if (chooser == 3) {
			Inlay.locationByDate3 = location;
			Inlay.dateByLocation3 = days;
		}
	}

	public Node getView() {
		return view;
	}

	public final ObjectProperty<YearMonth> monthProperty() {
		return this.month;
	}

	public final YearMonth getMonth() {
		return this.monthProperty().get();
	}

	public final void setMonth(final YearMonth month) {
		this.monthProperty().set(month);
	}

	public final ObjectProperty<Locale> localeProperty() {
		return this.locale;
	}

	public final java.util.Locale getLocale() {
		return this.localeProperty().get();
	}

	public final void setLocale(final java.util.Locale locale) {
		this.localeProperty().set(locale);
	}

}
