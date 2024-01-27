package horseracing;

import java.util.Scanner;

// Joline
public class Buildings {
    private String name;
    private String[][] buildings;
    private String[][] buildingInside;
    private boolean isMovingHorizontal = false;
    private boolean isMovingVertical = false;
    private boolean isInteracting = false;
    private String[][] storeItems = {{"Apple", "Golden apple", "poison", "chips", "water"}, {"50", "500", "300", "15", "50"}, {"a refreshing treet for your horse", "ultimate horse booster?", "poison your enemies", "a snack for you", "hydrates horse"}};

    public Buildings(String name) {
        this.name = name;
        buildings = new String[10][20];
        buildingInside = new String[20][100];
    }

    // Joline
    // draws the building on the street by drawing the base layer and using the name to distinguish between different buildings
    public void drawBuilding() {
        for (int i = 0; i < 10; i++) {
            if (i > 0 && i < 9) {
                for (int j = 0; j < 20; j++) {
                    if (j > 0 && j < 19) {
                        buildings[i][j] = " ";
                    }
                }
            }
            if (i == 0 || i == 9) {
                for (int j = 0; j < 19; j++)
                    buildings[i][j] = "-";
            }
            if (i > 5 && i < 10) {
                for (int j = 4; j < 7; j++) {
                    if (j == 4 || j == 6) {
                        buildings[i][j] = "|";
                    }
                }
            }
            if (i == 4) {
                for (int j = 5; j < name.length() + 5 && j < 20; j++) {
                    buildings[i][j] = name.substring(j - 5, j - 4);
                }
            }
            if (i == 6) {
                for (int j = 0; j < 20; j++) {
                    if (j == 5) {
                        buildings[i][j] = "-";
                    }
                }
            }

            for (int j = 0; j < 20; j++) {
                if (j == 0 || j == 19) {
                    buildings[i][j] = "|";
                }
            }
        }
    }
    public String[][] getBuildingArray() {
        return this.buildings;
    }

