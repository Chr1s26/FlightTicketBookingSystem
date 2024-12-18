package Service;

import java.util.List;

import Dao.FlightScheduleDaoImpl;
import Model.FlightSchedule;
import exception.InvalidFlightScheduleException;

public class FlightScheduleCreateService {
	
	private FlightSchedule flightSchedule;
	private FlightScheduleDaoImpl flightScheduleDao;
	
	public FlightScheduleCreateService(FlightSchedule flightSchedule) throws InvalidFlightScheduleException {
		this.flightSchedule = flightSchedule;
		this.flightScheduleDao = new FlightScheduleDaoImpl();
		this.validateFlightSchedule();
		
	}
	
	public void validateFlightSchedule() throws InvalidFlightScheduleException {
		List<FlightSchedule> flightSchedules= this.flightScheduleDao.getScheduleByRouteAndFlight(flightSchedule);
		if(!flightSchedules.isEmpty()) {
			throw new InvalidFlightScheduleException("Flight Schedule Dept Time must be valid time");
		}
	}
	
}
