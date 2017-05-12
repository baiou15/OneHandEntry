import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

import static java.math.RoundingMode.UP;

/**
 * Created by oubai on 5/1/17.
 */
public class test {

    public static void main(String[] args) {
        /*
        String filename = "english_bigram.txt";
        long sum = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] splited = line.split("\\s+");
                sum += Integer.parseInt(splited[1]);
                // System.out.println(sum);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        Map<String, Double> map = new HashMap<>();
        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(6);
        df.setRoundingMode(UP);
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] splited = line.split("\\s+");
                map.put(splited[0].toLowerCase(), (double)Integer.parseInt(splited[1]) / sum);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        String filename = "english_monograms.txt";
        long sum = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] splited = line.split("\\s+");
                sum += Integer.parseInt(splited[1]);
                // System.out.println(sum);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        Map<Character, Double> freq = new HashMap<>();
        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(6);
        df.setRoundingMode(UP);
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] splited = line.split("\\s+");
                freq.put(splited[0].toLowerCase().charAt(0), (double)Integer.parseInt(splited[1]) / sum);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("test");
        */
        Map<Character, Double> map = new HashMap<>();
        map.put('a', 5.0);
        map.put('b', 2.0);
        map.put('c', 3.0);

        for(Character c:map.keySet()) {
            map.put(c, map.get(c) / 10);
        }

        System.out.println("test");
    }
}
