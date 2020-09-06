package cup.grammar.ast;


import genetic.Exam;

public class Attribute extends Expr {

	private String examName;
	private String attrName;

	public Attribute(String n, String a) {
		examName = n;
		attrName = a;
	}

	@Override
	public Object eval(Environment anEnvironment) {
		 Exam exam = (Exam) anEnvironment.at(examName);
		 return exam.getAttribute(attrName);
	}

}
