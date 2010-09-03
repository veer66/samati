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
public class SplashScreen extends Form implements CommandListener {

    private StringItem infoItem;
    private SamatiController controller;
    private Command okCommand;

    public void commandAction(Command cmd, Displayable displayable) {
        if (displayable == this) {
//            if (cmd == exitCommand) {
//                controller.onExit();
            if (cmd == okCommand) {
                controller.onSplashOk();
            }
        }
    }

    public SplashScreen(SamatiController controller) {
        super("Samati");
        this.controller = controller;
        infoItem = new StringItem("Samati", "Copyright (C) 2010 Vee Satayamas\n\n"
                + "Version 0.0.1\n\n"
                + "Buzz sound is available under a "
                + "Creative Commons Sampling Plus 1.0 License. "
                + "Copyright (C) 2010 Nick Oakman.");
        append(infoItem);

        okCommand = new Command("OK", Command.OK, 1);
        addCommand(okCommand);
        setCommandListener(this);
    }
}
