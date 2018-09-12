package Chapter1;

import Chapter1.Interfaces.IDate;

import java.util.zip.DataFormatException;

public class DateSecondRealize implements IDate {
    private final int month;
    private final int day;
    private final int year;

    DateSecondRealize(int month, int day, int year) {
        this.month = month;
        this.day = day;
        this.year = year;
    }

    @Override
    public int month() {
        if (month > 12 || month < 0) {
            try {
                throw new DataFormatException();
            } catch (DataFormatException e) {
                e.printStackTrace();
            }
        } else return month;

        return -1;
    }

    @Override
    public int day() {
        if (day > 32 || day < 0) {
            try {
                throw new DataFormatException();
            } catch (DataFormatException e) {
                e.printStackTrace();
            }
        } else
            return day;
        return -1;
    }

    @Override
    public int year() {
        if (year < 0) {
            try {
                throw new DataFormatException();
            } catch (DataFormatException e) {
                e.printStackTrace();
            }
        } else
            return year;
        return -1;
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

        DateSecondRealize that = (DateSecondRealize) obj;

        if (this.day != that.day) return false;
        if (this.month != that.month) return false;
        if (this.year != that.year) return false;
        return true;
    }
}
