package Binary_Search.Matrix;

import javax.swing.text.TabableView;

public class matrix {
  static void main() {}

  public boolean searchMatrix(int[][] matrix, int target) {
    if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return false;
    int i = 0;
    int j = matrix[0].length - 1;

    while (i < matrix.length && j >= 0) {
      if (matrix[i][j] == target) return true;

      if (matrix[i][j] > target) {
        j--;
      } else {
        i++;
      }
    }
    return false;
  }

  public boolean searchMatrixII(int[][] matrix, int target) {
    if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return false;
    int i = 0;
    int j = matrix[0].length - 1;

    while (i < matrix.length && j >= 0) {
      if (matrix[i][j] == target) return true;

      if (matrix[i][j] > target) {
        j--;
      } else {
        i++;
      }
    }
    return false;
  }

  public int kthSmallest(int[][] matrix, int k) {
    int n = matrix.length;
    int left = matrix[0][0];
    int right = matrix[n - 1][n - 1];
    int ans = 0;
    int count = 0;
    while (left <= right) {
      int mid = left + (right - left) / 2;

      count = countPosition(matrix, n, mid);

      if (count >= k) {
        ans = mid;
        right = mid - 1;
      } else {
        left = mid + 1;
      }
    }
    return ans;
  }

  public int countPosition(int[][] martix, int n, int target) {
    int row = martix.length;
    int col = 0;
    int count = 0;

    while (row >= 0 && col < n) {
      if (martix[row][col] <= target) {
        count += (row + 1);
        col++;
      } else {
        row--;
      }
    }
    return count;
  }
}
