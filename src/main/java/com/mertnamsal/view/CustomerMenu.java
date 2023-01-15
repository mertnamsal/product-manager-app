package com.mertnamsal.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.mertnamsal.service.CustomerService;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class CustomerMenu extends JFrame {

	private JPanel contentPane;
	private JTextField tfEmail;
	private JPasswordField passwordField;
	private CustomerService customerService;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CustomerMenu frame = new CustomerMenu();
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
	public CustomerMenu() {
		setResizable(false);
		setTitle("Customer Menu");
		customerService = new CustomerService();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 370, 466);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(40, 40, 40));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		tfEmail = new JTextField();
		tfEmail.setToolTipText("Email");
		tfEmail.setBounds(107, 115, 139, 20);
		contentPane.add(tfEmail);
		tfEmail.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Email");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel.setBounds(25, 117, 40, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setForeground(Color.WHITE);
		lblPassword.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblPassword.setBounds(25, 195, 72, 14);
		contentPane.add(lblPassword);
		
		JButton btnSıgnIn = new JButton("Sign In");
		btnSıgnIn.setForeground(Color.WHITE);
		btnSıgnIn.setBackground(Color.DARK_GRAY);
		btnSıgnIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!tfEmail.getText().isEmpty() && !passwordField.getText().isEmpty()) {
					if(tfEmail.getText().contains("@")) {
						if(!tfEmail.getText().startsWith("@") && !(tfEmail.getText().endsWith("@"))) {
							
							//Kontrolleri yaptık email ve şifreyi kontrol etmeye yolladık
							//
							boolean check =customerService.check(tfEmail.getText(),passwordField.getText());
							if(check) {
								CustomerManager customerManager = new  CustomerManager(tfEmail.getText());
								customerManager.setVisible(true);
								dispose();
							}else {
								JOptionPane.showMessageDialog(null,"Email or password wrong");
							}
							
						}else {
							JOptionPane.showMessageDialog(null, "Email can't starts or ends with @");
						}
					}else {
						JOptionPane.showMessageDialog(null, "Email must contains @");
					}
					
				}else {
					JOptionPane.showMessageDialog(null, "Email or Password can't be empty !!!");
				}
			}
		});
		btnSıgnIn.setBounds(129, 250, 89, 23);
		contentPane.add(btnSıgnIn);
		
		JButton btnSignUp = new JButton("Sign Up");
		btnSignUp.setForeground(Color.WHITE);
		btnSignUp.setBackground(Color.DARK_GRAY);
		btnSignUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Register register = new Register();
				register.setVisible(true);
				dispose();
			}
		});
		btnSignUp.setBounds(129, 295, 89, 23);
		contentPane.add(btnSignUp);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(107, 193, 139, 20);
		contentPane.add(passwordField);
	}
}
