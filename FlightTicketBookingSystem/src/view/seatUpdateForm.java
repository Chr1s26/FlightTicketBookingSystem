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
import javax.swing.LayoutFocusTraversalPolicy;

import Dao.FlightDaoImpl;
import Dao.SeatDaoImpl;
import Model.Flight;
import Model.Seat;


public class seatUpdateForm extends BaseWindow {
	private JLabel seatIdLabel;
	private JLabel seatIdValue;
	
	private JLabel seatTypeLabel;
	private JLabel seatFlightLabel;
	private JLabel seatNumberLabel;
	
	private JTextField seatTypeValue;
	private JTextField seatFlightValue;
	private JTextField seatNumberValue;
	
	private JButton updateButton;
	private JButton cancelButton;
	
	private JPanel panel;
	
	private SeatDaoImpl seatDao;
	private FlightDaoImpl flightDao;
	
	 public seatUpdateForm(int id) {
		initializeComponent(id);
		prepareBaseWindow();
	}
	
	public void initializeComponent(int id) {
		this.seatDao = new SeatDaoImpl();
		this.flightDao = new FlightDaoImpl();
		seatIdLabel = new JLabel("seat Id");
		seatIdValue = new JLabel(id+"");
		seatTypeLabel = new JLabel("seat Type");
		seatTypeValue = new JTextField();
		seatFlightLabel = new JLabel("Flight Id");
		seatFlightValue = new JTextField();
		seatNumberLabel = new JLabel("Seat Number");
		seatNumberValue = new JTextField();
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
		
		addActionOnupdateButton(id);
		this.baseWindow.add(panel,BorderLayout.NORTH);
	}
	
	public void addActionOnupdateButton(int id) {
		this.updateButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String type = seatTypeValue.getText();
				int flightid = Integer.parseInt(seatFlightValue.getText());
				Flight flight = flightDao.getById(flightid);
				String seatNumber = seatNumberValue.getText();
				Seat seat = new Seat(id,type,flight,seatNumber);
				seatDao.updateSeat(seat);
				JOptionPane.showMessageDialog(baseWindow, "Successfully updated seat !!!");
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
