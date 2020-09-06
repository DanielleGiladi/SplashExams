package genetic;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

import GUI.Inlay;

public class Allocation implements Cloneable {

	private List<Exam> allExams;
	private int allocationId;
	private double rank;
	private Scanner scnr;

	public Allocation(Allocation other) {
		allocationId = other.allocationId;
		allExams = new ArrayList<Exam>();
		for (int i = 0; i < other.allExams.size(); i++) {
			allExams.add(new Exam(other.allExams.get(i)));
		}
		rank = other.rank;
	}

	public Allocation(int anallocationId) {

		this.allocationId = anallocationId;
		this.allExams = new ArrayList<Exam>();

	}
	


	public Allocation(int anallocationId, Random rNum) {

		rank = 0.0;

		this.allocationId = anallocationId;
		this.allExams = new ArrayList<Exam>();
		File file = new File(GeneticEngine.calendarDay.getFilePath());
		try {
			scnr = new Scanner(file);
			int maxDate = GeneticEngine.calendarDay.getSize();
			int counter = 0;
			Collections.shuffle(GeneticEngine.calendarDay.getAllValidDate());
			while (scnr.hasNextLine()) {
				if (counter == maxDate - 1) {
					Collections.shuffle(GeneticEngine.calendarDay.getAllValidDate());
					counter = 0;
				}

				String line = scnr.nextLine();
				String oneExam[] = line.split(" ");
				LocalDate date = GeneticEngine.calendarDay.getAllValidDate().get(counter);
				counter++;

				Integer id = Integer.parseInt(oneExam[0]);
				String name = oneExam[1];
				Integer year = Integer.parseInt(oneExam[2]);
				Integer faculty = Integer.parseInt(oneExam[3]);
				Integer amountStudents = Integer.parseInt(oneExam[4]);
				Integer period = Integer.parseInt(oneExam[5]);

				this.allExams.add(new Exam(id, period, year, faculty, date, rNum.nextInt(3) + 1, amountStudents, name));

			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}

	public Allocation(double arank, List<Exam> exams) {
		rank = arank;
		allExams = exams;

	}

	public void rank(double arank) {
		this.rank = arank;

	}

	public List<Exam> allocationExams() {
		return allExams;
	}

	public int allocationId() {
		return allocationId;
	}

	public void addExam(Exam anExam) {
		allExams.add(anExam);
	}

	public Iterator<Exam> iterator() {
		return allExams.iterator();
	}

	public Exam examAt(int index) {
		return allExams.get(index);
	}

	public int size() {
		return allExams.size();
	}

	public double getRank() {
		return rank;
	}
	
	

	public void incrRankBy(double x) {
		this.rank += x;

	}

	public void insertErrors(Exam exam, String name, int x) {
		if (x != 0 ) {
			if (name.equals("PeriodsNotTooClose")) {
				Set<Integer> set;
				if (Inlay.Constraint1.containsKey(exam.day())) {
					set = Inlay.Constraint1.get(exam.day());
				} else {
					set = new HashSet<Integer>();
				}
				set.add(exam.id());
				Inlay.Constraint1.put(exam.day(), set);

			}
			else if(name.equals("ExamsNotTooClose")) {
				Set<Integer> set;
				if (Inlay.Constraint2.containsKey(exam.day())) {
					set = Inlay.Constraint2.get(exam.day());
				} else {
					set = new HashSet<Integer>();
				}
				set.add(exam.id());
				Inlay.Constraint2.put(exam.day(), set);
			}
		}

	}

	@Override
	public String toString() {
		return "Allocation [allocationExams = \n" + allExams + ", allocationId=" + allocationId + "] rank ="
				+ 1 / (1 + rank);
	}

}