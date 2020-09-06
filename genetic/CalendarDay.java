package genetic;

import java.time.LocalDate;
import java.util.ArrayList;

public class CalendarDay {

	private ArrayList<LocalDate> allValidDate;
	private int size;
	private int periodsNotTooClose;
	private int examsNotTooClose;
	private String examsFilePath;
	private String constraintsFilePath;

	public CalendarDay(ArrayList<LocalDate> anallValidDate, int aPeriodsNotTooClose, int anExamsNotTooClose,
			String path , String pathGrammer) {
		allValidDate = anallValidDate;
		periodsNotTooClose = aPeriodsNotTooClose;
		examsNotTooClose = anExamsNotTooClose;
		size = allValidDate.size();
		examsFilePath = path;
		constraintsFilePath = pathGrammer;		
	}

	public CalendarDay(int aPeriodsNotTooClose, int anExamsNotTooClose , String grammerFilePath ) {
		periodsNotTooClose = aPeriodsNotTooClose;
		examsNotTooClose = anExamsNotTooClose;
		constraintsFilePath = grammerFilePath;
	}
	
	public CalendarDay(String grammerFilePath) {
		constraintsFilePath = grammerFilePath;
	}

	

	public ArrayList<LocalDate> getAllValidDate() {
		return allValidDate;
	}

	public int getSize() {
		return size;
	}

	public int getPeriodsNotTooClose() {
		return periodsNotTooClose;
	}

	public int getExamsNotTooClose() {
		return examsNotTooClose;
	}

	public String getFilePath() {
		return examsFilePath;
	}

	public String getFilePathGrammer() {
		return constraintsFilePath;
	}
	
	

}
