package Chapter1;

import Chapter1.Interfaces.IDate;

public class DateFirstRealize implements IDate {

    private final int value;

    DateFirstRealize(int month, int day, int year) {

        this.value = year * 512 + month * 32 + day;
    }

    DateFirstRealize(String date) {
        String[] fields = date.split("/");
        this.value = Integer.parseInt(fields[0]) * 512 + Integer.parseInt(fields[1]) * 32 + Integer.parseInt(fields[2]);
    }


    public int month() {
        return (value / 32) % 16;
    }


    public int day() {
        return value % 32;
    }


    public int year() {
        return value / 512;
    }

    public int hashcode() {
        int hash1 = 11;
        int hash2 = hash1 * Integer.valueOf(month()).hashCode() + 31;
        hash1 = hash2 + Integer.valueOf(year()).hashCode() + 31;
        hash2 = hash1 + Integer.valueOf(day()).hashCode() + 31;
        return hash2;
    }

    public String toString() {
        return month() + "/" + day() + "/" + year();
    }

    @Override
    public boolean equals(Object obj) {
        /**
         * МОДЕЛЬ ПРИ РЕАЛИЗАЦИИ МЕТОДА EQUALS()
         * */
        if (this == obj) return true;
        if (obj == null) return false;
        if (this.getClass() != obj.getClass()) return false;

        DateFirstRealize that = (DateFirstRealize) obj;

        if (this.day() != that.day()) return false;
        if (this.month() != that.month()) return false;
        if (this.year() != that.year()) return false;
        return true;
    }


    public static void main(String[] args) {
        DateFirstRealize dateFirstRealize = new DateFirstRealize(8, 31, 1998);
        System.out.println(dateFirstRealize.hashcode());

    }
}
