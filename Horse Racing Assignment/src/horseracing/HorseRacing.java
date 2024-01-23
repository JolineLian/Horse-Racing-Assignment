package horseracing;

import java.util.Scanner;

public class HorseRacing {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);    
        HorseRacingHelper.prepareHorseRacingSimulation();
        Player p1 = new Player();
        boolean gameOver = false;

        while(!gameOver){
            HorseRacingHelper.clearConsole();

            int numHorsesInRace = (int)(Math.random()*7)+5;

            int trackLength = (int)(Math.random()*3);

            int trackSurface = (int)(Math.random()*3);

            Race race = HorseRacingHelper.createRace(numHorsesInRace, trackLength, trackSurface);

            race.displayRaceInfo();
            race.drawTable();
            p1.initWalletBalance(in);
            p1.createBet(numHorsesInRace, in);

            race.startRace(p1);



            System.out.println("Race is Over");

            gameOver = playAgain(in);
        }
    }

    private static boolean playAgain(Scanner in) {
        System.out.print("\u001B[?25l");  // Hide the cursor

        System.out.print("Play Again: (y/n): ");
        String result = in.nextLine();

        if (result.equals("n"))
            return true;

        return false;

    }
}
