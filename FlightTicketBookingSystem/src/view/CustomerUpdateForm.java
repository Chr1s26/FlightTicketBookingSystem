package view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Dao.CustomerDaoImpl;
import Model.Customer;

public class CustomerUpdateForm extends BaseWindow {
	
	private JLabel customerIdLabel;
	private JLabel customerIdValue;
	private JLabel customerNameLabel;
	private JTextField customerNameValue;
	private JLabel customerEmailLabel;
	private JTextField customerEmailValue;
	
	private JPanel panel;
	private CustomerDaoImpl customerDao;
	
	private JButton updateButton;
	private JButton cancelButton;
	
	
	public CustomerUpdateForm(int customerId){
		InitializeComponent(customerId);
		addActionOnUpdateButton(customerId);
		prepareBaseWindow();
	}
	
	public void InitializeComponent(int customerId) {
		this.customerDao = new CustomerDaoImpl();
		Customer customer = customerDao.getById(customerId);
		this.customerIdLabel = new JLabel("Customer ID : ");
		this.customerIdValue = new JLabel(customerId+"");
		this.customerNameLabel = new JLabel("Customer Name : ");
		this.customerNameValue = new JTextField(customer.getCustomerName());
		this.customerEmailLabel = new JLabel("Customer Email");
		this.customerEmailValue = new JTextField(customer.getEmail());
		
		this.updateButton = new JButton("Update");
		this.cancelButton = new JButton("Cancel");
		
		this.panel = new JPanel();
		panel.setLayout(new GridLayout(4, 2));
		
		panel.add(this.customerIdLabel);
		panel.add(this.customerIdValue);
		panel.add(this.customerNameLabel);
		panel.add(this.customerNameValue);
		panel.add(this.customerEmailLabel);
		panel.add(this.customerEmailValue);
		panel.add(this.updateButton);
		panel.add(this.cancelButton);
		
		this.baseWindow.add(panel,BorderLayout.NORTH);
	}
	
	public void addActionOnUpdateButton(int customerId) {
		this.updateButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Customer customer1 = customerDao.getById(customerId);
				String name = customerNameValue.getText();
				customerNameValue.setText(customer1.getCustomerName());
				String email = customerEmailValue.getText();
				customerEmailValue.setText(customer1.getEmail());
				Customer customer = new Customer(customerId,name, email);
				baseWindow.dispose();
				CustomerListingPage customerListingPage = new CustomerListingPage();
				customerListingPage.call();
				customerDao.updateCustomer(customer);
			}
		});
	}
	
	public void prepareBaseWindow() {
		this.baseWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setTitle("Ticket Information");
		this.baseWindow.setSize(800,400);
		this.baseWindow.setVisible(true);
	}
	
}
