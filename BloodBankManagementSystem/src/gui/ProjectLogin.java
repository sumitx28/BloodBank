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

import pojo.Login;
import dao.LoginDao;;

public class ProjectLogin extends JFrame {

	private JPanel contentPane;
	private JTextField userName;
	private JPasswordField password;
	private boolean userNameFlag = true;
	private boolean passwordFlag = true;
	
//	public static void main(String[] args) {
//					new ProjectLogin();
//	}

	public ProjectLogin() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(ProjectLogin.class.getResource("/images/project logo.jpg")));
		setTitle("LOGIN");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 825, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JLabel lblBloodBankManagement = new JLabel("BLOOD BANK MANAGEMENT SYSTEM LOGIN");
		lblBloodBankManagement.setBackground(Color.WHITE);
		lblBloodBankManagement.setForeground(Color.BLUE);
		lblBloodBankManagement.setFont(new Font("Arial", Font.BOLD, 34));
		contentPane.add(lblBloodBankManagement, BorderLayout.NORTH);
		
		JPanel panel = new JPanel();
		panel.setForeground(Color.BLUE);
		panel.setBackground(Color.ORANGE);
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel lblUserName = new JLabel("User Name:");
		lblUserName.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblUserName.setBounds(206, 112, 105, 48);
		panel.add(lblUserName);
		
		userName = new JTextField();
		userName.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		userName.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				if(userNameFlag == true) {
					userName.setText("");
					userName.setForeground(Color.BLACK);
					userNameFlag = false;
				}
			}
		});
		userName.setForeground(Color.GRAY);
		userName.setHorizontalAlignment(SwingConstants.LEFT);
		userName.setText("Admin");
		userName.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		userName.setBounds(339, 112, 270, 48);
		panel.add(userName);
		userName.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblPassword.setBounds(206, 205, 105, 48);
		panel.add(lblPassword);
		
		password = new JPasswordField();
		password.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		password.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				if(passwordFlag == true) {
					password.setText("");
					password.setForeground(Color.BLACK);
					passwordFlag = false;
				}
			}
		});
		password.setForeground(Color.GRAY);
		password.setText("\u25CF\u25CF\u25CF\u25CF\u25CF\u25CF\u25CF\u25CF\u25CF\u25CF");
		password.setHorizontalAlignment(SwingConstants.LEFT);
		password.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		password.setBounds(339, 205, 270, 48);
		panel.add(password);
		
		JButton btnLogin = new JButton("LOGIN");
		btnLogin.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
		btnLogin.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnLogin.setBounds(208, 296, 401, 48);
		panel.add(btnLogin);
		
		CheckDetails listener = new CheckDetails();
		userName.addActionListener(listener);
		password.addActionListener(listener);
		btnLogin.addActionListener(listener);
		
		JButton btnChange = new JButton("Change Username and Password");
		btnChange.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnChange.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Change();
			}
		});
		btnChange.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
		btnChange.setBounds(206, 369, 403, 48);
		panel.add(btnChange);
		setLocationRelativeTo(null);
		setVisible(true);
	}
	private class CheckDetails implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent evt) {
			//check login username and password
			char[] pswd = password.getPassword();
			String uname= new String(userName.getText());
			LoginDao dao = new LoginDao();
			Login l = new Login(uname, pswd);
			try{
			  if (dao.checkDetails(l)) {
				  setVisible(false);
				  dispose();
				  new MainPage();
		        }
		
			  else {
				  userName.setText("");
				  password.setText("");
				  JOptionPane.showMessageDialog(null, "Please write valid username and password", "Invalid Username or Password", JOptionPane.WARNING_MESSAGE);
		        }
			}
			catch(Exception e) {
				JOptionPane.showMessageDialog(null, "Cannot connect with database", "Connection Error", JOptionPane.WARNING_MESSAGE);
			}
		}
	}
}