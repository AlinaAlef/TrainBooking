package trainBooking.model.dao;

import org.apache.log4j.Logger;
import trainBooking.model.entity.Price;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class MySQLPriceDAO implements PriceDAO {
    static Logger log = Logger.getLogger(MySQLPriceDAO.class.getName());

    @Override
    public Price findPrice(String routeID) {
        Connection connection = null;
        ResultSet resultSet = null;
        PreparedStatement statement = null;
        Price price = null;

        try {

            // Get Connection and create PreparedStatement.
            connection = ConnectionPool.getConnection();

            statement = connection.prepareStatement(
                    "SELECT * FROM prices WHERE routeID = ?");

            statement.setString(1, routeID);


            resultSet = statement.executeQuery();

            if (resultSet.first()) {

                price = new Price();

                // Retrieve information from the result set.
                price.setSuitePrice(resultSet.getInt("suitePrice"));
                price.setCoupePrice(resultSet.getInt("coupePrice"));
                price.setBerthPrice(resultSet.getInt("berthPrice"));

            }
            return price;
        } catch (SQLException e) {
            log.error(e.getMessage() + " MySQLRouteDAO.findPrice() error");
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
