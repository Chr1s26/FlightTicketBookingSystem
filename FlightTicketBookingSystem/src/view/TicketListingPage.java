package view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.*;

import Dao.TicketDaoImpl;
import Model.Ticket;

public class TicketListingPage extends BaseWindow{
	
	private TicketDaoImpl ticketDaoImpl;
	private String[] columns = {"TicketId","CustomerName","SeatName","SeatType","Dept Airport","Arrival Airport"};
	private JButton updateButton;
	private JButton deleteButton;
	private JPanel panel;
	
	public TicketListingPage() {
		InitializeComponent();
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
	
	public void addActionOnUpdateButton() {
		this.updateButton.addActionListener(e -> updateAction());
	}

	public void updateAction(){
		int ticketId = getTicketIdFromSelectedRow(getSelectedRow());
		if (ticketId == -1) {
			JOptionPane.showMessageDialog(baseWindow, "Please select a customer to update.");
			return;
		}
		new TicketUpdateForm(this, ticketId);
	}

	private int getSelectedRow() {
		return getDataTableTemplate().getSelectedRow();
	}

	private int getTicketIdFromSelectedRow(int rowIndex) {
		return Integer.parseInt(this.getTicketData()[rowIndex][0]);
	}

	public void addActionOnDeleteButton() {
		this.deleteButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int selectedRowIndex = getDataTableTemplate().getSelectedRow();
				int ticketId = Integer.parseInt(getTicketData()[selectedRowIndex][0]);
				ticketDaoImpl.deleteTicket(ticketId);
			}
		});
	}
	
	public void prepareBaseWindow() {
		this.baseWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setTitle("Ticket Information");
		this.baseWindow.setSize(800,400);
		this.baseWindow.setVisible(true);
	}

	public void refreshTicketDataTable(){
		super.createDataTable(this.getTicketData(), this.columns);
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
			rowCount++;
		}
		return ticketarray;
	}
}
