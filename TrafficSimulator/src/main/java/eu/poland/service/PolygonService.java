package eu.poland.service;

import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Polygon;

/**
 *
 * @author Robin
 */
public class PolygonService {
    
    private final GeometryFactory gf = new GeometryFactory();
    private Polygon polygon;
    
    public PolygonService(Coordinate[] coordinates) {
        this.polygon = gf.createPolygon(gf.createLinearRing(coordinates));
    }
}
