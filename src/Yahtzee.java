/**
 * This class runs a game of Yahtzee using the settings from
 * an external config file.
 * No sources to cite.
 *
 * @author Joshua Seward
 * @version 2.2 3/7/21
 */

import java.util.*;

public class Yahtzee {
    /**
     * Required main function for the whole program.
     *
     * @param args String array of arguments
     */
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        String cont = "y";
        while(cont.equals("y")) {
            playYahtzee();
            System.out.print("Enter 'y' to play again: ");
            cont = in.nextLine();
        }
    }

    private static void playYahtzee(){
        Config c = new Config("..\\yahtzeeConfig.txt");
        Hand h = new Hand(c);
        ScoreSheet actual = new ScoreSheet(h, c);

        while(!gameOver(actual)){
            turn(c.getRolls_per_round(), h, c, actual);
        }
        System.out.println("-- Final ScoreCard --");
        actual.printSheet();
        actual.printScore();
    }

    /**
     * Method for one turn of a game of Yahtzee
     *
     * @param maxRolls - int that shows the max number of rolls
     *                   in a hand
     * @param c - Config object
     * @param h - Hand object that is being used for the current turn
     * @param actual - ScoreSheet object that tracks the score for the
     *                 current game
     */
    private static void turn(int maxRolls, Hand h, Config c, ScoreSheet actual){
        Scanner in = new Scanner(System.in);
        ScoreSheet possible = new ScoreSheet(h, c);

        System.out.print("Enter 's' to see your current scorecard: ");
        if(in.nextLine().equals("s")){
            System.out.println();
            System.out.print("-- Current Scores --");
            actual.printSheet();
        }

        int cur_roll = 1;
        h.newHand();
        h.printHand();

        while(cur_roll < maxRolls){
            System.out.print("Enter which dice you want to keep (y or n): ");
            String keep = in.nextLine();

            int reRoll = h.reRoll(keep);
            if(reRoll == 1)
                cur_roll++;
            else if(reRoll == 0)
                cur_roll = maxRolls;
            h.printHand();
        }
        h.sortPrint();
        possible.calcScores();
        System.out.println();
        System.out.print("-- Possible Scores --");
        possible.printSheet();
        updateScores(possible, actual);
    }

    private static boolean gameOver(ScoreSheet actual){
        for(int i = 0; i < actual.getLength(); ++i){
            if(actual.scoreAt(i) == -1)
                return false;
        }
        return true;
    }

    private static void updateScores(ScoreSheet possible, ScoreSheet actual){
        Scanner in = new Scanner(System.in);
        boolean scoreSet = false;
        while(!scoreSet) {
            System.out.print("Enter the line of the score you would like to keep (Integer from 1-"
                    + (actual.getLength()) + "): ");
            int lineNum = Integer.parseInt(in.nextLine());
            int score = possible.scoreAt(lineNum-1);
            if(score == -1) score = 0;
            if (actual.setScore(lineNum-1, score)){
                scoreSet = true;
            }
        }
    }
}