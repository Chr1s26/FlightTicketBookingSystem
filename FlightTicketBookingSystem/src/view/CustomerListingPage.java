package view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
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
		
		refreshTableData();
		
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
		
	}
	
	public void call() {
		prepareBaseWindow();
	}
	
	public void refreshTableData() {
		this.createDataTable(getCustomerData(), columns);
	}
	
	public void addActionOnCreateButton() {
		this.createButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				baseWindow.dispose();
				CustomerCreateFormPage customerCreateFormPage = new CustomerCreateFormPage();
				
				
			}
		});
	}
	
	public void addActionOnUpdateButton() {
		this.updateButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				baseWindow.dispose();
				int selectedRowIndex = getDataTableTemplate().getSelectedRow();
				int customerId = Integer.parseInt(getCustomerData()[selectedRowIndex][0]);
				new CustomerUpdateForm(customerId);
			}
		});
	}
	
	public void addActionOnDeleteButton() {
		 this.deleteButton.addActionListener(e -> handleDeleteAction());}
	
	private void handleDeleteAction() {
		 
		 int selectedRowIndex = getSelectedRow();

		 if (selectedRowIndex == -1) {
			 JOptionPane.showMessageDialog(baseWindow, "Please select a customer to delete.");
			 return;
		 }

		 int customerId = getCustomerIdFromSelectedRow(selectedRowIndex);

		 if (confirmDeletion(customerId)) {
			 deleteCustomerAndRefresh(customerId);
			 baseWindow.dispose();
		 }
		}

		private int getSelectedRow() {
			return getDataTableTemplate().getSelectedRow();
		}

		private int getCustomerIdFromSelectedRow(int rowIndex) {
			return Integer.parseInt(getCustomerData()[rowIndex][0]);
		}

		private boolean confirmDeletion(int customerId) {
			int response = JOptionPane.showConfirmDialog(
					baseWindow,
					"Are you sure you want to delete customer with ID " + customerId + "?",
					"Confirm Deletion",
					JOptionPane.YES_NO_OPTION
		 );
			return response == JOptionPane.YES_OPTION;
		}
		
		private void deleteCustomerAndRefresh(int customerId) {
			 customerDao.deleteCustomer(customerId);
			 CustomerListingPage customerListingPage = new CustomerListingPage();
			 customerListingPage.call();
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
		this.baseWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Ticket Information");
		this.baseWindow.setSize(800,400);
		this.baseWindow.setVisible(true);
	}
}
