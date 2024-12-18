package view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import Dao.RouteDaoImpl;
import Model.Flight;
import Model.Route;

public class RouteListingPage extends BaseWindow {
	
	private RouteDaoImpl routeDao;
	private String[] columns = {"RouteId","Arrive Airport","Dept Airport","Distance"};
	private JButton createButton;
	private JButton updateButton;
	private JButton deleteButton;
	private JPanel panel;
	private BaseWindow parentWindow;
	private String[][] routeDataTable;
	private JButton selectButton;
	private String type;
	
	public RouteListingPage() {
		
		initializeComponent();
		panel = new JPanel();
		panel.setLayout(new GridLayout(1, 3));
		
		
		this.createButton = new JButton("Create");
		this.updateButton = new JButton("Update");
		this.deleteButton = new JButton("Delete");
		
		panel.add(createButton);
		panel.add(updateButton);
		panel.add(deleteButton);
		
		this.baseWindow.add(panel, BorderLayout.SOUTH);

		this.addActionOnCreateButton();
		this.addActionOnUpdateButton();
		this.addActionOnDeleteButton();
		
	}
	
	public RouteListingPage(BaseWindow parentWindow,String type) {
		this.type = type;
		this.parentWindow = parentWindow;
		initializeComponent();
		this.selectButton = new JButton("Select");
		panel = new JPanel();
		panel.setLayout(new GridLayout(1,1));
		panel.add(this.selectButton);
		this.baseWindow.add(panel,BorderLayout.SOUTH);
		this.addActionOnSelectButton();
	}
	
	public void initializeComponent() {
		routeDao = new RouteDaoImpl();
		this.getAllRouteData();
		this.createDataTable(routeDataTable, columns);
	}
	
	public void addActionOnSelectButton() {
		this.selectButton.addActionListener(e -> selectAction());
	}
	
	public void selectAction() {
		int selectedRowIndex = getSelectedRow();
		
		if (selectedRowIndex == -1) {
			JOptionPane.showMessageDialog(baseWindow, "Please select a route to update.");
			return;
		}
		
		int routeId = getRouteIdFromSelectedRow(selectedRowIndex);
		selectRouteAndRefresh(routeId);
		this.baseWindow.dispose();
	}
	
	public void selectRouteAndRefresh(int routeId) {
		Route selectedRoute = this.routeDao.getById(routeId);
		if(type.equalsIgnoreCase("schedule")) {
		ScheduleCreateForm scheduleCreateForm = (ScheduleCreateForm)this.parentWindow;
		scheduleCreateForm.refreshRouteValueBtn(selectedRoute);}
		else {
			ScheduleUpdateForm scheduleUpdateForm = (ScheduleUpdateForm)this.parentWindow;
			scheduleUpdateForm.refreshRouteValueBtn(selectedRoute);
		}
		
	}
	
	public void refreshTableData() {
		this.getAllRouteData();
		super.refreshDataTable(routeDataTable);
	}
	
	
	private void getAllRouteData() {
		List<Route> routes = routeDao.getAll();
		this.routeDataTable = getRouteToStringArr(routes);
		
	}
	
	private String[][] getRouteToStringArr(List<Route> routes){
		
		String[][] routeArray = new String[routes.size()][columns.length];
		int rowCount = 0;
		for(Route route : routes) {
			routeArray[rowCount][0] = route.getRouteId()+"";
			routeArray[rowCount][1] = route.getArrivalAirport().getName();
			routeArray[rowCount][2] = route.getDepatureAirport().getName();
			routeArray[rowCount][3] = route.getDistance()+"";
			rowCount++;
		}
		return routeArray;
	}

	private void addActionOnDeleteButton() {
		this.deleteButton.addActionListener(e -> handleDeleteAction());
		
	}
	
	private void handleDeleteAction() {

		int selectedRowIndex = getSelectedRow();

		if (selectedRowIndex == -1) {
			JOptionPane.showMessageDialog(baseWindow, "Please select a route to delete.");
			return;
		}

		int routeId = getRouteIdFromSelectedRow(selectedRowIndex);

		if (confirmDeletion(routeId)) {
			deleteRouteAndRefresh(routeId);
		}
	}
	
	private int getSelectedRow() {
		return getDataTableTemplate().getSelectedRow();
	}
	
	private int getRouteIdFromSelectedRow(int rowIndex) {
		return Integer.parseInt(routeDataTable[rowIndex][0]);
	}
	
	private boolean confirmDeletion(int routeId) {
		int response = JOptionPane.showConfirmDialog(baseWindow,
				"Are you sure you want to delete route with ID " + routeId + "?", "Confirm Deletion",
				JOptionPane.YES_NO_OPTION);
		return response == JOptionPane.YES_OPTION;
	}

	private void deleteRouteAndRefresh(int routeId) {
		routeDao.delete(routeId);
		this.refreshTableData();
	}


	private void addActionOnUpdateButton() {
		this.updateButton.addActionListener(e -> routeUpdateAction());
	}
	
	public void routeUpdateAction() {
		int selectedRowIndex = getDataTableTemplate().getSelectedRow();
		int routeId = Integer.parseInt(routeDataTable[selectedRowIndex][0]);
		new RouteUpdateForm(this, routeId);
	}

	private void addActionOnCreateButton() {
		this.createButton.addActionListener(e -> RouteCreateAction());
	}
	
	public void RouteCreateAction() {
		RouteCreateForm RouteCreateForm = new RouteCreateForm(this);
	}

	public void call() {
		prepareBaseWindow();
	}
	
	public void prepareBaseWindow() {
		this.baseWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setTitle("Route Information");
		this.baseWindow.setSize(800, 400);
		this.baseWindow.setVisible(true);
	}

	@Override
	public void renderPage() {
		// TODO Auto-generated method stub
		
	}
	
}
