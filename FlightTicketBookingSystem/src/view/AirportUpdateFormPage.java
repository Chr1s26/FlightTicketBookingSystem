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

import Dao.AirportDaoImpl;
import Model.Airport;

public class AirportUpdateFormPage extends BaseWindow {
	private JLabel airportIdLabel;
	private JLabel airportIdValue;
	
	private JLabel airportNameLabel;
	private JTextField airportNameValue;
	
	private JButton updateButton;
	private JButton cancelButton;
	
	private JPanel panel;
	
	private AirportDaoImpl airportDao;
	private AirportListingPage parentPage;
	private Airport airport;
	
	public AirportUpdateFormPage(AirportListingPage parentPage,int id) {
		this.parentPage = parentPage;
		this.airportDao = new AirportDaoImpl();
		this.airport = airportDao.getById(id);
		initializeComponent();
		addActionOnUpdateButton();
		prepareBaseWindow();
	}
	
	public void initializeComponent() {
		airportIdLabel = new JLabel("Airport Id");
		airportIdValue = new JLabel(this.airport.getAirportId()+"");
		airportNameLabel = new JLabel("Airport Name");
		airportNameValue = new JTextField(this.airport.getName());
		updateButton = new JButton("Update");
		cancelButton = new JButton("Cancel");
		
		panel = new JPanel();
		panel.setLayout(new GridLayout(3,3));
		panel.add(airportIdLabel);
		panel.add(airportIdValue);
		panel.add(airportNameLabel);
		panel.add(airportNameValue);
		panel.add(updateButton);
		panel.add(cancelButton);
		
		
		this.baseWindow.add(panel,BorderLayout.NORTH);
	}
	
	public void addActionOnUpdateButton() {
		this.updateButton.addActionListener(e -> airportUpdateAction());
	}
	
	public void airportUpdateAction() {
		String name = airportNameValue.getText();
		Airport airport = new Airport(this.airport.getAirportId(),name);
		airportDao.update(airport);
		JOptionPane.showMessageDialog(baseWindow, "Successfully updated Airport !!!");
		baseWindow.dispose();
		this.parentPage.refreshTableData();
	}
	
	public void prepareBaseWindow() {
		this.baseWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setTitle("Airport Update Form");
		this.baseWindow.setSize(800,400);
		this.baseWindow.setVisible(true);
	}

	@Override
	public void renderPage() {
		// TODO Auto-generated method stub
		
	}
}
