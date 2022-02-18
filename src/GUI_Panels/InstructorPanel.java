// package of the current class
package GUI_Panels;

// importing the DatabaseManage From DBMS Package
import DBMS.DatabaseManage;

// importing libraries
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

public class InstructorPanel implements ActionListener{
	
	// variable(class properties) declaration at the top so that it can be accessed from any part of the class
	// encapsulating by using the key word private
	private JFrame profile;
	protected String username;
	protected JFrame studentView;
	protected JButton dashboard, courses, viewStudent, analyze , crs , view, logout, student, editMarks, saveMarks, exit, viewProfile, editProfile, saveChange, deleteAccount, profileBack;
	protected JLabel loggedIn, teacherName, email, teacher_id, phoneLine;
	protected JPanel leftside, rightside,dash, body, course, search, students, searchPanel, studentsMarks;
	protected JTextField  nameTeacher, teacherEmail, idTeacher, phone;
	protected JComboBox<String> dropdown, studentInfo;
	private JTable assignedCourses, searchedStudent;
	protected JScrollPane scrollPane;
	private String[][] moduleName = null;
	private int lengthAssigned;
	public List<JButton> buttonList = new ArrayList<JButton>();
	private DatabaseManage manage = new DatabaseManage();
	private Border line = BorderFactory.createLineBorder(Color.GRAY);
	private Border wline = BorderFactory.createLineBorder(Color.WHITE);
	
