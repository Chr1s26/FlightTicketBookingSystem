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

import Dao.TicketDaoImpl;
import Model.Ticket;

public class TicketListingPage extends BaseWindow{
	
	private TicketDaoImpl ticketDaoImpl;
	private String[] columns = {"TicketId","CustomerName","SeatName","SeatType","Dept Airport","Arrival Airport","Price"};
	private JButton updateButton;
	private JButton deleteButton;
	private JPanel panel;
	
	public TicketListingPage() {
		InitializeComponent();
		this.createDataTable(getTicketData(), columns);
		this.prepareBaseWindow();
	}
	
	public TicketListingPage(BaseWindow parentWindow) {
		ticketDaoImpl = new TicketDaoImpl();
		this.createDataTable(getTicketData(), columns);
		this.prepareBaseWindow();
	}
	
	public void InitializeComponent() {
		ticketDaoImpl = new TicketDaoImpl();
		this.updateButton = new JButton("Update");
		this.deleteButton = new JButton("Delete");
		
		this.panel = new JPanel();
		panel.setLayout(new GridLayout(1, 2));
		panel.add(this.updateButton);
		panel.add(this.deleteButton);
		
		this.addActionOnUpdateButton();
		this.addActionOnDeleteButton();
		
		this.baseWindow.add(panel,BorderLayout.SOUTH);
	}
	
	public void call() {
		prepareBaseWindow();
	}
	
	public void refreshTableData() {
		super.refreshDataTable(getTicketData());
	}
	
	public void addActionOnUpdateButton() {
		this.updateButton.addActionListener(e -> updateAction());}
	
	public void updateAction() {
		int ticketId = getTicketIdFormSelectedRow(getSelectedRow());
		if(ticketId == -1) {
			JOptionPane.showMessageDialog(baseWindow, "Please select a ticket to update.");
			return;
		}	
		new TicketUpdateForm(this,ticketId);
	}
	
	public int getSelectedRow() {
		return getDataTableTemplate().getSelectedRow();
	}
	
	public int getTicketIdFormSelectedRow(int rowIndex) {
		return Integer.parseInt(getTicketData()[rowIndex][0]);
	}
	
	public void addActionOnDeleteButton() {
		this.deleteButton.addActionListener(e -> handleDeleteAction());}
	
	public void handleDeleteAction() {
		int selectedRowIndex = getSelectedRow();
		
		if(selectedRowIndex == -1) {
			JOptionPane.showMessageDialog(baseWindow, "Please select a ticket to delete ");
			return;
		}
		
		int ticketId = getTicketIdFormSelectedRow(selectedRowIndex);
		
		if(ConfirmDeletion(ticketId)) {
			deleteTicketAndRefresh(ticketId);
		}
	}
	
	
	public boolean ConfirmDeletion(int ticketId) {
		int response = JOptionPane.showConfirmDialog(baseWindow, "Are you sure you want to delete ticket with ID"+ticketId+"?","Confirm Deletion",JOptionPane.YES_NO_OPTION);
		return response == JOptionPane.YES_OPTION;
	}
	
	public void deleteTicketAndRefresh(int ticketId) {
		ticketDaoImpl.delete(ticketId);
		this.refreshTableData();
	}
	
	public void prepareBaseWindow() {
		this.baseWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setTitle("Ticket Information");
		this.baseWindow.setSize(800,400);
		this.baseWindow.setVisible(true);
	}
	
	public String[][] getTicketData(){
		List<Ticket> tickets = ticketDaoImpl.getAll();
		String[][] ticketarray = new String[tickets.size()][columns.length];
		int rowCount = 0;
		for(Ticket ticket : tickets) {
			ticketarray[rowCount][0] = ticket.getTicketId()+"";
			ticketarray[rowCount][1] = ticket.getCustomer().getCustomerName();
			ticketarray[rowCount][2] = ticket.getSeat().getSeatNumber();
			ticketarray[rowCount][3] = ticket.getSeat().getSeatType();
			ticketarray[rowCount][4] = ticket.getSchedule().getRoute().getDepatureAirport().getName();
			ticketarray[rowCount][5] = ticket.getSchedule().getRoute().getArrivalAirport().getName();
			ticketarray[rowCount][6] = ticket.getTicketprice()+"";
			rowCount++;
		}
		return ticketarray;
	}
}
