
package GUI;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.swing.JFileChooser;

import cup.grammar.Parser;
import cup.grammar.ast.Rules;
import genetic.Allocation;
import genetic.CalendarDay;
import genetic.Exam;
import genetic.GeneticEngine;

public final class Inlay {

	public static boolean flagUserInput = false;

	public static ArrayList<String> exceptionalDaysLocation1 = new ArrayList<>();
	public static ArrayList<String> exceptionalDaysLocation2 = new ArrayList<>();
	public static ArrayList<String> exceptionalDaysLocation3 = new ArrayList<>();

	public static Map<LocalDate, String> listOfExams1 = new HashMap<LocalDate, String>();
	public static Map<LocalDate, String> listOfExams2 = new HashMap<LocalDate, String>();
	public static Map<LocalDate, String> listOfExams3 = new HashMap<LocalDate, String>();

	public static Map<LocalDate, Integer> amoutStudentsForEachDay1 = new HashMap<LocalDate, Integer>();
	public static Map<LocalDate, Integer> amoutStudentsForEachDay2 = new HashMap<LocalDate, Integer>();
	public static Map<LocalDate, Integer> amoutStudentsForEachDay3 = new HashMap<LocalDate, Integer>();

	public static Map<String, LocalDate> locationByDate1 = new HashMap<String, LocalDate>();
	public static Map<String, LocalDate> locationByDate2 = new HashMap<String, LocalDate>();
	public static Map<String, LocalDate> locationByDate3 = new HashMap<String, LocalDate>();

	public static Map<LocalDate, String> dateByLocation1 = new HashMap<LocalDate, String>();
	public static Map<LocalDate, String> dateByLocation2 = new HashMap<LocalDate, String>();
	public static Map<LocalDate, String> dateByLocation3 = new HashMap<LocalDate, String>();

	public static Map<LocalDate, Set<Integer>> Constraint1 = new HashMap<LocalDate, Set<Integer>>();
	public static Map<LocalDate, Set<Integer>> Constraint2 = new HashMap<LocalDate, Set<Integer>>();

	public static String semester;
	public static JFileChooser jfc;
	public static String examsFilePath;
	public static String constraintsFilePath;

	public static double rank;

	public static String startDay;
	public static String endDay;
	public static int occupation;
	public static int exception;
	public static int numOfDaysExam;
	public static int numOfDaysFirstSecond;

	public static String Name = "";

	public static String date1, month1, year1;
	public static String date2, month2, year2;

	private ArrayList<LocalDate> exceptionalDays;

	public Inlay() {

	}

	public Inlay(String aStartDay, String anEndDay, int aOccupation, int anException, int aNoOfDaysExam,
			int aNoOfDaysFirstSecond, String pathExams , String pathGrammer) {
		startDay = aStartDay;
		endDay = anEndDay;
		occupation = aOccupation;
		exception = anException;
		numOfDaysExam = aNoOfDaysExam;
		numOfDaysFirstSecond = aNoOfDaysFirstSecond;
		examsFilePath = pathExams;
		constraintsFilePath = pathGrammer;
		flagUserInput = true;
		

	}

	public Inlay(String tableName, String aday1, String amonth1, String ayear1, String aday2, String amonth2,
			String ayear2, double aRank, int aConstraintPeriod, int aConstraintExam , String grammerFilePath) {
		Name = tableName;
		date1 = aday1;
		month1 = amonth1;
		year1 = ayear1;
		date2 = aday2;
		month2 = amonth2;
		year2 = ayear2;
		rank = aRank;
		numOfDaysFirstSecond = aConstraintPeriod;
		numOfDaysExam = aConstraintExam;
		constraintsFilePath = grammerFilePath;
		new GeneticEngine(new CalendarDay(numOfDaysFirstSecond, numOfDaysExam , constraintsFilePath));
		remove();

	}

