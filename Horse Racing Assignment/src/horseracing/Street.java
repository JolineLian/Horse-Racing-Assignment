package horseracing;

import java.util.Scanner;

// Joline
public class Street {
    private static String[][] sidewalk;
    private static String[][] street;
    private static boolean isDoneMoving = false;

    public Street() {
        sidewalk = new String[10][150];
        street = new String[20][150];
    }

    // Joline
    // draws the street by first drawing the individual object on the street, then setting elements in the street 2d array to the objects that were first drawn
    public static void drawStreet(Buildings bank, Buildings shop, Venue venue, NPC npc) {
        bank.drawBuilding();
        shop.drawBuilding();
        venue.drawVenue();
        npc.drawNPC();
        drawSidewalk();

        // sets all elements to " " so there is no null
        for (int i=0; i<20; i++) {
            for (int j=0; j<150; j++) {
                street[i][j] = " ";
            }
        }

        // draws the sidewalk onto the street
        for (int i=10; i<20; i++) {
            for (int j=0; j<150; j++) {
                street[i][j] = sidewalk[i-10][j];
            }
        }

        // draws the bank building onto the street
        for (int i=0; i<10; i++) {
            for (int j=10; j<30; j++) {
                street[i][j] = bank.getBuildingArray()[i][j-10];
            }
        }
        street[10][15] = "B";

        // draws the npc onto the street
        for (int i=7; i<10; i++) {
            for (int j=33; j<36; j++) {
                street[i][j] = npc.getNPCArray()[i-7][j-33];
            }
        }

        // draws the shop onto the street
        for (int i=0; i<10; i++) {
            for (int j=40; j<60; j++) {
                street[i][j] = shop.getBuildingArray()[i][j-40];
            }
        }
        street[10][45] = "S";

        // draws the venue onto the street
        for (int i=0; i<10; i++) {
            for (int j=70; j<110; j++) {
                street[i][j] = venue.getVenueArray()[i][j-70];
            }
        }
        street[10][75] = "H";

        // draws the player onto the street
        for (int i=11; i<14; i++) {
            for (int j=0; j<3; j++) {
                street[i][j] = Player.getPlayerArray()[i-11][j];
            }
        }
    }

    // Joline
    // displayers the whole street in the terminal
    public static void renderStreet(Scanner in, Buildings bank, Buildings store, NPC npc, Venue venue, Player player) {
        for (int i=0; i<20; i++) {
            for (int j=0; j<100; j++)
                System.out.print(street[i][j]);
            System.out.println();
        }
        displayOptions(in, bank, store, npc, venue, player);
    }

    // Joline
    // draws the sidewalk
    public static void drawSidewalk() {
        for (int i=0; i<10; i++) {
            for (int j=0; j<150; j++) {
                sidewalk[i][j] = " ";
            }
            if (i==0 || i==9) {
                for (int j=0; j<150; j++) {
                    sidewalk[i][j] = "-";
                }
            }
        }
    }

    // Joline
    // displays options for user to interact with the game
    public static void displayOptions(Scanner in, Buildings bank, Buildings store, NPC npc, Venue venue, Player player) {
        System.out.println("use d + enter to move left, use a + enter to move right. To interact with things press space + enter");
        // method allows for interaction with the game
        moveStreet(in, bank, store, npc, venue, player);
    }

    // Joline
    // moves the street depending on what the user inputs, and interacts with buildings the user chooses to interact with
    public static void moveStreet(Scanner in, Buildings bank, Buildings store, NPC npc, Venue venue, Player player) {
        String direction = in.nextLine();
        isDoneMoving = false;
        String[] checkLetter = new String[3];
        String temp[][] = new String[20][150];
        int numHorsesInRace = (int)(Math.random()*7)+5;

        int trackLength = (int)(Math.random()*3);

        int trackSurface = (int)(Math.random()*3);

        while (!isDoneMoving) {
            // allows user to input characters that are not a, d, or space and forces character to enter a, d, or space
            if(!direction.equals(" ") && !direction.equals("a") && !direction.equals(("d"))) {
                System.out.println("invalid input, enter a, d, or space + enter");
                moveStreet(in, bank, store, npc, venue, player);
            }
            // scrolls the screen to the left if d is entered (gives illusion that character is moving to the right)
            if (direction.equals("d")) {
                for (int i=0; i<11; i++) {
                    for (int k=0; k<3; k++) {
                    for (int j=0; j<street[i].length; j++) {
                        temp[i][j] = street[i][(j+1)%street[i].length];
                    }
                    for (int j=0; j<temp[i].length; j++) {
                        street[i][j] = temp[i][j];
                    }
                }
                }
                renderStreet(in, bank, store, npc, venue, player);
                direction = in.nextLine();
            }
            // scrolls the screen to the right if a is entered (gives illusion that character is moving to the left)
            if (direction.equals("a")) {
                for (int i=0; i<11; i++) {
                    for (int k=0; k<3; k++) {
                    for (int j=street[i].length-1; j>=0; j--) {
                        temp[i][j] = street[i][(j+(street[i].length)-1)%street[i].length];
                    }
                    for (int j=temp[i].length-1; j>=0; j--) {
                        street[i][j] = temp[i][j];
                    }
                }
                }
                renderStreet(in, bank, store, npc, venue, player);
                direction = in.nextLine();
                if (direction.equals("d")) moveStreet(in, bank, store, npc, venue, player);
            }
            // enters building if player hits space + enter
            if (direction.equals(" ")) {
                checkLetter[0] = street[10][0];
                checkLetter[1] = street[10][1];
                checkLetter[2] = street[10][2];
                for (int i=0; i<3; i++) {
                    // determines which building is being interacted with and what to do when the building is interacted with
                    switch (checkLetter[i]) {
                        case "B":
                            HorseRacingHelper.clearConsole();
                            bank.renderBuildingInside(in, bank, store, npc, venue, player);
                            isDoneMoving = true;
                            break;
                        case "S":
                            HorseRacingHelper.clearConsole();
                            store.renderBuildingInside(in, bank, store, npc, venue, player);
                            isDoneMoving = true;
                            break;
                        case "H":
                            HorseRacingHelper.clearConsole();
                            Venue.findNumRaces(in);
                            for (int j=0; j<Venue.getNumRaces(); j++) {
                            Race races = HorseRacingHelper.createRace(in, numHorsesInRace, trackLength, trackSurface);

                            races.displayRaceInfo();
                            races.drawTable();
                            player.createBet(numHorsesInRace, in);
            
                            races.startRace(player);
                            }
                            return;
                    }
                }
                moveStreet(in, bank, store, npc, venue, player);
            }
        }
    }

    public static void setIsDoneMovingTrue() {
        isDoneMoving = true;
    }
}
