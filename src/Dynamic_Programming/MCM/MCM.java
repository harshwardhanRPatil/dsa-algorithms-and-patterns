package Dynamic_Programming.MCM;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class MCM {
  int[][] HelperminCut;
  Map<String, Boolean> helperisScrambleMap = new HashMap<>();

  boolean[][] pal;
  int[] HSMDP;

  public int minCut(String s) {
    HelperminCut = new int[s.length() + 1][s.length() + 1];

    for (int i = 0; i < s.length() + 1; i++) {
      Arrays.fill(HelperminCut[i], -1);
    }

    // one fix is need that we need to hav ethe hepper that solve the parathens so we can do the
    // O(n^2)

    int n = s.length();
    pal = new boolean[n][n];

    for (int i = n - 1; i >= 0; i--) {
      for (int j = i; j < n; j++) {

        if (s.charAt(i) == s.charAt(j)) {

          if (j - i <= 2) pal[i][j] = true;
          else pal[i][j] = pal[i + 1][j - 1];
        }
      }
    }
    return HelperminCut(s, 0, s.length() - 1);
  }

  public int HelperminCut(String s, int i, int j) {
    if (i == j || i > j || pal[i][j]) {
      return 0;
    }
    if (HelperminCut[i][j] != -1) return HelperminCut[i][j];

    int temp = Integer.MAX_VALUE;
    int right = 0;

    // this work but we get LTE
    for (int k = i; k < j; k++) {
      if (!pal[i][k]) continue;
      //      temp = Math.min(temp, HelperminCut(s, i, k) + HelperminCut(s, k + 1, j) + 1);
      if (HelperminCut[k + 1][j] != -1) {
        right = HelperminCut[k + 1][j];
      } else {
        right = HelperminCut(s, k + 1, j);
      }
      temp = Math.min(temp, right + 1);
    }

    return HelperminCut[i][j] = temp;
  }

  public boolean isPalindrome(String s, int left, int right) {
    while (left < right) {
      if (s.charAt(left) != s.charAt(right)) {
        return false;
      }
      left++;
      right--;
    }

    return true;
  }

  public boolean isScramble(String s1, String s2) {
    if (s1.length() != s2.length()) {
      return false;
    }
    if (s1.isEmpty() && s2.isEmpty()) return false;

    return helperisScramble(s1, s2);
  }

  public boolean helperisScramble(String s1, String s2) {
    if (s1.contains(s2)) {
      return true;
    }
    if (s1.length() <= 1) {
      return false;
    }
    StringBuilder sb = new StringBuilder();
    sb.append(s1);
    sb.append("");
    sb.append(s2);

    String s = sb.toString();

    if (helperisScrambleMap.containsKey(s)) {
      return helperisScrambleMap.get(s);
    }

    int n = s1.length();
    boolean flag = false;

    for (int k = 1; k < n; k++) {
      if ((helperisScramble(s1.substring(0, k), s2.substring(n - k, n))
              && helperisScramble(s1.substring(k, n), s2.substring(0, n - k)))
          || (helperisScramble(s1.substring(0, k), s2.substring(0, k))
              && helperisScramble(s1.substring(k, n), s2.substring(k, n)))) {
        flag = true;
        break;
      }
    }

    return helperisScrambleMap.put(s, flag);
  }

  public int integerBreak(int n) {
    HSMDP = new int[n + 1];
    Arrays.fill(HSMDP, -1);
    return integerBreakhelper(n);
  }

  int integerBreakhelper(int n) {
    if (n == 1) return 1;
    if (HSMDP[n] != -1) return HSMDP[n];
    int ans = 0;

    for (int i = 1; i < n; i++) {

      int left = Math.max(i, integerBreakhelper(i));
      int right = Math.max(n - i, integerBreakhelper(n - i));

      ans = Math.max(ans, left * right);
    }

    return HSMDP[n] = ans;
  }

  static void main() {}
}
