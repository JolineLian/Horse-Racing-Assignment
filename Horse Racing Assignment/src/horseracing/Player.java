package horseracing;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Player {
    private double wallet;
    private List<Bet> bets;
    private String toBetOrNot;

    public Player() {
        wallet = 0.0;
        bets = new ArrayList<Bet>();
        toBetOrNot = "";
    }

    public void initWalletBalance(Scanner in) {
        double initValue = 0.0;
        System.out.println("How much money is in your wallet?");

        while (!(in.hasNextDouble())) {
            in.nextLine();
            System.out.println("Enter a number:");
        } 

        initValue = Double.parseDouble(in.nextLine());

        while (!(initValue > 0)) {
            System.out.println("Invalid input, enter a value greater than 0");
            while (!(in.hasNextDouble())) {
                in.nextLine();
                System.out.println("Enter a number:");
            }
            initValue = Double.parseDouble(in.nextLine());
        }

        wallet += initValue;
    }

    public void createBet(int numHorses, Scanner in) {
        int betCounter = 1;
        System.out.println("Do you want to place a bet?");
        toBetOrNot = in.nextLine();

        while (!toBetOrNot.equalsIgnoreCase("N") && !toBetOrNot.equalsIgnoreCase("no") && !toBetOrNot.equalsIgnoreCase("Y") && !toBetOrNot.equalsIgnoreCase("yes")) {
            System.out.println("Invalid input, please enter yes or no");
            toBetOrNot = in.nextLine();
        }

        if (toBetOrNot.equalsIgnoreCase("N") || toBetOrNot.equalsIgnoreCase("no")) {
            return;
        }

        while (toBetOrNot.equalsIgnoreCase("y") || toBetOrNot.equalsIgnoreCase("yes")) {
            betCounter++;
            bets.add(new Bet());
            System.out.println("Would you like to bet " + betCounter + " times?");

            toBetOrNot = in.nextLine();

            while (!toBetOrNot.equalsIgnoreCase("N") && !toBetOrNot.equalsIgnoreCase("no") && !toBetOrNot.equalsIgnoreCase("Y") && !toBetOrNot.equalsIgnoreCase("yes")) {
                System.out.println("Invalid input, please enter yes or no");
                toBetOrNot = in.nextLine();
            }
        }

        for (int i=0; i<bets.size(); i++) {
            bets.get(i).startbetting(in, wallet, numHorses);
            wallet -= bets.get(i).getAmountBet();
        }
    }

    public void getBetResults(List<Horse> results, double raceLength, String raceSurface) {
        double amountWon = 0.0;
        double amountLost = 0.0;

        for (int i=0; i<bets.size(); i++) {
            amountWon += bets.get(i).betResults(results, raceLength, raceSurface);
        }

        if (amountWon == 0) {
            System.out.println("You have not won any money :(");
            for (int i=0; i<bets.size(); i++) {
                amountLost += bets.get(i).getAmountBet();
            }
            System.out.println("You have lost " + amountLost + " dollars :(, you now have " + wallet + " dollars");
            return;
        }

        wallet += amountWon;

        System.out.println("You have won " + amountWon + " dollars :), you now have " + wallet + " dollars");
    }
}
