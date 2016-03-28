package integral;

/**
 * Created by eugeny on 22.03.2016.
 */
public class ThreadedCalculator extends Thread{
    IntegralCalculator calculator;
    Main main;

    public ThreadedCalculator(IntegralCalculator calculator, Main main) {
        this.calculator = calculator;
        this.main = main;
    }

    public void run() {
        double result = calculator.calculate();
        main.sendResult(result);
    }
}
