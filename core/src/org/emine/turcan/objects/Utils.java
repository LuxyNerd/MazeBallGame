package org.emine.turcan.objects;

/**
 * Created by emine on 04.02.17.
 */

public class Utils {
    public String convertMillisToDateString(long l) {
        String dateString = "";

        int minutes = 0;
        int seconds = 0;
        long milliSeconds = l;

        minutes = (int) (milliSeconds / 1000 / 60);
        milliSeconds -= minutes * 1000 * 60;

        seconds = (int) (milliSeconds / 1000);
        milliSeconds -= seconds * 1000;

        if(minutes > 0) {
            if(minutes < 10) {
                dateString += "0";
            }
            dateString += minutes + ":";
        }
        if(seconds > 0 || !dateString.equals("")) {
            if(seconds < 10) {
                dateString += "0";
            }
            dateString += seconds + ".";
        }

        if(milliSeconds < 100) {
            dateString += "0";
        }

        if(milliSeconds < 10) {
            dateString += "0";
        }

        return dateString + milliSeconds;
    }
}
