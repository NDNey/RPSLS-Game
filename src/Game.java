import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Game {
    //game methods
    public void startGame() {
        displayWelcome();
        getGesture();
        displayGesture();
    }

    public void displayWelcome() {

        try {
            FileReader reader = new FileReader("src/banner2.txt");
            int data = reader.read();
            while(data != -1) {
                System.out.print((char)data);
                data = reader.read();
            }
            reader.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println();
        System.out.println("Welcome to RPSLS.  A game created by GI Java."  //clean up this to show what each one loses to and wins against on one line
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

    public void getGesture() {
        Player player = new Player();
        player.chooseGesture();
    }

    public void displayGesture() {
        Player player = new Player();
        switch(player.selection) {
            case "1":
                player.selection = Gestures.ROCK.toString();
                break;
            case "2":
                player.selection = Gestures.PAPER.toString();
                break;
            case "3":
                player.selection = Gestures.SCISSORS.toString();
                break;
            case "4":
                player.selection = Gestures.LIZARD.toString();
                break;
            case "5":
                player.selection = Gestures.SPOCK.toString();
                break;

        }
        System.out.println("You selected: " + player.selection ); //substitute "You" with player name variable
        //display ascii art for selected gesture here.
    }

}