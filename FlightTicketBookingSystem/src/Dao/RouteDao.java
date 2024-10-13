package Dao;

import java.util.List;

import Model.Route;

public interface RouteDao {

	List<Route> getAllRoutes();
    Route getRouteById(int id);
    void addRoute(Route route);

}
