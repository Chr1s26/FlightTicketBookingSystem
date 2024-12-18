package view;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Dao.AirportDao;
import Dao.AirportDaoImpl;
import Dao.RouteDaoImpl;
import Model.Airport;
import Model.Route;

public class RouteCreateForm extends BaseWindow {
	
	private JLabel routeIdLabel;
	private JTextField routeIdValueLabel;

	private JLabel arriveAriportLabel;
	private JLabel arriveAirportValueLabel;
	
	private JLabel deptAirportLabel;
	private JLabel deptAirportValueLabel;
	
	private JLabel distanceLabel;
	private JTextField distanceValueLabel;
	
	private JButton createButton;
	private JButton cancelButton;
	
	private JPanel panel;
	
	private RouteDaoImpl routeDao;
	
	private RouteListingPage parentPage;
	private Route route;
	private Airport airport1;
	private Airport airport2;
	private AirportDaoImpl airportDao;
	
	public RouteCreateForm(RouteListingPage parentPage) {
		this.airportDao = new AirportDaoImpl();
		this.routeDao = new RouteDaoImpl();
		this.parentPage = parentPage;
		initializeComponent();
		prepareBaseWindow();
	}
	
	public void initializeComponent() {
		
		
		arriveAriportLabel = new JLabel("Arrive Airport");
		arriveAirportValueLabel = new JLabel(getArriveAirportLabel());
		arriveAirportValueLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
		
		deptAirportLabel = new JLabel("Dept Airport");
		deptAirportValueLabel = new JLabel(getDeptAirportLabel());
		deptAirportValueLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
		
		distanceLabel = new JLabel("Distance");
		distanceValueLabel = new JTextField();
		
		createButton = new JButton("Create");
		cancelButton = new JButton("Cancel");
		
		panel = new JPanel();
		panel.setLayout(new GridLayout(4,2));
		panel.add(arriveAriportLabel);
		panel.add(arriveAirportValueLabel);
		panel.add(deptAirportLabel);
		panel.add(deptAirportValueLabel);
		panel.add(distanceLabel);
		panel.add(distanceValueLabel);
		panel.add(createButton);
		panel.add(cancelButton);
		
		addActionOnArriveAirportLabel();
		addActionOnDeptAirportLabel();
		addActionOnCreateButton();
		this.baseWindow.add(panel,BorderLayout.NORTH);
	}
	
	public void addActionOnArriveAirportLabel() {
		this.arriveAirportValueLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
				selectArriveAirportAction();
			}
		});
	}
	
	public void selectArriveAirportAction() {
		AirportListingPage airportListingPage = new AirportListingPage(this,"arrive");
		airportListingPage.call();
	}
	
	public void refreshArriveAirportValueBtn(Airport selectArriveAirport) {
		this.airport1 = selectArriveAirport;
		this.arriveAirportValueLabel.setText(this.airport1.getName());
	}
	
	public void addActionOnDeptAirportLabel() {
		this.deptAirportValueLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
				selectDeptAirportAction();
			}
		});
	}
	
	public void selectDeptAirportAction() {
		AirportListingPage airportListingPage = new AirportListingPage(this,"dept");
		airportListingPage.call();
	}
	
	public void refreshDeptAirportValueBtn(Airport selectDeptAirport) {
		this.airport2 = selectDeptAirport;
		this.deptAirportValueLabel.setText(this.airport2.getName());
	}
	
	public void addActionOnCreateButton() {
		this.createButton.addActionListener(e -> createAction());
	}
	
	public void createAction() {
		int arriveAirportId = this.airport1.getAirportId();
		Airport Arriveairport = airportDao.getById(arriveAirportId);
		int deptAirportId = this.airport2.getAirportId();
		Airport deptAirport = airportDao.getById(deptAirportId);
		int distance = Integer.parseInt(distanceValueLabel.getText());
		Route route = new Route(Arriveairport, deptAirport, distance);
		routeDao.create(route);
		JOptionPane.showMessageDialog(baseWindow, "Successfully created route !!!");
		baseWindow.dispose();
		parentPage.refreshTableData();
	}
	
	public void prepareBaseWindow() {
		this.baseWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setTitle("Route Create Form");
		this.baseWindow.setSize(800,400);
		this.baseWindow.setVisible(true);
	}
	
	public String getArriveAirportLabel() {
		return "<html><a href='' style='color: black; text-decoration: none;'>" + "Please Click to select the airport "+ "</a></html>";
	}
	
	public String getDeptAirportLabel() {
		return "<html><a href='' style='color: black; text-decoration: none;'>" + "Please Click to select the airport "+ "</a></html>";
	}

	@Override
	public void renderPage() {
		// TODO Auto-generated method stub
		
	}
}
