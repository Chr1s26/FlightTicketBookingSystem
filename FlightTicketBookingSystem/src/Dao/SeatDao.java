package Dao;


import java.util.List;

import Model.Customer;
import Model.Seat;

public abstract class SeatDao extends AbstractDao<Seat> {

	public abstract boolean isAvailableSeat(int schedule_id, int seat_id);
	public abstract List<Seat> getAvaiableSeatBySchedule(int scheduleId);
}
