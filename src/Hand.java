// Joshua Seward
// February 2, 2021
// Description: Representation of a hand in yahtzee (a set of 5 dice).

import java.util.ArrayList;
import java.util.*;


public class Hand {
    private ArrayList<Die> hand;
    private int max_dice;

    // constructor generates a hand of 5 dice by initiating the 5 Die objects
    public Hand(){
        max_dice = 5;
        hand = new ArrayList<Die>();

        for(int i = 0; i < max_dice; ++i)
            hand.add(new Die());
    }

    // Method: newHand
    // Description: Rolls every die in the hand
    // Inputs: None
    // Outputs:None
    public void newHand(){
        for(int i = 0; i < max_dice; ++i){
            hand.get(i).rollDie();
        }
    }

    // Method: reRoll
    // Description: Re-rolls the hand of dice according to the input string.
    // Inputs: String of 'y' and 'n' chars that tell which Die objects should
    // be re-rolled
    // Outputs: None
    public boolean reRoll(){
        Scanner in = new Scanner(System.in);

        System.out.print("Enter which dice you want to keep (y or n): ");
        String keep = in.nextLine();

        // check for invalid input string size
        if(keep.length() < max_dice){
            System.out.println("Please input y or n for each die.");
            return false;
        }

        for(int i = 0; i  < max_dice; ++i){
            // check for invalid inputs
            if(keep.charAt(i) != 'y' && keep.charAt(i) != 'n'){
                System.out.println("Invalid input for die " + i +
                        " (Please input y or n for each die).");
                return false;
            }
            // check if reRoll is needed
            else if(keep.charAt(i) == 'n'){
                hand.get(i).rollDie();
            }
        }
        return true;
    }

    // Method: printHand
    // Description: Prints the values of the dice in the Hand
    // Inputs: None
    // Outputs: None
    public void printHand(){
        System.out.print("Your roll was:");
        for(int i = 0; i < max_dice; ++i){
            System.out.print(" " + hand.get(i).getValue());
        }
        System.out.println();
    }

    // Method: sortPrint
    // Description: Sorts then prints the values of the dice in the Hand
    // Inputs: None
    // Outputs: None
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

    // Method: dieAt
    // Description: Returns the die at the given index
    // Inputs: Index of desired die
    // Outputs: Die at the given index
    public Die dieAt(int index){
        return hand.get(index);
    }

    // Method: getMax_dice
    // Description: Returns maximum number of dice in the hand
    // Inputs: None
    // Outputs: Maximum number of dice in the hand
    public int getMax_dice() {
        return max_dice;
    }
}