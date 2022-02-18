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
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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
public class GenerateReportAdmin extends JFrame implements ActionListener {
	
	private JButton title, level1, level2, level3, backButton, upgradeButton;
	private String fullname, admin;
	private JTable resultLevel1, resultLevel2, resultLevel3;
	DatabaseManage manage = new DatabaseManage();
	Border line = BorderFactory.createLineBorder(Color.WHITE);

	GenerateReportAdmin(String fullname, String username, String admin){
		
		this.admin = admin;
		this.setTitle("Student Remarks");
		ImageIcon frameIcon = new ImageIcon("C:\\Users\\ma\\OneDrive\\Documents\\Object Oriented Design and Programming\\C_Management\\Course Management\\images\\main.png");
		this.setIconImage(frameIcon.getImage());
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
		
		String[] column = {"Code", "Modules", "Semester", "Marks"};
		String[][] data = null;
		
		try {
			
			data = manage.getLevel1Report("SELECT * FROM report WHERE student_name='"+ this.fullname +"' AND semester = '1' OR student_name='"+ this.fullname +"' AND semester = '2'", this.fullname);
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
			
			data2 = manage.getLevel2Report("SELECT * FROM report WHERE student_name='"+ this.fullname +"' AND semester = '3' OR  student_name='"+ this.fullname +"' AND semester = '4'", this.fullname);
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
		resultLevel2.setRowHeight(20);
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
			
			data3 = manage.getLevel3Report("SELECT * FROM report WHERE student_name='"+ this.fullname +"' AND semester = '5' OR student_name='"+ this.fullname +"' AND semester = '6'", this.fullname);
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
		backButton.setBounds(305,60, 80, 30);
		backButton.addActionListener(this);
		backButton.setFont(new Font("Consolas",Font.PLAIN, 14));
		backButton.setFocusable(false);
		this.add(backButton);
		
		upgradeButton = new JButton("Promote Student");
		upgradeButton.setBounds(390,60, 160, 30);
		upgradeButton.addActionListener(this);
		upgradeButton.setFont(new Font("Consolas",Font.PLAIN, 14));
		upgradeButton.setFocusable(false);
		this.add(upgradeButton);
		
		
		
		
		this.setVisible(true);

		
	}
	
	public static void main(String[] args) {
		
		
		new GenerateReportAdmin("Prabesh Bhattrai","Prabesh","Admin");
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == backButton) {
			
			this.dispose();
			
			try {
				
				new AdminPanel(this.admin).startGUI();
			} 
			
			catch (Exception e1) {
				
				e1.printStackTrace();
			}
			
		}
		
		
		if(e.getSource() == upgradeButton) {
			
			int[] data = null;
			
			try {
				
				data = manage.selectLevelStudent("SELECT level, semester, completed_sem FROM user_info WHERE fullname = '" + this.fullname +"' AND indicator = 'Student'");
			} 
			
			catch (SQLException e1) {
				
				e1.printStackTrace();
			}
			
			finally {
				
			   int count = 0;
				
				if(data[0] == 4) {
					
					if(data[1] == 1) {
						
						for(int i = 0; i < (int)resultLevel1.getModel().getRowCount(); i++) {
							
							int j = 0;
							
							int sem = Integer.parseInt((String) resultLevel1.getValueAt(i, j + 2));
							
							
							if(sem == 1) {
								
								int score = Integer.parseInt((String) resultLevel1.getValueAt(i, j + 3)); 
								
								if(score >= 40) {
									count+=1;
								}

							}
		
						}
						
						if(count == 4) {
							
							try {
								
								manage.upgradeSemester("UPDATE user_info SET semester='"+ (data[1] + 1) +"', completed_sem ='"+(data[2] + 1)+"', enrolled_modules = '"+"' WHERE fullname = '"+this.fullname+"'");
								JLabel er = new JLabel("Congratulation: Student Semester Increased");
								er.setFont(new Font("Consolas",Font.PLAIN, 13));
								JOptionPane.showMessageDialog(null,er,"", JOptionPane.INFORMATION_MESSAGE);
							}
							
							catch (SQLException e1) {
								
								e1.printStackTrace();
							}
							
						}

					}
					
					
					if(data[1] == 2) {

						for(int i = 0; i < (int)resultLevel1.getModel().getRowCount(); i++) {
							
							int j = 0;
							
							int sem = Integer.parseInt((String) resultLevel1.getValueAt(i, j + 2));
							
							if(sem == 2) {
								
								int score = Integer.parseInt((String) resultLevel1.getValueAt(i, j + 3)); 
								
								if(score >= 40) {
									
									count+=1;
								}
								
							}
		
						}
						
						if(count == 4) {
							
							try {
								
								manage.upgradeSemester("UPDATE `user_info` SET `semester`='"+ (data[1] + 1) +"', completed_sem ='"+(data[2] + 1)+"', level = '" + (data[0] + 1) +"', enrolled_modules = '"+"' WHERE fullname = '"+this.fullname+"'");
								JLabel er = new JLabel("Congratulation: Student Level Increased");
								er.setFont(new Font("Consolas",Font.PLAIN, 13));
								JOptionPane.showMessageDialog(null,er,"", JOptionPane.INFORMATION_MESSAGE);
							} 
							
							catch (SQLException e1) {
								
								e1.printStackTrace();
							}
							
						}

					}
					
					
				}
				
				
				if(data[0] == 5) {
					
					if(data[1] == 3) {
						
						for(int i = 0; i < (int)resultLevel2.getModel().getRowCount(); i++) {
							
							int j = 0;
							
							int sem = Integer.parseInt((String) resultLevel2.getValueAt(i, j + 2));
							
							
							if(sem == 3) {
								
								int score = Integer.parseInt((String) resultLevel2.getValueAt(i, j + 3)); 
								
								if(score >= 40) {
									count+=1;
								}

							}
		
						}
						
						if(count == 4) {
							
							try {
								
								manage.upgradeSemester("UPDATE user_info SET semester='"+ (data[1] + 1) +"', completed_sem ='"+(data[2] + 1)+"', enrolled_modules = '"+"' WHERE fullname = '"+this.fullname+"'");
								JLabel er = new JLabel("Congratulation: Student Semester Increased");
								er.setFont(new Font("Consolas",Font.PLAIN, 13));
								JOptionPane.showMessageDialog(null,er,"", JOptionPane.INFORMATION_MESSAGE);
							}
							
							catch (SQLException e1) {
								
								e1.printStackTrace();
							}
							
						}

					}
					
					
					if(data[1] == 4) {
						
						
						for(int i = 0; i < (int)resultLevel1.getModel().getRowCount(); i++) {
							
							int j = 0;
							
							int sem = Integer.parseInt((String) resultLevel2.getValueAt(i, j + 2));
							
							if(sem == 4) {
								
								int score = Integer.parseInt((String) resultLevel2.getValueAt(i, j + 3)); 
								
								if(score >= 40) {
									
									count+=1;
								}
								
							}
		
						}
						
						if(count == 4) {
							
							try {
								
								manage.upgradeSemester("UPDATE `user_info` SET `semester`='"+ (data[1] + 1) +"', completed_sem ='"+(data[2] + 1)+"', level = '" + (data[0] + 1) +"', enrolled_modules = '"+"' WHERE fullname = '"+this.fullname+"'");
								JLabel er = new JLabel("Congratulation: Student Level Increased");
								er.setFont(new Font("Consolas",Font.PLAIN, 13));
								JOptionPane.showMessageDialog(null,er,"", JOptionPane.INFORMATION_MESSAGE);
							} 
							
							catch (SQLException e1) {
								
								e1.printStackTrace();
							}
							
						}

					}

				}
				
				
				if(data[0] == 6) {
					
					if(data[1] == 5) {
						
						for(int i = 0; i < (int)resultLevel3.getModel().getRowCount(); i++) {
							
							int j = 0;
							
							int sem = Integer.parseInt((String) resultLevel3.getValueAt(i, j + 2));
							
							
							if(sem == 5) {
								
								int score = Integer.parseInt((String) resultLevel3.getValueAt(i, j + 3)); 
								
								if(score >= 40) {
									count+=1;
								}

							}
		
						}
						
						if(count == 3) {
							
							try {
								
								manage.upgradeSemester("UPDATE user_info SET semester='"+ (data[1] + 1) +"', completed_sem ='"+(data[2] + 1)+"', enrolled_modules = '"+"' WHERE fullname = '"+this.fullname+"'");
								JLabel er = new JLabel("Congratulation: Student Semester Increased");
								er.setFont(new Font("Consolas",Font.PLAIN, 13));
								JOptionPane.showMessageDialog(null,er,"", JOptionPane.INFORMATION_MESSAGE);
							}
							
							catch (SQLException e1) {
								
								e1.printStackTrace();
							}
							
						}

					}
					
					
					if(data[1] == 6) {
						
						for(int i = 0; i < (int)resultLevel3.getModel().getRowCount(); i++) {
							
							int j = 0;
							
							int sem = Integer.parseInt((String) resultLevel3.getValueAt(i, j + 2));
							
							if(sem == 6) {
								
								int score = Integer.parseInt((String) resultLevel3.getValueAt(i, j + 3)); 
								
								if(score >= 40) {
									
									count+=1;
								}
								
							}
		
						}
						
						if(count == 3) {
							
							try {
								
								manage.upgradeSemester("UPDATE `user_info` SET `semester`='"+ (data[1] + 1) +"', completed_sem ='"+(data[2] + 1)+"', level = '" + (data[0] + 1) +"', enrolled_modules = '"+"' WHERE fullname = '"+this.fullname+"'");
								JLabel er = new JLabel("Congratulation: Student Level Increased");
								er.setFont(new Font("Consolas",Font.PLAIN, 13));
								JOptionPane.showMessageDialog(null,er,"", JOptionPane.INFORMATION_MESSAGE);
							} 
							
							catch (SQLException e1) {
								
								e1.printStackTrace();
							}
							
						}

					}
					
					

				}
				
				
				if(data[0] == 7) {
					
					JLabel er = new JLabel("The course has been completed");
					er.setFont(new Font("Consolas",Font.PLAIN, 13));
					JOptionPane.showMessageDialog(null,er,"", JOptionPane.INFORMATION_MESSAGE);
					
					
				}
				
				this.dispose();
				new AdminPanel(this.admin).startGUI();
				
				upgradeButton.setEnabled(false);
			
			
		}
		
		
		
		
	}
	
}
	
}
