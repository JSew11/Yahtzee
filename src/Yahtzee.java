// Joshua Seward
// February 2, 2021
// Description: Main file for the full "Yahtzee" game simulation.

import java.util.*;

public class Yahtzee {

    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        Hand h = new Hand();
        ScoreSheet s = new ScoreSheet(h);
        int cur_roll; // current roll in the turn
        int max_rolls = 3; // max # of rolls in a turn
        char playOn = 'y';

        while(playOn == 'y'){
            cur_roll = 1;
            h.newHand();
            h.printHand();
            while(cur_roll < max_rolls){
                if(h.reRoll()) cur_roll++;
                h.printHand();
            }
            h.sortPrint();
            s.possibleScore();
            System.out.print("Enter 'y' to play again: ");
            playOn = in.next().charAt(0);
            System.out.println();
        }
    }
}