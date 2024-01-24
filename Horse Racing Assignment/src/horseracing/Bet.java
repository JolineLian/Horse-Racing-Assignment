package horseracing;

import java.util.List;
import java.util.Scanner;

public class Bet {
    private static double amountBet;
    private static String betType;
    private static int horseBet1;
    private static int horseBet2;
    

    public Bet() {
        amountBet = 0.0;
        betType = "";
        horseBet1 = 0;
        horseBet2 = 0;
    }

    public void startbetting (Scanner in, double wallet, int numHorses) {
        String bet = "";  

        if (wallet <= 0) {
            System.out.println("You do not have enough money to bet");
            return;
        }

        System.out.println("What type of bet do you want to place? (win, place, show, box, exacta): ");
        betType = in.nextLine();

        while (!betType.equalsIgnoreCase("win") && !betType.equalsIgnoreCase("place") && !betType.equalsIgnoreCase("show") && !betType.equalsIgnoreCase("box") && !betType.equalsIgnoreCase("exacta")) {
            System.out.println("Invalid input. Would you still like to bet?");
            bet = in.nextLine();
            if (bet.equalsIgnoreCase("N") || bet.equalsIgnoreCase("no")) {
                return;
            }
            System.out.println("Please enter what type of bet you want to place: ");
            betType = in.nextLine();
        }

        System.out.println("How much would you like to bet?");

        while(!(in.hasNextDouble())) {
            in.nextLine();
            System.out.println("enter a number:");
        }
        amountBet = Double.parseDouble(in.nextLine());

        while (amountBet > wallet || amountBet < 0) {
            System.out.println("Invalid Input, please enter an amount greater than 0 but less than what's in your wallet");
            while (!(in.hasNextDouble())) {
            in.nextLine();
            System.out.println("enter a number:");
            }
            amountBet = Double.parseDouble(in.nextLine());
        }

        wallet -= amountBet;

        if (betType.equalsIgnoreCase("win")) {
            betWin(in, numHorses);
        }
        if (betType.equalsIgnoreCase("place")) {
            betPlace(in, numHorses);
        }
        if (betType.equalsIgnoreCase("show")) {
            betShow(in, numHorses);
        }
        if (betType.equalsIgnoreCase("bet")) {
            betBox(in, numHorses);
        }
        if (betType.equalsIgnoreCase("exacta")) {
            betExacta(in, numHorses);
        }
    }

    public double getAmountBet() {
        return amountBet;
    }

    public void betWin(Scanner in, int numHorses) {
        System.out.println("which horse do you want to bet on (enter number)");

        while(!(in.hasNextInt())) {
            in.nextLine();
            System.out.println("enter a number:");
        }
        horseBet1 = Integer.parseInt(in.nextLine());

        while (!(horseBet1 > 0 && horseBet1 <= numHorses)) {
            System.out.println("invalid input, please enter a correct horse number");
            while(!(in.hasNextInt())) {
                in.nextLine();
                System.out.println("enter a number:");
            }
            horseBet1 = Integer.parseInt(in.nextLine());
        }
    }

    public void betPlace(Scanner in, int numHorses) {
        System.out.println("which horse do you want to bet on (enter number)");

        while(!(in.hasNextInt())) {
            in.nextLine();
            System.out.println("enter a number:");
        }
        horseBet1 = Integer.parseInt(in.nextLine());

        while (horseBet1 < 0 || horseBet1 > numHorses) {
            System.out.println("invalid input, please enter a correct horse number");
            while(!(in.hasNextInt())) {
                in.nextLine();
                System.out.println("enter a number:");
            }
            horseBet1 = Integer.parseInt(in.nextLine());
        }
    }

    public void betShow(Scanner in, int numHorses) {
        System.out.println("which horse do you want to bet on (enter number)");

        while(!(in.hasNextInt())) {
            in.nextLine();
            System.out.println("enter a number:");
        }
        horseBet1 = Integer.parseInt(in.nextLine());

        while (horseBet1 < 0 || horseBet1 > numHorses) {
            System.out.println("invalid input, please enter a correct horse number");
            while(!(in.hasNextInt())) {
                in.nextLine();
                System.out.println("enter a number:");
            }
            horseBet1 = Integer.parseInt(in.nextLine());
        }
    }

    public void betExacta(Scanner in, int numHorses) {
        System.out.println("which horse do you think will place first (enter number)");

        while(!(in.hasNextInt())) {
            in.nextLine();
            System.out.println("enter a number:");
        }
        horseBet1 = Integer.parseInt(in.nextLine());

        while (horseBet1 < 0 || horseBet1 > numHorses) {
            System.out.println("invalid input, please enter a correct horse number");
            while(!(in.hasNextInt())) {
                in.nextLine();
                System.out.println("enter a number:");
            }
            horseBet1 = Integer.parseInt(in.nextLine());
        }

        System.out.println("which horse do you think will place second (enter number)");

        while(!(in.hasNextInt())) {
            in.nextLine();
            System.out.println("enter a number:");
        }
        horseBet2 = Integer.parseInt(in.nextLine());

        while (horseBet2 < 0 || horseBet2 > numHorses) {
            System.out.println("invalid input, please enter a correct horse number");
            while(!(in.hasNextInt())) {
                in.nextLine();
                System.out.println("enter a number:");
            }
            horseBet2 = Integer.parseInt(in.nextLine());
        }
    }

    public void betBox(Scanner in, int numHorses) {
        System.out.println("which horse do you want to bet on (enter number)");

        horseBet1 = in.nextInt();

        while (horseBet1 < 0 || horseBet1 > numHorses) {
            System.out.println("invalid input, please enter a correct horse number");
            horseBet1 = in.nextInt();
        }
    }

    public double betResults(List<Horse> results, double raceLength, String raceSurface) {
        double winOdd = results.get(1).getWinningOdd(raceLength, raceSurface);
        double placeOdd = results.get(2).getPlaceOdd(winOdd);
        double showOdd = results.get(3).getShowOdd(placeOdd, winOdd);

        if (betType.equalsIgnoreCase("win")) {
            if (horseBet1 == results.get(1).getNumber()) {
                return amountBet * winOdd;
            }
        }
        if (betType.equalsIgnoreCase("place")) {
            if (horseBet1 == results.get(1).getNumber() || horseBet1 == results.get(2).getNumber()) {
                return amountBet * placeOdd;
            }
        }
        if (betType.equalsIgnoreCase("show")) {
            if (horseBet1 == results.get(1).getNumber() || horseBet1 == results.get(2).getNumber() || horseBet1 == results.get(3).getNumber()) {
                return amountBet * showOdd;
            }
        }
        if (betType.equalsIgnoreCase("bet")) {
            
        }
        if (betType.equalsIgnoreCase("exacta")) {
            
        }

        return 0.0;
    }

    public static int getHorseBet1() {
        return horseBet1;
        }

    public static int getHorseBet2() {
        return horseBet2;
        }

    public static String getBetType() {
        return betType;
        }     
    
}
