import java.util.Scanner;

public class Player {
    //fields
    public static String selection;
    public static int wins;

    //business methods

    //MOVE THIS METHOD TO GAME CLASS
    public static String chooseGesture() {
        Player player = new Player();

        Scanner scanner = new Scanner(System.in);
        System.out.print("\n [1] Rock"
                + "\n [2] Paper"
                + "\n [3] Scissors"
                + "\n [4] Lizard"
                + "\n [5] Spock"
                + "\n\nPlease enter a number for your selection: ");
        player.selection = scanner.nextLine();
        return player.selection;
    }

    //MOVE THIS METHOD TO GAME CLASS
    public static void displayGesture() {
        switch(selection) {
            case "1":
                selection = Gestures.ROCK.toString();
                break;
            case "2":
                selection = Gestures.PAPER.toString();
                break;
            case "3":
                selection = Gestures.SCISSORS.toString();
                break;
            case "4":
                selection = Gestures.LIZARD.toString();
                break;
            case "5":
                selection = Gestures.SPOCK.toString();
                break;

        }
        System.out.println("You selected: " + selection ); //substitute "You" with player name variable
        //display ascii art for selected gesture here.
    }


    //accessor methods


    public int getWins() {
        return wins;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }
}
