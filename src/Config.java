/**
 * This class reads the settings from an external config file and
 * changes the settings of the game accordingly. It also prompts
 * the user if they would like to change these settings, and if
 * so, the changes are saved to the config file.
 * No sources to cite.
 *
 * @author Joshua Seward
 * @version 2.1 2/21/21
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.*;

public class Config {
    private int die_sides;
    private int num_dice;
    private int rolls_per_round;
    /**
     * Constructor that creates a new Config object using the
     * data of a provided config file, and changes the config
     * settings within the file if the user chooses.
     *
     * @param filename String containing the name of the config
     *                 file
     */
    public Config(String filename){
        Scanner in = new Scanner(System.in);
        getConfig(filename);
        System.out.print("Enter 'y' if you would like to change the game configuration: ");
        String cont = in.next();
        if(cont.equals("y")){ setConfig(filename, in);}
    }

    /**
     * Reads the settings from the given config file
     *
     * @param filename String containing the name of the config
     *                 file
     */
    private void getConfig(String filename){
        try{
            Scanner inFile = new Scanner(new File(filename));
            die_sides = inFile.nextInt();
            num_dice = inFile.nextInt();
            rolls_per_round = inFile.nextInt();

            System.out.println("You are playing with " + num_dice + " "
                    + die_sides + " sided dice.");
            System.out.println("There are " + rolls_per_round + " rolls per round.");
            System.out.println();
        } catch(FileNotFoundException e){
            System.out.println("Config file was not found");
        }
    }

    /**
     * CHanges the settings contained in the config file
     *
     * @param filename String containing the name of the
     *                 config file
     * @param in Scanner to read the input from the command
     *           prompt
     */
    private void setConfig(String filename, Scanner in){
        try{
            // read input from console
            PrintStream outFile = new PrintStream(new File(filename));
            System.out.print("Enter the number of sides of each die: ");
            die_sides = in.nextInt();
            System.out.print("Enter the number of dice: ");
            num_dice = in.nextInt();
            System.out.print("Enter the number of rolls in a round: ");
            rolls_per_round = in.nextInt();

            // update config file
            outFile.println(die_sides);
            outFile.println(num_dice);
            outFile.println(rolls_per_round);
        } catch(FileNotFoundException e){
            System.out.println("Config file was not found.");
        }
    }

    /**
     * Getter for the number of sides for a die
     *
     * @return number of sides for a die (stored in
     *         the config file)
     */
    public int getDie_sides() {
        return die_sides;
    }

    /**
     * Getter for the number of dice in a hand
     *
     * @@return number of dice in a hand (stored in
     *          the config file)
     */
    public int getNum_dice() {
        return num_dice;
    }

    /**
     * Getter for the number of rolls per round
     *
     * @@return number of rolls per round (stored
     *          in the config file)
     */
    public int getRolls_per_round() {
        return rolls_per_round;
    }
}
