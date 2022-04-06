package com.rpsls.app;


import com.apps.util.Console;
import com.apps.util.Prompter;

import com.rpsls.Choice;
import com.rpsls.Player;

//import static com.rpsls.Choice.*;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Scanner;

public class Game {


    private final Scanner scanner = new Scanner(System.in);
    private static final String bannerPath = "data/banner2.txt";
    private static final String playerBanner = "data/youwin.txt";
    private static final String botBanner = "data/cpuwins.txt";

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[41m";
    public static final String ANSI_GREEN = "\u001B[42m";
    public static final String ANSI_YELLOW = "\u001B[43m";

    private static Player bot = new Player(1, "Bot");
    private int playerWins = 0;
    private int cpuWins = 0;


    Prompter prompter = new Prompter(new Scanner(System.in));

    public void execute() {
        welcome();
        rules();
        Console.clear();
        Console.blankLines(3);

        while (playerWins < 5 && cpuWins < 5) {
            String playerInput = prompter.prompt("Please enter your selection: [r]ock [p]aper [s]cissors [l]izard [x]spock ",
                    "(?i)[r,p,s,l,x]", "Invalid selection.  Please try again.");
            Choice playerChoice = Choice.get(playerInput);
            Choice botChoice = bot.randomChoice();
            displayGesture(playerChoice,botChoice);

            System.out.println(" You selected: " + playerChoice + "         "  + "CPU selected: " + botChoice);
            win(playerChoice , botChoice );
            System.out.println("\nScore:  Player: " + playerWins + " || " + "CPU: " + cpuWins + "\n");

            if (playerWins == 5) {
                System.out.println("You are the first to 5 points.  You win the match!");
                String playerWinArt;
                try{
                    playerWinArt = Files.readString(Path.of(playerBanner));
                    System.out.println(playerWinArt);
                }
                catch (IOException e){
                    e.printStackTrace();
                }
            }
            else if (cpuWins == 5){
                System.out.println("Bot is the first to 5 points.  Bot wins the match!");
                String botWinArt;
                try{
                    botWinArt = Files.readString(Path.of(botBanner));
                    System.out.println(botWinArt);
                }
                catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
    }



    private void win(Choice player, Choice bot) {
        // if comparasionMatrix == 1 player wins.
        if (Choice.compare(player,bot) == 1) {
            System.out.println("\n" + ANSI_GREEN + "You win! You gained a point." + ANSI_RESET);
            playerWins++;
        } else if (Choice.compare(player,bot) == 2) {
            System.out.println("\n" + ANSI_RED + "CPU wins! CPU gains a point." + ANSI_RESET);
            cpuWins++;
        }
        else {
            System.out.println("\n" + ANSI_YELLOW + "Tie! No points awarded." + ANSI_RESET);
        }
    }


    private void rules() {
        String showRules = prompter.prompt("Read Game Rules [Y] [N]: ", "(?i)[y,n]", "Invalid selection.  Please try again.");

        if (showRules.equals("y")) {
            System.out.println("Game Rules"  //clean up this to show what each one loses to and wins against on one line
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


    public void welcome() {

        String banner = null;
        try {
            banner = Files.readString(Path.of(bannerPath));
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.print(banner);

        System.out.println();
        System.out.println("Welcome to RPSLS.  A game created by GI Java.");
        System.out.println();

    }


    private void displayGesture(Choice player, Choice bot) {
        String playerSelection = "data/" + player.toString().toLowerCase();
        String botSelection = "data/" + bot.toString().toLowerCase();
        String output = "";

        try {
            List<String> playerGesture = Files.readAllLines(Path.of(playerSelection));
            List<String> botGesture = Files.readAllLines(Path.of(botSelection));
            for (int i = 0; i < playerGesture.size(); i++) {
                // you want to look at System.out.printf() for "aligned" output

                System.out.printf("%1$8s %2$20s %n", playerGesture.get(i) ,  botGesture.get(i));

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}

