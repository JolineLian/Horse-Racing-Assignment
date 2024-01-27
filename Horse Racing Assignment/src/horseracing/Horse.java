// comment
package horseracing;

public class Horse{
        private String name;
        private int mudRating;
        private int grassRating;
        private int dirtRating;
        private double preferredLength;


        private int currentPosition;
        private boolean finishedRace;
        private int number;

        public Horse(String name, int mudRating, int grassRating, int dirtRating, double preferredLength) {
            this.name = name;
            this.mudRating = mudRating;
            this.grassRating = grassRating;
            this.dirtRating = dirtRating;
            this.preferredLength = preferredLength;
            this.currentPosition = 2;
            this.finishedRace = false;
            this.number = 0;
        }
        
        public void setNumber(int number){
            this.number = number;
        }

        public int getNumber(){
            return this.number;
        }

        public void setRaceFinished(boolean finished){
            finishedRace = finished;
        }

        public boolean raceFinished(){
            return finishedRace;
        }
        public String getName() {
            return name;
        }

        public int getMudRating() {
            return mudRating;
        }

        public int getGrassRating() {
            return grassRating;
        }

        public int getDirtRating() {
            return dirtRating;
        }
        
        public double getPreferredLength() {
            return preferredLength;
        }

        public void incrementPosition(int inc){
            currentPosition += inc;
        }

        public int getCurrentPosition(){
            return currentPosition;
        }

        public void resetCurrenPosition(){
            currentPosition = 2;
            finishedRace = false;
        }

        // Joline
        // calculates win odds by finding difference between preferred length and race length, it then calculates the amount to add depending on the rating for the race surface
        public double getWinningOdd(double raceLength, String raceSurface){
            double winOdd = 2 + (Math.abs(preferredLength - raceLength));

            if (raceSurface.equals("dirt")) winOdd += ((0 - dirtRating) + 10);
            if (raceSurface.equals("grass")) winOdd += ((0 - grassRating) + 10);
            if (raceSurface.equals("mud")) winOdd += ((0 - mudRating) + 10);

            return winOdd;
        }

        // Joline
        // gets the place odd by determining how looking at how high or low the win odd is
        // the higher chance they have of winning the higher chance they have of placing, if their chance of winning is super low then their chancec of placing is lower because placing is close to winning and if they cant get close to winning then they can't place
        public double getPlaceOdd(double winOdd) { 
            if (winOdd < 3) return winOdd;
            if (winOdd >= 3 && winOdd <= 5) return winOdd - 1;
            if (winOdd > 5 && winOdd <= 7) return winOdd + 2;
            return winOdd + 4;
        }

        // Joline
        // gets the show odd, if there is a higher chance of winning than placing then there is a higher chance of placing than showing, 
        // if there is a lower chance of winning than plcaing then there is a lower chacne of placing than showing
        public double getShowOdd(double placeOdd, double winOdd) {
            if (winOdd < placeOdd) return placeOdd + 2;
            if (winOdd > placeOdd) return placeOdd - 2;
            return placeOdd + 4;
        }


        
         public double getExactaOdd(double placeOdd, double winOdd) {                // exacta odds = 1/# of possible combinations
            double exactaOdd = winOdd * (placeOdd +1);
            return exactaOdd;
        }                                                                            // # of possible combinations = n x (n - 1) <- eg. 10 x 9 (for the first pick you have 10 choices, for the second pick you have 9 choices)
        
        // SCRAPPED
        // double exactaOdd = 0;                                                                           
        // if (winOdd < (Race.numHorses()/2)) return exactaOdd + 6;
        // if (placeOdd > (Race.numHorses()/2)) return exactaOdd + 2;
        // exactaOdd = 1/ (Race.numHorses() * (Race.numHorses() - 1));
        //     return exactaOdd;
        // }


        public double getExactaBoxOdd(int[] oddsHorse1, int[] oddsHorse2) {
            double decimalOddsHorse1 = (double) oddsHorse1[0] / oddsHorse1[1]; // HELP
            double decimalOddsHorse2 = (double) oddsHorse2[0] / oddsHorse2[1];
     
            double totalOdds = (decimalOddsHorse1 * decimalOddsHorse2) - 1;  // multiplying number of selections for first by number of selections for second, minus 1

            return totalOdds;
        }
}