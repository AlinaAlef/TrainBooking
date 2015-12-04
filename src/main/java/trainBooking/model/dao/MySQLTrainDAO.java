package trainBooking.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;
import trainBooking.model.entity.Train;
import trainBooking.model.entity.User;

public class MySQLTrainDAO implements TrainDAO {
	static Logger log = Logger.getLogger(MySQLTrainDAO.class.getName());

	@Override
	public int insertTrain(Train train) {
		Connection connection = null;
		PreparedStatement statement = null;

		try {

			// Get Connection and create PreparedStatement.
			connection = ConnectionPool.getConnection();

			statement = connection.prepareStatement("INSERT INTO train "
					+ "(id, date, suiteReserved, coupleReserved,berthReserved) values "
					+ "(?, ?, ?, ?, ?)");
			statement.setInt(1, train.getId());
			statement.setDate(2, train.getDate());
			statement.setInt(3, train.getSuiteReserved());
			statement.setInt(4, train.getCoupeReserved());
			statement.setInt(5, train.getBerthReserved());

			return statement.executeUpdate();
		} catch (SQLException e) {
			return -1;
		} finally {

			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException e) {
					log.error(e.getMessage() + " MySQLUserDAO.insertTrain() cannot close statement");
				}
			}

			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					log.error(e.getMessage() + " MySQLUserDAO.insertTrain() cannot close onnection");
				}
			}
		}
	}

	@Override
	public Train findTrain(Train train) {
		Connection connection = null;
		ResultSet resultSet = null;
		PreparedStatement statement = null;

		try {

			// Get Connection and create PreparedStatement.
			connection = ConnectionPool.getConnection();

			statement = connection.prepareStatement(
					"SELECT * FROM trains WHERE routeID = ?");
			
			statement.setString(1, train.getRouteID());
//			statement.setDate(2, train.getDate());

			resultSet = statement.executeQuery();
			if (resultSet.first()) {
				Train resTrain = new Train();
				
				// Retrieve information from the result set.
				resTrain.setId(resultSet.getInt("id"));
				resTrain.setRouteID(resultSet.getString("routeID"));
				resTrain.setDate(resultSet.getDate("date"));
				resTrain.setSuiteReserved(resultSet.getInt("suiteReserved"));
				resTrain.setCoupeReserved(resultSet.getInt("coupeReserved"));
				resTrain.setBerthReserved(resultSet.getInt("berthReserved"));
				return resTrain;
			} else {
				return null;
			}
		} catch (SQLException e) {
			log.error(e.getMessage() + " MySQLTrainDAO.findTrain() error");
		} finally {

			if (resultSet != null) {
				try {
					resultSet.close();
				} catch (SQLException e) {
					log.error(e.getMessage() + " MySQLTrainDAO.findTrain() cannot close resultSet");
				}
			}

			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException e) {
					log.error(e.getMessage() + " MySQLTrainDAO.findTrain() cannot close statement");
				}
			}

			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					log.error(e.getMessage() + " MySQLTrainDAO.findTrain() cannot close connection");
				}
			}
		}

		return null;
	}

}
