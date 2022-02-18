// current class package
package GUI_Panels;
// importing DatabaseManage From DBMS package
import DBMS.DatabaseManage;

// importing libraries
import java.awt.Color;
import java.awt.Font;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


@SuppressWarnings("serial")
public class ModuleEditPanel extends JFrame implements ActionListener {
	
	// variable(class properties) declaration at the top so that it can be accessed from any part of the class
	// encapsulating by using the key word private
	private String course, code, username;
	private JLabel courseHead, moduleShort, moduleFull, semester, level;
	private JPanel moduleTable;
	private JTable modules;
	private JButton title, editModules, saveChange, addModules, returnBack, back, applyChange, delete;
	private JFrame manageModule;
	private String[][] data = null;
	private JTextField modCode,modName, modLevel,modSem;
	public DatabaseManage manage = new DatabaseManage();
	
	
	// class constructor
	public ModuleEditPanel(String course, String code, String username){

	
		this.course = course;
		this.code = code;
		this.username = username;
		this.setResizable(false);
		this.setSize(570,390);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		UIManager.put("Button.disabledText", Color.BLACK);
		
		title = new JButton("Course: "+this.course);
		title.setEnabled(false);
		title.setFont(new Font("Consolas",Font.PLAIN,14));
		title.setBounds(20,18,520,30);
		this.add(title);
		
		courseHead = new JLabel(this.course + ":");
		courseHead.setFont(new Font("Consolas",Font.PLAIN, 15));
		courseHead.setBounds(20,15, 300, 30);
		this.add(courseHead);
		
		moduleTable = new JPanel();
		moduleTable.setBounds(15, 50, 520, 285);
		moduleTable.setBackground(Color.WHITE);
		moduleTable.setLayout(null);

		
		try {
			
			data = manage.getSpecificCourses("SELECT * FROM modules WHERE course_names = '"+this.code+"'", this.code);
			
		} 
		
		catch (SQLException e) {
			
			e.printStackTrace();
		}  
		
		finally {
			
			String column[]={"S.N","Code","Modules","Semester", "Level"};      
			
			
			modules = new JTable(data,column);
			modules.setRowHeight(30);
			JTableHeader header = modules.getTableHeader();
			header.setFont(new Font("Consolas",Font.PLAIN, 13));
			
			modules.setFont(new Font("Consolas",Font.PLAIN, 12));
			modules.setEnabled(false);

			modules.getColumnModel().getColumn(0).setPreferredWidth(100);
			modules.getColumnModel().getColumn(1).setPreferredWidth(100);
			modules.getColumnModel().getColumn(2).setPreferredWidth(440);
			modules.getColumnModel().getColumn(3).setPreferredWidth(100);
			modules.getColumnModel().getColumn(4).setPreferredWidth(100);
			
			DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
			centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
			modules.getColumnModel().getColumn(0).setCellRenderer( centerRenderer );modules.setDefaultRenderer(String.class, centerRenderer);
			modules.getColumnModel().getColumn(1).setCellRenderer( centerRenderer );modules.setDefaultRenderer(String.class, centerRenderer);
			modules.getColumnModel().getColumn(2).setCellRenderer( centerRenderer );modules.setDefaultRenderer(String.class, centerRenderer);
			modules.getColumnModel().getColumn(3).setCellRenderer( centerRenderer );modules.setDefaultRenderer(String.class, centerRenderer);
			modules.getColumnModel().getColumn(4).setCellRenderer( centerRenderer );modules.setDefaultRenderer(String.class, centerRenderer);
			
			
			JScrollPane scrollPane = new JScrollPane(modules);
			scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
			scrollPane.setBounds(20, 60, 520, 233);
			moduleTable.add(scrollPane);
			
			
			editModules = new JButton("Edit Modules");
			editModules.setBounds(20, 312, 150, 30);
			editModules.setFont(new Font("Consolas",Font.PLAIN, 14));
			editModules.setFocusable(false);
			editModules.addActionListener(this);
			this.add(editModules);
			
			addModules = new JButton("Add/Delete");
			addModules.setBounds(175, 312, 130, 30);
			addModules.setFont(new Font("Consolas",Font.PLAIN, 14));
			addModules.setFocusable(false);
			addModules.addActionListener(this);
			this.add(addModules);
			
			saveChange = new JButton("Save Modules");
			saveChange.setBounds(310, 312, 130, 30);
			saveChange.setFont(new Font("Consolas",Font.PLAIN, 14));
			saveChange.setFocusable(false);
			saveChange.addActionListener(this);
			saveChange.setEnabled(false);
			this.add(saveChange);
			
			returnBack = new JButton("Back");
			returnBack.setBounds(444, 312, 95, 30);
			returnBack.setFont(new Font("Consolas",Font.PLAIN, 14));
			returnBack.setFocusable(false);
			returnBack.addActionListener(this);
			this.add(returnBack);
			
			
			this.add(scrollPane);
			this.setLayout(null);
			this.setVisible(true);

		}
	
	}
	


	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == editModules) {
			
			modules.setEnabled(true);
			editModules.setEnabled(false);
			addModules.setEnabled(false);
			saveChange.setEnabled(true);
			returnBack.setEnabled(false);
		}
		
		if(e.getSource() == addModules) {
			
			
			editModules.setEnabled(false);
			addModules.setEnabled(false);
			saveChange.setEnabled(false);
			returnBack.setEnabled(false);
			
			
			manageModule = new JFrame();
			manageModule.setSize(300,265);
			manageModule.setResizable(false); 
		
			moduleShort = new JLabel("Module Code:");
			moduleShort.setBounds(10, 5, 100, 30);
			moduleShort.setFont(new Font("Consolas",Font.PLAIN,13));
			manageModule.add(moduleShort);
			
			modCode = new JTextField();
			modCode.setBounds(10, 30, 265, 30);
			modCode.setFont(new Font("Consolas",Font.PLAIN,13));
			manageModule.add(modCode);
			
			moduleFull = new JLabel("Module Name:");
			moduleFull.setBounds(10, 65, 100, 30);
			moduleFull.setFont(new Font("Consolas",Font.PLAIN,13));
			manageModule.add(moduleFull);
			
			modName = new JTextField();
			modName.setBounds(10, 90, 265, 30);
			modName.setFont(new Font("Consolas",Font.PLAIN,13));
			manageModule.add(modName);
			
			semester = new JLabel("Semester:");
			semester.setBounds(10, 120, 100, 30);
			semester.setFont(new Font("Consolas",Font.PLAIN,13));
			manageModule.add(semester);

			modSem= new JTextField();
			modSem.setBounds(10, 145, 120, 30);
			modSem.setFont(new Font("Consolas",Font.PLAIN,13));
			manageModule.add(modSem);
			
			level = new JLabel("Level:");
			level.setBounds(150, 120, 60, 30);
			level.setFont(new Font("Consolas",Font.PLAIN,13));
			manageModule.add(level);

			modLevel = new JTextField();
			modLevel.setBounds(150, 145, 125, 30);
			modLevel.setFont(new Font("Consolas",Font.PLAIN,13));
			manageModule.add(modLevel);

			applyChange = new JButton("Add");
			applyChange.setBounds(10, 185, 85, 30);
			applyChange.setFont(new Font("Consolas",Font.PLAIN,13));
			applyChange.setFocusable(false);
			applyChange.addActionListener(this);
			manageModule.add(applyChange);
	
			delete = new JButton("Delete");
			delete.setBounds(100, 185, 90, 30);
			delete.setFont(new Font("Consolas",Font.PLAIN,13));
			delete.setFocusable(false);
			delete.addActionListener(this);
			manageModule.add(delete);

			back = new JButton("Back");
			back.setBounds(195, 185, 80, 30);
			back.setFont(new Font("Consolas",Font.PLAIN,13));
			back.setFocusable(false);
			back.addActionListener(this);
			manageModule.add(back);

			manageModule.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			manageModule.setLocationRelativeTo(null);
			manageModule.setLayout(null);
			manageModule.setVisible(true);

		}

		
		if(e.getSource() == saveChange) {
			
			
			
			this.dispose();
			
			String [][] moduleElements = new String[(int)modules.getModel().getRowCount()][4];

			for(int i = 0; i < (int)modules.getModel().getRowCount(); i++) {
				
				int j = 0;
				moduleElements[i] = new String[] {(String)modules.getModel().getValueAt(i,j), (String)modules.getModel().getValueAt(i,j+1),(String)modules.getModel().getValueAt(i,j+2),(String)modules.getModel().getValueAt(i,j+3),(String)modules.getModel().getValueAt(i,j+4)};

			}
			
			
			try {
	

				manage.updateDatabase(moduleElements, this.code);
				
			} 
			
			catch (SQLException e1) {
				
				e1.printStackTrace();
			}
			
			finally {
				
				new ModuleEditPanel(this.course,this.code,this.username); 
								
			}
	
	
			
			
		}
		
		if(e.getSource() == returnBack) {
			
			this.dispose();
			new AdminPanel(this.username).startGUI();
			
		}
		
		if(e.getSource() == back) {

			manageModule.dispose();
			new ModuleEditPanel(this.course,this.code,this.username);
	
		}
		
		
		if(e.getSource() == applyChange) {
		
			this.dispose();
			
			try {
				manage.insertModules("INSERT INTO `modules`(`course_modules`, `module_code`, `level`, `course_names`, `semester`) VALUES ('"+modName.getText()+"','"+ modCode.getText()+"','"+ modLevel.getText() +"','"+ this.code+"','"+modSem.getText()+"')");
				manage.resetAutoIncrement();
			} 
			
			catch (SQLException e1) {
				
				e1.printStackTrace();
			}
			
			finally {
				
				modCode.setText("");
				modName.setText("");
				modSem.setText("");
				modLevel.setText("");
				
			}
			
			JLabel er = new JLabel("Task Completed Succesfully!");
			er.setFont(new Font("Consolas",Font.PLAIN, 13));
			JOptionPane.showMessageDialog(null,er,"", JOptionPane.INFORMATION_MESSAGE);
			
			manageModule.dispose();
			new ModuleEditPanel(this.course, this.code, this.username);
	
		}
		
		if(e.getSource() == delete) {
			
			try {
				manage.deleteModules("DELETE FROM modules WHERE module_code='"+ modCode.getText() +"' ");
			} 
			
			catch (Exception error) {
				
				error.printStackTrace();
			}
			
			finally {
				
				modCode.setText("");
				modName.setText("");
				modSem.setText("");
				modLevel.setText("");

			}
			
			JLabel er = new JLabel("Task Completed Succesfully!");
			er.setFont(new Font("Consolas",Font.PLAIN, 13));
			JOptionPane.showMessageDialog(null,er,"", JOptionPane.INFORMATION_MESSAGE);
			
			try {
				
				manage.resetAutoIncrement();
			} 
			
			catch (SQLException e1) {
				
				e1.printStackTrace();
			}
			
			finally{
				
				manageModule.dispose();
				new ModuleEditPanel(this.course, this.code, this.username);
	   }
	}
  }
}
