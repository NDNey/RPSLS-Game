package com.rpsls.app;

import com.rpsls.Choice;
import com.rpsls.Player;

import static com.rpsls.Choice.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class Game {

    private final Scanner scanner = new Scanner(System.in);
    private static final String bannerPath = "data/banner2.txt";
    private static Player bot = new Player(1, "Bot");
    private int playerWins = 0;
    private  int cpuWins = 0;
    Choice choice;
    public static final String ANSI_RED = "\u001B[41m";
    public static final String ANSI_GREEN = "\u001B[42m";
    public static final String ANSI_YELLOW = "\u001B[43m";
    public static final String ANSI_CRAN = "\u001B[46m";
    public static final String ANSI_RESET = "\u001B[0m";

    public void execute() {
        welcome();
        prompForRules();
        while (playerWins < 5 && cpuWins < 5) {
            Choice playerChoice = prompForChoice();
            Choice botChoice = bot.randomChoice();
            displayGesture(playerChoice,botChoice);
            win(playerChoice, botChoice);

            System.out.println("Score: " + "Player: " + playerWins +" || " +  "Bot: " + cpuWins);
            if (playerWins == 5) {

                System.out.println("You are the first to 5 points.  You win the match!");
                String playerWins = "data/youwin.txt"; //you win ascii banner art
                try {
                    String youWinArt = Files.readString(Path.of(playerWins));
                    System.out.println(youWinArt);
                }
                catch (IOException e){
                    e.printStackTrace();
                }

            }
            else if (cpuWins == 5){

                System.out.println("Bot is the first to 5 points.  Bot wins the match!");
                String botWins = "data/cpuwin.txt"; //cpu wins ascii banner
                try {
                    String botWinArt = Files.readString(Path.of(botWins));
                    System.out.println(botWinArt);
                }
                catch (IOException e){
                    e.printStackTrace();
                }

            }
        }
    }

    private int comparasionMatrix(Choice player, Choice bot) {
        int result = 0;

        int[][] comparasionMatrix =  //Matrix will help decide case win, lose, tie.
                {{0, 2, 1, 1, 2}, // rock
                {1, 0, 2, 2, 1}, // paper
                {2, 1, 0, 1, 2}, // scissors
                {2, 1, 2, 0,1},  // lizard
                {1, 2, 1, 2, 0}}; // spock

        int botChoiceIndex = Choice.valueOf(bot.toString()).ordinal();
        int playerChoiceIndex = Choice.valueOf(player.toString()).ordinal();

        result = comparasionMatrix[playerChoiceIndex][botChoiceIndex];

//        System.out.println("result: " + result);

        return result;
    }

    private boolean win(Choice player, Choice bot) {
        // if comparasionMatrix == 1 player wins.
        boolean result = false;
        if (comparasionMatrix(player,bot) == 1) {
            playerWins++;
            result = true;
            System.out.println("You win!  You gain a point.");
        }
        else if (comparasionMatrix(player,bot) == 2) {
            cpuWins++;
            System.out.println("Bot wins! Bot gains a point.");
        }
        else {
            System.out.println("Tie! No points awarded.");
        }

        return result;
    }

    private Choice prompForChoice() {
//         Choice choice = null;  i don't think this is necessary, declare globally

        boolean validInput = false;
        while (!validInput) {
            System.out.print("\nPlease enter your selection: [r]ock [p]aper [s]cissors [l]izard [x]spock ");
            String input = scanner.nextLine();
            if (input.matches("(?i)[r,p,s,l,x]")) {
                // Choice choice = Choice.get(input);
                switch (input){
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
                validInput = true;
            }
        }
        System.out.println("\nYou have selected: " + choice);
        return choice;
    }

    private void prompForRules() {
        boolean  rules = false;

        boolean validInput = false;
        while (!validInput) {
            System.out.print("Would you like to read the game rules? " + ANSI_GREEN + "[Y]" + ANSI_RESET + " " + ANSI_RED + "[N]" + ANSI_RESET);
            String input = scanner.nextLine();
            if (input.matches("(?i)[y,n]")) {

                validInput = true;
                if(input.equals("Y") || input.equals("y")) {
                    System.out.println("Game Rules"
                            + "\n\nObserve the following rules: "
                            + "\nRock crushes Scissors."
                            + "\nScissors cuts Paper."
                            + "\nPaper covers Rock."
                            + "\nRock crushes Lizard."
                            + "\nLizard poisons Spock."
                            + "\nSpock smashes Scissors."
                            + "\nScissors decapitates Lizard."
                            + "\nLizard eats Paper."
                            + "\nPaper disproves Spock."
                            + "\nSpock vaporizes Rock."
                            + "\n\nIf the gesture you select defeats the opponent's selected gesture, you gain a point."
                            + "\nThe first player to gain 5 points, wins the match!"
                    );
                }

            }
        }

    }

//    showBoard()

    private void welcome()  {

        String banner = null;
        try {
            banner = Files.readString(Path.of(bannerPath));
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.print( banner);

        System.out.println();
        System.out.println("Welcome to RPSLS.  A game created by GI Java.");
        System.out.println();

    }



    private void displayGesture(Choice player, Choice bot)  {
        String playerSelection = "data/" +  player.toString().toLowerCase();
        String botSelection =  "data/" + bot.toString().toLowerCase();

        try {
           String playerGesture = Files.readString(Path.of(playerSelection));
           String botGesture = Files.readString(Path.of(botSelection));

            System.out.println(playerGesture);

            System.out.println(botGesture);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}