package Dynamic_Programming.TwoRowDP_Knapsack;

import java.lang.reflect.Array;
import java.util.Arrays;

public class LongestCommonSubsequenceProblem {

  int[][] LCSHelper;
  int[][] SCSHelper;
  int[][] LSCHelper;
  int[][] dp;
  int[][] isMatchDP;
  int[][] LRSHelper;
  int[] RobHelper;

  static void main() {}

  public int longestCommonSubsequence(String text1, String text2) {
    LCSHelper = new int[text1.length() + 1][text2.length() + 1];
    for (int i = 0; i < text1.length() + 1; i++) {
      Arrays.fill(LCSHelper[i], -1);
    }
    int ans = 0;
    ans = helperLSC(text1, text2, text1.length(), text2.length());

    int n = text1.length();
    int m = text2.length();

    StringBuilder stringBuilder = new StringBuilder();
    while (n > 0) {
      while (m > 0) {
        if (LCSHelper[n][m] > LCSHelper[n - 1][m] && LCSHelper[n][m] > LCSHelper[n][m - 1]) {
          stringBuilder.append(text1.charAt(n - 1));
          n--;
          m--;
        } else if (LCSHelper[n][m] == LCSHelper[n - 1][m]) {
          m--;
        } else {
          n--;
        }
      }
    }
    System.out.println(stringBuilder.reverse());
    return ans;
  }

  public int helperLSC(String s1, String s2, int n, int m) {
    if (n == 0 || m == 0) return 0;
    if (LCSHelper[n][m] != -1) return LCSHelper[n][m];

    if (s1.charAt(n - 1) == s2.charAt(m - 1)) {
      LCSHelper[n][m] = 1 + helperLSC(s1, s2, n - 1, m - 1);
    } else {
      LCSHelper[n][m] = Math.max(helperLSC(s1, s2, n - 1, m), helperLSC(s1, s2, n, m - 1));
    }

    return LCSHelper[n][m];
  }

  public int longestPalindromeSubseq(String text1) {

    LCSHelper = new int[text1.length() + 1][text1.length() + 1];
    for (int i = 0; i < text1.length() + 1; i++) {
      Arrays.fill(LCSHelper[i], -1);
    }
    int ans = 0;
    ans =
        helperLSC(
            text1, new StringBuilder(text1).reverse().toString(), text1.length(), text1.length());
    return ans;
  }

  /*
  https://www.geeksforgeeks.org/problems/longest-common-substring1452/1
    this approch not work as it need to have the lenght and we need to go with the bottom-up only aproch else we need to ahve 3-d array
    public int longCommSubstr(String s1, String s2) {

        int n = s1.length();
        int m = s2.length();

        int[][] dp = new int[n + 1][m + 1];

        int ans = 0;

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {

                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                    ans = Math.max(ans, dp[i][j]);
                } else {
                    dp[i][j] = 0;
                }
            }
        }

        return ans;
    }
   */
  public int longCommSubstr(String s1, String s2) {
    LSCHelper = new int[s1.length() + 1][s2.length() + 1];
    return helperLCS(s1, s2, s1.length(), s2.length(), 0);
  }

  public int helperLCS(String s1, String s2, int n, int m, int lenght) {
    if (n == 0 || m == 0) return lenght;
    if (LSCHelper[n][m] != -1) return LCSHelper[n][m];

    if (s1.charAt(n - 1) == s2.charAt(m - 1)) {
      LSCHelper[n][m] = helperLCS(s1, s2, n - 1, m - 1, lenght + 1);
    }
    LSCHelper[n][m] =
        Math.max(lenght, Math.max(helperLCS(s1, s2, n - 1, m, 0), helperLCS(s1, s2, n, m - 1, 0)));

    return LSCHelper[n][m];
  }

  public String shortestCommonSupersequence(String str1, String str2) {

    int n = str1.length();
    int m = str2.length();
    SCSHelper = new int[n + 1][m + 1];

    for (int i = 0; i < n + 1; i++) {
      Arrays.fill(SCSHelper[i], -1);
    }

    for (int i = 0; i < n + 1; i++) {
      SCSHelper[i][0] = i;
    }
    for (int j = 0; j < m + 1; j++) {
      SCSHelper[0][j] = j;
    }

    helperSCS(str1, str2, str1.length(), str2.length());

    for (int[] row : SCSHelper) {
      System.out.println(Arrays.toString(row));
    }

    StringBuilder stringBuilder = new StringBuilder();
    while (n > 0 && m > 0) {
      if (str1.charAt(n - 1) == str2.charAt(m - 1)) {
        stringBuilder.insert(0, str1.charAt(n - 1));
        n--;
        m--;
      } else if (SCSHelper[n][m - 1] > SCSHelper[n - 1][m]) {
        stringBuilder.insert(0, str1.charAt(n - 1));
        n--;
      } else {
        stringBuilder.insert(0, str2.charAt(m - 1));
        m--;
      }
    }
    if (n == 0 && m != 0) {
      stringBuilder.insert(0, str2.substring(0, m));
    }
    if (m == 0 && n != 0) {
      stringBuilder.insert(0, str1.substring(0, n));
    }

    return stringBuilder.toString();
  }

