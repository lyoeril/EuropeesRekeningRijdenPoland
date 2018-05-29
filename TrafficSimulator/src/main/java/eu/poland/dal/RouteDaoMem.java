package eu.poland.dal;

import com.google.maps.DirectionsApi;
import com.google.maps.GeoApiContext;
import com.google.maps.errors.ApiException;
import com.google.maps.model.DirectionsResult;
import com.google.maps.model.DirectionsRoute;
import com.google.maps.model.LatLng;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Robin
 */
public class RouteDaoMem implements IRouteDao {
    
    private Properties props;
    private Map<Long, DirectionsRoute> routes;

    private void initProps() {
        System.out.println("Loading properties file. . .");

        try {
            props = new Properties();
            InputStream in = ClassLoader.class.getResourceAsStream("/config.properties");
            props.load(in);
        } catch (IOException ioe) {
            //Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Please provide a properties file.");
            System.exit(1);
        }
        if (!props.containsKey("key")) {
            System.out.println("Please ensure that the properties file contains an API key.");
            System.exit(1);
        }
    }
    
    public RouteDaoMem() {
        initProps();
        this.routes = new HashMap();
        DirectionsRoute route = getRoute(new LatLng(52.0828121, 17.0008908), new LatLng(51.8774911, 17.0028028));
        this.routes.put(0L, route);
    }

    @Override
    public DirectionsRoute getRoute(Long id) {
        return routes.get(id);
    }
    
    private DirectionsRoute getRoute(LatLng origin, LatLng destination) {
        // API key RDK: ERP2018
        GeoApiContext geoContext = new GeoApiContext.Builder()
                .apiKey(props.getProperty("key"))
                .build();
        try {
            DirectionsResult result = DirectionsApi.newRequest(geoContext)
                    .origin(origin)
                    .destination(destination)
                    .await();
            return result.routes[0];
        } catch (ApiException ex) {
            Logger.getLogger(RouteDaoMem.class
                    .getName()).log(Level.SEVERE, null, ex);
            return null;

        } catch (InterruptedException ex) {
            Logger.getLogger(RouteDaoMem.class
                    .getName()).log(Level.SEVERE, null, ex);
            return null;

        } catch (IOException ex) {
            Logger.getLogger(RouteDaoMem.class
                    .getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
}
