package Dynamic_Programming.OneRowDP;

import java.lang.reflect.Array;
import java.util.Arrays;

class OneRowDPQueSolution {
  int n = 1000;
  int[] dp;

  // Constructor to initialize dp array
  public OneRowDPQueSolution() {
    dp = new int[n + 1];
    Arrays.fill(dp, -1);

    dp[0] = 0;
    dp[1] = 1;
    // No need to fill with 0 because new int[] is already 0
  }

  public int fib(int n) {

    if (n == 0 || n == 1) return n;
    if (dp[n] != 0) return dp[n];
    dp[n] = fib(n - 1) + fib(n - 2);
    return dp[n];
  }

  public int climbStairs(int n) {
    if (n == 0 || n == 1) return dp[n];
    if (dp[n] != 0) return dp[n];
    dp[n] = climbStairs(n - 1) + climbStairs(n - 2);
    return dp[n];
  }

  public int minCostClimbingStairs(int[] cost) {

    return Math.min(dpHelper(cost, 0), dpHelper(cost, 1));
  }

  public int dpHelper(int[] cost, int position) {
    if (position > cost.length) {
      return 0;
    }
    if (dp[position] != 0) return dp[position];

    dp[position] = Math.min(dpHelper(cost, position + 1), dpHelper(cost, position + 1));
    return dp[position];
  }

  public int rob(int[] nums) {
    int n = nums.length;
    if (n == 1) return nums[0];
    dp[0] = nums[0];
    dp[1] = Math.max(nums[1], nums[0]);

    return helperrob(nums, n - 1);
  }

  public int helperrob(int[] nums, int position) {
    if (position < 0) {
      return 0;
    }
    if (dp[position] != -1) return dp[position];

    dp[position] =
        Math.max(helperrob(nums, position - 2) + nums[position], helperrob(nums, position - 1));
    return dp[position];
  }

  public int tribonacci(int n) {
    if (n <= 1) return n;
    if (n == 2) return 1;
    dp[0] = 0;
    dp[1] = 1;
    dp[2] = 1;

    return tribonacciHelper(n);
  }

  public int tribonacciHelper(int n) {
    if (dp[n] != -1) return dp[n];

    dp[n] = tribonacciHelper(n - 1) + tribonacciHelper(n - 2) + tribonacciHelper(n - 3);
    return dp[n];
  }

  public int maxProfit(int[] prices) {
    return maxProfitHelper(prices, true, 0, 0);
  }

  public int maxProfitHelper(int[] prices, boolean buy, int sold, int position) {
    if (buy) {
      return Math.max(
          maxProfitHelper(prices, !buy, prices[position], position + 1),
          maxProfitHelper(prices, buy, 0, position + 1));
    } else {
      int profit =
          Math.max(maxProfitHelper(prices, buy, sold, position + 1), sold - prices[position]);
      return profit;
    }
  }
    public int uniquePaths(int m, int n) {
        int[][] dp = new int[m + 1][n + 1];
        dp[1][1] = 1;

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (i == 1 && j == 1) continue; // skip start cell

                dp[i][j] = dp[i][j - 1] + dp[i - 1][j];
            }
        }

        return dp[m][n];
    }

    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        int[][] dp = new int[m + 1][n + 1];
        if (obstacleGrid[0][0] == 1) return 0;
        dp[1][1] = 1;

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (i == 1 && j == 1) continue; // skip start cell

                if (obstacleGrid[i - 1][j - 1] == 1) {
                    dp[i][j] = 0;
                } else {
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                }
            }
        }

        return dp[m][n];

    }
}

public class OneRowDPQue {

  public static void main(String[] args) {}
}
