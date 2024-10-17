package view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import Dao.FlightScheduleDaoImpl;
import Model.FlightSchedule;

public class ScheduleListingPage extends BaseWindow {
	
	private String[] columns = {"Id","Flight Name","Flight Number","Dept Airport","Arrival Airport","Dept Time","Arrival Time","Created At"};
	private FlightScheduleDaoImpl flightScheduleDaoImpl;
	private JButton getAvaiableSeatBtn;
	
	public ScheduleListingPage() {
		this.setTitle("Schedule Page");
		this.flightScheduleDaoImpl = new FlightScheduleDaoImpl();
		
		this.getAvaiableSeatBtn = new JButton("Show Avaiable Seats");
		this.baseWindow.add(this.getAvaiableSeatBtn,BorderLayout.SOUTH);
		
		this.addActionAvailableSeatBtn();
			
		this.createDataTable(this.getScheduleData(), columns);
		
		this.baseWindow.setSize(800,400);
		this.baseWindow.setVisible(true);
	}

	private void addActionAvailableSeatBtn() {
		this.getAvaiableSeatBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int selectedRowIndex = getDataTableTemplate().getSelectedRow();
				int scheduleId = Integer.parseInt(getScheduleData()[selectedRowIndex][0]); 
				SeatView seatView = new SeatView(scheduleId);
			}
		});
	}
	
	private String[][] getScheduleData(){
		List<FlightSchedule> schedules = this.flightScheduleDaoImpl.getAll();
		String[][] scheduleData = new String[schedules.size()][8];
		int rowCount = 0;
		for(FlightSchedule schedule : schedules) {
			scheduleData[rowCount] = schedule.toArray();
			rowCount++;
		}
		return scheduleData;
	}
}
