package com.poland.dto.entities;

import com.poland.entities.Location;
import com.poland.entities.Ride;
import java.util.List;

public class DTOConverter {

    private DTOConverter() {
    }

    public static void toRideDTOList(List<Ride> rideList, List<RideDTO> rideTargetList) {
        if (rideList != null) {
            rideList.forEach(k -> {
                RideDTO rdto = new RideDTO();
                rdto.fromRide(k);
                rideTargetList.add(rdto);
            });
        }
    }
    
    
    public static void toLocationDTOList(List<Location> locationList, List<LocationDTO> locationTargetList) {
        if (locationList != null) {
            locationList.forEach(l -> {
                LocationDTO ldto = new LocationDTO();
                ldto.fromLocation(l);
                locationTargetList.add(ldto);
            });
        }
    }
}
