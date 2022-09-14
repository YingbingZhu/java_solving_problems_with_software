package week2.StringsFirstAssignments;
import src.edu.duke.*;

public class Part4 {
    public void findYoutube(String url) {
        URLResource ur = new URLResource(url);
        for (String curr : ur.words()) {
            System.out.println(curr);
            String currL = curr.toLowerCase();
            int index = currL.indexOf("youtube.com");
            if (index != -1) {
                int left = currL.indexOf("\"");
                // num is the last position in the string to look for it
                int right = currL.lastIndexOf("\"", index+1);
                String ytLink = curr.substring(left+1, right);
                System.out.println(ytLink);
            }


        }

    }

    public static void main(String[] args) {
        Part4 p4 = new Part4();
        String url = "http://www.dukelearntoprogram.com/course2/data/manylinks.html";
        p4.findYoutube(url);
    }



}
