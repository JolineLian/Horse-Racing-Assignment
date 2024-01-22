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

        public int getWinningOdd(double raceLength, String raceSurface){
            int horseWinOdd = 0;

            if (preferredLength > raceLength - 4 && preferredLength < raceLength + 4) horseWinOdd = 10;
            if (preferredLength >= raceLength - 3 && preferredLength <= raceLength + 3) horseWinOdd = 8;
            if (preferredLength >= raceLength - 2 && preferredLength <= raceLength + 2) horseWinOdd = 6;
            if (preferredLength >= raceLength - 1 && preferredLength <= raceLength + 1) horseWinOdd = 4;
            if (preferredLength == raceLength) horseWinOdd = 2;
            
            if (raceSurface.equals("grass")) {
                if (grassRating >= 91 && grassRating <= 100) horseWinOdd += 0;
                if (grassRating >= 81 && grassRating <= 90) horseWinOdd += 1;
                if (grassRating >= 71 && grassRating <= 80) horseWinOdd += 2;
                if (grassRating >= 61 && grassRating <= 70) horseWinOdd += 3;
                if (grassRating >= 51 && grassRating <= 60) horseWinOdd += 4;
                if (grassRating < 50) horseWinOdd += 5;
            }

            if (raceSurface.equals("dirt")) {
                if (dirtRating >= 91 && dirtRating <= 100) horseWinOdd += 0;
                if (dirtRating >= 81 && dirtRating <= 90) horseWinOdd += 1;
                if (dirtRating >= 71 && dirtRating <= 80) horseWinOdd += 2;
                if (dirtRating >= 61 && dirtRating <= 70) horseWinOdd += 3;
                if (dirtRating >= 51 && dirtRating <= 60) horseWinOdd += 4;
                if (dirtRating < 50) horseWinOdd += 5; 
            }

            if (raceSurface.equals("mud")) {
                if (mudRating >= 91 && mudRating <= 100) horseWinOdd += 0;
                if (mudRating >= 81 && mudRating <= 90) horseWinOdd += 1;
                if (mudRating >= 71 && mudRating <= 80) horseWinOdd += 2;
                if (mudRating >= 61 && mudRating <= 70) horseWinOdd += 3;
                if (mudRating >= 51 && mudRating <= 60) horseWinOdd += 4;
                if (mudRating < 50) horseWinOdd += 5;
            };

            return horseWinOdd;
            // max is 15
        }

        // the higher chance they have of winning the higher change they have of placing, if their chance of winning is super low then their chancec of placing is lower
        public int getPlaceOdd(int winOdd) { 
            if (winOdd > 1 && winOdd <= 4) return winOdd + 2;
            if (winOdd > 4 && winOdd <= 7) return winOdd + 4;
            if (winOdd > 7 && winOdd <= 10) return winOdd + 6;
            return winOdd - 2;
        }

        public int getShowOdd(int placeOdd, int winOdd) {
            if (winOdd < placeOdd) return placeOdd + 2;
            if (winOdd > placeOdd) return placeOdd - 2;
            return placeOdd + 4;
        }

        public int getExactaOdd() {return 0;}
    
        public int getBoxingOdd() {return 0;}
}