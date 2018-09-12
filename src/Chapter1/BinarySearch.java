package Chapter1;



import Chapter1.Interfaces.StaticSETofInts;

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
        //        int[] array = new int[1000];
//        int[] array2;
//        int[][] a = new int[1000][1000];
//        int[][] b = new int[1000][1000];
//        int[][] c = new int[1000][1000];
//        int temp = 0;

//        /**reverse array */
//        for (int i = 0; i < array.length / 2; i++) {
//            tmp = array[i];
//            array[i] = array[array.length - i - 1];
//            array[array.length - i - 1] = temp;
//        }
//        /**Умножение матрицы на матрицу (квадратных) */
//        for (int i = 0; i < array.length; i++) {
//            for (int j = 0; j < array.length; j++)
//            /**Вычисление скалярного произведения строки i и столбца j*/ {
//                for (int k = 0; k < array.length; k++) {
//                    c[i][j] += a[i][k] * b[k][j];
//                }
//            }
//        }


    }


}


