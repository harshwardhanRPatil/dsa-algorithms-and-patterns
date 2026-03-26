package Binary_Search.Peak_Mountain;

public class Peak_Mountain {
    public int findPeakElement(int[] nums) {
        int left = 0;
        int right = nums.length - 1;

        while (left < right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] < nums[mid + 1]) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }

    public int peakIndexInMountainArray(int[] nums) {
        int left = 0;
        int right = nums.length - 1;

        while (left < right) {
            int mid = left + (right - left) / 2;

            // Decide which side to search
            if (nums[mid] < nums[mid+1]) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        return left;
    }

//    public int findInMountainArray(int target, MountainArray nums) {
//        int peakIndex = findPeakII(nums);
//        if (target == nums.get(peakIndex))
//            return peakIndex;
//
//        int ans = searchLeft(nums, target, 0, peakIndex - 1);
//        if (ans != -1)
//            return ans;
//
//        return searchRight(nums, target, peakIndex + 1, nums.length() - 1);
//
//    }
//
//    public int findPeakII(MountainArray nums) {
//        int left = 0;
//        int right = nums.length() - 1;
//
//        while (left < right) {
//            int mid = left + (right - left) / 2;
//
//            if (nums.get(mid) < nums.get(mid + 1)) {
//                left = mid + 1;
//            } else {
//                right = mid;
//            }
//        }
//        return left;
//    }
//
//    public int searchLeft(MountainArray nums, int target, int left, int right) {
//
//        while (left <= right) {
//            int mid = left + (right - left) / 2;
//            if (nums.get(mid) > target) {
//                right = mid - 1;
//            } else if (nums.get(mid) < target) {
//                left = mid + 1;
//            } else {
//                return mid;
//            }
//        }
//        return -1;
//    }
//
//    public int searchRight(MountainArray nums, int target, int left, int right) {
//
//        while (left <= right) {
//            int mid = left + (right - left) / 2;
//            if (nums.get(mid) > target) {
//                left = mid + 1;
//            } else if (nums.get(mid) < target) {
//                right = mid - 1;
//            } else {
//                return mid;
//            }
//        }
//        return -1;
//    }
}
