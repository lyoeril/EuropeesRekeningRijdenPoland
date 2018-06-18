package com.poland.dto.entities;

import com.poland.entities.Location;
import com.poland.entities.Ride;
import com.poland.entities.Vehicle;
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

    public static void toBasicLocationDTOList(List<Location> locationList, List<BasicLocationDTO> basiclocationTargetList) {
        if (locationList != null) {
            locationList.forEach(l -> {
                BasicLocationDTO ldto = new BasicLocationDTO();
                ldto.fromLocation(l);
                basiclocationTargetList.add(ldto);
            });
        }
    }

    public static void toVehicleDTOList(List<Vehicle> vehicleList, List<VehicleDTO> vehicleTargetList) {
        if (vehicleList != null) {
            vehicleList.forEach(v -> {
                VehicleDTO vdto = new VehicleDTO();
                vdto.fromVehicle(v);
                vehicleTargetList.add(vdto);
            });
        }
    }   
    
    public static void toPoliceVehicleDTOList(List<Vehicle> vehicleList, List<PoliceVehicleDTO> policeVehicleTargetList) {
        if (vehicleList != null) {
            vehicleList.forEach(v -> {
                PoliceVehicleDTO vdto = new PoliceVehicleDTO();
                vdto.fromVehicleLocation(v.getAuthorisationCode(),v.getLocation());
                policeVehicleTargetList.add(vdto);
            });
        }
    }
}
