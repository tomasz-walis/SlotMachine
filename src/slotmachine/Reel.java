
package slotmachine;


/**
 * Created by Tom on 11/12/2016.
 */

import javax.swing.*;
import java.time.*;
import java.util.*;

public class Reel {


    private ArrayList<Symbol> symbols = new ArrayList<>();
    private static final int INITIAL_CREDITS = 10;
    private int credits = INITIAL_CREDITS;
    private int bet;
    private int numberOfWins;
    private int numberOfLosses;


    public Reel() {

        Symbol seven = null;
        Symbol bell = null;
        Symbol melon = null;
        Symbol plum = null;
        Symbol lemon = null;
        Symbol cherry = null;


        //load images
        try{
            seven = new Symbol(7, new ImageIcon(getClass().getResource("/images/redseven.png")));
        }catch(NullPointerException e){
            JOptionPane.showMessageDialog(null, "The image REDSEVEN is not found !!");
        }

        try{
            bell = new Symbol(6, new ImageIcon(getClass().getResource("/images/bell.png")));
        }catch(NullPointerException e){
            JOptionPane.showMessageDialog(null, "The image BELL is not found !!");
        }

        try{
            melon = new Symbol(5, new ImageIcon(getClass().getResource("/images/watermelon.png")));
        }catch(NullPointerException e){
            JOptionPane.showMessageDialog(null, "The image WATERMELON is not found !!");
        }

        try{
            plum = new Symbol(4, new ImageIcon(getClass().getResource("/images/plum.png")));
        }catch(NullPointerException e){
            JOptionPane.showMessageDialog(null, "The image PLUM is not found !!");
        }

        try{
            lemon = new Symbol(3, new ImageIcon(getClass().getResource("/images/lemon.png")));
        }catch(NullPointerException e){
            JOptionPane.showMessageDialog(null, "The image LEMON is not found !!");
        }

        try{
            cherry = new Symbol(2, new ImageIcon(getClass().getResource("/images/cherry.png")));
        }catch(NullPointerException e){
            JOptionPane.showMessageDialog(null, "The image CHERRY is not found !!");
        }


        //add images to the array list
        symbols.add(seven);
        symbols.add(bell);
        symbols.add(melon);
        symbols.add(plum);
        symbols.add(lemon);
        symbols.add(cherry);

    }

    //spin method to randomize the symbols
    public Symbol spin() {
        Random rand = new Random();
        int randomNum;
        randomNum = rand.nextInt(symbols.size());
        return symbols.get(randomNum);
    }


    // get credit
    public int getCredits() {
        return credits;
    }

    // add credit
    public void addCredit() {
        this.credits++;
    }

    // get bet
    public int getBet() {
        return this.bet;
    }

    // get number of wins
    public int getNumberOfWins() {
        return numberOfWins;
    }

    // get number of loses
    public int getNumberOfLosses() {
        return numberOfLosses;
    }

    // bet one method
    public void betOne() {
        if (this.credits > 0) {
            this.bet++;
            this.credits--;
        }
    }

    // bet max method
    public void betMax() {
        if(this.credits >= 3) {
            this.bet += 3;
            this.credits -= 3;
        }
    }

    // reset button method
    public void reset() {
        this.credits += this.bet;
        this.bet = 0;
    }


    // calculate average credit netted per game
    public double getAvgCreditsNettedPerGame() {

        if (this.numberOfWins + this.numberOfLosses == 0) {
            return 0;
        }

        double creditsEarned = this.credits - INITIAL_CREDITS; // don't count the initial credits

        if(creditsEarned == 0) {
            return -(this.numberOfLosses + this.numberOfWins);
        }
        creditsEarned = Math.round(creditsEarned/(this.numberOfLosses + this.numberOfWins)*100.0)/100.0;
        return  creditsEarned ;
    }

    //game won method
    public int won(int result) {
        int payout = 0;
        if (this.bet==0){
            credits=credits;
        }else {
            payout = this.bet * result;
            this.credits += payout;
            this.bet = 0;
            //calculate number of wins for statistics
            this.numberOfWins++;

        }
        return payout;

    }

    // game lost method
    public int lost() {
        int amountLost = this.bet;
        //calculate number of loses for statistics
        this.numberOfLosses++;
        this.bet = 0;
        return amountLost;
    }

    // convert number of wins and loses to string, to store them in to the file as strings,
    //also adds current date and time when statistics are saved to file
    @Override
    public String toString() {
        return "Number of wins: " + this.numberOfWins + "\n  " +
                "Number of losses: " + this.numberOfLosses + "\n  " +
                "Average credits netted per game:  " + this.getAvgCreditsNettedPerGame() +"\n  " +
                "Date/time: " + LocalDateTime.now();
    }
}