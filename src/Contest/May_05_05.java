package Contest;

public class May_05_05 {

    // Que B
  public int sumOfPrimesInRange(int n) {

    int ans = 0;
    int number = reverseNumber(n);
    int maxValue = Math.max(n, number);
    int minValue = Math.min(n, number);

    for (int i = minValue; i <= maxValue; i++) {
      if (isPrime(i)) {
        ans += i;
      }
    }
    return ans;
  }

  public static boolean isPrime(int n) {
    if (n < 2) return false;

    for (int i = 2; i * i <= n; i++) { // sqrt optimization
      if (n % i == 0) return false;
    }
    return true;
  }

  public int reverseNumber(int num) {
    int sum = 0;

    while (num > 0) {
      int temp = num % 10;
      sum = sum * 10 + temp;
      num /= 10;
    }
    return sum;
  }
}
