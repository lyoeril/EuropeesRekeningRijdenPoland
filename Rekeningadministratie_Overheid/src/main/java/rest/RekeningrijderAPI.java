/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import domain.Invoice;
import domain.Location;
import domain.Rekeningrijder;
import domain.Ride;
import domain.User;
import domain.Vehicle;
import dto.DTO_Invoice;
import dto.DTO_Location;
import dto.DTO_Rekeningrijder;
import dto.DTO_Ride;
import dto.DTO_Vehicle;
import enums.InvoiceStatus;
import enums.VehicleType;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.TimeZone;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import services.InvoiceCalculationService;
import services.InvoiceService;
import services.RegistrationService;
import services.RideService;
import services.UserService;

/**
 *
 * @author Laurent
 */
@Path("rekeningrijder")
@JWTTokenNeeded
@Stateless
public class RekeningrijderAPI {

    @Inject
    private InvoiceService invoiceService;

    @Inject
    private RegistrationService registrationService;

    @Inject
    private UserService userService;
    
    @Inject
    private InvoiceCalculationService ics;

    @Inject
    private RideService rideService;

    //TODO
    //Still need to fix User/Kwetteraar difference; Essential to just need 1 databasecall
    //TODO
    //FIX DATABASECALL for findByUsername();
    @GET
    @Produces(APPLICATION_JSON)
    public Response getRekeningrijder(@Context HttpHeaders headers) {
        String token = headers.getHeaderString(HttpHeaders.AUTHORIZATION).substring("Bearer".length()).trim();

        System.out.println("TOKIETOKIE: " + token);
        String username = this.getUsernameFromToken(token);
        if (username != null) {
            User u = userService.findByUsername(username).get(0);
            Rekeningrijder r = registrationService.findRekeningrijderById(u.getId());
            System.out.println("Rekeningrijder ->> " + r);
            DTO_Rekeningrijder dto = new DTO_Rekeningrijder(r);
            return Response.accepted(dto).build();
        }

        return Response.accepted(token).build();
    }

    @PUT
    @Path("update")
    @Produces(APPLICATION_JSON)
    public Response updateRekeningrijder(
            @Context HttpHeaders headers,
            @FormParam("email") String email,
            @FormParam("address") String address,
            @FormParam("password") String password) {
        String token = headers.getHeaderString(HttpHeaders.AUTHORIZATION).substring("Bearer".length()).trim();
        Rekeningrijder rekeningrijder = this.getRekeningrijderFromToken(token);

        if (rekeningrijder != null) {
            rekeningrijder.setEmail(email);
            rekeningrijder.setAddress(address);
            rekeningrijder.setPassword(password);
            registrationService.updateRekeningrijder(rekeningrijder);
            return Response.accepted(new DTO_Rekeningrijder(rekeningrijder)).build();
        }
        return Response.status(Status.NOT_FOUND).build();
    }

    @POST
    @Produces(APPLICATION_JSON)
    @Path("invoices/new")
    public Response newInvoice(@Context HttpHeaders headers) {
        String token = headers.getHeaderString(HttpHeaders.AUTHORIZATION).substring("Bearer".length()).trim();
        Rekeningrijder rekeningrijder = this.getRekeningrijderFromToken(token);

        Invoice i = new Invoice(1, 20.00, 2017, 1, rekeningrijder);
        if (rekeningrijder == null) {
            return Response.status(Status.NOT_FOUND).build();
        }
        rekeningrijder.getInvoices().add(i);
        registrationService.updateRekeningrijder(rekeningrijder);
        invoiceService.addInvoice(i);
        return Response.accepted().build();
    }

    @GET
    @Produces(APPLICATION_JSON)
    @Path("invoices")
    public Response getInvoices(@Context HttpHeaders headers) {
        String token = headers.getHeaderString(HttpHeaders.AUTHORIZATION).substring("Bearer".length()).trim();
        Rekeningrijder rekeningrijder = this.getRekeningrijderFromToken(token);
        if (rekeningrijder == null) {
            return Response.status(Status.NOT_FOUND).build();
        }

        List<Invoice> invoices = rekeningrijder.getInvoices();
        if (invoices != null) {
            List<DTO_Invoice> dto_invoices = new ArrayList<>();
            for (Invoice i : invoices) {
                dto_invoices.add(new DTO_Invoice(i));
            }

            return Response.accepted(dto_invoices).build();
        }
        return Response.status(Status.NOT_FOUND).build();
    }

