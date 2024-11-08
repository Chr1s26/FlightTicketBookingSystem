package view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import Dao.FlightScheduleDaoImpl;
import Dao.SeatDaoImpl;
import Model.FlightSchedule;
import Model.Seat;

public class SeatView extends BaseWindow {

	private String[] columns = {"Id","Seat Number","Seat Type","Seat Price"};
	private FlightScheduleDaoImpl flightScheduleDaoImpl;
	private SeatDaoImpl seatDao;
	private FlightSchedule flightSchedule;
	private JButton ticketBookingBtn;
	
	public SeatView(int scheduleId) {
		
		this.flightScheduleDaoImpl = new FlightScheduleDaoImpl();
		flightSchedule= this.flightScheduleDaoImpl.getById(scheduleId);
		
		this.addBtnComponent();
		
		String title = "Avaiable Seats for "+ flightSchedule.getFlight().getFlightname() + "("+flightSchedule.getFlight().getFlightNumber()+")";
		this.setTitle(title);
	
		this.createDataTable(getSeatsdata(), columns);
		
		this.baseWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.baseWindow.setSize(800,400);
		this.baseWindow.setVisible(true);
	}
	
	public String[][] getSeatsdata(){
		this.seatDao = new SeatDaoImpl();
		List<Seat> seats = this.seatDao.getAvaiableSeatBySchedule(this.flightSchedule.getScheduleid());
		String[][] seatData = new String[seats.size()][columns.length];
		int rowCount = 0;
		for(Seat seat : seats) {
			seatData[rowCount] = seat.toArray();
			rowCount++;
		}
		return seatData;
	}
	
	public void addBtnComponent() {
		this.ticketBookingBtn = new JButton("Booking Ticket");
		addActionBookingTicketBtn();
		this.baseWindow.add(this.ticketBookingBtn,BorderLayout.SOUTH);
	}
	
	public void addActionBookingTicketBtn() {
		this.ticketBookingBtn.addActionListener(e -> actionBookingTicketBtn());
	}

	private void actionBookingTicketBtn() {
		int selectedIndex = getDataTableTemplate().getSelectedRow();
		if(selectedIndex != -1) {
			int seatId = Integer.parseInt(getSeatsdata()[selectedIndex][0]);
			TicketBookingPage ticketBookingPage = new TicketBookingPage(flightSchedule.getScheduleid(),seatId);
			this.baseWindow.setVisible(false);
		}else {
			JOptionPane.showMessageDialog(baseWindow, "Please select a seat !!!");
		}
	}

	@Override
	public void renderPage() {
		// TODO Auto-generated method stub
		
	}
}
