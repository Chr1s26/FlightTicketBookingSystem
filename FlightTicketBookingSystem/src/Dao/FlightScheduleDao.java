package Dao;

import Model.Flight;
import Model.FlightSchedule;

public interface FlightScheduleDao {

	FlightSchedule[] getFlightSchedule();
	FlightSchedule getFlightScheduleByFlight(Flight flight);

}
