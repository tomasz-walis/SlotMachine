package slotmachine;

/**
 * Created by Tom on 11/12/2016.
 */
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.net.URL;
import javax.swing.border.*;

public class SlotMachineGui extends JFrame implements ActionListener {

    //button variables
    private JButton btSpin, btAddCoin, btBetOne, btBetMax, btReset, btStat;
    //reel buttons variables
    private JButton btReel1,btReel2,btReel3;
    //labels
    private JLabel lbCreditArea,lbBetArea,infoArea;

    private Reel reel = new Reel();
    //reels
    private Symbol reel1;
    private Symbol reel2;
    private Symbol reel3;

    //spinning from thread
    private SpinReels spinReel1;
    private SpinReels spinReel2;
    private SpinReels spinReel3;

    //boolean to ignore reel clicks
    private boolean checkResult =true;

    //main frame
    private SlotMachineGui() {


        Icon luckySeven = null;
        try {
            setTitle("Slot Machine");
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setSize(850, 600);
            URL url = getClass().getResource("/images/icon.png");
            ImageIcon imgIcon = new ImageIcon(url);
            super.setIconImage(imgIcon.getImage());

            //when game run the first time this gets the lucky 7 images
            luckySeven = new ImageIcon((getClass().getResource("/images/luckySeven.png")));
            setVisible(true);
        }catch (NullPointerException e){
            JOptionPane.showMessageDialog(null, "Image not found, unable to load frame !!");
        }

        //sets the empty border around components
        Border emptyBorder = BorderFactory.createEmptyBorder();
        //sets the the font for buttons
        Font buttonsFont = new Font("Berlin Sans FB Demi", Font.BOLD, 25);
        //sets the the font for information labels
        Font areasFont = new Font("Arial", Font.BOLD, 17);

        //main panel added to the JFrame
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(2, 0));
        mainPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        mainPanel.setBackground(Color.BLACK);
        add(mainPanel);

        //Panel for reels added to the main panel
        JPanel panelReels = new JPanel();
        panelReels.setBorder(new EmptyBorder(5, 5, 5, 5));
        panelReels.setLayout(new GridLayout(0, 3, 20, 20));
        panelReels.setBackground(Color.BLACK);
        mainPanel.add(panelReels);

        //interface bottom panel added to the main panel
        JPanel panelInterface = new JPanel();
        panelInterface.setLayout(new GridLayout(2, 0,12,0));
        mainPanel.add(panelInterface);

        //Panel for the information labels
        JPanel panelInfo = new JPanel();
        panelInfo.setLayout(new GridLayout(0, 3));
        panelInfo.setBackground(Color.BLACK);
        panelInterface.add(panelInfo);

        //Panel for the buttons
        JPanel panelButtons = new JPanel();
        panelButtons.setBorder(new EmptyBorder(5, 5, 5, 5));
        panelButtons.setLayout(new GridLayout(0, 3, 5, 5));
        panelButtons.setBackground(Color.BLACK);
        panelInterface.add(panelButtons);

        //Spin Button
        btSpin = new JButton("SPIN");
        btSpin.setBackground(Color.YELLOW);
        btSpin.setBorder(emptyBorder);
        btSpin.setFont(buttonsFont);
        btSpin.setForeground(Color.BLACK);
        btSpin.addActionListener(this);

        //Add Coin button
        btAddCoin = new JButton("ADD COIN");
        btAddCoin.setBackground(Color.GREEN);
        btAddCoin.setBorder(emptyBorder);
        btAddCoin.setFont(buttonsFont);
        btAddCoin.setForeground(Color.BLACK);
        btAddCoin.addActionListener(this);

        //Bet one button
        btBetOne = new JButton("BET ONE");
        btBetOne.setBackground(new Color(122, 0, 0));
        btBetOne.setBorder(emptyBorder);
        btBetOne.setFont(buttonsFont);
        btBetOne.setForeground(Color.BLACK);
        btBetOne.addActionListener(this);

        //Bet Max Button
        btBetMax = new JButton("BET MAX");
        btBetMax.setBackground(new Color(122, 0, 0));
        btBetMax.setBorder(emptyBorder);
        btBetMax.setFont(buttonsFont);
        btBetMax.setForeground(Color.BLACK);
        btBetMax.addActionListener(this);

