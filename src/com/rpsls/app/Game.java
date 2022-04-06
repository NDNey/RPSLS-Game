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
            String playerInput = prompter.prompt("Please enter RPSLS: ", "(?i)[r,p,s,l,x]", "error");
           Choice playerChoice = Choice.get(playerInput);
            Choice botChoice = bot.randomChoice();
            win(playerChoice , botChoice );


            displayGesture(playerChoice,botChoice);

            System.out.println("playerChoice: " + playerChoice + ", " + "botChoice: " + botChoice);

            System.out.println("playerWins: " + playerWins);
            System.out.println("cpuWins: " + cpuWins);
        }

    }



    private void win(Choice player, Choice bot) {
        // if comparasionMatrix == 1 player wins.
        if (Choice.compare(player,bot) == 1) {
            System.out.println("you win! you get 1 point");
            playerWins++;
        } else if (Choice.compare(player,bot) == 2) {
            System.out.println("Computer wins! Computer gets 1 point");
            cpuWins++;
        }

    }


    private void rules() {
        String showRules = prompter.prompt("Read Game Rules [Y] [N]: ", "(?i)[y,n]", "error");

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

