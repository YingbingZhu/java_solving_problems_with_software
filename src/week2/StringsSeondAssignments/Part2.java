package week2.StringsSeondAssignments;

public class Part2 {
    //  Write the method named howMany that has two String parameters named stringa and stringb.
    //  This method returns an integer indicating how many times stringa appears in stringb,
    //  where each occurrence of stringa must not overlap with another occurrence of it.
    //  For example, the call howMany(“GAA”, “ATGAACGAATTGAATC”) returns 3 as GAA occurs 3 times.
    //  The call howMany(“AA”, “ATAAAA”) returns 2. Note that the AA’s found cannot overlap.
    public int howMany(String stringa, String stringb) {
        int ans = 0;
        int cIndex = 0;
        while (true) {
            // each occurrence of stringa must not overlap
            cIndex = stringb.indexOf(stringa, cIndex);
            if (cIndex == -1) {
                break;
            }
            ans ++;
            cIndex += stringa.length();
        }
        return ans;
    }

    public void testHowMany() {
        int howMuch = howMany ("aa", "ataaaa");
        System.out.println("The word: aa occures in ataaaa " + howMuch + " times.");
        howMuch = howMany ("GAA", "ATGAACGAATTGAATC");
        System.out.println("The word: GAA occures in ATGAACGAATTGAATC " + howMuch + " times.");
    }

    public static void main(String[] args) {
        Part2 p2 = new Part2();
        p2.testHowMany();
    }




}
