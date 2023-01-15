package com.mertnamsal.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import com.mertnamsal.entity.Customer;
import com.mertnamsal.entity.Product;
import com.mertnamsal.entity.ProductEvaluate;
import com.mertnamsal.service.CustomerService;
import com.mertnamsal.service.ProductEvaluateService;
import com.mertnamsal.service.ProductService;

import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JScrollBar;
import javax.swing.JSlider;
import javax.swing.JProgressBar;
import javax.swing.JSpinner;
import javax.swing.JToggleButton;
import javax.swing.JRadioButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Toolkit;
import java.awt.Font;
import javax.swing.JLabel;

public class CustomerManager extends JFrame {

	private JPanel contentPane;
	private JTable tableProduct;
	private ProductService productService;
	private CustomerService customerService;
	private ProductEvaluateService productEvaluateService;
	private JTable tableProductEvaluate;
	private static String email;
	private JTextField tfComment;
	private JRadioButton rdbtn5;
	private JRadioButton rdbtn4;
	private JRadioButton rdbtn3;
	private JRadioButton rdbtn2;
	private JRadioButton rdbtn1;
	private JButton btnShowPurchasedProducts;

	/**
	 * Launch the application.
	 */
	
	/**
	 * Create the frame.
	 */
	public CustomerManager(String email) {
		setTitle("Customer Manager");
		productService = new ProductService();
		customerService = new CustomerService();
		
		productEvaluateService = new ProductEvaluateService();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1123, 611);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(40, 40, 40));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnShowAllProducts = new JButton("Show All Products");
		btnShowAllProducts.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnShowAllProducts.setForeground(Color.WHITE);
		btnShowAllProducts.setBackground(Color.DARK_GRAY);
		btnShowAllProducts.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				List<Product> products = productService.listAll();
				
				tableProduct.setModel(new DefaultTableModel(
						new Object[][] {
						},
						new String[] {
							"Product ID", "Product Name", "Product Price", "Product Stock"
						}
					));
				fillProductTable(products);
			}
		});
		btnShowAllProducts.setBounds(49, 82, 137, 23);
		contentPane.add(btnShowAllProducts);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(49, 176, 462, 342);
		contentPane.add(scrollPane);
		
		tableProduct = new JTable();
		tableProduct.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				int row = tableProduct.getSelectedRow();
				String productName = (String) tableProduct.getValueAt(row, 1);
				Product product = productService.findByName(productName);
				List<ProductEvaluate> list = productEvaluateService.listProductComments(product.getId());
				fillCommentTable(list);
			}
		});
		tableProduct.setGridColor(Color.LIGHT_GRAY);
		tableProduct.setSelectionForeground(new Color(225, 109, 4));
		tableProduct.setSelectionBackground(new Color(40, 40, 40));
		tableProduct.setFont(new Font("Tahoma", Font.BOLD, 11));
		tableProduct.setBackground(Color.LIGHT_GRAY);
		tableProduct.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		tableProduct.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ProductID", "Product Name", "Product Price", "Product Stock"
			}
		));
		scrollPane.setViewportView(tableProduct);
		
		JButton btnBuy = new JButton("Buy");
		btnBuy.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnBuy.setBackground(Color.DARK_GRAY);
		btnBuy.setForeground(Color.WHITE);
		btnBuy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(!tableProduct.getColumnName(0).equalsIgnoreCase("Quantity")) {
					
					if(tableProduct.getSelectedRow()!= -1) {
						int row =tableProduct.getSelectedRow();
						long id =   (long) (tableProduct.getValueAt(row, 0)) ;
						Product product = productService.getById(id);
						int count = Integer.parseInt(JOptionPane.showInputDialog("How many units would you like to buy ?"));
						if(product.getStock()-count>0) {
							product.setStock(product.getStock()-count);
							productService.update(product);
							Customer customer = customerService.findByEmail(email);
							if(customer.getProduct() != null) {
								List<Product> products = customer.getProduct();
								for(int i = 0 ;i<count;i++) {
									products.add(product);
								}
								customer.setProduct(products);
							}else {
								customer.setProduct(Arrays.asList(product));
							}
							
							customerService.update(customer);
							
							tableProduct.setModel(new DefaultTableModel(
									new Object[][] {
									},
									new String[] {
										"Product ID", "Product Name", "Product Price", "Product Stock"
									}
								));
							fillProductTable(productService.listAll());
						}else {
							JOptionPane.showMessageDialog(null, "Run out of stock");
						}
					}
					
				}else {
					JOptionPane.showMessageDialog(null, "List products first");
				}
				
				
				
			}
		});
		btnBuy.setBounds(225, 82, 89, 23);
		contentPane.add(btnBuy);
		
		JButton btnShowLessThan10Stock = new JButton("Show Less Than 10 Stock");
		btnShowLessThan10Stock.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnShowLessThan10Stock.setBackground(Color.DARK_GRAY);
		btnShowLessThan10Stock.setForeground(Color.WHITE);
		btnShowLessThan10Stock.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tableProduct.setModel(new DefaultTableModel(
						new Object[][] {
						},
						new String[] {
							"Product ID", "Product Name", "Product Price", "Product Stock"
						}
					));
				List<Product> products = productService.listLessThan10Stock();
				fillProductTable(products);
			}
		});
		btnShowLessThan10Stock.setBounds(49, 128, 184, 23);
		contentPane.add(btnShowLessThan10Stock);
		
		JButton btnNewButton = new JButton("Add a Comment");
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton.setBackground(Color.DARK_GRAY);
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!tfComment.getText().isEmpty()) {
					if(rdbtn1.isSelected() || rdbtn2.isSelected() || rdbtn3.isSelected() || rdbtn4.isSelected() || rdbtn5.isSelected()) {
						if(tableProduct.getColumnName(0).equalsIgnoreCase("Quantity")) {
							if(tableProduct.getSelectedRow()!= -1) {
								int row =tableProduct.getSelectedRow();
								String productName =  (String) (tableProduct.getValueAt(row, 1)) ;
								Product product = productService.findByName(productName);
								Customer customer = customerService.findByEmail(email);
								
								if(rdbtn1.isSelected()) {
									ProductEvaluate productEvaluate = new ProductEvaluate(tfComment.getText(),Double.parseDouble(rdbtn1.getText()),product, customer);
									productEvaluateService.save(productEvaluate);
									List<ProductEvaluate> list = productEvaluateService.listAll();
									fillCommentTable(list);
								}
								if(rdbtn2.isSelected()) {
									ProductEvaluate productEvaluate = new ProductEvaluate(tfComment.getText(),Double.parseDouble(rdbtn2.getText()),product, customer);
									productEvaluateService.save(productEvaluate);
									List<ProductEvaluate> list = productEvaluateService.listAll();
									fillCommentTable(list);
								}
								if(rdbtn3.isSelected()) {
									ProductEvaluate productEvaluate = new ProductEvaluate(tfComment.getText(),Double.parseDouble(rdbtn3.getText()),product, customer);
									productEvaluateService.save(productEvaluate);
									List<ProductEvaluate> list = productEvaluateService.listAll();
									fillCommentTable(list);
								}
								if(rdbtn4.isSelected()) {
									ProductEvaluate productEvaluate = new ProductEvaluate(tfComment.getText(),Double.parseDouble(rdbtn4.getText()),product, customer);
									productEvaluateService.save(productEvaluate);
									List<ProductEvaluate> list = productEvaluateService.listAll();
									fillCommentTable(list);
								}
								if(rdbtn5.isSelected()) {
									ProductEvaluate productEvaluate = new ProductEvaluate(tfComment.getText(),Double.parseDouble(rdbtn5.getText()),product, customer);
									productEvaluateService.save(productEvaluate);
									List<ProductEvaluate> list = productEvaluateService.listAll();
									fillCommentTable(list);
								}
						}else {
							JOptionPane.showMessageDialog(null, "Please click on the product you want to comment");
						}
						
						}else {
							JOptionPane.showMessageDialog(null, "Only comment on products you have purchased");
						}
						
						
					}else {
						JOptionPane.showMessageDialog(null, "Mark your score!");
					}
				}else {
					tfComment.setText("This area must be filled!");
				}
			}
		});
		btnNewButton.setBounds(572, 58, 137, 23);
		contentPane.add(btnNewButton);
		
		JButton btnShowComments = new JButton("Show All Comments");
		btnShowComments.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnShowComments.setBackground(Color.DARK_GRAY);
		btnShowComments.setForeground(Color.WHITE);
		btnShowComments.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				List<ProductEvaluate> list = productEvaluateService.listAll();
				fillCommentTable(list);
			}
		});
		btnShowComments.setBounds(925, 58, 154, 23);
		contentPane.add(btnShowComments);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(572, 176, 507, 342);
		contentPane.add(scrollPane_1);
		
		tableProductEvaluate = new JTable();
		tableProductEvaluate.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		tableProductEvaluate.setFont(new Font("Tahoma", Font.BOLD, 11));
		tableProductEvaluate.setSelectionForeground(new Color(225, 109, 4));
		tableProductEvaluate.setSelectionBackground(Color.BLACK);
		tableProductEvaluate.setGridColor(Color.LIGHT_GRAY);
		tableProductEvaluate.setBackground(Color.LIGHT_GRAY);
		tableProductEvaluate.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Id", "Comment", "Score", "Product", "Customer"
			}
		));
		tableProductEvaluate.getColumnModel().getColumn(0).setPreferredWidth(50);
		tableProductEvaluate.getColumnModel().getColumn(1).setPreferredWidth(120);
		tableProductEvaluate.getColumnModel().getColumn(2).setPreferredWidth(50);
		scrollPane_1.setViewportView(tableProductEvaluate);
		
		tfComment = new JTextField();
		tfComment.setBounds(572, 114, 299, 20);
		contentPane.add(tfComment);
		tfComment.setColumns(10);
		
		rdbtn5 = new JRadioButton("5");
		rdbtn5.setForeground(Color.WHITE);
		rdbtn5.setFont(new Font("Tahoma", Font.BOLD, 11));
		rdbtn5.setBackground(new Color(40, 40, 40));
		rdbtn5.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				rdbtn1.setSelected(false);
				rdbtn2.setSelected(false);
				rdbtn3.setSelected(false);
				rdbtn4.setSelected(false);
			}
		});
		rdbtn5.setBounds(893, 113, 36, 23);
		contentPane.add(rdbtn5);
		
		rdbtn4 = new JRadioButton("4");
		rdbtn4.setForeground(Color.WHITE);
		rdbtn4.setFont(new Font("Tahoma", Font.BOLD, 11));
		rdbtn4.setBackground(new Color(40, 40, 40));
		rdbtn4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				rdbtn1.setSelected(false);
				rdbtn2.setSelected(false);
				rdbtn3.setSelected(false);
				rdbtn5.setSelected(false);
			}
		});
		rdbtn4.setBounds(931, 113, 36, 23);
		contentPane.add(rdbtn4);
		
		rdbtn3 = new JRadioButton("3");
		rdbtn3.setBackground(new Color(40, 40, 40));
		rdbtn3.setForeground(Color.WHITE);
		rdbtn3.setFont(new Font("Tahoma", Font.BOLD, 11));
		rdbtn3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				rdbtn1.setSelected(false);
				rdbtn2.setSelected(false);
				rdbtn5.setSelected(false);
				rdbtn4.setSelected(false);
			}
		});
		rdbtn3.setBounds(969, 113, 36, 23);
		contentPane.add(rdbtn3);
		
		rdbtn2 = new JRadioButton("2");
		rdbtn2.setForeground(Color.WHITE);
		rdbtn2.setFont(new Font("Tahoma", Font.BOLD, 11));
		rdbtn2.setBackground(new Color(40, 40, 40));
		rdbtn2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				rdbtn1.setSelected(false);
				rdbtn5.setSelected(false);
				rdbtn3.setSelected(false);
				rdbtn4.setSelected(false);
			}
		});
		rdbtn2.setBounds(1007, 113, 36, 23);
		contentPane.add(rdbtn2);
		
		rdbtn1 = new JRadioButton("1");
		rdbtn1.setBackground(new Color(40, 40, 40));
		rdbtn1.setFont(new Font("Tahoma", Font.BOLD, 11));
		rdbtn1.setForeground(Color.WHITE);
		rdbtn1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				rdbtn5.setSelected(false);
				rdbtn2.setSelected(false);
				rdbtn3.setSelected(false);
				rdbtn4.setSelected(false);
			}
		});
		rdbtn1.setBounds(1045, 113, 36, 23);
		contentPane.add(rdbtn1);
		
		JButton btnNewButton_1 = new JButton("Logout");
		btnNewButton_1.setBackground(new Color(40, 40, 40));
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton_1.setForeground(new Color(255, 255, 255));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CustomerMenu customerMenu = new CustomerMenu();
				customerMenu.setVisible(true);
				dispose();
			}
		});
		btnNewButton_1.setBounds(990, 538, 89, 23);
		contentPane.add(btnNewButton_1);
		
		btnShowPurchasedProducts = new JButton("Show purchased products");
		btnShowPurchasedProducts.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tableProduct.setModel(new DefaultTableModel(
						new Object[][] {
						},
						new String[] {
							"Quantity", "Product Name", "Product Price", "Total Price"
						}
					));
				
				
				Customer customer = customerService.findByEmail(email);
				List<Product> list = customer.getProduct();
				
				HashMap<Product,Integer> uniqueList = new HashMap<>();
				

				for (int i = 0; i <list.size(); i++) {
					if(uniqueList.containsKey(list.get(i))) {
						uniqueList.replace(list.get(i),uniqueList.get(list.get(i))+1);
					}else {
						uniqueList.put(list.get(i), 1);
					}
				}
				fillProductTableMAP(uniqueList);
				
				
			}
		});
		btnShowPurchasedProducts.setForeground(Color.WHITE);
		btnShowPurchasedProducts.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnShowPurchasedProducts.setBackground(Color.DARK_GRAY);
		btnShowPurchasedProducts.setBounds(263, 128, 184, 23);
		contentPane.add(btnShowPurchasedProducts);
		
		JLabel lblEmail = new JLabel("");
		lblEmail.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblEmail.setForeground(Color.WHITE);
		lblEmail.setBounds(49, 25, 137, 20);
		contentPane.add(lblEmail);
		
		Customer c = customerService.findByEmail(email);
		String fullname =c.getFirstname().substring(0, 1).toUpperCase()+c.getFirstname().substring(1).toLowerCase()+" "
						+c.getLastname().substring(0, 1).toUpperCase()+c.getLastname().substring(1).toLowerCase();
		lblEmail.setText(fullname);
		
		JButton btnMyComments = new JButton("My Comments");
		btnMyComments.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Customer customer = customerService.findByEmail(email);
				List<ProductEvaluate> list = productEvaluateService.listMyComments(customer.getId());
				fillCommentTable(list);
			}
		});
		btnMyComments.setForeground(Color.WHITE);
		btnMyComments.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnMyComments.setBackground(Color.DARK_GRAY);
		btnMyComments.setBounds(747, 58, 137, 23);
		contentPane.add(btnMyComments);
	}
	public void fillProductTable(List<Product> list) {
		DefaultTableModel model = (DefaultTableModel) tableProduct.getModel();
		model.setRowCount(0);
		Object[] columns= new Object[4];
		for(int i = 0;i< list.size();i++) {
			columns[0]=list.get(i).getId();
			columns[1]=list.get(i).getName();
			columns[2]=list.get(i).getPrice();
			columns[3]=list.get(i).getStock();
			model.addRow(columns);
		}

	}
	
	public void fillProductTableMAP(HashMap<Product,Integer> list) {
		DefaultTableModel model = (DefaultTableModel) tableProduct.getModel();
		model.setRowCount(0);
		Object[] columns= new Object[4];
		for (Map.Entry<Product, Integer> entry : list.entrySet()) {
			columns[0] = entry.getValue();
			columns[1] = entry.getKey().getName();
			columns[2] = entry.getKey().getPrice();
			columns[3] = entry.getKey().getPrice()*entry.getValue();
			model.addRow(columns);
		}
		
		
		

	}
	public void fillCommentTable(List<ProductEvaluate> list) {
		
		
		DefaultTableModel model = (DefaultTableModel) tableProductEvaluate.getModel();
		model.setRowCount(0);
		Object[] columns= new Object[5];
		
		for(int i = 0;i< list.size();i++) {
			columns[0]=list.get(i).getId();
			columns[1]=list.get(i).getComment();
			columns[2]=list.get(i).getScore();
			columns[3]=list.get(i).getProduct().getName();
			String customerAd = list.get(i).getCustomer().getFirstname().substring(0, 1)+"***";
			String customerSoyad =list.get(i).getCustomer().getLastname().substring(0, 1)+"***";
			String customer = customerAd+" "+customerSoyad;
			columns[4]=customer;
			
			model.addRow(columns);
		}
	}
}
