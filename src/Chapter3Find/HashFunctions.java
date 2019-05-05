package Chapter3Find;


import java.util.LinkedList;

public class HashFunctions<Key> {

    public static int HashStringKey(String str, int SizeOfHashTable) {
        int hash = 0, R = 256;
        for (int i = 0; i < str.length(); i++) hash = (R * hash + str.charAt(i)) % SizeOfHashTable;
        return hash;
    }

    public int hashCodeToIndex(Key key, int m) {
        return (key.hashCode() & 0x7fffffff) % m;
    }

    public static String CheckHashCode(String str) {
        LinkedList<Integer> list = new LinkedList<>();
        int prime;
        int hash;
        int size = str.length() + 1;
        while (true) {
            for (prime = 11; prime < 1001; prime += 2) {
                for (int i = 0; i < str.length(); i++) {
                    hash = hash(str.charAt(i) - 64, prime, size);
                    if (!list.contains(hash)) {
                        list.add(hash);
                    } else {
                        list.clear();
                        break;
                    }
                }
                if (list.size() == 10) {
                    return String.format("a = %s, M = %s", prime, size);
                }
            }
            size++;
        }
    }

    private static boolean isPrime(int p) {
        if (p < 2) return false;
        for (int i = 2; i <= p / 2; i++)
            if (p % i == 0) return false;
        return true;
    }

    private static int hash(int k, int a, int m) {
        return (a * k) % m;
    }

    public static void main(String[] args) {

    }
}
