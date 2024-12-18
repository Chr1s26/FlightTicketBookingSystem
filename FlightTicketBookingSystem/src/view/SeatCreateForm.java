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
import javax.swing.LayoutFocusTraversalPolicy;

import Dao.FlightDaoImpl;
import Dao.SeatDaoImpl;
import Model.Airport;
import Model.Flight;
import Model.Seat;


public class SeatCreateForm extends BaseWindow {
	private JLabel seatIdLabel;
	private JTextField seatIdValue;
	
	private JLabel seatTypeLabel;
	private JLabel seatFlightLabel;
	private JLabel seatNumberLabel;
	
	private JTextField seatTypeValue;
	private JLabel seatFlightValue;
	private JTextField seatNumberValue;
	
	private JButton createButton;
	private JButton cancelButton;
	
	private JPanel panel;
	
	private SeatDaoImpl seatDao;
	private FlightDaoImpl flightDao;
	private SeatListingPage parentPage;
	
	private Flight flight;
	
	 public SeatCreateForm(SeatListingPage parentPage) {
		this.seatDao = new SeatDaoImpl();
		this.flightDao = new FlightDaoImpl();
		this.parentPage = parentPage;
		initializeComponent();
		prepareBaseWindow();
	}
	
	public void initializeComponent() {
		
		seatTypeLabel = new JLabel("seat Type");
		seatTypeValue = new JTextField();
		
		seatFlightLabel = new JLabel("Flight Name");
		seatFlightValue = new JLabel(getFlightLabel());
		seatFlightValue.setCursor(new Cursor(Cursor.HAND_CURSOR));
		
		seatNumberLabel = new JLabel("Seat Number");
		seatNumberValue = new JTextField();
		createButton = new JButton("Create");
		cancelButton = new JButton("Cancel");
		
		panel = new JPanel();
		panel.setLayout(new GridLayout(4,2));
		panel.add(seatTypeLabel);
		panel.add(seatTypeValue);
		panel.add(seatFlightLabel);
		panel.add(seatFlightValue);
		panel.add(seatNumberLabel);
		panel.add(seatNumberValue);
		panel.add(createButton);
		panel.add(cancelButton);
		addActionOnFlightLabel();
		addActionOnCreateButton();
		this.baseWindow.add(panel,BorderLayout.NORTH);
	}
	
	public void addActionOnFlightLabel() {
		this.seatFlightValue.addMouseListener(new MouseAdapter() {
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
		this.seatFlightValue.setText(this.flight.getFlightname());
	}
	
	public void addActionOnCreateButton() {
		this.createButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String type = seatTypeValue.getText();
				String seatNumber = seatNumberValue.getText();
				Seat seat = new Seat(type,flight,seatNumber);
				seatDao.create(seat);
				JOptionPane.showMessageDialog(baseWindow, "Successfully created seat !!!");
				baseWindow.dispose();
				parentPage.refreshTableData();
			}
		});
	}
	
	public void prepareBaseWindow() {
		this.baseWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setTitle("Seat Create Form");
		this.baseWindow.setSize(800,400);
		this.baseWindow.setVisible(true);
	}
	
	public String getFlightLabel() {
		return "<html><a href='' style='color: black; text-decoration: none;'>" + "Please Click to select the flight "+ "</a></html>";
	}

	@Override
	public void renderPage() {
		// TODO Auto-generated method stub
		
	}
}
