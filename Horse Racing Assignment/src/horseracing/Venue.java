package horseracing;

import java.util.Scanner;

public class Venue{
    private static int numRaces;
    private String[][] venue;
    private String name;

    public Venue(String name) {
        venue = new String[10][40];
        this.name = name;
    }

    public static void findNumRaces(Scanner in) {
        System.out.println("how many races would you like to participate in?");

        while(!(in.hasNextInt())) {
            in.nextLine();
            System.out.println("Invalid Input, enter a number of races you would like to participate in");
        }

        numRaces = Integer.parseInt(in.nextLine());

        while (numRaces <= 0) {
            System.out.println("Invalid input, enter a number greater than 0");
            while (!(in.hasNextInt())) {
                in.nextLine();
                System.out.println("Invalid Input, enter a number of races you would like to participate in");
            }
            numRaces = Integer.parseInt(in.nextLine());
        }

        return;
    }

    public static int getNumRaces() {
        return numRaces;
    }

    public void drawVenue() {
        for (int i=0; i<10; i++) {
            if (i>0 && i<9) {
                for (int j=0; j<40; j++) {
                    if (j>0 && j<40) {
                        venue[i][j] = " ";
                    }
                }
            }
            if (i==0 || i==9) {
                for (int j=0; j<40; j++)
                    venue[i][j] = "-";
            }
            if (i>5 && i<10) {
                for (int j=4; j<7; j++) {
                    if (j==4 || j==6) {
                        venue[i][j] = "|";
                    }
                }
            }
            if (i==4) {
                for (int j=5; j<name.length()+5 && j<40; j++) {
                    venue[i][j] = name.substring(j-5, j-4);
                }
            }
            if (i==6) {
                for (int j=0; j<10; j++) {
                    if (j==5) {
                        venue[i][j] = "-";
                    }
                }
            }
            for (int j=0; j<40; j++) {
                if (j==0 || j==39) {
                    venue[i][j] = "|";
                }
            }
        }
    }

    public String[][] getVenueArray() {
        return venue;
    }

}
