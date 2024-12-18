package view;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.border.EmptyBorder;

import org.jdatepicker.JDatePicker;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.SqlDateModel;

import Dao.FlightDaoImpl;
import Dao.FlightScheduleDaoImpl;
import Dao.RouteDaoImpl;
import Model.Flight;
import Model.FlightSchedule;
import Model.Route;
import Service.FlightScheduleCreateService;
import exception.InvalidFlightScheduleException;
import util.DateConverter;
import util.DateLabelFormatter;
import util.DateUtil;

public class ScheduleUpdateForm extends BaseWindow {
	
	private SqlDateModel deptModel;
	private JDatePanelImpl deptDatePanel;
	private JDatePickerImpl deptDatePicker;
	private JSpinner deptTimeSpinner;
	
	private SqlDateModel arriveModel;
	private JDatePanelImpl  arriveDatePanel;
	private JDatePickerImpl arriveDatePicker;
	private JSpinner arriveTimeSpinner;
	
	private SqlDateModel createdModel;
	private JDatePanelImpl createdDatePanel;
	private JDatePickerImpl createdDatePicker;
	private JSpinner createdTimeSpinner;
	
	private JLabel scheduleIdLabel;
	private JLabel scheduleIdValue;
	
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
	
	private JButton updateButton;
	private JButton cancelButton;
	
	private JPanel panel;
	private JPanel panel1;
	private JPanel panel2;
	
	private Flight flight;
	private Route route;
	private FlightSchedule schedule;
	
	private ScheduleListingPage parentPage;
	private FlightScheduleDaoImpl flightScheduleDao;
	private RouteDaoImpl routeDao;
	private FlightDaoImpl flightDao;
	
	public ScheduleUpdateForm(ScheduleListingPage parentPage, int id) {
		this.parentPage = parentPage;
		flightScheduleDao = new FlightScheduleDaoImpl();
		routeDao = new RouteDaoImpl();
		flightDao = new FlightDaoImpl();
		schedule = flightScheduleDao.getById(id);
		route = schedule.getRoute();
		flight = schedule.getFlight();
		initializeComponent();
		prepareBaseWindow();
	}
	
	public void initializeComponent() {
		
		scheduleIdLabel = new JLabel("Schedule Id");
		scheduleIdValue = new JLabel(schedule.getScheduleid()+"");
		
		routeIdLabel = new JLabel("Route Id");
		routeIdValue = new JLabel(schedule.getRoute().getRouteInfo());
		routeIdValue.setCursor(new Cursor(Cursor.HAND_CURSOR));
		
		flightIdLabel = new JLabel("Flight Id");
		flightIdValue = new JLabel(schedule.getFlight().getFlightInfo());
		flightIdValue.setCursor(new Cursor(Cursor.HAND_CURSOR));
		
		deptTimeLabel = new JLabel("Dept Time");
		
		this.deptModel = new SqlDateModel(DateConverter.toSqlDate(this.schedule.getDeptTime()));
		this.deptDatePanel = new JDatePanelImpl(deptModel, new Properties());
		this.deptDatePicker = new JDatePickerImpl(deptDatePanel, new DateLabelFormatter());
		this.deptTimeSpinner = this.createTimeSpinner(DateConverter.toUtilDate(this.schedule.getDeptTime()));
		
		
		arriveTimeLabel = new JLabel("Arrive Time");
		
		this.arriveModel = new SqlDateModel(DateConverter.toSqlDate(this.schedule.getArrivalTime()));
		this.arriveDatePanel = new JDatePanelImpl(arriveModel, new Properties());
		this.arriveDatePicker = new JDatePickerImpl(arriveDatePanel, new DateLabelFormatter());
		this.arriveTimeSpinner = this.createTimeSpinner(DateConverter.toUtilDate(this.schedule.getArrivalTime()));
		
		createdAtLabel = new JLabel("Created At");
		
		this.createdModel = new SqlDateModel(DateConverter.toSqlDate(this.schedule.getCreatedAt()));
		this.createdDatePanel = new JDatePanelImpl(createdModel, new Properties());
		this.createdDatePicker = new JDatePickerImpl(createdDatePanel, new DateLabelFormatter());
		this.createdTimeSpinner = this.createTimeSpinner(DateConverter.toUtilDate(this.schedule.getCreatedAt()));
		
		updateButton = new JButton("Update");
		cancelButton = new JButton("Cancel");
		
		panel = new JPanel();
		panel.setLayout(new GridLayout(3,2,40,40));
		panel.add(scheduleIdLabel);
		panel.add(scheduleIdValue);
		panel.add(routeIdLabel);
		panel.add(routeIdValue);
		panel.add(flightIdLabel);
		panel.add(flightIdValue);
		panel.setBorder(new EmptyBorder(10, 20, 0, 10));
		
		panel1 = new JPanel();
		panel1.setLayout(new GridLayout(3,3,40,40));
		panel1.add(deptTimeLabel);
		panel1.add(deptDatePicker);
		panel1.add(deptTimeSpinner);
		
		panel1.add(arriveTimeLabel);
		panel1.add(arriveDatePicker);
		panel1.add(arriveTimeSpinner);
		
		panel1.add(createdAtLabel);
		panel1.add(createdDatePicker);
		panel1.add(createdTimeSpinner);
		panel1.setBorder(new EmptyBorder(10, 20, 0, 60));
		
		panel2 = new JPanel();
		panel2.setLayout(new GridLayout(1,1));
		panel2.add(updateButton);
		panel2.add(cancelButton);
		
		addActionOnFlightLabel();
		addActionOnRouteLabel();
		addActionOnUpdateButton();
		
		this.baseWindow.add(panel,BorderLayout.NORTH);
		this.baseWindow.add(panel1,BorderLayout.CENTER);
		this.baseWindow.add(panel2,BorderLayout.SOUTH);
		
	}
	
