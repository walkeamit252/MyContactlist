package com.app.mycontactlist.common.utils;
import android.content.Context;
import android.os.Build;
import android.provider.Settings;

/**
 * Created by Amit Walke on 20-01-2018.
 */
public class BuildUtil {

    public static boolean isLollipopAndAbove() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP;
    }

    public static String getDeviceId(Context ctx) {
        return Settings.Secure.getString(ctx.getContentResolver(),
                Settings.Secure.ANDROID_ID);
    }

    public static String getDeviceName(){
        String manufacturer = Build.MANUFACTURER;
        return manufacturer;
    }


    public static String getDeviceModelName() {
        String model = Build.MODEL;
        return model;
    }

    public static int getAndroidVersionCode(){
        int currentapiVersion = Build.VERSION.SDK_INT;
        return currentapiVersion;
    }


}
