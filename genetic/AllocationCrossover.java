package genetic;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.uncommons.watchmaker.framework.operators.AbstractCrossover;

public class AllocationCrossover extends AbstractCrossover<Allocation> {
	private static int id = 0;

	protected AllocationCrossover(int crossoverPoints) {
		super(crossoverPoints);
	}

	@Override
	protected List<Allocation> mate(Allocation allocation1, Allocation allocation2, int numberOfCrossoverPoints,
			Random rNum) {

		List<Allocation> resultCrossover = new ArrayList<Allocation>();
		Allocation res1 = new Allocation(id++);
		Allocation res2 = new Allocation(id++);
		int j = rNum.nextInt(allocation1.size() - 1);

		for (int i = 0; i < allocation1.allocationExams().size(); i++) {
			if (i < allocation1.allocationExams().size() - j) {
				res1.addExam(allocation1.allocationExams().get(i));
				res2.addExam(allocation2.allocationExams().get(i));
			} else {
				res2.addExam(allocation1.allocationExams().get(i));
				res1.addExam(allocation2.allocationExams().get(i));
			}
		}

		resultCrossover.add(res1);
		resultCrossover.add(res2);

		return resultCrossover;
	}

}
