package cup.grammar.ast;

import java.util.Iterator;
import java.util.List;

import genetic.Allocation;
import genetic.Exam;
import genetic.GeneticEngine;

public class Rule {

	private String name;
	private Integer priority;
	private List<String> vars;
	private Expr expr;
	
	int PeriodsNotTooClose = GeneticEngine.calendarDay.getPeriodsNotTooClose();
	int ExamsNotTooClose = GeneticEngine.calendarDay.getExamsNotTooClose();

	public Rule(String aname, Integer apriority, List<String> avars, Expr aexpr) {
		name = aname;
		priority = apriority;
		vars = avars;
		expr = aexpr;
		
		
	}
	
	public void rankAllocation(Allocation allocation , int type) {
		Environment anEnvironment = new Environment();
		
		if(name.equals("PeriodsNotTooClose")) {
			anEnvironment.put("input", PeriodsNotTooClose);
		
		}
		if(name.equals("ExamsNotTooClose")) {
			anEnvironment.put("input", ExamsNotTooClose);
		}
		
		if(type == 0) {
			rankAllocationAux(0, allocation, anEnvironment );
		}
		else if(type == 1 ) {
			rankAllocationErrorsAux(0, allocation, anEnvironment , name , null);
		}
	}
	
	private void rankAllocationAux(int vi, Allocation allocation, Environment anEnvironment) {
		if (vi == vars.size()) {
			allocation.incrRankBy(priority*(1 - (Integer)expr.eval(anEnvironment)));
		}
		else {
			String v = vars.get(vi);
			for(Iterator<Exam> p = allocation.iterator(); p.hasNext();) {
				anEnvironment.put(v, p.next());
				rankAllocationAux(vi+1, allocation, anEnvironment );
			}
		}
	}
	
	private void rankAllocationErrorsAux(int vi, Allocation allocation, Environment anEnvironment, String name , Exam exam) {
		if (vi == vars.size()) {
			int n = 1- (Integer)expr.eval(anEnvironment);
			allocation.insertErrors(exam,  name ,n);
		}
		else {
			String v = vars.get(vi);
			for(Iterator<Exam> p = allocation.iterator(); p.hasNext();) {
				Exam tempExam = p.next();
				anEnvironment.put(v, tempExam);
				rankAllocationErrorsAux(vi+1, allocation, anEnvironment, name ,  tempExam);
			}
		}
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<String> getVars() {
		return vars;
	}

	public void setVars(List<String> vars) {
		this.vars = vars;
	}

	public Expr getExpr() {
		return expr;
	}

	public void setExpr(Expr expr) {
		this.expr = expr;
	}

	public String toString() {
		String xs = "Rule " + name + "\nVariables\n";
		for(String s : vars) {
			xs = xs + s + " ";
		}
		xs = xs + "\nConstraint\n";
		
		return xs;
	}
}
