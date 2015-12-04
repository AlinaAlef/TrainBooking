package trainBooking.model.dao;

import trainBooking.model.entity.Price;

/**
 * Created by alef on 02.12.15.
 */
public interface PriceDAO {

    /**
     * Find price for determinate route
     *
     * @param routeID is id of determinate route
     * @return found price
     */
    Price findPrice(String routeID);
}
