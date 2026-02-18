package Binary_Search.Bound;

public class Bound_Basic {
  public static void main(String args[]) {}

  public int[] searchRange(int[] nums, int target) {
    int first_index = searchFirst(nums, target,true);
    int second_index = searchFirst(nums, target,false);
    return new int[] {first_index, second_index};
  }

    int countFreq(int[] nums, int target) {
        int first_index = searchFirst(nums, target,true);
        int second_index = searchFirst(nums, target,false);
        if(first_index==-1) return 0;
        return second_index-first_index+1;

    }
  public int searchFirst(int[] nums, int target,boolean first) {
    int ans = 0;
    int left = 0;
    int right = nums.length - 1;
    while (left <= right) {
      int mid = left + (right - left) / 2;

      if (nums[mid] > target) {
        right = mid - 1;
      } else if (nums[mid] < target) {
        left = mid + 1;
      } else {
          ans = mid;
          if(first){

        right = mid - 1;}else{
              left=mid+1;
          }
      }
    }
    return ans;
  }

}
