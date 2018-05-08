package com.poland.rest.api;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import com.poland.service.RegistrationService;
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
import org.apache.activemq.command.ActiveMQTextMessage;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.json.JSONObject;

/**
 *
 * @author PC-YOERI
 */
@MessageDriven(name = "testmdb", activationConfig = {
    @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "jms/TESTQ")
    ,
    @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue")
    ,
    @ActivationConfigProperty(propertyName = "destination", propertyValue = "TESTQ")
    ,
    @ActivationConfigProperty(propertyName = "resourceAdapter", propertyValue = "activemq-rar-5.15.3")})
public class CarTrackerMQ implements MessageListener {

    RegistrationService registrationService;

    @Inject
    public CarTrackerMQ() {
        this.registrationService = registrationService;
    }

    @Override
    public void onMessage(Message msg) {
        try {
            if (msg instanceof TextMessage) {
                TextMessage textMessage = (TextMessage) msg;
                ((TextMessage) msg).getText();
                System.out.println(textMessage.getText());

//                JSONObject obj = new JSONObject(textMessage.getText());
//
//                System.out.println(obj);
//                Date date = new Date(obj.getString("date"));
//                Double latitude = obj.getDouble("latitude");
//                Double longitude = obj.getDouble("longitude");
//                String authorisationCode = obj.getString("longitude");
//                registrationService.registerLocation(date, latitude, longitude, authorisationCode);
            }
        } catch (JMSException ex) {
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
