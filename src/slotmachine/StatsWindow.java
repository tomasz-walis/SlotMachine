package slotmachine;

/**
 * Created by Tom on 11/12/2016.
 */

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;
import java.io.*;


//class to show statistics window
public class StatsWindow extends JFrame implements ActionListener{


    private static final String STATS_FILE_NAME = "current datetime.txt";
    private  Reel reel;
    private  JButton saveButton;
    private  JButton btClose;

    //minimum and maximum representation for the progress bar
    private int minimum = 0;
    private int maximum = 100;

    /*progress bar used to represent graphical representation for losing, wining the game, and average credit
    has been chosen because it can show graphical representation very similar to histogram, and wont exceed the size of frame*/

    public StatsWindow(Reel reel) {
        this.reel = reel;

        //statistics frame
        setTitle("Statistics");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(520, 550);
        setResizable(false);

        // main panel added to the statistics frame
        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(Color.BLACK);
        mainPanel.setLayout(null);
        add(mainPanel);

        // top statistics label
        JLabel statisticsLabel = new JLabel("Statistics", SwingConstants.CENTER);
        statisticsLabel.setForeground(Color.WHITE);
        statisticsLabel.setBounds(20, 20, getWidth(), 20);
        mainPanel.add(statisticsLabel);

        // label for wins
        JLabel winsLabel = new JLabel("Number of wins: " + reel.getNumberOfWins());
        winsLabel.setForeground(Color.WHITE);
        winsLabel.setBounds(20, 350, getWidth(), 20);
        mainPanel.add(winsLabel);
        // progress bar for wins
        JProgressBar barWins = new JProgressBar(JProgressBar.VERTICAL, minimum, maximum);
        barWins.setBorderPainted(false);
        barWins.setString("");
        barWins.setBackground(Color.BLACK);
        barWins.setForeground(Color.GREEN);
        barWins.setBounds(45, 70, 40,270);
        barWins.setValue(reel.getNumberOfWins());
        barWins.setStringPainted(true);
        mainPanel.add(barWins);

        //label for loses
        JLabel lossesLabel = new JLabel("Number of losses: " + reel.getNumberOfLosses());
        lossesLabel.setForeground(Color.WHITE);
        lossesLabel.setBounds(160, 350, getWidth(), 20);
        mainPanel.add(lossesLabel);
        // progress bar for loses
        JProgressBar barLose = new JProgressBar(JProgressBar.VERTICAL, minimum, maximum);
        barLose.setBorderPainted(false);
        barLose.setString("");
        barLose.setBackground(Color.BLACK);
        barLose.setForeground(Color.YELLOW);
        barLose.setBounds(200, 70, 40,270);
        barLose.setValue(reel.getNumberOfLosses());
        barLose.setStringPainted(true);
        mainPanel.add(barLose);

        // label for average credit
        JLabel averageNettedLabel = new JLabel("Average credits per game: " + reel.getAvgCreditsNettedPerGame());
        averageNettedLabel.setForeground(Color.WHITE);
        averageNettedLabel.setBounds(320, 350, getWidth(), 20);
        mainPanel.add(averageNettedLabel);
        // progress bar for average credit
        JProgressBar barAvgCredit = new JProgressBar(JProgressBar.VERTICAL, minimum, maximum);
        barAvgCredit.setBorderPainted(false);
        barAvgCredit.setString("");
        barAvgCredit.setBackground(Color.BLACK);
        barAvgCredit.setForeground(Color.RED);
        barAvgCredit.setBounds(380, 70, 40,270);
        barAvgCredit.setValue(reel.getNumberOfWins()+reel.getNumberOfLosses());
        barAvgCredit.setStringPainted(true);
        mainPanel.add(barAvgCredit);

        //save statistics button
        Border emptyBorder = BorderFactory.createEmptyBorder();
        saveButton = new JButton("SAVE STATISTICS");
        saveButton.addActionListener(this);
        saveButton.setBounds(80, 430, 150, 40);
        saveButton.setBackground(Color.GREEN);
        saveButton.setForeground(Color.BLACK);
        saveButton.setBorder(emptyBorder);
        mainPanel.add(saveButton);

        //close statistics window
        btClose = new JButton("CLOSE");
        btClose.addActionListener(this);
        btClose.setBackground(Color.RED);
        btClose.setForeground(Color.BLACK);
        btClose.setBorder(emptyBorder);
        btClose.setBounds(280,430, 150, 40);
        mainPanel.add(btClose);
    }

    //action listener for the buttons
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(saveButton)) {
            saveStats();
        }
        if (e.getSource().equals(btClose)) {
            dispose ();

        }
    }

    //this method includes PrintWriter to save statistics to file
    private void saveStats() {
        PrintWriter writer;
        try {
            writer = new PrintWriter(new File(STATS_FILE_NAME));
            writer.write(reel.toString());
            writer.close();

            JOptionPane.showMessageDialog(this, "Stats saved to " + STATS_FILE_NAME);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


}
