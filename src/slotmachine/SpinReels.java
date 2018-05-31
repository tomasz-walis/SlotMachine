package slotmachine;

/**
 * Created by Tom on 11/12/2016.
 */

import javax.swing.*;


public class SpinReels extends Thread {
    private Reel reel = new Reel();

    private Symbol reels;
    private Thread spinAll;
    private boolean stopThread;

    JButton btReels;

    //constructor
    public SpinReels(Symbol reels, JButton btReels) {
        this.reels = reels;
        this.btReels= btReels;
    }

    // start thread method
    public void startThread() {
        if (spinAll == null) {
            spinAll = new Thread(this);
            spinAll.start();
        }
    }

    // stop thread method
    public void stopThread(boolean aValue) {
        stopThread = aValue;
    }

    //thread to spin all reels
    public void run() {
        while(true) {
            try {
                Thread.sleep(40);

                if (stopThread)
                    break;
                reels = reel.spin();
                btReels.setIcon(reels.getImage());

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    //return value of the symbols in the list
    public Symbol getList() {
        return reels;
    }
}













