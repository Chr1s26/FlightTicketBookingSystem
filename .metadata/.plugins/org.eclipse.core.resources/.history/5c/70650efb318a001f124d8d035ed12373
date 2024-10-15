package Service;

import java.io.IOException;
import java.sql.SQLException;
import Dao.CustomerDaoImpl;
import Dao.FlightScheduleDaoImpl;
import Dao.RouteDaoImpl;
import Dao.SeatDaoImpl;
import Model.Customer;
import Model.FlightSchedule;
import Model.Seat;
import Model.Ticket;

public class BookingService extends BaseService {
	
	CustomerDaoImpl customerDao = new CustomerDaoImpl();
	FlightScheduleDaoImpl flightScheduleDao = new FlightScheduleDaoImpl();
	RouteDaoImpl routeDao = new RouteDaoImpl();
	SeatDaoImpl seatdao = new SeatDaoImpl();

	public void book() throws IOException, SQLException {
		
		System.out.println(customerDao.getAll());
		System.out.println("Please enter the customer Id : ");
		int id = Integer.parseInt(bufferedReader.readLine());
		Customer customer = customerDao.getById(id);
		
		
		System.out.println(routeDao.getAll());
		System.out.println("Please choose the routes by id : ");
		int routeid = Integer.parseInt(bufferedReader.readLine()); 
		
		
		System.out.println(flightScheduleDao.getAll());
		System.out.println("Please choose the flight schedules : ");
		int scheduleid = Integer.parseInt(bufferedReader.readLine());
		FlightSchedule flightSchedule = flightScheduleDao.getById(scheduleid);
		
		System.out.println(seatdao.getAll());
		System.out.println("Please choose the seat : ");
		int seatid = Integer.parseInt(bufferedReader.readLine());
		Seat seat = seatdao.getById(seatid);
		
		System.out.println("You have successfully booked the airflight");
		double price = 40.0;
		Ticket ticket = new Ticket(flightSchedule, customer,seat,price);
	}
	
	

}
