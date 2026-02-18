package TwoPointers_SlidingWindow_MasterList.TwoPointers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TwoPointerBasic {

  static void main() {}

  public void sortColors(int[] nums) {
    int mid = 0;
    int left = 0;
    int right = nums.length - 1;

    while (mid <= right) {
      if (nums[mid] == 2) {
        int temp = nums[mid];
        nums[mid] = nums[right];
        nums[right] = temp;
        right--;
      } else if (nums[mid] == 0) {
        int temp = nums[left];
        nums[left] = nums[mid];
        nums[mid] = temp;
        mid++;
        left++;
      } else {
        mid++;
      }
    }
  }

  public int findMaxLength(int[] nums) {
    Map<Integer, Integer> map = new HashMap<>();
    map.put(0, -1);
    int sum = 0;
    int max_length = 0;
    for (int i = 0; i < nums.length; i++) {

      if (nums[i] == 0) {
        sum -= 1;
      } else {
        sum += 1;
      }
      if (map.containsKey(sum)) {
        max_length=Math.max(max_length,i-map.get(sum));
      } else {
        map.put(sum, i);
      }
    }

    return max_length;
  }

    public int[] productExceptSelf(int[] nums) {
        int n=nums.length;
         int[] left_prod=new int[n+1];
         int[] right_prod=new int[n+1];
        int[] result = new int[n];

         left_prod[0]=1;
         right_prod[n]=1;

         for(int i=1;i<=n;i++){
             left_prod[i]=left_prod[i-1]*nums[i-1];
         }
        for(int i=n-1;i>=0;i--){
            right_prod[i]=right_prod[i+1]*nums[i];
        }

        for(int i = 0; i < n; i++){
            result[i] = left_prod[i] * right_prod[i+1];
        }


        return result ;
    }
}
