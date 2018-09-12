package Chapter1.Interfaces;

/**
 * Provide clients with only the methods they need, and nothing more
 */

public interface IAccumulator {

    void addDataValue(double val);

    double mean();

    String toString();
}
