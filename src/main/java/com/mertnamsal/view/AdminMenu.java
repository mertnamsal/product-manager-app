package com.mertnamsal.view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.mertnamsal.entity.Category;
import com.mertnamsal.entity.Customer;
import com.mertnamsal.entity.Product;
import com.mertnamsal.service.CategoryService;
import com.mertnamsal.service.CustomerService;
import com.mertnamsal.service.ProductService;


import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.border.LineBorder;

public class AdminMenu extends JFrame {

	private JPanel contentPane;
	private ProductService productService;
	private CategoryService categoryService;
	private CustomerService customerService;
	private JTextField tfCategoryName;
	private JTextField tfProductName;
	private JTextField tfProductPrice;
	private JTextField tfProductStock;
	private JComboBox comboBoxCategory;
	private JTable table;


	/**
	 * Create the frame.
	 */
	public AdminMenu() {
		setResizable(false);
		setTitle("Admin Menu");
		productService = new ProductService();
		categoryService = new CategoryService();
		customerService = new CustomerService();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1144, 659);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(40, 40, 40));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Add Category ");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel.setBounds(98, 112, 88, 36);
		contentPane.add(lblNewLabel);
		
		JLabel lblProductEkle = new JLabel("Add Product");
		lblProductEkle.setForeground(Color.WHITE);
		lblProductEkle.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblProductEkle.setBounds(98, 159, 88, 36);
		contentPane.add(lblProductEkle);
		
		JLabel lblListCustomers = new JLabel("List Customers");
		lblListCustomers.setForeground(Color.WHITE);
		lblListCustomers.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblListCustomers.setBounds(98, 212, 88, 36);
		contentPane.add(lblListCustomers);
		
		JLabel lblMenu = new JLabel("Menu");
		lblMenu.setForeground(Color.WHITE);
		lblMenu.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblMenu.setBounds(98, 269, 88, 36);
		contentPane.add(lblMenu);
		
		JButton btnNewButton = new JButton("Add Category ");
		btnNewButton.setBackground(new Color(40, 40, 40));
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String categoryName = tfCategoryName.getText();
				Category categoryCheck = categoryService.findByName(categoryName);
				
				if(!categoryName.isEmpty()) {
					if(categoryCheck == null) {
						Category category = new Category(categoryName);
						boolean isNumericCategoryName = categoryName.chars().allMatch(Character::isDigit);
						if(!isNumericCategoryName) {		
							categoryService.save(category);
							fillComboBoxCategory();
							tfCategoryName.setText("");
						}else {
							JOptionPane.showMessageDialog(null,"All characters can't consist of digits !!!");
						}
					}else {
						JOptionPane.showMessageDialog(null, "This category already exists");
					}
					
					
				}else {
					JOptionPane.showMessageDialog(null,"Category name can't be empty !!!");
				}
				
				
			}
		});
		btnNewButton.setBounds(246, 119, 124, 23);
		contentPane.add(btnNewButton);
		
		JButton btnAddProduct = new JButton("Add Product");
		btnAddProduct.setBackground(new Color(40, 40, 40));
		btnAddProduct.setForeground(Color.WHITE);
		btnAddProduct.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnAddProduct.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String productName = tfProductName.getText();
				String productPrice = tfProductPrice.getText();
				String productStock = tfProductStock.getText();
				Long categoryId = (comboBoxCategory.getSelectedIndex()+1)*1L;
				boolean isNumericProductPrice = productPrice.chars().allMatch(Character::isDigit);
				boolean isNumericProductStock = productStock.chars().allMatch(Character::isDigit);
				
				Product productCheck = productService.findByName(productName);
				
				
				if((isNumericProductPrice && isNumericProductStock)== true ) {
					if(comboBoxCategory.getItemCount() !=0) {
						if(productCheck == null) {
							Category category = categoryService.findById(categoryId);
							Product product = new Product(productName, Integer.parseInt(productStock), Double.parseDouble(productPrice),category);
							
							productService.save(product);
						}else {
							productCheck.setStock(productCheck.getStock()+Integer.parseInt(productStock));
							productCheck.setPrice(Double.parseDouble(productPrice));
							productService.update(productCheck);
						}
						
					}else {
						JOptionPane.showMessageDialog(null,"Category can't be empty");
					}
	
					
				}else {
					JOptionPane.showMessageDialog(null,"Product price and stock must be integer");
				}
				
				
				
			}
		});
		btnAddProduct.setBounds(246, 166, 124, 23);
		contentPane.add(btnAddProduct);
		
		JButton btnListCustomers = new JButton("List Customers");
		btnListCustomers.setBackground(new Color(40, 40, 40));
		btnListCustomers.setForeground(Color.WHITE);
		btnListCustomers.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnListCustomers.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				List<Customer>customers =customerService.listAll();
				
				fillCustomerTable(customers);
			}
		});
		btnListCustomers.setBounds(246, 219, 124, 23);
		contentPane.add(btnListCustomers);
		
		JButton btnMenu = new JButton("Menu");
		btnMenu.setBackground(new Color(40, 40, 40));
		btnMenu.setForeground(Color.WHITE);
		btnMenu.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Menu menu = new Menu();
				menu.setVisible(true);
				dispose();
			}
		});
		btnMenu.setBounds(246, 276, 124, 23);
		contentPane.add(btnMenu);
		
		tfCategoryName = new JTextField();
		tfCategoryName.setBounds(442, 120, 86, 20);
		contentPane.add(tfCategoryName);
		tfCategoryName.setColumns(10);
		
		tfProductName = new JTextField();
		tfProductName.setColumns(10);
		tfProductName.setBounds(442, 167, 86, 20);
		contentPane.add(tfProductName);
		
		tfProductPrice = new JTextField();
		tfProductPrice.setColumns(10);
		tfProductPrice.setBounds(553, 167, 86, 20);
		contentPane.add(tfProductPrice);
		
		tfProductStock = new JTextField();
		tfProductStock.setColumns(10);
		tfProductStock.setBounds(664, 167, 86, 20);
		contentPane.add(tfProductStock);
		
		comboBoxCategory = new JComboBox();
		comboBoxCategory.setForeground(Color.WHITE);
		comboBoxCategory.setBackground(new Color(40, 40, 40));
		comboBoxCategory.setBounds(789, 166, 114, 22);
		contentPane.add(comboBoxCategory);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(410, 246, 638, 336);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setSelectionForeground(new Color(225, 109, 4));
		table.setSelectionBackground(new Color(40, 40, 40));
		table.setBackground(Color.LIGHT_GRAY);
		table.setBorder(new LineBorder(Color.BLACK, 1, true));
		table.setFont(new Font("Tahoma", Font.BOLD, 11));
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"CustomerID", "First Name", "Last Name", "Email", "Password", "TC"
			}
		));
		scrollPane.setViewportView(table);
		
		JLabel lblNewLabel_1 = new JLabel("Category Name");
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_1.setBounds(442, 95, 124, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Product Name");
		lblNewLabel_1_1.setForeground(Color.WHITE);
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_1_1.setBounds(442, 151, 86, 14);
		contentPane.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("Product Price");
		lblNewLabel_1_2.setForeground(Color.WHITE);
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_1_2.setBounds(553, 151, 86, 14);
		contentPane.add(lblNewLabel_1_2);
		
		JLabel lblNewLabel_1_3 = new JLabel("Product Stock");
		lblNewLabel_1_3.setForeground(Color.WHITE);
		lblNewLabel_1_3.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_1_3.setBounds(664, 151, 86, 14);
		contentPane.add(lblNewLabel_1_3);
		fillComboBoxCategory();
	}

	public void fillCustomerTable(List<Customer> customers) {
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		model.setRowCount(0);
		Object[] columns= new Object[6];
		for(int i = 0;i< customers.size();i++) {
			columns[0]=customers.get(i).getId();
			columns[1]=customers.get(i).getFirstname();
			columns[2]=customers.get(i).getLastname();
			columns[3]=customers.get(i).getEmail();
			columns[4]=customers.get(i).getPassword();
			columns[5]=customers.get(i).getTc();
			model.addRow(columns);
		}
		
		
	}
	public void fillComboBoxCategory() {
		List<Category> categories = categoryService.listAll();
		comboBoxCategory.removeAllItems();
		for (int i = 0; i < categories.size(); i++) {
			String categoryName = categories.get(i).getId()+" - "+categories.get(i).getName();
			comboBoxCategory.addItem(categoryName);
		}
	}
}
