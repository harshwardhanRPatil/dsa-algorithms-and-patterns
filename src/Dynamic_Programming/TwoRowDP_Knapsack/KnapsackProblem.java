package Dynamic_Programming.TwoRowDP_Knapsack;

import java.util.Arrays;
import java.util.Map;

public class KnapsackProblem {
  int[][] perfectSum;
  int[][] CoinChange;

  int[][] MinCoin;
  int INF = 1000000000;
  int[][] MaxCutRod;
  int[][][] wiggleMaxLengthDP;

  static void main() {}

  public int perfectSum(int[] nums, int k) {
    perfectSum = new int[nums.length + 1][k + 1];
    for (int i = 0; i <= nums.length; i++) {
      Arrays.fill(perfectSum[i], -1);
    }

    return perfectSumHelper(nums, k, nums.length);
  }

  public int perfectSumHelper(int[] nums, int k, int index) {

    if (index == 0) {
      return k == 0 ? 1 : 0;
    }
    if (perfectSum[index][k] != -1) return perfectSum[index][k];

    if (k < nums[index - 1]) {
      perfectSum[index][k] = perfectSumHelper(nums, k, index - 1);
    } else {
      perfectSum[index][k] =
          perfectSumHelper(nums, k - nums[index - 1], index - 1)
              + perfectSumHelper(nums, k, index - 1);
    }

    return perfectSum[index][k];
  }

  public int change(int amount, int[] coins) {
    CoinChange = new int[coins.length][amount + 1];
    for (int i = 0; i < coins.length; i++) {
      Arrays.fill(CoinChange[i], -1);
    }
    return fun(coins, coins.length - 1, amount);
  }

  private int fun(int[] coins, int i, int amount) {

    if (amount == 0) {
      return 1;
    }

    if (i < 0) {
      return 0;
    }

    if (CoinChange[i][amount] != -1) {
      return CoinChange[i][amount];
    }

    int notTake = fun(coins, i - 1, amount);

    int take = 0;
    if (coins[i] <= amount) {
      take = fun(coins, i, amount - coins[i]);
    }

    CoinChange[i][amount] = take + notTake;
    return CoinChange[i][amount];
  }

  public int coinChange(int[] coins, int amount) {

    MinCoin = new int[coins.length][amount + 1];

    for (int i = 0; i < coins.length; i++) {
      Arrays.fill(MinCoin[i], -1);
    }

    int ans = funMinCoin(coins, coins.length - 1, amount);

    return ans == INF ? -1 : ans;
  }

  private int funMinCoin(int[] coins, int i, int amount) {

    if (amount == 0) {
      return 0;
    }

    if (i < 0) {
      return INF;
    }

    if (MinCoin[i][amount] != -1) {
      return MinCoin[i][amount];
    }

    int notTake = funMinCoin(coins, i - 1, amount);

    int take = INF;
    if (coins[i] <= amount) {
      take = 1 + funMinCoin(coins, i, amount - coins[i]);
    }

    return MinCoin[i][amount] = Math.min(take, notTake);
  }

  public int cutRod(int[] price) {
    // code here
    MaxCutRod = new int[price.length + 1][price.length + 1];
    for (int i = 0; i <= price.length; i++) {
      Arrays.fill(MaxCutRod[i], -1);
    }
    return helperCutRod(price, price.length, price.length);
  }

  public int helperCutRod(int[] price, int n, int i) {
    if (n == 0 || i == 0) return 0;
    if (MaxCutRod[i][n] != -1) return MaxCutRod[i][n];

    int notTake = helperCutRod(price, n, i - 1);
    int take = 0;
    if (i <= n) {
      take = price[i - 1] + helperCutRod(price, n - i, i);
    }
    MaxCutRod[i][n] = Math.max(notTake, take);

    return MaxCutRod[i][n];
  }

  public int wiggleMaxLength(int[] nums) {
    int n = nums.length;
    if (n <= 1) return n;
      wiggleMaxLengthDP = new int[n + 1][n + 1][2];
    for (int[][] mat : wiggleMaxLengthDP) {
      for (int[] row : mat) {
        Arrays.fill(row, -1);
      }
    }
    return 1
        + Math.max(
            helperwiggleMaxLength(nums, n, n - 1, true),
            helperwiggleMaxLength(nums, n, n - 1, false));
  }

  public int helperwiggleMaxLength(int[] nums, int i, int next, boolean flag) {
    if (next <= 0) {
      return 0;
    }

    if (wiggleMaxLengthDP[i][next][flag ? 1 : 0] != -1) return wiggleMaxLengthDP[i][next][flag ? 1 : 0];
    int ans = 0;
    boolean positive =
        (flag && nums[i - 1] - nums[next - 1] > 0) || (!flag && nums[i - 1] - nums[next - 1] < 0);
    if (positive) {
      ans =
          Math.max(
              helperwiggleMaxLength(nums, next, next - 1, !flag) + 1,
              helperwiggleMaxLength(nums, next, next - 1, flag));
    } else {
      ans = helperwiggleMaxLength(nums, i, next - 1, flag);
    }
    wiggleMaxLengthDP[i][next][flag ? 1 : 0] = ans;
    return wiggleMaxLengthDP[i][next][flag ? 1 : 0];
  }
}