  public int helperSCS(String s1, String s2, int n, int m) {
    if (n == 0 && m == 0) return 0;

    if ((n == 0 && m != 0) || (n != 0 && m == 0)) {
      return Math.max(n, m);
    }
    if (SCSHelper[n][m] != -1) return SCSHelper[n][m];
    if (s1.charAt(n - 1) == s2.charAt(m - 1)) {
      SCSHelper[n][m] = 1 + helperSCS(s1, s2, n - 1, m - 1);
    } else {
      SCSHelper[n][m] = 1 + Math.min(helperSCS(s1, s2, n - 1, m), helperSCS(s1, s2, n, m - 1));
    }
    return SCSHelper[n][m];
  }

  // https://leetcode.com/problems/delete-operation-for-two-strings/
  public int minDistance(String word1, String word2) {

    for (int i = 0; i < word1.length(); i++) {
      Arrays.fill(SCSHelper[i], -1);
    }
    return helperMD(word1, word2, word1.length(), word2.length());
  }

  public int helperMD(String s1, String s2, int n, int m) {
    if (n == 0 && m == 0) return 0;

    if ((n == 0 && m != 0) || (n != 0 && m == 0)) {
      return Math.max(n, m);
    }
    if (SCSHelper[n][m] != -1) return SCSHelper[n][m];
    if (s1.charAt(n - 1) == s2.charAt(m - 1)) {
      SCSHelper[n][m] = helperMD(s1, s2, n - 1, m - 1);
    } else {
      SCSHelper[n][m] = 1 + Math.min(helperMD(s1, s2, n - 1, m), helperMD(s1, s2, n, m - 1));
    }
    return SCSHelper[n][m];
  }

  // https://www.geeksforgeeks.org/problems/minimum-number-of-deletions-and-insertions0209/1

  public int minOperationsIandD(String word1, String word2) {
    SCSHelper = new int[word1.length() + 1][word2.length() + 1];
    for (int i = 0; i < word1.length() + 1; i++) {
      Arrays.fill(SCSHelper[i], -1);
    }
    return helperMD(word1, word2, word1.length(), word2.length());
  }

  public int minInsertions(String s) {
    SCSHelper = new int[s.length() + 1][s.length() + 1];
    for (int i = 0; i < s.length() + 1; i++) {
      Arrays.fill(SCSHelper[i], -1);
    }

    for (int i = 0; i < s.length() + 1; i++) {
      SCSHelper[i][0] = i;
    }
    for (int i = 0; i < s.length() + 1; i++) {
      SCSHelper[0][i] = i;
    }
    String s1 = new StringBuilder(s).reverse().toString();
    for (int i = 1; i < s.length() + 1; i++) {
      for (int j = 1; j < s.length() + 1; j++) {
        if (s.charAt(i - 1) == s1.charAt(j - 1)) {
          SCSHelper[i][j] = SCSHelper[i - 1][j - 1];
        } else {
          SCSHelper[i][j] = 1 + Math.min(SCSHelper[i - 1][j], SCSHelper[i][j - 1]);
        }
      }
    }
    // return helperminInsertions(s, s1, s.length(), s.length())/2;
    return SCSHelper[s.length()][s.length()] / 2;
  }

  public int helperminInsertions(String s1, String s2, int n, int m) {
    if (n == 0 && m == 0) return 0;

    if ((n == 0 && m != 0) || (n != 0 && m == 0)) {
      return Math.max(n, m);
    }

    if (SCSHelper[n][m] != -1) return SCSHelper[n][m];
    if (s1.charAt(n - 1) == s2.charAt(m - 1)) {
      SCSHelper[n][m] = helperMD(s1, s2, n - 1, m - 1);
    } else {
      SCSHelper[n][m] = 1 + Math.min(helperMD(s1, s2, n - 1, m), helperMD(s1, s2, n, m - 1));
    }
    return SCSHelper[n][m];
  }

  public boolean isSubsequence(String s, String t) {
    int n = s.length();
    int m = t.length();
    dp = new int[n + 1][m + 1];
    for (int i = 0; i < n + 1; i++) {
      Arrays.fill(dp[i], -1);
    }
    return helper(s, t, n, m);
  }

  private boolean helper(String s, String t, int i, int j) {

    if (i == 0) return true;
    if (j == 0) return false;
    if (dp[i][j] != -1) return dp[i][j] == 1;
    boolean ans;
    if (s.charAt(i - 1) == t.charAt(j - 1)) {
      ans = helper(s, t, i - 1, j - 1);
    } else {
      ans = helper(s, t, i, j - 1);
    }

    dp[i - 1][j - 1] = ans ? 1 : 0;

    return ans;
  }

