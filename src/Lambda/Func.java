package Lambda;

public interface Func<T, R, K> {
    K apply(T value, R value2);
}
