package DataBase;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import GUI.Inlay;
import genetic.Allocation;
import genetic.Exam;

public class DataBase {

	public Allocation allocation;
	public String date1, month1, year1;
	public String date2, month2, year2;
	public String name;
	public String semester;
	public int constraintPeriod , constraintExam;
	public String pathGrammer;
	private DBConnection db = new DBConnection();

	public DataBase() {

	}

	public DataBase(Allocation anAllocation, String aDate1, String aMonth1, String aYaer1, String aDate2,
			String aMonth2, String aYaer2, String aName, String aSemester ,  int aConstraintPeriod ,
			int aConstraintExam , String aPathGrammer) {

		allocation = anAllocation;
		date1 = aDate1;
		month1 = aMonth1;
		year1 = aYaer1;
		date2 = aDate2;
		month2 = aMonth2;
		year2 = aYaer2;
		name = aName;
		semester = aSemester;
		constraintPeriod = aConstraintPeriod;
		constraintExam = aConstraintExam;
		pathGrammer = aPathGrammer;
		
	}

	public void saveResult() {
		if (!checkIfTableExist(semester)) {
			db.createMainTable(semester);
		}
		db.createExamsTable(name);
		db.insertIntoMainTable(semester, date1, month1, year1, date2, month2, year2, name, (int) allocation.getRank() , constraintPeriod , constraintExam , pathGrammer);
		db.insertIntoTableExam(name, allocation.allocationExams());

	}

	public boolean checkIfTableExist(String name) {
		Set<String> allTables = null;
		try {
			allTables = getDBTables(db.getConn());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (allTables.contains(name.toLowerCase())) {
			return true;
		}

		return false;

	}

	public Set<String> searchFilesFromDataBase(String aSemester) {
		Set<String> allTables = new HashSet<String>();

		if (checkIfTableExist(aSemester)) {
			allTables = db.readAllTableName(aSemester);
		}
		if (allTables.contains("")) {
			allTables.remove("");
		}
		return allTables;
	}

	public void loadRecordFromDataBase(String selected, String aSemester) throws SQLException {
		ResultSet res = db.readOneLine(selected, aSemester);
		int rank = res.getInt("Rank");
		date1 = res.getString("Day1");
		month1 = res.getString("Month1");
		year1 = res.getString("Year1");
		date2 = res.getString("Day2");
		month2 = res.getString("Month2");
		year2 = res.getString("Year2");
		constraintPeriod = res.getInt("ConstraintPeriod");
		constraintExam = res.getInt("ConstraintExam");
		pathGrammer = res.getString("PathGrammar");
		

		List<Exam> exams = db.readAllExam(selected);
		allocation = new Allocation(rank, exams);
		Inlay inlay = new Inlay(selected, date1, month1, year1, date2, month2,
				year2, allocation.getRank() / 2 , constraintPeriod , constraintExam ,pathGrammer);
		inlay.arrangingDates(allocation);
	}

	public void resetSystem() {
		try {
			db.dropAllTables(getDBTables(db.getConn()));
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void deleteSpecificTable(String semester, String tableName) {
		db.dropSpecificTable(semester, tableName);

	}
	
	public Set<String> getAllTables() throws SQLException {
		return getDBTables(db.getConn());
		
	}

	private Set<String> getDBTables(Connection targetDBConn) throws SQLException {
		Set<String> set = new HashSet<String>();
		DatabaseMetaData dbmeta = targetDBConn.getMetaData();
		readDBTable(set, dbmeta, "TABLE", null);
		return set;
	}

	private void readDBTable(Set<String> set, DatabaseMetaData dbmeta, String searchCriteria, String schema)
			throws SQLException {
		ResultSet rs = dbmeta.getTables(null, schema, null, new String[] { searchCriteria });
		while (rs.next()) {
			set.add(rs.getString("TABLE_NAME").toLowerCase());
		}
	}

}
