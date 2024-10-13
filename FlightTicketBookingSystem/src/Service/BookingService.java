package Service;

import java.io.IOException;

import Dao.AirportDao;
import Dao.AirportDaoImpl;
import Dao.CustomerDao;
import Dao.CustomerDaoImpl;
import Dao.FlightScheduleDao;
import Dao.FlightScheduleDaoImpl;
import Dao.RouteDao;
import Dao.RouteDaoImpl;
import Dao.SeatDao;
import Dao.SeatDaoImpl;
import Model.Customer;
import Model.FlightSchedule;
import Model.Ticket;

public class BookingService extends BaseService {
	
	CustomerDao customerDao = new CustomerDaoImpl();
	FlightScheduleDao flightScheduleDao = new FlightScheduleDaoImpl();
	RouteDao routeDao = new RouteDaoImpl();
	SeatDao seatdao = new SeatDaoImpl();

	public void book() throws IOException {
		System.out.println("Please enter the customer name : ");
		String name = bufferedReader.readLine();
		Customer customer = customerDao.getCustomerByName(name);
		System.out.println(routeDao.getAllRoutes());
		System.out.println("Please choose the routes by id : ");
		int routeid = Integer.parseInt(bufferedReader.readLine()); 
		System.out.println(flightScheduleDao.getFlightSchedule());
		System.out.println("Please choose the flight schedules : ");
		int scheduleid = Integer.parseInt(bufferedReader.readLine());
		System.out.println(seatdao.getAllSeats());
		System.out.println("Please choose the seat : ");
		int seatid = Integer.parseInt(bufferedReader.readLine());
		System.out.println("You have successfully booked the airflight");
		Ticket ticket = new Ticket(null, customer, seatid, null);
	}
	

}
