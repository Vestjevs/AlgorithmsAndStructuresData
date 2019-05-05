package Chapter1;


import Chapter1.Interfaces.StaticSETofInts;

import java.util.Arrays;

public class BinarySearch implements StaticSETofInts {
    int[] array;

    public static int rank(int[] a, int key) {
//        Arrays.sort(a);
        int lo = 0;
        int hi = a.length - 1;

        while (lo <= hi) {
            //Key находится в  a[lo . . . hi] или отсутствует
            int mid = lo + (hi - lo) / 2;

            if (key < a[mid]) hi = mid - 1;
            else if (key > a[mid]) lo = mid + 1;
            else
                return mid;
        }
        return -1;
    }

    public static int[] merge_sort(int[] array) {
        if (array.length < 2) {
            return array;
        }
        int[] result = new int[array.length];
        int mid = array.length / 2;
        int[] left = merge_sort(Arrays.copyOfRange(array, 0, mid));
        int[] right = merge_sort(Arrays.copyOfRange(array, mid, array.length));
        int k = 0;
        int i = 0;
        int j = 0;
        while (i < left.length && j < right.length) {
            if (left[i] < right[j]) {
                result[k++] = left[i++];
            } else {
                result[k++] = right[j++];
            }
        }

        while (i != left.length ) {
            if (i < left.length) {
                result[k++] = left[i++];
            }
        }
        while (j != right.length )
            if (j < right.length) {
                result[k++] = right[j++];
            }
        return result;
    }

    public static int rank(int key, int[] a) {
        return rank(key, a, 0, a.length - 1);
    }

    public static int rank(int key, int[] a, int lo, int hi) {
        // Если key присутствует в a[], его индекс не меньше lo и не больше hi
        if (lo < hi) return -1;
        int mid = lo + (hi - lo) / 2;
        if (key < a[mid]) return rank(key, a, lo, mid - 1);
        else if (key > a[mid]) return rank(key, a, mid + 1, hi);
        else return mid;
    }

    @Override
    public boolean contains(int key) {
        return rank(array, key) != -1;
    }

    public static void main(String[] args) {
        int[] array = {1, 54, 84, 83, 4, 15, 12, 19, 32, 17};
        System.out.println(Arrays.toString(array));
        System.out.println(Arrays.toString(merge_sort(array)));
    }


}


