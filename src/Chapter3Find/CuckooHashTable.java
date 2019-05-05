package Chapter3Find;

import java.util.Random;

public class CuckooHashTable<Key, Value> {

    private int mL; // size hash table
    private int mR; // size hash table
    private int scoreR; // score pairs key-value
    private int scoreL; // score pairs key-value
    private Key[] keysR;
    private Value[] valuesR;
    private Key[] keysL;
    private Value[] valuesL;
    private Random random;

    public CuckooHashTable() {
        this.mL = this.mR = 7;
        scoreR = 0;
        scoreL = 0;
        keysL = (Key[]) new Object[mL];
        keysR = (Key[]) new Object[mR];
        valuesL = (Value[]) new Object[mL];
        valuesR = (Value[]) new Object[mR];
        random = new Random();
    }


    public void put(Key key, Value value) {
        int i = hashL(key);
        if (keysL[i] == null) {
            keysL[i] = key;
            valuesL[i] = value;
            scoreL++;
            return;
        } else if (keysL[i] != null) {
            Key auxK = keysL[i];
            Value auxV = valuesL[i];
            keysL[i] = key;
            valuesL[i] = value;
            scoreL++;
            i = hashR(auxK);
            if (keysR[i] == null) {
                keysR[i] = auxK;
                valuesR[i] = auxV;
                scoreR++;
                return;
            } else {
                put(auxK, auxV);
            }
        }
    }

    // 0 -> Right ; 1 -> Left
    private void resize(int size, int LR) {
        Key[] keys = (Key[]) new Object[size];
        Value[] values = (Value[]) new Object[size];

        if (LR == 0) {
            for (int i = 0; i < keysR.length; i++) {
                if (keysR[i] != null) {
                    int k;
                    for (k = hashR(keysR[i]); keys[k] != null; k = (k + 1) % size) {
                        if (keysR[i].equals(keys[k])) {
                            values[k] = valuesR[i];
                            return;
                        }
                        keys[k] = keysR[i];
                        values[k] = valuesR[i];
                    }
                }
            }
            keysR = keys;
            valuesR = values;
            mR = size;

        } else if (LR == 1) {
            for (int i = 0; i < keysL.length; i++) {
                if (keysL[i] != null) {
                    int k;
                    for (k = hashL(keysL[i]); keys[k] != null; k = (k + 1) % size) {
                        if (keysL[i].equals(keys[k])) {
                            values[k] = valuesL[i];
                            return;
                        }
                        keys[k] = keysL[i];
                        values[k] = valuesL[i];
                    }
                }
            }
            keysL = keys;
            valuesL = values;
            mR = size;
        }
    }

    private int hashL(Key key) {
        return ((random.nextInt() + key.hashCode()) & 0x7fffffff) % mL;
    }

    private int hashR(Key key) {
        return ((random.nextInt() + key.hashCode()) & 0x7fffffff) % mL;
    }


}
