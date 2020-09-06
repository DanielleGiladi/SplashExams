package cup.grammar.ast;

public class Variable extends Expr {

	private String name;

	public Variable(String aString) {
		name = aString;
	}
	
	@Override
	public Object eval(Environment anEnvironment) {
		return anEnvironment.at(name);
	}

}
