package week2.StringsFirstAssignments;

public class Part1 {
    public String findSimpleGene(String dna) {
        String res = "";
        int sIndex = dna.indexOf("ATG");
        if (sIndex == -1)
        {
            return res;
        }
        int stopIndex = dna.indexOf("TAA", sIndex+3);
        if (stopIndex == -1)
        {
            return res;
        }
        res = dna.substring(sIndex,stopIndex+3);
        return res;
    }

    public void testFindSimpleGene(){
        String dna = "AAATGCCCTAACTAGATTAAGAAACC";
        System.out.println("DNA Strand is" + dna);
        String gene = findSimpleGene(dna);
        System.out.println("Gene is " + gene);

        dna = "ACGATGCAATCAGGA";
        System.out.println("DNA Strand is" + dna);
        gene = findSimpleGene(dna);
        System.out.println("Gene is " + gene);

        dna = "AGTATCAAGTACGA";
        System.out.println("DNA Strand is" + dna);
        gene = findSimpleGene(dna);
        System.out.println("Gene is " + gene);

        dna = "ATCATCATGGTGTTGTTATAAGT";
        System.out.println("DNA Strand is" + dna);
        gene = findSimpleGene(dna);
        System.out.println("Gene is " + gene);

        dna = "ATGACCGTAA";
        System.out.println("DNA Strand is" + dna);
        gene = findSimpleGene(dna);
        System.out.println("Gene is " + gene);
    }

    public static void main(String[] args) {
        Part1 p1 = new Part1();
        p1.testFindSimpleGene();
    }
}
