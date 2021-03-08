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
    private int length;
    private int totalScore;
    private UpperSection upperSection;
    private LowerSection lowerSection;
    private Hand hand;
    private Config config;

    /**
     * Constructor that creates a new ScoreSheet object
     * and uses the given Hand and Config objects to
     * calculate and store the players' scores.
     *
     * @param hand   - Hand object that contains the dice for
     *               calculating the scores
     * @param config - Config object that contain the settings
     *               for the game
     */
    public ScoreSheet(Hand hand, Config config) {
        this.hand = hand;
        this.config = config;
        upperSection = new UpperSection(hand, config);
        lowerSection = new LowerSection(hand, config);
        length = upperSection.getLength() + lowerSection.getLength();
        totalScore = 0;
    }

    public void calcScores() {
        upperSection.calcScores();
        lowerSection.calcScores();
    }

    /**
     * Method that calculates and prints the possible
     * scores for the current hand.
     */
    public void printSheet() {
        int upperLength = upperSection.getLength();
        int lowerLength = lowerSection.getLength();
        int totalLength = upperLength + lowerLength;
        System.out.println();
        System.out.println("-- Upper Section --");
        for (int i = 0; i < upperLength; ++i) {
            System.out.print((i + 1) + "- " + (i + 1) + "'s Score: ");
            int score = upperSection.getScore(i);
            if (score == -1)
                System.out.println();
            else System.out.println(score);
        }
        System.out.println("-- Lower Section --");
        System.out.print((upperLength + 1) + "- Three of a Kind: ");
        if (lowerSection.getScore(0) == -1)
            System.out.println();
        else System.out.println(lowerSection.getScore(0));
        System.out.print((upperLength + 2) + "- Four of a Kind: ");
        if (lowerSection.getScore(1) == -1)
            System.out.println();
        else System.out.println(lowerSection.getScore(1));
        System.out.print((upperLength + 3) + "- Full House: ");
        if (lowerSection.getScore(2) == -1)
            System.out.println();
        else System.out.println(lowerSection.getScore(2));
        System.out.print((upperLength + 4) + "- Small Straight: ");
        if (lowerSection.getScore(3) == -1)
            System.out.println();
        else System.out.println(lowerSection.getScore(3));
        System.out.print((upperLength + 5) + "- Large Straight: ");
        if (lowerSection.getScore(4) == -1)
            System.out.println();
        else System.out.println(lowerSection.getScore(4));
        System.out.print((upperLength + 6) + "- Yahtzee: ");
        if (lowerSection.getScore(5) == -1)
            System.out.println();
        else System.out.println(lowerSection.getScore(5));
        System.out.print((upperLength + 7) + "- Chance: ");
        if (lowerSection.getScore(6) == -1)
            System.out.println();
        else System.out.println(lowerSection.getScore(6));
        System.out.println();
    }

    /**
     * Method that returns the length of the ScoreSheet
     *
     * @return - returns the length of the ScoreSheet
     */
    public int getLength() {
        return length;
    }

    public int scoreAt(int line) {
        if (line >= 0 && line < upperSection.getLength())
            return upperSection.getScore(line);
        else if (line >= upperSection.getLength() && line < length)
            return lowerSection.getScore(line - upperSection.getLength());
        else {
            System.out.println("Invalid scorecard line (enter a line from 1-"
                    + length + ")");
            return -2;
        }

    }

    public boolean setScore(int line, int score) {
        if (line >= 0 && line < upperSection.getLength())
            return (upperSection.setScore(line, score));
        else if (line >= upperSection.getLength() && line < length) {
            return (lowerSection.setScore((line - upperSection.getLength()), score));
        } else {
            System.out.println("Invalid scorecard line (enter a line from 1-"
                    + (length) + ")");
            return false;
        }
    }

    public void printScore() {
        upperSection.printUpperScore();
        lowerSection.printLowerScore();
        int totalScore = upperSection.getUpperScore() + lowerSection.getLowerScore();
        System.out.println("Total Score = " + totalScore);
    }
}
