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
	private JButton selectButton;
	private BaseWindow parentWindow;
	private String type;
	
	public AirportListingPage() {
		initializeComponent();
		panel = new JPanel();
		panel.setLayout(new GridLayout(1,3));
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
	
	public AirportListingPage(BaseWindow parentWindow,String type) {
		this.type = type;
		this.parentWindow = parentWindow;
		initializeComponent();
		this.selectButton = new JButton("Select");
		panel = new JPanel();
		panel.setLayout(new GridLayout(1,1));
		panel.add(this.selectButton);
		this.baseWindow.add(panel,BorderLayout.SOUTH);
		this.addActionOnSelectButton();
	}
	
	public void initializeComponent() {
		airportDao = new AirportDaoImpl();
		this.createDataTable(getAirportData(), columns);
	}
	
	public void addActionOnSelectButton() {
		this.selectButton.addActionListener(e -> selectAction());
	}
	
	public void selectAction() {
		int selectedRowIndex = getSelectedRow();
		
		if (selectedRowIndex == -1) {
			JOptionPane.showMessageDialog(baseWindow, "Please select a airport to update.");
			return;
		}
		
		int airportId = getairportIdFromSelectedRow(selectedRowIndex);
		selectAirportAndRefresh(airportId);
		this.baseWindow.dispose();
	}
	
	public void selectAirportAndRefresh(int airportId) {
		Airport selectedAirport = this.airportDao.getById(airportId);
		
		if(this.parentWindow instanceof RouteCreateForm) {
			RouteCreateForm routeCreateForm = (RouteCreateForm)this.parentWindow;
			if(type.equalsIgnoreCase("arrive")) {
			routeCreateForm.refreshArriveAirportValueBtn(selectedAirport);
			}
			else {
				routeCreateForm.refreshDeptAirportValueBtn(selectedAirport);
			}
		}
		else if(this.parentWindow instanceof RouteUpdateForm) {
			RouteUpdateForm routeUpdateForm = (RouteUpdateForm)this.parentWindow;
			if(type.equalsIgnoreCase("arrive")) {
			routeUpdateForm.refreshArriveAirportValueBtn(selectedAirport);
			}
			else {
				routeUpdateForm.refreshDeptAirportValueBtn(selectedAirport);
			}
		}
	}
	
	public void call() {
		prepareBaseWindow();
	}
	
	public void refreshTableData() {
		super.refreshDataTable(getAirportData());
	}
	
	public void addActionOnCreateButton() {
		this.createButton.addActionListener(e -> airportCreateAction());
	}
	
	public void airportCreateAction() {
		new AirportCreateFormPage(this);
	}
	
	public void addActionOnUpdateButton() {
		this.updateButton.addActionListener(e -> airportUpdateAction());
	}
	
	public void airportUpdateAction() {
		int selectedRowIndex = getDataTableTemplate().getSelectedRow();
		int airportId = Integer.parseInt(getAirportData()[selectedRowIndex][0]);
		new AirportUpdateFormPage(this,airportId);
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
			 airportDao.delete(airportId);
			 this.refreshTableData();
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
		this.setTitle("Airport Information");
		this.baseWindow.setSize(800,400);
		this.baseWindow.setVisible(true);
	}

	@Override
	public void renderPage() {
		// TODO Auto-generated method stub
		
	}
}
