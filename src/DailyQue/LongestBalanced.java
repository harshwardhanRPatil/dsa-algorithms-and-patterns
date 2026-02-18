package DailyQue;

import java.util.HashMap;
import java.util.Map;

public class LongestBalanced {
  static void main() {}

  public int longestBalanced(String s) {
      int n = s.length();
      int ans = 0;

      for (int i = 0; i < n; i++) {
          int[] freq = new int[26];
          int maxFreq = 0;
          int distinct = 0;

          for(int j = i; j < n; j++){

              int idx = s.charAt(j) - 'a';

              if(freq[idx] == 0){
                  distinct++;
              }

              freq[idx]++;
              maxFreq = Math.max(maxFreq, freq[idx]);

              // check if balanced
              if(maxFreq * distinct == (j - i + 1)){
                  ans = Math.max(ans, j - i + 1);
              }
          }
      }
      return ans;
  }

  public boolean isBalanced(Map<Character, Integer> map) {

    int freq = -1;

    for (int val : map.values()) {

      if (freq == -1) {
        freq = val;
      } else if (freq != val) {
        return false;
      }
    }

    return true;
  }
}