    // Joline
    // this method first draws the npc and players so there is no null spaces in the 2d array, it then sets the elements at index [i][j] of buildingInside to the base building, npc and player
    public void drawBank(NPC npc, Player player) {
        npc.drawNPC();
        player.drawPlayer();


        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 100; j++) {
                buildingInside[i][j] = " ";
            }
            if (i == 0 || i == 19) {
                for (int j = 0; j < 100; j++) {
                    buildingInside[i][j] = "-";
                }
            }
            for (int j = 0; j < 100; j++) {
                if (j > 60 && j < 100) {
                    buildingInside[9][j] = "-";
                    buildingInside[10][j] = "-";
                }
                if (j == 60 || j == 85) {
                    buildingInside[i][j] = "|";
                }
                if (j == 60 && (i == 5 || i == 15)) {
                    buildingInside[i][j] = "B";
                }
                if (j == 2  && (i == 9 || i == 10)) {
                    buildingInside[i][j] = "E";
                }
            }
        }

        for (int i = 3; i < 6; i++) {
            for (int j = 88; j < 91; j++)
                buildingInside[i][j] = npc.getNPCArray()[i - 3][j - 88];
        }

        for (int i = 14; i < 17; i++) {
            for (int j = 88; j < 91; j++)
                buildingInside[i][j] = npc.getNPCArray()[i - 14][j - 88];
        }

        for (int i = 10; i < 13; i++) {
            for (int j = 5; j < 8; j++)
                buildingInside[i][j] = Player.getPlayerArray()[i - 10][j - 5];
        }
    }

    // Joline
    // sets the elements of buildingInside at [i][j] to the characters that are needed to draw the inside of the store
    public void drawStore(NPC npc, Player player) {
        npc.drawNPC();
        player.drawPlayer();

        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 100; j++) {
                buildingInside[i][j] = " ";
            }

            for (int j = 75; j < 100; j++) {
                if (i == 0 || i == 19) {
                    buildingInside[i][j] = "-";
                }

                if ((i == 6 || i == 13) && j > 87 && j < 100) {
                    buildingInside[i][j] = "-";
                }
            }

            for (int j = 0; j <= 75; j++) {
                if (i == 0 || i == 2 || i == 4 || i == 15 || i == 17 || i == 19) {
                    buildingInside[i][j] = "-";
                }

                if (j == 0 || j == 75) {
                    buildingInside[i][j] = "|";
                    buildingInside[10][0] = "E";
                    buildingInside[10][75] = "S";
                }

                if ((i >= 0 && i <= 4 || i >= 15 && i <= 19) && (j == 25 || j == 50)) {
                    buildingInside[i][j] = "|";
                }

                if ((i >= 0 && i < 6) || (i > 13 && i <= 19)) {
                    buildingInside[i][99] = "|";
                }

                if (i < 14 && i > 5) {
                    buildingInside[i][87] = "|";
                }
            }
        }

        for (int i = 9; i < 12; i++) {
            for (int j = 5; j < 8; j++)
                buildingInside[i][j] = Player.getPlayerArray()[i - 9][j - 5];
        }

        for (int i = 9; i < 12; i++) {
            for (int j = 88; j < 91; j++)
                buildingInside[i][j] = npc.getNPCArray()[i - 9][j - 88];
        }
    }

    // Joline
    // displays the inside of the building onto the screen then allows the player to move by calling movePlayer()
    public void renderBuildingInside(Scanner in, Buildings bank, Buildings store, NPC npc, Venue venue, Player player) {
        Street.setIsDoneMovingTrue();
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 100; j++)
                System.out.print(buildingInside[i][j]);
            System.out.println();
        }
        System.out.println("respectively, use w, a, s, or d + enter to move up, left, down, right. Presse space + enter to interact");
        movePlayer(in, bank, store, npc, venue, player);
    }

    // Joline
    // clears the player from the inside of the building so there are not multiple players when the player moves indexes in the 2d array
    public void clearPlayer() {
        int playerHeadPosRow = getPlayerHeadPos()[0];
        int playerHeadPosCol = getPlayerHeadPos()[1]-1;

        for (int i=playerHeadPosRow; i<playerHeadPosRow+3; i++) {
            for (int j=playerHeadPosCol; j<playerHeadPosCol+3; j++) {
                buildingInside[i][j] = " ";
            }
        }
    }

    // Joline
    // searches the 2d array buildingInside for the character that represents the players head (works because the npc's do not have the same character for a head as the player)
    // results in a return of the position of the players head in the 2d array thereby allowing you to find the position of the whole player
    public int[] getPlayerHeadPos() {
        int[] playerPos = new int[2];

        for (int i=0; i<buildingInside.length; i++) {
            for (int j=0; j<buildingInside[i].length-25; j++) {
                if (buildingInside[i][j].equals("O")) {
                        playerPos[0] = i;
                        playerPos[1] = j;
                }
            }
        }
        return playerPos;
        }

    // Joline 
    // moves player to a different index in the 2d array
    public void movePlayer(Scanner in, Buildings bank, Buildings store, NPC npc, Venue venue, Player player) {
        int playerPosRow = getPlayerHeadPos()[0];
        int playerPosCol = getPlayerHeadPos()[1]-1;
        int movement = getMovementDireciton(in);
        String[][] temp = new String[20][100];

        // elements in buildingInside is coppied to temp so temp does not have null elements
        while (isMovingHorizontal) {
        for (int i=0; i<buildingInside.length; i++) {
            for (int j=0; j<buildingInside[i].length; j++) {
                temp[i][j] = buildingInside[i][j];
            }
        }
        // clears the player from the buildingInside so that there are no double players
        clearPlayer();
        // draws the new playerPos to the inside of buildingInside by finding player in temp and incrasing [j] for buildingInside to show the next playerPos
        for (int i=playerPosRow; i<playerPosRow+3; i++) {
            for (int j=playerPosCol; j<playerPosCol+3; j++) {
                buildingInside[i][j+movement] = temp[i][j];
            }
        }
        // displays the inside of the building with the new player position
        renderBuildingInside(in, bank, store, npc, venue, player);
        }
        // does the same as the horizontal one but now instead of the movement being added or subtracted from [j] (columns) its added or subtracted from [i] (rows)
        while (isMovingVertical) {
            for (int i=0; i<buildingInside.length; i++) {
                for (int j=0; j<buildingInside[i].length; j++) {
                    temp[i][j] = buildingInside[i][j];
                }
            }
            clearPlayer();
            for (int i=playerPosRow; i<playerPosRow+3; i++) {
                for (int j=playerPosCol; j<playerPosCol+3; j++) {
                    buildingInside[i+movement][j] = temp[i][j];
                }
            }
            renderBuildingInside(in, bank, store, npc, venue, player);
        }
        // starts interacting with npc's in the building
        if (isInteracting) {
            startInteracting(in, bank, store, npc, venue, player);
            isInteracting = false;
        }
    }

    // Joline
    // starts interaction with npc's
    public void startInteracting(Scanner in, Buildings bank, Buildings store, NPC npc, Venue venue, Player player) {
        int playerPosRow = getPlayerHeadPos()[0];
        int playerPosCol = getPlayerHeadPos()[1]-1;
        // if the index of the letter that represents the NPC is near the index of the player then the npc will interact with the player
        for (int i=playerPosRow; i<playerPosRow+3; i++) {
            for (int j=playerPosCol-3; j<playerPosCol+6; j++) {
                if (buildingInside[i][j].equals("B")) {
                    NPC.interactBankNPC(in);
                }
                if (buildingInside[i][j].equals("S")) {
                    NPC.interactStoreNPC(in, store, npc, venue, player);
                }
                // exits the building
                if (buildingInside[i][j].equals("E")) {
                    Street.renderStreet(in, bank, store, npc, venue, player);
                }
            }
        }
    }

    // Joline 
    // gets the movement direction of the player by allowing player to input a string and returning the amonut to move and which direction or returning if the player is interacting with an something or not
    public int getMovementDireciton(Scanner in) {
        String movement = in.nextLine();

        // checks if the player inputs wasd, if not then it returns 0 and does not set any boolean to true so player doesn't move in or interact with anything in movePlayer() class
        if (movement.equals("d")) {
            isMovingHorizontal = true;
            isMovingVertical = false;
            return 2;
        } else if (movement.equals("a")) {
            isMovingHorizontal = true;
            isMovingVertical = false;
            return -2;
        } else if (movement.equals("w")) {
            isMovingVertical = true;
            isMovingHorizontal = false;
            return -2;
        } else if (movement.equals("s")) {
            isMovingHorizontal = false;
            isMovingVertical = true;
            return 2;
        } else if (movement.equals(" ")){
            isMovingHorizontal = false;
            isMovingVertical = false;
            isInteracting = true;
            return 0;
        } else {
            return 0;
        }
    }

    // Joline
    // displayes what items are buyable/in the store
    public void renderStoreItems() {
    System.out.println("+---------------+----------+--------------------------------------------------+");
    System.out.printf("|%-15s|%10s|%50s|\n", "item name", "price ($)", "Description");
        for (int i = 0; i < 5; i++) {
            String s1 = "" + storeItems[0][i];
            String s2 = "" + storeItems[1][i];
            String s3 = "" + storeItems[2][i];

            System.out.println("+---------------+----------+--------------------------------------------------+");
            System.out.printf("|%-15s|%10s|%50s|\n", s1, s2, s3);
        }
    System.out.println("+---------------+----------+--------------------------------------------------+");
    }

    public String[][] getStoreItems() {
        return storeItems;
    }
}
