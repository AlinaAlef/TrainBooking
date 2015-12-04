package trainBooking.model.logic;

import trainBooking.model.dao.TrainDAO;
import trainBooking.model.dao.UserDAO;
import trainBooking.model.entity.Train;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Date;

public class CreateTrainCommand extends Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		Train train = new Train();

		train.setId(Integer.parseInt(request.getParameter("id")));
		train.setDate(Date.valueOf(request.getParameter("date")));
		train.setSuiteReserved(Integer.parseInt(request.getParameter("suite")));
		train.setCoupeReserved(Integer.parseInt(request.getParameter("couple")));
		train.setBerthReserved(Integer.parseInt(request.getParameter("couple")));

		// Pass  the train to TrainDAO.
		TrainDAO trainDAO = daoFactory.getTrainDAO();


		
		int result = trainDAO.insertTrain(train);
		
		if (result == -1) {
			
			// The user was not inserted.
			request.setAttribute("trainNotCreated", true);
		}
	}

}
