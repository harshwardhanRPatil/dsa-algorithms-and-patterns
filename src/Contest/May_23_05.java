package Contest;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class May_23_05 {

    // QUE A
    public int minimumSwaps(int[] nums) {
        int i =0;
        int j=nums.length-1;

        int count =0;
        while(i<j){
            if(nums[i]!=0){
                i++;
            } else if(nums[j]==0){
                j--;
            }else if(nums[i]==0 && nums[j]!=0){
                swap(nums,i,j);
                i++;
                j--;
                count++;
            }

        }
        return count;
    }
    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

  public int[] limitOccurrences(int[] nums, int k) {

        int count =1;
        int i=0;
      while (i < nums.length - 1) {

          // same number
          if (nums[i] == nums[i + 1]) {
              count++;

              // extra occurrence
              if (count > k) {
                  nums[i + 1] = 0;
              }
          } else {
              // new number -> reset count
              count = 1;
          }

          i++;
      }
      int insert = 0;

      for (i = 0; i < nums.length; i++) {

          if (nums[i] != 0) {

              int temp = nums[insert];
              nums[insert] = nums[i];
              nums[i] = temp;

              insert++;
          }
      }


      return nums;
  }

// https://leetcode.com/problems/count-the-number-of-special-characters-ii/?envType=daily-question&envId=2026-05-27
        public int numberOfSpecialChars(String word) {

            Map<Character, Integer> lower = new HashMap<>();
            Map<Character, Integer> upper = new HashMap<>();

            for (int i = 0; i < word.length(); i++) {
                char ch = word.charAt(i);

                if (Character.isLowerCase(ch)) {
                    char upperChar = Character.toUpperCase(ch);

                    lower.put(ch, i);
                    if(upper.containsKey(upperChar) && lower.get(ch)>upper.get(upperChar)){
                        lower.remove(ch);
                    }
                } else {
                    upper.put(ch, i); // first uppercase index
                }
            }

            int count = 0;

            for (char ch = 'a'; ch <= 'z'; ch++) {

                char up = Character.toUpperCase(ch);

                if (lower.containsKey(ch) &&
                        upper.containsKey(up) &&
                        lower.get(ch) < upper.get(up)) {

                    count++;
                }
            }

            return count;
        }

  static void main() {}
}
