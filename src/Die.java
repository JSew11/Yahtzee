/**
 * This class represents a single die with a varying number of sides
 * to be used in a game of Yahtzee.
 * No sources to cite.
 *
 * @author Joshua Seward
 * @version 2.1 2/21/21
 */
import java.util.Random;

public class Die{
    private int sides; // Number of sides for the dice
    private int value;

    /**
     * Constructor that creates a new Die object with
     * 'numSides' sides.
     *
     * @param numSides int for the number of sides of this
     *                 particular die object
     */
    public Die(int numSides){
        sides = numSides;
        rollDie();
    }

    /**
     * Getter for the value of a die
     *
     * @return value of the die object
     */
    public int getValue(){
        return value;
    }

    /**
     * Rolls the die by selecting a random value between
     * 1 and the number of sides of the die.
     */
    public void rollDie(){
        Random rand = new Random();
        value = rand.nextInt(sides)+1;
    }
}
