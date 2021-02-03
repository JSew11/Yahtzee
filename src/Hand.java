// Joshua Seward
// February 2, 2021
// Description: Representation of a hand in yahtzee (a set of 5 dice).

public class Hand {
    private Die d1;
    private Die d2;
    private Die d3;
    private Die d4;
    private Die d5;

    // constructor generates a hand of 5 dice by initiating the 5 Die objects
    public Hand(){
        d1 = new Die();
        d2 = new Die();
        d3 = new Die();
        d4 = new Die();
        d5 = new Die();
    }

    // Method: reRoll
    // Description: Re-rolls the hand of dice according to the input string.
    // Inputs: String of 'y' and 'n' chars that tell which Die objects should
    // be re-rolled
    // Outputs: None
    public void reRoll(String keep){
        if(keep.charAt(0) == 'n') d1.rollDie();
        if(keep.charAt(1) == 'n') d2.rollDie();
        if(keep.charAt(2) == 'n') d3.rollDie();
        if(keep.charAt(3) == 'n') d4.rollDie();
        if(keep.charAt(4) == 'n') d5.rollDie();
    }

    // Method: print_Hand
    // Description: Prints the values of the dice in the Hand
    // Inputs: None
    // Outputs: None
    public void printHand(){
        System.out.println("Your roll was: " + d1.getValue() + " " +
                d2.getValue() + " " + d3.getValue() + " " +
                d4.getValue() + " " + d5.getValue());
    }
}