        //Reset Button
        btReset = new JButton("RESET BET");
        btReset.setBackground(Color.LIGHT_GRAY);
        btReset.setBorder(emptyBorder);
        btReset.setFont(buttonsFont);
        btReset.setForeground(Color.BLACK);
        btReset.addActionListener(this);

        //Statistics Button
        btStat = new JButton("STATISTICS");
        btStat.setBackground(Color.LIGHT_GRAY);
        btStat.setBorder(emptyBorder);
        btStat.setFont(buttonsFont);
        btStat.setForeground(Color.BLACK);
        btStat.addActionListener(this);

        //Buttons added to the button panel
        panelButtons.add(btSpin);
        panelButtons.add(btAddCoin);
        panelButtons.add(btReset);
        panelButtons.add(btBetMax);
        panelButtons.add(btBetOne);
        panelButtons.add(btStat);

        //label to display credit
        lbCreditArea = new JLabel("CREDITS:  " + reel.getCredits());
        lbCreditArea.setHorizontalAlignment(JLabel.CENTER);
        lbCreditArea.setForeground(Color.RED);
        lbCreditArea.setFont(areasFont);
        TitledBorder titledC = new TitledBorder("CREDIT AREA");
        titledC.setTitleFont(new Font("Arial", Font.BOLD, 12));
        titledC.setTitleColor(Color.RED);
        lbCreditArea.setBorder(titledC);
        panelInfo.add(lbCreditArea);

        //label to display bet
        lbBetArea = new JLabel("BET: 0 CREDITS");
        lbBetArea.setHorizontalAlignment(JLabel.CENTER);
        lbBetArea.setForeground(Color.GREEN);
        lbBetArea.setFont(areasFont);
        TitledBorder titledB = new TitledBorder("BET AREA");
        titledB.setTitleFont(new Font("Arial", Font.BOLD, 12));
        titledB.setTitleColor(Color.GREEN);
        lbBetArea.setBorder(titledB);
        panelInfo.add(lbBetArea);

        //label to display information's
        infoArea = new JLabel("ENTER BET AND SPIN!!");
        infoArea.setForeground(Color.WHITE);
        infoArea.setFont(areasFont);
        infoArea.setHorizontalAlignment(JTextField.CENTER);
        TitledBorder titled = new TitledBorder("INFORMATION AREA");
        titled.setTitleFont(new Font("Arial", Font.BOLD, 12));
        titled.setTitleColor(Color.WHITE);
        infoArea.setBorder(titled);
        panelInfo.add(infoArea);

        //First reel button, added to reels panel
        btReel1 = new JButton(luckySeven);
        btReel1.setBackground(Color.BLACK);
        btReel1.setBorder(emptyBorder);
        btReel1.addActionListener(this);
        panelReels.add(btReel1);

        //Second reel button, added to reels panel
        btReel2 = new JButton(luckySeven);
        btReel2.setBackground(Color.BLACK);
        btReel2.setBorder(emptyBorder);
        btReel2.addActionListener(this);
        panelReels.add(btReel2);

