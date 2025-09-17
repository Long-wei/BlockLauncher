package org.bkl.os;

public class SystemUtils {

    public static String osName = null;

    public SystemUtils() {
        osName = System.getProperty("os.name").toLowerCase();
    }


}
