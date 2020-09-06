package genetic;

import java.util.List;

import cup.grammar.Parser;
import cup.grammar.ast.Rules;
import org.uncommons.watchmaker.framework.FitnessEvaluator;

public class AllocationEvaluator implements FitnessEvaluator<Allocation> {

	private Parser parser;
	private Rules rules;

	public AllocationEvaluator() {
		parser = new Parser();
		rules = null;
		try {
			rules = (Rules) parser.parse().value;
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public double getFitness(Allocation anAllocation, List<? extends Allocation> arg1) {

		anAllocation.rank(0.0);
		
		rules.totalRank(anAllocation);

		return 1 / (1 + anAllocation.getRank());

	}

	@Override
	public boolean isNatural() {
		return true;
	}

}