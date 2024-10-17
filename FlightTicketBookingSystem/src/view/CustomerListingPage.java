package view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import Dao.CustomerDaoImpl;
import Model.Customer;

public class CustomerListingPage extends BaseWindow {
	
	private CustomerDaoImpl customerDao;
	private String[] columns = {"CustomerId","CustomerName","Customer Email"};
	private JButton createButton;
	private JButton updateButton;
	private JButton deleteButton;
	private JPanel panel;
	
	public CustomerListingPage() {
		panel = new JPanel();
		panel.setLayout(new GridLayout(1,3));
		customerDao = new CustomerDaoImpl();
		
		this.createDataTable(getCustomerData(), columns);
		
		this.createButton = new JButton("Create");
		this.updateButton = new JButton("Update");
		this.deleteButton = new JButton("Delete");
		
		panel.add(createButton);
		panel.add(updateButton);
		panel.add(deleteButton);
		
		this.baseWindow.add(panel,BorderLayout.SOUTH);
		
		this.addActionOnCreateButton();
		this.addActionOnUpdateButton();
		this.addActionOnDeleteButton();
		prepareBaseWindow();
	}
	
	public void addActionOnCreateButton() {
		this.createButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				CustomerCreateFormPage customerCreateFormPage = new CustomerCreateFormPage();
				
			}
		});
	}
	
	public void addActionOnUpdateButton() {
		this.updateButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int selectedRowIndex = getDataTableTemplate().getSelectedRow();
				int customerId = Integer.parseInt(getCustomerData()[selectedRowIndex][0]);
				new CustomerUpdateForm(customerId);
			}
		});
	}
	
	public void addActionOnDeleteButton() {
		this.deleteButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int selectedRowIndex = getDataTableTemplate().getSelectedRow();
				int customerId = Integer.parseInt(getCustomerData()[selectedRowIndex][0]);
				customerDao.deleteCustomer(customerId);
			}
		});
	}
	
	public String[][] getCustomerData(){
		List<Customer> customers = customerDao.getAll();
		String[][] customerArray = new String[customers.size()][columns.length];
		int rowCount = 0;
		for(Customer customer : customers) {
			customerArray[rowCount][0] = customer.getCustomerId()+"";
			customerArray[rowCount][1] = customer.getCustomerName();
			customerArray[rowCount][2] = customer.getEmail();
			rowCount++;
		}
		return customerArray;
	}
	
	public void prepareBaseWindow() {
		this.baseWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setTitle("Ticket Information");
		this.baseWindow.setSize(800,400);
		this.baseWindow.setVisible(true);
	}
}