  // https://leetcode.com/problems/wildcard-matching/

  public boolean isMatch(String s, String p) {
    isMatchDP = new int[s.length() + 1][p.length() + 1];

    for (int i = 0; i < s.length() + 1; i++) {
      Arrays.fill(isMatchDP[i], -1);
    }

    return helperisMatch(s, p, s.length(), p.length()) == 1;
  }

  public int helperisMatch(String s1, String s2, int n, int m) {
    if (n == 0 && m == 0) {
      return 1;
    }
    if (n == 0) {
      if (isValid(s2, m)) {
        return 1;
      } else {
        return 0;
      }
    } else if (n != 0 && m == 0) {
      return 0;
    }

    if (isMatchDP[n][m] != -1) {
      return isMatchDP[n][m];
    }
    int ans = 0;
    if (s1.charAt(n - 1) == s2.charAt(m - 1) || s2.charAt(m - 1) == '?') {
      ans = helperisMatch(s1, s2, n - 1, m - 1);
    } else if (s2.charAt(m - 1) == '*') {
      ans = Math.max(helperisMatch(s1, s2, n, m - 1), helperisMatch(s1, s2, n - 1, m));
    }
    isMatchDP[n][m] = ans;

    return isMatchDP[n][m];
  }

  // we only chekc the * because we need a empty string and ? mean some value
  public boolean isValid(String s, int m) {
    for (int i = 0; i < m; i++) {
      if (s.charAt(i) != '*') {
        return false;
      }
    }
    return true;
  }

  // https://www.geeksforgeeks.org/problems/longest-repeating-subsequence2004/1

  public int LongestRepeatingSubsequence(String s) {
    // code here
    LRSHelper = new int[s.length() + 1][s.length() + 1];
    for (int i = 0; i < s.length() + 1; i++) {
      Arrays.fill(LRSHelper[i], -1);
    }
    return helperLongestRepeatingSubsequence(s, s.length(), s.length());
  }

  public int helperLongestRepeatingSubsequence(String s, int n, int m) {
    if (n <= 0 || m <= 0) {
      return 0;
    }
    if (LRSHelper[n][m] != -1) {
      return LRSHelper[n][m];
    }
    if (s.charAt(n - 1) == s.charAt(m - 1) && m != n) {
      LRSHelper[n][m] = 1 + helperLongestRepeatingSubsequence(s, n - 1, m - 1);
    } else {
      LRSHelper[n][m] =
          Math.max(
              helperLongestRepeatingSubsequence(s, n - 1, m),
              helperLongestRepeatingSubsequence(s, n, m - 1));
    }
    return LRSHelper[n][m];
  }

  public int rob(int[] nums) {
    RobHelper = new int[nums.length + 1];

    Arrays.fill(RobHelper, -1);
    return helperRob(nums, nums.length);
  }

  public int helperRob(int[] nums, int n) {
    if (n <= 0) {
      return 0;
    }
    if (RobHelper[n] != -1) return RobHelper[n];
    RobHelper[n] = Math.max(nums[n - 1] + helperRob(nums, n - 2), helperRob(nums, n - 1));
    return RobHelper[n];
  }

  public int robII(int[] nums) {
    if (nums.length == 1) return nums[0];
    RobHelper = new int[nums.length + 1];
    Arrays.fill(RobHelper, -1);
    int left = helperRobII(nums, nums.length, 2);
    Arrays.fill(RobHelper, -1);
    int right = helperRobII(nums, nums.length - 1, 1);
    return Math.max(left, right);
  }

  public int helperRobII(int[] nums, int n, int start) {
    if (n < start) {
      return 0;
    }
    if (RobHelper[n] != -1) return RobHelper[n];
    RobHelper[n] =
        Math.max(nums[n - 1] + helperRobII(nums, n - 2, start), helperRobII(nums, n - 1, start));
    return RobHelper[n];
  }


//    public int rob(TreeNode root) {
//        return Math.max(helper(root, true), helper(root, false));
//    }
//
//    public int helper(TreeNode root, boolean canTake) {
//
//        if (root == null) {
//            return 0;
//        }
//        if (!memo.containsKey(root)) {
//            memo.put(root, new int[] { -1, -1 });
//        }
//
//        int state = canTake ? 1 : 0;
//
//        if (memo.get(root)[state] != -1) {
//            return memo.get(root)[state];
//        }
//        int ans;
//
//        if (canTake) {
//            int take = root.val
//                    + helper(root.left, false)
//                    + helper(root.right, false);
//
//            int skip = helper(root.left, true)
//                    + helper(root.right, true);
//
//            ans = Math.max(take, skip);
//
//        } else {
//
//            ans = helper(root.left, true)
//                    + helper(root.right, true);
//        }
//        memo.get(root)[state] = ans;
//        return ans;
//    }
}
