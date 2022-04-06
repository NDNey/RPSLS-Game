package com.rpsls;

public enum Choice {

    ROCK, PAPER, SCISSORS, LIZARD, SPOCK;

   public static Choice get(String playerChoice) {
        Choice choice = null;

        switch (playerChoice.toLowerCase()){
            case "r":
                choice = ROCK;
                break;
            case "p" :
                choice = PAPER;
                break;
            case "s":
                choice = SCISSORS;
                break;
            case "l":
                choice = LIZARD;
                break;
            case "x":
                choice = SPOCK;
                break;
        }
        return choice;
    }

    public static int compare(Choice playerChoice, Choice botChoice) {
        int result = 0;

        int[][] comparasionMatrix =  //Matrix will help decide case win, lose, tie.
                {{0, 2, 1, 1, 2}, // rock
                        {1, 0, 2, 2, 1}, // paper
                        {2, 1, 0, 1, 2}, // scissors
                        {2, 1, 2, 0, 1},  // lizard
                        {1, 2, 1, 2, 0}}; // spock

        int botChoiceIndex = Choice.valueOf(botChoice.toString()).ordinal();
        int playerChoiceIndex = Choice.valueOf(playerChoice.toString()).ordinal();

        result = comparasionMatrix[playerChoiceIndex][botChoiceIndex];


        return result;
    }
}
