package cup.grammar.ast;

public class Literal extends Expr {

	private Integer value;

	public Literal(Integer anInteger) {
		value = anInteger;
	}
	
	@Override
	public Integer eval(Environment anEnvironment) {
		return value;
	}

}
