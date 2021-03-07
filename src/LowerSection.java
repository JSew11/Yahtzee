/**
 * This class computes and stores the values for the lower
 * section of a score sheet in a game of Yahtzee.
 * No sources to cite.
 *
 * @author Joshua Seward
 * @version 1.0 3/7/21
 */

public class LowerSection {
    private int length;
    private Hand hand;
    private Config config;
    private int lowerSection[];

    /**
     * Constructor that creates a new LowerSection object
     * and uses the given Hand and Config objects to
     * calculate and store the players' scores.
     *
     * @param hand Hand object that contains the dice for
     *             calculating the scores
     * @param config Config object that contain the settings
     *               for the game
     */
    public LowerSection(Hand hand, Config config){
        this.hand = hand;
        this.config = config;
        // initialize the length variable
        // initialize the lowerSection array to have length as its size
        lowerSection = new int[length];
    }

}
