package Contest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

public class May_10_05 {

  public int[] concatWithReverse(int[] nums) {
    int size = nums.length;
    int[] ans = new int[size * 2];

    for (int i = 0; i < size; i++) {
      ans[i] = nums[i];
    }
    int index = 0;
    for (int i = size - 1; i >= 0; i--) {
      ans[size + index] = nums[i];
    }
    return ans;
  }

  public long minArraySum(int[] nums) {

    int size=nums.length;

    int[] ans= nums.clone();

    for(int i=0;i<size;i++){
        int greddy= nums[i];

        for(int j=i;j<size;j++){

            if(nums[i]%nums[j]==0){
                greddy= Math.min(greddy,nums[j]);
            }
        }
        ans[i]=greddy;
      System.out.println(Arrays.toString(ans));
    }

    long result=0;

    for(int i:ans){
        result+=i;
    }
      return result;
  }

  static void main() {}
}
