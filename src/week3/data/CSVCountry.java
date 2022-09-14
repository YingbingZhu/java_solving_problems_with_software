package week3.data;
import edu.duke.*;
import org.apache.commons.csv.*;

import java.util.ArrayList;


public class CSVCountry {

    /**
     *
     * @param parser
     * @param country
     * @return a string of information about the country or returns “NOT FOUND” if there is no information about the country
     */
    public String countryInfo(CSVParser parser, String country) {
        String res = "";
        for (CSVRecord record : parser){
            if (record.get("Country").equals(country)) {
                res = record.get("Country") + ":" + record.get("exports")+ ":" + record.get("Value");
                return res;
            }
        }
        return "NOT FOUND";
    }

    /**
     * prints the names of all the countries that have both exportItem1 and exportItem2 as export items
     * @param parser
     * @param exportItem1
     * @param exportItem2
     */
    public void listExportersTwoProducts(CSVParser parser, String exportItem1, String exportItem2) {
        for (CSVRecord record : parser){
            if (record.get("Exports").contains(exportItem1) && record.get("Exports").contains(exportItem2)){
                System.out.println(record.get("Country"));
            }
        }
    }

    /**
     *
     * @param parser
     * @param exportItem
     * @return  the number of countries that export exportItem
     */
    public int numberOfExporters(CSVParser parser, String exportItem) {
        int count = 0;
        for (CSVRecord record : parser){
            if (record.get("Exports").contains(exportItem)){
                count++;
            }
        }
        return count;
    }


    public void bigExporters (CSVParser parser, String amount)
    {
        for(CSVRecord record: parser) {
            String value = record.get("Value (dollars)");
            if(value.length() > amount.length()) {
                String countryCheck = record.get("Country");
                System.out.println(countryCheck + " " + value);
            }
        }
    }

    public void tester()
    {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        System.out.println("Country Info for Nauru: ");
        countryInfo(parser, "Nauru");

        parser = fr.getCSVParser();
        System.out.println("Countries that export gold+diamonds: ");
        listExportersTwoProducts(parser, "gold", "diamonds");

        parser = fr.getCSVParser();
        System.out.println("Total countries that export sugar: "+ numberOfExporters(parser, "sugar"));

        parser = fr.getCSVParser();
        System.out.println("Big Exporters: ");
        bigExporters(parser, "$999,999,999,999");

    }

    public static void main(String[] args) {

    }





}
