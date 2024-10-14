package Dao;

import Model.FlightSchedule;
import Model.Seat;
import Model.Ticket;

public class TicketDaoImpl implements TicketDao {
	
	FlightScheduleDao flightScheduleDao = new FlightScheduleDaoImpl();
	SeatDao seatDao = new SeatDaoImpl();
	
	FlightSchedule[] schedules = flightScheduleDao.getFlightSchedule();
	Seat[] seats = seatDao.getAllSeats();
	
	Ticket[] tickets = {
			 new Ticket(1, schedules[0], seats[0].getSeatPrice(), seats[0]),
	         new Ticket(2, schedules[1], seats[1].getSeatPrice(), seats[1])
	};

	@Override
	public Ticket[] getAllTickets() {
		return tickets;
	}

	@Override
	public Ticket getTicketById(int id) {
		for (Ticket ticket : tickets) {
            if (ticket.getTicketId() == id) {
                return ticket;
            }
        }
        return null;
    }
	}

	
