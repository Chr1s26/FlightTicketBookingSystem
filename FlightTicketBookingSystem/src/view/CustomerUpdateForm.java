package view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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
	private Customer customer;
	private CustomerListingPage parentPage;
	
	public CustomerUpdateForm(CustomerListingPage parentPage,int customerId){
		this.customerDao = new CustomerDaoImpl();
		this.customer = customerDao.getById(customerId);
		this.parentPage = parentPage;
		InitializeComponent();
		addActionOnUpdateButton();
		prepareBaseWindow();
	}
	
	public void InitializeComponent() {
		
		this.customerIdLabel = new JLabel("Customer ID : ");
		this.customerIdValue = new JLabel(this.customer.getCustomerId()+"");
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
	
	public void addActionOnUpdateButton() {
		this.updateButton.addActionListener(e -> customerUpdateAction());}
	
	
	public void customerUpdateAction() {
		String name = customerNameValue.getText();
		String email = customerEmailValue.getText();
		Customer customer = new Customer(this.customer.getCustomerId(),name, email);
		customerDao.update(customer);
		JOptionPane.showMessageDialog(this.baseWindow, "Successful update!!!");
		baseWindow.dispose();
		this.parentPage.refreshTableData();
		
	}
	
	public void prepareBaseWindow() {
		this.baseWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setTitle("Customer Update Form");
		this.baseWindow.setSize(800,400);
		this.baseWindow.setVisible(true);
	}

	@Override
	public void renderPage() {
		// TODO Auto-generated method stub
		
	}
	
}
