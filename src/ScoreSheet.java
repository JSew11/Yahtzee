/**
 * This class computes the values for a score sheet in a game
 * of Yahtzee. It automatically is updated for dice with more
 * or less than 6 sides.
 * No sources to cite.
 *
 * @author Joshua Seward
 * @version 3.1 3/7/21
 */

public class ScoreSheet {
    private UpperSection upperSection;
    private LowerSection lowerSection;
    private Hand hand;
    private Config config;

    /**
     * Constructor that creates a new ScoreSheet object
     * and uses the given Hand and Config objects to
     * calculate and store the players' scores.
     *
     * @param hand - Hand object that contains the dice for
     *               calculating the scores
     * @param config - Config object that contain the settings
     *                 for the game
     */
    public ScoreSheet(Hand hand, Config config){
        this.hand = hand;
        this.config = config;
        upperSection = new UpperSection(hand, config);
        lowerSection = new LowerSection(hand, config);
    }

    /**
     * Method that calculates and prints the possible
     * scores for the current hand.
     */
    public void possibleScore(){
        int upperLength = upperSection.getLength();
        int lowerLength = lowerSection.getLength();
        int totalLength = upperLength + lowerLength;

        upperSection.calcScores();
        lowerSection.calcScores();
        System.out.println("-- Upper Section --");
        for(int i = 0; i < upperLength; ++i){
            int score = upperSection.getScore(i);
            if(score != -1)
                System.out.println(i + "- " + (i+1) + "'s Score: " + score);
        }
        System.out.println("-- Lower Section --");
        System.out.println(upperLength + "- Three of a Kind: " +
                            lowerSection.getScore(0));
        System.out.println((upperLength+1) + "- Four of a Kind: " +
                            lowerSection.getScore(1));
        System.out.println((upperLength+2) + "- Full House: " +
                            lowerSection.getScore(2));
        System.out.println((upperLength+3) + "- Small Straight: " +
                            lowerSection.getScore(3));
        System.out.println((upperLength+4) + "- Large Straight: " +
                            lowerSection.getScore(4));
        System.out.println((upperLength+5) + "- Yahtzee: " +
                            lowerSection.getScore(5));
        System.out.println((upperLength+6) + "- Chance: " +
                            lowerSection.getScore(6));
    }
}
