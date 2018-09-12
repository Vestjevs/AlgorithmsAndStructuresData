package Chapter1.Interfaces;

import java.util.zip.DataFormatException;

/**
 * Provide clients with only the methods they need, and nothing more
 */

public interface IDate {

    int month() throws DataFormatException;

    int day();

    int year();

    public String toString();
}
