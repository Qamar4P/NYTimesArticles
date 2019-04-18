package dev.qamar.nytimesarticles.utils;

import android.util.Base64;

import java.io.UnsupportedEncodingException;

/**
 * Created by Qamar4P on 18/4/2019.
 * Islamabad, Pakistan.
 */
public class Utils {
    public static String decode(String k) {
        String dk = "";
        try {
            dk = new String(Base64.decode(AppConst.K, Base64.DEFAULT), "UTF8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return dk;
    }
}
