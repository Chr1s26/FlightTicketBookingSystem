package view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class HomeView extends BaseWindow {
	private JMenuBar homeMenu;
	private JMenu customerMenu;
	private JMenu flightScheduleMenu;
	private JMenu ticketMenu;
	private JMenuItem scheduleListing;
	private JMenuItem ticketMenuListing;
	private JMenuItem customerMenuListing;
	private JMenu seatMenu;
	private JMenuItem seatMenuListing;
	private JMenu airportMenu;
	private JMenuItem airportMenuListing;
	
	public HomeView() {
		this.initializeHomeViewComponent();
		this.setTitle("Home View");
		this.baseWindow.setVisible(true);
	}
	
	public void initializeHomeViewComponent() {
		
		this.homeMenu = new JMenuBar();
		this.customerMenu = new JMenu("Customer");
		this.flightScheduleMenu = new JMenu("Flight Schedule");
		this.ticketMenu = new JMenu("Ticket");
		this.seatMenu = new JMenu("Seat");
		this.airportMenu = new JMenu("Airport");
		
		this.scheduleListing = new JMenuItem("Schedule Listing");
		this.ticketMenuListing = new JMenuItem("Ticket Listing");
		this.customerMenuListing = new JMenuItem("Customer Listing");
		this.seatMenuListing = new JMenuItem("Seat Listing");
		this.airportMenuListing = new JMenuItem("Airport Listing");
		
		this.homeMenu.add(this.customerMenu);
		this.homeMenu.add(this.flightScheduleMenu);
		this.homeMenu.add(this.ticketMenu);
		this.homeMenu.add(this.seatMenu);
		this.homeMenu.add(this.airportMenu);
		
		this.ticketMenu.add(ticketMenuListing);
		this.flightScheduleMenu.add(scheduleListing);
		this.customerMenu.add(customerMenuListing);
		this.seatMenu.add(seatMenuListing);
		this.airportMenu.add(airportMenuListing);
		
		this.addActionFlightScheduleMenu();
		this.addActionTicketMenu();
		this.addActionCustomerMenu();
		this.addActionSeatMenu();
		this.addActionAirportMenu();
		this.baseWindow.setJMenuBar(this.homeMenu);
		
	}
	
	public void addActionAirportMenu() {
		this.airportMenuListing.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new AirportListingPage();
			}
		});
	}
	
	public void addActionCustomerMenu() {
		this.customerMenuListing.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
			CustomerListingPage customerListingPage = new CustomerListingPage();
			customerListingPage.call();
			}
		});
	}
	
	public void addActionSeatMenu() {
		this.seatMenuListing.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
			new	SeatListingPage(); 
			}
		});
	}
	
	
	public void addActionTicketMenu() {
		this.ticketMenuListing.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				TicketListingPage ticketListingPage = new TicketListingPage();
				
			}
		});
	}
	public void addActionFlightScheduleMenu() {
		this.scheduleListing.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				ScheduleListingPage scheduleListingPage = new ScheduleListingPage();
			}
		});
	}

}
