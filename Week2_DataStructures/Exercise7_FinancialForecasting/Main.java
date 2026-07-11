public class Main {
    public static void main(String[] args) {

        double presentValue = 10000.00;
        double growthRate = 0.05; // 5% annual growth
        int years = 10;

        System.out.println("-- Financial Forecasting --");
        System.out.println("Present Value : $" + presentValue);
        System.out.println("Growth Rate   : " + (growthRate * 100) + "%");
        System.out.println("Years         : " + years);

        // recursive solution
        System.out.println("\n-- Recursive Solution --");
        double futureValue = FinancialForecast.calculateFutureValue(
                                presentValue, growthRate, years);
        System.out.printf("Future Value after %d years: $%.2f%n", years, futureValue);

        // show year by year
        System.out.println("\n-- Year by Year Forecast --");
        for (int i = 1; i <= years; i++) {
            double value = FinancialForecast.calculateFutureValue(
                            presentValue, growthRate, i);
            System.out.printf("Year %2d: $%.2f%n", i, value);
        }

        // optimized solution
        System.out.println("\n-- Optimized Solution (Math.pow) --");
        double optimizedValue = FinancialForecast.calculateFutureValueOptimized(
                                    presentValue, growthRate, years);
        System.out.printf("Future Value after %d years: $%.2f%n", years, optimizedValue);

        System.out.println("\n-- Time Complexity --");
        System.out.println("Recursive : O(n) - n recursive calls");
        System.out.println("Optimized : O(1) - single Math.pow calculation");
    }
}