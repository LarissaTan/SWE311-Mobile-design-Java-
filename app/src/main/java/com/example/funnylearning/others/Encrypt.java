package com.example.funnylearning.others;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Encrypt {

    public static String SHA(final String str) {

        String result = null;

        /// Make sure it is a valid string before starting to encrypt
        if (str != null && str.length() > 0) {
            try {

                /***** SHA Encryption begins *****/
                // Create an encrypted object and pass in the encrypted type
                MessageDigest messageDigest = MessageDigest.getInstance("MD5");
                messageDigest.update(str.getBytes());                           // Pass in the string to encrypt
                byte[] byteBuffer = messageDigest.digest();                         // get byte type result

                // convert byte to string
                StringBuilder strHexString = new StringBuilder();

                // Traversing the byte buffer
                for (byte b : byteBuffer) {
                    String hex = Integer.toHexString(0xff & b);
                    if (hex.length() == 1) {
                        strHexString.append('0');
                    }
                    strHexString.append(hex);
                }

                // get the result
                result = strHexString.toString();

            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
        }

        return result;
    }
}
