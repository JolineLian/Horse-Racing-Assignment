package horseracing;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Player {
    private static double wallet;
    private static List<Bet> bets;
    private static String toBetOrNot;
    private static String[][] player;
    private static double bank;
    private static List<String> inventory;

    public Player() {
        wallet = 50.0;
        bets = new ArrayList<Bet>();
        toBetOrNot = "";
        player = new String[3][3];
        bank = 25.00;
        inventory = new ArrayList<String>();
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

    public static void depositMoney(double amountChanged) {
        bank += amountChanged;
        wallet -= amountChanged;
    }

    public static void withdrawMoney(double amountChanged) {
        bank -= amountChanged;
        wallet += amountChanged;
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

        if (bets.isEmpty()) {
            System.out.println("you have not won any money because you have not made a bet, your wallet still has " + wallet + " dollars in it :)");
            return;
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

    public List<Bet> getBets() {
        return bets;
    }

    public void drawPlayer() {
        player[0][1] = "O";
        player[0][2] = " ";
        player[0][0] = " ";
        player[1][0] = "/";
        player[1][1] = "|";
        player[1][2] = "\\";
        player[2][0] = "/";
        player[2][1] = " ";
        player[2][2] = "\\";
    }

    public static String[][] getPlayerArray() {
        return player;
    }

    public static void addInventory(String item, Boolean isAdding) {
        if (isAdding) {
            inventory.add(item);
            System.out.println("you bought " + item);
        } else {
            inventory.remove(item);
        }
    }
}
