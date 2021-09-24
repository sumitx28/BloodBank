package gui;

import javax.swing.JOptionPane;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.border.SoftBevelBorder;

import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import java.awt.Insets;
import java.awt.Window.Type;
import javax.swing.ImageIcon;
import javax.swing.UIManager;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFormattedTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.border.BevelBorder;
import dao.*;
import pojo.*;

public class NewDonor extends JFrame {
	private JTextField name;
	private JTextField number;
	private JTextField textField_1;
	private JTextField address;
	private JTextField date;
	private boolean nameFlag;
	private boolean dateFlag;
	private boolean numberFlag;
	private String myDate = new String();
	private String[] checkDate = new String[3];
	
//	public static void main(String[] args) {
//		new NewDonor();		
//	}

	public NewDonor() {
		nameFlag = true;
		dateFlag = true;
		numberFlag = true;
		getContentPane().setBackground(UIManager.getColor("Viewport.background"));	
		JPanel panelLbl = new JPanel();
		panelLbl.setBounds(10, 25, 177, 445);
		getContentPane().add(panelLbl);
		panelLbl.setLayout(new GridLayout(7, 0, 0, 10));
		
		JLabel lblName = new JLabel("Name");
		lblName.setFont(new Font("Times New Roman", Font.BOLD, 26));
		panelLbl.add(lblName);
		
		JLabel lblGender = new JLabel("Gender");
		lblGender.setFont(new Font("Times New Roman", Font.BOLD, 26));
		panelLbl.add(lblGender);
		
		JLabel lblBG = new JLabel("Blood Group");
		lblBG.setFont(new Font("Times New Roman", Font.BOLD, 26));
		panelLbl.add(lblBG);
		
		JLabel lblDate = new JLabel("Date");
		lblDate.setFont(new Font("Times New Roman", Font.BOLD, 26));
		panelLbl.add(lblDate);
		
		JLabel lblPhone = new JLabel("Phone number");
		lblPhone.setFont(new Font("Times New Roman", Font.BOLD, 26));
		panelLbl.add(lblPhone);
		
		JLabel lblAddress = new JLabel("Address");
		lblAddress.setFont(new Font("Times New Roman", Font.BOLD, 26));
		panelLbl.add(lblAddress);
		
		JButton btnBack = new JButton("Back");
		panelLbl.add(btnBack);
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				dispose();
			}
		});
		btnBack.setBackground(Color.RED);
		btnBack.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnBack.setFont(new Font("Shruti", Font.BOLD, 25));
		
		
		JPanel panelTxt = new JPanel();
		panelTxt.setBounds(193, 25, 420, 445);
		getContentPane().add(panelTxt);
		panelTxt.setLayout(new GridLayout(7, 1, 0, 10));
		
		name = new JTextField();
		name.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if(nameFlag == true) {
					name.setText("");
					name.setForeground(Color.BLACK);
					nameFlag = false;
				}
			}
		});
		name.setForeground(Color.GRAY);
		name.setText("Enter your full name");
		name.setFont(new Font("Cambria Math", Font.PLAIN, 26));
		panelTxt.add(name);
		name.setColumns(10);
		
		JComboBox gender = new JComboBox();
		gender.setFont(new Font("Cambria Math", Font.PLAIN, 26));
		gender.setModel(new DefaultComboBoxModel(new String[] {"Select Gender", "Male", "Female", "Other"}));
		panelTxt.add(gender);
		
		JComboBox bg = new JComboBox();
		bg.setFont(new Font("Cambria Math", Font.PLAIN, 26));
		bg.setModel(new DefaultComboBoxModel(new String[] {"Select Blood Group", "A+", "A-", "B+", "B-", "AB+", "AB-", "O+", "O-"}));
		panelTxt.add(bg);
		
		JPanel panel_2 = new JPanel();
		panelTxt.add(panel_2);
		panel_2.setLayout(new BorderLayout(0, 0));
		
		date = new JTextField();
		date.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				int length = date.getText().length();
				if(length == 2 || length == 5) {
					date.setText(date.getText() + "/");
				}
			}
		});
		date.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if(dateFlag == true) {
					date.setText("");
					date.setForeground(Color.BLACK);
					dateFlag = false;
				}
			}
			@Override
			public void focusLost(FocusEvent arg0) {
				String myDate = date.getText();
				checkDate = myDate.split("/"); 
				try {
					if(myDate.length() == 10) {
						if(Integer.parseInt(checkDate[0]) > 31) {
							date.setForeground(Color.red);
							JOptionPane.showMessageDialog(null, "Day should be between 1 and 31", "Invalid Date", JOptionPane.WARNING_MESSAGE);
						}
						else if(Integer.parseInt(checkDate[1]) > 12) {
							date.setForeground(Color.red);
							JOptionPane.showMessageDialog(null, "Month should be between 1 and 12", "Invalid Date", JOptionPane.WARNING_MESSAGE);
						}
						else
							date.setForeground(Color.black);
					}
					else {
						date.setForeground(Color.red);
						JOptionPane.showMessageDialog(null, "please write the date in dd/MM/yyyy format", "Invalid Date", JOptionPane.WARNING_MESSAGE);
					}
				}
				catch(NumberFormatException ae) {
					JOptionPane.showMessageDialog(null, "please write the date in dd/MM/yyyy format", "Invalid Date format", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		date.setForeground(Color.GRAY);
		date.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		date.setHorizontalAlignment(SwingConstants.LEFT);
		date.setText("dd/MM/yyyy  eg. 21/09/2017");
		panel_2.add(date, BorderLayout.CENTER);
		date.setColumns(10);
		
		JButton btnToday = new JButton("Today");
		btnToday.setBackground(new Color(0, 0, 255));
		btnToday.setForeground(new Color(255, 255, 255));
		btnToday.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Date today = new Date();
				SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
				try {
					String dateString = formatter.format(today);
					date.setForeground(Color.black);
					date.setText(dateString);
				} catch (IllegalArgumentException e) {
					date.setForeground(Color.red);
					date.setText("Error: " + e.getMessage());
				}
			}
		});
		btnToday.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnToday.setFont(new Font("Shruti", Font.BOLD, 20));
		panel_2.add(btnToday, BorderLayout.EAST);
		
		number = new JTextField();
		number.setForeground(Color.GRAY);
		number.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				number.setForeground(Color.BLACK);
				if(numberFlag == true) {
					number.setText("+91");
					numberFlag = false;
				}
			}
			@Override
			public void focusLost(FocusEvent arg0) {
				try {
					if(number.getText().length() != 13) {
						number.setForeground(Color.red);
						JOptionPane.showMessageDialog(null, "Invalid length of number", "Invalid number", JOptionPane.WARNING_MESSAGE);
					}
					else {
						Integer.parseInt(number.getText().substring(0, 6));
						Integer.parseInt(number.getText().substring(6));
					}
				}
				catch(NumberFormatException ae) {
					number.setForeground(Color.red);
					JOptionPane.showMessageDialog(null, "please write a valid number", "Invalid number", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		number.setText("+91xxxxxxxxxx");
		number.setFont(new Font("Cambria Math", Font.PLAIN, 26));
		panelTxt.add(number);
		number.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		panelTxt.add(scrollPane);
		
		JTextArea address = new JTextArea();
		address.setFont(new Font("Cambria Math", Font.PLAIN, 26));
		scrollPane.setViewportView(address);
		
		JButton btnReset = new JButton("Reset");
		panelTxt.add(btnReset);
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				dispose();
				new NewDonor();
			}
		});
		btnReset.setBackground(Color.ORANGE);
		btnReset.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnReset.setFont(new Font("Shruti", Font.BOLD, 25));
		
		setTitle("New Donor");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 825, 600);
		setIconImage(Toolkit.getDefaultToolkit().getImage(MainPage.class.getResource("/images/project logo.jpg")));
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.setBounds(10, 477, 603, 55);
		getContentPane().add(btnSubmit);
		btnSubmit.setBackground(Color.GREEN);
		btnSubmit.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnSubmit.setFont(new Font("Shruti", Font.BOLD, 25));
		
		JPanel panel_3 = new JPanel();
		panel_3.setBounds(621, 26, 178, 506);
		getContentPane().add(panel_3);
		panel_3.setLayout(new BorderLayout(0, 0));
		
		JLabel labelImage = new JLabel("");
		labelImage.setIcon(new ImageIcon(NewDonor.class.getResource("/images/New donor.jpg")));
		panel_3.add(labelImage, BorderLayout.CENTER);
		btnSubmit.addActionListener(new ActionListener() {
			//insert data into Donor table
			public void actionPerformed(ActionEvent arg0) {
				DonorDao dao = new DonorDao();
				Donor donor = new Donor();
				donor.setName(name.getText());
				donor.setGender((String) gender.getSelectedItem());
				donor.setBg((String) bg.getSelectedItem());
				donor.setDate(date.getText());
				donor.setPhone(number.getText());
				donor.setAddress(address.getText());
				boolean status = dao.insert(donor);
				if(status) {
					JOptionPane.showMessageDialog(null, "Donor added Successfully", "Message", JOptionPane.INFORMATION_MESSAGE);	
				}
				else {
					JOptionPane.showMessageDialog(null, "REASONS:\n1.Wrong Entry\n2.Could Connect with database", "ERROR", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		setVisible(true);
	}
}
