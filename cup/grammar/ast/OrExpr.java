package cup.grammar.ast;

public class OrExpr extends Expr {

	private Expr left;
	private Expr right;

	public OrExpr(Expr aleft, Expr aright) {
		left = aleft;
		right = aright;
	}

	@Override
	public Integer eval(Environment anEnvironment) {
		return ((Integer)left.eval(anEnvironment) == 1  || (Integer)right.eval(anEnvironment) == 1) ? 1 : 0;
	}

}
