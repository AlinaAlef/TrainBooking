package trainBooking.model.dao;

import trainBooking.model.entity.User;

public interface UserDAO {
	int insertUser(User user);           // New user is registered.
	User findUser(User user);            // User wants to login.
	boolean updateUser(User user);       // Administrator activates/blocks user.
	Iterable<User> findUnconfirmed();    // Returns unconfirmed user records.
	int activateBatch(Iterable<User> users); // Activates a collection of users.
	int deleteBatch(Iterable<User> users); // Deletes a collection of users.
}
