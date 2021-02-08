// Joshua Seward
// February 2, 2021
// Description: Representation of a Yahtzee score sheet.

public class ScoreSheet {
    private int possibleScore[];
    private int actualScore[];
    private Hand hand;
    int sheetLength = 13;

    public ScoreSheet(Hand h){
        possibleScore = new int[sheetLength];
        actualScore = new int[sheetLength];
        hand = h;

        for(int i = 0; i < sheetLength; ++i){
            possibleScore[i] = 0;
            actualScore[i] = -1;
        }
    }

    // Method: printPossibleSheet
    // Description: Calculates and prints scores for PossibleSheet
    // Inputs: None
    // Outputs: None
    public void possibleScore(){
        // calculate possible scores
        pScoreCalc();
        // print upper section
        System.out.println("-- Upper Section --");
        System.out.println("1's line Score: " + possibleScore[0]);
        System.out.println("2's line Score: " + possibleScore[1]);
        System.out.println("3's line Score: " + possibleScore[2]);
        System.out.println("4's line Score: " + possibleScore[3]);
        System.out.println("5's line Score: " + possibleScore[4]);
        System.out.println("6's line Score: " + possibleScore[5]);
        // print lower section
        System.out.println("-- Lower Section --");
        System.out.println("Three in a Row line Score: " + possibleScore[6]);
        System.out.println("Four in a Row line Score: " + possibleScore[7]);
        System.out.println("Full House line Score: " + possibleScore[8]);
        System.out.println("Small Straight line Score: " + possibleScore[9]);
        System.out.println("Large Straight line Score: " + possibleScore[10]);
        System.out.println("YAHTZEE line Score: " + possibleScore[11]);
        System.out.println("Chance line Score: " + possibleScore[12]);
    }

    // Method: pScoreCalc
    // Description: Calculates the possible score combinations for the current
    // hand and updates possibleScore
    // Inputs: None
    // Outputs: None
    private void pScoreCalc(){
        // calculate the upper section scores (number lines)
        numCalc();
        // calculate the lower section scores
        threeKindCalc();
        fourKindCalc();
        fullHouseCalc();
        smallStraightCalc();
        largeStraightCalc();
        yahtzeeCalc();
        chanceCalc();
    }

    // Method: numCalc
    // Description: Calculates the possible score combinations for the number
    // lines and updates possibleScore
    // Inputs: None
    // Outputs: None
    private void numCalc(){
        for(int i = 0; i < 6; ++i){
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

    // Method: threeKindCalc
    // Description: Checks hand for a 3 of a kind and updates possibleScore
    // Inputs: None
    // Outputs: None
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
        if(actualScore[6] == -1)
            possibleScore[6] = sum;
        else
            possibleScore[6] = -1;
    }

    // Method: fourKindCalc
    // Description: Checks hand for a 4 of a kind and updates possibleScore
    // Inputs: None
    // Outputs: None
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
        if(actualScore[7] == -1)
            possibleScore[7] = sum;
        else
            possibleScore[7] = -1;
    }

    // Method: fullHouseCalc
    // Description: Checks hand for a full house and updates possibleScore
    // Inputs: None
    // Outputs: None
    private void fullHouseCalc(){
        // check for the "3 - 2" case
        if(hand.dieAt(0).getValue() == hand.dieAt(1).getValue() &&
                hand.dieAt(0).getValue() == hand.dieAt(2).getValue() &&
                hand.dieAt(3).getValue() == hand.dieAt(4).getValue()){
            if(actualScore[8] == -1)
                possibleScore[8] = 25;
            else
                possibleScore[8] = -1;
        }
        // check for the "2 - 3" case
        else if(hand.dieAt(0).getValue() == hand.dieAt(1).getValue() &&
                hand.dieAt(2).getValue() == hand.dieAt(3).getValue() &&
                hand.dieAt(2).getValue() == hand.dieAt(4).getValue()){
            if(actualScore[8] == -1)
                possibleScore[8] = 25;
            else
                possibleScore[8] = -1;
        }
    }

    // Method: smallStraightCalc
    // Description: Checks hand for a small straight and updates possibleScore
    // Inputs: None
    // Outputs: None
    private void smallStraightCalc(){
        // check for the "first-4" case
        if(hand.dieAt(0).getValue() == hand.dieAt(1).getValue()-1 &&
                hand.dieAt(1).getValue() == hand.dieAt(2).getValue()-1 &&
                hand.dieAt(2).getValue() == hand.dieAt(3).getValue()-1){
            if(actualScore[9] == -1)
                possibleScore[9] = 30;
            else
                possibleScore[9] = -1;
        }
        // check for the "last-4" case
        else if(hand.dieAt(1).getValue() == hand.dieAt(2).getValue()-1 &&
                hand.dieAt(2).getValue() == hand.dieAt(3).getValue()-1 &&
                hand.dieAt(3).getValue() == hand.dieAt(4).getValue()-1){
            if(actualScore[9] == -1)
                possibleScore[9] = 30;
            else
                possibleScore[9] = -1;
        }
    }

    // Method: largeStraightCalc
    // Description: Checks hand for a large straight and updates possibleScore
    // Inputs: None
    // Outputs: None
    private void largeStraightCalc(){
        if(hand.dieAt(0).getValue() == hand.dieAt(1).getValue()-1 &&
                hand.dieAt(1).getValue() == hand.dieAt(2).getValue()-1 &&
                hand.dieAt(2).getValue() == hand.dieAt(3).getValue()-1 &&
                hand.dieAt(3).getValue() == hand.dieAt(4).getValue()-1){
            if(actualScore[10] == -1)
                possibleScore[10] = 40;
            else
                possibleScore[10] = -1;
        }
    }

    // Method: yahtzeeCalc
    // Description: Checks hand for a Yahtzee and and updates possibleScore
    // Inputs: None
    // Outputs: None
    private void yahtzeeCalc(){
        if(hand.dieAt(0).getValue() == hand.dieAt(1).getValue() &&
                hand.dieAt(0).getValue() == hand.dieAt(2).getValue() &&
                hand.dieAt(0).getValue() == hand.dieAt(3).getValue() &&
                hand.dieAt(0).getValue() == hand.dieAt(4).getValue()){
            if(actualScore[11] == -1)
                possibleScore[11] = 50;
            else
                possibleScore[11] = -1;
        }
    }

    // Method: chanceCalc
    // Description: Calculates Chance score and updates possibleScore
    // Inputs: None
    // Outputs: None
    public void chanceCalc(){
        int sum = sumHand();
        if(actualScore[12] == -1)
            possibleScore[12] = sum;
        else
            possibleScore[12] = -1;
    }

    // Method: sumHand
    // Description: Returns the sum of all the dice in the hand
    // Inputs: None
    // Outputs: Returns the sum of all the dice in the hand
    private int sumHand(){
        int sum = 0;
        for(int i = 0; i < hand.getMax_dice(); ++i){
            sum += hand.dieAt(i).getValue();
        }
        return sum;
    }
}
