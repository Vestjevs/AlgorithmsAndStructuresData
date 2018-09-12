package Chapter1;

public class Josephus {
    public static void main(String[] args) {
        int m = 2;
        int n = 7;

        LinkedList<Integer> list = new LinkedList<>();

        for (int i = 0; i < n; i++) {
            list.addEnd(i);
        }
        while (!list.isEmpty()) {
            for (int i = 0; i < m - 1; i++) {
                list.addEnd(list.removeEnd());
            }
            System.out.print(list.removeEnd() + " ");
        }
        System.out.println();
    }

}
