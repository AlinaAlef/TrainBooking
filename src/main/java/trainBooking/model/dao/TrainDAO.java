package trainBooking.model.dao;

import trainBooking.model.entity.Train;
import trainBooking.model.entity.User;

public interface TrainDAO {
	int insertTrain(Train train);
	Train findTrain(Train train);
}