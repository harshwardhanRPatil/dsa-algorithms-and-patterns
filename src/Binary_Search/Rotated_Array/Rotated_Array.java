package Binary_Search.Rotated_Array;

public class Rotated_Array {
  static void main(String[] args) {}

  public int search(int[] nums, int target) {
    if (nums.length == 0) return -1;

    int peak = findPeak(nums);

    // Decide which subarray to search
    if (target >= nums[0] && target <= nums[peak]) {
      return binarySearch(nums, 0, peak, target);
    } else {
      return binarySearch(nums, peak + 1, nums.length - 1, target);
    }
  }

  public boolean searchII(int[] nums, int target) {
    if (nums.length == 0) return false;

    int peak = findPeak(nums);
    // Decide which subarray to search
    if (target >= nums[0] && target <= nums[peak]) {
      return binarySearchII(nums, 0, peak, target);
    } else {
      return binarySearchII(nums, peak + 1, nums.length - 1, target);
    }
  }

  public int binarySearch(int[] nums, int left, int right, int target) {
    while (left <= right) {
      int mid = left + (right - left) / 2;
      if (nums[mid] == target) return mid;
      else if (nums[mid] < target) left = mid + 1;
      else right = mid - 1;
    }
    return -1;
  }

  public boolean binarySearchII(int[] nums, int left, int right, int target) {
    while (left <= right) {
      int mid = left + (right - left) / 2;
      if (nums[mid] == target) return true;
      else if (nums[mid] < target) left = mid + 1;
      else right = mid - 1;
    }
    return false;
  }

  public int findPeak(int[] nums) {
    int left = 0;
    int right = nums.length - 1;

    while (left < right) {
      int mid = left + (right - left) / 2;

      // Check if mid is the peak
      if (nums[mid] > nums[mid + 1]) {
        return mid; // return INDEX of peak
      }

      // Decide which side to search
      if (nums[mid] >= nums[left]) {
        // Left half sorted, max is on the right
        left = mid + 1;
      } else {
        // Max is on the left side
        right = mid;
      }
    }

    return left; // left == right, peak index
  }

  public int findMin(int[] nums) {
    int left = 0;
    int right = nums.length - 1;

    while (left < right) {
      int mid = left + (right - left) / 2;

      if (nums[mid] > nums[right]) {
        left = mid + 1;
      } else {
        right = mid;
      }
    }
    return nums[left];
  }
}
