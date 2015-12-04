package trainBooking.model.logic;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import trainBooking.model.dao.DAOFactory;

import java.text.ParseException;

public abstract class Command {
	protected static DAOFactory daoFactory;
	
	public static void setDAOFactory(DAOFactory factory) {
		daoFactory = factory;
	}
	
	public abstract void execute(HttpServletRequest request,
			                     HttpServletResponse response);
}