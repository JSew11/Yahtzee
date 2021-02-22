/**
 * This class runs a game of Yahtzee using the settings from
 * an external config file.
 * No sources to cite.
 *
 * @author Joshua Seward
 * @version 2.1 2/21/21
 */

import java.util.*;

public class Yahtzee {
    /**
     * Required main function for the whole program.
     *
     * @param args String array of arguments
     */
    public static void main(String[] args){
        Config c = new Config("..\\yahtzeeConfig.txt");
        Hand h = new Hand(c);
        ScoreSheet s = new ScoreSheet(h, c);

        turn(c.getRolls_per_round(), h, s);
    }

    /**
     * Method for one turn of a game of Yahtzee
     *
     * @param maxRolls int that shows the max number of rolls
     *                 in a hand
     * @param h Hand object that is being used for the current turn
     * @param s Scoresheet object that tracks the scoring for the
     *          current turn
     */
    public static void turn(int maxRolls, Hand h, ScoreSheet s){
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