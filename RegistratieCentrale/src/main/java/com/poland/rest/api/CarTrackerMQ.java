package com.poland.rest.api;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import com.poland.service.RegistrationService;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.inject.Inject;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import org.json.JSONObject;

/**
 *
 * @author PC-YOERI
 */
@MessageDriven(name = "testmdb", activationConfig = {
    @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "jms/TrafficQueue")
    ,
    @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue")
    ,
    @ActivationConfigProperty(propertyName = "destination", propertyValue = "TrafficQueue")
    ,
    @ActivationConfigProperty(propertyName = "resourceAdapter", propertyValue = "activemq-rar-5.15.3")
    ,
    @ActivationConfigProperty(propertyName = "maxSessions", propertyValue = "1000")
    ,
    @ActivationConfigProperty(propertyName = "maxMessagesPerSessions", propertyValue = "10000")})
public class CarTrackerMQ implements MessageListener {

    RegistrationService registrationService;

    SimpleDateFormat sdf;

    public CarTrackerMQ() {
    }

    @Inject
    public CarTrackerMQ(RegistrationService registrationService) {
        this.registrationService = registrationService;
        this.sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    }

    @Override
    public void onMessage(Message msg) {
        try {
            if (msg instanceof TextMessage) {
                TextMessage textMessage = (TextMessage) msg;

                JSONObject obj = new JSONObject(textMessage.getText());

                JSONObject dateObj = obj.getJSONObject("date");

                StringBuilder sb = new StringBuilder();

                sb.append(dateObj.getLong("year"));
                sb.append("-");
                sb.append(dateObj.getLong("month"));
                sb.append("-");
                sb.append(dateObj.getLong("dayOfMonth"));
                sb.append(" ");
                sb.append(dateObj.getLong("hourOfDay"));
                sb.append(":");
                sb.append(dateObj.getLong("minute"));
                sb.append(":");
                sb.append(dateObj.getLong("second"));

                Date date = sdf.parse(sb.toString());

                registrationService.registerLocationSimple(date,
                        obj.getJSONObject("location").getDouble("lat"),
                        obj.getJSONObject("location").getDouble("lng"),
                        obj.getString("trackerId"));
            }
        } catch (JMSException | ParseException ex) {
            Logger.getLogger(CarTrackerMQ.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
