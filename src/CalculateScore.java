import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

import static java.math.RoundingMode.UP;

/**
 * Created by oubai on 4/30/17.
 */
public class CalculateScore {

    char[] alphabet = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};

    public static void main(String[] args) {

        // get hashmaps
        double[][] dist = new SetDist().getDist();
        Map<String, Double> bifreq = new TwoGramFreq().getFeq();
        Map<Character, Double> freq = new LetterFreq().getFreq();


        // calculate scores
        CalculateScore cs = new CalculateScore();
//        double o1 = cs.interchangeabilityScore(dist, inter);
//        double o2 = cs.predictiveAmbiguity(dist, interPred);
        double o3 = cs.motorEfficiency(dist, bifreq);
//        double o4 = cs.locationLearnability(weights, freq);

        // set weights
        double a = 1;
        double b = 2;
        double c = 3;
        double d = 4;

//        double finalScore = a * o1 + b * o2 + c * o3 + d * o4;

//        System.out.println("The find score for this layout is " + finalScore);

    }

    public double interchangeabilityScore(double[][] dist, Map<String, Integer> inter) {
        double o1 = 0;
        for(int i = 0; i < 26; i++) {
            for(int j = 0; j < 26; j++) {
                String s = alphabet[i] + alphabet[j] + "";
                double cost = Math.exp(-0.56 * dist[alphabet[i] - 'a'][alphabet[j] - 'a']);
                o1 += cost * inter.get(s);
            }
        }
        return o1;
    }

    public double predictiveAmbiguity(double[][] dist, Map<String, Integer> interPred) {
        double o2 = 0;
        for(int i = 0; i < 26; i++) {
            for(int j = 0; j < 26; j++) {
                String s = alphabet[i] + alphabet[j] + "";
                double cost = Math.exp(-0.56 * dist[alphabet[i] - 'a'][alphabet[j] - 'a']);
                o2 += cost * interPred.get(s);
            }
        }
        return o2;
    }

    public double motorEfficiency(double[][] dist, Map<String, Double> bifreq) {
        double o3 = 0;
        for(int i = 0; i < 26; i++) {
            for(int j = 0; j < 26; j++) {
                String s = alphabet[i] + alphabet[j] + "";
                double cost = Math.exp(-0.56 * dist[alphabet[i] - 'a'][alphabet[j] - 'a']);
                o3 += bifreq.get(s) * Math.log(dist[alphabet[i] - 'a'][alphabet[j] - 'a'] + 1);
            }
        }
        return o3;
    }

    public double locationLearnability(double[] weights, Map<Character, Double> freq) {
        double o4 = 0;
        for(int i = 0; i < 26; i++) {
            o4 += weights[i] * freq.get(alphabet[i]);
        }
        return o4;
    }

    public Map<String, Double> buildBifreq() {
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

        Map<String, Double> bigreq = new HashMap<>();
        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(6);
        df.setRoundingMode(UP);
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] splited = line.split("\\s+");
                bigreq.put(splited[0].toLowerCase(), (double)Integer.parseInt(splited[1]) / sum);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bigreq;
    }

    public Map<Character, Double> buildfreq() {
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
        return freq;
    }
}
