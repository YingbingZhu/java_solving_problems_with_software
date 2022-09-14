package week2.StringThirdAssignments;

import edu.duke.*;

public class Part1 {
    /**
     *
     * @param dna
     * @param startIndex the first occurrence of ATG occurs in dna
     * @param stopCodon
     * @return the index of the first occurrence of stopCodon that appears past startIndex
     * and is a multiple of 3 away from startIndex
     */
    public int findStopCodon(String dna, int startIndex, String stopCodon) {
        int currIndex = dna.indexOf(stopCodon, startIndex+1);
        while (currIndex != -1) {
            int diff = (currIndex - startIndex) % 3;
            if (diff == 0) {
                return currIndex;
            }
            currIndex = dna.indexOf(stopCodon, currIndex + stopCodon.length());
        }
        return dna.length();
    }

    public void testFindStopCodon() {
        String dna = "ATGaaaaATGaaTAGaaaaTAG";
        System.out.println("The dna string is :" + dna);
        int stopIndex = findStopCodon(dna, dna.indexOf("ATG"), "TAG");
        System.out.println("StopCodon found is :" + stopIndex);

        FileResource fr = new FileResource("week2/brca1line.fa");
        dna = fr.asString().toUpperCase();
        System.out.println("The dna string is :" + dna);
        stopIndex = findStopCodon(dna, dna.indexOf("ATG"), "TAA");
        System.out.println("StopCodon found is :" + stopIndex);
        stopIndex = findStopCodon(dna, dna.indexOf("ATG"), "TAG");
        System.out.println("StopCodon found is :" + stopIndex);
        stopIndex = findStopCodon(dna, dna.indexOf("ATG"), "TGA");
        System.out.println("StopCodon found is :" + stopIndex);
    }

    public String findGene(String dna){
        // Find the index of the first occurrence of the start codon “ATG”.
        int atgIndex = dna.indexOf("ATG");
        // If there is no “ATG”, return the empty string.
        if (atgIndex == -1) {
            return "";
        }
        // Find the index of the first occurrence of the stop codon “TAA”
        // after the first occurrence of “ATG” that is a multiple of three away from the “ATG”
        int taaIndex = findStopCodon(dna, atgIndex, "TAA");
        int tagIndex = findStopCodon(dna, atgIndex, "TAG");
        int tgaIndex = findStopCodon(dna, atgIndex, "TGA");

        // Return the gene formed from the “ATG” and the closest stop codon that is a multiple of three away.
        // If there is no valid stop codon and therefore no gene, return the empty string.
        int minIndex = 0;
        if (taaIndex == -1 || (tgaIndex != -1 && tgaIndex < taaIndex)) {
            minIndex = tgaIndex;
        } else { minIndex = taaIndex; }

        if (minIndex == -1 || (tagIndex != -1 && tagIndex < minIndex)) {
            minIndex = tagIndex;
        }

        if (minIndex == -1 ){ return ""; }
        return dna.substring(atgIndex, minIndex+3);
    }

    public void testFindGene(){
        FileResource fr = new FileResource("week2/brca1line.fa");
        String dna = fr.asString().toUpperCase();
        System.out.println("The dna string is :" + dna);
        String gene = findGene(dna);
        System.out.println("Gene found is :" + gene);
//        String dna= "AGDEGAASZZATAAAAA";
//        System.out.println("The dna string is :" + dna);
//        String gene = findGene(dna);
//        System.out.println("Gene found is :" + gene);
//
//        dna= "aaaaaaATGaaaaaaaaaTAGaaaa";
//        System.out.println("The dna string is :" + dna);
//        gene = findGene(dna);
//        System.out.println("Gene found is :" + gene);
//
//        dna= "aaaaaaATGaaaaaaaaaTAGTTATGAaaa";
//        System.out.println("The dna string is :" + dna);
//        gene = findGene(dna);
//        System.out.println("Gene found is :" + gene);
//
//        dna= "aaaaaaATGaaaaaaaaaAAAAaaa";
//        System.out.println("The dna string is :" + dna);
//        gene = findGene(dna);
//        System.out.println("Gene found is :" + gene);
//
//        dna= "ATGaaaaATGaaTAGaaaaTAG";
//        System.out.println("The dna string is :" + dna);
//        gene = findGene(dna);
//        System.out.println("Gene found is :" + gene);
    }

    public void printAllGenes(String dna) {
        // repeatedly find genes and print each one until there are no more genes
        int sIndex = dna.indexOf("ATG");
        while (true) {
            dna = dna.substring(sIndex);
            if (findGene(dna).isEmpty()) {
                break;
            }
            System.out.println("printing genes" + findGene(dna));
            sIndex += 3;
        }
    }

    public StorageResource getAllGenes(String dna) {
        // a StorageResource containing the genes found
        StorageResource sr = new StorageResource();
        int startIndex = 0;
        while ( startIndex < dna.length()) {
            String gene = findGene(dna);
            if (gene.isEmpty()) { break; }
            sr.add(gene);
            startIndex = dna.indexOf(gene, startIndex) + gene.length();
        }
        return sr;
    }

    public void testgetAllGenes() {
        FileResource fr = new FileResource("week2/GRch38dnapart.fa");
        String dna = fr.asString();
//        String dna = "ATGTAAGATGCCCTAGT";
        StorageResource sr = getAllGenes(dna);
        for (String s: sr.data()){
            System.out.println(s);
        }
        System.out.println(sr.size());
    }

    /**
     *
     * @param dna
     * @return the ratio of C’s and G’s in dna as a fraction of the entire strand of DNA
     */
    public float cgRatio(String dna) {
        int cTimes = countS(dna, "C");
        int gTimes = countS(dna, "G");
        return (float) (cTimes + gTimes) / dna.length();
    }

    public int countS(String s, String finds) {
        String rs = s.replaceAll(finds, "");
        return (s.length() - rs.length()) / finds.length();
    }

    public int countCTG(String dna) {
        return countS(dna, "CTG");
    }

    public void processGenes(StorageResource sr) {
        int count = 0;
        int ratioCount = 0;
        int longest = 0;
        for (String s:sr.data()) {
            if (s.length() > 60) {
                System.out.println(s);
                count += 1;
            }
            if (cgRatio(s) > 0.35) {
                System.out.println(s);
                ratioCount += 1;
            }
            if (s.length() > longest) {
                longest = s.length();
            }
        };
        System.out.printf("> 60 : %d", count);
        System.out.println();
        System.out.printf("> 0.35: %d ", ratioCount);
        System.out.println();
        System.out.printf("longest: %d ", longest);
    }

    public void testProcessGenes() {
        FileResource fr = new FileResource("week2/GRch38dnapart.fa");
        String dna = fr.asString();
        System.out.println(dna.toUpperCase());
        processGenes(getAllGenes(dna.toUpperCase()));
    }


    public static void main(String[] args) {
        Part1 p1 = new Part1();
        p1.testFindGene();
        p1.testFindStopCodon();
        p1.testgetAllGenes();
        p1.testProcessGenes();
        FileResource fr = new FileResource("week2/GRch38dnapart.fa");
        String dna = fr.asString();
        System.out.println(p1.countCTG(dna));
    }


}
