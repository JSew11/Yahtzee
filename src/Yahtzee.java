// Joshua Seward
// February 2, 2021
// Description: Main file for the full "Yahtzee" game simulation.

public class Yahtzee {

    public static void main(String[] args){
        Hand h = new Hand();
        int cur_roll = 1; // current roll in the turn
        int max_rolls = 3; // max # of rolls in a turn

        h.printHand();
        while(cur_roll < max_rolls){
           if(h.reRoll()) cur_roll++;
            h.printHand();
        }
        h.sortPrint();
    }
}