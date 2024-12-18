package view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutFocusTraversalPolicy;

import Dao.FlightDaoImpl;
import Dao.SeatDaoImpl;
import Model.Customer;
import Model.Flight;
import Model.Seat;


public class seatUpdateForm extends BaseWindow {
	private JLabel seatIdLabel;
	private JLabel seatIdValue;
	
	private JLabel seatTypeLabel;
	private JLabel seatFlightLabel;
	private JLabel seatNumberLabel;
	
	private JComboBox<String> seatTypeValue;
	private JComboBox<Flight> seatFlightValue;
	private JTextField seatNumberValue;
	
	private JButton updateButton;
	private JButton cancelButton;
	
	private JPanel panel;
	
	private SeatDaoImpl seatDao;
	private FlightDaoImpl flightDao;
	
	private SeatListingPage parentPage;
	private Seat seat;
	private Flight flight;
	
	private List<Flight> flights;
	private Flight[] flightArray;
	
	 public seatUpdateForm(SeatListingPage parentPage,int id) {
		this.seatDao = new SeatDaoImpl();
		
		this.seat = seatDao.getById(id);
		this.flight = seat.getFlight();
		
		this.flightDao = new FlightDaoImpl();
		this.parentPage = parentPage;
		this.flights = this.flightDao.getAll();
		flightArray = this.flights.toArray(new Flight[0]);
		
		initializeComponent();
		addActionSeatTypeComboBox();
		addActionOnupdateButton();
		prepareBaseWindow();
	}
	
	public void initializeComponent() {
		seatIdLabel = new JLabel("seat Id : ");
		seatIdValue = new JLabel(this.seat.getSeatid()+"");
		
		seatTypeLabel = new JLabel("Select seat Type : ");
		seatTypeValue = new JComboBox<String>(Seat.SEAT_TYPES);
		seatTypeValue.setSelectedItem(this.seat.getSeatType());
		
		seatFlightLabel = new JLabel("Flight Id");
		seatFlightValue = new JComboBox<Flight>(flightArray);
		seatFlightValue.setSelectedItem(this.flight);
		
		seatNumberLabel = new JLabel("Seat Number");
		seatNumberValue = new JTextField(seat.getSeatNumber());
		updateButton = new JButton("Update");
		cancelButton = new JButton("Cancel");
		
		panel = new JPanel();
		panel.setLayout(new GridLayout(5,2));
		panel.add(seatIdLabel);
		panel.add(seatIdValue);
		panel.add(seatTypeLabel);
		panel.add(seatTypeValue);
		panel.add(seatFlightLabel);
		panel.add(seatFlightValue);
		panel.add(seatNumberLabel);
		panel.add(seatNumberValue);
		panel.add(updateButton);
		panel.add(cancelButton);
		
		this.baseWindow.add(panel,BorderLayout.NORTH);
	}
	
	public void addActionOnupdateButton() {
		this.updateButton.addActionListener(e -> seatUpdateAction());
	}
	
	public void addActionSeatTypeComboBox() {
		this.seatTypeValue.addActionListener(e -> seatTypeSelectAction());
	}
	
	public void seatTypeSelectAction() {
		String seatType = (String) (seatTypeValue.getSelectedItem());
		this.seat.setSeatType(seatType);
	}
	
	public void seatUpdateAction() {
		String type = this.seat.getSeatType();
		this.flight = (Flight)(seatFlightValue.getSelectedItem());
		Flight flight = flightDao.getById(this.flight.getFlightid());
		String seatNumber = seatNumberValue.getText();
		Seat seat = new Seat(this.seat.getSeatid(),type,flight,seatNumber);
		seatDao.update(seat);
		JOptionPane.showMessageDialog(baseWindow, "Successfully updated seat !!!");
		baseWindow.dispose();
		this.parentPage.refreshTableData();
	}
	
	public void prepareBaseWindow() {
		this.baseWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setTitle("Seat Update Form");
		this.baseWindow.setSize(800,400);
		this.baseWindow.setVisible(true);
	}

	@Override
	public void renderPage() {
		// TODO Auto-generated method stub
		
	}
}
