package horseracing;

import java.util.ArrayList;
import java.util.List;

public class Race {
    private static List<Horse> horses;
    private double raceLength; // in furlongs
    private String raceSurface; // "grass", "dirt", or "mud" (Uses HorseRacingHelper constants)
    private int currentHorse;
    private static Player player;

    private static List<Horse> results;


    public Race(List<Horse> horses, double raceLength, String raceSurface) {
        this.horses = horses;
        this.raceLength = raceLength;
        this.raceSurface = raceSurface;
        this.currentHorse = 0;
        this.results = new ArrayList<Horse>();
        if (player == null)
            player = new Player();
    }

    public static List<Horse> getResults() {
        return results;
    }

    public List<Horse> getHorses() {
        return horses;
    }

    public static int numHorses(){
        return horses.size();
    }

    public Horse getCurrentHorse(){
        return horses.get(currentHorse);
    }

    public Horse getNextHorse(){
        if (currentHorse == horses.size())
            currentHorse = 0;
        
        return horses.get(currentHorse++);
    }

    public double getRaceLength() {
        return raceLength;
    }

    public String getRaceSurface() {
        return raceSurface;
    }

    public void displayRaceInfo() {
        System.out.println("Race Information:");
        System.out.println("Race Surface: " + raceSurface);
        System.out.println("Race Length: " + raceLength + " furlongs");
    }

    public void displayResults(){
        System.out.println("\n\nRace Results");
        System.out.println("------------");
        for(int i=0; i<results.size(); i++){
            System.out.println((i+1) + ": " + results.get(i).getName() + "("+results.get(i).getNumber()+")");
        }
    }


    public void startRace(Player player){
        resetHorses();
        int numSpaces = (int)(raceLength*10);
        boolean done = false;
        HorseRacingHelper.pauseForMilliseconds(100);
        HorseRacingHelper.playBackgroundMusicAndWait("Race.wav");
        HorseRacingHelper.playBackgroundMusic("horse_gallop.wav", true);

        while(!done){
            HorseRacingHelper.pauseForMilliseconds(100);
            HorseRacingHelper.clearConsole();
            HorseRacingHelper.updateTrack(numSpaces, horses);
            Horse horse = getNextHorse();

            for (int i = 0; i<player.getBets().size();i++) {

                if (player.getBets().get(i).getBetType().equalsIgnoreCase("exacta")){
                    System.out.println("\033[0;1m" + "You placed your bets on Horse #" + player.getBets().get(i).getHorseBet1() + " & #" + player.getBets().get(i).getHorseBet2() + "\u001B[0m");
                }else {
                    System.out.println("\033[0;1m" + "Bet #" + (i+1) + " | " + "You placed your bets on Horse #" + player.getBets().get(i).getHorseBet1() + "\u001B[0m");
                }
            }
            if(!horse.raceFinished() && horse.getCurrentPosition() >= numSpaces){
                results.add(horse);
                horse.setRaceFinished(true);
            } else if(!horse.raceFinished()){

                // HorseRacingHelper.pauseForMilliseconds(5000);

                horse.incrementPosition(getIncrement(horse));

            }

            displayResults();

            if (results.size() == horses.size())
                done = true;
        }

        HorseRacingHelper.stopMusic();
        
        player.getBetResults(results, raceLength, raceSurface);
    }
    // Other methods for simulating the race, calculating winners, etc., can be added as needed

    private int getIncrement(Horse horse) {
        // we have racelength and racesurface (as attributes)
        // horse.getDirtRating();
        // horse.getGrassRating();
        // horse.getMudRating();
        // this.getRaceLength();
        // this.getRaceSurface();

        // check race surface and check horses and compare race length with preferred length
    

        int d = (int)(7 - Math.abs(horse.getPreferredLength() - raceLength));

        // System.out.println(horse.getName() + " d = " + d);

        if (raceSurface.equals("grass")) {
            d += horse.getGrassRating() /2 + 2;
        }
        else if (raceSurface.equals("mud")) {
            d += horse.getMudRating() /2 + 2;   
        }  
        else {
            d += horse.getDirtRating() /2 + 2;
        }

        // System.out.println(horse.getName() + " d = " + d);

        int incrementSize = (int)(Math.random() * d) + 1;

        // System.out.println(horse.getName() + " Increment size: " + incrementSize);

        // HorseRacingHelper.pauseForMilliseconds(5000);

        return incrementSize;

    }


    private void resetHorses() {
        for (Horse horse : horses) {
            horse.resetCurrenPosition();
        }
    }

    public void drawTable(){
    System.out.println("+-------------------------+--------------------+---------------+---------------+---------------+----------+----------+----------+----------+------------+");
    System.out.printf("|%-25s|%20s|%15s|%15s|%15s|%10s|%10s|%10s|%10s|%12s|\n", "Horse Name", "Preferred Length", "Dirt Rating", "Grass Rating", "Mud Rating", "Win Odds", "Place Odds", "Show Odds", "Box Odds", "Exacta Odds");
        for (int i = 0; i < horses.size(); i++) {
            Horse horse = horses.get(i);
            double winningOdds = horse.getWinningOdd(getRaceLength(), getRaceSurface());
            String s1 = "" + horse.getName();
            String s2 = "" + (int)horse.getPreferredLength() + " furlongs";
            String s3 = "" + horse.getDirtRating() * 10;
            String s4 = "" + horse.getGrassRating() * 10;
            String s5 = "" + horse.getMudRating() * 10;
            String s6 = "" + winningOdds + "-1";
            String s7 = "" + horse.getPlaceOdd(winningOdds) + "-1";
            String s8 = "" + horse.getShowOdd(horse.getPlaceOdd(winningOdds), winningOdds) + "-1";
            // String s9 = "" + horse.getExactaBoxOdd() + "-1"; <<<<<<<<<<<<<<<
            // String s10 = "" + horse.getExactaOdd() + "-1";

        System.out.println("+-------------------------+--------------------+---------------+---------------+---------------+----------+----------+----------+----------+------------+");
        System.out.printf("|%-25s|%20s|%15s|%15s|%15s|%10s|%10s|%10s|\n", s1, s2, s3, s4, s5, s6, s7, s8);
    }
    System.out.println("+-------------------------+--------------------+---------------+---------------+---------------+----------+----------+----------+----------+------------+");
    }
}
