package Contest;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class Mar_14_03 {
  static void main() {}

  // Que A
  public int firstUniqueEven(int[] nums) {

    Map<Integer, Integer> map = new HashMap<>();
    int ans = -1;
    for (int i : nums) {
      map.put(i, map.getOrDefault(i, 0) + 1);
    }
    for (int i : nums) {
      if (i % 2 == 0 && map.get(i) == 1) {
        return i;
      }
    }
    return -1;
  }

  // Que B
  public long gcdSum(int[] nums) {
    int max_value = nums[0];
    int n = nums.length;

    int[] preGcp = new int[n];
    int index = 0;

    for (int i : nums) {
      max_value = Math.max(i, max_value);
      preGcp[index++] = gcp(max_value, i);
    }
    Arrays.sort(preGcp);
    int left = 0;
    int right = n - 1;

    long ans = 0;
    while (left < right) {
      ans += gcp(preGcp[left], preGcp[right]);
      left++;
      right--;
    }
    return ans;
  }

  public int gcp(int a, int b) {
    while (b != 0) {
      int temp = b;
      b = a % b;
      a = temp;
    }
    return a;
  }

    public int minCost(int[] nums1, int[] nums2) {

      Map<Integer,Integer> map= new HashMap<>();

      for(int i:nums1){
          map.put(i, map.getOrDefault(i,0)+1);
      }
      for(int i :nums2){
          if(map.containsKey(i)){
              map.put(i,map.get(i)-1);
              if(map.get(i)==0){
                  map.remove(i);
              }
          }else{
              return -1;
          }
      }
        int n= nums1.length;
      int count=0;
      for(int i=0;i<n;i++){
          if(nums1[i]!=nums2[i]){
              for(int j=i;j<n;j++){
                  if(nums1[i]==nums2[j]){
                      nums2[i] = nums2[i] ^ nums2[j];
                      nums2[j] = nums2[i] ^ nums2[j];
                      nums2[i] = nums2[i] ^ nums2[j];
                      count++;
                    break;
                  }

              }
          }

      }
      return count;
    }


}
