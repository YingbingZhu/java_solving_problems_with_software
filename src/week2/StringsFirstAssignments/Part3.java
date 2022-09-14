package week2.StringsFirstAssignments;

public class Part3 {
    public boolean twoOccurences(String stringa, String stringb){
        boolean result = false;
        int len = stringa.length();
        String stringaa = stringa.toLowerCase();
        String stringbb = stringb.toLowerCase();
        int ind1 = stringbb.indexOf(stringaa);
        int ind2 = stringbb.indexOf(stringaa, ind1+len);
        if (ind2 >-1){
            result = true;
        }
        return result;
    }

    public String lastPart(String stringa, String stringb){
        String strn = stringb;
        int len1 = stringa.length();
        int len2 = stringb.length();
        int ind1 = stringb.indexOf(stringa);
        //int ind2 = len2 -1;
        if(ind1 >-1){
            strn = stringb.substring(ind1+len1,len2);
        }
        return strn;
    }
    public void testing(){
        String stringa = "an";
        String stringb = "banana";
        String stringa1 = "zoo";
        String stringb1 = "forest";
        boolean test2occurs = twoOccurences(stringa, stringb);
        String testlastpart = lastPart(stringa,stringb);
        String testlastpart1 = lastPart(stringa1,stringb1);
        System.out.println(test2occurs);
        System.out.println(testlastpart);
        System.out.println(testlastpart1);
    }
}
