package week2.StringsSeondAssignments;

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
        int currIndex = dna.indexOf(stopCodon, startIndex + 3);
        if (currIndex != -1) {
            int diff = (currIndex - startIndex) % 3;
            if (diff == 0) {
                return currIndex;
            }
        }
        return dna.length();
    }

    public void testFindStopCodon() {
        String dna = "ATGaaaaATGaaTAGaaaaTAG";
        System.out.println("The dna string is :" + dna);
        int stopIndex = findStopCodon(dna, dna.indexOf("ATG"), "TAG");
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
        int closetIndex = Math.min(Math.min(taaIndex, tagIndex), tgaIndex);
        if (closetIndex == dna.length()) {
            return "";
        }
        return dna.substring(atgIndex, closetIndex+3);
    }

    public void testFindGene(){
        String dna= "AGDEGAASZZATAAAAA";
        System.out.println("The dna string is :" + dna);
        String gene = findGene(dna);
        System.out.println("Gene found is :" + gene);

        dna= "aaaaaaATGaaaaaaaaaTAGaaaa";
        System.out.println("The dna string is :" + dna);
        gene = findGene(dna);
        System.out.println("Gene found is :" + gene);

        dna= "aaaaaaATGaaaaaaaaaTAGTTATGAaaa";
        System.out.println("The dna string is :" + dna);
        gene = findGene(dna);
        System.out.println("Gene found is :" + gene);

        dna= "aaaaaaATGaaaaaaaaaAAAAaaa";
        System.out.println("The dna string is :" + dna);
        gene = findGene(dna);
        System.out.println("Gene found is :" + gene);

        dna= "ATGaaaaATGaaTAGaaaaTAG";
        System.out.println("The dna string is :" + dna);
        gene = findGene(dna);
        System.out.println("Gene found is :" + gene);
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

    public static void main(String[] args) {
        Part1 p1 = new Part1();
        p1.testFindGene();
        p1.testFindStopCodon();
        String dna= "ATGaaaaATGaaTAGaaaaTAGaaATGTAG";
        p1.printAllGenes(dna);
    }


}
