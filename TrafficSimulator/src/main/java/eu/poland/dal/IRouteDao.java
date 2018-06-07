package eu.poland.dal;

import com.google.maps.model.DirectionsRoute;

/**
 *
 * @author Robin
 */
public interface IRouteDao {
    public DirectionsRoute getRoute(Long id);
    public DirectionsRoute getRandomRoute();
}
