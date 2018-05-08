/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.poland.jms;


import com.google.maps.model.LatLng;
import com.google.gson.Gson;

import eu.poland.domain.LocationTimed;
import javax.jms.JMSException;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

/**
 *
 * @author Bart
 */
public class ProducerTest {

    private Producer msgQueueSender;
    private String activeMQIp = "192.168.25.14";

    @Before
    public void setup() {
        msgQueueSender = new Producer("tcp://localhost:61616", "admin", "admin");
    }

    @After
    public void cleanup() throws JMSException {
        msgQueueSender.close();
    }

//    @Ignore
    @Test
    public void TestSendMsg() throws JMSException {
        msgQueueSender.setup("TestQueue");
        msgQueueSender.sendMessage("Test");
    }

    @Test
    public void TestSendObject() throws JMSException {
        Gson gson = new Gson();
        LocationTimed location = new LocationTimed(new LatLng(1.1, 2.2));
        String msg = gson.toJson(location);
        
        msgQueueSender.setup("TestQueue");
        msgQueueSender.sendMessage(msg);
    }
}
