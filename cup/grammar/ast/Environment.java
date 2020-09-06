package cup.grammar.ast;

import java.util.HashMap;
import java.util.Map;

import genetic.Exam;

public class Environment {
	private Map<String, Object> valuesExam = new HashMap<String, Object>();

	public void put(String name, Object value) {
		valuesExam.put(name, value);
	}

	public Object at(String name) {

		return valuesExam.get(name);
	}

	
	public void print() {
		for(String n : valuesExam.keySet()) {
			System.out.println(n + " = " + ((Exam) valuesExam.get(n)).id() + "\n");
		}
	}

}
