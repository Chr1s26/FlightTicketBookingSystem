package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.border.EmptyBorder;

import Service.AuthenticationService;
import exception.InvalidTokenException;

import java.awt.Insets;
import javax.swing.UIManager;

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
    private JMenu flightMenu;
    private JMenuItem flightMenuListing;
    private JMenu routeMenu;
    private JMenuItem routeMenuListing;
    private JMenu scheduleMenu;
    private JMenuItem scheduleMenuListing;
    

    public HomeView() {
    	renderPage();
    }
    
    @Override
	public void renderPage() {
		this.initializeHomeViewComponent();
        this.setTitle("Home View");
        this.baseWindow.setVisible(true);
	}

    public void initializeHomeViewComponent() {
       
        Insets padding = new Insets(5, 10, 5, 10);

       
        this.homeMenu = new JMenuBar();
        this.homeMenu.setBorder(new EmptyBorder(10, 10, 10, 10)); 
        
        this.customerMenu = createMenuWithPadding("Customer", padding);
        this.flightScheduleMenu = createMenuWithPadding("Booking", padding);
        this.ticketMenu = createMenuWithPadding("Ticket", padding);
        this.seatMenu = createMenuWithPadding("Seat", padding);
        this.airportMenu = createMenuWithPadding("Airport", padding);
        this.flightMenu = createMenuWithPadding("Flight", padding);
        this.routeMenu = createMenuWithPadding("Route", padding);
        this.scheduleMenu = createMenuWithPadding("Schedule", padding);

        this.scheduleListing = createMenuItemWithPadding("Schedule Listing for booking", padding);
        this.ticketMenuListing = createMenuItemWithPadding("Ticket Listing", padding);
        this.customerMenuListing = createMenuItemWithPadding("Customer Listing", padding);
        this.seatMenuListing = createMenuItemWithPadding("Seat Listing", padding);
        this.airportMenuListing = createMenuItemWithPadding("Airport Listing", padding);
        this.flightMenuListing = createMenuItemWithPadding("Flight Listing", padding);
        this.routeMenuListing = createMenuItemWithPadding("Route Listing", padding);
        this.scheduleMenuListing = createMenuItemWithPadding("Schedule Listing", padding);

     
        this.homeMenu.add(this.customerMenu);
        this.homeMenu.add(this.flightScheduleMenu);
        this.homeMenu.add(this.ticketMenu);
        this.homeMenu.add(this.seatMenu);
        this.homeMenu.add(this.airportMenu);
        this.homeMenu.add(this.flightMenu);
        this.homeMenu.add(this.routeMenu);
        this.homeMenu.add(this.scheduleMenu);

        
        this.ticketMenu.add(ticketMenuListing);
        this.flightScheduleMenu.add(scheduleListing);
        this.customerMenu.add(customerMenuListing);
        this.seatMenu.add(seatMenuListing);
        this.airportMenu.add(airportMenuListing);
        this.flightMenu.add(flightMenuListing);
        this.routeMenu.add(routeMenuListing);
        this.scheduleMenu.add(scheduleMenuListing);

     
        this.addActionFlightScheduleMenu();
        this.addActionTicketMenu();
        this.addActionCustomerMenu();
        this.addActionSeatMenu();
        this.addActionAirportMenu();
        this.addActionFlightMenu();
        this.addActionRouteMenu();
        this.addActionScheduleMenu();

     
        this.baseWindow.setJMenuBar(this.homeMenu);
    }

   
    private JMenu createMenuWithPadding(String title, Insets padding) {
        JMenu menu = new JMenu(title);
        menu.setMargin(padding); 
        return menu;
    }


    private JMenuItem createMenuItemWithPadding(String title, Insets padding) {
        JMenuItem menuItem = new JMenuItem(title);
        menuItem.setMargin(padding); 
        return menuItem;
    }

    public void addActionScheduleMenu() {
        this.scheduleMenuListing.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ScheduleListingPage scheduleListingPage = new ScheduleListingPage("schedule");
                scheduleListingPage.call();
            }
        });
    }

    public void addActionFlightMenu() {
        this.flightMenuListing.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FlightListingPage flightListingPage = new FlightListingPage();
                flightListingPage.call();
            }
        });
    }

    public void addActionRouteMenu() {
        this.routeMenuListing.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RouteListingPage routeListingPage = new RouteListingPage();
                routeListingPage.call();
            }
        });
    }

    public void addActionAirportMenu() {
        this.airportMenuListing.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AirportListingPage airportListingPage = new AirportListingPage();
                airportListingPage.call();
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
                SeatListingPage seatListingPage = new SeatListingPage();
                seatListingPage.call();
            }
        });
    }

    public void addActionTicketMenu() {
        this.ticketMenuListing.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TicketListingPage ticketListingPage = new TicketListingPage();
                ticketListingPage.call();
            }
        });
    }

    public void addActionFlightScheduleMenu() {
        this.scheduleListing.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ScheduleListingPage scheduleListingPage = new ScheduleListingPage("booking");
                scheduleListingPage.call();
            }
        });
    }

	
}
