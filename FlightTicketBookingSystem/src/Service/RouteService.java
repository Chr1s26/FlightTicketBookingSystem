package Service;

import Dao.RouteDaoImpl;

public class RouteService {
	
	RouteDaoImpl routeDao = new RouteDaoImpl();
	
	public void showallRoute() {
		System.out.println(routeDao.getAll());
	}
}
