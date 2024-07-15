package team01.yaksutor.security.listener;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserAgentPaser {

    public static String getBrowserName(String userAgent) {
        if (userAgent == null) {
            return "Unknown";
        }

        if (userAgent == null) {
            return "Unknown";
        }

        if (userAgent.contains("Edg")) {
            return "Edge";
        } else if (userAgent.contains("Whale")) {
            return "Whale";
        } else if (userAgent.contains("Chrome") && !userAgent.contains("Chromium")) {
            return "Chrome";
        } else if (userAgent.contains("Firefox")) {
            return "Firefox";
        } else if (userAgent.contains("Safari") && !userAgent.contains("Chrome")) {
            return "Safari";
        } else if (userAgent.contains("MSIE") || userAgent.contains("Trident")) {
            return "Internet Explorer";
        } else if (userAgent.contains("Opera") || userAgent.contains("OPR")) {
            return "Opera";
        }  else {
            return "Unknown";
        }
    }
}
