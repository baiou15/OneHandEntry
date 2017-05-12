import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 * Created by oubai on 5/9/17.
 */
public class BuildPred {

    private Map<String, Double> map;
    private ArrayList<String> wordlist;
    private double[][] inter = new double[26][26];

    public BuildPred() {
        map = new HashMap<>();
        wordlist = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader("ANC-token-count.txt"));

            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while(line != null) {
                String[] strings = line.split("\t");
                System.out.println(strings[0]);
                map.put(strings[0], Double.parseDouble(strings[2]));
                wordlist.add(strings[0]);
                line = br.readLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Collections.sort(wordlist, new MyComparator(""));

        for(int i = 0; i < wordlist.size(); i++) {
            String s = wordlist.get(i);
            if(i % 10000 == 0) {
                System.out.print("Processing: " + Double.toString(i / 230000.0 * 100) + "%\n");
            }
            if(!s.matches("[a-z]+")) {
                continue;
            }
            for(int j = i + 1; j < wordlist.size(); j++) {
                String t = wordlist.get(j);

                if(!t.matches("[a-z]+")) {
                    continue;
                }

                if(s.length() != t.length()) {
                    break;
                }

                int valid = 0;
                char a = 'a', b = 'a';
                for(int k = 0; k < s.length(); k++) {
                    if(s.charAt(k) != t.charAt(k)) {
                        a = s.charAt(k);
                        b = t.charAt(k);
                        valid++;
                    }
                }

                if(valid == 1) {
                    Double freq_s = map.get(s), freq_t = map.get(t);
                    if(a > b) {
                        char tmp = b;
                        b = a;
                        a = tmp;
                    }
                    inter[a - 'a'][b - 'a'] = freq_s * freq_t;
                }
            }
        }

        System.out.print("End.");
    }

    public class MyComparator implements java.util.Comparator<String> {

        private int referenceLength;

        public MyComparator(String reference) {
            super();
            this.referenceLength = reference.length();
        }

        public int compare(String s1, String s2) {
            int dist1 = Math.abs(s1.length() - referenceLength);
            int dist2 = Math.abs(s2.length() - referenceLength);

            return dist1 - dist2;
        }
    }

    public static void main(String... args) {
        BuildPred buildPred = new BuildPred();
    }
}
