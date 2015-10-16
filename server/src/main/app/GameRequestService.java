package app;

import database.EmployeeDatabase;
import util.GameRequestMessage;
import util.bean.Employee;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by mihfilip on 16/10/2015.
 */
public class GameRequestService {

    public static Map<String, String> handleGameRequest(GameRequestMessage message) {

        Map<String, String> results = new HashMap<>();
        //get 3 random employees
        EmployeeDatabase ed = new EmployeeDatabase();
        try {
            results = ed.getRandomPicturesForOfficeId(message.getCity(), message.getCountry());
            return results;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

}
