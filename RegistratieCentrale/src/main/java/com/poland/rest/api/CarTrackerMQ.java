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
    @ActivationConfigProperty(propertyName = "resourceAdapter", propertyValue = "activemq-rar-5.15.3")})
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

                JSONObject locationObj = obj.getJSONObject("location");
                JSONObject dateObj = obj.getJSONObject("date");

                Double latitude = locationObj.getDouble("lat");
                Double longitude = locationObj.getDouble("lng");
                
                long year = dateObj.getLong("year");
                long month = dateObj.getLong("month");
                long dayOfMonth = dateObj.getLong("dayOfMonth");
                long hourOfDay = dateObj.getLong("hourOfDay");
                long minute = dateObj.getLong("minute");
                long second = dateObj.getLong("second");
                
                StringBuilder sb = new StringBuilder();
                
                sb.append(year);
                sb.append("-");
                sb.append(month);
                sb.append("-");
                sb.append(dayOfMonth);
                sb.append(" ");
                sb.append(hourOfDay);
                sb.append(":");
                sb.append(minute);
                sb.append(":");
                sb.append(second);
                
                Date date = sdf.parse(sb.toString());

                String authorisationCode = obj.getString("trackerId");
                registrationService.registerLocation(date, latitude, longitude, authorisationCode);
            }
        } catch (JMSException | ParseException ex) {
            Logger.getLogger(CarTrackerMQ.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /*
    Format Expected result
    {
   "date" : 123456789,
   "latitude" : 0,
   "longitude" : 0,
   "authorisationCode" : ""
}

     */
}
