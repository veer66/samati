/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package th.in.veer.samati;

import javax.microedition.midlet.*;

/**
 * @author Vee Satayamas
 */
public class SamatiMidlet extends MIDlet {
    private boolean midletPaused;
    private SamatiController controller;

    public SamatiMidlet() {
        midletPaused = false;
        controller = new SamatiController(this);
    }

    public void startApp() {
        if (midletPaused) {
            controller.onResume();
        } else {
            controller.onStart();
        }
        midletPaused = false;
    }

    public void pauseApp() {
        midletPaused = true;
    }

    public void destroyApp(boolean unconditional) {
    }
}
