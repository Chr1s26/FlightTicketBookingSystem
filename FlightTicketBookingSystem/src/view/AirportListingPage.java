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

import Dao.AirportDaoImpl;
import Model.Airport;

public class AirportListingPage extends BaseWindow {
	
	private AirportDaoImpl airportDao;
	private String[] columns = {"AirportId","AirportName"};
	private JButton createButton;
	private JButton updateButton;
	private JButton deleteButton;
	private JPanel panel;
	
	public AirportListingPage() {
		panel = new JPanel();
		panel.setLayout(new GridLayout(1,3));
		airportDao = new AirportDaoImpl();
		
		this.createDataTable(getAirportData(), columns);
		
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
	
	public void addActionOnCreateButton() {
		this.createButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new AirportCreateFormPage();
			}
		});
	}
	
	public void addActionOnUpdateButton() {
		this.updateButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int selectedRowIndex = getDataTableTemplate().getSelectedRow();
				int airportId = Integer.parseInt(getAirportData()[selectedRowIndex][0]);
				new AirportUpdateFormPage(airportId);
			}
		});
	}
	
	public void call() {
		prepareBaseWindow();
	}
	
	public void addActionOnDeleteButton() {
		 this.deleteButton.addActionListener(e -> handleDeleteAction());}
	
	private void handleDeleteAction() {
		 
		 int selectedRowIndex = getSelectedRow();

		 if (selectedRowIndex == -1) {
			 JOptionPane.showMessageDialog(baseWindow, "Please select a airport to delete.");
			 return;
		 }

		 int airportId = getairportIdFromSelectedRow(selectedRowIndex);

		 if (confirmDeletion(airportId)) {
			 deleteCustomerAndRefresh(airportId);
			 baseWindow.dispose();
		 }
		}

		private int getSelectedRow() {
			return getDataTableTemplate().getSelectedRow();
		}

		private int getairportIdFromSelectedRow(int rowIndex) {
			return Integer.parseInt(getAirportData()[rowIndex][0]);
		}

		private boolean confirmDeletion(int airportId) {
			int response = JOptionPane.showConfirmDialog(
					baseWindow,
					"Are you sure you want to delete customer with ID " + airportId + "?",
					"Confirm Deletion",
					JOptionPane.YES_NO_OPTION
		 );
			return response == JOptionPane.YES_OPTION;
		}
		
		private void deleteCustomerAndRefresh(int airportId) {
			 airportDao.deleteAirport(airportId);
			 AirportListingPage airportListingPage = new AirportListingPage();
			 airportListingPage.call();
			}
	
	public String[][] getAirportData(){
		List<Airport> airports = airportDao.getAll();
		String[][] airportArray = new String[airports.size()][columns.length];
		int rowCount = 0;
		for(Airport airport : airports) {
			airportArray[rowCount][0] = airport.getAirportId()+"";
			airportArray[rowCount][1] = airport.getName();
			rowCount++;
		}
		return airportArray;
	}
	
	public void prepareBaseWindow() {
		this.baseWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setTitle("Ticket Information");
		this.baseWindow.setSize(800,400);
		this.baseWindow.setVisible(true);
	}
}
