package view;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Dao.FlightDaoImpl;
import Model.Flight;

public class FlightUpdateForm extends BaseWindow {
	
	private JLabel flightIdLabel;
	private JLabel flightIdValueLabel;
	
	private JLabel flightNameLabel;
	private JTextField flightNameValueLabel;
	
	private JLabel flightNumberLabel;
	private JTextField flightNumberValueLabel;
	
	private JButton updateButton;
	private JButton cancelButton;
	
	private JPanel panel;
	private Flight flight;
	
	private FlightDaoImpl flightDao;
	private FlightListingPage parentPage;
	
	public FlightUpdateForm(FlightListingPage flightListingPage,int id) {
		this.flightDao = new FlightDaoImpl();
		this.parentPage = flightListingPage;
		this.flight = flightDao.getById(id);
		initializeComponent();
		addActionOnUpdateButton();
		prepareBaseWindow();
	}
	
	public void initializeComponent(){
		flightIdLabel = new JLabel("Flight id : ");
		flightIdValueLabel = new JLabel(this.flight.getFlightid()+"");
		
		flightNameLabel = new JLabel("Flight Name : ");
		flightNameValueLabel = new JTextField(this.flight.getFlightname());
		
		flightNumberLabel = new JLabel("Flight Number : ");
		flightNumberValueLabel = new JTextField(flight.getFlightNumber());
		
		updateButton = new JButton("Update");
		cancelButton = new JButton("Cancel");
		
		panel = new JPanel();
		panel.setLayout(new GridLayout(4,2));
		panel.add(flightIdLabel);
		panel.add(flightIdValueLabel);
		panel.add(flightNameLabel);
		panel.add(flightNameValueLabel);
		panel.add(flightNumberLabel);
		panel.add(flightNumberValueLabel);
		panel.add(updateButton);
		panel.add(cancelButton);
		this.baseWindow.add(panel,BorderLayout.NORTH);
		
	}
	
	public void addActionOnUpdateButton() {
		this.updateButton.addActionListener(e -> flightUpdateAction());
	}
	
	public void flightUpdateAction() {
		String flightName = flightNameValueLabel.getText();
		String flightNumber = flightNumberValueLabel.getText();
		Flight flight = new Flight(this.flight.getFlightid(),flightName, flightNumber);
		flightDao.update(flight);
		JOptionPane.showMessageDialog(baseWindow, "Successfully updated flight !!!");
		baseWindow.dispose();
		this.parentPage.refreshTableData();
	}
	
	public void prepareBaseWindow() {
		this.baseWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setTitle("Flight Update Form");
		this.baseWindow.setSize(800,400);
		this.baseWindow.setVisible(true);
	}

	@Override
	public void renderPage() {
		// TODO Auto-generated method stub
		
	}
	
}
