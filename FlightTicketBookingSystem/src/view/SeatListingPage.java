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
		prepareBaseWindow();
	}
	
	public void addActionOnCreateButton() {
		this.createButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new SeatCreateForm();
				
			}
		});
	}
	
	public void addActionOnUpdateButton() {
		this.updateButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int selectedRowIndex = getDataTableTemplate().getSelectedRow();
				int seatId = Integer.parseInt(getSeatData()[selectedRowIndex][0]);
				new seatUpdateForm(seatId);
			}
		});
	}
	
	public void addActionOnDeleteButton() {
		this.deleteButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int selectedRowIndex = getDataTableTemplate().getSelectedRow();
				int seatId = Integer.parseInt(getSeatData()[selectedRowIndex][0]);
				seatDao.deleteSeat(seatId);
			}
		});
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
