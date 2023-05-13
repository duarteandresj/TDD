import java.util.Random;
import java.util.Scanner;

public class Game {

    private  Scanner input = new Scanner(System.in);
    private  Random random = new Random();

    public void play() {
        printGamesRules();

        ScoreBoard scoreBoard = new ScoreBoard();
        String choice = input.nextLine().toUpperCase();

        while (!choice.equals("QUIT")) {
            GameOption choicenum = getChoiceNum(choice);

            while (choicenum == null)
            {
                System.out.println("Sorry, it looks like you didn't enter a correct input. Try again.");
                choice = input.nextLine().toUpperCase();
                choicenum = getChoiceNum(choice);
            }
            GameOption compnum = getChoiceComputer();

            completeGamePlay(scoreBoard, choicenum, compnum);
            printResults(scoreBoard);
            choice = input.nextLine().toUpperCase();
        }
    }

    private static void printResults(ScoreBoard scoreBoard) {
        System.out.println("wins:" + scoreBoard.getWins() + "\nloses:" + scoreBoard.getLosses() + "\nties:" + scoreBoard.getTie());
        System.out.println("Let's play again! \n \n");
        System.out.println("Say \"Rock\", \"Paper\", or \"Scissors\" to indicate your choice. Otherwise say " +
                "\"Quit\" to quit.");
    }

    private static void completeGamePlay(ScoreBoard scoreBoard, GameOption choicenum, GameOption compnum) {
        if (choicenum == compnum)
        {
            tie(scoreBoard);
        } else if (choicenum == GameOption.ROCK && compnum == GameOption.SCISSORS
                || choicenum == GameOption.SCISSORS && compnum == GameOption.PAPER
                || choicenum == GameOption.PAPER && compnum == GameOption.ROCK) {
            wins(scoreBoard);
        }
        lose(scoreBoard);

    }

    private static void lose(ScoreBoard scoreBoard) {
        System.out.println("you lose.");
        scoreBoard.incrementLosses();
    }

    private static void tie(ScoreBoard scoreBoard) {
        System.out.println("It's a tie");
        scoreBoard.incrementTie();
    }

    private static void wins(ScoreBoard scoreBoard) {
        System.out.println("you win!");
        scoreBoard.incrementWins();
    }

    private GameOption getChoiceNum(String choice) {
        GameOption selectedOption;
        if (choice.equals("quit")) System.exit(0);
        try {
            selectedOption = GameOption.valueOf(choice);

        } catch (Exception e) {
            return null;
        }
        return selectedOption;
    }

    private GameOption getChoiceComputer() {
        GameOption option = GameOption.values()[random.nextInt(3)];
        System.out.println("Computer chose " + option.toString().toLowerCase());
        return option;

    }

    private static void printGamesRules() {
        String message ="Let's play Rock, Paper, Scissors!";
        String message2 ="Say \"Rock\", \"Paper\", or \"Scissors\" to indicate your choice. Otherwise say \"Quit\" "
                + "to quit.";
        System.out.println(message);
        System.out.println(message2);
    }
}
