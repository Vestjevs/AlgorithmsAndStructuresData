package Chapter2Sort;

import java.util.Arrays;
import java.util.Random;
import java.util.function.Function;
import java.util.stream.IntStream;

public class Stopwatch {
    private final long start;

    public Stopwatch() {
        start = System.currentTimeMillis();
    }

    public double elapsedTime() {
        long now = System.currentTimeMillis();
        return (now - start) / 1000.0;
    }

    public static int[] bubble_sort(int[] array) {
        int size = array.length;
        int aux;
        for (int i = 1; i < size; i++) {
            int j = i - 1;
            while (j >= 0 && array[j + 1] < array[j]) {
                aux = array[j + 1];
                array[j + 1] = array[j];
                array[j] = aux;
                j--;
            }
        }
        return array;
    }

    public static int[] insertion_sort(int[] array) {
        int size = array.length;
        int aux;
        for (int i = 1; i < size; i++) {
            int j = i - 1;
            aux = array[i];
            while (j >= 0 && aux < array[j]) {
                array[j + 1] = array[j];
                j--;
            }
            array[j + 1] = aux;

        }
        return array;
    }

    public static int[] selection_sort(int[] array) {
        int size = array.length;
        int aux;

        for (int i = 0; i < size; i++) {
            aux = array[i];
            for (int j = i + 1; j < size; j++) {
                if (array[j] < aux) {
                    aux = array[j];
                }
            }
            array[i] = aux;
        }
        return array;
    }

    public static int[] merge_sort(int[] array) {
        if (array.length < 2) {
            return array;
        }
        int mid = array.length / 2;
        int[] result = new int[array.length];
        int[] left = merge_sort(Arrays.copyOfRange(array, 0, mid));
        int[] right = merge_sort(Arrays.copyOfRange(array, mid, array.length));
        int i = 0;
        int j = 0;
        int k = 0;
        while (i < left.length && j < right.length) {
            if (left[i] < right[j]) {
                result[k++] = left[i++];
            } else {
                result[k++] = right[j++];
            }
        }
        while (i < left.length) {

            result[k++] = left[i++];
        }
        while (j < right.length)
            result[k++] = right[j++];

        return result;
    }

    public static void main(String[] args) {
        int[] array;
        Random random = new Random();
        array = IntStream.range(0, 15).map(i -> random.nextInt(15)).toArray();
        Function<int[], int[]> function = Stopwatch::merge_sort;
        System.out.println(Arrays.toString(function.apply(array)));
    }
}
