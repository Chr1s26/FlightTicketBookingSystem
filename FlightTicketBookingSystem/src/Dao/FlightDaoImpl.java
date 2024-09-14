package Dao;

import Model.Flight;
import Model.Seat;

public class FlightDaoImpl implements FlightDao {
	
	private SeatDao seatDao = new SeatDaoImpl();
	Seat[] seats = seatDao.getAllSeats();
	
	private Flight[] flights = {
			 new Flight("Flight 1", "FL123", seats),
	         new Flight("Flight 2", "FL456", seats),
	         new Flight("Flight 3", "FL876", seats)
	};

	@Override
	public Flight[] getAllFlights() {
		return flights;
	}

	@Override
	public Flight getFlightByFlightNumber(String number) {
		for (Flight flight : flights) {
            if (flight.getFlightNumber().equalsIgnoreCase(number)) {
                return flight;
            }
        }
        return null;
    }
	
	}

	
