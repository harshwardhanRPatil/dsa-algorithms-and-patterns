package Binary_Search.Min_Max;

public class Min_Max_Basic {
  public static void main(String args[]) {}

  public int mySqrt(int x) {
    int ans = 0;
    int left = 1;
    int right = x;
    while (left <= right) {
      int mid = left + (right - left) / 2;
        long sq = (long) mid * mid;
      if (sq > x) {
        right = mid - 1;
      } else if (sq < x) {
        left = mid + 1;
        ans = mid;
      } else {
        return mid;
      }
    }
    return ans;
  }
}
