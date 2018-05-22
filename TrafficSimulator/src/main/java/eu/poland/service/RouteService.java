package eu.poland.service;

import com.google.maps.model.DirectionsRoute;
import eu.poland.dal.*;

/**
 *
 * @author Robin
 */
public class RouteService {
    
    private IRouteDao routeDao;
    
    public RouteService() {
        this.routeDao = new RouteDaoMem();
    }
    
    public DirectionsRoute getRandomRoute() {
        // TODO robkor: make this random
        return routeDao.getRoute(0L);
    }
}
