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
import javax.swing.LayoutFocusTraversalPolicy;

import Dao.CustomerDaoImpl;
import Model.Customer;

public class CustomerCreateFormPage extends BaseWindow {
	private JLabel customerIdLabel;
	private JTextField customerIdValue;
	
	private JLabel customerNameLabel;
	private JLabel customerEmailLabel;
	
	private JTextField customerNameValue;
	private JTextField customerEmailValue;
	
	private JButton createButton;
	private JButton cancelButton;
	
	private JPanel panel;
	
	private CustomerDaoImpl customerDao;
	private CustomerListingPage parentPage;
	
	public CustomerCreateFormPage(CustomerListingPage parentPage) {
		this.customerDao = new CustomerDaoImpl();
		this.parentPage = parentPage;
		initializeComponent();
		prepareBaseWindow();
	}
	
	public void initializeComponent() {
		customerIdLabel = new JLabel("Customer Id");
		customerIdValue = new JTextField();
		customerNameLabel = new JLabel("Customer Name");
		customerNameValue = new JTextField();
		customerEmailLabel = new JLabel("Customer Email");
		customerEmailValue = new JTextField();
		createButton = new JButton("Create");
		cancelButton = new JButton("Cancel");
		
		panel = new JPanel();
		panel.setLayout(new GridLayout(4,4));
		panel.add(customerIdLabel);
		panel.add(customerIdValue);
		panel.add(customerNameLabel);
		panel.add(customerNameValue);
		panel.add(customerEmailLabel);
		panel.add(customerEmailValue);
		panel.add(createButton);
		panel.add(cancelButton);
		
		addActionOnCreateButton();
		this.baseWindow.add(panel,BorderLayout.NORTH);
	}
	
	public void addActionOnCreateButton() {
		this.createButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int id = Integer.parseInt(customerIdValue.getText());
				String name = customerNameValue.getText();
				String email = customerEmailValue.getText();
				Customer customer = new Customer(id,name,email);
				customerDao.create(customer);
				JOptionPane.showMessageDialog(baseWindow, "Successfully created Customer !!!");
				baseWindow.dispose();
				parentPage.refreshTableData();
			}
		});
	}
	
	public void prepareBaseWindow() {
		this.baseWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Ticket Information");
		this.baseWindow.setSize(800,400);
		this.baseWindow.setVisible(true);
	}
}