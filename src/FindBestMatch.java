import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 * Created by baiou on 5/9/2017.
 */
public class FindBestMatch {

    public static void main(String[] args) {
        FindBestMatch findBestMatch = new FindBestMatch();
        findBestMatch.getBestMatch();
    }

    public void getBestMatch() {
        int[] inputSequence = {1, 8, 2};
        Map<String, Integer> frequency = getWordFreq();
        List<String> candidates = getCombination(inputSequence);
        int max1 = 0;
        int max2 = 0;
        String s1 = "";
        String s2 = "";
        for(int i = 0; i < candidates.size(); i++) {
            String s = candidates.get(i);
            if(frequency.containsKey(s)) {
                int num = frequency.get(s);
                if(max1 < num) {
                    max2 = max1;
                    s2 = s1;
                    max1 = num;
                    s1 = s;
                } else if(max2 < num) {
                    max2 = num;
                    s2 = s;
                }
            }
        }
        System.out.println("The best match is: " + s1);
        System.out.println("The second match is: " + s2);
    }

    public Map<Integer, List<Character>> setLayout() {
        Map<Integer, List<Character>> map = new HashMap<>();
        Character[] p1 = {'a', 'b', 'c'};
        Character[] p2 = {'d', 'e', 'f'};
        Character[] p3 = {'g', 'h', 'i'};
        Character[] p4 = {'j', 'k', 'l'};
        Character[] p5 = {'m', 'n', 'o'};
        Character[] p6 = {'p', 'q', 'r', 's'};
        Character[] p7 = {'t', 'u', 'v'};
        Character[] p8 = {'w', 'x', 'y', 'z'};

        map.put(1, Arrays.asList(p1));
        map.put(2, Arrays.asList(p2));
        map.put(3, Arrays.asList(p3));
        map.put(4, Arrays.asList(p4));
        map.put(5, Arrays.asList(p5));
        map.put(6, Arrays.asList(p6));
        map.put(7, Arrays.asList(p7));
        map.put(8, Arrays.asList(p8));

        return map;
    }

    public Map<String, Integer> getWordFreq() {
        Map<String, Integer> map = new HashMap<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader("ANC-token-count.txt"));

            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while(line != null) {
                String[] strings = line.split("\t");
//                System.out.println(strings[0]);
                map.put(strings[0], Integer.parseInt(strings[1]));
                line = br.readLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return map;
    }

    public List<String> getCombination(int[] inputSequence) {
        Map<Integer, List<Character>> map = setLayout();
        List<String> res = new ArrayList<>();
        if(inputSequence == null || inputSequence.length == 0) return res;
        addElement(res, inputSequence, map, "", 0);
        return res;
    }

    public void addElement(List<String> res, int[] inputSequence, Map<Integer, List<Character>> map, String s, int pos) {
        if(pos == inputSequence.length) {
            res.add(s);
            return;
        }
        List<Character> list = map.get(inputSequence[pos]);

        for(int i = 0; i < list.size(); i++) {
            addElement(res, inputSequence, map, s + list.get(i), pos + 1);
        }
    }
}
