/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dampizza.util;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

/**
 * Mail utility
 * @author Ismael
 */
public class MailUtil {
    /**
     *  Send email
     * @param emailTo the destinatary
     * @param subject the subject of the email
     * @param message the message of the email
     */
    public static void sendEmail(String emailTo,String subject, String message) {
        try {
            //create the email
            Email email = new SimpleEmail();
            //set the host and the port
            email.setHostName("smtp.googlemail.com");
            email.setSmtpPort(465);
            //create the autentificator
            DefaultAuthenticator da = new DefaultAuthenticator("dampizza123@gmail.com", "1234567890p");
            //set the autetificator
            email.setAuthenticator(da);
            email.setSSLOnConnect(true);
            //set from
            email.setFrom("dampizza123@gmail.com", "DamPizza");
            //set subject
            email.setSubject(subject);
            //set the message
            email.setMsg(message);
            //send to
            email.addTo(emailTo);
            //send email
            email.send();
        } catch (EmailException ex) {
            ex.printStackTrace();
        }
        //System.out.println("email send");
    }
}