	private void remove() {
		exceptionalDaysLocation1.removeAll(exceptionalDaysLocation1);
		exceptionalDaysLocation2.removeAll(exceptionalDaysLocation2);
		exceptionalDaysLocation3.removeAll(exceptionalDaysLocation3);

		Constraint1 = new HashMap<LocalDate, Set<Integer>>();
		Constraint2 = new HashMap<LocalDate, Set<Integer>>();

		listOfExams1 = new HashMap<LocalDate, String>();
		listOfExams2 = new HashMap<LocalDate, String>();
		listOfExams3 = new HashMap<LocalDate, String>();

		amoutStudentsForEachDay1 = new HashMap<LocalDate, Integer>();
		amoutStudentsForEachDay2 = new HashMap<LocalDate, Integer>();
		amoutStudentsForEachDay3 = new HashMap<LocalDate, Integer>();

		locationByDate1 = new HashMap<String, LocalDate>();
		locationByDate2 = new HashMap<String, LocalDate>();
		locationByDate3 = new HashMap<String, LocalDate>();

		dateByLocation1 = new HashMap<LocalDate, String>();
		dateByLocation2 = new HashMap<LocalDate, String>();
		dateByLocation3 = new HashMap<LocalDate, String>();

	}

	public void addtoDate() {
		String checkStart[] = startDay.split("-");
		date1 = (checkStart[0]);
		month1 = (checkStart[1]);
		year1 = (checkStart[2]);

		String checkEnd[] = endDay.split("-");
		date2 = (checkEnd[0]);
		month2 = (checkEnd[1]);
		year2 = (checkEnd[2]);

	}

	public static void setallNull() {
		exceptionalDaysLocation1.removeAll(exceptionalDaysLocation1);
		exceptionalDaysLocation2.removeAll(exceptionalDaysLocation2);
		exceptionalDaysLocation3.removeAll(exceptionalDaysLocation3);

		Name = "";
		date1 = "";
		month1 = "";
		year1 = "";
		date2 = "";
		month2 = "";
		year2 = "";

		listOfExams1 = new HashMap<LocalDate, String>();
		listOfExams2 = new HashMap<LocalDate, String>();
		listOfExams3 = new HashMap<LocalDate, String>();

		amoutStudentsForEachDay1 = new HashMap<LocalDate, Integer>();
		amoutStudentsForEachDay2 = new HashMap<LocalDate, Integer>();
		amoutStudentsForEachDay3 = new HashMap<LocalDate, Integer>();

		locationByDate1 = new HashMap<String, LocalDate>();
		locationByDate2 = new HashMap<String, LocalDate>();
		locationByDate3 = new HashMap<String, LocalDate>();

		dateByLocation1 = new HashMap<LocalDate, String>();
		dateByLocation2 = new HashMap<LocalDate, String>();
		dateByLocation3 = new HashMap<LocalDate, String>();

		Constraint1 = new HashMap<LocalDate, Set<Integer>>();
		Constraint2 = new HashMap<LocalDate, Set<Integer>>();

	}

	public CalendarDay createCalenderDay() {
		CalendarDay calendarDay = null;
		exceptional();
		ArrayList<LocalDate> allValidDate = new ArrayList<LocalDate>();
		allValidDate = crateValidCollection(allValidDate);
		calendarDay = new CalendarDay(allValidDate, numOfDaysFirstSecond, numOfDaysExam, examsFilePath , constraintsFilePath);
		return calendarDay;

	}

	private void exceptional() {
		exceptionalDays = new ArrayList<LocalDate>();
		int month1Int = Integer.parseInt(month1);
		int month2Int = Integer.parseInt(month2);

		int num = exceptionalDaysLocation1.size();

		for (int i = 0; i < num; i++) {
			exceptionalDays.add(locationByDate1.get(exceptionalDaysLocation1.get(i)));
		}

		if (month2Int - month1Int == 1 || month2Int - month1Int == -11) {
			num = exceptionalDaysLocation2.size();
			for (int i = 0; i < num; i++) {
				exceptionalDays.add(locationByDate2.get(exceptionalDaysLocation2.get(i)));
			}

		} else if (month2Int - month1Int == 2 || month2Int - month1Int == -10) {

			num = exceptionalDaysLocation2.size();
			for (int i = 0; i < num; i++) {
				exceptionalDays.add(locationByDate2.get(exceptionalDaysLocation2.get(i)));

			}

			num = exceptionalDaysLocation3.size();
			for (int i = 0; i < num; i++) {
				exceptionalDays.add(locationByDate3.get(exceptionalDaysLocation3.get(i)));
			}
		}
	}

