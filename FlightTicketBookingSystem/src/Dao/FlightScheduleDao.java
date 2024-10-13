package Dao;

import java.util.List;

import Model.Flight;
import Model.FlightSchedule;

public interface FlightScheduleDao {

	List<FlightSchedule> getFlightSchedule();
	FlightSchedule getFlightScheduleById(int id);
	void addFlightSchedule(FlightSchedule flightSchedule);

}
