/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shido.encryptor;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author AmaranSidhiq
 */
public class MD5 {

//    public static String bigIntegerEncrypt(String aString) {
//        String result = null;
//        try {
//            MessageDigest m = MessageDigest.getInstance("SHA"); // or MD5
//            BigInteger tempNumber = new BigInteger(1, m.digest(aString.getBytes()));
//            result = tempNumber.toString(16);
//        } catch (NoSuchAlgorithmException nsae) {
//        }
//        return result;
//    }
    public static String encrypt(String aString) {
        String result = null;
        byte[] defaultBytes = aString.getBytes();
        try {
            MessageDigest algorithm = MessageDigest.getInstance("MD5");
            algorithm.reset();
            algorithm.update(defaultBytes);
            byte messageDigest[] = algorithm.digest();

            StringBuffer hexString = new StringBuffer();
            for (byte diggest : messageDigest) {
                String hex = Integer.toHexString(0xFF & diggest);
                if (hex.length() == 1) {
                    hexString.append("0");
                }
                hexString.append(hex);
            }
            String foo = messageDigest.toString();
//            System.out.println(foo);
            result = hexString.toString();
        } catch (NoSuchAlgorithmException nsae) {
        }
        return result;
    }

    public String MD5_Convert(String vcInput) throws
            java.security.NoSuchAlgorithmException {

        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(vcInput.getBytes());
        byte[] output = md.digest();
        String result = bytesToHex(output);

        return result;
    }

    public static String bytesToHex(byte[] b) {
        char hexDigit[] = {'0', '1', '2', '3', '4', '5', '6', '7',
            '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'
        };
        StringBuffer buf = new StringBuffer();
        for (int j = 0; j < b.length; j++) {
            buf.append(hexDigit[(b[j] >> 4) & 15]);
            buf.append(hexDigit[b[j] & 15]);
        }
        return buf.toString();
    }

    public static String encodeHexString(String sourceText) {

        byte[] rawData = sourceText.getBytes();
        StringBuffer hexText = new StringBuffer();
        String initialHex = null;
        int initHexLength = 0;

        for (int i = 0; i < rawData.length; i++) {
            int positiveValue = rawData[i] & 0x000000FF;
            initialHex = Integer.toHexString(positiveValue);
            initHexLength = initialHex.length();
            while (initHexLength++ < 2) {
                hexText.append("0");
            }
            hexText.append(initialHex);
        }
        return hexText.toString();
    }

    public static String decodeHexString(String hexText) {

        String decodedText = null;
        String chunk = null;

        if (hexText != null && hexText.length() > 0) {
            int numBytes = hexText.length() / 2;

            byte[] rawToByte = new byte[numBytes];
            int offset = 0;
            int bCounter = 0;
            for (int i = 0; i < numBytes; i++) {
                chunk = hexText.substring(offset, offset + 2);
                offset += 2;
                rawToByte[i] = (byte) (Integer.parseInt(chunk, 16) & 0x000000FF);
            }
            decodedText = new String(rawToByte);
        }
        return decodedText;
    }
    public static void main(String args[]){
        String text="Amaran sidhiq";
        String hex=encodeHexString(text);
        System.out.println(hex);
        System.out.println(hex+"->" + decodeHexString(hex));
    }
}
