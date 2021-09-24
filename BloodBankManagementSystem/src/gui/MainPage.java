package gui;

//project created by dharmendra agrawal

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JTextField;
import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.UIManager;
import javax.swing.ImageIcon;
import java.awt.SystemColor;

public class MainPage extends JFrame {

	private JPanel contentPane;

//	public static void main(String[] args) {
//		 new MainPage();
//	}

	public MainPage() {
		getContentPane().setBackground(Color.ORANGE);
		setTitle("BLOOD BANK MANAGEMENT SYSTEM");
		getContentPane().setName("");
		getContentPane().setFont(new Font("FangSong", Font.PLAIN, 46));
		getContentPane().setLayout(null);
		
		JPanel panelButtons = new JPanel();
		panelButtons.setBackground(Color.ORANGE);
		
		panelButtons.setBounds(10, 24, 356, 502);
		getContentPane().add(panelButtons);
		panelButtons.setLayout(new GridLayout(5, 0, 0, 10));
		
		JButton btnNewDonor = new JButton("New Donor");
		btnNewDonor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new NewDonor();
			}
		});
		btnNewDonor.setBackground(UIManager.getColor("Button.background"));
		btnNewDonor.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		btnNewDonor.setMnemonic('N');
		btnNewDonor.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
		panelButtons.add(btnNewDonor);
		
		JButton btnNewCustomer = new JButton("New Customer");
		btnNewCustomer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new NewCustomer();	
			}
		});
		btnNewCustomer.setBackground(UIManager.getColor("Button.background"));
		btnNewCustomer.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		btnNewCustomer.setMnemonic('C');
		btnNewCustomer.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
		panelButtons.add(btnNewCustomer);
		
		JButton btnSearchDonor = new JButton("Search Donor");
		btnSearchDonor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new SearchDonor();
			}
		});
		btnSearchDonor.setMnemonic('D');
		btnSearchDonor.setBackground(UIManager.getColor("Button.background"));
		btnSearchDonor.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		btnSearchDonor.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
		panelButtons.add(btnSearchDonor);
		
		JButton btnSearchBuyer = new JButton("Search Buyer");
		btnSearchBuyer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new SearchBuyer();
			}
		});
		btnSearchBuyer.setMnemonic('B');
		btnSearchBuyer.setBackground(UIManager.getColor("Button.background"));
		btnSearchBuyer.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		btnSearchBuyer.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
		panelButtons.add(btnSearchBuyer);
		
		JButton btnAvailableStock = new JButton("Available Stock");
		btnAvailableStock.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new AvailableStock();
			}
		});
		btnAvailableStock.setMnemonic('A');
		btnAvailableStock.setBackground(UIManager.getColor("Button.background"));
		btnAvailableStock.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
		btnAvailableStock.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		panelButtons.add(btnAvailableStock);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.ORANGE);
		panel_1.setBounds(401, 24, 398, 502);
		getContentPane().add(panel_1);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		JLabel labelImage = new JLabel("");
		labelImage.setIcon(new ImageIcon(MainPage.class.getResource("/images/mainPage.jpg")));
		panel_1.add(labelImage, BorderLayout.CENTER);
		setIconImage(Toolkit.getDefaultToolkit().getImage(MainPage.class.getResource("/images/project logo.jpg")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 825, 600);
		setLocationRelativeTo(null);
		setVisible(true);
	}
}