    @GET
    @Produces(APPLICATION_JSON)
    @Path("invoices/{id}")
    public Response getInvoiceById(@PathParam("id") long id) {
        try {
            Invoice i = invoiceService.findInvoiceById(id);
            if (i != null) {
                return Response.ok(i).build();
            }
        } catch (Exception e) {
            return Response.status(Status.BAD_REQUEST).build();
        }
        return Response.status(Status.BAD_REQUEST).build();
    }

    @GET
    @Produces(APPLICATION_JSON)
    @Path("invoices/{year}/{month}")
    public Response getInvoicebyDate(
            @Context HttpHeaders headers,
            @PathParam("year") int year,
            @PathParam("month") int month) {
        String token = headers.getHeaderString(HttpHeaders.AUTHORIZATION).substring("Bearer".length()).trim();
        Rekeningrijder rekeningrijder = this.getRekeningrijderFromToken(token);

        Invoice toReturn = invoiceService.findInvoiceByRekeningrijderMonth(rekeningrijder, year, month);
        if (toReturn != null) {
            return Response.accepted(new DTO_Invoice(toReturn)).build();
        }
        return Response.status(Status.NOT_FOUND).build();
    }

    //TODO
    @PUT
    @Path("invoices/{year}/{month}/")
    public Response payInvoice(
            @Context HttpHeaders headers,
            @PathParam("year") int year,
            @PathParam("month") int month) {
        String token = headers.getHeaderString(HttpHeaders.AUTHORIZATION).substring("Bearer".length()).trim();
        Rekeningrijder r = this.getRekeningrijderFromToken(token);
        Invoice toReturn = invoiceService.findInvoiceByRekeningrijderMonth(r, year, month);
        if (toReturn != null) {
            toReturn.setStatus(InvoiceStatus.PAID);
            invoiceService.updateInvoice(toReturn);
            return Response.accepted().build();
        }
        return Response.status(Status.BAD_REQUEST).build();
    }

    @GET
    @Produces(APPLICATION_JSON)
    @Path("rides/vehicle/{id}/date/{year}/{month}")
    public Response getRidesOfVehicle(
            @Context HttpHeaders headers,
            @PathParam("id") long id,
            @PathParam("year") int year,
            @PathParam("month") int month) {
        List<Ride> rides = rideService.getRides(id, year, month);
        List<DTO_Ride> dtoRides = new ArrayList<>();
        for (Ride r : rides) {
            List<DTO_Location> dtoLocations = new ArrayList<>();
            for(Location l : r.getLocations()) {
                dtoLocations.add(new DTO_Location(l.getDate().toString(), l.getId(), l.getLatitude(), l.getLongitude()));
            }
            dtoRides.add(new DTO_Ride(r.getId(), r.getStartDate().toString(), r.getEndDate().toString(), dtoLocations));
            return Response.accepted(dtoRides).build();
        }
        return Response.status(Status.BAD_REQUEST).build();
    }

    @GET
    @Produces(APPLICATION_JSON)
    @Path("/cars")

    public Response getCars(@Context HttpHeaders headers) {
        String token = headers.getHeaderString(HttpHeaders.AUTHORIZATION).substring("Bearer".length()).trim();

        Rekeningrijder r = this.getRekeningrijderFromToken(token);

        if (r != null) {
            List<Vehicle> vehicles = r.getOwnedVehicles();
            List<DTO_Vehicle> vehicleNames = new ArrayList<>();
            for (Vehicle v : vehicles) {
                DTO_Vehicle vehicle = new DTO_Vehicle(v);
                vehicleNames.add(vehicle);
            }
            return Response.ok(vehicleNames).build();
        }
        return Response.status(Status.NOT_FOUND).build();
    }

    @GET
    @Produces(APPLICATION_JSON)
    @Path("/cars/{carId}")
    public Response getCarById(
            @Context HttpHeaders headers,
            @PathParam("carId") long carId) {
        if (carId != 0L) {
            try {
                String token = headers.getHeaderString(HttpHeaders.AUTHORIZATION).substring("Bearer".length()).trim();
                System.out.println("TOKIETOKIE: " + token);
                String username = this.getUsernameFromToken(token);

                List<Vehicle> vehicles = this.getRekeningrijderFromUsername(username).getOwnedVehicles();
                if (vehicles != null) {
                    for (Vehicle vehicle : vehicles) {
                        if (vehicle.getId() == carId) {
                            return Response.ok(new DTO_Vehicle(vehicle)).build();
                        }
                    }
                }

            } catch (Exception e) {
                return Response.status(Status.NOT_FOUND).build();
            }
        }
        return Response.status(Status.NOT_FOUND).build();
    }

