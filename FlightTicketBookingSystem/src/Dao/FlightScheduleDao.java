package Dao;

import java.util.List;

import Model.FlightSchedule;
import Model.FlightSchedule;

public interface FlightScheduleDao {

	public abstract void updateFlightSchedule(FlightSchedule flightSchedule);
	public abstract void deleteFlightSchedule(int flightScheduleId);

}
