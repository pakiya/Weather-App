package com.mvparchitecture.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;

public class UtilsClass {

    public static boolean checkInternetConnection(Context context) {
        if (context != null) {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

            NetworkInfo wifi = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            NetworkInfo mobile = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

            return wifi.isConnected() || mobile.isConnected();
        }
        return false;
    }

    public static boolean isStringNullOrEmpty(String str) {
        return str == null || str.trim().length() == 0;
    }

    public static String getTime(long timestamp) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(timestamp);
        SimpleDateFormat formatter = new SimpleDateFormat("h:mm a", Locale.getDefault()); // 12:08 PM
        formatter.setTimeZone(TimeZone.getTimeZone("GMT"));
        String dateTime = formatter.format(calendar.getTime());
        return dateTime.replace("a.m", "AM").replace("p.m", "PM");
    }

    public static String getDateTimeDayMonthDay(long timestamp) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(timestamp);
        SimpleDateFormat formatter = new SimpleDateFormat("EEE, d MMM", Locale.getDefault());// Sat, 20 Jan.
        formatter.setTimeZone(TimeZone.getTimeZone("GMT"));
        return formatter.format(calendar.getTime());
    }

    public static String convertKtoC(Double k) {
        return String.valueOf((int) (k - 273));
    }

}
