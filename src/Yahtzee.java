// Joshua Seward
// February 2, 2021
// Description: Main file for the full "Yahtzee" game simulation.

import java.util.*;

public class Yahtzee {

    public static void main(String[] args){
        Hand h = new Hand(6, 5);
        ScoreSheet s = new ScoreSheet(h);
        int max_rolls = 3; // max # of rolls in a turn

        round(max_rolls, h, s);
    }

    public static void round(int maxRolls, Hand h, ScoreSheet s){
        Scanner in = new Scanner(System.in);
        int cur_roll;
        char playOn = 'y';

        while(playOn == 'y'){
            cur_roll = 1;
            h.newHand();
            h.printHand();

            while(cur_roll < maxRolls){
                System.out.print("Enter which dice you want to keep (y or n): ");
                String keep = in.nextLine();

                int reRoll = h.reRoll(keep);
                if(reRoll == 1){
                    cur_roll++;
                }
                else if(reRoll == 0){
                    cur_roll = maxRolls;
                }
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