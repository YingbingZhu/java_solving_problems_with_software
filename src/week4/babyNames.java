package week4;

import edu.duke.DirectoryResource;
import edu.duke.FileResource;
import edu.duke.StorageResource;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.File;

public class babyNames {
    // Modify the method totalBirths to print the number of girls names ,
    // the number of boys names and the total names in the file.
    public void totalBirths (FileResource fr) {
        int totalBirths = 0;
        int totalBoys = 0;
        int totalGirls = 0;
        int totalGirlsN = 0;
        int totalBoyN = 0;
        int totalN = 0;
        for (CSVRecord rec : fr.getCSVParser(false)) {
            int numBorn = Integer.parseInt(rec.get(2));
            totalBirths += numBorn;
            totalN++;
            if (rec.get(1).equals("M")) {
                totalBoys += numBorn;
                totalBoyN ++;
            }
            else {
                totalGirls += numBorn;
                totalGirlsN ++;
            }
        }
        System.out.println("total births = " + totalBirths);
        System.out.println("female girls = " + totalGirls);
        System.out.println("male boys = " + totalBoys);
        System.out.println("Total Count of Girls Name: " + totalGirlsN);
        System.out.println("Total Count of boys Name: " + totalBoyN);
        System.out.println("Total Names: " + totalN);
    }

    public void testTotalBirths () {
        //FileResource fr = new FileResource();
        FileResource fr = new FileResource("week4/data/yob1905.csv");
        totalBirths(fr);
    }

    /**
     * 
     * @param year
     * @param name
     * @param gender F for female and M for male
     * @return the rank of the name in the file for the given gender,
     */
    public int getRank(int year, String name, String gender) {
        String fileName = "yob" + year + ".csv";
        FileResource fr = new FileResource("week4/data/" + fileName);
        int rank = 0;
        for (CSVRecord rec : fr.getCSVParser(false)) {
            if (rec.get(1).equals(gender)) {
                rank ++;
                if (rec.get(0).equals(name)) {
                    return rank;
                }
            }
        }
        return -1;
    }

    public void testgetRank () {
//        System.out.println(getRank(1960, "Emily", "F"));
        System.out.println(getRank(1971, "Frank", "M"));
    }

    /**
     * 
     * @param year
     * @param rank
     * @param gender
     * @return  the name of the person in the file at this rank, for the given gender,
     */
    public String getName(int year, int rank, String gender) {
        String fileName = "yob" + year + ".csv";
        FileResource fr = new FileResource("week4/data/" + fileName);
        String res = "NO NAME";
        int counter = 0;
        for (CSVRecord rec : fr.getCSVParser(false)) {
            if (rec.get(1).equals(gender)) {
                counter++;
            }
            if (rank == counter) {
                return rec.get(0);
            }
        }
        return res;
    }

    public void testgetName() {
        System.out.println(getName(1982, 450, "M"));
    }

    /**
     *  determines what name would have been named if they were born in a different year, based on the same popularity
     * @param name
     * @param year
     * @param newYear
     * @param gender
     */
    public void whatIsNameInYear(String name, int year, int newYear, String gender) {
        int rank = getRank(year, name, gender);
        String newName = getName(newYear, rank, gender);
        String heOrShe =  (gender.equals("F")) ? "she" : "he";
        System.out.println(name + " born in " + year + " would be " + newName + " if " + heOrShe + " was born in " + newYear);
    }

    public void testwhatIsNameInYear() {
        whatIsNameInYear("Susan" , 1972, 2014, "F");
        whatIsNameInYear("Owen"  , 1974, 2014, "M");
    }


    public int yearOfHighestRank(String name, String gender) {
        int yearOfHighestRank = -1;
        int highestRank = Integer.MAX_VALUE;
        DirectoryResource dir = new DirectoryResource();
        for(File f : dir.selectedFiles()){
            String filename = f.getName().replaceAll("[^\\d]", "");
            int yearOfCurrentFile = Integer.parseInt(filename);
            int currentRank = getRank(yearOfCurrentFile, name, gender);
            if(currentRank == -1) continue;
            if(currentRank < highestRank){
                yearOfHighestRank = yearOfCurrentFile;
                highestRank = currentRank;
            }
        }
        return yearOfHighestRank;
    }

    public void testyearOfHighestRank() {
        System.out.println(yearOfHighestRank("Genevieve" ,  "F"));
    }

    public double getAverageRank(String name, String gender) {
        double sumOfRanks = 0.0;
        StorageResource fileNameList = new StorageResource();
        DirectoryResource dir = new DirectoryResource();
        for (File f : dir.selectedFiles()) {
            String filename = f.getName().replaceAll("[^\\d]", "");
            fileNameList.add(filename);
            int yearOfCurrentFile = Integer.parseInt(filename);
            int currentRank = getRank(yearOfCurrentFile, name, gender);
            sumOfRanks += (double) currentRank;
        }
        return sumOfRanks / (double) fileNameList.size();
    }

    public void testgetAverageRank() {
        System.out.println(getAverageRank("Robert" ,  "M"));
    }

    private CSVParser getParser(int year){
        FileResource fr = new FileResource("week4/data/yob" + year + ".csv");
        return fr.getCSVParser(false);
    }

    public int getTotalBirthsRankedHigher(int year, String name, String gender){
        int total = 0;
        int rankOfName = getRank(year, name, gender);
        int birthNumOfName = 0;
        for(CSVRecord record : getParser(year)){
            if(record.get(0).equals(name) && record.get(1).equals(gender)){
                birthNumOfName = Integer.parseInt(record.get(2));
            }
        }
        for(CSVRecord record : getParser(year)){
            int birthNumOfCurrent = Integer.parseInt(record.get(2));
            if(record.get(1).equals(gender) && birthNumOfCurrent > birthNumOfName){
                total += Integer.parseInt(record.get(2));
            }
        }
        return total;
    }

    public void testgetTotalBirthsRankedHigher() {
        System.out.println(getTotalBirthsRankedHigher(1990, "Drew" ,  "M"));
    }


    public static void main(String[] args) {
        babyNames bn = new babyNames();
        bn.testTotalBirths();
        bn.testgetRank();
        bn.testgetName();
        bn.testwhatIsNameInYear();
//        bn.testyearOfHighestRank();
//        bn.testgetAverageRank();
        bn.testgetTotalBirthsRankedHigher();
    }
}
