/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.poland.websocket;

/**
 *
 * @author PC-YOERI
 */
import com.poland.dto.entities.PoliceVehicleDTO;
import com.poland.entities.Vehicle;
import com.poland.service.RegistrationService;
import java.util.ArrayList;
import javax.inject.Inject;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import javax.ws.rs.PathParam;

@ServerEndpoint("/policeSocket/{uuid}")
public class WebSocket {

    private RegistrationService registrationService;

    @Inject
    public WebSocket(RegistrationService rs) {
        registrationService = rs;
    }

    @OnOpen
    public void onOpen(Session session, @PathParam("uuid") String authenticationCode) {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                boolean run = true;
                while (run) {
                    try {
                        Thread.sleep((long) 10000);
                        Vehicle v = registrationService.getVehicleService().getVehicleByAuthorisationCode(authenticationCode);
                        if (v != null && v.isStolen()) {
                            if (v.isForeignCar()) {
//EUROPE THINGS UPDATE LOCATION FROM EXTERNAL APIs
                            } else {
                                PoliceVehicleDTO vehicleTarget = new PoliceVehicleDTO();
                                vehicleTarget.fromVehicleLocation(v.getAuthorisationCode(), v.getLocation());
                                session.getAsyncRemote().sendObject(vehicleTarget);
                            }
                        }else{
                            run = false;
                            Thread.currentThread().interrupt();
                        }
                    } catch (InterruptedException e) {
                        run = false;
                        Thread.currentThread().interrupt();
                    }
                }
            }
        });
        t.start();
        SessionLister.getInstance().getSessionMapRunnable().put(session.getId(), t);
    }

    @OnClose
    public void onClose(Session session) {
        SessionLister.getInstance().getSessionMapRunnable().get(session).interrupt();
        SessionLister.getInstance().getSessionMapRunnable().remove(session);
    }

    @OnMessage
    public void onMessage(String message, Session session) {
//        if (SessionLister.getInstance().getSessionMapVehicles().get(session.getId()).contains(message)) {
//            SessionLister.getInstance().getSessionMapVehicles().get(session.getId()).remove(message);
//        } else {
//            Vehicle v = registrationService.getVehicleService().getVehicleByAuthorisationCode(message);
//            if (v != null && v.isStolen()) {
//                SessionLister.getInstance().getSessionMapVehicles().get(session.getId()).add(v.getAuthorisationCode());
//            }
//        }
    }
}
