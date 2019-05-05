package Lambda;

import java.util.stream.LongStream;

public class Main {
     Func<Long, Long, Long> factorialRange = (left, right) -> LongStream.rangeClosed(left, right).reduce(1, (a, b) -> a * b);
}
