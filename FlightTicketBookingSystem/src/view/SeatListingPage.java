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
import Dao.SeatDaoImpl;
import Model.Customer;
import Model.Seat;

public class SeatListingPage extends BaseWindow {
	
	private SeatDaoImpl seatDao;
	private String[] columns = {"SeatId","Seat Type","Flight Id","Flight Name","Seat Number"};
	private JButton createButton;
	private JButton updateButton;
	private JButton deleteButton;
	private JPanel panel;
	
	public SeatListingPage() {
		panel = new JPanel();
		panel.setLayout(new GridLayout(1,3));
		seatDao = new SeatDaoImpl();
		
		this.createDataTable(getSeatData(), columns);
		
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
		super.refreshDataTable(this.getSeatData());
	}
	
	public void addActionOnCreateButton() {
		this.createButton.addActionListener(e -> seatCreateAction());
	}
	
	public void seatCreateAction() {
		SeatCreateForm seatCreateForm = new SeatCreateForm(this);
	}
	
	public void addActionOnUpdateButton() {
		this.updateButton.addActionListener(e -> seatUpdateAction());
	}
	
	public void seatUpdateAction() {
		int selectedRowIndex = getDataTableTemplate().getSelectedRow();
		int seatId = Integer.parseInt(getSeatData()[selectedRowIndex][0]);
		new seatUpdateForm(this,seatId);
	}
	

	public void addActionOnDeleteButton() {
		 this.deleteButton.addActionListener(e -> handleDeleteAction());}
	
	private void handleDeleteAction() {
		 
		 int selectedRowIndex = getSelectedRow();

		 if (selectedRowIndex == -1) {
			 JOptionPane.showMessageDialog(baseWindow, "Please select a seat to delete.");
			 return;
		 }

		 int seatId = getSeatIdFromSelectedRow(selectedRowIndex);

		 if (confirmDeletion(seatId)) {
			 deleteCustomerAndRefresh(seatId);
		 }
		}

		private int getSelectedRow() {
			return getDataTableTemplate().getSelectedRow();
		}

		private int getSeatIdFromSelectedRow(int rowIndex) {
			return Integer.parseInt(getSeatData()[rowIndex][0]);
		}

		private boolean confirmDeletion(int seatId) {
			int response = JOptionPane.showConfirmDialog(
					baseWindow,
					"Are you sure you want to delete customer with ID " + seatId + "?",
					"Confirm Deletion",
					JOptionPane.YES_NO_OPTION
		 );
			return response == JOptionPane.YES_OPTION;
		}
		
		private void deleteCustomerAndRefresh(int seatId) {
			 seatDao.deleteSeat(seatId);
			 this.refreshTableData();
			}
	
	public String[][] getSeatData(){
		List<Seat> seats = seatDao.getAll();
		String[][] seatArray = new String[seats.size()][columns.length];
		int rowCount = 0;
		for(Seat seat : seats) {
			seatArray[rowCount][0] = seat.getSeatid()+"";
			seatArray[rowCount][1] = seat.getSeatType();
			seatArray[rowCount][2] = seat.getFlight().getFlightid()+"";
			seatArray[rowCount][3] = seat.getFlight().getFlightname();
			seatArray[rowCount][4] = seat.getSeatNumber();
			rowCount++;
		}
		return seatArray;
	}
	
	public void prepareBaseWindow() {
		this.baseWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setTitle("Seat Information");
		this.baseWindow.setSize(800,400);
		this.baseWindow.setVisible(true);
	}
}
