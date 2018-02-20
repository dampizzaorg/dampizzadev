/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dampizza.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Encrypter util
 * @author Ismael
 * @author Jonxa
 */
public class EncrypterUtil {
    
    public static String encrypt(String message){
        MessageDigest md;
        byte[] resumen=null;
        try {
            //Assign the algorithm 
            md=MessageDigest.getInstance("MD5");
            //parse the message to encrypt to byte array
            byte messageBytes []= message.getBytes();
            //encrypt the byte array
            md.update(messageBytes);
            resumen= md.digest();
            
        } catch (NoSuchAlgorithmException ex) {
            ex.printStackTrace();
        }
        //return the encrypted message on a string
        return  new String(resumen);
    }
    
}
