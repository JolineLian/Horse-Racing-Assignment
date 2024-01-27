package horseracing;

import java.util.Scanner;

public class HorseRacing {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);    
        HorseRacingHelper.prepareHorseRacingSimulation();
        boolean gameOver = false;
        Player player = new Player();


        while(!gameOver){
            HorseRacingHelper.clearConsole();

            int numHorsesInRace = (int)(Math.random()*7)+5;

            int trackLength = (int)(Math.random()*3);

            int trackSurface = (int)(Math.random()*3);

            Street street = new Street();
            Buildings bank = new Buildings("bank");
            Buildings store = new Buildings("shop");
            Venue venue = new Venue("Horse Racing Venue");
            NPC npc = new NPC();

            store.drawStore(npc, player);
            bank.drawBank(npc, player);
            Street.drawStreet(bank, store, venue, npc);

            Street.renderStreet(in, bank, store, npc, venue , player);

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
