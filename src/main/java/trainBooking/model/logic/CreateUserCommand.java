package trainBooking.model.logic;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import trainBooking.model.dao.UserDAO;
import trainBooking.model.entity.User;

public class CreateUserCommand extends Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		User user = new User();
		
		user.setEmail(request.getParameter("email"));
		user.setPassword(request.getParameter("password"));
		user.setName(request.getParameter("name"));
		user.setSurname(request.getParameter("surname"));
		user.setPhone(request.getParameter("phone"));
		
		// Pass  the user to UserDAO.
		UserDAO userDAO = daoFactory.getUserDAO();
		
		int result = userDAO.insertUser(user);
		
		if (result == -1) {
			
			// The user was not inserted.
			request.setAttribute("userNotCreated", true);
		}
	}

}
