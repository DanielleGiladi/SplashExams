package cup.grammar.ast;


import java.util.ArrayList;
import java.util.List;

import genetic.Allocation;

public class Rules {
	private List<Rule> rules;
	
	

	public Rules() {
		this.rules = new ArrayList<Rule>();
	}
	
	public void add(Rule rule) {
		rules.add(rule);
	}


	public Rules(List<Rule> arules) {
		this.rules = arules;
	}
	
	
	public void totalRank(Allocation anAllocation) {
		for(int i = 0 ; i< rules.size() ; i++ ) {
			rules.get(i).rankAllocation(anAllocation , 0);

		}	
	}
	
	public void totalErrors(Allocation anAllocation) {
		for(int i = 0 ; i< rules.size() ; i++ ) {
			rules.get(i).rankAllocation(anAllocation , 1);

		}	
	}

}
