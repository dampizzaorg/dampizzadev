/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dampizza.util;

import com.dampizza.App;
import com.dampizza.logic.imp.UserManagerImp;
import com.dampizza.logic.io.UserManagerInterface;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 *
 * @author DamPizza
 */

public class PasswordGenerator {

    // SecureRandom() constructs a secure random number generator (RNG) implementing the default random number algorithm

    private static final Random RANDOM = new SecureRandom();
    /**
     * Length of password. @see #generateRandomPassword()
     */
    public static final int PASSWORD_LENGTH = 10;

    /**
     * Generate a random String suitable for use as a temporary password.
     *
     * @return String suitable for use as a temporary password
     * @since 2.4
     */
    public static void generateRandomPassword(String email) {
      // Pick from some letters that won't be easily mistaken for each
        // other. So, for example, omit o O and 0, 1 l and L.
        String letters = "abcdefghjkmnpqrstuvwxyzABCDEFGHJKMNPQRSTUVWXYZ23456789+@!#$%&/()=?¿";

        String pw = "";
        for (int i = 0; i < PASSWORD_LENGTH; i++) {
            int index = (int) (RANDOM.nextDouble() * letters.length());
            pw += letters.substring(index, index + 1);
        }
        System.out.println(pw);
        sendEmail(pw, email);
        //Update the user password on the DB
        //UserManagerInterface i=new UserManagerImp();
        new UserManagerImp().changePassword(email, EncrypterUtil.encrypt(pw));
    }

    private static void sendEmail(String pw, String email) {
        String message = "You has request  recover passwod, unfortunaly we "
                + "cant send you your password.\nThis is yor new password: " + pw + "\n dont forget changing the password before get logged";
        MailUtil.sendEmail(email, "Password recover", message);
    }

}
