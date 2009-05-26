package com.pdpsoft.commons;

/**
 * Common util class provides methods for accessing underlying OS System.
 * <p/>
 * Ashnaco - G16
 * User: d_farid
 * Date: Aug 20, 2006
 * Time: 10:04:11 AM
 */
public class SysUtil {

    public static String getComputerName() {
        String computerName;
        try {
            computerName = System.getenv("COMPUTERNAME");
            if (computerName == null)
                computerName = "COMPUTER-NAME";
        } catch (SecurityException e) {
            computerName = "COMPUTER-NAME";
        }
        return computerName;
    }
}
