/**
 * This class computes and stores the values for the lower
 * section of a score sheet in a game of Yahtzee.
 * No sources to cite.
 *
 * @author Joshua Seward
 * @version 1.0 3/7/21
 */

public class LowerSection {
    private int length = 7;
    int score;
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
        int score = 0;
        lowerSection = new int[length];

        for(int i = 0; i < length; ++i)
            lowerSection[i] = -1;
    }

    /**
     * Method that calculates the lower section scores for the
     * current hand.
     */
    public void calcScores(){
        threeKindCalc();
        fourKindCalc();
        fullHouseCalc();
        smallStraightCalc();
        largeStraightCalc();
        if(isYahtzee()) lowerSection[5] = 50;
        chanceCalc();
    }

    /**
     * Method that sets the score for a specific line of the
     * upper section.
     *
     * @param line - the line to set the score in
     * @param score - the score to set at the given line
     */
    public boolean setScore(int line, int score){
        if(line >= 0 && line < length)
            if(lowerSection[line] == -1) {
                if (score == -1) lowerSection[line] = 0;
                else lowerSection[line] = score;
                return true;
            }
            else{
                System.out.println("There is already a score here (please enter another line 0-"
                        + (length-1) + ")");
                return false;
            }
        else{
            System.out.println("Invalid Lower Section line (please enter 0-"
                    + (length-1) + ")");
            return false;
        }
    }

    /**
     * Method that gives the score at the desired line
     *
     * @param line - the line you want the score at
     * @return - the score at the given line
     */
    public int getScore(int line){
        if(line >= 0 && line < length)
            return lowerSection[line];
        else{
            System.out.println("Invalid Lower Section line (please enter 0-"
                               + (length-1) + ")");
            return -1;
        }
    }

    /**
     * Method that returns the length of the upper section
     *
     * @return - returns the length of the upper section
     */
    public int getLength(){ return length;}

    /**
     * Method that calculates the score if there is a three
     * of a kind in the current hand.
     */
    private void threeKindCalc(){
        int sum = 0;
        for(int i = 0; i < hand.getMax_dice()-2; ++i){
            Die d1 = hand.dieAt(i);
            for(int j = i+1; j < hand.getMax_dice()-1; ++j){
                Die d2 = hand.dieAt(j);
                Die d3 = hand.dieAt(j+1);
                if(d1.getValue() == d2.getValue() &&
                   d1.getValue() == d3.getValue()){
                    sum = sumHand();
                }
            }
        }
        if(sum != 0)
            lowerSection[0] = sum;
    }

    /**
     * Method that calculates the score if there is a four
     * of a kind in the current hand.
     */
    private void fourKindCalc(){
        int sum = 0;
        for(int i = 0; i < hand.getMax_dice()-3; ++i){
            Die d1 = hand.dieAt(i);
            for(int j = i+1; j < hand.getMax_dice()-2; ++j){
                Die d2 = hand.dieAt(j);
                Die d3 = hand.dieAt(j+1);
                Die d4 = hand.dieAt(j+2);
                if(d1.getValue() == d2.getValue() &&
                        d1.getValue() == d3.getValue() &&
                        d1.getValue() == d4.getValue()){
                    sum = sumHand();
                }
            }
        }
        if(sum != 0)
            lowerSection[1] = sum;
    }

    /**
     * Method that calculates the score if there is a full
     * house in the current hand.
     */
    private void fullHouseCalc(){
        // check for the "3 - 2" case
        if(hand.dieAt(0).getValue() == hand.dieAt(1).getValue() &&
                hand.dieAt(0).getValue() == hand.dieAt(2).getValue() &&
                hand.dieAt(3).getValue() == hand.dieAt(4).getValue()){
            lowerSection[2] = 25;
        }
        // check for the "2 - 3" case
        else if(hand.dieAt(0).getValue() == hand.dieAt(1).getValue() &&
                hand.dieAt(2).getValue() == hand.dieAt(3).getValue() &&
                hand.dieAt(2).getValue() == hand.dieAt(4).getValue()){
            lowerSection[2] = 25;
        }
    }

    /**
     * Method that calculates the score if there is a small
     * straight in the current hand.
     */
    private void smallStraightCalc(){
        // check for the "first-4" case
        if(hand.dieAt(0).getValue() == hand.dieAt(1).getValue()-1 &&
                hand.dieAt(1).getValue() == hand.dieAt(2).getValue()-1 &&
                hand.dieAt(2).getValue() == hand.dieAt(3).getValue()-1){
            lowerSection[3] = 30;
        }
        // check for the "last-4" case
        else if(hand.dieAt(1).getValue() == hand.dieAt(2).getValue()-1 &&
                hand.dieAt(2).getValue() == hand.dieAt(3).getValue()-1 &&
                hand.dieAt(3).getValue() == hand.dieAt(4).getValue()-1){
            lowerSection[3] = 30;
        }
    }

    /**
     * Method that calculates the score if there is a large
     * straight in the current hand.
     */
    private void largeStraightCalc(){
        if (hand.dieAt(0).getValue() == hand.dieAt(1).getValue() - 1 &&
                hand.dieAt(1).getValue() == hand.dieAt(2).getValue() - 1 &&
                hand.dieAt(2).getValue() == hand.dieAt(3).getValue() - 1 &&
                hand.dieAt(3).getValue() == hand.dieAt(4).getValue() - 1) {
            lowerSection[4] = 40;
        }
    }

    /**
     * Method that determines whether the current hand is a yahtzee.
     *
     * @return boolean value that says whether or not the current
     *         hand is a yahtzee
     */
    private boolean isYahtzee() {
        int val = hand.dieAt(0).getValue();
        for (int i = 1; i < config.getNum_dice(); ++i) {
            if (val != hand.dieAt(i).getValue()) return false;
        }
        return true;
    }

    /**
     * Method that calculates and updates the chance line for the
     * lower section of a Yahtzee ScoreSheet
     */
    private void chanceCalc(){
        lowerSection[6] = sumHand();
    }

    /**
     * Method that adds all the dice in the current hand.
     *
     * @return int value that is the sum of all the dice in the
     *         current hand
     */
    private int sumHand(){
        int sum = 0;
        for(int i = 0; i < hand.getMax_dice(); ++i){
            sum += hand.dieAt(i).getValue();
        }
        return sum;
    }

    /**
     * Method that prints the score of the lower section
     */
    public void printLowerScore(){
        int sum = 0;
        for(int i = 0; i < length; ++i)
            sum += lowerSection[i];
        System.out.println("Lower Section Score = " + sum);
        score = sum;
    }

    /**
     * Method that gives the score of the lower section
     *
     * @return - score of the lower section
     */
    public int getLowerScore(){ return score;}
}
