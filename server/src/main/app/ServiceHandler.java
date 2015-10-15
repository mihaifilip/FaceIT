package app;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Mihai on 10/14/2015.
 */
public class ServiceHandler {

    public static Map<String, IService> services = new HashMap<>();

    public synchronized void addService(IService service) {
        services.put(service.getName(), service);
    }

}
