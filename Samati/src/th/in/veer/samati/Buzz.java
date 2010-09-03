/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package th.in.veer.samati;

import java.io.IOException;
import java.io.InputStream;
import javax.microedition.media.Manager;
import javax.microedition.media.MediaException;
import javax.microedition.media.Player;

/**
 *
 * @author Vee Satayamas
 */
public class Buzz {

    Player player;

    public Buzz() {
        InputStream is = getClass().getResourceAsStream("buzz.wav");
        try {
            player = Manager.createPlayer(is, "audio/X-wav");
        } catch (IOException ex) {
        } catch (MediaException ex) {
        }
    }

    public void buzz() {
        try {
            player.start();
        } catch (MediaException ex) {
        }
    }
}
