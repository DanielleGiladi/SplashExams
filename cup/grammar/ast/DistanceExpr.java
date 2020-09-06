package cup.grammar.ast;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class DistanceExpr extends Expr {

	private Expr left;
	private Expr right;

	public DistanceExpr(Expr aleft, Expr aright) {
		left = aleft;
		right = aright;
	}

	@Override
	public Integer eval(Environment anEnvironment) {
		LocalDate d1 = (LocalDate) left.eval(anEnvironment);
		LocalDate d2 = (LocalDate) right.eval(anEnvironment);
		Integer dis = (int) ChronoUnit.DAYS.between(d1, d2);


		return Math.abs(dis);
	}

}
