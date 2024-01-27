package horseracing;

import java.util.Scanner;

public class NPC {
    private String[][] NPC;

    public NPC() {
        NPC = new String[3][3];
    }

    public void drawNPC() {
        NPC[0][0] = " ";
        NPC[0][1] = "o";
        NPC[0][2] = " ";
        NPC[1][0] = "/";
        NPC[1][1] = "|";
        NPC[1][2] = "\\";
        NPC[2][0] = "/";
        NPC[2][1] = " ";
        NPC[2][2] = "\\";
    }

    public String[][] getNPCArray() {
        return NPC;
    }

     public static void interactBankNPC(Scanner in) {
        double amountChanged;
        String interactionChoice;
        System.out.println("Would you like to withdraw or deposit money? (enter exit to return)");
        interactionChoice = in.nextLine();

        if (interactionChoice.equalsIgnoreCase("withdraw") || interactionChoice.equalsIgnoreCase("deposit")) {
            System.out.println("Enter a number to deposit or withdraw:");
            while (!(in.hasNextDouble())) {
                in.nextLine();
                System.out.println("Enter a number to deposit or withdraw:");
            } 
        } else if (interactionChoice.equalsIgnoreCase("exit")) {
            return;
        } else {
            interactBankNPC(in);
        }

        amountChanged = Double.parseDouble(in.nextLine());

        while (!(amountChanged > 0)) {
            System.out.println("Invalid input, enter a value greater than 0");
            while (!(in.hasNextDouble())) {
                in.nextLine();
                System.out.println("Enter a number to deposit or withdraw:");
            }
            amountChanged = Double.parseDouble(in.nextLine());
        }

        if (interactionChoice.equalsIgnoreCase("withdraw")) {
            Player.withdrawMoney(amountChanged);
            return;
        } else if (interactionChoice.equalsIgnoreCase("deposit")) {
            Player.depositMoney(amountChanged);
            return;
        }
    }

    public static void interactStoreNPC(Scanner in, Buildings store, NPC npc, Venue venue, Player player) {
        String interactionChoice;
        store.renderStoreItems();
        System.out.println("What would you like to buy?");
        interactionChoice = in.nextLine();

        if (interactionChoice.equalsIgnoreCase("exit")) {
            store.renderBuildingInside(in, store, store, npc, venue, player);
        }

        for (int i=0; i<5; i++) {
            if (interactionChoice.equalsIgnoreCase(store.getStoreItems()[0][i])) {
                Player.addInventory(store.getStoreItems()[0][i], true);
                store.renderBuildingInside(in, store, store, npc, venue, player);
            }
        }

        interactStoreNPC(in, store, npc, venue, player);
    }
}
