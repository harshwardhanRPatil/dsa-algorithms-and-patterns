package Dynamic_Programming.OneRowDP.TwoRowDPQue;

import java.util.Arrays;

class TwoRowDPQueSolution {
  int[][] dp;

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
    public int minPathSum(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;

        int[][] dp = new int[n][m];

        dp[0][0] = grid[0][0];

        // first row
        for (int j = 1; j < m; j++) {
            dp[0][j] = dp[0][j - 1] + grid[0][j];
        }

        // first column
        for (int i = 1; i < n; i++) {
            dp[i][0] = dp[i - 1][0] + grid[i][0];
        }

        // rest cells
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                dp[i][j] = grid[i][j] + Math.min(dp[i - 1][j], dp[i][j - 1]);
            }
        }

        return dp[n - 1][m - 1];
    }


    public int coinChange(int[] coins, int amount) {
    int m = coins.length;
    int[][] dp = new int[m + 1][amount + 1];

    for (int i = 0; i < amount + 1; i++) {
      dp[0][i] = Integer.MAX_VALUE - 1;
      ;
    }
    for (int i = 1; i < m + 1; i++) {
      dp[i][0] = 0;
    }

    for (int i = 1; i < m + 1; i++) {
      for (int j = 1; j < amount + 1; j++) {
        if (j < coins[i - 1]) {
          dp[i][j] = dp[i - 1][j];
        } else {
          dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - coins[i - 1]] + 1);
        }
      }
    }

    return dp[m][amount] == Integer.MAX_VALUE - 1 ? -1 : dp[m][amount];
  }

  public int change(int amount, int[] coins) {
    int m = coins.length;
    int[][] dp = new int[m + 1][amount + 1];

    for (int i = 0; i < amount + 1; i++) {
      dp[0][i] = 0;
      ;
    }
    for (int i = 1; i < m + 1; i++) {
      dp[i][0] = 1;
    }

    for (int i = 1; i < m + 1; i++) {
      for (int j = 1; j < amount + 1; j++) {
        if (j < coins[i - 1]) {
          dp[i][j] = dp[i - 1][j];
        } else {
          dp[i][j] = dp[i - 1][j] + dp[i][j - coins[i - 1]];
        }
      }
    }

    return dp[m][amount] == 0 ? 0 : dp[m][amount];
  }

  public int lengthOfLIS(int[] nums) {
    return lengthOfLIShelper(nums, 0, Integer.MIN_VALUE);
  }

  public int lengthOfLIShelper(int[] nums, int position, int current) {
    if (position > nums.length - 1) {
      return 0;
    }
    if (current < nums[position]) {
      return Math.max(
          lengthOfLIShelper(nums, position + 1, current),
          lengthOfLIShelper(nums, position + 1, nums[position]) + 1);
    } else {
      return lengthOfLIShelper(nums, position + 1, current);
    }
  }

  public int lengthOfLISII(int[] nums, int k) {
    int n = nums.length;
    dp = new int[n][n + 1];

    for (int i = 0; i < n; i++) {
      Arrays.fill(dp[i], -1);
    }

    return lengthOfLIShelper(nums, 0, -1, k);
  }

  public int lengthOfLIShelper(int[] nums, int current, int prev, int k) {
    if (current == nums.length) {
      return 0;
    }
    if (dp[current][prev + 1] != -1) {
      return dp[current][prev + 1];
    }
    int notTake = lengthOfLIShelper(nums, current + 1, prev, k);
    int tle = 0;
    if (prev == -1 || (nums[current] > nums[prev] && nums[current] - nums[prev] <= k)) {
      tle = 1 + lengthOfLIShelper(nums, current + 1, current, k);
    }
    dp[current][prev + 1] = Math.max(tle, notTake);

    return dp[current][prev + 1];
  }

    public int longestCommonSubsequence(String text1, String text2) {
        int n = text1.length();
        int m = text2.length();
        int[][] dp = new int[n + 1][m + 1];


        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        return dp[n][m];
    }
    public boolean canPartition(int[] nums) {
        int sum = 0;
        for (int x : nums) sum += x;

        if (sum % 2 != 0) return false;   // step 1: odd check
        int target = sum / 2;
        Boolean[][] dp = new Boolean[nums.length][target + 1];
        return dfs(nums, 0, target, dp);
    }

    private boolean dfs(int[] nums, int index, int target, Boolean[][] dp) {
        // base cases
        if (target == 0) return true;          // found subset
        if (index == nums.length || target < 0) return false;
        if (dp[index][target] != null) return dp[index][target];

        // take OR skip
        boolean take = dfs(nums, index + 1, target - nums[index],dp);
        boolean skip = dfs(nums, index + 1, target,dp);

        dp[index][target] = take || skip;
        return dp[index][target];
    }

    public int numDecodings(String s) {
        int[] dp = new int[s.length()];
        for (int i = 0; i < dp.length; i++) dp[i] = -1;
        return decodeFrom(s, 0,dp);
    }

    private int decodeFrom(String s, int index, int[] dp) {
        if (index == s.length()) return 1;

        if (s.charAt(index) == '0') return 0;

        if (dp[index] != -1) return dp[index];

        int ways = 0;

        ways += decodeFrom(s, index + 1, dp);

        if (index + 1 < s.length()) {
            int num = (s.charAt(index) - '0') * 10 + (s.charAt(index + 1) - '0');
            if (num >= 10 && num <= 26) {
                ways += decodeFrom(s, index + 2, dp);
            }
        }

        dp[index] = ways;
        return ways;
    }
}

public class TwoRowDPQue {}
