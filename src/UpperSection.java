/**
 * This class computes and stores the values for the upper
 * section of a score sheet in a game of Yahtzee.
 * No sources to cite.
 *
 * @author Joshua Seward
 * @version 1.0 3/7/21
 */

public class UpperSection {
    private int length;
    private Hand hand;
    private Config config;
    private int upperSection[];

    /**
     * Constructor that creates a new UpperSection object
     * and uses the given Hand and Config objects to
     * calculate and store the players' scores.
     *
     * @param hand Hand object that contains the dice for
     *             calculating the scores
     * @param config Config object that contain the settings
     *               for the game
     */
    public UpperSection(Hand hand, Config config){
        this.config = config;
        this.hand = hand;
        length = config.getDie_sides();
        upperSection = new int[length];

        for(int i: upperSection)
            i = -1;
    }

    /**
     * Method that calculates the upper section scores for the
     * current hand.
     */
    public void calcScores(){
        for(int i = 0; i < length; ++i){
            int sum = 0;
            for(int j = 0; j < config.getNum_dice(); ++j){
                if(hand.dieAt(j).getValue() == i+1)
                    sum += i+1;
            }
            upperSection[i] = sum;
        }
    }

    /**
     * Method that sets the score for a specific line of the
     * upper section.
     *
     * @param line - the line to set the score in
     * @param score - the score to set at the given line
     */
    public void setScore(int line, int score){ upperSection[line] = score;}

    /**
     * Method that gives the score at the desired line
     *
     * @param line - the line you want the score at
     * @return - the score at the input line
     */
    public int getScore(int line){ return upperSection[line];}

    /**
     * Method that returns the length of the upper section
     *
     * @return - returns the length of the upper section
     */
    public int getLength(){ return length;}
}
