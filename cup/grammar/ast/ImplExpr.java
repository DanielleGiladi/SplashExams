package cup.grammar.ast;

public class ImplExpr extends Expr {

	private Expr left;
	private Expr right;

	public ImplExpr(Expr aleft, Expr aright) {
		left = aleft;
		right = aright;
	}

	@Override
	public Integer eval(Environment anEnvironment) {
		return (Integer)left.eval(anEnvironment) == 1 && (Integer)right.eval(anEnvironment) == 0 ? 0 : 1;
	}

}
