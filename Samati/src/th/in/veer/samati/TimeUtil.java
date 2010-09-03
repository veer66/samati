/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package th.in.veer.samati;

/**
 *
 * @author Vee Satayamas
 */
public class TimeUtil {
    private static String padZero(String txt) {
        if(txt.length() < 2) {
            txt = "0" + txt;
        }
        return txt;
    }

    public static String minSec(long sec) {
        long min = sec / 60;
        sec = sec % 60;
        String minTxt = padZero(Long.toString(min));
        String secTxt = padZero(Long.toString(sec));
        return minTxt + ":" + secTxt;
    }

    public static String hourMinSec(long sec) {
        long min = (sec / 60) % 60;
        long hour = min / 60;
        sec = sec % 60;
        String minTxt = padZero(Long.toString(min));
        String secTxt = padZero(Long.toString(sec));
        String hourTxt = padZero(Long.toString(hour));
        return hourTxt + ":" + minTxt + ":" + secTxt;
    }
}
