package DataBase;

import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import genetic.Exam;

public class DBConnection {

	private static final String JDBC_URL = "jdbc:derby:DB;create=true";

	private Connection conn;

	public DBConnection() {
		try {
			this.conn = DriverManager.getConnection(JDBC_URL);

		} catch (SQLException e) {
			Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, e);
		}
	}

	public void createMainTable(String semester) {
		try {
			conn.createStatement()
					.execute("Create TABLE " + semester + "(NAME varchar(50) , Rank INT ,"
							+ " Day1 varchar(2) , Month1 varchar(2), Year1 varchar(4) ,"
							+ "  Day2 varchar(2) , Month2 varchar(2), Year2 varchar(4) , ConstraintPeriod INT ,"
							+ " ConstraintExam INT  , PathGrammar varchar(200))");
		} catch (SQLException e) {
			Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, e);
		}
	}

	public void insertIntoMainTable(String semester, String aDate1, String aMonth1, String aYaer1, String aDate2,
			String aMonth2, String aYaer2, String aName, int rank, int aConstraintPeriod, int aConstraintExam,
			String pathGrammer) {
		try {
			conn.createStatement()
					.execute("INSERT INTO " + semester + " Values ('" + aName + "' , " + rank + " ,'" + aDate1 + "','"
							+ aMonth1 + "','" + aYaer1 + "','" + aDate2 + "','" + aMonth2 + "','" + aYaer2 + "' ,"
							+ aConstraintPeriod + "," + aConstraintExam + ",'" + pathGrammer + "' )");
		} catch (SQLException e) {
			Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, e);
		}

	}

	public void createExamsTable(String name) {
		try {
			conn.createStatement().execute("Create TABLE " + name
					+ "(NAME varchar(150) , ID INT , Period INT , SchoolYear INT, Faculty INT , AmountStudents INT ,Date varchar(10) , Shift INT )");
		} catch (SQLException e) {
			Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, e);
		}
	}

	public void insertIntoTableExam(String tableName, List<Exam> allExam) {
		try {
			for (int i = 0; i < allExam.size(); i++) {
				Integer aid = allExam.get(i).id();
				Integer aperiod = allExam.get(i).period();
				Integer ayear = allExam.get(i).year();
				Integer afaculty = allExam.get(i).faculty();
				LocalDate aday = allExam.get(i).day();
				String date = aday.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
				Integer ashift = allExam.get(i).shift();
				Integer anamountStudents = allExam.get(i).amountStudents();
				String aname = allExam.get(i).name();

				conn.createStatement()
						.execute("INSERT INTO " + tableName + " Values('" + aname + "' , " + aid + " , " + aperiod
								+ " , " + ayear + " , " + afaculty + " , " + anamountStudents + " , '" + date + "' , "
								+ ashift + " )");
			}
		} catch (SQLException e) {
			Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, e);
		}
	}

	public List<Exam> readAllExam(String tableName) {
		List<Exam> allExam = new ArrayList<Exam>();
		try {
			Statement statement = this.conn.createStatement();
			ResultSet resultSet = statement.executeQuery("Select * FROM " + tableName);
			while (resultSet.next()) {
				String name = resultSet.getString("NAME");
				Integer id = resultSet.getInt("ID");
				Integer period = resultSet.getInt("Period");
				Integer year = resultSet.getInt("SchoolYear");
				Integer faculty = resultSet.getInt("Faculty");
				Integer amountStudents = resultSet.getInt("AmountStudents");
				String date = resultSet.getString("Date");

				LocalDate day = LocalDate.parse(date, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
				Integer shift = resultSet.getInt("Shift");
				allExam.add(new Exam(id, period, year, faculty, day, shift, amountStudents, name));

			}

		} catch (SQLException e) {
			Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, e);

		}
		return allExam;

	}

	public Set<String> readAllTableName(String aSemester) {
		Set<String> allTable = new HashSet<String>();
		try {
			Statement statement = this.conn.createStatement();
			ResultSet resultSet = statement.executeQuery("Select * FROM " + aSemester);
			while (resultSet.next()) {
				allTable.add(resultSet.getString("NAME"));

			}

		} catch (SQLException e) {
			Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, e);

		}
		return allTable;

	}

	public ResultSet readOneLine(String name, String aSemester) {
		ResultSet resultSet = null;
		try {
			Statement statement = this.conn.createStatement();
			resultSet = statement.executeQuery("Select * FROM " + aSemester);
			while (resultSet.next()) {
				if (resultSet.getString("NAME").equals(name))
					return resultSet;
			}

		} catch (SQLException e) {
			Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, e);

		}
		return resultSet;

	}

	public void dropAllTables(Set<String> allTable) {
		try {
			Iterator<String> value = allTable.iterator();
			while (value.hasNext()) {
				String a = value.next();
				this.conn.createStatement().execute("DROP TABLE " + a);
			}

		} catch (SQLException e) {
			Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, e);

		}
	}

	public void dropSpecificTable(String semester, String tableName) {
		try {

			this.conn.createStatement().execute("DROP TABLE " + tableName);
			this.conn.createStatement().execute("DELETE FROM " + semester + " WHERE NAME = '" + tableName + "'");

		} catch (SQLException e) {
			Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, e);

		}
	}

	public Connection getConn() {
		return conn;
	}

}
