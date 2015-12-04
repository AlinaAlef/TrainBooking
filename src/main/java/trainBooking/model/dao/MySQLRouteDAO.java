package trainBooking.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;

import org.apache.log4j.Logger;
import trainBooking.model.entity.Route;

public class MySQLRouteDAO implements RouteDAO {
	static Logger log = Logger.getLogger(MySQLRouteDAO.class.getName());

	@Override
	public Iterable<Route> findRoutes(Route route) {
		Connection connection = null;
		ResultSet resultSet = null;
		PreparedStatement statement = null;
		ArrayList<Route> routes = null;

		try {

			// Get Connection and create PreparedStatement.
			connection = ConnectionPool.getConnection();

			statement = connection.prepareStatement(
					"SELECT * FROM routes WHERE departureStation = ?"
							+ " AND destinationStation = ? AND departureTime >= ?");

			statement.setString(1, route.getDepartureStation());
			statement.setString(2, route.getDestinationStation());
			statement.setTime(3, route.getDepartureTime());


			resultSet = statement.executeQuery();

			while (resultSet.next()) {
				if (routes == null) {
					routes = new ArrayList<Route>();
				}
				Route resRoute = new Route();

				// Retrieve information from the result set.
				resRoute.setId(resultSet.getString("id"));
				resRoute.setDepartureStation(resultSet.getString("departureStation"));
				resRoute.setDestinationStation(resultSet.getString("destinationStation"));
				resRoute.setDepartureTime(Time.valueOf(resultSet.getString("departureTime")));
				resRoute.setDestinationTime(Time.valueOf(resultSet.getString("destinationTime")));
				resRoute.setSuitPlaces(resultSet.getInt("suitePlaces"));
				resRoute.setCoupePlaces(resultSet.getInt("coupePlaces"));
				resRoute.setBerthPlaces(resultSet.getInt("berthPlaces"));

				routes.add(resRoute);
			}

			return routes;
		} catch (SQLException e) {
			log.error(e.getMessage() + " MySQLRouteDAO.findRoutes() error");
		} finally {

			if (resultSet != null) {
				try {
					resultSet.close();
				} catch (SQLException e) {
					log.error(e.getMessage() + " MySQLRouteDAO.findPrice() cannot close resultSet");
				}
			}

			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException e) {
					log.error(e.getMessage() + " MySQLRouteDAO.findPrice() cannot close statement");
				}
			}

			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					log.error(e.getMessage() + " MySQLRouteDAO.findPrice() cannot close connection");
				}
			}
		}

		return null;
	}

	@Override
	public Route findRoute(String id) {
		Connection connection = null;
		ResultSet resultSet = null;
		PreparedStatement statement = null;
		Route resRoute = null;

		try {

			// Get Connection and create PreparedStatement.
			connection = ConnectionPool.getConnection();

			statement = connection.prepareStatement(
					"SELECT * FROM routes WHERE id = ?");

			statement.setString(1, id);


			resultSet = statement.executeQuery();

			if (resultSet.first()) {

				resRoute = new Route();

				// Retrieve information from the result set.
				resRoute.setId(resultSet.getString("id"));
				resRoute.setDepartureStation(resultSet.getString("departureStation"));
				resRoute.setDestinationStation(resultSet.getString("destinationStation"));
				resRoute.setDepartureTime(Time.valueOf(resultSet.getString("departureTime")));
				resRoute.setDestinationTime(Time.valueOf(resultSet.getString("destinationTime")));
				resRoute.setSuitPlaces(resultSet.getInt("suitePlaces"));
				resRoute.setCoupePlaces(resultSet.getInt("coupePlaces"));
				resRoute.setBerthPlaces(resultSet.getInt("berthPlaces"));
			}

			return resRoute;
		} catch (SQLException e) {
			log.error(e.getMessage() + " MySQLRouteDAO.findRoutes() error");
		} finally {

			if (resultSet != null) {
				try {
					resultSet.close();
				} catch (SQLException e) {
					log.error(e.getMessage() + " MySQLRouteDAO.findPrice() cannot close resultSet");
				}
			}

			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException e) {
					log.error(e.getMessage() + " MySQLRouteDAO.findPrice() cannot close statement");
				}
			}

			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					log.error(e.getMessage() + " MySQLRouteDAO.findPrice() cannot close connection");
				}
			}
		}

		return null;
	}
}
