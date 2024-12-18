package view;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Cursor;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutFocusTraversalPolicy;

import Dao.FlightScheduleDaoImpl;
import Dao.TicketDaoImpl;
import Model.Customer;
import Model.Flight;
import Model.FlightSchedule;
import Model.Seat;
import Model.Ticket;

public class TicketUpdateForm extends BaseWindow {
	
	private JLabel ticketIdLabel;
	private JLabel ticketIdValue;
	
	private JLabel customerLabel;
	private JLabel customerValueLabel;
	
	private JLabel ticketScheduleLabel;
	private JLabel ticketScheduleValueLabel;
	
	private JLabel seatLabel;
	private JLabel seatValueLabel;
	
	private JLabel priceLabel;
	private JTextField priceValue;
	
	private JButton createButton;
	private JButton cancelButton;
	
	private JPanel panel;
	
	private TicketDaoImpl ticketDao;
	
	private TicketListingPage parentPage;
	private Ticket ticket;
	
	private Customer customer;
	private FlightSchedule flightSchedule;
	private Seat seat;
	
	private FlightScheduleDaoImpl flightScheduleDaoImpl;
	private Flight flight;
	
	public TicketUpdateForm(TicketListingPage parentPage,int ticektId) {
		this.ticketDao = new TicketDaoImpl();
		this.ticket = this.ticketDao.getById(ticektId);
		this.seat = this.ticket.getSeat();
		this.flightScheduleDaoImpl = new FlightScheduleDaoImpl();
		this.customer = ticket.getCustomer();
		this.flightSchedule = this.flightScheduleDaoImpl.getById(ticket.getSchedule().getScheduleid());
		this.parentPage = parentPage;
		this.flight = this.flightSchedule.getFlight();
		initializeComponent();
		prepareBaseWindow();
	}
	
	public void initializeComponent() {
		ticketIdLabel = new JLabel("ticket Id : ");
		ticketIdValue = new JLabel(this.ticket.getTicketId()+"");
		
		customerLabel = new JLabel("Customer Name : ");
		customerValueLabel = new JLabel(getCustomerNameLabel());
		customerValueLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
		
		ticketScheduleLabel = new JLabel("Schedule Id : ");
		ticketScheduleValueLabel = new JLabel(getScheduleValueLabel());
		ticketScheduleValueLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
		
		seatLabel = new JLabel("Seat Id : ");
		seatValueLabel = new JLabel(getSeatLabel());
		seatValueLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
		
		priceLabel = new JLabel("Ticket Price : ");
		priceValue = new JTextField(ticket.getTicketprice()+"");
		
		createButton = new JButton("Update");
		cancelButton = new JButton("Cancel");
		
		panel = new JPanel();
		panel.setLayout(new GridLayout(6,2));
		panel.add(ticketIdLabel);
		panel.add(ticketIdValue);
		 
		panel.add(customerLabel);
		panel.add(customerValueLabel);
		 
		panel.add(ticketScheduleLabel);
		panel.add(ticketScheduleValueLabel);
		
		panel.add(seatLabel);
		panel.add(seatValueLabel);
		
		panel.add(priceLabel);
		panel.add(priceValue);
		
		panel.add(createButton);
		panel.add(cancelButton);
		
		
		addActionOnCustomerButton();
		addActionOnTicketSchedule();
		addActionOnSeatButton();
		addActionOnUpdateButton();
		
		this.baseWindow.add(panel,BorderLayout.NORTH);
	}
	
	
	public void addActionOnTicketSchedule() {
		this.ticketScheduleValueLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
				selectScheduleAction();
			}
		});
	}
	
	public void selectScheduleAction() {
		ScheduleListingPage schedulingListingPage = new ScheduleListingPage(this);
		schedulingListingPage.call();
	}
	
	public void refreshScheduleValueBtn(FlightSchedule selectedSchedule) {
		this.flightSchedule = selectedSchedule;
		this.ticketScheduleValueLabel.setText(this.getScheduleValueLabel());
	}
	
	public void refreshcustomerValueLabel(Customer selectedCustomer) {
		this.customer = selectedCustomer;
		this.customerValueLabel.setText(this.getCustomerNameLabel());
	}
	
	public void refreshseatValueLabel(Seat selectedSeat) {
		this.seat = selectedSeat;
		this.seatValueLabel.setText(this.getSeatLabel());
	}
	
	public void addActionOnCustomerButton() {
		this.customerValueLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
				selectCustomerAction();
			}
		});
	}
	
	public void selectCustomerAction() {
		CustomerListingPage customerListingPage = new CustomerListingPage(this);
		customerListingPage.call();
	}
	
	public void addActionOnSeatButton() {
		this.seatValueLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
				selectSeatAction();
			}
		});
	}
	
	public void selectSeatAction() {
		SeatListingPage seatListingPage = new SeatListingPage(this,this.flightSchedule.getScheduleid());
		seatListingPage.call();
	}
	
	public void addActionOnUpdateButton() {
		this.createButton.addActionListener(e -> ticketUpdateAction());
	}
	
	public void ticketUpdateAction() {
		double price = Double.parseDouble(this.priceValue.getText());
		this.ticket.setCustomer(this.customer);
		this.ticket.setSchedule(flightSchedule);
		this.ticket.setSeat(seat);
		this.ticket.setTicketprice(price);
		this.ticketDao.update(ticket);
		JOptionPane.showMessageDialog(baseWindow, "Successfully created Ticket !!!");
		this.baseWindow.dispose();
		this.parentPage.refreshTableData();
	}
	
	public void prepareBaseWindow() {
		this.baseWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setTitle("Ticket Update Form ");
		this.baseWindow.setSize(800,400);
		this.baseWindow.setVisible(true);
	}
	
	public String getCustomerNameLabel() {
	    return "<html><a href='' style='color: black; text-decoration: none;'>" + customer.getCustomerName() + "</a></html>";
	}

	
	public String getScheduleValueLabel() {
		return "<html><a href= '' style='color: black;text-decoration: none;'>"+this.flightSchedule.getScheduleInfo()+"</a><html>";
	}
	
	public String getSeatLabel() {
		return "<html><a href= ''style='color: black; text-decoration: none;'>"+this.seat.getSeatid()+"("+this.ticket.getSeat().getSeatNumber()+")"+"</a><html>";
	}

	@Override
	public void renderPage() {
		// TODO Auto-generated method stub
		
	}
}
