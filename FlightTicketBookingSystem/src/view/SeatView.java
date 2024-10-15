package view;

import Dao.FlightScheduleDaoImpl;
import Dao.SeatDaoImpl;
import Model.FlightSchedule;
import Model.Seat;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class SeatView extends BaseWindow{
    private SeatDaoImpl seatDao;
    private JTable scheduleDataTable;
    private String[] columns = {"Id", "Seat Number", "Type", "Price"};
    private FlightScheduleDaoImpl flightScheduleDaoImpl;
    private JScrollPane scrollPane;

    public SeatView(int scheduleId){
        this.seatDao = new SeatDaoImpl();
        this.flightScheduleDaoImpl = new FlightScheduleDaoImpl();
        List<Seat> seats = this.seatDao.getAvailableSeatsBySchedule(scheduleId);
        FlightSchedule flightSchedule= this.flightScheduleDaoImpl.getById(scheduleId);
        String title = "Avaliable Seats For "+flightSchedule.getFlight().getFlightname() +"("+flightSchedule.getFlight().getFlightNumber()+")";
        this.setTitle(title);

        String[][] seatData = new String[seats.size()][columns.length];
        int rowCount = 0;
        for(Seat seat : seats){
            seatData[rowCount] = seat.toArray();
            rowCount++;
        }
        this.createDataTable(seatData, columns);
        this.baseWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.baseWindow.setSize(800, 400);
        this.baseWindow.setVisible(true);
    }
}
