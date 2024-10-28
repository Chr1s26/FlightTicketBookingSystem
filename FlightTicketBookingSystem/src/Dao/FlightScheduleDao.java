package Dao;

import java.util.List;

import Model.FlightSchedule;
import Model.FlightSchedule;

public abstract class FlightScheduleDao extends AbstractDao<FlightSchedule>{

	public abstract List<FlightSchedule> getScheduleByRouteAndFlight(FlightSchedule schedule) ;


}
