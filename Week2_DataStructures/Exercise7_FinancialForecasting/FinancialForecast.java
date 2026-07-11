public class FinancialForecast {

    // recursive - calculates future value based on growth rate
    // O(n) time, O(n) space due to call stack
    public static double calculateFutureValue(double presentValue, 
                                               double growthRate, 
                                               int years) {
        // base case
        if (years == 0) {
            return presentValue;
        }
        // recursive case - grow by rate each year
        return calculateFutureValue(presentValue * (1 + growthRate), 
                                    growthRate, 
                                    years - 1);
    }

    // memoization - optimized version avoids recalculation
    // O(n) time, O(1) space
    public static double calculateFutureValueOptimized(double presentValue,
                                                        double growthRate,
                                                        int years) {
        return presentValue * Math.pow((1 + growthRate), years);
    }
}