package cup.grammar.ast;

public class LessExpr extends Expr {

	private Expr left;
	private Expr right;

	public LessExpr(Expr aleft, Expr aright) {
		left = aleft;
		right = aright;
	}

	@Override
	public Integer eval(Environment anEnvironment) {
		return (Integer)left.eval(anEnvironment) < (Integer)right.eval(anEnvironment) ? 1 : 0;
	}

}
