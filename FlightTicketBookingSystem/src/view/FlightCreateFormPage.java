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

import Dao.FlightDaoImpl;
import Model.Flight;

public class FlightCreateFormPage extends BaseWindow{
	
	private FlightDaoImpl flightDao;
	
	private JLabel flightIdLabel;
	private JTextField flightIdValue;
	private JLabel flightNameLabel;
	private JTextField flightNameValue;
	private JLabel flightNumberLabel;
	private JTextField flightNumberValue;
	
	private JButton createButton;
	private JButton cancelButton;
	
	private JPanel panel;
	private FlightListingPage parentPage;
	
	public FlightCreateFormPage(FlightListingPage flightPage) {
		this.flightDao = new FlightDaoImpl();
		this.parentPage = flightPage;
		initializeComponent();
		prepareBaseWindow();
	}
	
	public void initializeComponent() {

		flightNameLabel = new JLabel("flight Name");
		flightNameValue = new JTextField();
		flightNumberLabel = new JLabel("flight Number");
		flightNumberValue = new JTextField();
		createButton = new JButton("Create");
		cancelButton = new JButton("Cancel");
		
		panel = new JPanel();
		panel.setLayout(new GridLayout(3,2));
		panel.add(flightNameLabel);
		panel.add(flightNameValue);
		panel.add(flightNumberLabel);
		panel.add(flightNumberValue);
		panel.add(createButton);
		panel.add(cancelButton);
		
		addActionOnCreateButton();
		this.baseWindow.add(panel,BorderLayout.NORTH);
	}
	
	public void addActionOnCreateButton() {
		this.createButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String name = flightNameValue.getText();
				String number = flightNumberValue.getText();
				Flight flight = new Flight(name, number);
				flightDao.create(flight);
				JOptionPane.showMessageDialog(baseWindow, "Successfully created flight !!!");
				baseWindow.dispose();
				parentPage.refreshTableData();
			}
		});
	}
	
	public void prepareBaseWindow() {
		this.baseWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setTitle("Flight Create Form");
		this.baseWindow.setSize(800,400);
		this.baseWindow.setVisible(true);
	}

	@Override
	public void renderPage() {
		// TODO Auto-generated method stub
		
	}
}
