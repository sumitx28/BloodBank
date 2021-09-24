package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.BevelBorder;
import java.awt.Color;
import javax.swing.border.MatteBorder;
import javax.swing.border.SoftBevelBorder;

import connection.MyDatabaseConnection;
import net.proteanit.sql.DbUtils;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.UIManager;
import javax.swing.JScrollPane;

public class SearchBuyer extends JFrame {

	private JPanel contentPane;
	private JTextField searchField;
	private String choice = new String();
	private String myDate = new String();
	private String[] checkDate = new String[3];
	private JTable table;
	
//	public static void main(String[] args) {
//		new SearchBuyer();
//	}

	public SearchBuyer() {
		contentPane = new JPanel();
		contentPane.setBackground(UIManager.getColor("Viewport.background"));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		setTitle("Search Buyer");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 825, 600);
		setIconImage(Toolkit.getDefaultToolkit().getImage(MainPage.class.getResource("/images/project logo.jpg")));
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		contentPane.setLayout(null);
		
		JLabel lable = new JLabel("Search Buyer by:");
		lable.setFont(new Font("Shruti", Font.PLAIN, 25));
		lable.setBounds(56, 11, 223, 48);
		contentPane.add(lable);
		
		JComboBox comboBox = new JComboBox();
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				searchField.setForeground(Color.gray);
				choice = (String) comboBox.getSelectedItem();
				searchField.setText("<Enter " + choice + ">");
			}
		});
		comboBox.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		comboBox.setFont(new Font("Shruti", Font.PLAIN, 25));
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Select from options:", "Name", "Blood Group", "Date (dd/MM/yyyy)", "number (+91xxxxxxxxxx)"}));
		comboBox.setBounds(272, 11, 454, 48);
		contentPane.add(comboBox);
		
		searchField = new JTextField();
		searchField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				if(choice.equals("Date (dd/MM/yyyy)")) {
					int length = searchField.getText().length();
					if(length == 2 || length == 5) {
						searchField.setText(searchField.getText() + "/");
					}
				}
			}
		});
		searchField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				searchField.setText("");
				searchField.setForeground(Color.BLACK);
				if(choice.equals("number (+91xxxxxxxxxx)")) {
					searchField.setText("+91");
				}
			}
		});
		searchField.setFont(new Font("Shruti", Font.PLAIN, 25));
		searchField.setBounds(56, 70, 395, 48);
		contentPane.add(searchField);
		searchField.setColumns(10);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.setBackground(Color.GREEN);
		btnSearch.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnSearch.addActionListener(new ActionListener() {
			//checking date
			public void actionPerformed(ActionEvent e) {
				if(choice.equals("Date (dd/MM/yyyy)")) {
					String myDate = searchField.getText();
					checkDate = myDate.split("/"); 
					try {
						if(myDate.length() == 10) {
							if(Integer.parseInt(checkDate[0]) > 31) {
								searchField.setForeground(Color.red);
								JOptionPane.showMessageDialog(null, "Day should be between 1 and 31", "Invalid Date", JOptionPane.WARNING_MESSAGE);
							}
							else if(Integer.parseInt(checkDate[1]) > 12) {
								searchField.setForeground(Color.red);
								JOptionPane.showMessageDialog(null, "Month should be between 1 and 12", "Invalid searchField.set", JOptionPane.WARNING_MESSAGE);
							}
							else
								searchField.setForeground(Color.black);
						}
						else {
							searchField.setForeground(Color.red);
							JOptionPane.showMessageDialog(null, "please write the date in dd/MM/yyyy format", "Invalid Date", JOptionPane.WARNING_MESSAGE);
						}
					}
					catch(NumberFormatException ae) {
						JOptionPane.showMessageDialog(null, "please write the date in dd/MM/yyyy format", "Invalid Date format", JOptionPane.WARNING_MESSAGE);
					}
				}
				//checking number
				if(choice.equals("number (+91xxxxxxxxxx)")) {
					try {
						if(searchField.getText().length() != 13) {
							searchField.setForeground(Color.red);
							JOptionPane.showMessageDialog(null, "Invalid length of number", "Invalid number", JOptionPane.WARNING_MESSAGE);
						}
						else {
							Integer.parseInt(searchField.getText().substring(0, 6));
							Integer.parseInt(searchField.getText().substring(6));
						}
					}
					catch(NumberFormatException ae) {
						searchField.setForeground(Color.red);
						JOptionPane.showMessageDialog(null, "please write a valid number", "Invalid number", JOptionPane.WARNING_MESSAGE);
					}
				}
				//Showing search result in table
				String item = "";
				if(choice.equals("Name")) {
					item = "Name";
				}
				else if(choice.equals("Blood Group")) {
					item = "BG";
				}
				else if(choice.equals("number (+91xxxxxxxxxx)")) {
					item = "Phone";
				}
				if(choice.equals("Date (dd/MM/yyyy)")) {
					item = "cDate";
				}
				
				String search = searchField.getText();
				Connection con =  null;
				Statement st = null;
				ResultSet rs = null;
				try {
					con = MyDatabaseConnection.getConnection();
					String query = "select * from customer where " + item + " like '%" + search + "%';";
					st = con.createStatement();
					rs = st.executeQuery(query);
					table.setModel(DbUtils.resultSetToTableModel(rs));
					st.close();
					rs.close();
					con.close();
				} catch (Exception e1) {
					 JOptionPane.showMessageDialog(null, "Reasons:\n1.Invalid choise\n2.Database connection error", "DATABASE ERROR", JOptionPane.WARNING_MESSAGE);
				}
				finally {
					try {
		                if (st != null) {
		                    st.close();
		                }
		            } catch (SQLException se1) {
		            }
				   try {
					   if(rs != null) {
						 rs.close();
					   }
				   } catch (SQLException se2) {
				   }
				   try {
		                if (con != null) {
		                    con.close();
		                }
		            } catch (SQLException se3) {
		            }
				}
			}
		});
		btnSearch.setFont(new Font("Shruti", Font.BOLD, 25));
		btnSearch.setBounds(56, 129, 670, 48);
		contentPane.add(btnSearch);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				dispose();
			}
		});
		btnBack.setBackground(Color.RED);
		btnBack.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnBack.setFont(new Font("Shruti", Font.BOLD, 25));
		btnBack.setBounds(456, 70, 130, 48);
		contentPane.add(btnBack);
		
		JButton btnReset = new JButton("Reset");
		btnReset.setBounds(596, 70, 130, 48);
		getContentPane().add(btnReset);
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				dispose();
				new SearchBuyer();
			}
		});
		btnReset.setBackground(Color.ORANGE);
		btnReset.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnReset.setFont(new Font("Shruti", Font.BOLD, 25));
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 194, 789, 356);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setBorder(new LineBorder(new Color(72, 61, 139), 2));
		
		setVisible(true);
	}
}