	private ArrayList<LocalDate> crateValidCollection(ArrayList<LocalDate> allValidDate) {
		allValidDate = new ArrayList<LocalDate>();
		LocalDate d1 = null;
		LocalDate d2 = null;
		try {
			d1 = LocalDate.parse(startDay, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
			d2 = LocalDate.parse(endDay, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
		} catch (Exception e) {
			e.printStackTrace();
		}

		int daysBetween = (int) ChronoUnit.DAYS.between(d1, d2);

		for (int i = 0; i <= daysBetween; i++) {
			if (validDate(d1)) {
				allValidDate.add(d1);
			}
			d1 = d1.plusDays(1);

		}
		return allValidDate;

	}

	private boolean validDate(LocalDate date) {
		Calendar c1 = Calendar.getInstance();
		c1.set(date.getYear(), date.getMonthValue() - 1, date.getDayOfMonth());
		if (c1.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
			return false;
		}
		for (int i = 0; i < exceptionalDays.size(); i++) {
			if (date.equals(exceptionalDays.get(i))) {
				return false;
			}
		}
		return true;

	}

	public void arrangingDates(Allocation allocation) {
		int monthInt1 = 0, monthInt2 = 0, monthInt3 = 0;
		monthInt1 = Integer.parseInt(month1);
		monthInt2 = Integer.parseInt(month2);
		if ((monthInt2 - monthInt1) == 2 || (monthInt2 - monthInt1) == -10) {
			if (monthInt1 == 12) {
				monthInt3 = monthInt2;
				monthInt2 = 1;
			} else {
				monthInt3 = monthInt2;
				monthInt2 = monthInt1 + 1;
			}
		}

		for (int i = 0; i < allocation.size(); i++) {
			Exam exam = allocation.allocationExams().get(i);
			LocalDate date = exam.day();
			int amout = exam.amountStudents();
			int n = 0;
			int tempMonth = date.getMonthValue();

			if (tempMonth == monthInt1) {
				String stringAllExam = listOfExams1.get(date);
				if (amoutStudentsForEachDay1.containsKey(date)) {
					n = amoutStudentsForEachDay1.get(date);
				}
				if (stringAllExam == null) {
					stringAllExam = "";
				} else {
					stringAllExam = stringAllExam + "\n";
				}
				listOfExams1.put(date,
						stringAllExam + "ID-" + exam.id() + ", Name - " + exam.name() + ", Period -" + exam.period());
				amoutStudentsForEachDay1.put(date, n + amout);
			} else if (tempMonth == monthInt2) {
				String stringAllExam = listOfExams2.get(date);
				if (amoutStudentsForEachDay2.containsKey(date)) {
					n = amoutStudentsForEachDay2.get(date);
				}
				if (stringAllExam == null) {
					stringAllExam = "";
				} else {
					stringAllExam = stringAllExam + "\n";
				}
				listOfExams2.put(date,
						stringAllExam + "ID-" + exam.id() + ", Name - " + exam.name() + ", Period -" + exam.period());
				amoutStudentsForEachDay2.put(date, n + amout);

			} else if (tempMonth == monthInt3) {
				String stringAllExam = listOfExams3.get(date);
				if (amoutStudentsForEachDay3.containsKey(date)) {
					n = amoutStudentsForEachDay3.get(date);
				}
				if (stringAllExam == null) {
					stringAllExam = "";
				} else {
					stringAllExam = stringAllExam + "\n";
				}
				listOfExams3.put(date,
						stringAllExam + "ID-" + exam.id() + ", Name - " + exam.name() + ", Period -" + exam.period());
				amoutStudentsForEachDay3.put(date, n + amout);

			}

		}
		errorCalculator(allocation);

	}

	private void errorCalculator(Allocation anAllocation) {
		Parser parser = new Parser();
		Rules rules = null;
		try {
			rules = (Rules) parser.parse().value;
		} catch (Exception e) {
			e.printStackTrace();
		}

		rules.totalErrors(anAllocation);

	}

}
