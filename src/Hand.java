/**
 * This class takes a variable number of dice and forms a hand
 * for the game Yahtzee. It can reRoll, print, and sort the hand
 * to be used in the other aspects of the game.
 * No sources to cite.
 *
 * @author Joshua Seward
 * @version 2.1 2/21/21
 */

import java.util.ArrayList;

public class Hand {
    private ArrayList<Die> hand;
    private int max_dice;
    private Config c;

    /**
     * Constructor that creates a new Hand object by adding a
     * variable number of dice with a variable number of sides
     * (determined buy the config file).
     *
     * @param c Config object containing the settings for the
     *          current game
     */
    public Hand(Config c){
        this.c = c;
        max_dice = c.getNum_dice();
        hand = new ArrayList<Die>();

        for(int i = 0; i < max_dice; ++i)
            hand.add(new Die(c.getDie_sides()));
    }

    /**
     * Method that rolls each die in the hand.
     */
    public void newHand(){
        for(int i = 0; i < max_dice; ++i){
            hand.get(i).rollDie();
        }
    }

    /**
     * Method that re-rolls specific dice in a hand according to
     * the input string.
     *
     * @param keep String that is used to determine which dice
     *             to re-roll
     * @return int value that reflects the different "states" of
     *         the re-roll:
     *            0 = player wants to keep all of their dice
     *           -1 = invalid input of reRoll string
     *            1 = dice were re-rolled successfully
     */
    public int reRoll(String keep){
        String keepAll = "";
        for(int i = 0; i < max_dice; ++i){
            keepAll = keepAll + "y";
        }

        // check if the player wants to keep all their dice
        if(keep.equals(keepAll)){
            return 0;
        }
        // check for invalid input string size
        if(keep.length() < max_dice){
            System.out.println("Please input y or n for each die.");
            return -1;
        }
        for(int i = 0; i  < max_dice; ++i){
            // check for invalid inputs
            if(keep.charAt(i) != 'y' && keep.charAt(i) != 'n'){
                System.out.println("Invalid input for die " + i +
                        " (Please input y or n for each die).");
                return -1;
            }
            // check if reRoll is needed
            else if(keep.charAt(i) == 'n'){
                hand.get(i).rollDie();
            }
        }
        // dice were re rolled successfully
        return 1;
    }

    /**
     * Method that prints the values of the dice in the
     * hand to the console
     */
    public void printHand(){
        System.out.print("Your roll was:");
        for(int i = 0; i < max_dice; ++i){
            System.out.print(" " + hand.get(i).getValue());
        }
        System.out.println();
    }

    /**
     * Method that sorts then prints the dice in the hand
     * according to their values
     */
    public void sortPrint(){
        System.out.print("Sorted Hand:");
        // sort through the hand, printing the smallest each pass
        for(int i = 0; i < max_dice; ++i){
            Die smallest = hand.get(i);
            int index = i;
            // find the smallest die left in the hand
            for(int j = i+1; j < max_dice; ++j){
                if(hand.get(j).getValue() < smallest.getValue()){
                    smallest = hand.get(j);
                    index = j;
                }
            }
            // swap the smallest value to the front
            hand.set(index, hand.get(i));
            hand.set(i, smallest);
            // print the smallest value
            System.out.print(" " + hand.get(i).getValue());
        }
        System.out.println();
    }

    /**
     * Method that returns the Die object at a given index of
     * the current hand object
     *
     * @param index String containing the name of the config
     *                 file
     * @return Die object at the given index
     */
    public Die dieAt(int index){
        return hand.get(index);
    }

    /**
     * Getter method for the max number of dice in the hand
     *
     * @return int value for the maximum number of dice in the
     * current hand
     */
    public int getMax_dice() {
        return max_dice;
    }
}