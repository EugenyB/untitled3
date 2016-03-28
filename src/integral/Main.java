package integral;

/**
 * Created by eugeny on 22.03.2016.
 */
public class Main {

    double total = 0;
    int runningThreads = 0;

    public static void main(String[] args) {
        new Main().run();
    }

    private void run() {
        double a = 0;
        double b = Math.PI;
        int n = 100_000_000;
        long start = System.currentTimeMillis();
        int totalThreads = 100;
        double delta = (b-a)/totalThreads;
        runningThreads = totalThreads;
        for (int i = 0; i < totalThreads; i++) {
            new ThreadedCalculator(
                    new IntegralCalculator(a+i*delta, a+(i+1)*delta, n/totalThreads, MyFunction::f),
                    this
            ).start();
        }
//        IntegralCalculator calculator = new IntegralCalculator(a,b,n,MyFunction::f);
//        double result = calculator.calculate();

        try {
            synchronized (this) {
                while (runningThreads > 0) {
                    wait();
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        long finish = System.currentTimeMillis();
        System.out.println(total);
        System.out.println(finish-start);
    }

    public synchronized void sendResult(double result) {
        total += result;
        runningThreads--;
        notify();
    }
}
