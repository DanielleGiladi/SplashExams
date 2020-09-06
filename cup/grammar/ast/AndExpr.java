package cup.grammar.ast;

public class AndExpr extends Expr {

	private Expr left;
	private Expr right;

	public AndExpr(Expr aleft, Expr aright) {
		left = aleft;
		right = aright;
	}

	@Override
	public Integer eval(Environment anEnvironment) {
		return ((Integer)left.eval(anEnvironment) == 1  && (Integer)right.eval(anEnvironment) == 1) ? 1 : 0;
	}

}
