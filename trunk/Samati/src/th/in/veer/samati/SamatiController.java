/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package th.in.veer.samati;

import java.util.Timer;
import java.util.TimerTask;
import javax.microedition.lcdui.Display;

/**
 *
 * @author Vee Satayamas
 */
public class SamatiController {

    static final int WORK_TIME = 25 * 60;
    static final int SHORT_REST_TIME = 5 * 60;
    static final int LONG_REST_TIME = 20 * 60;

    static final int PAUSE = 1;
    static final int WORK = 2;
    static final int REST = 3;
    static final long DELAY = 1000; //ms
    private SamatiMidlet midlet;
    private Display display;

    private TimerScreen timerScreen;
    private SplashScreen splashScreen;

    private long time;
    private int state;
    private long expectedTime;
    private int pomodoroCount;
    private long accumulatedWorkTime = 0;
    private Timer timer;
    private TimerTask timerTask;
    private Buzz buzz;

    public SamatiController(SamatiMidlet midlet) {
        this.midlet = midlet;
        display = Display.getDisplay(midlet);
        timerScreen = new TimerScreen(this);
        splashScreen = new SplashScreen(this);
        
        state = SamatiController.PAUSE;

        accumulatedWorkTime = 0;
        pomodoroCount = 0;

        timerTask = new SamatiTimerTask(this);
        timer = new Timer();

        buzz = new Buzz();
    }

    public void onStart() {
        timer.schedule(timerTask, 0, SamatiController.DELAY);
        display.setCurrent(splashScreen);
    }

    public void onResume() {
        display.setCurrent(timerScreen);
    }

    public void onExit() {
        display.setCurrent(null);
        midlet.destroyApp(true);
        midlet.notifyDestroyed();
    }

    public void onWork() {
        if (state == SamatiController.PAUSE) {
            transitToWork();
        }
    }

    public void onInterrupt() {
        if (state != SamatiController.PAUSE) {
            transitToPause();
        }
    }

    private void updateTimerView() {
        timerScreen.setTime(expectedTime - time);
    }

    private void updateStateView() {
        if (state == SamatiController.PAUSE) {
            timerScreen.setState("Interrupted");
        } else if (state == SamatiController.WORK) {
            timerScreen.setState("Work");
        } else if (state == SamatiController.REST) {
            timerScreen.setState("Rest");
        }
    }

    private void updateAccumulatedWorkTime() {
        timerScreen.setAccumulatedWorkTime(accumulatedWorkTime);
    }

    private void updatePomodoroView() {
        timerScreen.setPomodoroCount(pomodoroCount);
    }

    private void transitToWork() {
        buzz.buzz();
        time = 0;
        expectedTime = SamatiController.WORK_TIME;
        state = SamatiController.WORK;
        updateStateView();
    }

    private void transitToRest() {
        buzz.buzz();
        pomodoroCount++;
        time = 0;
        if(pomodoroCount % 4 == 0)
            expectedTime = SamatiController.LONG_REST_TIME;
        else
            expectedTime = SamatiController.SHORT_REST_TIME;
        state = SamatiController.REST;        
        updateStateView();
        updatePomodoroView();
    }

    private void transitToPause() {
        state = SamatiController.PAUSE;
        updateStateView();
    }

    public void onUpdateTime() {
        if (state != SamatiController.PAUSE) {
            time++;

            if (state == SamatiController.WORK) {
                accumulatedWorkTime++;
                updateAccumulatedWorkTime();
            }

            if (time >= expectedTime) {
                time = 0;
                if (state == SamatiController.WORK) {
                    transitToRest();
                } else if (state == SamatiController.REST) {
                    transitToWork();
                }
            }

            updateTimerView();
        }
    }

    void onSplashOk() {
        onResume();
    }
}
