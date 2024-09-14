package Dao;

import Model.Airport;
import Model.Route;

public class RouteDaoImpl implements RouteDao {
	
	AirpotDao airpotDao = new AirportDaoImpl();
    Airport[] airportArr = airpotDao.getAirport();

	Route[] routeArr = {
			 new Route(1, airportArr[0], airportArr[1]),
	         new Route(2, airportArr[1], airportArr[0])
        };

	@Override
	public Route[] getAllRoutes() {
		return routeArr;
	}

	@Override
	public Route getRouteById(int id) {
		 for (Route route : routeArr) {
	            if (route.getRouteId() == id) {
	                return route;
	            }
	        }
	        return null;
	}

}
