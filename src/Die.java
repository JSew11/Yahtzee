// Joshua Seward
// February 2, 2021
// Description: A class representing a single die with a set number of sides. The
// dice is "rolled" when it is created and the value is kept within it.

import java.util.Random;

public class Die{
    private int sides = 6; // Number of sides for the dice
    private int value;

    // constructor "rolls" the die
    public Die(){
        rollDie();
    }

    // Method: getValue
    // Description: Return the value in the die
    // Inputs: None
    // Outputs: The value stored in the die
    public int getValue(){
        return value;
    }

    // Method: reRoll
    // Description: Randomize the value in the die ("roll" the die)
    // Inputs: None
    // Outputs: None
    public void rollDie(){
        Random rand = new Random();
        value = rand.nextInt(sides)+1;
    }
}
