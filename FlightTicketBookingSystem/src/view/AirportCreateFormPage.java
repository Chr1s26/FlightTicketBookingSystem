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
	private AirportListingPage parentPage;
	
	public AirportCreateFormPage(AirportListingPage parentPage) {
		this.airportDao = new AirportDaoImpl();
		this.parentPage = parentPage;
		initializeComponent();
		prepareBaseWindow();
	}
	
	@Override
	public void renderPage() {
		
	}
	
	public void initializeComponent() {
		airportNameLabel = new JLabel("Airport Name");
		airportNameValue = new JTextField();
		createButton = new JButton("Create");
		cancelButton = new JButton("Cancel");
		
		panel = new JPanel();
		panel.setLayout(new GridLayout(2,2));
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
				String name = airportNameValue.getText();
				Airport airport = new Airport(name);
				airportDao.create(airport);
				JOptionPane.showMessageDialog(baseWindow, "Successfully created Airport !!!");
				baseWindow.dispose();
				parentPage.refreshTableData();
			}
		});
	}
	
	public void prepareBaseWindow() {
		this.baseWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setTitle("Airport Create Form");
		this.baseWindow.setSize(800,400);
		this.baseWindow.setVisible(true);
	}

	
}
