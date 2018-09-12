package Chapter1;

import java.util.Arrays;

public class Experiment {

    public static double[] modelingOfDice(int n) {
        int SIDES = 6;

        double[] dist = new double[n * SIDES + 1];
        for (int i = 1; i <= SIDES; i++) {
            for (int j = 1; j <= SIDES; j++) {
                dist[i + j] += 1.0;
            }
        }
        for (int i = 2; i <= n * SIDES; i++) {
            dist[i] /= 36.0;
        }
        return dist;
    }

    public static int[] reverse(int[] array) {
        int val;
        for (int i = 0; i < array.length / 2; i++) {
            val = array[i];
            array[i] = array[array.length - i - 1];
            array[array.length - i - 1] = val;
        }
        return array;
    }

    public static void main(String[] args) {

        int[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        System.out.println(Arrays.toString(reverse(array)));
    }


}
