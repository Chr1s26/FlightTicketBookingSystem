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
	private JButton selectButton;
	private JPanel panel;
	private BaseWindow parentWindow;
	
	public CustomerListingPage(BaseWindow parentWindow) {
		this.parentWindow = parentWindow;
		initializeSelectComponent();
	}
	
	public CustomerListingPage() {
		initializeComponent();
	}
	
	public void initializeComponent() {
		panel = new JPanel();	
		panel.setLayout(new GridLayout(1,3));
		customerDao = new CustomerDaoImpl();
		
		this.createDataTable(this.getCustomerData(), this.columns);
		
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
	
	public void initializeSelectComponent() {
		panel = new JPanel();	
		panel.setLayout(new GridLayout(1,1));
		customerDao = new CustomerDaoImpl();
		
		this.createDataTable(this.getCustomerData(), this.columns);
		this.selectButton = new JButton("Select Customer");
		
		panel.add(this.selectButton);
		
		this.baseWindow.add(panel,BorderLayout.SOUTH);
		
		this.addActionOnSelectButton();
	}
	
	public void addActionOnSelectButton() {
		this.selectButton.addActionListener(e -> selectAction());
	}
	
	public void selectAction() {
		 int selectedRowIndex = getSelectedRow();

		 if (selectedRowIndex == -1) {
			 JOptionPane.showMessageDialog(baseWindow, "Please select a customer to update.");
			 return;
		 }

		 int customerId = getCustomerIdFromSelectedRow(selectedRowIndex);
		 
		 selectedCustomerAndRefresh(customerId);
		 this.baseWindow.dispose();
	}
	
	public void selectedCustomerAndRefresh(int customerId) {
		Customer selectedCustomer = this.customerDao.getById(customerId);
		
		if(this.parentWindow instanceof TicketUpdateForm) {
			TicketUpdateForm ticketUpdateForm = (TicketUpdateForm)this.parentWindow;
			ticketUpdateForm.refreshcustomerValueLabel(selectedCustomer);
		}
		else if(this.parentWindow instanceof TicketBookingPage) {
			TicketBookingPage ticketBookingPage = (TicketBookingPage)this.parentWindow;
			ticketBookingPage.refreshCustomerValueLabel(selectedCustomer);
		}
	}
	
	public void call() {
		prepareBaseWindow();
	}
	
	public void refreshTableData() {
		super.refreshDataTable(this.getCustomerData());
	}
	
	public void addActionOnCreateButton() {
		this.createButton.addActionListener(e -> customerCreateAction()); }
		
	
	public void customerCreateAction() {
		CustomerCreateFormPage customerCreateFormPage = new CustomerCreateFormPage(this);	
	}
	

	public void addActionOnUpdateButton() {
		this.updateButton.addActionListener(e -> customerUpdateAction());
	}

	public void customerUpdateAction(){
		int selectedRowIndex = getDataTableTemplate().getSelectedRow();
		int customerId = Integer.parseInt(getCustomerData()[selectedRowIndex][0]);
		new CustomerUpdateForm(this ,customerId);
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
			 customerDao.delete(customerId);
			 this.refreshTableData();
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
		this.setTitle("Customer Information");
		this.baseWindow.setSize(800,400);
		this.baseWindow.setVisible(true);
	}

	@Override
	public void renderPage() {
		// TODO Auto-generated method stub
		
	}
}
