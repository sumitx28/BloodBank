package gui;

import connection.*;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
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

import net.proteanit.sql.DbUtils;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.UIManager;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import javax.swing.JScrollPane;

public class AvailableStock extends JFrame {

	private JPanel contentPane;
	private JTable table;

//	public static void main(String[] args) {
//		new AvailableStock();
//	}

	public AvailableStock() {
		contentPane = new JPanel();
		contentPane.setBackground(UIManager.getColor("Viewport.background"));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		setTitle("AVAILABE STOCK");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 825, 600);
		setIconImage(Toolkit.getDefaultToolkit().getImage(MainPage.class.getResource("/images/project logo.jpg")));
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		contentPane.setLayout(null);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				dispose();
			}
		});
		btnCancel.setBackground(Color.RED);
		btnCancel.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnCancel.setFont(new Font("Shruti", Font.BOLD, 25));
		btnCancel.setBounds(429, 24, 370, 48);
		contentPane.add(btnCancel);
		
		JButton btnRefresh = new JButton("Refresh");
		btnRefresh.setBounds(10, 24, 370, 48);
		getContentPane().add(btnRefresh);
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				dispose();
				new AvailableStock();
			}
		});
		btnRefresh.setBackground(Color.GREEN);
		btnRefresh.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnRefresh.setFont(new Font("Shruti", Font.BOLD, 25));
		
		table = new JTable();
		table.setBorder(new LineBorder(new Color(75, 0, 130), 2));
		table.setBounds(290, 135, 275, 265);
		contentPane.add(table);
		Connection con =  null;
		Statement st = null;
		ResultSet rs = null;
		try {
			con = MyDatabaseConnection.getConnection();
			String query = "select * from blood;";
			st = con.createStatement();
			rs = st.executeQuery(query);
			table.setModel(DbUtils.resultSetToTableModel(rs));
			st.close();
			rs.close();
			con.close();
		} catch (Exception e1) {
			 JOptionPane.showMessageDialog(null, "problem in connecting with database", "DATABASE ERROR", JOptionPane.WARNING_MESSAGE);
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
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		setVisible(true);
	}
}
