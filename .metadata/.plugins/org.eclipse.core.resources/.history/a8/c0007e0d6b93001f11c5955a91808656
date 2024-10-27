package view;

import java.awt.BorderLayout;
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

import Dao.FlightDaoImpl;
import Dao.FlightScheduleDaoImpl;
import Dao.RouteDaoImpl;
import Model.Flight;
import Model.FlightSchedule;
import Model.Route;
import Model.Seat;

public class ScheduleCreateForm extends BaseWindow{
	private JLabel scheduleIdLabel;
	private JTextField scheduleIdValue;
	
	private JLabel routeIdLabel;
	private JLabel routeIdValue;
	
	private JLabel flightIdLabel;
	private JLabel flightIdValue;
	
	private JLabel deptTimeLabel;
	private JTextField deptTimeValue;
	
	private JLabel arriveTimeLabel;
	private JTextField arriveTimeValue;
	
	private JLabel createdAtLabel;
	private JTextField createdAtValue;
	
	private JButton createButton;
	private JButton cancelButton;
	
	private JPanel panel;
	private Flight flight;
	private Route route;
	
	private ScheduleListingPage parentPage;
	private FlightScheduleDaoImpl flightScheduleDao;
	private RouteDaoImpl routeDao;
	private FlightDaoImpl flightDao;
	
	public ScheduleCreateForm(ScheduleListingPage parentPage) {
		this.parentPage = parentPage;
		flightScheduleDao = new FlightScheduleDaoImpl();
		routeDao = new RouteDaoImpl();
		flightDao = new FlightDaoImpl();
		initializeComponent();
		prepareBaseWindow();
		
	}
	
	public void initializeComponent() {
		scheduleIdLabel = new JLabel("Schedule Id");
		scheduleIdValue = new JTextField();
		
		routeIdLabel = new JLabel("Route Id");
		routeIdValue = new JLabel(getRouteLabel());
		routeIdValue.setCursor(new Cursor(Cursor.HAND_CURSOR));
		
		flightIdLabel = new JLabel("Flight Id");
		flightIdValue = new JLabel(getFlightLabel());
		flightIdValue.setCursor(new Cursor(Cursor.HAND_CURSOR));
		
		deptTimeLabel = new JLabel("Dept Time");
		deptTimeValue = new JTextField();
		
		arriveTimeLabel = new JLabel("Arrive Time");
		arriveTimeValue = new JTextField();
		
		createdAtLabel = new JLabel("Created At");
		createdAtValue = new JTextField();
		
		createButton = new JButton("Create");
		cancelButton = new JButton("Cancel");
		
		panel = new JPanel();
		panel.setLayout(new GridLayout(7,2));
		panel.add(scheduleIdLabel);
		panel.add(scheduleIdValue);
		panel.add(routeIdLabel);
		panel.add(routeIdValue);
		panel.add(flightIdLabel);
		panel.add(flightIdValue);
		panel.add(deptTimeLabel);
		panel.add(deptTimeValue);
		panel.add(arriveTimeLabel);
		panel.add(arriveTimeValue);
		panel.add(createdAtLabel);
		panel.add(createdAtValue);
		panel.add(createButton);
		panel.add(cancelButton);
		
		addActionOnFlightLabel();
		addActionOnRouteLabel();
		addActionOnCreateButton();
		this.baseWindow.add(panel,BorderLayout.NORTH);
	}
	
	public void addActionOnCreateButton() {
		this.createButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int id = Integer.parseInt(scheduleIdValue.getText());
				Route routeObj = routeDao.getById(route.getRouteId());
				Flight flightObj = flightDao.getById(flight.getFlightid());
				String deptTime = deptTimeValue.getText();
				String arriveTime = arriveTimeValue.getText();
				String createdAt = createdAtValue.getText();
				FlightSchedule schedule = new FlightSchedule(flightObj, routeObj, arriveTime, deptTime, createdAt);
				flightScheduleDao.create(schedule);
				JOptionPane.showMessageDialog(baseWindow, "Successfully created schedule !!!");
				baseWindow.dispose();
				parentPage.refreshTableData();
			}
		});
	}
	
	public void addActionOnFlightLabel() {
		this.flightIdValue.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
				selectFlightAction();
			}
		});
	}
	
	public void selectFlightAction() {
		FlightListingPage flightListingPage = new FlightListingPage(this,"schedule");
		flightListingPage.call();
	}
	
	public void refreshFlightValueBtn(Flight selectFlight) {
		this.flight = selectFlight;
		this.flightIdValue.setText(this.flight.getFlightid()+"");
	}
	
	public void addActionOnRouteLabel() {
		this.routeIdValue.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
				selectRouteAction();
			}
		});
	}
	
	public void selectRouteAction() {
		RouteListingPage routeListingPage = new RouteListingPage(this,"schedule");
		routeListingPage.call();
	}
	
	public void refreshRouteValueBtn(Route selectRoute) {
		this.route = selectRoute;
		this.routeIdValue.setText(this.route.getRouteId()+"");
	}
	
	public void prepareBaseWindow() {
		this.baseWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setTitle("Schedule Create Form");
		this.baseWindow.setSize(800,400);
		this.baseWindow.setVisible(true);
	}
	
	public String getFlightLabel() {
		return "<html><a href='' style='color: black; text-decoration: none;'>" + "Please Click to select the flight "+ "</a></html>";
	}
	
	public String getRouteLabel() {
		return "<html><a href='' style='color: black; text-decoration: none;'>" + "Please Click to select the route "+ "</a></html>";
	}
}
