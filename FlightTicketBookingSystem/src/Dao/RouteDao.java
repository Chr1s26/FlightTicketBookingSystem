package Dao;

import Model.Route;

public interface RouteDao {

	Route[] getAllRoutes();
    Route getRouteById(int id);

}
