package gui;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class SplashPanel extends JPanel {

	JProgressBar progressBar;

	public SplashPanel() {
		setLayout(new BorderLayout());
		setBorder(BorderFactory.createLineBorder(new Color(0,0,128), 10));
		progressBar = new JProgressBar(1, 100);
		progressBar.setForeground(Color.RED);
		add(progressBar, BorderLayout.SOUTH);
		Icon icon = new ImageIcon(getClass().getResource("/images/splash panel.png"));
		JLabel label = new JLabel(icon);
		label.setText("");
		setBounds(new Rectangle(0, 0, 825, 540));
		add(label, BorderLayout.CENTER);
	}

	public void setProgress(int progress) {
		progressBar.setValue(progress);
	}

	public static void main(String args[]) {
		SplashPanel sp = new SplashPanel();
		JWindow win = new JWindow();
		win.setContentPane(sp);
		win.pack();
		win.setLocationRelativeTo(null);
        win.setVisible(true);
		try {
			for (int i = 1; i <= 100; i++) {
				Thread.sleep(25);
				if(i == 75 || i == 25)
					Thread.sleep(200);
				sp.setProgress(i);
			}
		} catch (Exception e) {
		}
		 win.setVisible(false);
		 win.dispose();
		 new ProjectLogin();
	}
}
