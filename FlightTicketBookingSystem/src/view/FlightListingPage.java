package view;

import java.awt.BorderLayout;
import java.awt.GridLayout;import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import Dao.FlightDaoImpl;
import Model.Flight;

public class FlightListingPage extends BaseWindow {
	
	private String[] columns = {"id","name","number"};
	private FlightDaoImpl flightDao ;
	private String[][] flightDataTable;
	private JButton createButton;
	private JButton updateButton;
	private JButton deleteButton;
	private JPanel panel;
	private BaseWindow parentWindow;
	private JButton selectButton;
	
	
	public FlightListingPage() {
		initializeComponent();
		panel = new JPanel();
		panel.setLayout(new GridLayout(1,3));
		
		this.createButton = new JButton("Create");
		this.updateButton = new JButton("Update");
		this.deleteButton = new JButton("Delete");
		
		panel.add(createButton);
		panel.add(updateButton);
		panel.add(deleteButton);
		addActionOnCreateButton();
		addActionOnUpdateButton();
		addActionOnDeleteAction();
		this.baseWindow.add(panel,BorderLayout.SOUTH);
	}
	
	public FlightListingPage(BaseWindow parentWindow) {
	
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
		flightDao = new FlightDaoImpl();
		this.getAllFlightData();
		this.createDataTable(flightDataTable, columns);
	}
	
	public void addActionOnSelectButton() {
		this.selectButton.addActionListener(e -> selectAction());
	}
	
	public void selectAction() {
		int selectedRowIndex = getSelectedRow();
		
		if (selectedRowIndex == -1) {
			JOptionPane.showMessageDialog(baseWindow, "Please select a flight to update.");
			return;
		}
		
		int flightId = getFlightIdFromSelectedRow(selectedRowIndex);
		selectFlightAndRefresh(flightId);
		this.baseWindow.dispose();
	}
	
	public void selectFlightAndRefresh(int flightId) {
		Flight selectedFlight = this.flightDao.getById(flightId);
		if(this.parentWindow instanceof SeatCreateForm) {
			SeatCreateForm seatCreateForm = (SeatCreateForm)this.parentWindow;
			seatCreateForm.refreshFlightValueBtn(selectedFlight);
		}
		else if(this.parentWindow instanceof ScheduleCreateForm){
			ScheduleCreateForm scheduleCreateForm = (ScheduleCreateForm)this.parentWindow;
			scheduleCreateForm.refreshFlightValueBtn(selectedFlight);
		}
		else if(this.parentWindow instanceof ScheduleUpdateForm) {
			ScheduleUpdateForm scheduleUpdateForm = (ScheduleUpdateForm)this.parentWindow;
			scheduleUpdateForm.refreshFlightValueBtn(selectedFlight);
		}
	}
	
	public void refreshTableData() {
		this.getAllFlightData();
		super.refreshDataTable(flightDataTable);
	}
	
	public void addActionOnCreateButton() {
		this.createButton.addActionListener(e -> flightCreateAction());
	}
	
	public void flightCreateAction() {
		FlightCreateFormPage flightCreateFormPage = new FlightCreateFormPage(this);
	}
	
	public void addActionOnUpdateButton() {
		this.updateButton.addActionListener(e -> flightUpdateAction());
	}
	
	public void flightUpdateAction() {
		int selectedRowIndex = getDataTableTemplate().getSelectedRow();
		int flightId = Integer.parseInt(flightDataTable[selectedRowIndex][0]);
		FlightUpdateForm flightUpdateForm= new FlightUpdateForm(this,flightId);
	}
	
	public void addActionOnDeleteAction() {
		this.deleteButton.addActionListener(e -> handleDeleteAction());
	}
	
	public void handleDeleteAction() {
		int selectedRowIndex = getSelectedRow();
		
		if(selectedRowIndex == -1) {
			JOptionPane.showMessageDialog(baseWindow,"Please select a flight to delete");
			return ;
		}
		
		int flightId = getFlightIdFromSelectedRow(selectedRowIndex);
		
		if(confirmDeletion(flightId)) {
			deleteFlightAndRefresh(flightId);
		}
	}
	
	public int getSelectedRow() {
		return getDataTableTemplate().getSelectedRow();
	}
	
	public int getFlightIdFromSelectedRow(int rowIndex) {
		return Integer.parseInt(flightDataTable[rowIndex][0]);
	}
	
	public boolean confirmDeletion(int flightId) {
		int response = JOptionPane.showConfirmDialog(baseWindow,
				"Are you sure you want to delete flight with ID " + flightId + "?", "Confirm Deletion",
				JOptionPane.YES_NO_OPTION);
		return response == JOptionPane.YES_OPTION;
	}
	
	public void deleteFlightAndRefresh(int flightId) {
		flightDao.delete(flightId);
		this.refreshTableData();
	}
	
	public void call() {
		prepareBaseWindow();
	}
	
	public void getAllFlightData() {
		List<Flight> flights = flightDao.getAll();
		this.flightDataTable = getFlightStrings(flights);
	}
	
	public String[][] getFlightStrings(List<Flight> flights){
		
		String[][] flightArray = new String[flights.size()][columns.length];
		int rowCount = 0;
		for(Flight flight : flights) {
			flightArray[rowCount][0] = flight.getFlightid()+"";
			flightArray[rowCount][1] = flight.getFlightname();
			flightArray[rowCount][2] = flight.getFlightNumber();
			rowCount++;
		}
		return flightArray;
	}
	
	public void prepareBaseWindow() {
		this.baseWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setTitle("Flight Information");
		this.baseWindow.setSize(800,400);
		this.baseWindow.setVisible(true);
	}

	@Override
	public void renderPage() {
		// TODO Auto-generated method stub
		
	}
}
