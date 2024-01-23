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

        public double getWinningOdd(double raceLength, String raceSurface){
            double winOdd = 2 + (Math.abs(preferredLength - raceLength));

            if (raceSurface.equals("dirt")) winOdd += ((0 - dirtRating) + 10);
            if (raceSurface.equals("grass")) winOdd += ((0 - grassRating) + 10);
            if (raceSurface.equals("mud")) winOdd += ((0 - mudRating) + 10);

            return winOdd;
        }

        // the higher chance they have of winning the higher change they have of placing, if their chance of winning is super low then their chancec of placing is lower
        public double getPlaceOdd(double winOdd) { 
            if (winOdd < 3) return winOdd;
            if (winOdd >= 3 && winOdd <= 5) return winOdd - 1;
            if (winOdd > 5 && winOdd <= 7) return winOdd + 2;
            return winOdd + 4;
        }

        public double getShowOdd(double placeOdd, double winOdd) {
            if (winOdd < placeOdd) return placeOdd + 2;
            if (winOdd > placeOdd) return placeOdd - 2;
            return placeOdd + 4;
        }

        public int getExactaOdd() {return 0;}
    
        public int getBoxingOdd() {return 0;}
}