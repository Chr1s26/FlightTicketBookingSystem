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
	
	public AirportUpdateFormPage(int id) {
		this.airportDao = new AirportDaoImpl();
		initializeComponent(id);
		prepareBaseWindow();
	}
	
	public void initializeComponent(int id) {
		airportIdLabel = new JLabel("Airport Id");
		airportIdValue = new JLabel(id+"");
		airportNameLabel = new JLabel("Airport Name");
		airportNameValue = new JTextField();
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
		
		addActionOnUpdateButton(id);
		this.baseWindow.add(panel,BorderLayout.NORTH);
	}
	
	public void addActionOnUpdateButton(int id) {
		this.updateButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String name = airportNameValue.getText();
				Airport airport = new Airport(id,name);
				airportDao.updateAirport(airport);
				JOptionPane.showMessageDialog(baseWindow, "Successfully updated Airport !!!");
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
