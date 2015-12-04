package trainBooking.model.logic;

import java.sql.Date;
import java.sql.Time;
import java.util.Calendar;
import java.util.TimeZone;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import trainBooking.model.dao.RouteDAO;
import trainBooking.model.entity.Route;

public class FindTrainCommand extends Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		Route route = new Route();
		route.setDepartureStation(request.getParameter("departureStation"));
		route.setDestinationStation(request.getParameter("destinationStation"));

		String strDepartureDate = request.getParameter("departureDate");
		String strDepartureTime = request.getParameter("departureTime") + ":00";

		Date departureDate = Date.valueOf(strDepartureDate);
		Time departureTime = Time.valueOf(strDepartureTime);

		route.setDepartureTime(departureTime);

		Calendar currentDateTime = Calendar.getInstance(TimeZone
				.getTimeZone("GMT+2:00"));
		Calendar departureDateTime = Calendar.getInstance(TimeZone
				.getTimeZone("GMT+2:00"));

		// For some reason we get extra 12 hours every time so we subtract them.
		departureDateTime.setTimeInMillis(departureDate.getTime()
				+ departureTime.getTime() - 12 * 60 * 60 * 1000);

		// Check if it is not too late.
		if (departureDateTime.after(currentDateTime)) {
			RouteDAO routeDAO = daoFactory.getRouteDAO();

			// Find the routes by departure and destination stations, dep. time.
			Iterable<Route> routes = routeDAO.findRoutes(route);
			if (routes != null) {
				request.setAttribute("routes", routes);
				request.setAttribute("departureDate", departureDate);
			}
			/*
			 * // Get all trains by routeID and date. TrainDAO trainDAO =
			 * daoFactory.getTrainDAO(); Train train = new Train();
			 * train.setRouteID(resRoute.getId()); train.setDate(departureDate);
			 * trainDAO.findTrain(train);
			 */
		}
	}
}