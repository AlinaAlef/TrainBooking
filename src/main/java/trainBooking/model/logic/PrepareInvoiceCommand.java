package trainBooking.model.logic;

import trainBooking.model.dao.PriceDAO;
import trainBooking.model.dao.RouteDAO;
import trainBooking.model.dao.TrainDAO;
import trainBooking.model.entity.Price;
import trainBooking.model.entity.Route;
import trainBooking.model.entity.Train;

import java.text.ParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PrepareInvoiceCommand extends Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		
		String buttonPressed =  request.getParameterNames().nextElement();
		Pattern patternSuite = Pattern.compile("^(\\w{5})(\\d{1,2})(\\_)(.+)$");
		Matcher matcher = null;
		
		// Determine the wagon type and route id
		matcher = patternSuite.matcher(buttonPressed);

		if (matcher.matches()) {
			String wagonType = matcher.group(1);
			String routeID = matcher.group(2);
			String departureDate = matcher.group(4);

			request.setAttribute("wagonType", wagonType);
			request.setAttribute("departureDate", departureDate);

			Train exampleTrain = new Train();
			exampleTrain.setRouteID(routeID);
			TrainDAO trainDAO = daoFactory.getTrainDAO();
			Train train = trainDAO.findTrain(exampleTrain);
			if (train != null) {
				request.setAttribute("train", train);
			}

			RouteDAO routeDAO = daoFactory.getRouteDAO();
			Route route = routeDAO.findRoute(routeID);
			if (route != null) {
				request.setAttribute("route", route);
			}

			PriceDAO priceDAO = daoFactory.getPriceDAO();
			Price price = priceDAO.findPrice(routeID);
			if (price != null) {
				request.setAttribute("price", price);
			}
		}
	}

}
