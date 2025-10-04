package org.bkl.os;

public class SystemUtils {

    public static String osName = null;

    public SystemUtils() {
        osName = System.getProperty("os.name").toLowerCase();
    }

    public static String getOsName() {
        return osName = System.getProperty("os.name").toLowerCase();
    }

}
