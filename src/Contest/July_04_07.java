package Contest;

import java.util.Arrays;
import java.util.HashMap;

public class July_04_07 {


    int[][][][][] dp;

  // A -: https://leetcode.com/contest/biweekly-contest-186/problems/unique-middle-element/

  public boolean isMiddleElementUnique(int[] nums) {
    HashMap<Integer, Integer> map = new HashMap<>();

    for (int i = 0; i < nums.length; i++) {
      map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
    }
    System.out.println(map);
    return map.get(nums[(nums.length / 2) + 1]) == 1 ? true : false;
  }

  // B->
  // https://leetcode.com/contest/biweekly-contest-186/problems/maximum-valid-pair-sum/description/

  public int maxValidPairSum(int[] nums, int k) {
    int ans = Integer.MIN_VALUE;
    int windowMax = Integer.MIN_VALUE;
    for (int j = k; j < nums.length; j++) {
      windowMax = Math.max(windowMax, nums[j - k]);
      ans = Math.max(ans, nums[j] + windowMax);
    }
    return ans;
  }

    // https://leetcode.com/contest/biweekly-contest-186/problems/count-distinct-ways-to-form-target-from-two-strings/
    public int interleaveCharacters(String word1, String word2, String target) {
        dp = new int[word1.length() + 1][word2.length() + 1][target.length() + 1][2][2];

        for (int i = 0; i < word1.length()+1; i++) {
            for (int j = 0; j < word2.length()+1; j++) {
                for (int k = 0; k < target.length()+1; k++) {
                    for (int a = 0; a < 2; a++) {
                        Arrays.fill(dp[i][j][k][a], -1);
                    }
                }
            }
        }
        return dfs(word1,word2,target,0, 0, 0, 0, 0);
    }
    private int dfs(String s1,String s2, String target,int i, int j, int k, int usedS1, int usedS2) {

        if (k == target.length()) {
            return (usedS1 == 1 && usedS2 == 1) ? 1 : 0;
        }

        if (i == s1.length() && j == s2.length()) {
            return 0;
        }

        if (dp[i][j][k][usedS1][usedS2] != -1) {
            return dp[i][j][k][usedS1][usedS2];
        }
        int ans = 0;
        if (i < s1.length()) {
            ans += dfs(s1,s2,target,i + 1, j, k, usedS1, usedS2);
        }
        if (j < s2.length()) {
            ans += dfs(s1,s2,target,i, j + 1, k, usedS1, usedS2);
        }
        if (i < s1.length() && s1.charAt(i) == target.charAt(k)) {
            ans += dfs(s1,s2,target,i + 1, j, k + 1, 1, usedS2);
        }
        if (j < s2.length() && s2.charAt(j) == target.charAt(k)) {
            ans += dfs(s1,s2,target,i, j + 1, k + 1, usedS1, 1);
        }

        return dp[i][j][k][usedS1][usedS2] = ans;
    }
  static void main() {}
}
