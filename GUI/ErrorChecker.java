
package GUI;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;
import cup.grammar.Parser;
import cup.grammar.ast.Rules;
import genetic.CalendarDay;
import genetic.GeneticEngine;

public class ErrorChecker {

	private Scanner scnr;

	public boolean intChecker(String text, int min, int max) {
		try {
			int temporary = Integer.parseInt(text);
			if (temporary >= min && temporary <= max) {
				return true;
			}
			return false;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	public String dateChecker(String name) {
		LocalDate date = null;
		try {
			date = LocalDate.parse(name, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
			int day = date.getDayOfMonth();
			String[] temp = name.split("-");
			int StringDay = Integer.parseInt(temp[0]);
			if (day != StringDay) {
				return "The date does not exist, please enter a valid date";
			}
			LocalDate currentDay = LocalDate.now();

			if (date.isBefore(currentDay)) {
				return "The date you entered is already past, please enter a future date ";
			}
		} catch (Exception e) {
			return "Please enter a valid Date of format DD-MM-YYYY";
		}
		return "good";

	}

	public String compareBothDates(String startDate, String endDate) {

		LocalDate d1 = LocalDate.parse(startDate, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
		LocalDate d2 = LocalDate.parse(endDate, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
		if (d1.isAfter(d2) || d1.equals(d2)) {
			return "Start Date cannot be after or Equal to End Date";
		}

		long monthsBetween = ChronoUnit.MONTHS.between(d1, d2);

		if (monthsBetween > 2) {

			return "Exam period cannot be longer than 3 months";
		}

		long daysBetween = ChronoUnit.DAYS.between(d1, d2);

		if (daysBetween < 15) {

			return "Exam period cannot be lees than 15 days";
		}

		return "good";

	}

	public String checkFile(String jfc) {
		String maasge = "The file is not in the correct format, please insert a valid file";
		File file = new File(jfc);
		try {
			scnr = new Scanner(file);

			if (!scnr.hasNextLine()) {
				return "The file must be txt format, please insert a valid file";
			}

			while (scnr.hasNextLine()) {

				String line = scnr.nextLine();
				String oneExam[] = line.split(" ");
				if (oneExam.length < 6) {
					return maasge;
				}
				if (cheakInt(oneExam[0]) == null) {
					return maasge;
				}
				if (oneExam[1] == null) {
					return maasge;
				}
				if (cheakInt(oneExam[2]) == null) {
					return maasge;
				}
				if (cheakInt(oneExam[3]) == null) {
					return maasge;
				}
				if (cheakInt(oneExam[4]) == null) {
					return maasge;
				}
				if (cheakInt(oneExam[5]) == null) {
					return maasge;
				}

			}

		} catch (FileNotFoundException e) {
			return "Unselected file, please select a exams file ";
		}
		return "good";

	}

	
	public String checkFileGrammer(String filePath) {
		new GeneticEngine(new CalendarDay(filePath));
		File file = new File(filePath);
		try {
			scnr = new Scanner(file);
			if (!scnr.hasNextLine()) {
				return "The file must be txt format, please insert a valid file";
			}

			Parser parser = new Parser();
			Rules rules = null;
			try {
				rules = (Rules) parser.parse().value;
			} catch (Exception e) {
				return "The file doesn't match to the grammer";
			}

		} catch (FileNotFoundException e) {
			return "Unselected file, please select a grammer file ";
		}
		return "good";

	}

	private Integer cheakInt(String numberString) {
		Integer num = null;
		try {
			num = Integer.parseInt(numberString);
			return num;
		} catch (NumberFormatException e) {
			return num;
		}

	}

}
