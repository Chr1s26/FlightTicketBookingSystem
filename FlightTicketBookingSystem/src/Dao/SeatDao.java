package Dao;

import Model.Seat;

public interface SeatDao {

	 Seat[] getAllSeats();
	 Seat getSeatByNumber(String seatNumber);

}
