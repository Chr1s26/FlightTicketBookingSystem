package Dao;


import Model.Seat;
import java.util.List;

public abstract class SeatDao extends AbstractDao<Seat> {

	public abstract boolean isAvailableSeat(int schedule_id, int seat_id);
	public abstract List<Seat> getAvailableSeatsBySchedule(int scheduleId);
}
