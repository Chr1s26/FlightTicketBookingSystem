package Service;

import java.io.IOException;
import java.sql.SQLException;
import Dao.CustomerDaoImpl;
import Dao.FlightScheduleDaoImpl;
import Dao.RouteDaoImpl;
import Dao.SeatDaoImpl;
import Dao.TicketDaoImpl;
import Model.Customer;
import Model.FlightSchedule;
import Model.Seat;
import Model.Ticket;

public class BookingService extends BaseService {
	
	CustomerDaoImpl customerDao = new CustomerDaoImpl();
	FlightScheduleDaoImpl flightScheduleDao = new FlightScheduleDaoImpl();
	RouteDaoImpl routeDao = new RouteDaoImpl();
	SeatDaoImpl seatdao = new SeatDaoImpl();
	TicketDaoImpl ticketdao = new TicketDaoImpl();

	public void book() throws IOException, SQLException {
		
		Customer customer = this.chooseCustomer();
		this.chooseRoute();
		int flightScheduleid = this.chooseSchedule();
		
		this.chooseSeats(customer,flightScheduleid);
	}
	
	public void cancelbook() throws IOException {
		System.out.println("Please enter the customer name : ");
		String name = bufferedReader.readLine();
		Customer customer = customerDao.findCustomerByName(name);
		System.out.println(ticketdao.findticketByCustomerId(customer.getCustomerId()));
		System.out.println("Which booking do you want to delete");
		int id = Integer.parseInt(bufferedReader.readLine()) ;
		ticketdao.delete(id);
		System.out.print("Booking deleted successfully");
	}
	
	public void chooseSeats(Customer customer,int scheduleid) throws NumberFormatException, IOException {
		System.out.println(seatdao.getAll());
		System.out.println("Please choose the seat : ");
		int seatid = Integer.parseInt(bufferedReader.readLine());
		
		if(seatdao.isAvailableSeat(scheduleid, seatid)) {
			FlightSchedule flightSchedule = flightScheduleDao.getById(scheduleid);
			Seat seat = seatdao.getById(seatid);
			double price = seat.calculatePrice();
			Ticket ticket = new Ticket(flightSchedule, customer,seat,price);
			ticketdao.create(ticket);
		}
		else {
			System.out.println("Seat is already booked");
		}
		
		System.out.println("Do you want to book another seats : ");
		String answer = bufferedReader.readLine();
		if(answer.equalsIgnoreCase("yes")) {
			this.chooseSeats(customer, scheduleid);
		}
		else {
			System.out.print("You have booked successfully !!!");
		}
	}
	
	public Customer chooseCustomer() throws NumberFormatException, IOException {
		System.out.println(customerDao.getAll());
		System.out.println("Please enter the customer Id : ");
		int id = Integer.parseInt(bufferedReader.readLine());
		Customer customer = customerDao.getById(id);
		return customer;
	}
	
	public void chooseRoute() throws NumberFormatException, IOException {
		System.out.println(routeDao.getAll());
		System.out.println("Please choose the routes by id : ");
		int routeid = Integer.parseInt(bufferedReader.readLine()); 
	}
	
	public int chooseSchedule() throws NumberFormatException, IOException {
		System.out.println(flightScheduleDao.getAll());
		System.out.println("Please choose the flight schedules : ");
		int scheduleid = Integer.parseInt(bufferedReader.readLine());
		return scheduleid;
	}

}
