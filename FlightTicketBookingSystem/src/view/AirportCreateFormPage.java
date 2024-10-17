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

public class AirportCreateFormPage extends BaseWindow {
	private JLabel airportIdLabel;
	private JTextField airportIdValue;
	
	private JLabel airportNameLabel;
	private JTextField airportNameValue;
	
	private JButton createButton;
	private JButton cancelButton;
	
	private JPanel panel;
	
	private AirportDaoImpl airportDao;
	
	public AirportCreateFormPage() {
		this.airportDao = new AirportDaoImpl();
		initializeComponent();
		prepareBaseWindow();
	}
	
	public void initializeComponent() {
		airportIdLabel = new JLabel("Airport Id");
		airportIdValue = new JTextField();
		airportNameLabel = new JLabel("Airport Name");
		airportNameValue = new JTextField();
		createButton = new JButton("Create");
		cancelButton = new JButton("Cancel");
		
		panel = new JPanel();
		panel.setLayout(new GridLayout(3,3));
		panel.add(airportIdLabel);
		panel.add(airportIdValue);
		panel.add(airportNameLabel);
		panel.add(airportNameValue);
		panel.add(createButton);
		panel.add(cancelButton);
		
		addActionOnCreateButton();
		this.baseWindow.add(panel,BorderLayout.NORTH);
	}
	
	public void addActionOnCreateButton() {
		this.createButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int id = Integer.parseInt(airportIdValue.getText());
				String name = airportNameValue.getText();
				Airport airport = new Airport(id,name);
				airportDao.create(airport);
				JOptionPane.showMessageDialog(baseWindow, "Successfully created Airport !!!");
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
