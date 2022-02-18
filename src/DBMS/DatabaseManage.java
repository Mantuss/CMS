package DBMS;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class DatabaseManage {
	
	private Statement statement;
	
	public DatabaseManage(){
		
		DBConnection conn = new DBConnection();
		statement = conn.getConnection();
		
	}
	
	
// StudentPanel database query execution
	
	public String[] getSession(String query) throws SQLException    {
		
		
		ResultSet result = statement.executeQuery(query);
		
		if(result.next()) {
			
			if(result.getString("indicator").equals("Student")) {
				
				return new String[] {"true", result.getString("username")};
			}
			
			if(result.getString("indicator").equals("Teacher")) {
				
				return new String[] {"true", result.getString("username")};
			}
			
		}
		
		return new String[] {"false","null"};
		
	}
	
	
	public boolean sessionExistence(String query) throws SQLException {
		
		ResultSet result = statement.executeQuery(query);
		return result.next();
		
		
	}

	public String[] getSessionInfo(String query) throws SQLException {
		
		ResultSet result = statement.executeQuery(query);
		if(result.next()) {
			
			return new String[] {"true",result.getString("username"), result.getString("indicator")};
			
		}
		
		return new String[] {"false", null, null};

	}
	
	
	public void setSession(String query) throws SQLException {
		
		statement.executeUpdate(query);
		
	}
	
	public String[] getUserData(String query) throws SQLException {
		
		
		ResultSet result = statement.executeQuery(query);
		
		if(result.next()) {
			
			return new String[] {"true", result.getString("fullname"), result.getString("level"), result.getString("enrolled_courses"), result.getString("semester")};
			
		}
		
		return new String[] {"false", null, null, null};
		
	}
	
	public ArrayList<String[]> getCourseData(String query) throws SQLException{
		
		ResultSet result = statement.executeQuery(query);
		
		ArrayList<String[]> courses = new ArrayList<>();
		
		while(result.next()) {
			
			courses.add(new String[] {result.getString("course_modules"), result.getString("module_code"), result.getString("level")});
	
		}
		

		return courses;
		
	}
	
	
	public String getEnrolledCourses(String query) throws SQLException {
		
		ResultSet result = statement.executeQuery(query);
		
		if(result.next()) {
			
			return result.getString("enrolled_modules");
			
		}
		
		return "";
	}
	
	public void deleteSession(String query) throws SQLException {
		
		statement.executeUpdate(query);
		
	}
	
	
	public String[] getUserModules(String query) throws SQLException {
		
		ResultSet result = statement.executeQuery(query);
		
		if(result.next()) {
			
			return new String[] {result.getString("fullname"),result.getString("level"),result.getString("semester")};
			
		}
		
		return new String[] {};
		
	}
	
	public void insertNewReport(String query) throws SQLException {

		statement.executeUpdate(query);

	}
	
	public String[] getProfileData(String query) throws SQLException {
		
		ResultSet result = statement.executeQuery(query);
		
		if(result.next()) {
			
			return new String[] {result.getString("fullname"),result.getString("Email"),result.getString("college_id"),result.getString("phone")};
			
		}
		
		return new String[] {};
		
	}
	
	
	public void updateUserInfo(String query) throws SQLException {
		
		statement.executeUpdate(query);
		
		
	}
	
	public void updateReport(String query) throws SQLException {
		
		statement.executeUpdate(query);
		
	}
	
	public void deleteAccount(String query1, String query2, String query3, String query4) throws SQLException {
		
		statement.executeUpdate(query1);
		statement.executeUpdate(query2);
		statement.executeUpdate(query3);
		statement.executeUpdate(query4);
		
		
	}
	
	
	public String[][] getLevel1Report(String query, String name) throws SQLException {
		
		
		int reportCount = -1;
		ResultSet count = statement.executeQuery("SELECT COUNT(DISTINCT module_name) AS count FROM report WHERE student_name ='"+ name +"' AND  semester ='1' OR  student_name ='"+ name +"' AND semester = '2'");
		
		if(count.next()) {
			
			reportCount = count.getInt("count");
			
		}
		
		
		int x = 0, i = 1;
		
		ResultSet result = statement.executeQuery(query);
		
		String[][] report = new String[reportCount][4];
		
		
		while(result.next()) {
			
			report[x] = new String[] {Integer.toString(i),result.getString("module_name"),result.getString("semester"),result.getString("marks")};
			x+=1;
			i+=1;
		}
		

		return report;
		
		
		
	}
	
	public String[][] getLevel2Report(String query, String name) throws SQLException {
		
		
		int reportCount = -1;
		ResultSet count = statement.executeQuery("SELECT COUNT(DISTINCT module_name) AS count FROM report WHERE student_name ='"+ name +"' AND  semester ='3' OR  student_name ='"+ name +"' AND semester = '4'");
		
		if(count.next()) {
			
			reportCount = count.getInt("count");
			
		}
		
		
		int x = 0, i = 1;
		
		ResultSet result = statement.executeQuery(query);
		
		String[][] report = new String[reportCount][4];
		
	
		
		while(result.next()) {
			
			report[x] = new String[] {Integer.toString(i),result.getString("module_name"),result.getString("semester"),result.getString("marks")};
			x+=1;
			i+=1;
		}
		
		
		
		return report;
		
		
		
	}

	public String[][] getLevel3Report(String query, String name) throws SQLException {
		
		
		int reportCount = -1;
		ResultSet count = statement.executeQuery("SELECT COUNT(DISTINCT module_name) AS count FROM report WHERE student_name ='"+ name +"' AND  semester ='5' OR  student_name ='"+ name +"' AND semester = '6'");
		
		if(count.next()) {
			
			reportCount = count.getInt("count");
			
		}
		
		
		int x = 0, i = 1;
		
		ResultSet result = statement.executeQuery(query);
		
		String[][] report = new String[reportCount][4];
		
		
		while(result.next()) {
			
			report[x] = new String[] {Integer.toString(i),result.getString("module_name"),result.getString("semester"),result.getString("marks")};
			x+=1;
			i+=1;
		}
		
	
		return report;
		
		
		
	}
	
	public void resetAutoIncrementReport() throws SQLException {
		
		statement.executeUpdate("ALTER TABLE report DROP module_id");
		statement.executeUpdate("ALTER TABLE report AUTO_INCREMENT = 1");
		statement.executeUpdate("ALTER TABLE report ADD module_id int UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY FIRST");
		
	}
	
	
	
	public void unEnrollcourse(String query) throws SQLException {
		
		statement.executeUpdate(query);
		
	}
	
	public void removeResult(String query) throws SQLException {
		
		statement.executeUpdate(query);
		
	}
	
	
	public ArrayList<String[]> getAllCourse(String query) throws SQLException{
		
		ArrayList<String[]> course = new ArrayList<>();
		
		ResultSet result = statement.executeQuery(query);
		
		while(result.next()) {
			
			course.add(new String[] {result.getString("course_name"), result.getString("course_full")});
			
		}
		
		return course;
		
	}
	
	public void updateUserCourse(String query) throws SQLException {
		
		
		statement.executeUpdate(query);
		
		
	}
	
	public String getCodeModule(String query) throws SQLException {
		
		
		ResultSet result = statement.executeQuery(query);
		
		if(result.next()) {
			
			return result.getString("module_code");
			
		}
		
		return "";
		
	}
	
	
	
	public String[][] getModuleTeachers(String code) throws SQLException{
		
		String[][] assigned = null;
		
		ResultSet rs = statement.executeQuery("SELECT * FROM user_info WHERE indicator = 'Teacher'");
		
		int i = 0;
		
		while(rs.next()) {

			if(rs.getString("assigned").isBlank() || rs.getString("assigned").isEmpty()) {
				
				List<String> temp = Arrays.asList(rs.getString("assigned").split(","));
				
				if(temp.contains(code)) {
					
					i+=1;
					
				}
				
				
			}
			
			else {
				
				List<String> temp = Arrays.asList(rs.getString("assigned").substring(1).split(","));
				
				if(temp.contains(code)) {
					
					i+=1;
					
				}
				
				
			}
			
			
		}
		
		assigned = new String[i][3];
		
		
		ResultSet result = statement.executeQuery("SELECT * FROM user_info WHERE indicator = 'Teacher'");
		
		int x = 0;
		
		while(result.next()) {
			
			
			
			if(result.getString("assigned").isBlank() || result.getString("assigned").isEmpty()) {
				
				List<String> temp = Arrays.asList(result.getString("assigned").split(","));
				
				if(temp.contains(code)) {
					
					assigned[x] = new String[] {Integer.toString(x + 1), result.getString("fullname"), result.getString("email")};
					x+=1;
					
				}
				
				
			}
			
			else {
				
				List<String> temp = Arrays.asList(result.getString("assigned").substring(1).split(","));
				
				if(temp.contains(code)) {
					
					assigned[x] = new String[] {Integer.toString(x + 1), result.getString("fullname"), result.getString("email")};
					x+=1;
					
				}
				
				
			}
			
			
		}
		
		return assigned;
		
	}
	
	
	
	
	// Profile setup sql executes
	
	public int getCourseCount(String query) throws SQLException {
		
		
		ResultSet result = statement.executeQuery(query);
		
		if(result.next()) {
			
			return result.getInt("count");
			
		}
		
		return 0;
		
		
	}
	
	public String[] getCourses(String query, int courseCount) throws SQLException {
		
		String[] course = new String[courseCount + 1];
		course[0] = "Choose Course";

		int x = 1;
		ResultSet result = statement.executeQuery(query);
		
		while(result.next()) {
			
			course[x] = result.getString("course_name");
			x+=1;
		}

		return course;
		
	}
	
	public void insertProfileData(String query) throws SQLException {

		statement.executeUpdate(query);

	}
	
	
	public String[] getEnrolledCourse(String query) throws SQLException {
		
		ResultSet result = statement.executeQuery(query);
		
		if(result.next()) {
			
			return new String[] {result.getString("enrolled_modules"), Integer.toString(result.getInt("level"))};
			
		}
		
		return new String[] {};
	}
	
	
	public String getModuleCode(String query) throws SQLException {
		
		ResultSet result = statement.executeQuery(query);
		
		if(result.next()) {
			
			return result.getString("module_code");
			
		}
		
		return "";
	}
	
	
	public void updateEnrolledModule(String query) throws SQLException {

		statement.executeUpdate(query);

	}
	
	public void updateResult(String query) throws SQLException {

		statement.executeUpdate(query);
		
	}
	
	
	
	
	
	
	
	//Forgot Password executes
	
	
	
	public String getUserInfo(String query) throws SQLException {

		
		ResultSet result = statement.executeQuery(query);
		
		if(result.next()) {
			
			return result.getString("username");
			
		}
		
		return "";
		
	}
	
	
	public void updateLogs(String query) throws SQLException {
		
		statement.executeUpdate(query);
		
	}
	
	
	
	
	// Admin Panel executes
	
	
	
	public String getUser(String query) throws SQLException{
		
		ResultSet result = statement.executeQuery(query);
		
		if(result.next()) {

			return result.getString("fullname");

		}
		
		return "";

		}

	
	
	
		public ArrayList<String[]> getAccountDetails(String query) throws SQLException{
			
			ArrayList<String[]> students = new ArrayList<>();
			ResultSet result = statement.executeQuery(query);
			
			
			while(result.next()) {
				
				students.add(new String[] {result.getString("level"), result.getString("college_id"), result.getString("fullname")});
				
			}
		
			return students;
			
		}
		
		public ArrayList<String[]> getCourseDetails(String query) throws SQLException{
			
			ArrayList<String[]> courseDetails = new ArrayList<>();
			ResultSet result = statement.executeQuery(query);
			
			while(result.next()) {
				
				courseDetails.add(new String[]{result.getString("course_full"), result.getString("course_name")});
				
			}
			
			return courseDetails;
			
		}
		
		
		public String[] findCourse(String query) throws SQLException {
			
			
			ResultSet result = statement.executeQuery(query);
			
			if(result.next()) {
				
				return new String[] {result.getString("course_name"), result.getString("course_full")};
				
			}
			
			return new String[] {};
		}
		
		public void deleteCourse(String query) throws SQLException {

			statement.executeUpdate(query);
			
		}
		
		
		public void updateCourse(String query) throws SQLException {
			
			
			statement.executeUpdate(query);
			
			
		}
		
		public boolean getExistingCourse(String query) throws SQLException {
			
			
			ResultSet result = statement.executeQuery(query);
			
			if(result.next()) {
				
				return true;
				
			}
			
			return false;
			
		}
		
		public void addCourse(String query) throws SQLException {
			
			statement.executeUpdate(query);
			
		}
		
		
		public String[] getSearchedCourse(String query) throws SQLException {
			
	
			ResultSet result = statement.executeQuery(query);
			
			if(result.next()) {
				
				return new String[] {result.getString("course_full"), result.getString("course_name")};
				
			}
			
			return new String[] {};
			
		}
		
		
		@SuppressWarnings("null")
		public String[][] getSpecificCourses(String query, String course) throws SQLException {
			
			int courseCount = -1;
			ResultSet count = statement.executeQuery("SELECT COUNT(DISTINCT course_modules) AS count FROM modules WHERE course_names ='"+ course +"'");
			
			if(count.next()) {
				
				courseCount = count.getInt("count");
				
			}
			
			
			int x = 0;
			
			ResultSet result = statement.executeQuery(query);
			
			String[][] modules = new String[courseCount][4];
			
			
			
			
			while(result.next()) {
				
				modules[x] = new String[] {result.getString("course_id"),result.getString("module_code"), result.getString("course_modules"), Integer.toString(result.getInt("semester")), Integer.toString(result.getInt("level"))};
				x+=1;
			}
			
			return modules;
			
		}
		
		
		public void updateDatabase(String[][] data, String course) throws SQLException {

			for(int i = 0; i < data.length; i++) {
				
				int j = 0;
				
				statement.executeUpdate("UPDATE `modules` SET `course_modules`='"+ data[i][j + 2]+ "',`module_code`='"+data[i][j+1]+"',`level`='"+ data[i][j+4] + "',`course_names`='"+course+"',`semester`='"+ data[i][j+3] +"' WHERE course_id = '"+data[i][j]+"'");
	
			}

		}


		public void resetAutoIncrement() throws SQLException {
			
			statement.executeUpdate("ALTER TABLE modules DROP course_id");
			statement.executeUpdate("ALTER TABLE modules AUTO_INCREMENT = 1");
			statement.executeUpdate("ALTER TABLE modules ADD course_id int UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY FIRST");
			
		}
		
		
		public void insertModules(String query) throws SQLException {
			

			statement.executeUpdate(query);
			
			
		}
		
		public void deleteModules(String query) throws SQLException {
			
			statement.executeUpdate(query);
			
		}
		
		
		public ArrayList<String[]> getLogs(String query) throws SQLException {
			
			
			ArrayList<String[]> logs = new ArrayList<>();
			ResultSet result = statement.executeQuery(query);
			
			while(result.next()) {
				
				logs.add(new String[] {result.getString("user_id"), result.getString("username"),result.getString("indicator"), result.getString("status")});
				
			}
			
			return logs;
			
		}
		
		public void updateAssigned(String query) throws SQLException {
			
			statement.executeUpdate(query);
			
		}
		
		
		public int[] selectLevelStudent(String query) throws SQLException{
			
			ResultSet result = statement.executeQuery(query);
			
			if(result.next()) {
				
				return new int[] {result.getInt("level"), result.getInt("semester"), result.getInt("completed_sem")};
				
			}
			
			return new int[] {};

		}
		
		
		public void upgradeSemester(String query) throws SQLException {
			
			
			statement.executeUpdate(query);
			
			
		}
		
		public String[] getUserReport(String query) throws SQLException {
			
			
			ResultSet result = statement.executeQuery(query);
			
			if(result.next()) {
				
				return new String[] {result.getString("fullname"),result.getString("username")};
				
				
			}
			
			return new String[] {};
			
		}
		
		
		public String[] getModules(String query) throws SQLException {
			
			String[] modules = null;
			
			ResultSet count = statement.executeQuery("SELECT COUNT(DISTINCT course_modules) as count FROM modules WHERE course_modules !=''");
	
			if(count.next()) {
				
				modules = new String[count.getInt("count")];
				
			}
			
			ResultSet result = statement.executeQuery(query);
			
			int x = 0;
			
			while (result.next()) {
				
				modules[x] = result.getString("course_modules");
				x+=1;
				
			}
			
			return modules;

		}
		
		
		public int getCount(String query) throws SQLException {
			
			
			ResultSet result = statement.executeQuery(query);
			
			if(result.next()) {
				
				return result.getInt("count");
				
			}
			
			return 0;
			
		}
		
		
		


		// Teacher executes
		
		public String[] searchAccount(String query) throws SQLException {
			
			
			ResultSet result = statement.executeQuery(query);
			
			if(result.next()) {
				
				return new String[] {result.getString("username"), result.getString("status")};
			}
			
			return new String[] {};
			
		}
		
		
		
		public String[] getTeacherDetails(String query) throws SQLException {
			
			
			ResultSet result = statement.executeQuery(query);
			if(result.next()) {
				
				return new String[] {result.getString("fullname"), result.getString("email"), result.getString("assigned")};
				
			}
			
			return new String[] {};
			
		}
		
		
		public String[] getAssignedCourse(String query) throws SQLException {
			
			String assigned = null;
			
			ResultSet result = statement.executeQuery(query);
			
			if(result.next()) {
				
				assigned = result.getString("assigned");
				
				if(assigned.isBlank() || assigned.isEmpty()) {
					
					return new String[] {};
					
				}
				
				else {
					
					return assigned.substring(1).split(",");
					
				}
				
			}
			
			return new String[] {};
			

		}
		
		
		public String[] getModuleNames(String query) throws SQLException {
			
			
			ResultSet result = statement.executeQuery(query);
			
			if(result.next()) {
				
				return new String[] {result.getString("module_code"), result.getString("course_modules"), result.getString("semester"), result.getString("level") };
				
			}
			
			return new String[] {"","","","",""};
			
			
		}
		
		public int getResultCount(String query) throws SQLException {
			
			ResultSet result = statement.executeQuery(query);
			
			if(result.next()) {
				
				return result.getInt("count");
				
			}
			
			return 0;
			
		}
		
		public String[][] getResultData(String query, String module) throws SQLException {
			
			
			String[][] data = null;
			int x = 0;
			
			ResultSet countResult = statement.executeQuery("SELECT COUNT(DISTINCT student_name) AS count FROM report WHERE module_name ='" + module + "'");
			
			if(countResult.next()) {
				
				data = new String[countResult.getInt("count")][4];
				
			}
			
			
			
			
			ResultSet result = statement.executeQuery(query);
			
			while(result.next()) {
				
				data[x] =  new String[] {result.getString("level"),result.getString("student_name"),result.getString("semester"),result.getString("marks")};
				x+=1;
			}
			
			
			return data;
			
			
		}
		
		public void updateMarks(String[][] marks) throws SQLException {
			
			for(int i = 0; i < marks.length; i++) {
				
				int j = 0;
				
				statement.executeUpdate("UPDATE report SET  marks ='"+marks[i][j + 2]+"' WHERE student_name = '"+marks[i][j]+"' AND module_name ='"+marks[i][j+1]+"'");

			}
			
			
			
		}
		
		@SuppressWarnings("null")
		public ArrayList<String> getUserName(String query) throws SQLException {
			
			ArrayList<String> list = new ArrayList<>();
			ResultSet result = statement.executeQuery(query);
			
			while(result.next()) {
				
				list.add(result.getString("student_name"));
				
				
			}
			
			
			return list;

		}
		
		public String[] getUserIntel(String query) throws SQLException{
			
			ResultSet result = statement.executeQuery(query);
			
			if(result.next()) {
				
				return new String[] {result.getString("college_id"),result.getString("fullname"),result.getString("semester"),result.getString("level")};
			}
			
			return new String[] {};
		}
		
		public void deleteAccount(String query1, String query2) throws SQLException {
			
			statement.executeUpdate(query1);
			statement.executeUpdate(query2);
			
		}
		
		



}
	
	

