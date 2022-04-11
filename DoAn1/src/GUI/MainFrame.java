package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTabbedPane;
import javax.swing.JLayeredPane;
import javax.swing.JLabel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.SystemColor;
import java.awt.Font;
import java.awt.Color;

public class MainFrame extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
					frame.setUndecorated(true);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainFrame() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1200, 562);
		contentPane = new JPanel();
		contentPane.setBackground(Color.GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lbClose = new JLabel("");
		ImageIcon icClose = new ImageIcon("Close.png");
		lbClose.setIcon(icClose);
		lbClose.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				System.exit(1);
			}
		});
		lbClose.setBounds(1129, 0, 61, 56);
		contentPane.add(lbClose);
		
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setFont(new Font("Monospaced", Font.BOLD, 25));
		tabbedPane.setBounds(10, 22, 1180, 527);
		contentPane.add(tabbedPane);
		
		ImageIcon icHome = new ImageIcon("Home.png");
		JPanel tabHome = new JPanel();
		tabbedPane.addTab("Home", icHome, tabHome, null);
		
		ImageIcon icSystem = new ImageIcon("System.png");
		JPanel tabSystem = new JPanel();
		tabbedPane.addTab("System", icSystem, tabSystem, null);
		
		JPanel tab3 = new JPanel();
		tabbedPane.addTab("New tab", null, tab3, null);
		
		JPanel tab4 = new JPanel();
		tabbedPane.addTab("New tab", null, tab4, null);
		
		
		
		
		
	}
}
