package GUI_Panels;

import DBMS.DatabaseManage;

import java.awt.Color;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.Border;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class ProfileSetPanel implements ActionListener{
	
private String indi, user;

private JFrame frame;
private JLabel lname, lemail, lphone, lcollegeid, lcourse;
private JTextField name, email, phone, collegeId;
private JButton set, title;
private JPanel backPanel;
private JComboBox<String> dropdown;
	
	public ProfileSetPanel(String indi, String user){
		
		this.indi = indi;
		this.user = user;
	
	}
	
	private boolean insertDatabase(String fullname, String email, String phone, String course, String collegeId) throws SQLException {
		

		DatabaseManage manage = new DatabaseManage();
			
		if(this.indi == "Student") {
				
			manage.insertProfileData("INSERT INTO `user_info`(`fullname`, `email`, `phone`, `indicator`, `username`, `enrolled_modules`, `level`, `assigned`, `college_id`, `enrolled_courses`,`semester`,`completed_sem`) "
		    + "VALUES ('"+ fullname +"','"+ email +"','"+ phone +"','"+ this.indi +"','"+ this.user +"','"+"','4','null','"+ collegeId +"','"+ course +"','1','0')");
				
				
		}
			
		else {
				
			manage.insertProfileData("INSERT INTO `user_info`(`fullname`, `email`, `phone`, `indicator`, `username`, `enrolled_modules`, `level`, `assigned`, `college_id`, `enrolled_courses`,`semester`,`completed_sem`) "
			+ "VALUES ('"+ fullname +"','"+ email +"','"+ phone +"','"+ this.indi +"','"+ this.user +"','null','null','"+ "" +"','null','null','0','0')");
				
		}
	
	    return true;		
	}

	
	public void profileGUI() throws SQLException  {
		
		frame = new JFrame();
		frame.setSize(520,335);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		
		Border line = BorderFactory.createLineBorder(Color.GRAY);
		
		backPanel = new JPanel();
		backPanel.setBounds(20, 75, 465, 205);
		backPanel.setBackground(Color.WHITE);
		backPanel.setBorder(line);
		backPanel.setLayout(null);
		
			
			title = new JButton();
			title.setBounds(20, 14, 465, 50);
			title.setEnabled(false);
			title.setFont(new Font("Consolas",Font.PLAIN,15));
			title.setText("Profile Setup");
			frame.add(title);
			
			lname = new JLabel();
			lname.setBounds (20,5,100,40);
			lname.setFont(new Font("Consolas",Font.PLAIN,15));
			lname.setText("Full Name ");
			backPanel.add(lname);
			
			name= new JTextField();
			name.setBounds(20,35,200,30);
			name.setFont(new Font("Consolas",Font.PLAIN,15));
			backPanel.add(name);
			
			lemail = new JLabel();
			lemail.setBounds(20,65,150,40);
			lemail.setFont(new Font("Consolas",Font.PLAIN,15));
			lemail.setText("Email Address");
			backPanel.add(lemail);
			
			email = new JTextField();
			email.setBounds(20,95,200,30);
			email.setFont(new Font("Consolas",Font.PLAIN,15));
			backPanel.add(email);
			
			lphone = new JLabel();
			lphone.setBounds(20,125,150,40);
			lphone.setFont(new Font("Consolas",Font.PLAIN,15));
			lphone.setText("Phone Line");
			backPanel.add(lphone);
			
			phone = new JTextField();
			phone.setBounds(20,155,200,30);
			phone.setFont(new Font("Consolas",Font.PLAIN,15));
			backPanel.add(phone);
			
			if(this.indi == "Student") {
				
				lcollegeid = new JLabel();
				lcollegeid.setBounds(250,5,150,40);
				lcollegeid.setFont(new Font("Consolas",Font.PLAIN,15));
				lcollegeid.setText("College ID");
				backPanel.add(lcollegeid);
				
				collegeId = new JTextField();
				collegeId.setBounds(250,35,200,30);
				collegeId.setFont(new Font("Consolas",Font.PLAIN,15));
				backPanel.add(collegeId);
				
				lcourse = new JLabel();
				lcourse.setText("Courses Available");
				lcourse.setFont(new Font("Consolas",Font.PLAIN,15));
				lcourse.setBounds(250, 71, 200,30);
				backPanel.add(lcourse);
	
				DatabaseManage manage = new DatabaseManage();
				int courseCount = manage.getCourseCount("SELECT COUNT(DISTINCT course_name) AS count FROM courses WHERE course_name !='"+"'");
	
				String[] subject = manage.getCourses("SELECT course_name FROM courses WHERE course_name != '" + "' ORDER BY course_name ASC", courseCount);
		
				dropdown = new JComboBox<String>(subject);
				dropdown.setFont(new Font("Consolas",Font.PLAIN,14));
				dropdown.addActionListener(this);
				dropdown.setBounds(250,95,200,30);
				dropdown.setSelectedItem(" ");
				backPanel.add(dropdown);
				
			}
			
			
			set = new JButton();
			set.setText("Set Profile");
			set.setBounds(250,155,200,30);
			set.setFont(new Font("Consolas",Font.PLAIN,15));
			set.setFocusable(false);
			set.addActionListener(this);
			backPanel.add(set);

		UIManager.put("Button.disabledText", Color.BLACK);
		frame.add(backPanel);
		frame.setLayout(null);
		frame.setResizable(false);
		frame.setVisible(true);
	
}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == set) {
			
			if(this.indi == "Student") {
				
				
				try {
					
					if(insertDatabase(name.getText(),email.getText(),phone.getText(),String.valueOf(dropdown.getSelectedItem()), collegeId.getText())) {
						
						frame.dispose();
						StudentPanel std = new StudentPanel(this.user);
						
						try {
							std.startGUI();
						}
						
						catch(SQLException error) {
							
							
						}
						

					}
					
				} 
				
				catch (SQLException e1) {
					
					e1.printStackTrace();
				}
				
			}
			
			if(this.indi == "Teacher") {
				
				
				try {
					if(insertDatabase(name.getText(),email.getText(),phone.getText(), null , null)) {
						
						frame.dispose();
						InstructorPanel inst = new InstructorPanel(this.user);
						inst.startGUI();
					}
				} 
				
				catch (SQLException e1) {
					
					e1.printStackTrace();
				}
				
			}
						 
		}
		
	}
				
}
