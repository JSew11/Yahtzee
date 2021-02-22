/**
 * This class computes the values for a score sheet in a game
 * of Yahtzee. It automatically is updated for dice with more
 * or less than 6 sides.
 * No sources to cite.
 *
 * @author Joshua Seward
 * @version 2.1 2/21/21
 */

public class ScoreSheet {
    private int possibleScore[];
    private int actualScore[];
    private Hand hand;
    private Config c;
    private int sheetLength;

    /**
     * Constructor that creates a new ScoreSheet object
     * and uses the given Hand and Config objects to
     * calculate and store the players' scores.
     *
     * @param h Hand object that contains the dice for
     *          calculating the scores
     * @param c Config object that contain the settings
     *          for the game
     */
    public ScoreSheet(Hand h, Config c){
        this.c = c;
        sheetLength = c.getDie_sides() + 7;
        possibleScore = new int[sheetLength];
        actualScore = new int[sheetLength];
        hand = h;

        for(int i = 0; i < sheetLength; ++i){
            possibleScore[i] = 0;
            actualScore[i] = -1;
        }
    }

    /**
     * Method that calculates and prints the possible
     * scores for the current hand.
     */
    public void possibleScore(){
        // calculate possible scores
        pScoreCalc();
        // print upper section
        System.out.println("-- Upper Section --");
        for(int i = 0; i < c.getDie_sides(); ++i){
            System.out.println((i+1) + "'s line Score: " + possibleScore[i]);
        }
        // print lower section
        System.out.println("-- Lower Section --");
        System.out.println("Three of a Kind line Score: " + possibleScore[c.getDie_sides()]);
        System.out.println("Four of a Kind line Score: " + possibleScore[c.getDie_sides() + 1]);
        System.out.println("Full House line Score: " + possibleScore[c.getDie_sides() + 2]);
        System.out.println("Small Straight line Score: " + possibleScore[c.getDie_sides() + 3]);
        System.out.println("Large Straight line Score: " + possibleScore[c.getDie_sides() + 4]);
        System.out.println("YAHTZEE line Score: " + possibleScore[c.getDie_sides() + 5]);
        System.out.println("Chance line Score: " + possibleScore[c.getDie_sides() + 6]);
    }

    /**
     * Method that calculates the possible scores for
     * the current hand.
     */
    private void pScoreCalc(){
        // calculate the upper section scores (number lines)
        numCalc();
        // calculate the lower section scores
        threeKindCalc();
        fourKindCalc();
        fullHouseCalc();
        smallStraightCalc();
        largeStraightCalc();
        if(yahtzeeCalc()) possibleScore[c.getDie_sides()+5] = 50;
        chanceCalc();
    }

    /**
     * Method that calculates the scores for each of the
     * "number" lines of the ScoreSheet based on the
     * current hand.
     */
    private void numCalc(){
        for(int i = 0; i < c.getDie_sides(); ++i){
            int sum = 0;
            for(int j = 0; j < hand.getMax_dice(); ++j) {
                if (hand.dieAt(j).getValue() == i+1)
                    sum += i+1;
            }
            if(actualScore[i] == -1)
                possibleScore[i] = sum;
            else
                possibleScore[i] = -1;
        }
    }

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
        if(actualScore[c.getDie_sides()] == -1)
            possibleScore[c.getDie_sides()] = sum;
        else
            possibleScore[c.getDie_sides()] = -1;
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
        if(actualScore[c.getDie_sides()+1] == -1)
            possibleScore[c.getDie_sides()+1] = sum;
        else
            possibleScore[c.getDie_sides()+1] = -1;
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
            if(actualScore[c.getDie_sides()+2] == -1)
                possibleScore[c.getDie_sides()+2] = 25;
            else
                possibleScore[c.getDie_sides()+2] = -1;
        }
        // check for the "2 - 3" case
        else if(hand.dieAt(0).getValue() == hand.dieAt(1).getValue() &&
                hand.dieAt(2).getValue() == hand.dieAt(3).getValue() &&
                hand.dieAt(2).getValue() == hand.dieAt(4).getValue()){
            if(actualScore[c.getDie_sides()+2] == -1)
                possibleScore[c.getDie_sides()+2] = 25;
            else
                possibleScore[c.getDie_sides()+2] = -1;
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
            if(actualScore[c.getDie_sides()+3] == -1)
                possibleScore[c.getDie_sides()+3] = 30;
            else
                possibleScore[c.getDie_sides()+3] = -1;
        }
        // check for the "last-4" case
        else if(hand.dieAt(1).getValue() == hand.dieAt(2).getValue()-1 &&
                hand.dieAt(2).getValue() == hand.dieAt(3).getValue()-1 &&
                hand.dieAt(3).getValue() == hand.dieAt(4).getValue()-1){
            if(actualScore[c.getDie_sides()+3] == -1)
                possibleScore[c.getDie_sides()+3] = 30;
            else
                possibleScore[c.getDie_sides()+3] = -1;
        }
    }

    /**
     * Method that calculates the score if there is a large
     * straight in the current hand.
     */
    private void largeStraightCalc(){
        if(hand.dieAt(0).getValue() == hand.dieAt(1).getValue()-1 &&
                hand.dieAt(1).getValue() == hand.dieAt(2).getValue()-1 &&
                hand.dieAt(2).getValue() == hand.dieAt(3).getValue()-1 &&
                hand.dieAt(3).getValue() == hand.dieAt(4).getValue()-1){
            if(actualScore[c.getDie_sides()+4] == -1)
                possibleScore[c.getDie_sides()+4] = 40;
            else
                possibleScore[c.getDie_sides()+4] = -1;
        }
    }

    /**
     * Method that determines whether the current hand is a yahtzee.
     *
     * @return boolean value that says whether or not the current
     *         hand is a yahtzee
     */
    private boolean yahtzeeCalc() {
        int val = hand.dieAt(0).getValue();
        for (int i = 1; i < c.getNum_dice(); ++i) {
            if (val != hand.dieAt(i).getValue()) return false;
        }
        return true;
    }

    /**
     * Method that calculates the score for the chance line of the
     * ScoreSheet.
     */
    public void chanceCalc(){
        int sum = sumHand();
        if(actualScore[c.getDie_sides()+6] == -1)
            possibleScore[c.getDie_sides()+6] = sum;
        else
            possibleScore[c.getDie_sides()+6] = -1;
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
}
