/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package th.in.veer.samati;

import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.StringItem;

/**
 *
 * @author Vee Satayamas
 */
public class TimerScreen extends Form implements CommandListener {

    private SamatiController controller;
    private StringItem statusItem;
    private StringItem timeItem;
    private StringItem roundCountItem;
    private StringItem accumulatedWorkTimeItem;
    private StringItem pomodoroCountItem;
//    private Command exitCommand;
    private Command workCommand;
    private Command interruptCommand;

    public TimerScreen(SamatiController controller) {
        super("Samati");
        this.controller = controller;


        statusItem = new StringItem("Status", "Inactive");
        append(statusItem);
        timeItem = new StringItem("Time", "00:00");
        append(timeItem);

        roundCountItem = new StringItem("Pomodoro", "0");
        append(roundCountItem);

        accumulatedWorkTimeItem = new StringItem("Accumulated work", "00:00:00");
        append(accumulatedWorkTimeItem);

        pomodoroCountItem = new StringItem("Pomodoro count", "0");
        append(pomodoroCountItem);

//        exitCommand = new Command("Exit", Command.EXIT, 1);
//        addCommand(exitCommand);

        workCommand = new Command("Work", Command.ITEM, 1);
        addCommand(workCommand);

        interruptCommand = new Command("Stop", Command.ITEM, 1);
        addCommand(interruptCommand);

        setCommandListener(this);
    }

    public void commandAction(Command cmd, Displayable displayable) {
        if (displayable == this) {
//            if (cmd == exitCommand) {
//                controller.onExit();
            if (cmd == workCommand) {
                controller.onWork();
            } else if (cmd == interruptCommand) {
                controller.onInterrupt();
            }
        }
    }

    public void setTime(long time) {
        String text = TimeUtil.minSec(time);
        timeItem.setText(text);
    }

    public void setState(String state) {
        statusItem.setText(state);
    }

    void setPomodoroCount(int pomodoroCount) {
        pomodoroCountItem.setText(Integer.toString(pomodoroCount));
    }

    void setAccumulatedWorkTime(long accumulatedWorkTime) {
        accumulatedWorkTimeItem.setText(TimeUtil.hourMinSec(accumulatedWorkTime));
    }
}
