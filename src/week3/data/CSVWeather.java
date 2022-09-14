package week3.data;

import edu.duke.DirectoryResource;
import edu.duke.FileResource;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.File;

public class CSVWeather {

    public CSVRecord hottestInManyDays() {
        CSVRecord largestSoFar = null;
        DirectoryResource dr = new DirectoryResource();
        for(File f: dr.selectedFiles())
        {
            FileResource fr = new FileResource(f);
            CSVRecord currentRow = hottestHourInFile(fr.getCSVParser());
            largestSoFar = getLargestOfTwo(currentRow, largestSoFar);
        }
        return largestSoFar;
    }

    public CSVRecord hottestHourInFile(CSVParser parser) {
        CSVRecord largestSoFar = null;
        for(CSVRecord currentRow: parser)
        {
            largestSoFar = getLargestOfTwo(currentRow, largestSoFar);
        }
        return largestSoFar;
    }

    public CSVRecord getLargestOfTwo(CSVRecord currentRow, CSVRecord largestSoFar) {
        if(largestSoFar == null)
        {
            largestSoFar = currentRow;
        }
        else
        {
            double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
            double largestTemp = Double.parseDouble(largestSoFar.get("TemperatureF"));
            if(currentTemp > largestTemp)
            {
                largestSoFar = currentRow;
            }
        }
        return largestSoFar;
    }

    public String fileWithColdestTemperature () {
        String nameOfFile = "";
        CSVRecord smallestSoFar = null;
        DirectoryResource dr = new DirectoryResource();

        for(File f: dr.selectedFiles())
        {
            FileResource fr = new FileResource(f);
            CSVParser parse = fr.getCSVParser();
            CSVRecord currentRow = coldestHourInFile(parse);

            if(smallestSoFar == null)
            {
                smallestSoFar = currentRow;
                nameOfFile = f.getAbsolutePath();
            }
            else
            {
                double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
                double smallestTemp = Double.parseDouble(smallestSoFar.get("TemperatureF"));
                if((currentTemp < smallestTemp) && (currentTemp > -90))
                {
                    smallestSoFar = currentRow;
                    nameOfFile = f.getAbsolutePath();
                }
            }
        }
        return nameOfFile;
    }

    public CSVRecord coldestHourInFile (CSVParser parser) {
        CSVRecord smallestSoFar = null;
        for(CSVRecord currentRow: parser)
        {
            smallestSoFar = getSmallestOfTwo(currentRow, smallestSoFar);
        }
        return smallestSoFar;
    }

    public CSVRecord getSmallestOfTwo(CSVRecord currentRow, CSVRecord smallestSoFar) {
        if(smallestSoFar == null)
        {
            smallestSoFar = currentRow;
        }
        else
        {
            double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
            double smallestTemp = Double.parseDouble(smallestSoFar.get("TemperatureF"));
            if((currentTemp < smallestTemp) && (currentTemp > -90))
            {
                smallestSoFar = currentRow;
            }
        }
        return smallestSoFar;
    }


    public CSVRecord lowestHumidityInFile(CSVParser parser) {
        CSVRecord smallestSoFar = null;
        for(CSVRecord currentRow: parser)
        {
            smallestSoFar = getSmallestOfTwoHumidities(currentRow, smallestSoFar);
        }
        return smallestSoFar;
    }

    public CSVRecord lowestHumidityInManyFiles() {
        CSVRecord smallestSoFar = null;
        DirectoryResource dr = new DirectoryResource();

        for(File f: dr.selectedFiles())
        {
            FileResource fr = new FileResource(f);
            CSVRecord currentRow = lowestHumidityInFile(fr.getCSVParser());
            smallestSoFar = getSmallestOfTwoHumidities(currentRow, smallestSoFar);
        }
        return smallestSoFar;
    }

