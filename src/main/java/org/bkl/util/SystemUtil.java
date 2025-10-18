package org.bkl.util;

public class SystemUtil {

    public static String osName = null;

    public SystemUtil() {
        osName = System.getProperty("os.name").toLowerCase();
    }

    public static String getOsName() {
        return osName = System.getProperty("os.name").toLowerCase();
    }

}
