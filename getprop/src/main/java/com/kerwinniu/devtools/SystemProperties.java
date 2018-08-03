package com.kerwinniu.devtools;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * I cannot get the the prop set by `adb shell setprop prop_name` with some devices, so I accomplish this just as the
 * System class {@link Log#isLoggable(String, int)} does, that is read the `log.tag.prop_name`
 * <p>
 * In order to use this utility, you should set the prop you want via `adb shell setprop log.tag.prop_name prop_value`
 *
 * @author KerwinNiu
 */
public class SystemProperties {

    private static String TAG = "SystemProperties";

    public static String read(String key) {
        String propName = hackKey(key);
        Process process = null;
        BufferedReader bufferedReader = null;

        try {
            String getPropExecutable = "/system/bin/getprop";
            process = new ProcessBuilder().command(getPropExecutable, propName).redirectErrorStream(true).start();
            bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line = bufferedReader.readLine();
            if (line == null) {
                line = ""; //prop not set
            }
            logi(key, line);
            return line;
        } catch (Exception e) {
            loge(key, e);
            return "";
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    loge(key, e);
                }
            }
            if (process != null) {
                process.destroy();
            }
        }
    }

    private static String hackKey(String key) {
        return "log.tag." + key;
    }

    private static void loge(String key, Exception e) {
        Log.e(TAG, "Failed to read System Property " + key, e);
    }

    private static void logi(String key, String value) {
        Log.e(TAG, "Read System Property: " + key + "=" + value);
    }
}
