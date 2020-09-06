package genetic;

import java.util.Random;

import org.uncommons.watchmaker.framework.factories.AbstractCandidateFactory;

public class AllocationFactory extends AbstractCandidateFactory<Allocation>{

private static int enterToFun=1;

	@Override
	public Allocation generateRandomCandidate(Random rNum) {
		
			return new Allocation(enterToFun++,rNum);
	}


}
