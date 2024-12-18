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
import javax.swing.JScrollPane;
import javax.swing.JTable;

import Dao.FlightScheduleDaoImpl;
import Model.FlightSchedule;

public class ScheduleListingPage extends BaseWindow {
	
	private String[] columns = {"Id","Flight Name","Flight Number","Dept Airport","Arrival Airport","Dept Time","Arrival Time","Created At"};
	private FlightScheduleDaoImpl flightScheduleDaoImpl;
	private JButton getAvaiableSeatBtn;
	private BaseWindow parentWindow;
	private JPanel panel;
	private JButton selectButton;
	private JButton createButton;
	private JButton updateButton;
	private JButton deleteButton;
	private String type;
	private String[][] scheduleDataTable;
	
	public ScheduleListingPage(BaseWindow parentWindow) {
		this.parentWindow = parentWindow;
		initializeSelectComponent();
	}
	
	public ScheduleListingPage(String type) {
		this.type = type;
		this.flightScheduleDaoImpl = new FlightScheduleDaoImpl();
		this.createDataTable(this.getScheduleData(), columns);
		if(type.equalsIgnoreCase("booking")) {
			initializeAvaliableSeatBtnComponent();
		}
		else {
			initializeComponent();
		}
	}
	
	public void initializeAvaliableSeatBtnComponent() {
		this.getAvaiableSeatBtn = new JButton("Show Avaiable Seats");
		this.baseWindow.add(this.getAvaiableSeatBtn,BorderLayout.SOUTH);
		
		this.addActionAvailableSeatBtn();
			
	}
	
	public void initializeComponent() {
		this.createButton = new JButton("Create");
		this.updateButton = new JButton("Update");
		this.deleteButton = new JButton("Delete");
		panel = new JPanel();
		panel.setLayout(new GridLayout(1, 3));
		
		panel.add(createButton);
		panel.add(updateButton);
		panel.add(deleteButton);

		this.baseWindow.add(panel, BorderLayout.SOUTH);

		this.addActionOnCreateButton();
		this.addActionOnUpdateButton();
		this.addActionOnDeleteButton();
	}
	
	public void addActionOnCreateButton() {
		this.createButton.addActionListener(e -> scheduleCreateAction());
	}
	
	public void scheduleCreateAction() {
		ScheduleCreateForm scheduleCreateForm = new ScheduleCreateForm(this);
	}
	
	public void addActionOnUpdateButton() {
		this.updateButton.addActionListener(e -> scheduleUpdateAction());
	}
	
	public void scheduleUpdateAction() {
		int selectedRowIndex = getDataTableTemplate().getSelectedRow();
		int scheduleId = Integer.parseInt(getScheduleData()[selectedRowIndex][0]);
		new ScheduleUpdateForm(this, scheduleId);
	}
	
	public void addActionOnDeleteButton() {
		this.deleteButton.addActionListener(e -> handleDeleteAction());
	}

	private void handleDeleteAction() {

		int selectedRowIndex = getSelectedRow();

		if (selectedRowIndex == -1) {
			JOptionPane.showMessageDialog(baseWindow, "Please select a schedule to delete.");
			return;
		}

		int scheduleId = getScheduleIdFromSelectedRow(selectedRowIndex);

		if (confirmDeletion(scheduleId)) {
			deleteScheduleAndRefresh(scheduleId);
		}
	}
	
	public void getAllScheduleData() {
		List<FlightSchedule> schedules = flightScheduleDaoImpl.getAll();
		this.scheduleDataTable = getScheduleData();
	}
	
	public void refreshTableData() {
		this.getAllScheduleData();
		super.refreshDataTable(scheduleDataTable);
	}

	private boolean confirmDeletion(int scheduleId) {
		int response = JOptionPane.showConfirmDialog(baseWindow,
				"Are you sure you want to delete schedule with ID " + scheduleId + "?", "Confirm Deletion",
				JOptionPane.YES_NO_OPTION);
		return response == JOptionPane.YES_OPTION;
	}

	private void deleteScheduleAndRefresh(int seatId) {
		flightScheduleDaoImpl.delete(seatId);
		this.refreshTableData();
	}
	
	public void initializeSelectComponent() {
		panel = new JPanel();
		panel.setLayout(new GridLayout(1,1));
		this.flightScheduleDaoImpl = new FlightScheduleDaoImpl();
		this.createDataTable(this.getScheduleData(), columns);
		this.selectButton = new JButton("Select Schedule");
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
		 
		 int scheduleId = getScheduleIdFromSelectedRow(selectedRowIndex);
		 selectedScheduleAndRefresh(scheduleId);
		 this.baseWindow.dispose();
	}
	
	public void selectedScheduleAndRefresh(int scheduleId) {
		FlightSchedule flightSchedule = this.flightScheduleDaoImpl.getById(scheduleId);
		TicketUpdateForm ticketUpdateForm = (TicketUpdateForm)this.parentWindow;
		ticketUpdateForm.refreshScheduleValueBtn(flightSchedule);
	}
	
	public int getScheduleIdFromSelectedRow(int rowIndex) {
		return Integer.parseInt(getScheduleData()[rowIndex][0]);
	}

	private void addActionAvailableSeatBtn() {
		this.getAvaiableSeatBtn.addActionListener(e -> actionSeatBtn());
	}
	
	public void actionSeatBtn() {
		int selectedRowIndex = getDataTableTemplate().getSelectedRow();
		int scheduleId = Integer.parseInt(getScheduleData()[selectedRowIndex][0]); 
		SeatView seatView = new SeatView(scheduleId);
		this.baseWindow.setVisible(false);
	}
	
	private int getSelectedRow() {
		return getDataTableTemplate().getSelectedRow();
	}
	
	public void call() {
		prepareBaseWindow();
	}
	
	public void prepareBaseWindow() {
		this.baseWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setTitle("Schedule Information");
		this.baseWindow.setSize(800,400);
		this.baseWindow.setVisible(true);
	}
	
	private String[][] getScheduleData(){
		List<FlightSchedule> schedules = this.flightScheduleDaoImpl.getAll();
		String[][] scheduleData = new String[schedules.size()][8];
		int rowCount = 0;
		for(FlightSchedule schedule : schedules) {
			scheduleData[rowCount] = schedule.toArray();
			rowCount++;
		}
		return scheduleData;
	}

	@Override
	public void renderPage() {
		// TODO Auto-generated method stub
		
	}
	
	
}
