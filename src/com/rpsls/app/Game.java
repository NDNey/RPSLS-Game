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

    private final Prompter PROMPTER = new Prompter(new Scanner(System.in));
    private static final int MAX_GAME = 5;

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[41m";
    public static final String ANSI_GREEN = "\u001B[42m";
    public static final String ANSI_YELLOW = "\u001B[43m";

    private static Player bot = new Player(1, "bot");
    private int playerWins = 0;
    private int cpuWins = 0;


    public void execute() {
        welcome();
        rules();
        Console.blankLines(2);
        Console.clear();
        play();
        String playAgain = PROMPTER.prompt("Would you like to play again? [y][n]: ",
                "(?i)[y,n]", "Invalid selection.  Please try again.");

      while (playAgain.matches("(?i)y")) {
          playerWins = 0;
          cpuWins = 0;
          play();
          playAgain = PROMPTER.prompt("Would you like to play again? [y][n]: ",
                  "(?i)[y,n]", "Invalid selection.  Please try again.");
      }

    }


    private void play () {


        while (playerWins < MAX_GAME && cpuWins < MAX_GAME) {
            Console.clear();

            String playerInput = PROMPTER.prompt("Please enter your selection: [r]ock [p]aper [s]cissors [l]izard [x]spock: ",

                    "(?i)[r,p,s,l,x]", "Invalid selection.  Please try again.");

            Choice playerChoice = Choice.get(playerInput);
            Choice botChoice = bot.randomChoice();

            displayGesture(playerChoice, botChoice);

            System.out.println(" You selected: " + playerChoice + "         "  + "CPU selected: " + botChoice);

            win(playerChoice , botChoice );

            System.out.println("\nScore:  Player: " + playerWins + " || " + "CPU: " + cpuWins + "\n");
        }
    }

    private void win(Choice player, Choice bot) {
        // if comparasionMatrix == 1 player wins.
        if (Choice.compare(player,bot).equals("win")) {
            System.out.println("\n" + ANSI_GREEN + "You win! You gained a point." + ANSI_RESET);
            playerWins++;
        } else if (Choice.compare(player,bot).equals("lose")) {
            System.out.println("\n" + ANSI_RED + "CPU wins! CPU gains a point." + ANSI_RESET);
            cpuWins++;
        }
        else {
            System.out.println("\n" + ANSI_YELLOW + "Tie! No points awarded." + ANSI_RESET);
        }

        if (playerWins == MAX_GAME || cpuWins == MAX_GAME ) {
            String winArt = "";
            try{
                if (playerWins == MAX_GAME) {
                    winArt =  Files.readString(Path.of("data/youwin.txt"));
                    Console.blankLines(1);
                    System.out.println("You are the first to 5 points.  You win the match!");
                    Console.blankLines(1);
                }
                else if (cpuWins == MAX_GAME) {
                    winArt =  Files.readString(Path.of("data/cpuwins.txt"));
                    Console.blankLines(1);
                    System.out.println("Bot is the first to 5 points.  Bot wins the match!");
                    Console.blankLines(1);
                }

                System.out.println(winArt);
            }
            catch (IOException e){
                e.printStackTrace();
            }
        }
    }


    private void rules() {
        String showRules = PROMPTER.prompt("Read Game Rules [Y] [N]: ", "(?i)[y,n]", "Invalid selection.  Please try again.");

        if (showRules.matches("(?i)y")) {
            try {
                Console.blankLines(2);
                System.out.println(Files.readString(Path.of("data/rules")));;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    public void welcome() {

        String banner = null;
        try {
            banner = Files.readString(Path.of("data/banner.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.print(banner);

        Console.blankLines(1);
        System.out.println("Welcome to RPSLS.  A game created by GI Java.");
        Console.blankLines(1);

    }


    private void displayGesture(Choice player, Choice bot) {
        String playerSelection = "data/" + player.toString().toLowerCase();
        String botSelection = "data/" + bot.toString().toLowerCase();

        try {
            List<String> playerGesture = Files.readAllLines(Path.of(playerSelection));
            List<String> botGesture = Files.readAllLines(Path.of(botSelection));
            for (int i = 0; i < playerGesture.size(); i++) {

                System.out.printf("%1$8s %2$20s %n", playerGesture.get(i) ,  botGesture.get(i));

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

