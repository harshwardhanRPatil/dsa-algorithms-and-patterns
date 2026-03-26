package Binary_Search.Binary_Search;

import java.util.Arrays;

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
      int pick = guess(mid);
      if (pick == 1) {
        right = mid - 1;
      } else if (pick == 1) {
        left = mid + 1;
      } else {
        return mid;
      }
    }
    return -1;
  }

  public int countOfElement(int[] nums, int target) {
    if (nums.length == 0) return -1;
    return searchlast(nums, target) - searchfirst(nums, target) + 1;
  }

  public int searchfirst(int[] nums, int target) {
    int left = 0;
    int right = nums.length - 1;
    int ans = -1;
    if (right == 0 && nums[right] == target) return right;
    while (left <= right) {
      int mid = left + (right - left) / 2;
      if (nums[mid] == target) {
        ans = mid;
        right = mid - 1;
      } else if (nums[mid] > target) {
        right = mid - 1;
      } else {
        left = mid + 1;
      }
    }
    return ans;
  }

  public int searchlast(int[] nums, int target) {
    int left = 0;
    int right = nums.length - 1;
    int ans = -1;
    if (right == 0 && nums[right] == target) return right;
    while (left <= right) {
      int mid = left + (right - left) / 2;
      if (nums[mid] == target) {
        ans = mid;
        left = mid + 1;
      } else if (nums[mid] > target) {
        right = mid - 1;
      } else {
        left = mid + 1;
      }
    }

    return ans;
  }

  public int searchIndex(int[] nums, int target) {
    int mid = searchmin(nums, target);
    int n = nums.length - 1;
    if (target == nums[mid]) {
      return mid;
    }
    if (target > nums[mid] && target <= nums[n]) {
      return search(nums, target, mid + 1, n);
    } else {
      return search(nums, target, 0, mid - 1);
    }
  }

  public int searchmin(int[] nums, int target) {
    int left = 0;
    int right = nums.length - 1;
    int n = nums.length;
    while (left < right) {
      int mid = left + (right - left) / 2;

      if (nums[mid] < nums[right]) {
        right = mid;
      } else {
        left = mid + 1;
      }
    }
    return left;
  }

  public int search(int[] nums, int target, int left, int right) {

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

  public int findFloor(int[] arr, int x) {
    // code here
    int left = 0;
    int right = arr.length - 1;
    int ansIndex = -1;

    while (left <= right) {

      int mid = left + (right - left) / 2;

      if (arr[mid] == x) {
        ansIndex = mid;
        left = mid + 1;
      }
      if (arr[mid] > x) {
        right = mid - 1;
      } else {

        ansIndex = mid;
        left = mid + 1;
      }
    }
    return ansIndex;
  }

  public int findTarget(int arr[], int target) {
    // code here
    int left = 0;
    int right = arr.length - 1;
    int n = right + 1;
    while (left <= right) {

      int mid = left + (right - left) / 2;

      if (arr[mid] == target) {
        return mid;
      }
      if (mid + 1 < n && arr[mid + 1] == target) {
        return mid + 1;
      }
      if (mid - 1 >= 0 && arr[mid - 1] == target) {
        return mid - 1;
      }
      if (arr[mid] > target) {
        right = mid - 2;
      } else {
        left = mid + 2;
      }
    }
    return -1;
  }

  public int findCeil(int[] arr, int x) {
    // code here
    int left = 0;
    int right = arr.length - 1;
    int ansIndex = -1;

    while (left <= right) {

      int mid = left + (right - left) / 2;

      if (arr[mid] == x) {
        ansIndex = mid;
        right = mid - 1;
      }
      if (arr[mid] < x) {
        left = mid + 1;
      } else {
        ansIndex = mid;
        right = mid - 1;
      }
    }
    return ansIndex;
  }

  public int infintArray(int[] arr, int target) {

    int left = 0;
    int right = 1;

    // consied array is infine so we not check for the right size
    // if we have a size as contest need one we check
    // while(right<n && arr[right]<target)
    while (arr[right] < target) {
      left = right;
      right = 2 * right;
    }

    /*
    If right crossed array size, fix it
    if (right >= arr.length) {
        right = arr.length - 1;
    }
    */
    return search(arr, target, left, right);
  }

  public int infintArrayFirstOccurence(int[] arr, int target) {

    int left = 0;
    int right = 1;

    // consied array is infine so we not check for the right size
    // if we have a size as contest need one we check
    // while(right<n && arr[right]<target)
    while (arr[right] < target) {
      left = right;
      right = 2 * right;
    }

    /*
    If right crossed array size, fix it
    if (right >= arr.length) {
        right = arr.length - 1;
    }
    */
    // we need to pass the left and right in param
    return searchfirst(arr, target);
  }

  public int minDiffElement(int[] nums, int key) {

    int left = 0;
    int right = nums.length - 1;

    if (key <= nums[0]) return nums[0];
    if (key >= nums[nums.length - 1]) return nums[nums.length - 1];

    while (left <= right) {
      int mid = left + (right - left) / 2;

      if (nums[mid] == key) return nums[mid];

      if (nums[mid] > key) {
        right = mid - 1;
      } else {
        left = mid + 1;
      }
    }
    // my ;ogic for checking the index value
    //    if (ans == Integer.MAX_VALUE) return -1;
    //    return nums[ans];

    if (Math.abs(nums[left] - key) < Math.abs(nums[right] - key)) {
      return nums[left];
    } else {
      return nums[right];
    }
  }
    public int findPeakII(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        int n=nums.length;

        while (left <=right) {
            int mid = left + (right - left) / 2;

            if ((mid==0 || nums[mid-1]<nums[mid]) && (mid==n-1 || nums[mid] > nums[mid + 1])) {
                return mid; // return INDEX of peak
            }

            else if (mid+1<n && nums[mid+1] > nums[mid]) {
                left = mid + 1;
            } else {
                right = mid-1;
            }
        }
        return -1;
    }

    public int bitonixArray(int[] nums){
      return findPeakII(nums);
    }


    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0)
            return false;
        int i=0;
        int j=matrix[0].length-1;

        while(i<matrix.length && j>=0 ){
            if(matrix[i][j]==target) return true;

            if(matrix[i][j]>target){
                j--;
            }
            else{
                i++;
            }
        }
        return false;
    }


  public int guess(int n) {
    return 0;
  }
}
