package GUI_Panels;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import java.awt.event.ActionListener;

import DBMS.DatabaseManage;

@SuppressWarnings("serial")
public class GenerateReport extends JFrame implements ActionListener {
	
	private JButton title, level1, level2, level3, backButton;
	private String fullname, username;
	private JTable resultLevel1, resultLevel2, resultLevel3;
	public DatabaseManage manage = new DatabaseManage();
	public Border line = BorderFactory.createLineBorder(Color.WHITE);

	public GenerateReport(String fullname, String username){
	
		this.setTitle("Student Report");
		ImageIcon frameIcon = new ImageIcon("C:\\Users\\ma\\OneDrive\\Documents\\Object Oriented Design and Programming\\C_Management\\Course Management\\images\\main.png");
		this.setIconImage(frameIcon.getImage());
		this.username = username;
		this.fullname = fullname;
		this.setSize(585,650);
		this.setResizable(false);
		this.setLayout(null);
		this.setLocationRelativeTo(null);
		
		title = new JButton("Grade Sheet: " + this.fullname);
		title.setBounds(20, 10, 530, 40);
		title.setFocusable(false);
		title.setEnabled(false);
		title.setFont(new Font("Consolas",Font.PLAIN,15));
		UIManager.put("Button.disabledText", Color.BLACK);
		this.add(title);

		level1 = new JButton("Level 1");
		level1.setBounds(20, 60, 120, 30);
		level1.setFocusable(false);
		level1.setEnabled(false);
		level1.setFont(new Font("Consolas",Font.PLAIN,13));
		UIManager.put("Button.disabledText", Color.BLACK);
		this.add(level1);
		
		String[] column = {"S.N", "Modules", "Semester", "Marks"};
		String[][] data = null;
		
		try {
			
			data = manage.getLevel1Report("SELECT * FROM report WHERE student_name='"+ this.fullname +"' AND semester = '1' OR  student_name='"+ this.fullname +"' AND semester = '2'", this.fullname);
		} 
		
		catch (SQLException e) {
			
			e.printStackTrace();
		}

		resultLevel1 = new JTable(data, column);
		resultLevel1.setBorder(line);
		resultLevel1.setRowHeight(25);
		JTableHeader header1 = resultLevel1.getTableHeader();
		header1.setFont(new Font("Consolas",Font.PLAIN, 15));
		
		resultLevel1.setFont(new Font("Consolas",Font.PLAIN, 12));
		resultLevel1.setEnabled(false);

		resultLevel1.getColumnModel().getColumn(0).setPreferredWidth(120);
		resultLevel1.getColumnModel().getColumn(1).setPreferredWidth(400);
		resultLevel1.getColumnModel().getColumn(2).setPreferredWidth(150);
		resultLevel1.getColumnModel().getColumn(3).setPreferredWidth(100);
		
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
		resultLevel1.getColumnModel().getColumn(0).setCellRenderer( centerRenderer );
		resultLevel1.getColumnModel().getColumn(1);
		resultLevel1.getColumnModel().getColumn(2).setCellRenderer( centerRenderer );
		resultLevel1.getColumnModel().getColumn(3).setCellRenderer( centerRenderer );
		
		JScrollPane scrollPane = new JScrollPane(resultLevel1);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setBounds(20, 100, 530, 126);
		this.add(scrollPane);
		
		String[][] data2 = null;
		
		try {
			
			data2 = manage.getLevel2Report("SELECT * FROM report WHERE student_name='"+ this.fullname +"' AND semester = '3' OR student_name='"+ this.fullname +"' AND semester = '4'", this.fullname);
		} 
		
		catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		level2 = new JButton("Level 2");
		level2.setBounds(20, 240, 120, 30);
		level2.setFocusable(false);
		level2.setEnabled(false);
		level2.setFont(new Font("Consolas",Font.PLAIN,13));
		UIManager.put("Button.disabledText", Color.BLACK);
		this.add(level2);

		resultLevel2 = new JTable(data2, column);
		resultLevel2.setBorder(line);
		resultLevel2.setRowHeight(25);
		JTableHeader header2 = resultLevel2.getTableHeader();
		header2.setFont(new Font("Consolas",Font.PLAIN, 15));
		
		resultLevel2.setFont(new Font("Consolas",Font.PLAIN, 12));
		resultLevel2.setEnabled(false);
		resultLevel2.getColumnModel().getColumn(0).setPreferredWidth(120);
		resultLevel2.getColumnModel().getColumn(1).setPreferredWidth(400);
		resultLevel2.getColumnModel().getColumn(2).setPreferredWidth(150);
		resultLevel2.getColumnModel().getColumn(3).setPreferredWidth(100);
		
		DefaultTableCellRenderer centerRendererr = new DefaultTableCellRenderer();
		centerRendererr.setHorizontalAlignment(SwingConstants.CENTER);
		
		resultLevel2.getColumnModel().getColumn(0).setCellRenderer( centerRendererr );
		resultLevel2.getColumnModel().getColumn(1);
		resultLevel2.getColumnModel().getColumn(2).setCellRenderer( centerRendererr );
		resultLevel2.getColumnModel().getColumn(3).setCellRenderer( centerRendererr );
		
		JScrollPane scrollPanee = new JScrollPane(resultLevel2);
		scrollPanee.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollPanee.setBounds(20, 280, 530, 126);
		this.add(scrollPanee);

		level3 = new JButton("Level 3");
		level3.setBounds(20, 420, 120, 30);
		level3.setFocusable(false);
		level3.setEnabled(false);
		level3.setFont(new Font("Consolas",Font.PLAIN,13));
		UIManager.put("Button.disabledText", Color.BLACK);
		this.add(level3);
		
		String[][] data3 = null;
		
		try {
			
			data3 = manage.getLevel3Report("SELECT * FROM report WHERE student_name='"+ this.fullname +"' AND semester = '5' OR  student_name='"+ this.fullname +"' AND semester = '6'", this.fullname);
		} 
		
		catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		
		resultLevel3 = new JTable(data3, column);
		resultLevel3.setBorder(line);
		resultLevel3.setRowHeight(25);
		JTableHeader header3 = resultLevel3.getTableHeader();
		header3.setFont(new Font("Consolas",Font.PLAIN, 15));
		
		resultLevel3.setFont(new Font("Consolas",Font.PLAIN, 12));
		resultLevel3.setEnabled(false);

		resultLevel3.getColumnModel().getColumn(0).setPreferredWidth(120);
		resultLevel3.getColumnModel().getColumn(1).setPreferredWidth(400);
		resultLevel3.getColumnModel().getColumn(2).setPreferredWidth(150);
		resultLevel3.getColumnModel().getColumn(3).setPreferredWidth(100);
		
		DefaultTableCellRenderer centerRendererrr = new DefaultTableCellRenderer();
		centerRendererrr.setHorizontalAlignment(SwingConstants.CENTER);

		resultLevel3.getColumnModel().getColumn(0).setCellRenderer( centerRendererrr );
		resultLevel3.getColumnModel().getColumn(1);
		resultLevel3.getColumnModel().getColumn(2).setCellRenderer( centerRendererrr );
		resultLevel3.getColumnModel().getColumn(3).setCellRenderer( centerRendererrr );
	
		JScrollPane scrollPaneee = new JScrollPane(resultLevel3);
		scrollPaneee.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollPaneee.setBounds(20, 460, 530, 126);
		this.add(scrollPaneee);
		
		
		backButton = new JButton();
		Icon backIcon = new ImageIcon("C:\\Users\\ma\\OneDrive\\Documents\\Object Oriented Design and Programming\\C_Management\\Course Management\\images\\exit.png");
		backButton.setIcon(backIcon);
		backButton.setBounds(470, 60, 80, 30);
		backButton.addActionListener(this);
		backButton.setFont(new Font("Consolas",Font.PLAIN, 14));
		backButton.setFocusable(false);
		this.add(backButton);

		this.setVisible(true);

		
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == backButton) {
			
			this.dispose();
			
			try {
				
				new StudentPanel(this.username).startGUI();
			} 
			
			catch (Exception e1) {
				
				e1.printStackTrace();
			}
			
		}

	
	}
	
}

