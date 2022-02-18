// current class package
package GUI_Panels;

// importing DatabaseManage from DBMS Package
import DBMS.DatabaseManage;

// importing libraries
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;


@SuppressWarnings("serial")
public class CourseEnroll extends JFrame implements ActionListener {
	
	// variable(class properties) declaration at the top so that it can be accessed from any part of the class
	// encapsulating by using the key word private
	private String username;
	private JPanel backPanel, downPanel;
	private JButton rear1, back1,neww, courseButton;
	
	// creating object of ArrayList
	public ArrayList<JButton> buttons = new ArrayList<>();
	
	// creating object of DatabaseManage 
	public DatabaseManage manage = new DatabaseManage();
	
	// class constructor
	public CourseEnroll(String username) {
		
		this.username = username;
		
		// main frame of this class
		ImageIcon frameIcon = new ImageIcon("C:\\Users\\ma\\OneDrive\\Documents\\Object Oriented Design and Programming\\C_Management\\Course Management\\images\\main.png");
		this.setIconImage(frameIcon.getImage());
		this.setTitle("Enroll Course");
		this.setSize(535, 370);
		this.setLocationRelativeTo(null);
		this.setLayout(null);
		this.setResizable(false);
		
		
		// components added to the frame
		backPanel = new JPanel();
		backPanel.setBounds(0,0,700,500);
		backPanel.setLayout(null);
		
		downPanel = new JPanel();
		downPanel.setBounds(20,10,480,300);
		downPanel.setBackground(Color.WHITE);
		downPanel.setLayout(null);

		rear1 = new JButton(" ");
		rear1.setFocusable(false);
		rear1.setEnabled(false);
		rear1.setBounds(195,-10,500,30);
		downPanel.add(rear1);
		
		back1 = new JButton("");
		back1.setFocusable(false);
		back1.setEnabled(false);
		back1.setBounds(-1,-10,22,30);
		downPanel.add(back1);
		
		neww = new JButton("Courses Available");
		neww.setBounds(20,0,176,35);
		neww.setFont(new Font("Consolas",Font.PLAIN,14));
		neww.setEnabled(false);
		UIManager.put("Button.disabledText", Color.BLACK);
		downPanel.add(neww);
		backPanel.add(downPanel);
		
		// creating object of ArrayList
		ArrayList<String[]> course = new ArrayList<>();
		
		// exception handling
		try {
		
			course = manage.getAllCourse("SELECT course_name, course_full FROM courses WHERE course_name !=''");
		}
		
		catch (Exception e) {
			
			e.printStackTrace();
		}
		
		
		finally {
			
			int x = 0;
			
			// panel to show all of the courses
			JPanel panel = new JPanel();
			panel.setBackground(Color.WHITE);
			panel.setBounds(15,40,550,280);
			panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
			panel.add(Box.createRigidArea(new Dimension(0, 10)));
			JScrollPane topPane = new JScrollPane(panel);
	        topPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
	        topPane.setBounds(15, 50, 450, 238);
	        
	        // creating JButton in a loop and adding it to the arraylist for reference
	        for(String[] crs: course) {
				
				courseButton = new JButton( "("+ crs[0] +"): "+ crs[1]);
				courseButton.setHorizontalAlignment(SwingConstants.LEFT);
				courseButton.setFont(new Font("Consolas",Font.PLAIN,13));
				courseButton.setFocusable(false);
			    buttons.add(courseButton);
			    x+=1;
			}
	        
	        // adding all the buttons from the arraylist to the frame
	        for(JButton butt : buttons) {
	 	    	
		 	    butt.addActionListener(this);
		 	    	
		 	    if(x <= 3) {
		 	    		
		 	    		butt.setMaximumSize(new Dimension(420, 70));
		 		        butt.setPreferredSize(new Dimension(395, 65));

		 	    }
		 	    	
		 	    else {
		 	    		
		 	    		butt.setMaximumSize(new Dimension(410, 70));
		 		        butt.setPreferredSize(new Dimension(395, 65));
		 	    		
		 	    }
		 	    	
		 	    panel.add(butt);
		 	    panel.add(Box.createRigidArea(new Dimension(20, 10)));
		 	    	
		 	  }
	        
	        // adding the topPane where all the courses button are to the downpanel
	        downPanel.add(topPane);
			
		}
		
		// adding the last component to the frame
		this.add(backPanel);
		this.setVisible(true);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		// if the component pressed is a button
		if(e.getSource() instanceof JButton) {

			String course = (((JButton) e.getSource()).getText());
			String[] course_split = course.split(":");
			String finalized = course_split[0].replace(")", "").replace("(","");
			
			// excecption handling
			try {
				// updating the column enrolled_courses in the database
				manage.updateUserCourse("UPDATE user_info SET  enrolled_courses ='"+ finalized + "', semester ='1', level = '4' WHERE username = '"+this.username+"'");
			} 
			
			catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			finally {
				
				// closing the main panel
				this.dispose();
				
				// exception handling
				try {
					
					// calling the StudenPanel constructor and calling the startGUI() methods
					new StudentPanel(this.username).startGUI();
				} 
				
				catch (SQLException e1) {
					
					e1.printStackTrace();
				}
				
			}
		}
		
	}

}
