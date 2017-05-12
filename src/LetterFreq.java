import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by oubai on 5/5/17.
 */
public class LetterFreq {

    public static void main(String[] args) {
        LetterFreq letterFreq = new LetterFreq();
        letterFreq.getFreq();
    }

    public Map<Character, Double> getFreq() {
        //        char[] capital = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N',
//                        'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};

        char[] small = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n',
                'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};

        long total = 0;
        int progress = 0;
        int lineNum = 0;
        List<String> list = new ArrayList<>();
        Map<Character, Double> map = new HashMap<>();

        long[] count = new long[26];
        try {
            BufferedReader br = new BufferedReader(new FileReader("ANC-token-count.txt"));

            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while(line != null) {
                String[] strings = line.split("\t");
                list.add(strings[0]);
                lineNum++;
                if(lineNum % 300 == 0) {
                    progress++;
                    System.out.println("Get String Progress: " + progress + "%");
                }
                line = br.readLine();
            }
            lineNum = 0;
            progress = 0;
            for(String s:list) {
                lineNum++;
                for(int i = 0; i < s.length(); i++) {
                    for(int j = 0; j < small.length; j++) {
                        if(s.charAt(i) == small[j]) {
                            count[j]++;
                            total = total + 1;
                            break;
                        }
                    }
                }
                if(lineNum % 300 == 0) {
                    progress++;
                    System.out.println("Calculate Progress: " + progress + "%");
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("");
        System.out.println("Complete count");

        for(int i = 0; i < 26; i++) {
            map.put(small[i], (((double) count[i]/total)*100));
//            System.out.println(" " + small[i]);
//            System.out.println(" " + count[i]);
//            if (count[i] > 0)
//                System.out.println(" " + (((double) count[i]/total)*100) + "%");
//            else
//                System.out.println(" 0%");
        }
        return map;
    }
}
