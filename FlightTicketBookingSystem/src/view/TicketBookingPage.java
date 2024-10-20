package view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Dao.CustomerDaoImpl;
import Dao.FlightScheduleDaoImpl;
import Dao.SeatDaoImpl;
import Dao.TicketDaoImpl;
import Model.Customer;
import Model.FlightSchedule;
import Model.Seat;
import Model.Ticket;

public class TicketBookingPage extends BaseWindow {
	
	private FlightScheduleDaoImpl flightScheduleDao;
	private SeatDaoImpl seatDaoImpl;
	
	private JLabel flightNumberLabel;
	private JLabel flightNameLabel;
	private JLabel seatNumberLabel;
	private JLabel deptTimeLabel;
	private JLabel arriveTimeLabel;
	private JLabel ticketPriceLabel;
	private JLabel customerIdLabel;
	private JLabel routeInfoLabel;
	
	private JLabel flightNumbervalue;
	private JLabel flightNamevalue;
	private JLabel SeatNumbervalue;
	private JLabel deptTimevalue;
	private JLabel arriveTimevalue;
	private JLabel ticketPricevalue;
	private JTextField customerTextField;
	private JLabel routeInfoValue;
	
	private FlightSchedule flightSchedule;
	private Seat seat;
	private JPanel detailsPanel;
	
	private JButton createButton;
	private JButton cancelButton;
	private CustomerDaoImpl customerdao;
	private TicketDaoImpl ticketDaoImpl;
	
	public TicketBookingPage(int scheduleId,int seatId) {
		flightScheduleDao = new FlightScheduleDaoImpl();
		seatDaoImpl = new SeatDaoImpl();
		this.customerdao = new CustomerDaoImpl();
		this.ticketDaoImpl = new TicketDaoImpl();
		
		flightSchedule = this.flightScheduleDao.getById(scheduleId);
		seat = this.seatDaoImpl.getById(seatId);
		initializeUIComponent();
		addComponentToBaseFrame();
		this.prepareBaseWindow();
	}
	
	public void addComponentToBaseFrame() {
		this.detailsPanel = new JPanel();
		this.detailsPanel.setLayout(new GridLayout(9, 2));
		
		this.detailsPanel.add(this.flightNumberLabel);
		this.detailsPanel.add(this.flightNumbervalue);
		
		this.detailsPanel.add(this.flightNameLabel);
		this.detailsPanel.add(this.flightNamevalue);
		
		this.detailsPanel.add(this.seatNumberLabel);
		this.detailsPanel.add(this.SeatNumbervalue);
		
		this.detailsPanel.add(this.deptTimeLabel);
		this.detailsPanel.add(this.deptTimevalue);
		
		this.detailsPanel.add(this.arriveTimeLabel);
		this.detailsPanel.add(this.arriveTimevalue);
		
		this.detailsPanel.add(this.routeInfoLabel);
		this.detailsPanel.add(this.routeInfoValue);
		
		this.detailsPanel.add(this.ticketPriceLabel);
		this.detailsPanel.add(this.ticketPricevalue);
		
		this.detailsPanel.add(this.customerIdLabel);
		this.detailsPanel.add(this.customerTextField);
		
		this.detailsPanel.add(this.createButton);
		this.detailsPanel.add(this.cancelButton);
		
		this.baseWindow.add(this.detailsPanel,BorderLayout.NORTH);
	}
	
	public void initializeUIComponent() {
		this.flightNumberLabel = new JLabel("Flight Number : ");
		this.flightNumbervalue = new JLabel(this.flightSchedule.getFlight().getFlightNumber());
		
		this.flightNameLabel = new JLabel("Flight Name : ");
		this.flightNamevalue = new JLabel(this.flightSchedule.getFlight().getFlightname());
		
		this.seatNumberLabel = new JLabel("Seat Number : ");
		this.SeatNumbervalue = new JLabel(this.seat.getSeatNumber()+"("+this.seat.getSeatType()+")");
		
		this.deptTimeLabel = new JLabel("Dept Time : ");
		this.deptTimevalue = new JLabel(this.flightSchedule.getDeptTime());
		
		this.arriveTimeLabel = new JLabel("Arrive Time : ");
		this.arriveTimevalue = new JLabel(this.flightSchedule.getArrivalTime());
		
		this.routeInfoLabel = new JLabel("Route Info : ");
		this.routeInfoValue = new JLabel(this.flightSchedule.getRoute().getRouteInfo());
		
		this.ticketPriceLabel = new JLabel("Ticket Price : ");
		this.ticketPricevalue = new JLabel(this.seat.calculatePrice()+"$");
		
		this.customerIdLabel = new JLabel("Customer Id : ");
		this.customerTextField = new JTextField(15);
		
		this.createButton = new JButton("Create Button");
		this.cancelButton = new JButton("Cancel Button");
		
		this.addCreateBtnAction();
		
	}
	
	public void addCreateBtnAction() {
		this.createButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
			int customerid = Integer.parseInt(customerTextField.getText());
			Customer customer = customerdao.getById(customerid);
			if(customer != null) {
			Ticket ticket = new Ticket(flightSchedule, customer, seat, seat.calculatePrice());
			ticketDaoImpl.create(ticket);
			JOptionPane.showMessageDialog(baseWindow, "Successfully created Ticket !!!");
			}
			else {
				JOptionPane.showMessageDialog(baseWindow, "Customer ID not found for "+customerid);
			}
			}
		});
	}
	
	public void prepareBaseWindow() {
		
		this.baseWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setTitle("Ticket Information");
		this.baseWindow.setSize(800,400);
		this.baseWindow.setVisible(true);
	}
	
	
}