    @POST
    @Produces(APPLICATION_JSON)
    @Path("cars/newcar")
    public Response newCar(
            @Context HttpHeaders headers,
            @FormParam("vehicletype") VehicleType vehicleType,
            @FormParam("licensePlate") String licensePlate) {

        String token = headers.getHeaderString(HttpHeaders.AUTHORIZATION).substring("Bearer".length()).trim();
        Rekeningrijder r = this.getRekeningrijderFromToken(token);
        if (r == null) {
            return Response.status(Status.UNAUTHORIZED).build();
        }
        Vehicle v = new Vehicle(vehicleType, licensePlate);
        v.getOwnersHistory().add(r);
        r.getOwnedVehicles().add(v);
        registrationService.updateRekeningrijder(r);
        return Response.accepted().build();
    }

    @PUT
    @Produces(APPLICATION_JSON)
    @Path("cars/{carId}/update")
    public Response updateCar(
            @Context HttpHeaders headers,
            @FormParam("vehicletype") VehicleType vehicleType,
            @FormParam("licensePlate") String licensePlate) {

        String token = headers.getHeaderString(HttpHeaders.AUTHORIZATION).substring("Bearer".length()).trim();
        Rekeningrijder r = this.getRekeningrijderFromToken(token);
        if (r == null) {
            return Response.status(Status.UNAUTHORIZED).build();
        }
        List<Vehicle> vehicles = r.getOwnedVehicles();
        Vehicle old = new Vehicle(vehicleType, licensePlate);
        for (Vehicle v : vehicles) {
            if (v.getLicensePlate() == licensePlate) {
                r.getOwnedVehicles().remove(v);
                old.setVehicleType(vehicleType);
                r.getOwnedVehicles().add(v);
                registrationService.updateRekeningrijder(r);
            }
        }
        return Response.accepted().build();
    }

    @DELETE
    @Produces(APPLICATION_JSON)
    @Path("cars/{carId}")
    public Response deleteCarFromUser(
            @Context HttpHeaders headers,
            @PathParam("carId") long carId) {
        String token = headers.getHeaderString(HttpHeaders.AUTHORIZATION).substring("Bearer".length()).trim();
        Rekeningrijder r = this.getRekeningrijderFromToken(token);
        if (r == null) {
            return Response.status(Status.UNAUTHORIZED).build();
        }
        for (Vehicle v : r.getOwnedVehicles()) {
            if (v.getId() == carId) {
                r.getOwnedVehicles().remove(v);
                registrationService.updateRekeningrijder(r);
                return Response.accepted().build();
            }
        }
        return Response.status(Status.FORBIDDEN).build();
    }
    
    @GET
    @Produces(APPLICATION_JSON)
    @Path("invoicecalc")
    public Response calcInvoice(){
        Calendar c1 = new GregorianCalendar(2018, 01, 01);
        Location l1 = new Location(1L, c1, 52.40533, 19.27417);
        Location l2 = new Location(2L, c1, 52.23478, 19.17059);
        
        List<Ride> rides = new ArrayList<Ride>();
        Ride r = new Ride(1L, c1, c1);
        List<Location> locations = new ArrayList<>();
        locations.add(l1);
        locations.add(l2);
        r.setLocations(locations);
        rides.add(r);
        
        Invoice returnable = ics.calculateInvoice(3L, 1, 2018, new Rekeningrijder(), rides, VehicleType.VAN);
        return Response.accepted(returnable).build();
    }

    private String getUsernameFromToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC512("supersecret");
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer("mario")
                    .build();
            DecodedJWT jwt = verifier.verify(token);
            final String username = jwt.getSubject();
            return username;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private Rekeningrijder getRekeningrijderFromUsername(String username) {
        User u = userService.findByUsername(username).get(0);

        if (u == null) {
            return null;
        }

        Rekeningrijder rekeningrijder = registrationService.findRekeningrijderById(u.getId());
        return rekeningrijder;
    }

    private Rekeningrijder getRekeningrijderFromToken(String token) {
        String username = this.getUsernameFromToken(token);
        return this.getRekeningrijderFromUsername(username);
    }
}