        //Third reel button, added to reels panel
        btReel3 = new JButton(luckySeven);
        btReel3.setBackground(Color.BLACK);
        btReel3.setBorder(emptyBorder);
        btReel3.addActionListener(this);
        panelReels.add(btReel3);
    }

    // ActionListener
    public void actionPerformed(ActionEvent e) {

        //Add Coin button clicked
        if (e.getSource().equals(btAddCoin)) {
            reel.addCredit();

            //Bet one button clicked
        } else if (e.getSource().equals(btBetOne)) {

            //Bet one button, validation, if not enough credit display JOption Pane
            if (reel.getCredits()==0) {

                JOptionPane.showMessageDialog(null, "NOT ENOUGH CREDITS !! ");

            } else {
                //go to reel class, to bet one method
                reel.betOne();
            }

            //Bet Max button clicked
        } else if (e.getSource().equals(btBetMax)) {

            //Bet Max button, validation, if not enough credit display JOption Pane
            if (reel.getCredits() < 3) {
                btBetMax.setEnabled(false);
                JOptionPane.showMessageDialog(null, "NOT ENOUGH CREDITS TO BET MAX !! ");

            } else {
                //go to reel class, to betMax method, and disable button, as can be clicked only once per game
                reel.betMax();
                btBetMax.setEnabled(false);
            }

            //Reset button clicked
        } else if (e.getSource().equals(btReset)) {
            //go to reel class, to reset method
            reel.reset();
            btBetMax.setEnabled(true);

            //Statistics button clicked
        } else if (e.getSource().equals(btStat)) {
            //oes to the statsWindow class and opens statistics window, and sets it visible
            StatsWindow statsWindow = new StatsWindow(reel);
            statsWindow.setVisible(true);

            //Spin button clicked
        } else if (e.getSource().equals(btSpin)) {
            //Validation for the bet, bet cant be zero, if no bet has been done display JOption Pane
            if (reel.getBet()==0){
                JOptionPane.showMessageDialog(null, "PLEASE BET FIRST !!");

                //Validation for the credit, bet cant be zero, if no bet has been done display JOption Pane
            }else if (reel.getCredits()<1&& reel.getBet()<1) {
                JOptionPane.showMessageDialog(null, "NO CREDITS PLEASE ADD FIRST !!");
            } else {
                //sets boolean to true to allow reel click
                checkResult = true;

                //spinning reel objects
                spinReel1 = new SpinReels(reel1, btReel1);
                spinReel2 = new SpinReels(reel2, btReel2);
                spinReel3 = new SpinReels(reel3, btReel3);

                //start the thread when button clicked
                spinReel1.startThread();
                spinReel2.startThread();
                spinReel3.startThread();

                //disable all buttons while spinning
                btBetMax.setEnabled(false);
                btBetOne.setEnabled(false);
                btAddCoin.setEnabled(false);
                btStat.setEnabled(false);
                btReset.setEnabled(false);
                btSpin.setEnabled(false);



                //set the information label when spinning
                infoArea.setText("SPINNING !!");
            }

            //One of the reels clicked
        } else if (e.getSource().equals(btReel1) || (e.getSource().equals(btReel2) || (e.getSource().equals(btReel3)))) {
            boolean stop = true;

            //Stop the thread when One of the reels clicked
            // Catch the null exception when reels are clicked without spinning when game run for the first time
            try {
                spinReel1.stopThread(stop);
                spinReel2.stopThread(stop);
                spinReel3.stopThread(stop);

                //goes to checkResult method when boolean is true
                if (checkResult) {
                    checkResult();
                }

            }catch (NullPointerException ex){
                JOptionPane.showMessageDialog(null, "PLEASE SPIN FIRST !!");
            }
        }
        //update the labels
        lbBetArea.setText("BET: " + reel.getBet() + " CREDITS");
        lbCreditArea.setText("CREDITS: " + reel.getCredits());

    }

    /*Runs code on the AWT thread. Which allows to modify the GUI from other threads.
    Causes doRun.run() to be executed asynchronously on the AWT event dispatching thread.
    This will happen after all pending AWT events have been processed.
    This method is used because thread needs to update the GUI.*/
    public static void main(String args[]) {

        SwingUtilities.invokeLater(new Runnable(){
            public void run() {
                new SlotMachineGui();
            }
        });
    }

    //check result method, if the game was won or lost
    private void checkResult() {

        reel1 = spinReel1.getList();
        reel2 = spinReel2.getList();
        reel3 = spinReel3.getList();


        //if symbols on reel one, two, or on reel two and three are the same, multiply the value of symbol by the bet made
        if (reel1.isSameSymbol(reel2) || reel2.isSameSymbol(reel3)) {
            int amountWon = reel.won(reel2.getValue());

            infoArea.setText("YOU WON " + amountWon + " CREDITS !!");


            //if symbols on reel one and 3 are the same, multiply the value of symbol by the bet made
        }else if(reel1.isSameSymbol(reel3)){
            int amountWon = reel.won(reel1.getValue());
            infoArea.setText("YOU WON " + amountWon + " CREDITS !!");


            //if symbols on all 3 reels are the same multiply the value of symbol by the bet made
        }else if (reel1.isSameSymbol(reel2) && reel1.isSameSymbol(reel3)) {
            int amountWon = reel.won(reel1.getValue());
            infoArea.setText("YOU WON " + amountWon + " CREDITS !!");


            //if no matching symbols game is lost
        }else {
            int amountLost = reel.lost();
            infoArea.setText("YOU LOST " + amountLost + " CREDITS !!");

        }

        //enable buttons after each spin has ended
        btBetMax.setEnabled(true);
        btBetOne.setEnabled(true);
        btAddCoin.setEnabled(true);
        btStat.setEnabled(true);
        btReset.setEnabled(true);
        btSpin.setEnabled(true);
        //sets boolean to false to ignore clicking on reels when they stopped
        checkResult = false;

    }
}
