package Contest;

import java.util.Arrays;

public class June_20_06 {
  public String[] createGrid(int m, int n) {

    char[][] block = new char[m][n];

    for (int i = 0; i < m; i++) {
      Arrays.fill(block[i], '#');
    }

    for (int j = 0; j < n; j++) {
      block[0][j] = '.';
    }

    for (int i = 0; i < m; i++) {
      block[i][n - 1] = '.';
    }

    String[] result = new String[m];
    for (int i = 0; i < m; i++) {
      result[i] = new String(block[i]);
    }

    return result;
  }

  public int minLights(int[] lights) {
    int n = lights.length;
    boolean[] lightUp = new boolean[n];

    for (int i = 0; i < n; i++) {
      if (lights[i] != 0) {
        int left = Math.max(0, i - lights[i]);
        int right = Math.min(n - 1, i + lights[i]);

        for (int j = left; j <= right; j++) {
          lightUp[j] = true;
        }
      }
    }
    int ans = 0;

    for (int i = 0; i < n; i++) {
      if (!lightUp[i]) {
        int pos = Math.min(i + 1, n - 1);

        int left = Math.max(0, pos - 1);
        int right = Math.min(n - 1, pos + 1);

        for (int j = left; j <= right; j++) {
          lightUp[j] = true;
        }

        ans++;
      }
    }
    return ans;
  }

  static void main() {}
}
