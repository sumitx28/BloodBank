package gui;

import javax.swing.JOptionPane;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.UIManager;
import java.awt.SystemColor;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.Color;
import java.awt.Toolkit;
import javax.swing.SwingConstants;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JSeparator;
import pojo.Login;
import dao.LoginDao;;

public class Change extends JFrame {

	private JPanel contentPane;
	private JTextField oldUserName;
	private JPasswordField oldPassword;
	private boolean userNameFlag = true;
	private boolean passwordFlag = true;
	private JTextField newUserName;
	private JPasswordField newPassword;
	
// public static void main(String[] args) {
//					new Change();
//	}

	public Change() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Change.class.getResource("/images/project logo.jpg")));
		setTitle("CHANGE USERNAME AND PASSWORD");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 825, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JLabel lable = new JLabel("CHANGE USERNAME AND PASSWORD");
		lable.setBackground(Color.WHITE);
		lable.setForeground(Color.BLUE);
		lable.setFont(new Font("Arial", Font.BOLD, 34));
		contentPane.add(lable, BorderLayout.NORTH);
		
		JPanel panel = new JPanel();
		panel.setForeground(Color.BLUE);
		panel.setBackground(Color.ORANGE);
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel lblUserName = new JLabel("Old User Name:");
		lblUserName.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblUserName.setBounds(144, 56, 212, 48);
		panel.add(lblUserName);
		
		oldUserName = new JTextField();
		oldUserName.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		oldUserName.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				if(userNameFlag == true) {
					oldUserName.setText("");
					oldUserName.setForeground(Color.BLACK);
					userNameFlag = false;
				}
			}
		});
		oldUserName.setForeground(Color.GRAY);
		oldUserName.setHorizontalAlignment(SwingConstants.LEFT);
		oldUserName.setText("Admin");
		oldUserName.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		oldUserName.setBounds(378, 56, 270, 48);
		panel.add(oldUserName);
		oldUserName.setColumns(10);
		
		JLabel lblPassword = new JLabel("Old Password:");
		lblPassword.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblPassword.setBounds(144, 119, 212, 48);
		panel.add(lblPassword);
		
		oldPassword = new JPasswordField();
		oldPassword.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		oldPassword.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				if(passwordFlag == true) {
					oldPassword.setText("");
					oldPassword.setForeground(Color.BLACK);
					passwordFlag = false;
				}
			}
		});
		oldPassword.setForeground(Color.GRAY);
		oldPassword.setText("\u25CF\u25CF\u25CF\u25CF\u25CF\u25CF\u25CF\u25CF\u25CF\u25CF");
		oldPassword.setHorizontalAlignment(SwingConstants.LEFT);
		oldPassword.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		oldPassword.setBounds(378, 119, 270, 48);
		panel.add(oldPassword);
		
		JLabel lblNewUserName = new JLabel("New User Name:");
		lblNewUserName.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblNewUserName.setBounds(144, 189, 212, 48);
		panel.add(lblNewUserName);
		
		newUserName = new JTextField();
		newUserName.setHorizontalAlignment(SwingConstants.LEFT);
		newUserName.setForeground(Color.BLACK);
		newUserName.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		newUserName.setColumns(10);
		newUserName.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		newUserName.setBounds(378, 189, 270, 48);
		panel.add(newUserName);
		
		JLabel lblNewPassword = new JLabel("New Password:");
		lblNewPassword.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblNewPassword.setBounds(144, 255, 212, 48);
		panel.add(lblNewPassword);
		
		newPassword = new JPasswordField();
		newPassword.setHorizontalAlignment(SwingConstants.LEFT);
		newPassword.setForeground(Color.BLACK);
		newPassword.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		newPassword.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		newPassword.setBounds(378, 255, 270, 48);
		panel.add(newPassword);
		
		JButton btnChange = new JButton("Change");
		btnChange.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				char[] pswd = oldPassword.getPassword();
				String uname= new String(oldUserName.getText());
				LoginDao dao = new LoginDao();
				Login l = new Login(uname, pswd);
				try{
					if (dao.checkDetails(l)) {
					  l.setUname(newUserName.getText());
					  l.setPswd(newPassword.getPassword());
					  if(dao.changeDetails(l)) {
						  setVisible(false);
						  dispose();
						  JOptionPane.showMessageDialog(null, "Username and Password successfully changed\n\nLOGIN WITH YOUR NEW USERNAME AND PASSWORD", "Success", JOptionPane.INFORMATION_MESSAGE);
					  }
					  else {
						  JOptionPane.showMessageDialog(null, "Unable to change username and password", "Some Error", JOptionPane.WARNING_MESSAGE);
					  }
					}			
					else {
					  oldUserName.setText("");
					  oldPassword.setText("");
					  JOptionPane.showMessageDialog(null, "Please write valid old username and old password", "Invalid Username or Password", JOptionPane.WARNING_MESSAGE);
			        }
				}
				catch(Exception e) {
					JOptionPane.showMessageDialog(null, "Cannot connect with database", "Connection Error", JOptionPane.WARNING_MESSAGE);
				}	 
			}
		});
		btnChange.setFont(new Font("Vijaya", Font.BOLD, 28));
		btnChange.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
		btnChange.setBounds(144, 320, 504, 48);
		panel.add(btnChange);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(144, 178, 504, 2);
		panel.add(separator);
		
		JButton btnBack = new JButton("Back");
		btnBack.setForeground(Color.YELLOW);
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
				dispose();
			}
		});
		btnBack.setFont(new Font("Vijaya", Font.BOLD, 28));
		btnBack.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnBack.setBackground(Color.RED);
		btnBack.setBounds(144, 381, 509, 42);
		panel.add(btnBack);
		
		setLocationRelativeTo(null);
		setVisible(true);
	}
}