    public CSVRecord getSmallestOfTwoHumidities(CSVRecord currentRow, CSVRecord smallestSoFar) {
        if(smallestSoFar == null)
        {
            smallestSoFar = currentRow;
        }
        else
        {
            if((currentRow.get("Humidity").length() != 3))
            {
                double currentTemp = Double.parseDouble(currentRow.get("Humidity"));
                double smallestTemp = Double.parseDouble(smallestSoFar.get("Humidity"));
                if ((currentTemp < smallestTemp) && (currentTemp > -90))
                {
                    smallestSoFar = currentRow;
                }
            }
        }
        return smallestSoFar;
    }


    public double averageTemperatureInFile (CSVParser parser)
    {
        double count = 0.0;
        double total = 0.0;
        CSVRecord smallestSoFar = null;
        for(CSVRecord currentRow: parser)
        {
            if(Double.parseDouble(currentRow.get("TemperatureF")) > -90)
            {
                total +=  Double.parseDouble(currentRow.get("TemperatureF"));
                count++;
            }
        }
        return total/count;
    }

    public double averageTemperatureWithHighHumidityInFile(CSVParser parser, int value)
    {
        double count = 0.0;
        double total = 0.0;
        CSVRecord smallestSoFar = null;
        for(CSVRecord currentRow: parser)
        {
            if((Double.parseDouble(currentRow.get("TemperatureF")) > -90) && (Double.parseDouble(currentRow.get("Humidity")) >= value))
            {
                total +=  Double.parseDouble(currentRow.get("TemperatureF"));
                count++;
            }
        }
        if(total == 0.0)
        {
            return -1;
        }
        else
        {
            return total/count;
        }

    }


    public void testHottestInManyDays()
    {
        CSVRecord largest = hottestInManyDays();
        System.out.println("Hottest Temp was in many days was " + largest.get("TemperatureF") + " on " + largest.get("DateUTC") + " at " + largest.get("TimeEST"));
    }

    public void testHottestInDay()
    {
        FileResource fr = new FileResource();
        CSVRecord largest = hottestHourInFile(fr.getCSVParser());
        System.out.println("Hottest Temp was" + largest.get("TemperatureF") + "at " + largest.get("TimeEST"));
    }

    public void testColdestHourInFile()
    {
        FileResource fr = new FileResource();
        CSVRecord smallest = coldestHourInFile(fr.getCSVParser());
        System.out.println("Coldest Temp in the file was " + smallest.get("TemperatureF") + " at " + smallest.get("DateUTC"));
    }

    public void testFileWithColdestTemperature()
    {
        String nameOfFile = fileWithColdestTemperature ();
        FileResource fr = new FileResource(nameOfFile);
        CSVRecord smallest = coldestHourInFile(fr.getCSVParser());
        System.out.println("Coldest day was in file "+ nameOfFile);
        System.out.println("Coldest Temp on that day was "+ smallest.get("TemperatureF"));
        System.out.println("All the Temps on that coldest day were: ");

        for(CSVRecord record: fr.getCSVParser())
        {
            System.out.println(record.get("DateUTC") + ": " + record.get("TemperatureF"));
        }
    }

    public void testlowestHumidityInManyFiles()
    {
        CSVRecord smallest = lowestHumidityInManyFiles();
        System.out.println("Lowest Humidity was " + smallest.get("Humidity") + " at " + smallest.get("DateUTC"));
    }

    public void testlowestHumidityInFile()
    {
        FileResource fr = new FileResource();
        CSVRecord smallest = lowestHumidityInFile(fr.getCSVParser());
        System.out.println("Lowest Humidity in file was " + smallest.get("Humidity") + " at " + smallest.get("DateUTC"));
    }

    public void testAverageTemperatureInFile()
    {
        FileResource fr = new FileResource();

        System.out.println("Average Temperature in file is: " + averageTemperatureInFile(fr.getCSVParser()));
    }

    public void testAverageTemperatureWithHighHumidityInFile()
    {
        FileResource fr = new FileResource();

        double result = averageTemperatureWithHighHumidityInFile(fr.getCSVParser(), 80);
        if(result == -1)
        {
            System.out.println("No temps with that humidity.");
        }
        else
        {
            System.out.println("Average Temperature with High Humidity is: " + result);
        }
    }

}
