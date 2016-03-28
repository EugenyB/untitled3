package integral;

import java.util.function.DoubleUnaryOperator;
import java.util.stream.IntStream;

/**
 * Created by eugeny on 22.03.2016.
 */
public class IntegralCalculator {
    double a;
    double b;
    int n;
    DoubleUnaryOperator f;
    double h;

    public IntegralCalculator(double a, double b, int n, DoubleUnaryOperator f) {
        this.a = a;
        this.b = b;
        this.n = n;
        this.f = f;
        h = (b-a)/n;
    }

    public double calculate() {
        return IntStream.range(0, n).mapToDouble(i -> a + i * h).map(f).sum() * h;
    }
}
