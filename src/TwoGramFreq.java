import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 * Created by oubai on 5/8/17.
 */
public class TwoGramFreq {

    public static void main(String[] args) {
        TwoGramFreq twoGramFreq = new TwoGramFreq();
        twoGramFreq.getFeq();
    }

    public Map<String, Double> getFeq() {

        Character[] small = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n',
                'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
        Set<Character> set = new HashSet<>(Arrays.asList(small));
        long total = 0;
        int progress = 0;
        int lineNum = 0;
        List<String> list = new ArrayList<>();
        Map<String, Double> map = new HashMap<>();

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
                for(int i = 0; i < s.length() - 1; i++) {
                    if(set.contains(s.charAt(i)) && set.contains(s.charAt(i + 1))) {
                        total = total + 1;
                        String sub = s.substring(i, i + 2);
                        if (map.containsKey(sub)) map.put(sub, map.get(sub) + 1);
                        else map.put(sub, 1.0);
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        for(String s:map.keySet()) {
            map.put(s, map.get(s) / total);
            System.out.println(s);
            System.out.println(map.get(s) + "%");
        }
        map.put("qz", 0.0);

        return map;
    }
}
