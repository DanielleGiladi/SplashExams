package genetic;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.uncommons.maths.random.MersenneTwisterRNG;
import org.uncommons.watchmaker.framework.EvolutionEngine;
import org.uncommons.watchmaker.framework.EvolutionObserver;
import org.uncommons.watchmaker.framework.EvolutionaryOperator;
import org.uncommons.watchmaker.framework.GenerationalEvolutionEngine;
import org.uncommons.watchmaker.framework.PopulationData;
import org.uncommons.watchmaker.framework.operators.EvolutionPipeline;
import org.uncommons.watchmaker.framework.selection.RouletteWheelSelection;
import org.uncommons.watchmaker.framework.termination.TargetFitness;

import GUI.ExamBoardGUI;
import GUI.Inlay;
import javafx.concurrent.Task;
import javafx.stage.Stage;

public class GeneticEngine extends Task<Void> {

	public static CalendarDay calendarDay;
	private Stage primaryStage;
	private Allocation result;
	private Allocation tempResult;

	public GeneticEngine(Stage aPrimaryStage, CalendarDay aCalendarDay) {
		this.primaryStage = aPrimaryStage;
		calendarDay = aCalendarDay;
	}

	public GeneticEngine(CalendarDay aCalendarDay) {
		calendarDay = aCalendarDay;
	}

	@Override
	protected Void call() throws Exception {

		long start = System.currentTimeMillis();

		AllocationEvaluator anEvaluator = new AllocationEvaluator();
		AllocationFactory factory = new AllocationFactory();
		List<EvolutionaryOperator<Allocation>> operators = new ArrayList<EvolutionaryOperator<Allocation>>(1);
		operators.add(new AllocationCrossover(1));
		operators.add(new AllocationMutation());
		EvolutionaryOperator<Allocation> pipeline = new EvolutionPipeline<Allocation>(operators);
		EvolutionEngine<Allocation> engine = new GenerationalEvolutionEngine<Allocation>(factory, pipeline, anEvaluator,
				new RouletteWheelSelection(), new MersenneTwisterRNG());

		EvolutionObserver<? super Allocation> observer = new EvolutionObserver<Allocation>() {
			double maxRank = 0;
			boolean flagFirstRunning = true;

			@Override
			public void populationUpdate(PopulationData<? extends Allocation> data) {

				updateMessage(data.getBestCandidate().getRank() / 2 + "");
				if (flagFirstRunning == true) {
					maxRank = data.getBestCandidate().getRank();
					flagFirstRunning = false;
				}

				if (maxRank > data.getBestCandidate().getRank()) {
					tempResult = data.getBestCandidate();
					maxRank = data.getBestCandidate().getRank();
				}

				// The calculation is up to 90 minutes
				if (((System.currentTimeMillis() - start) / 1000F) / 60 >= 90) {
					cancel();
				}

			}
		};

		engine.addEvolutionObserver(observer);

		result = engine.evolve(100, 10, new TargetFitness(0.9, true));

		result = fixAB(result);
		Inlay inlay = new Inlay();
		inlay.arrangingDates(result);


		return null;
	}

	@Override
	protected void succeeded() {
		super.succeeded();
		new ExamBoardGUI(primaryStage, result);
	}

	@Override
	protected void cancelled() {
		super.cancelled();
		tempResult = fixAB(tempResult);
		Inlay inlay = new Inlay();
		inlay.arrangingDates(tempResult);

		new ExamBoardGUI(primaryStage, tempResult);

	}

	/*
	 * *********** Arranging a first period before the second
	 */
	private Allocation fixAB(Allocation allocation) {
		Allocation a = new Allocation(allocation);
		for (int i = 0; i < allocation.size(); i++) {
			LocalDate temp1 = a.allocationExams().get(i).day();
			int c = i;
			LocalDate temp2 = a.allocationExams().get(++i).day();
			if (temp1.isAfter(temp2)) {
				LocalDate m = temp1;
				temp1 = temp2;
				temp2 = m;
			}
			a.allocationExams().get(c).setDay(temp1);
			a.allocationExams().get(i).setDay(temp2);

		}

		return a;
	}

}
