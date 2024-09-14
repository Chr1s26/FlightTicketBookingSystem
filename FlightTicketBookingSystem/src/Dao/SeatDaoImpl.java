package Dao;

import Model.Seat;

public class SeatDaoImpl implements SeatDao {
	
	Seat[] seats = {
	            new Seat("A1", "Economy", 100.0, true),
	            new Seat("A2", "Economy", 100.0, true),
	            new Seat("A3", "Economy", 100.0, true),
	            new Seat("B1", "Business", 500.0, true),
	            new Seat("B2", "Business", 500.0, true),
	            new Seat("B3", "Business", 500.0, true)
	            };
	    

	@Override
	public Seat[] getAllSeats() {
		return seats;
	}

	@Override
	public Seat getSeatByNumber(String seatNumber) {
	    for (Seat seat : seats) {
            if (seat.getSeatNumber().equals(seatNumber)) {
                return seat;
            }
        }
        return null;
    }
	}



