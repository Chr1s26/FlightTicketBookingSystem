package view;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Dao.AirportDaoImpl;
import Dao.RouteDaoImpl;
import Model.Airport;
import Model.Route;

public class RouteUpdateForm extends BaseWindow {

	private JLabel routeIdLabel;
	private JLabel routeIdValueLabel;

	private JLabel arriveAriportLabel;
	private JLabel arriveAirportValueLabel;

	private JLabel deptAirportLabel;
	private JLabel deptAirportValueLabel;

	private JLabel distanceLabel;
	private JTextField distanceValueLabel;

	private JButton updateButton;
	private JButton cancelButton;

	private JPanel panel;

	private RouteDaoImpl routeDao;

	private RouteListingPage parentPage;
	private Route route;
	private Airport airport1;
	private Airport airport2;
	private AirportDaoImpl airportDao;

	public RouteUpdateForm(RouteListingPage parentPage, int id) {
		this.airportDao = new AirportDaoImpl();
		this.routeDao = new RouteDaoImpl();
		this.parentPage = parentPage;
		this.route = routeDao.getById(id);
		airport1 = route.getArrivalAirport();
		airport2 = route.getDepatureAirport();
		prepareBaseWindow();
		initializeComponent();
		
		addActionOnArriveAirportLabel();
		addActionOnDeptAirportLabel();
		addActionOnUpdateButton();
	}

	public void initializeComponent() {

		routeIdLabel = new JLabel("Route Id");
		routeIdValueLabel = new JLabel(route.getRouteId() + "");

		arriveAriportLabel = new JLabel("Arrive Airport");
		arriveAirportValueLabel = new JLabel(getArriveAirportLabel());
		arriveAirportValueLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));

		deptAirportLabel = new JLabel("Dept Airport");
		deptAirportValueLabel = new JLabel(getDeptAirportLabel());
		deptAirportValueLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));

		distanceLabel = new JLabel("Distance");
		distanceValueLabel = new JTextField(route.getDistance()+"");

		updateButton = new JButton("Update");
		cancelButton = new JButton("Cancel");

		panel = new JPanel();
		panel.setLayout(new GridLayout(5, 2));
		panel.add(routeIdLabel);
		panel.add(routeIdValueLabel);
		panel.add(arriveAriportLabel);
		panel.add(arriveAirportValueLabel);
		panel.add(deptAirportLabel);
		panel.add(deptAirportValueLabel);
		panel.add(distanceLabel);
		panel.add(distanceValueLabel);
		panel.add(updateButton);
		panel.add(cancelButton);
		
		this.baseWindow.add(panel, BorderLayout.NORTH);
	}
	
	public void addActionOnUpdateButton(){
		this.updateButton.addActionListener(e -> addAction());
	}
	
	public void addAction() {
		int routeid = route.getRouteId();
		int arriveAirportId = this.airport1.getAirportId();
		Airport Arriveairport = airportDao.getById(arriveAirportId);
		int deptAirportId = this.airport2.getAirportId();
		Airport deptAirport = airportDao.getById(deptAirportId);
		int distance = Integer.parseInt(distanceValueLabel.getText());
		Route route = new Route(routeid,Arriveairport, deptAirport, distance);
		routeDao.update(route);
		JOptionPane.showMessageDialog(baseWindow, "Successfully updated route !!!");
		baseWindow.dispose();
		this.parentPage.refreshTableData();
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
		AirportListingPage airportListingPage = new AirportListingPage(this, "arrive");
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
		AirportListingPage airportListingPage = new AirportListingPage(this, "dept");
		airportListingPage.call();
	}

	public void refreshDeptAirportValueBtn(Airport selectDeptAirport) {
		this.airport2 = selectDeptAirport;
		this.deptAirportValueLabel.setText(this.airport2.getName());
	}

	public void prepareBaseWindow() {
		this.baseWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setTitle("Route Update Form");
		this.baseWindow.setSize(800, 400);
		this.baseWindow.setVisible(true);
	}

	public String getArriveAirportLabel() {
		return "<html><a href='' style='color: black; text-decoration: none;'>" + route.getArrivalAirport().getName()
				+ "</a></html>";
	}

	public String getDeptAirportLabel() {
		return "<html><a href='' style='color: black; text-decoration: none;'>" + route.getDepatureAirport().getName()
				+ "</a></html>";
	}

	@Override
	public void renderPage() {
		// TODO Auto-generated method stub
		
	}
}
