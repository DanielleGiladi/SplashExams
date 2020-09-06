package genetic;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.uncommons.watchmaker.framework.EvolutionaryOperator;

public class AllocationMutation implements EvolutionaryOperator<Allocation> {

	@Override
	public List<Allocation> apply(List<Allocation> allAllocations, Random rNum) {

		List<Allocation> res = new ArrayList<Allocation>(allAllocations.size());
		for (int i = 0; i < allAllocations.size(); i++) {
			Allocation a = new Allocation(allAllocations.get(i));
			for (Exam exam : a.allocationExams()) {
				if (rNum.nextInt(100) < 10) {
					exam.setDay(changeDate(exam.day(), rNum));
				}
			}
			res.add(a);
		}

		return res;
	}

	public LocalDate changeDate(LocalDate date, Random rNum) {
		LocalDate newDate = date.plusDays(rNum.nextInt(20) - 10);
		while (!GeneticEngine.calendarDay.getAllValidDate().contains(newDate)) {
			newDate = date.plusDays(rNum.nextInt(20) - 10);
		}

		return newDate;
	}
	
	
}
