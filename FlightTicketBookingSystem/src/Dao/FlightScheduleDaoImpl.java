package Dao;

import Model.Flight;
import Model.FlightSchedule;
import Model.Route;

public class FlightScheduleDaoImpl implements FlightScheduleDao{
	
	FlightDao flightDao = new FlightDaoImpl();
	RouteDao routeDao = new RouteDaoImpl();
	
	Flight[] flights = flightDao.getAllFlights();
	Route[] routes = routeDao.getAllRoutes();
	
	private FlightSchedule[] scheduleArr = {
			 new FlightSchedule(flights[0], routes[0], "11.11.2025", "10:00"),
	         new FlightSchedule(flights[1], routes[1], "14.11.2025", "14:00")
	};

	@Override
	public FlightSchedule[] getFlightSchedule() {
		return scheduleArr;
	}

	@Override
	public FlightSchedule getFlightScheduleByFlight(Flight flight) {
		for (FlightSchedule schedule : scheduleArr) {
            if (schedule.getFlight().equalFlight(flight)) {
                return schedule;
            }
        }
        return null;
	}

	

}
