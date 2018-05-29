package eu.poland.service;

import com.google.maps.model.LatLng;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;

/**
 *
 * @author Robin
 */
public class PolygonService {

    private final GeometryFactory gf = new GeometryFactory();
    private Geometry geom;
    private Coordinate topLeft;
    private Coordinate bottomRight;

    public PolygonService(Geometry geom) {
        this.geom = geom;
        // TODO robkor: What is a multipolygon and why is it in the geometrycollection?
        Coordinate[] c = geom.getEnvelope().getCoordinates();
        this.topLeft = c[0];
        this.bottomRight = c[2];
    }

    public LatLng getRandomPoint() {
        double width = bottomRight.x - topLeft.x;
        double height = bottomRight.y - topLeft.y;
        Point p = null;
        while (true) {
            double x = topLeft.x + (width * Math.random());
            double y = topLeft.y + (height * Math.random());
            p = gf.createPoint(new Coordinate(x, y));
            if (geom.covers(p)) {
                break;
            }
        }
        return new LatLng(p.getX(), p.getY());
    }
}
