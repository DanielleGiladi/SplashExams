package genetic;

import java.time.LocalDate;

public class Exam {
	
	private LocalDate day;
	private Integer id;
	private Integer period;
	private Integer shift;
	private Integer year;
	private Integer faculty;
	private String name;
	private Integer amountStudents;

	public Exam() {
		super();
	}

	public Exam(Exam other) {
		this.day = other.day;
		this.id = other.id;
		this.period = other.period;
		this.shift = other.shift;
		this.year = other.year;
		this.faculty = other.faculty;
		this.amountStudents = other.amountStudents;
		this.name = other.name;
	}

	public Exam(Integer aid, Integer aperiod, Integer ayear, Integer afaculty, LocalDate aday, Integer ashift,
			Integer anamountStudents, String aname) {
		super();
		this.day = aday;
		this.id = aid;
		this.period = aperiod;
		this.shift = ashift;
		this.year = ayear;
		this.faculty = afaculty;
		this.amountStudents = anamountStudents;
		this.name = aname;
	}

	public LocalDate day() {
		return day;
	}

	public Integer id() {
		return id;
	}

	public Integer period() {
		return period;
	}

	public Integer shift() {
		return shift;
	}

	public Integer year() {
		return year;
	}

	public Integer faculty() {
		return faculty;
	}

	public Integer amountStudents() {
		return amountStudents;

	}
	
	
	public String name() {
		return name;
		
	}

	public Object getAttribute(String name) {
		if (name.equals("id"))
			return id();
		if (name.equals("day"))
			return day();
		if (name.equals("period"))
			return period();
		if (name.equals("shift"))
			return shift();
		if (name.equals("year"))
			return year();
		if (name.equals("faculty"))
			return faculty();
		if (name.equals("amountStudents"))
			return amountStudents();
		throw new RuntimeException("Error in getAttribute: unknown attribute :" + name);
	}

	

	@Override
	public String toString() {
		return "Exam [day=" +  day + ", id=" + id + ", period=" + period + ", shift=" + shift + ", year=" + year
				+ ", faculty=" + faculty + ", name=" + name + ", amountStudents=" + amountStudents + "]\n";
	}

	public void setDay(LocalDate day) {
		this.day = day;
	}

	public void setShift(Integer shift) {
		this.shift = shift;
	}

}
