package week2.StringsFirstAssignments;

public class Part2 {
    public String findSimpleGene(String dna, String startCodon, String stopCodon){

        int flag = 0; // flag to indicate that dna is lower case

        if (dna.indexOf("atg") != -1){
            flag = 1;
        }
        String s = dna.toUpperCase();

        String result = "";

        int startindex = s.indexOf(startCodon.toUpperCase());
        if(startindex == -1){
            result = "";
        }
        int endindex = s.indexOf(stopCodon.toUpperCase(),startindex+3);

        if(endindex == -1){
            result = "";
        }

        if(startindex != -1 && endindex != -1){
            String gene = s.substring(startindex, endindex+3);
            String geneinbetween = gene.substring(startindex+3, endindex-3);
            int multipleofthree = (geneinbetween.length()%3);
            if (multipleofthree==0){
                result = gene;
                if(flag == 1){
                    result = gene.toLowerCase();
                }
            }
        }
        else{
            result = "";
        }
        return result;
    }

    public void testSimpleGene(){
        String startcodon = "ATG";
        String stopcodon = "TAA";
        //String s1 = "GGTTTGGAATAAAGTTTGGG"; // without ATG
        String s1 = "ggtttggaataaagtttggg";
        System.out.println("The dna is "+ s1);
        String gene1 = findSimpleGene(s1,startcodon, stopcodon);
        System.out.println("The gene is " +gene1);
        String s2 = "AAAGGGATGGGGGGTTTTAGTGGG"; // without TAA
        System.out.println("The dna is "+ s2);
        String gene2 = findSimpleGene(s2,startcodon, stopcodon);
        System.out.println("The gene is "+ gene2);
        String s3 = "gggatgttttgtttgggataattt"; // without ATG or TAA
        System.out.println("The dna is "+ s3);
        String gene3 = findSimpleGene(s3,startcodon, stopcodon);
        System.out.println("The gene is "+gene3);
        //String s4 = "GGGATGGGTTTGGGATAATTT"; // with ATG and TAA and multiple of 3
        String s4 = "gggatgggtttgggataattt";
        System.out.println("The dna is " + s4);
        String gene4 = findSimpleGene(s4,startcodon, stopcodon);
        System.out.println("The gene is "+gene4);
        String s5 = "gggatgggtttgataattt"; // with ATG and TAA and not multiple of 3
        System.out.println("The dna is " +s5);
        String gene5 = findSimpleGene(s5,startcodon, stopcodon);
        System.out.println("The gene is "+gene5);
    }

    public static void main(String[] args) {
        Part2 p2 = new Part2();
        p2.testSimpleGene();
    }


}
