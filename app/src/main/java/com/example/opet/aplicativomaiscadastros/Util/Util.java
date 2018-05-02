package com.example.opet.aplicativomaiscadastros.Util;

import android.os.Build;
import android.support.annotation.RequiresApi;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by opet on 02/05/2018.
 */

public class Util {
    public static Date toDate (String data) throws ParseException {
        return new SimpleDateFormat ("dd/MM/yyyy").parse(data);
    }

    public static String toString (Date data) {
        return new SimpleDateFormat("dd/MM/yyyy").format(data);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public static String toMD5(String data){
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
            md5.update(StandardCharsets.UTF_8.encode(data));
            return String.format("%032x", new BigInteger(1, md5.digest()));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }
}
