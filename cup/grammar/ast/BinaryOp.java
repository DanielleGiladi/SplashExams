package cup.grammar.ast;


import cup.grammar.sym;

public class BinaryOp extends Expr {

	private Expr left;
	private Integer op;
	private Expr right;

	public BinaryOp(Expr x, Integer s, Expr y) {
		
		left = x;
		op = s;
		right = y;
	}

	@Override
	public Integer eval(Environment anEnvironment) {
		if (op == sym.PLUS)
			return (Integer)left.eval(anEnvironment) + (Integer)right.eval(anEnvironment);
		else if (op == sym.MINUS)
			return (Integer)left.eval(anEnvironment) - (Integer)right.eval(anEnvironment);
		else if (op == sym.TIMES)
			return (Integer)left.eval(anEnvironment) * (Integer)right.eval(anEnvironment);
		throw new RuntimeException("Error in eval: Unexpected binary operator: " + op);
	}
}
