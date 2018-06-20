package com.poland.websocket;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.websocket.Session;

public class SessionLister {

    private static SessionLister sessionLister = null;

    private static Map<String, List<Session>> trackerMapSessions= new HashMap<>();;

    protected SessionLister() {
    }

    public static SessionLister getInstance() {
        if (sessionLister == null) {
            sessionLister = new SessionLister();
        }
        return sessionLister;
    }

    public Map<String, List<Session>> getTrackerMapSessions() {
        return trackerMapSessions;
    }
}
