package com.mycompany.malisafiveges;

import java.awt.FlowLayout;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ProductsAdd {
	private static JTextField jTextfield_product_name;
	private static JTextField jTextField_product_code;
	private static JTextField jTextField_price;
		 
		 public static void main(String[] args) throws Exception {
			 
			 
		       
	        JFrame frmAddVegetables = new JFrame("JFrame Example");  
	        frmAddVegetables.setTitle("Add Vegetables");
	        JPanel panel = new JPanel();  
	        panel.setLayout(new FlowLayout());
	        frmAddVegetables.getContentPane().add(panel);  
	        
	        JLabel lblNewLabel = new JLabel("Product Name");
	        panel.add(lblNewLabel);
	        
	        jTextfield_product_name = new JTextField();
	        panel.add(jTextfield_product_name);
	        jTextfield_product_name.setColumns(10);
	        
	        JLabel lblNewLabel_1 = new JLabel("Product Code");
	        panel.add(lblNewLabel_1);
	        
	        jTextField_product_code = new JTextField();
	        panel.add(jTextField_product_code);
	        jTextField_product_code.setColumns(10);
	        
	        JLabel lblNewLabel_2 = new JLabel("Price");
	        panel.add(lblNewLabel_2);
	        
	        jTextField_price = new JTextField();
	        panel.add(jTextField_price);
	        jTextField_price.setColumns(10);
	        
	        JButton btnaddProduct = new JButton("Add Product");
	        btnaddProduct.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent e) {
	        		try {
						AddProduct();
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
	        	}
	        });
	        panel.add(btnaddProduct);
	        frmAddVegetables.setSize(800, 200);  
	        frmAddVegetables.setLocationRelativeTo(null);  
	        frmAddVegetables.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
	        frmAddVegetables.setVisible(true);  
	    } 
		  
		 public static void AddProduct() throws Exception
		    {
			 URL url = new URL("https://localhost:3000/products/");
		        Map<String,Object> params = new LinkedHashMap<>();
		        params.put("name", jTextfield_product_name.getText());
		        params.put("code", jTextField_product_code.getText());
		        params.put("price", jTextField_price.getText());

		        StringBuilder postData = new StringBuilder();
		        for (Map.Entry<String,Object> param : params.entrySet()) {
		            if (postData.length() != 0) postData.append('&');
		            postData.append(URLEncoder.encode(param.getKey(), "UTF-8"));
		            postData.append('=');
		            postData.append(URLEncoder.encode(String.valueOf(param.getValue()), "UTF-8"));
		        }
		        byte[] postDataBytes = postData.toString().getBytes("UTF-8");

		        HttpURLConnection conn = (HttpURLConnection)url.openConnection();
		        conn.setRequestMethod("POST");
		        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
		        conn.setRequestProperty("Content-Length", String.valueOf(postDataBytes.length));
		        conn.setDoOutput(true);
		        conn.getOutputStream().write(postDataBytes);

		        Reader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));

		       // for (int c; (c = in.read()) >= 0;)
		            System.out.print("Vegetable added Successfully");
		        
		        jTextfield_product_name.setText("");
		        jTextField_product_code.setText(""); 
		        jTextField_price.setText("");
				
		    }

}