	// class constructor
	public InstructorPanel(String username){
		
		this.username = username;
		
	}
	
	
	// function to add session
	private void addSession() {
		
		try {
			
			DatabaseManage manage = new DatabaseManage();
			
			if(!manage.sessionExistence("SELECT * FROM session WHERE username = '"+ this.username + "' AND indicator = 'Teacher'")) {

				String[] result = manage.getSessionInfo("SELECT username, indicator FROM logs WHERE username = '"+ this.username + "'");
				manage.setSession("INSERT INTO session(`username`, `indicator`) VALUES ('"+ result[1] +"','"+ result[2]+"')");
			}
			
		}
		
		catch(SQLException error) {
			
			error.printStackTrace();
			
		}
	}
	
	
	// profile details panel
	private void profileDetails() {
		
		
		profile = new JFrame();
		profile.setSize(500,260);
	
		teacherName = new JLabel("Name    :");
		teacherName.setBounds(20, 20, 150, 30);
		teacherName.setFont(new Font("Consolas",Font.PLAIN,14));
		profile.add(teacherName);
		
		nameTeacher = new JTextField();
		nameTeacher.setBounds(100, 20, 200, 30);
		nameTeacher.setFont(new Font("Consolas",Font.PLAIN,14));
		nameTeacher.setEditable(false);
		profile.add(nameTeacher);
		
		email = new JLabel("Email   :");
		email.setBounds(20, 70, 150, 30);
		email.setFont(new Font("Consolas",Font.PLAIN,14));
		profile.add(email);
		
		teacherEmail = new JTextField();
		teacherEmail.setBounds(100, 70, 200, 30);
		teacherEmail.setFont(new Font("Consolas",Font.PLAIN,14));
		teacherEmail.setEditable(false);
		profile.add(teacherEmail);
		
		teacher_id = new JLabel("Identity:");
		teacher_id.setBounds(20, 120, 150, 30);
		teacher_id.setFont(new Font("Consolas",Font.PLAIN,14));
		profile.add(teacher_id);
		
		idTeacher = new JTextField();
		idTeacher.setBounds(100, 120, 200, 30);
		idTeacher.setFont(new Font("Consolas",Font.PLAIN,14));
		idTeacher.setEditable(false);
		profile.add(idTeacher);
		
		phoneLine = new JLabel("Phone   :");
		phoneLine.setBounds(20, 170, 150, 30);
		phoneLine.setFont(new Font("Consolas",Font.PLAIN,14));
		profile.add(phoneLine);
		
		phone = new JTextField();
		phone.setBounds(100, 170, 200, 30);
		phone.setFont(new Font("Consolas",Font.PLAIN,14));
		phone.setEditable(false);
		profile.add(phone);

		editProfile = new JButton("Edit Profile");
		editProfile.setBounds(330, 20, 130, 30);
		editProfile.setFocusable(false);
		editProfile.addActionListener(this);
		editProfile.setFont(new Font("Consolas",Font.PLAIN,14));
		profile.add(editProfile);
		
		saveChange = new JButton("Save Change");
		saveChange.setBounds(330, 70, 130, 30);
		saveChange.setFocusable(false);
		saveChange.setEnabled(false);
		saveChange.addActionListener(this);
		saveChange.setFont(new Font("Consolas",Font.PLAIN,14));
		profile.add(saveChange);
		
		deleteAccount = new JButton("Delete A/C");
		deleteAccount.setBounds(330, 120, 130, 30);
		deleteAccount.setFocusable(false);
		deleteAccount.setEnabled(false);
		deleteAccount.addActionListener(this);
		deleteAccount.setFont(new Font("Consolas",Font.PLAIN,14));
		profile.add(deleteAccount);
		
		profileBack = new JButton("Back");
		profileBack.setBounds(330, 170, 130, 30);
		profileBack.setFocusable(false);
		profileBack.addActionListener(this);
		profileBack.setFont(new Font("Consolas",Font.PLAIN,14));
		profile.add(profileBack);
		
		String[] info = null;
		try {
			
			info = manage.getProfileData("SELECT * FROM user_info WHERE username='" + this.username +"' AND indicator = 'Teacher'");
			
		} 
		
		catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		finally {
			
			nameTeacher.setText(info[0]);
			teacherEmail.setText(info[1]);
			idTeacher.setText(info[2]);
			phone.setText(info[3]);
			
		}
		
	
		profile.setLocationRelativeTo(null);
		profile.setResizable(false);
		profile.setLayout(null);
		profile.setVisible(true);
		
	}

	
	// starting the main GUI
	public void startGUI()  {
		
		addSession();
		leftside = new JPanel();
		leftside.setBounds(0,-5,210,450);
		leftside.setLayout(null);
		leftside.setBorder(line);
		
		rightside = new JPanel();
		rightside.setBounds(210,0,575,372);
		rightside.setLayout(null);
		
		dashboard = new JButton();
		dashboard.setText("Dashboard");
		dashboard.setBounds(10, 15, 190, 50);
		dashboard.addActionListener(this);
		dashboard.setFont(new Font("Consolas",Font.PLAIN,15));
		dashboard.setFocusable(false);
		dashboard.setBackground(Color.WHITE);
		
		
		analyze = new JButton();
		analyze.setText("Review");
		analyze.setBounds(10, 70, 190, 50);
		analyze.addActionListener(this);
		analyze.setFont(new Font("Consolas",Font.PLAIN,15));
		analyze.setFocusable(false);
		analyze.setBackground(Color.WHITE);
		
		viewStudent = new JButton();
		viewStudent.setText("View Students");
		viewStudent.setBounds(10, 125, 190, 50);
		viewStudent.addActionListener(this);
		viewStudent.setFont(new Font("Consolas",Font.PLAIN,15));
		viewStudent.setFocusable(false);
		viewStudent.setBackground(Color.WHITE);
		
		
		exit = new JButton();
		exit.setText("Exit");
		exit.setBounds(10, 255, 190, 50);
		exit.addActionListener(this);
		exit.setFont(new Font("Consolas",Font.PLAIN,15));
		exit.setFocusable(false);
		exit.setBackground(Color.WHITE);
		
		leftside.add(dashboard);
		leftside.add(viewStudent);
		leftside.add(analyze);
		leftside.add(exit);

		studentView = new JFrame("Teacher View");
		ImageIcon frameIcon = new ImageIcon("C:\\Users\\ma\\OneDrive\\Documents\\Object Oriented Design and Programming\\C_Management\\Course Management\\images\\main.png");
		studentView.setIconImage(frameIcon.getImage());
		studentView.getContentPane().setBackground(Color.LIGHT_GRAY);
		studentView.setSize(800,350);
		studentView.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		studentView.setLocationRelativeTo(null);
		studentView.setLayout(null);
		studentView.setResizable(false);
		studentView.add(leftside);
		studentView.add(rightside);
		dashboard.doClick();
		studentView.setVisible(true);
		
	}
	

	
	@SuppressWarnings({ "unchecked", "rawtypes", "null" })
	@Override
	public void actionPerformed(ActionEvent e) {
		
		
		// if the dashboard button is pressed dashboard content is displayed(this button is default clicked)
		if(e.getSource() == dashboard) {
			
			rightside.removeAll();
			rightside.revalidate();
			rightside.repaint();
			dashboard.setEnabled(false);
			dashboard.setBorderPainted(false);
			analyze.setEnabled(true);
			analyze.setBorderPainted(true);
			viewStudent.setEnabled(true);
			viewStudent.setBorderPainted(true);
			
			String data[] = null;
			String assigned[] = null;
			
			try {
				
				data = manage.getTeacherDetails("SELECT fullname, email, assigned FROM user_info WHERE username='"+ this.username +"' AND indicator='Teacher'");
				
				assigned = manage.getAssignedCourse("SELECT assigned FROM user_info WHERE username = '"+this.username+"' AND indicator ='Teacher'");
				
				moduleName = new String[assigned.length][4];
				
				lengthAssigned = assigned.length;
				
				int x = 0;
				
				for(String module: assigned) {
					
					moduleName[x] = manage.getModuleNames("SELECT * FROM modules WHERE module_code = '" + module + "'");
					x+=1;
					
				}
				
			}
			
			catch(Exception error) {
				
			}
		
			finally{
				
				dash = new JPanel();
			    dash.setBounds(0,0,575,372);
			    dash.setLayout(null);
			   
			    body = new JPanel();
			    body.setBounds(10,10,550,50);
			    body.setBackground(Color.WHITE);
			    body.setBorder(line);
			    body.setLayout(null);
			    dash.add(body);
	
			    loggedIn = new JLabel();
			    loggedIn.setText("Logged: " +data[0] +"");
	    		loggedIn.setFont(new Font("Consolas",Font.PLAIN,15));
	    		loggedIn.setBounds(17,-25,300,100);
	    		body.add(loggedIn);
	    		
	    		
	    		viewProfile = new JButton();
	    		viewProfile.setText("View Profile");
	    		viewProfile.setBounds(315,10,130,30);
	    		viewProfile.addActionListener(this);
	    		viewProfile.setFont(new Font("Consolas",Font.PLAIN,14));
	    		viewProfile.setFocusable(false);
	    		body.add(viewProfile);

	    		logout = new JButton();
	    		logout.setText("Logout");
	    		logout.setBounds(450,10,85,30);
	    		logout.addActionListener(this);
	    		logout.setFont(new Font("Consolas",Font.PLAIN,14));
	    		logout.setFocusable(false);
	    		body.add(logout);
	    		
	    		JButton assignedModules = new JButton("Assigned Modules");
	    		assignedModules.setBounds(170, 90, 200, 40);
	    		assignedModules.setFocusable(false);
	    		assignedModules.setEnabled(false);
	    		assignedModules.setFont(new Font("Consolas",Font.PLAIN, 14));
	    		rightside.add(assignedModules);
	    		
	    		
	    		String[] column = {"Code", "Modules", "Semester", "Level"};
	    		assignedCourses = new JTable(moduleName, column);
	    		assignedCourses.setRowHeight(30);
				JTableHeader header = assignedCourses.getTableHeader();
				header.setFont(new Font("Consolas",Font.PLAIN, 15));
				assignedCourses.setBorder(wline);
				
				assignedCourses.setFont(new Font("Consolas",Font.PLAIN, 12));
				assignedCourses.setEnabled(false);

				assignedCourses.getColumnModel().getColumn(0).setPreferredWidth(150);
				assignedCourses.getColumnModel().getColumn(1).setPreferredWidth(440);
				assignedCourses.getColumnModel().getColumn(2).setPreferredWidth(100);
				assignedCourses.getColumnModel().getColumn(3).setPreferredWidth(100);
				
				DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
				centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
				assignedCourses.getColumnModel().getColumn(0).setCellRenderer( centerRenderer );assignedCourses.setDefaultRenderer(String.class, centerRenderer);
				assignedCourses.getColumnModel().getColumn(1).setCellRenderer( centerRenderer );assignedCourses.setDefaultRenderer(String.class, centerRenderer);
				assignedCourses.getColumnModel().getColumn(2).setCellRenderer( centerRenderer );assignedCourses.setDefaultRenderer(String.class, centerRenderer);
				assignedCourses.getColumnModel().getColumn(3).setCellRenderer( centerRenderer );assignedCourses.setDefaultRenderer(String.class, centerRenderer);
				
				JScrollPane scrollPane = new JScrollPane(assignedCourses);
				scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
				scrollPane.setBounds(10, 155, 550, 146);
				rightside.add(scrollPane);
				
	    		
	    		rightside.add(dash);
	    		UIManager.put("Button.disabledText", Color.BLACK);
				studentView.add(rightside);
				SwingUtilities.updateComponentTreeUI(studentView);
			
				
				
			}
		
			
		}
		
		// if the analyze button is pressed scoring panel will replace the dashboard panel
		if(e.getSource() == analyze) {
			
			
			rightside.removeAll();
			rightside.revalidate();
			rightside.repaint();
			dashboard.setEnabled(true);
			dashboard.setBorderPainted(true);
			analyze.setEnabled(false);
			analyze.setBorderPainted(false);
			viewStudent.setEnabled(true);
			viewStudent.setBorderPainted(true);
			
			searchPanel = new JPanel();
			searchPanel.setBounds(10, 10, 550, 60);
			searchPanel.setFont(new Font("Consolas",Font.PLAIN,14));
			searchPanel.setBackground(Color.WHITE);
			searchPanel.setLayout(null);
			searchPanel.setBorder(line);
			
			String[] modules = new String[lengthAssigned];
			int x = 0;
			
			for(String[] mod: moduleName) {
				modules[x] = mod[1];
				x+=1;
			}

			dropdown = new JComboBox(modules);
			dropdown.setBounds(10,10, 530, 40);
			dropdown.addActionListener(this);
			dropdown.setFont(new Font("Consolas",Font.PLAIN,15));
			searchPanel.add(dropdown);
			
			studentsMarks = new JPanel();
			studentsMarks.setBounds(10, 80, 550, 180);
			studentsMarks.setBackground(Color.WHITE);
			studentsMarks.setLayout(null);
			
			String[][] data = null;
			
			try {

				data = manage.getResultData("SELECT * FROM report WHERE module_name ='" + dropdown.getSelectedItem() + "'", dropdown.getSelectedItem().toString());
				
				
			}
		
			catch(Exception error) {
				
				
			}
			
			finally {

				String[] column = {"Level", "Student Name", "Semester", "Marks"};
		
				searchedStudent = new JTable(data, column);
				searchedStudent.setRowHeight(30);
				JTableHeader header = searchedStudent.getTableHeader();
				header.setFont(new Font("Consolas",Font.PLAIN, 15));
				searchedStudent.setEnabled(false);
				searchedStudent.setFont(new Font("Consolas",Font.PLAIN, 12));

				
				@SuppressWarnings("serial")
				DefaultTableModel tableModel = new DefaultTableModel(data, column) {

					@Override
				    public boolean isCellEditable(int row, int column) {
				       
				    	if(column == 3) {
				    		
				    		return true;
				    	}
				    	 return false;
				    }
				};
				
				searchedStudent.setModel(tableModel);
				searchedStudent.getColumnModel().getColumn(0).setPreferredWidth(150);
				searchedStudent.getColumnModel().getColumn(1).setPreferredWidth(400);
				searchedStudent.getColumnModel().getColumn(2).setPreferredWidth(150);
				searchedStudent.getColumnModel().getColumn(3).setPreferredWidth(100);
				
				DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
				centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
				searchedStudent.getColumnModel().getColumn(0).setCellRenderer( centerRenderer );searchedStudent.setDefaultRenderer(String.class, centerRenderer);
				searchedStudent.getColumnModel().getColumn(1).setCellRenderer( centerRenderer );searchedStudent.setDefaultRenderer(String.class, centerRenderer);
				searchedStudent.getColumnModel().getColumn(2).setCellRenderer( centerRenderer );searchedStudent.setDefaultRenderer(String.class, centerRenderer);
				searchedStudent.getColumnModel().getColumn(3).setCellRenderer( centerRenderer );searchedStudent.setDefaultRenderer(String.class, centerRenderer);
				
				JScrollPane scrollPane = new JScrollPane(searchedStudent);
				scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
				scrollPane.setBounds(0, 0, 550, 180);
				studentsMarks.add(scrollPane);
				
				editMarks = new JButton();
				editMarks = new JButton("Edit Marks");
				editMarks.setBounds(10,270, 120, 30);
				editMarks.addActionListener(this);
				editMarks.setEnabled(true);
				editMarks.setFont(new Font("Consolas",Font.PLAIN, 14));
				editMarks.setFocusable(false);
				rightside.add(editMarks);
				
				saveMarks = new JButton("Save Marks");
				saveMarks.setBounds(135,270, 120, 30);
				saveMarks.addActionListener(this);
				saveMarks.setEnabled(false);
				saveMarks.setFont(new Font("Consolas",Font.PLAIN, 14));
				saveMarks.setFocusable(false);
				rightside.add(saveMarks);
				
				rightside.add(studentsMarks);
				rightside.add(searchPanel);
				UIManager.put("Button.disabledText", Color.BLACK);
				SwingUtilities.updateComponentTreeUI(studentView);
				
				
			}

		}
		
	
		if(e.getSource() == viewStudent )
		{
			rightside.removeAll();
			rightside.revalidate();
			rightside.repaint();
			dashboard.setEnabled(true);
			dashboard.setBorderPainted(true);
			analyze.setEnabled(true);
			analyze.setBorderPainted(true);
			viewStudent.setEnabled(false);
			viewStudent.setBorderPainted(false);
			
			searchPanel = new JPanel();
			searchPanel.setBounds(10, 10, 550, 60);
			searchPanel.setFont(new Font("Consolas",Font.PLAIN,14));
			searchPanel.setBackground(Color.WHITE);
			searchPanel.setLayout(null);
			searchPanel.setBorder(line);
			
			String[] modules = new String[lengthAssigned];
			int x = 0;
			
			for(String[] mod: moduleName) {
				modules[x] = mod[1];
				x+=1;
			}

			studentInfo = new JComboBox(modules);
			studentInfo.setBounds(10,10, 530, 40);
			studentInfo.addActionListener(this);
			studentInfo.setFont(new Font("Consolas",Font.PLAIN,15));
			searchPanel.add(studentInfo);
			
			studentsMarks = new JPanel();
			studentsMarks.setBounds(10, 80, 550, 220);
			studentsMarks.setBackground(Color.WHITE);
			studentsMarks.setLayout(null);
	
			ArrayList<String> enrolledMod;
			String[][] data = null;
			int i = 0;
			
			try {

				enrolledMod = manage.getUserName("SELECT DISTINCT student_name FROM report WHERE module_name ='" + studentInfo.getSelectedItem() + "'");
				
				data = new String[enrolledMod.size()][4];
				
				for(String mod: enrolledMod)
				{
					data[i] = manage.getUserIntel("SELECT * FROM user_info WHERE fullname = '"+ mod +"'");
					i+=1;
				}
			
			
				
			}
		
			catch(Exception error) {
				
				
			}
			
			finally {

				String[] column = {"Identity", "Student Name", "Semester", "Level"};
		
				searchedStudent = new JTable(data, column);
				searchedStudent.setRowHeight(30);
				JTableHeader header = searchedStudent.getTableHeader();
				header.setFont(new Font("Consolas",Font.PLAIN, 15));
				searchedStudent.setEnabled(false);
				searchedStudent.setFont(new Font("Consolas",Font.PLAIN, 12));
				
				searchedStudent.getColumnModel().getColumn(0).setPreferredWidth(150);
				searchedStudent.getColumnModel().getColumn(1).setPreferredWidth(400);
				searchedStudent.getColumnModel().getColumn(2).setPreferredWidth(150);
				searchedStudent.getColumnModel().getColumn(3).setPreferredWidth(100);
				
				DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
				centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
				searchedStudent.getColumnModel().getColumn(0).setCellRenderer( centerRenderer );searchedStudent.setDefaultRenderer(String.class, centerRenderer);
				searchedStudent.getColumnModel().getColumn(1).setCellRenderer( centerRenderer );searchedStudent.setDefaultRenderer(String.class, centerRenderer);
				searchedStudent.getColumnModel().getColumn(2).setCellRenderer( centerRenderer );searchedStudent.setDefaultRenderer(String.class, centerRenderer);
				searchedStudent.getColumnModel().getColumn(3).setCellRenderer( centerRenderer );searchedStudent.setDefaultRenderer(String.class, centerRenderer);
				
				JScrollPane scrollPane = new JScrollPane(searchedStudent);
				scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
				scrollPane.setBounds(0, 0, 550, 220);
				studentsMarks.add(scrollPane);

				rightside.add(studentsMarks);
				rightside.add(searchPanel);
				UIManager.put("Button.disabledText", Color.BLACK);
				SwingUtilities.updateComponentTreeUI(studentView);
				
				
			}
			
			
			
		}
		
		
		
		if(e.getSource() == editMarks) {
			
			searchedStudent.setEnabled(true);
			saveMarks.setEnabled(true);
			editMarks.setEnabled(false);
			
		}
		
		if(e.getSource() == saveMarks) {
			
			studentView.dispose();
			
			String [][] moduleElements = new String[(int)searchedStudent.getModel().getRowCount()][2];

			for(int i = 0; i < (int)searchedStudent.getModel().getRowCount(); i++) {
				
				int j = 0;
				moduleElements[i] = new String[] {(String)searchedStudent.getModel().getValueAt(i,j+1),(String)dropdown.getSelectedItem(), (String)searchedStudent.getModel().getValueAt(i,j+3)};

			}
			
			
			try {
				
				manage.updateMarks(moduleElements);
				
			}
			
			catch(SQLException error) {
				
				
			}
			
			finally {
				
				new InstructorPanel(this.username).startGUI();
				
				
			}

		}
		
		
		if(e.getSource() == dropdown) {
			
			studentsMarks.removeAll();
			String[][] data = null;
			
			
			try {
				
				data = manage.getResultData("SELECT * FROM report WHERE module_name ='"+ dropdown.getSelectedItem()+"'",dropdown.getSelectedItem().toString());

			}
			
			catch(Exception error) {
				
				
				
			}
			
			finally {
				
				
				String[] column = {"Level", "Student Name", "Semester", "Marks"};
				searchedStudent = new JTable(data, column);
				searchedStudent.setEnabled(false);
				searchedStudent.setRowHeight(30);
				JTableHeader header = searchedStudent.getTableHeader();
				header.setFont(new Font("Consolas",Font.PLAIN, 15));
				searchedStudent.setFont(new Font("Consolas",Font.PLAIN, 12));
				
				@SuppressWarnings("serial")
				DefaultTableModel tableModel = new DefaultTableModel(data, column) {

				    @Override
				    public boolean isCellEditable(int row, int column) {
				       
				    	if(column == 3) {
				    		
				    		return true;
				    	}
				    	 return false;
				    }
				};
				
				searchedStudent.setModel(tableModel);
				searchedStudent.getColumnModel().getColumn(0).setPreferredWidth(150);
				searchedStudent.getColumnModel().getColumn(1).setPreferredWidth(400);
				searchedStudent.getColumnModel().getColumn(2).setPreferredWidth(150);
				searchedStudent.getColumnModel().getColumn(3).setPreferredWidth(100);
				
				DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
				centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
				searchedStudent.getColumnModel().getColumn(0).setCellRenderer( centerRenderer );searchedStudent.setDefaultRenderer(String.class, centerRenderer);
				searchedStudent.getColumnModel().getColumn(1).setCellRenderer( centerRenderer );searchedStudent.setDefaultRenderer(String.class, centerRenderer);
				searchedStudent.getColumnModel().getColumn(2).setCellRenderer( centerRenderer );searchedStudent.setDefaultRenderer(String.class, centerRenderer);
				searchedStudent.getColumnModel().getColumn(3).setCellRenderer( centerRenderer );searchedStudent.setDefaultRenderer(String.class, centerRenderer);
				
				JScrollPane scrollPane = new JScrollPane(searchedStudent);
				scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
				scrollPane.setBounds(0, 0, 550, 180);
				studentsMarks.add(scrollPane);
				
				rightside.add(studentsMarks);
				rightside.add(searchPanel);
				UIManager.put("Button.disabledText", Color.BLACK);

			}

			SwingUtilities.updateComponentTreeUI(studentView);
		}
		
		
		if(e.getSource() == studentInfo) {
			
			studentsMarks.removeAll();
			
			
		
		String[] modules = new String[lengthAssigned];
			int x = 0;
			
			for(String[] mod: moduleName) {
				modules[x] = mod[1];
				x+=1;
			}

			ArrayList<String> enrolledMod;
			String[][] data = null;
			int i = 0;
			
			try {

				enrolledMod = manage.getUserName("SELECT DISTINCT student_name FROM report WHERE module_name ='" + studentInfo.getSelectedItem() + "'");
				
				data = new String[enrolledMod.size()][4];
				
				for(String mod: enrolledMod)
				{
					data[i] = manage.getUserIntel("SELECT * FROM user_info WHERE fullname = '"+ mod +"'");
					i+=1;
				}
			
			
				
			}
		
			catch(Exception error) {
				
				
			}
			
			finally {

				String[] column = {"Identity", "Student Name", "Semester", "Level"};
		
				searchedStudent = new JTable(data, column);
				searchedStudent.setRowHeight(30);
				JTableHeader header = searchedStudent.getTableHeader();
				header.setFont(new Font("Consolas",Font.PLAIN, 15));
				searchedStudent.setEnabled(false);
				searchedStudent.setFont(new Font("Consolas",Font.PLAIN, 12));
				
				searchedStudent.getColumnModel().getColumn(0).setPreferredWidth(150);
				searchedStudent.getColumnModel().getColumn(1).setPreferredWidth(400);
				searchedStudent.getColumnModel().getColumn(2).setPreferredWidth(150);
				searchedStudent.getColumnModel().getColumn(3).setPreferredWidth(100);
				
				DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
				centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
				searchedStudent.getColumnModel().getColumn(0).setCellRenderer( centerRenderer );searchedStudent.setDefaultRenderer(String.class, centerRenderer);
				searchedStudent.getColumnModel().getColumn(1).setCellRenderer( centerRenderer );searchedStudent.setDefaultRenderer(String.class, centerRenderer);
				searchedStudent.getColumnModel().getColumn(2).setCellRenderer( centerRenderer );searchedStudent.setDefaultRenderer(String.class, centerRenderer);
				searchedStudent.getColumnModel().getColumn(3).setCellRenderer( centerRenderer );searchedStudent.setDefaultRenderer(String.class, centerRenderer);
				
				JScrollPane scrollPane = new JScrollPane(searchedStudent);
				scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
				scrollPane.setBounds(0, 0, 550, 220);
				studentsMarks.add(scrollPane);

				rightside.add(studentsMarks);
				rightside.add(searchPanel);
				UIManager.put("Button.disabledText", Color.BLACK);
				SwingUtilities.updateComponentTreeUI(studentView);
				
				
			}
			
			
			
		}
		
		if(e.getSource() == viewProfile) {
			
			studentView.dispose();
			profileDetails();
			
			
			
		}
		
		if(e.getSource() == editProfile) {
			
			editProfile.setEnabled(false);
			saveChange.setEnabled(true);
			deleteAccount.setEnabled(true);
			profileBack.setEnabled(false);
			nameTeacher.setEditable(true);
			teacherEmail.setEditable(true);
			phone.setEditable(true);
			idTeacher.setEditable(true);
			
		}
		
		if(e.getSource() == saveChange) {
			
			
			try {
				manage.updateUserInfo("UPDATE user_info SET fullname='"+ nameTeacher.getText() +"', email='"+ teacherEmail.getText() +"', phone ='"+  phone.getText() + "', college_id ='"+ idTeacher.getText() +"' WHERE username ='"+this.username+"' AND indicator = 'Teacher'");
			} 
			
			catch (SQLException e2) {
				
				e2.printStackTrace();
			}
			
			finally {
				
				profile.dispose();
				
				JLabel er = new JLabel("Task Completed Succesfully!");
				er.setFont(new Font("Consolas",Font.PLAIN, 13));
				JOptionPane.showMessageDialog(null,er,"", JOptionPane.INFORMATION_MESSAGE);
				
				try {
					new InstructorPanel(this.username).startGUI();
				}
				catch (Exception e1) {
					
					e1.printStackTrace();
				}
				
			}

			
		}
		
		if(e.getSource() == deleteAccount) {
			
			String query1 = "DELETE FROM user_info WHERE username='"+this.username+"'";
			String query2 = "DELETE FROM logs WHERE username='"+this.username+"'";
			
			try {
				
				manage.deleteAccount(query1, query2);
			} 
			
			catch (SQLException e1) {
				
				e1.printStackTrace();
			}
			
			finally {
				
				profile.dispose();
				JLabel er = new JLabel("Account has been deleted!");
				er.setFont(new Font("Consolas",Font.PLAIN, 13));
				JOptionPane.showMessageDialog(null,er,"", JOptionPane.INFORMATION_MESSAGE);
				new MainPanel().startGUI();
				
			}
			
		}
		
		if(e.getSource() == profileBack) {
			
			profile.dispose();
			
			new InstructorPanel(this.username).startGUI();
			
		}
		
		
		if(e.getSource() == logout) {
			
			studentView.dispose();
			
			try {
				
				manage.deleteSession("DELETE FROM session WHERE username='"+this.username+"' AND indicator = 'Teacher'");
				
			} 
			
			catch (SQLException e1) {
			
				e1.printStackTrace();
			}
			
			
			new MainPanel().startGUI();
			
		}
		
		if(e.getSource() == exit) {
			
			studentView.dispose();
	
		}

	}
	
}


