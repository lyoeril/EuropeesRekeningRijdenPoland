package com.poland.websocket;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SessionLister {

    private static SessionLister sessionLister = null;

    private static Map<String, List<String>> sessionMapVehicles= new HashMap<>();;
    private static Map<String, Thread> sessionMapRunnable= new HashMap<>();;

    protected SessionLister() {
    }

    public static SessionLister getInstance() {
        if (sessionLister == null) {
            sessionLister = new SessionLister();
        }
        return sessionLister;
    }

    public Map<String, List<String>> getSessionMapVehicles() {
        return sessionMapVehicles;
    }
    public Map<String, Thread> getSessionMapRunnable() {
        return sessionMapRunnable;
    }
}
