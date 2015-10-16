package app;

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

        //get 3 random employees
        List<Employee> employees = new ArrayList<Employee>();

        //send map containing pictureid and username to UI
        Map<String, String> resource = new HashMap<String, String>();
        for (Employee e : employees) {
            resource.put(e.getName(), e.getPicture());
        }

        return resource;
    }

}
