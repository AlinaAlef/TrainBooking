package trainBooking.model.dao;

import trainBooking.model.entity.Route;

public interface RouteDAO {
	/**
	 * Find the route by departure and
	 * destination stations, departure
	 * time.
	 *
	 * @param route contains departure and
	 * destination stations, departure
	 * time
	 *
	 * @return list of routs
	 */
	Iterable<Route> findRoutes(Route route);
	Route findRoute(String id);
}
