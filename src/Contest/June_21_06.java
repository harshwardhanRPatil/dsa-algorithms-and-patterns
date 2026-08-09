package Contest;

public class June_21_06 {

  public int maxDistance(String moves) {
      int l=0,r=0,d=0,u=0,f=0;

      for(char c:moves.toCharArray()){
          if(c=='L') l++;
          if(c=='U') u++;
          if(c=='D') d++;
          if(c=='R') r++;
          if(c=='_') f++;
      }
      return Math.abs(r-l)+Math.abs(u-d)+f;
  }

  public int countValidSubarrays(int[] nums, int x) {
    int ans = 0;
    int n = nums.length;
    for (int i = 0; i < n; i++) {
      int temp = 0;

      for (int j = i; j < n; j++) {
        temp += nums[j];

        int first = temp;
        while (first >= 10) {
          first /= 10;
        }
        int last=temp%10;

        if (first == x && last==x) {
          ans++;
        }
      }
    }

    return ans;
  }

  static void main() {}
}
