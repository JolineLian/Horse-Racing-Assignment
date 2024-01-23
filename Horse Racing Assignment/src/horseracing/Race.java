package horseracing;

import java.util.ArrayList;
import java.util.List;

public class Race {
    private List<Horse> horses;
    private double raceLength; // in furlongs
    private String raceSurface; // "grass", "dirt", or "mud" (Uses HorseRacingHelper constants)
    private int currentHorse;

    private List<Horse> results;


    public Race(List<Horse> horses, double raceLength, String raceSurface) {
        this.horses = horses;
        this.raceLength = raceLength;
        this.raceSurface = raceSurface;
        this.currentHorse = 0;
        this.results = new ArrayList<Horse>();
    }

    public List<Horse> getHorses() {
        return horses;
    }

    public int numHorses(){
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
        System.out.println("List of Horses:");
        for (Horse horse : horses) {
            System.out.println("- " + horse.getName());
        }
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
        HorseRacingHelper.pauseForMilliseconds(1000);
        HorseRacingHelper.playBackgroundMusicAndWait("Race.wav");
        HorseRacingHelper.playBackgroundMusic("horse_gallop.wav", true);

        while(!done){
            HorseRacingHelper.pauseForMilliseconds(100);
            HorseRacingHelper.clearConsole();
            HorseRacingHelper.updateTrack(numSpaces, horses);
            Horse horse = getNextHorse();
           

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
        
        player.getResults(results, raceLength, raceSurface);
    }
    // Other methods for simulating the race, calculating winners, etc., can be added as needed

    private int getIncrement(Horse horse) {
        // we have racelength and racesurface (as attributes)
        horse.getDirtRating();
        horse.getGrassRating();
        horse.getMudRating();
        this.getRaceLength();
        this.getRaceSurface();

        // check race surface and check horses and compare race length with preferred length
    

        int d = (int)(7 - Math.abs(horse.getPreferredLength() - this.raceLength));

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
        for (int i = 0; i < horses.size(); i++) {
            Horse horse = horses.get(i);
            String s1 = "" + horse.getName();
            String s2 = "" + horse.getDirtRating();
            String s3 = "" + horse.getGrassRating();
            String s4 = "" + horse.getMudRating();

        System.out.println("+--------------------+-----+-----+-----+");
        System.out.printf("|%-20s|%5s|%5s|%5s|\n", s1, s2, s3, s4);
    }
    System.out.println("+--------------------+-----+-----+-----+");
    }
}
