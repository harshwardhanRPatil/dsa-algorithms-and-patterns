package Binary_Search.Binary_Search;

public class Binary_Search_bASIC {
  public static void main(String args[]) {}

  public int search(int[] nums, int target) {
    int left = 0;
    int right = nums.length - 1;
    if (left == right && nums[left] == target) return left;
    while (left <= right) {
      int mid = left + (right - left) / 2;
      if (nums[mid] > target) {
        right = mid - 1;
      } else if (nums[mid] < target) {
        left = mid + 1;
      } else {
        return mid;
      }
    }
    return -1;
  }

  public int searchInsert(int[] nums, int target) {
    int ans = 0;
    int left = 0;
    int right = nums.length - 1;
    while (left <= right) {
      int mid = left + (right - left) / 2;

      if (nums[mid] > target) {
        right = mid - 1;
        ans = mid;
      } else if (nums[mid] < target) {
        left = mid + 1;
        ans = mid + 1;
      } else {
        return mid;
      }
    }
    return ans;
  }

  public int firstBadVersion(int nums) {
    int left = 0;
    int right = nums - 1;
    int ans = 0;
    while (left <= right) {
      int mid = left + (right - left) / 2;
      if (isBadVersion(mid + 1)) {
        right = mid - 1;
        ans = mid + 1;
      } else {
        left = mid + 1;
      }
    }
    return ans;
  }

  public boolean isBadVersion(int n) {
    return true;
  }

    public int guessNumber(int nums) {
        int left = 1;
        int right = nums;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            int pick= guess(mid);
            if(pick==1){
                right = mid - 1;
            } else if(pick==1){
                left=mid+1;
            }else{
                return mid;
            }
        }
        return -1;
    }

  public int guess(int n) {
    return 0;
  }
}
