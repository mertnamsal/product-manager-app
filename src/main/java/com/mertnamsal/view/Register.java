package com.mertnamsal.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.mertnamsal.entity.Customer;
import com.mertnamsal.service.CustomerService;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Font;

public class Register extends JFrame {

	private JPanel contentPane;
	private JTextField tfFirstName;
	private JTextField tfLastName;
	private JTextField tfEmail;
	private JTextField tfTC;
	private JTextField tfPassword;
	private CustomerService customerService;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Register frame = new Register();
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
	public Register() {
		setResizable(false);
		customerService = new CustomerService();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 510);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(40, 40, 40));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		tfFirstName = new JTextField();
		tfFirstName.setBounds(147, 94, 144, 20);
		contentPane.add(tfFirstName);
		tfFirstName.setColumns(10);
		
		tfLastName = new JTextField();
		tfLastName.setColumns(10);
		tfLastName.setBounds(147, 144, 144, 20);
		contentPane.add(tfLastName);
		
		tfEmail = new JTextField();
		tfEmail.setColumns(10);
		tfEmail.setBounds(147, 193, 144, 20);
		contentPane.add(tfEmail);
		
		tfTC = new JTextField();
		tfTC.setColumns(10);
		tfTC.setBounds(147, 240, 144, 20);
		contentPane.add(tfTC);
		
		tfPassword = new JTextField();
		tfPassword.setColumns(10);
		tfPassword.setBounds(147, 291, 144, 20);
		contentPane.add(tfPassword);
		
		JLabel lblNewLabel = new JLabel("First Name:");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setBounds(50, 97, 74, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblLastName = new JLabel("Last Name:");
		lblLastName.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblLastName.setForeground(Color.WHITE);
		lblLastName.setBounds(50, 147, 74, 14);
		contentPane.add(lblLastName);
		
		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblEmail.setForeground(Color.WHITE);
		lblEmail.setBounds(50, 196, 46, 14);
		contentPane.add(lblEmail);
		
		JLabel lblTc = new JLabel("TC:");
		lblTc.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblTc.setForeground(Color.WHITE);
		lblTc.setBounds(50, 243, 46, 14);
		contentPane.add(lblTc);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblPassword.setForeground(Color.WHITE);
		lblPassword.setBounds(50, 294, 74, 14);
		contentPane.add(lblPassword);
		
		JButton btnSignUp = new JButton("Sing Up");
		btnSignUp.setBackground(Color.DARK_GRAY);
		btnSignUp.setForeground(Color.WHITE);
		btnSignUp.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnSignUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!tfEmail.getText().isEmpty() &&
						!tfFirstName.getText().isEmpty() &&
						!tfLastName.getText().isEmpty() &&
						!tfPassword.getText().isEmpty() &&
						!tfTC.getText().isEmpty()) {
					if(tfEmail.getText().contains("@")) {
						if(!tfEmail.getText().startsWith("@")) {
							if(tfPassword.getText().length()>4) {
								Customer customer = new Customer(tfFirstName.getText(),
										tfLastName.getText(),
										tfEmail.getText(),
										tfPassword.getText(),
										Long.parseLong(tfTC.getText()));
								customerService.save(customer);
								CustomerMenu customerMenu = new CustomerMenu();
								customerMenu.setVisible(true);
								dispose();
								
							}else {
								JOptionPane.showMessageDialog(null, "Password must be longer than 4 character");
							}
						}else {
							JOptionPane.showMessageDialog(null, "Email can't starts with @");
						}
					}else {
						JOptionPane.showMessageDialog(null, "Email must contains @");
					}
				}else {
					JOptionPane.showMessageDialog(null, "Fill empty spaces");
				}
			}
		});
		btnSignUp.setBounds(163, 363, 89, 23);
		contentPane.add(btnSignUp);
		
		JLabel lblNewLabel_1 = new JLabel("REGISTER");
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblNewLabel_1.setBounds(171, 40, 142, 14);
		contentPane.add(lblNewLabel_1);
	}
}
