package cup.grammar.ast;

import java.util.LinkedList;
import java.util.List;



public abstract class Expr {
	
	public static List<Expr> list(Expr e) {
		List<Expr> aList = new LinkedList<Expr>();
		aList.add(e);
		return aList;
	}
	
	public static Expr binop(Expr left, Integer op, Expr right) {
		return new BinaryOp(left, op, right);
	}
	
	public static Expr var(String aString) {
		return new Variable(aString);
	}
	
	public static Expr lit(Integer anInteger) {
		return new Literal(anInteger);
	}
	
	public static Expr equal(Expr left, Expr right) {
		return new EqualExpr(left, right);
	}
	
	public static Expr nonequal(Expr left, Expr right) {
		return new NonEqualExpr(left, right);
	}
	
	public static Expr less(Expr left, Expr right) {
		return new LessExpr(left, right);
	}
	
	public static Expr greater(Expr left, Expr right) {
		return new GreaterExpr(left, right);
	}
	
	public static Expr and(Expr left, Expr right) {
		return new AndExpr(left, right);
	}
	
	public static Expr or(Expr left, Expr right) {
		return new OrExpr(left, right);
	}
	
	public static Expr impl(Expr left, Expr right) {
		return new ImplExpr(left, right);
	}
	
	
	public static Expr distance(Expr left, Expr right) {
		return new DistanceExpr(left, right);
	}
	
	
	public static Expr attr (String n, String a) {
		return new Attribute(n, a);
	}

	public abstract Object eval(Environment anEnvironment);
}
