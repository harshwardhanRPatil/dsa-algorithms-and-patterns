package DailyQue;

import java.util.HashSet;
import java.util.Set;

class HasAllCodesSolution {
  public boolean hasAllCodes(String s, int k) {
    Set<String> set = new HashSet<>();
    for (int i = 0; i <= s.length() - k; i++) {
      String sub = s.substring(i, i + k);
      set.add(sub);
    }
    return set.size() == (1 << k);
  }

  public int sumOddLengthSubarrays(int[] arr) {

    int sum = 0;
    for (int i = 0; i < arr.length; i++) {
      int temp = 0;
      for (int j = i; j < arr.length; j++) {
        temp += arr[j];
        if ((j - i + 1) % 2 == 1) sum += temp;
      }
    }
    return sum;
  }
}

public class HasAllCodes {
  static void main() {}
}
