package horseracing;

import java.util.Scanner;

public class Buildings {
    private String name;
    private String[][] buildings;
    private String[][] buildingInside;
    private boolean isMovingHorizontal = false;
    private boolean isMovingVertical = false;
    private boolean isInteracting = false;
    private String[][] storeItems = {{"Apple", "Golden apple", "poison", "chips", "water"}, {"50", "500", "300", "15", "50"}, {"a refreshing treet for your horse (+1 increment)", "ultimate horse booster? (+4 increment)", "poison your enemies (-4 increment)", "a snack for you (does nothing)", "hydrates horse (+2)"}};

    public Buildings(String name) {
        this.name = name;
        buildings = new String[10][20];
        buildingInside = new String[20][100];
    }

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

    public void clearPlayer() {
        int playerHeadPosRow = getPlayerHeadPos()[0];
        int playerHeadPosCol = getPlayerHeadPos()[1]-1;

        for (int i=playerHeadPosRow; i<playerHeadPosRow+3; i++) {
            for (int j=playerHeadPosCol; j<playerHeadPosCol+3; j++) {
                buildingInside[i][j] = " ";
            }
        }
    }

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

    public void movePlayer(Scanner in, Buildings bank, Buildings store, NPC npc, Venue venue, Player player) {
        int playerPosRow = getPlayerHeadPos()[0];
        int playerPosCol = getPlayerHeadPos()[1]-1;
        int movement = getMovementDireciton(in);
        String[][] temp = new String[20][100];

        while (isMovingHorizontal) {
        for (int i=0; i<buildingInside.length; i++) {
            for (int j=0; j<buildingInside[i].length; j++) {
                temp[i][j] = buildingInside[i][j];
            }
        }
        clearPlayer();
        for (int i=playerPosRow; i<playerPosRow+3; i++) {
            for (int j=playerPosCol; j<playerPosCol+3; j++) {
                buildingInside[i][j+movement] = temp[i][j];
            }
        }
        renderBuildingInside(in, bank, store, npc, venue, player);
        }
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
        if (isInteracting) {
            startInteracting(in, bank, store, npc, venue, player);
            isInteracting = false;
        }
    }

    public void startInteracting(Scanner in, Buildings bank, Buildings store, NPC npc, Venue venue, Player player) {
        int playerPosRow = getPlayerHeadPos()[0];
        int playerPosCol = getPlayerHeadPos()[1]-1;
        for (int i=playerPosRow; i<playerPosRow+3; i++) {
            for (int j=playerPosCol-3; j<playerPosCol+6; j++) {
                if (buildingInside[i][j].equals("B")) {
                    NPC.interactBankNPC(in);
                }
                if (buildingInside[i][j].equals("S")) {
                    NPC.interactStoreNPC(in, store, npc, venue, player);
                }
                if (buildingInside[i][j].equals("E")) {
                    Street.renderStreet(in, bank, store, npc, venue, player);
                }
            }
        }
    }

    public int getMovementDireciton(Scanner in) {
        String movement = in.nextLine();

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
