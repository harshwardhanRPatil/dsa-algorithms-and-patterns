package Contest;

import kotlin.time.TimeMark;

public class April_11_04 {
  static void main() {}

    // Que A
  public String trafficSignal(int timer) {
    if (timer == 0) return "Green";
    if (timer == 30) return "Orange";
    if (timer <= 90 && timer > 30) return "Red";
    return "Invalid";
  }

  // QUE B
  public int minOperations(int[] nums) {
    int ans = 0;
    for (int i = 0; i < nums.length; i++) {
      int value = nums[i];
      if (i % 2 == 0) {

        while (!prime(value)) {
          value++;
          ans++;
        }
      } else {
        while (prime(value)) {
          value++;
          ans++;
        }
      }
    }
    return ans;
  }

  public boolean prime(int n) {
    if (n <= 1) return false;
    for (int i = 2; i * i <= n; i++) {
      if (n % i == 0) return false;
    }
    return true;
  }
// Que B
  public int countDigitOccurrences(int[] nums, int digit) {
    int ans = 0;
    for (int i : nums) {
      while (i > 0) {
        if (i % 10 == digit) ans++;
        i = i / 10;
      }
    }
    return ans;
  }
}
