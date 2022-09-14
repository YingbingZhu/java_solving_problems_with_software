package week4;

import edu.duke.FileResource;
import org.apache.commons.csv.CSVRecord;

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
        FileResource fr = new FileResource("data/yob2014.csv");
        totalBirths(fr);
    }
}
