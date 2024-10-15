package view;

import Dao.FlightScheduleDaoImpl;
import Model.FlightSchedule;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ScheduleListingPage extends BaseWindow {
    private JTable scheduleDataTable;
    private String[] columns = {"Id", "Flight Name", "Flight Number", "Dept Airport" ,"Arrival Airport", "Dept Time", "Arrive Time", "Created At"};
    private FlightScheduleDaoImpl flightScheduleDaoImpl;
    private JScrollPane scrollPane;
    private JButton getAvaliableSeatBtn;

    public ScheduleListingPage(){
        this.setTitle("Schedule Page");
        this.flightScheduleDaoImpl = new FlightScheduleDaoImpl();
        this.getAvaliableSeatBtn = new JButton("Show Available Seats");
        this.baseWindow.add(this.getAvaliableSeatBtn, BorderLayout.SOUTH);
        this.addActionAvailableSeatBtn();
        this.createDataTable(this.getScheduleData(), columns);
        this.baseWindow.setSize(800, 400);
        this.baseWindow.setVisible(true);
    }

    private void addActionAvailableSeatBtn() {
        this.getAvaliableSeatBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectRowIndex = getDataTableTample().getSelectedRow();
                System.out.println("selectRowIndex : "+ selectRowIndex);
                int scheduleId = Integer.parseInt(getScheduleData()[selectRowIndex][0]);
                System.out.println("scheduleId : "+ scheduleId);
                SeatView seatView = new SeatView(scheduleId);
            }
        });
    }

    private String[][] getScheduleData() {
        List<FlightSchedule> schedules = this.flightScheduleDaoImpl.getAll();
        String[][] schedulesData = new String[schedules.size()][8];
        int rowCount = 0;
        for (FlightSchedule schedule : schedules) {
            schedulesData[rowCount] = schedule.toArray();
            rowCount++;
        }
        return schedulesData;
    }
}
