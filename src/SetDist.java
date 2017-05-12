/**
 * Created by oubai on 5/8/17.
 */
public class SetDist {

    public static void main(String[] args) {
    }

    public double[][] getDist() {
        int[] positions = {0, 0, 0, 1, 1, 1, 2, 2, 2, 3, 3, 3, 4, 4, 4, 5, 5, 5, 5, 6, 6, 6, 7, 7, 7, 7, 7};

        double[][] distances = {
                {0, 1, 2, 3, 4, 3, 2, 1},
                {1, 0, 1, 2, 3, 4, 3, 2},
                {2, 1, 0, 1, 2, 3, 4, 3},
                {3, 2, 1, 0, 1, 2, 3, 4},
                {4, 3, 2, 1, 0, 1, 2, 3},
                {3, 4, 3, 2, 1, 0, 1, 2},
                {2, 3, 4, 3, 2, 1, 0, 1},
                {1, 2, 3, 4, 3, 2, 1, 0}};

        double[][] dist = new double[26][26];

        for(int i = 0; i < 26; i++) {
            for(int j = 0; j < 26; j++) {
                dist[i][j] = distances[positions[i]][positions[j]];
            }
        }
        return dist;
    }
}