	public JSpinner createTimeSpinner(Date selectedDate) {
	    SpinnerDateModel timeModel = new SpinnerDateModel(selectedDate, null, null, Calendar.HOUR_OF_DAY);
	    JSpinner timeSpinner = new JSpinner(timeModel);
	    JSpinner.DateEditor timeEditor = new JSpinner.DateEditor(timeSpinner, "HH:mm");
	    timeSpinner.setEditor(timeEditor);
	  
	    Dimension spinnerSize = new Dimension(10, 10);  
	    timeSpinner.setPreferredSize(spinnerSize);
	    
	    return timeSpinner;
	}
	
	

	
	public void addActionOnUpdateButton() {
		this.updateButton.addActionListener(e -> updateBtnAction());
	}
	
	private void updateBtnAction() {
	    this.schedule.setRoute(route);
	    this.schedule.setFlight(flight);

	    this.schedule.setArrivalTime(DateUtil.getSelectedDate(this.arriveDatePanel, this.arriveTimeSpinner));
	    this.schedule.setDeptTime(DateUtil.getSelectedDate(this.deptDatePanel, this.deptTimeSpinner));
	    this.schedule.setCreatedAt(DateUtil.getSelectedDate(this.createdDatePanel, this.createdTimeSpinner));
	    
	    try {
	    	new FlightScheduleCreateService(schedule);
	    	this.flightScheduleDao.update(schedule);
	    	  JOptionPane.showMessageDialog(baseWindow, "Successfully updated schedule !!!");
	  	    baseWindow.dispose();
		} catch (InvalidFlightScheduleException e) {
			JOptionPane.showMessageDialog(baseWindow,e.getMessage());
		}
	    

	  
	    parentPage.refreshTableData();
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
		FlightListingPage flightListingPage = new FlightListingPage(this);
		flightListingPage.call();
	}
	
	public void refreshFlightValueBtn(Flight selectFlight) {
		this.flight = selectFlight;
		this.flightIdValue.setText(this.flight.getFlightInfo()+"");
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
		RouteListingPage routeListingPage = new RouteListingPage(this,"scheduleUpdate");
		routeListingPage.call();
	}
	
	public void refreshRouteValueBtn(Route selectRoute) {
		this.route = selectRoute;
		this.routeIdValue.setText(this.route.getRouteInfo()+"");
	}
	
	public void prepareBaseWindow() {
		this.baseWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setTitle("Schedule Update Form");
		this.baseWindow.setSize(800,400);
		this.baseWindow.setVisible(true);
	}
	
	public String getFlightLabel() {
		return "<html><a href='' style='color: black; text-decoration: none;'>" + schedule.getFlight().getFlightid()+ "</a></html>";
	}
	
	public String getRouteLabel() {
		return "<html><a href='' style='color: black; text-decoration: none;'>" + schedule.getRoute().getRouteId()+ "</a></html>";
	}

	@Override
	public void renderPage() {
		// TODO Auto-generated method stub
		
	}
}
