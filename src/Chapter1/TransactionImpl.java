package Chapter1;

/**
 * реализацию типа данных Transaction по образу реализации типа Date 1.2.22
 * реализацию метода equals для типа Transaction по образу метода Date 1.2.5
 */
public class TransactionImpl implements Comparable<TransactionImpl> {
    private final String who;      // customer
    private final DateFirstRealize when;     // date
    private final double amount;   // amount
    private int hash = 31;


    public TransactionImpl(String who, DateFirstRealize when, double amount) throws IllegalAccessException {
        if (Double.isNaN(amount) || Double.isInfinite(amount))
            throw new IllegalAccessException("Amount cannot be Nan or Infinite");
        this.who = who;
        this.when = when;
        this.amount = amount;
    }

    public TransactionImpl(String transaction) {
        String[] str1 = transaction.split(" ");
        this.who = str1[0];
        this.when = new DateFirstRealize(str1[1]);
        this.amount = Double.parseDouble(str1[2]);

    }

    public DateFirstRealize when() {
        return when;
    }

    public String who() {
        return who;
    }

    public double amount() {
        return amount;
    }

    public String toString() {
        return String.format("-%10s %10s %8.2f", who, when, amount);
    }


    @Override
    public int hashCode() {
        if (hash == 31) {
            hash = 31 * hash + who.hashCode();
            hash = 31 * hash + when.hashCode();
            hash = 31 * hash + ((Double) amount).hashCode();
        }
        return hash;
    }

    @Override
    public int compareTo(TransactionImpl o) {
        return 0;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (this.getClass() != obj.getClass()) return false;

        TransactionImpl that = (TransactionImpl) obj;

        if (this.amount != that.amount) return false;
        if (!this.when.equals(that.when)) return false;
        return this.who.equals(that.who);
    }

    public static void main(String[] args) {

    }

}
