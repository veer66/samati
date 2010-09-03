/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package th.in.veer.samati;

import java.util.TimerTask;

/**
 *
 * @author Vee Satayamas
 */
public class SamatiTimerTask extends TimerTask {

    private SamatiController controller;

    public SamatiTimerTask(SamatiController controller) {
        this.controller = controller;
    }

    public void run() {
        controller.onUpdateTime();
    }

}
