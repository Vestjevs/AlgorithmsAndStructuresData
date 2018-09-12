package Chapter1;

public class LocalMinimum {

    public static int MinimumInArray(int[] a) {
        int mid = a.length / 2;
        while (mid != 0 && mid != a.length) {


            if (a[mid - 1] > a[mid] && a[mid] < a[mid + 1]) {
                return mid;
            } else if (a[mid - 1] < a[mid] && a[mid - 1] < a[mid + 1]) {
                mid = mid - 1;
            } else if (a[mid + 1] < a[mid] && a[mid + 1] < a[mid - 1]) {
                mid = mid + 1;
            }
        }
        return -1;
    }

    public static int MinimumInMatrix(int[][] a) {
        int i = a.length / 2;
        int j = a.length / 2;


        // TODO: 04.09.18  
        while ((i != 0 || j != a.length - 1) || (i != a.length || j != 0)) {
            if (a[i][j] < a[i][j + 1] && a[i][j] < a[i][j - 1] && a[i][j] < a[i - 1][j] && a[i][j] < a[i + 1][j]) {
                return a[i][j];
            } else if ((a[i][j] > a[i - 1][j]) && (a[i - 1][j] < a[i][j - 1] && a[i - 1][j] < a[i][j + 1])) {
                i = i - 1;
            } else if ((a[i][j] > a[i][j + 1]) && (a[i][j + 1] < a[i - 1][j] && a[i][j + 1] < a[i + 1][j])) {
                j = j + 1;
            } else if ((a[i][j] > a[i + 1][j] && (a[i + 1][j] < a[i][j + 1] && a[i + 1][j] < a[i][j - 1]))) {
                i = i + 1;
            } else if ((a[i][j] > a[i][j - 1]) && (a[i][j - 1] < a[i + 1][j] && a[i][j - 1] < a[i - 1][j])) {
                j = j - 1;
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        int[] a = {1, 2, 4, 8, 3, 4, 5, 7, -5, -8, -9, -4, -5};
        System.out.println(MinimumInArray(a));
    }
}
