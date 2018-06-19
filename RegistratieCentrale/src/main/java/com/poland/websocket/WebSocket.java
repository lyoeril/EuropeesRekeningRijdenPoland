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
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/policeSocket/{uuid}")
public class WebSocket {

    private RegistrationService registrationService;

    @Inject
    public WebSocket(RegistrationService rs) {
        registrationService = rs;
    }

    @OnOpen
    public void onOpen(Session session, @PathParam("uuid") String authenticationCode) {
        if (SessionLister.getInstance().getTrackerMapSessions().get(authenticationCode) == null) {
            SessionLister.getInstance().getTrackerMapSessions().put(authenticationCode, new ArrayList<>());
            SessionLister.getInstance().getTrackerMapSessions().get(authenticationCode).add(session);
        } else {
            SessionLister.getInstance().getTrackerMapSessions().get(authenticationCode).add(session);
        }
    }

    @OnClose
    public void onClose(Session session, @PathParam("uuid") String authenticationCode) {
        if (SessionLister.getInstance().getTrackerMapSessions().get(authenticationCode) != null) {
            SessionLister.getInstance().getTrackerMapSessions().get(authenticationCode).remove(session);
        } else {

        }
    }
    
    @OnMessage
    public void onMessage(String message, Session session, @PathParam("uuid") String authenticationCode) {
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
