package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import DB.ConnectDB;
import Model.Account;

import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;

public class SignInFrame extends JFrame {

	private JPanel contentPane;
	private JTextField tfUsername;
	private JPasswordField passwordField;
	private ConnectDB c = new ConnectDB();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SignInFrame frame = new SignInFrame();
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public SignInFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(300, 100, 713, 451);
		contentPane = new JPanel();
		contentPane.setBackground(Color.GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		ImageIcon icCloseB = new ImageIcon("CloseB.png");
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 680, 391);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lbImage = new JLabel("hinh anh");
		ImageIcon icImage = new ImageIcon("hinhanh.jpg");
		lbImage.setIcon(icImage);
		lbImage.setBackground(Color.RED);
		lbImage.setBounds(10, 11, 250, 369);
		panel.add(lbImage);
		
		JLabel lbUsername = new JLabel("Username");
		lbUsername.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lbUsername.setBounds(329, 63, 104, 45);
		panel.add(lbUsername);
		
		JLabel lbPassword = new JLabel("Password");
		lbPassword.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lbPassword.setBounds(330, 152, 104, 45);
		panel.add(lbPassword);
		
		tfUsername = new JTextField();
		tfUsername.setFont(new Font("Tahoma", Font.PLAIN, 20));
		tfUsername.setBounds(448, 75, 197, 33);
		panel.add(tfUsername);
		tfUsername.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Tahoma", Font.PLAIN, 20));
		passwordField.setBounds(448, 162, 197, 33);
		panel.add(passwordField);
		
		JLabel lbLogin = new JLabel("Login");
		lbLogin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				ImageIcon icLoginA = new ImageIcon("LoginA.png");
				lbLogin.setIcon(icLoginA);
				lbLogin.setFont(new Font("Tahoma", Font.BOLD, 20));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				ImageIcon icLoginB = new ImageIcon("LoginB.png");
				lbLogin.setIcon(icLoginB);
				lbLogin.setFont(new Font("Tahoma", Font.PLAIN, 20));
			}
			@Override
			public void mousePressed(MouseEvent e) {
				if(tfUsername.getText().isEmpty() || passwordField.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Please fill in the blank");
				}
				else {
					Account a = new Account(tfUsername.getText(), passwordField.getText());
					if(c.SignIn(a)>0) {	
						a.setRole(c.getRoleInSQL(a));
						if(a.getRole().equals("Admin")) {
							JOptionPane.showMessageDialog(null, "Sign in with admin rights successful");
							dispose();
							MainFrame f = new MainFrame(c.getEmployeeName(tfUsername.getText()));
						}
						else if(a.getRole().equals("Employee")) {
							JOptionPane.showMessageDialog(null, "Sign in with employee rights successful");
							dispose();
							MainFrame f = new MainFrame(c.getEmployeeName(tfUsername.getText()));
							f.tabbedPane.setEnabledAt(5, false);
							f.tabbedPane.setSelectedIndex(1);
						}
					}
					else {
						JOptionPane.showMessageDialog(null, "Wrong password or username");
					}
				}
			}
		});
		ImageIcon icLoginB = new ImageIcon("LoginB.png");
		lbLogin.setIcon(icLoginB);
		lbLogin.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lbLogin.setBounds(448, 261, 117, 61);
		panel.add(lbLogin);
		ImageIcon icSignUpB = new ImageIcon("SignUpB.png");
		
		
		
		
		
		
		setResizable(false);
		setUndecorated(false);
		setVisible(true);
	}
}
