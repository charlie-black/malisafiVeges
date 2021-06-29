package com.mycompany.malisafiveges;


import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JComboBox;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.awt.Color;
import javax.swing.JSpinner;
import java.awt.Font;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.JTextArea;

public class Vegetables extends JFrame {

	private static JPanel contentPane;
	private static JTextField product_name;
	private static JTextField price;
	private static JTable table;
	private static JSpinner quantity;
	private static JLabel totalamount;

	String[] tblHead = { "Product", "Quantity", "Price", "Amount" };
	DefaultTableModel dtm = new DefaultTableModel(tblHead, 0);

	private static HttpRequest httpRequest;

	private static ArrayList<ProductModel> products;
	private static JComboBox<ProductModel> product_code;
	private JTextField paid;
	private JTextField balance1;
	private JTextField totalBill;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Vegetables frame = new Vegetables();
					frame.setVisible(true);
					System.out.println(httpRequest.getProducts().toString());
					products = httpRequest.getProducts();
					populateProductCodes();
					populateOtherFields(0);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Vegetables() {
		products = new ArrayList<>();
		httpRequest = new HttpRequest();
		setAlwaysOnTop(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1243, 650);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnAdd = new JButton("Add Item");
		btnAdd.setFont(new Font("Nimbus Sans", Font.PLAIN, 16));
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dtm.addRow(new Object[] { product_name.getText().toString(), quantity.getValue(),
						price.getText().toString(), totalamount.getText().toString() });
				
				   Double sum = (double) 0;
			        
				      for(int i = 0; i<table.getRowCount(); i++) 
				      {
				       sum = sum + Double.parseDouble(table.getValueAt(i, 3).toString());
				      }
				      totalBill.setText(Double.toString(sum));
			}
		});
		btnAdd.setBounds(48, 354, 117, 25);
		contentPane.add(btnAdd);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setForeground(Color.WHITE);
		scrollPane.setBackground(Color.WHITE);
		scrollPane.setBounds(262, 48, 435, 300);
		contentPane.add(scrollPane);

		table = new JTable();
		table.setFillsViewportHeight(true);
		table = new JTable(dtm);
		scrollPane.setViewportView(table);

		JLabel lblWelcome = new JLabel("MALISAFI VEGES");
		lblWelcome.setHorizontalAlignment(SwingConstants.CENTER);
		lblWelcome.setFont(new Font("Nimbus Sans", Font.BOLD, 25));
		lblWelcome.setBounds(262, -5, 392, 41);
		contentPane.add(lblWelcome);
		
				product_code = new JComboBox();
				product_code.setBounds(149, 19, 100, 33);
				contentPane.add(product_code);
				product_code.setSelectedItem("Select code...");
				
						JLabel lblProductName = new JLabel("Product code");
						lblProductName.setBounds(12, 20, 130, 15);
						contentPane.add(lblProductName);
						lblProductName.setFont(new Font("Monospaced", Font.BOLD, 16));
						
								product_name = new JTextField();
								product_name.setBounds(150, 79, 100, 33);
								contentPane.add(product_name);
								product_name.setColumns(10);
								
										JLabel lblProductName_2 = new JLabel("Product name");
										lblProductName_2.setBounds(12, 79, 130, 33);
										contentPane.add(lblProductName_2);
										lblProductName_2.setFont(new Font("Nimbus Sans", Font.PLAIN, 16));
										
												JLabel lblQuantity = new JLabel("Quantity");
												lblQuantity.setBounds(12, 151, 100, 32);
												contentPane.add(lblQuantity);
												lblQuantity.setFont(new Font("Nimbus Sans", Font.PLAIN, 16));
												
														quantity = new JSpinner();
														quantity.setBounds(159, 150, 73, 33);
														contentPane.add(quantity);
														quantity.setModel(new SpinnerNumberModel(1, 1, 100, 1));
														
																price = new JTextField();
																price.setBounds(159, 222, 91, 33);
																contentPane.add(price);
																price.setColumns(10);
																
																		JLabel lblPrice = new JLabel("Price");
																		lblPrice.setBounds(12, 223, 60, 32);
																		contentPane.add(lblPrice);
																		lblPrice.setFont(new Font("Nimbus Sans", Font.PLAIN, 16));
																		
																				JLabel lblAmount = new JLabel("Amount");
																				lblAmount.setBounds(12, 294, 100, 33);
																				contentPane.add(lblAmount);
																				lblAmount.setFont(new Font("Nimbus Sans", Font.BOLD, 16));
																				
																						totalamount = new JLabel("Total");
																						totalamount.setBounds(182, 292, 50, 35);
																						contentPane.add(totalamount);
																						totalamount.setBackground(Color.LIGHT_GRAY);
																																																								
																																																								JLabel lblPaid = new JLabel("PAID");
																																																								lblPaid.setBounds(66, 446, 70, 15);
																																																								contentPane.add(lblPaid);
																																																								
																																																								paid = new JTextField();
																																																								paid.setBounds(159, 444, 114, 19);
																																																								contentPane.add(paid);
																																																								paid.setColumns(10);
																																																								
																																																								JLabel lblBalance = new JLabel("BALANCE");
																																																								lblBalance.setBounds(305, 446, 70, 15);
																																																								contentPane.add(lblBalance);
																																																								
																																																								balance1 = new JTextField();
																																																								balance1.setBounds(399, 444, 114, 19);
																																																								contentPane.add(balance1);
																																																								balance1.setColumns(10);
																																																								
																																																								JButton btnComplete = new JButton("COMPLETE");
																																																								btnComplete.addActionListener(new ActionListener() {
																																																									public void actionPerformed(ActionEvent e) {
																																																										 JTextArea panel_1 = new JTextArea(); 
																																																										panel_1.setText("**********MALI SAFI VEGES************");
																																																										//Bill(); 
																																																										//generateReceipt();
																																																										Balance();
																																																									}
																																																								});
																																																								btnComplete.setBounds(540, 441, 117, 25);
																																																								contentPane.add(btnComplete);
																																																								
																																																								JLabel lblTotalBill = new JLabel("TOTAL BILL");
																																																								lblTotalBill.setBounds(355, 364, 130, 51);
																																																								contentPane.add(lblTotalBill);
																																																								
																																																								totalBill = new JTextField();
																																																								totalBill.addActionListener(new ActionListener() {
																																																									public void actionPerformed(ActionEvent e) {
																																																										
																																																									}
																																																								});
																																																								totalBill.setEditable(false);
																																																								totalBill.setBounds(456, 360, 114, 52);
																																																								contentPane.add(totalBill);
																																																								totalBill.setColumns(10);
																																																								
																																																								JTextArea panel_1 = new JTextArea();
																																																								panel_1.setBackground(Color.WHITE);
																																																								panel_1.setBounds(709, 12, 370, 451);
																																																								contentPane.add(panel_1);
														quantity.addChangeListener(new ChangeListener() {
															@Override
															public void stateChanged(ChangeEvent e) {
																totalamount.setText(updateAmount());
															}
														});
				product_code.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						System.out.println("Item changed");
						populateOtherFields(product_code.getSelectedIndex());
					}
				});
	}
	
	  public void Bill()
	    {
	        String total =  totalBill.getText();
	        String pay =  paid.getText();
	        String balance =  balance1.getText();
	       JTextArea panel_1 = new JTextArea();
	       
	        
	        
	          DefaultTableModel model = new DefaultTableModel();
	        model= (DefaultTableModel)table.getModel();
	        
	        
			
			panel_1.setText(panel_1.getText()+"*******************************************************\n");
	        panel_1.setText(panel_1.getText()+"*     MALI SAFI VEGETABLES       \n");
	        panel_1.setText(panel_1.getText()+"*******************************************************\n");
	        
	        panel_1.setText(panel_1.getText() + "Product" + "\t" + "Price" + "\t" + "Qauntity" + "\t" + "Amount" + "\n"  );
	          
	          
	          for(int i = 0; i < model.getRowCount(); i++)
	          {
	              
	              String pname = (String)model.getValueAt(i, 1).toString();
	              String price = (String)model.getValueAt(i, 2).toString();
	              String amount = (String)model.getValueAt(i, 3).toString(); 
	              String quantity = (String)model.getValueAt(i, 1).toString(); 
	              
	          panel_1.setText(panel_1.getText() + pname  + "\t" + price + "\t" + quantity + "\t"+ amount  + "\n"  );
	    
	          }
	          
	          panel_1.setText(panel_1.getText() + "\n");     
	          
	          panel_1.setText(panel_1.getText() + "\t" + "\t" + "Subtotal :" + total + "\n");
	          panel_1.setText(panel_1.getText() + "\t" + "\t" + "Pay :" + pay + "\n");
	          panel_1.setText(panel_1.getText() + "\t" + "\t" + "Balance :" + balance + "\n");
	          panel_1.setText(panel_1.getText() + "\n");
	          panel_1.setText(panel_1.getText() + "*******************************************************\n");
	          panel_1.setText(panel_1.getText() + "     THANK YOU FOR SHOPPING AT MALI SAFI VEGETABLES             \n");
	        
	    }
	 public void Balance()
	    {
	       Double total = Double.parseDouble(totalBill.getText());
	        Double pay = Double.parseDouble(paid.getText());
	        
	        Double bal = pay-total; 
	        balance1.setText(String.valueOf(bal));
	    
	    }

	public static void populateProductCodes() {
		String[] productCodes = new String[products.size()];
		for (int i = 0; i < products.size(); i++) {
			productCodes[i] = products.get(i).getProductCode();
		}
		product_code.setModel(new DefaultComboBoxModel(productCodes));
	}

	public static void populateOtherFields(int selected) {
		product_name.setText(products.get(selected).getProductName());
		price.setText(products.get(selected).getProductPrice());
		totalamount.setText(getTotalAmount(selected));
	}

	public static String getTotalAmount(int selected) {
		Double productPrice = Double.parseDouble(products.get(selected).getProductPrice());
		int noOfItems = (Integer) quantity.getValue();
		return String.valueOf(productPrice * noOfItems);
	}

	public static String updateAmount() {
		Double productPrice = Double.parseDouble(price.getText().toString());
		int noOfItems = (Integer) quantity.getValue();
		return String.valueOf(productPrice * noOfItems);
	}

//	public void generateReceipt() {
//		lblNo.setText(String.valueOf(dtm.getRowCount()));
//	
//		lblSub.setText(String.valueOf(sumTotal));
//		lblVt.setText(String.valueOf(vat));
//		totalPayable = (sumTotal - (sumTotal * 0.10)) + vat;
//		lbltotalAmnt.setText(String.valueOf(totalPayable));
//		panel_1.setVisible(true);
//	}
}