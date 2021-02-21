// Joshua Seward
// February 21, 2021
// Description: Reads a configuration file and prompts the
// user to update the number of dice, number of sides per die,
// and number of rolls per round in a yahtzee game.

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.*;

public class Config {
    private int die_sides;
    private int num_dice;
    private int rolls_per_round;

    public Config(String filename){
        Scanner in = new Scanner(System.in);
        getConfig(filename);
        System.out.print("Enter 'y' if you would like to change the game configuration: ");
        String cont = in.next();
        if(cont.equals("y")){ setConfig(filename, in);}
    }

    // Method: getConfig
    // Description: Gets the config settings from an external file.
    // Inputs: Config file filename
    // Outputs:None
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

    // Method: setConfig
    // Description: Reads inputs from the user and updates the config file
    // to the desired settings.
    // Inputs: Config file filename / command prompt scanner
    // Outputs:None
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

    // Method: getDie_sides
    // Description: Returns number of sides of each die
    // Inputs: None
    // Outputs: Returns number of sides for each die
    public int getDie_sides() {
        return die_sides;
    }

    // Method: getNum_dice
    // Description: Returns number of dice in one hand
    // Inputs: None
    // Outputs: Returns number of dice in one hand
    public int getNum_dice() {
        return num_dice;
    }

    // Method: getRolls_per_round
    // Description: Returns number of rolls in a round
    // Inputs: None
    // Outputs: Returns number of rolls in a round
    public int getRolls_per_round() {
        return rolls_per_round;
    }
}
