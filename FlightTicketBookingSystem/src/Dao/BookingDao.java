package Dao;

import Model.Booking;

public interface BookingDao {

	Booking[] getAllBookings();
    Booking getBookingById(int id);

}
