package Graph.BFS;

import java.util.*;

class position {
  int x;
  int y;

  public position(int x, int y) {
    this.x = x;
    this.y = y;
  }
}

public class BFS_solution {

  /*
     https://leetcode.com/problems/find-if-path-exists-in-graph/

     Note -: for this we first createa  array for adding the flow like this
     0 ->1,2
     1 -> 2
     2->1,0
     and have a visited array so make sure that we have make sure we don't visit the same node again
     and then we have the put data in queue and the use the info for loop the data for the size of the array we created
  */
  public boolean validPath(int n, int[][] edges, int source, int destination) {
    List<List<Integer>> arr = new ArrayList<>();
    for (int i = 0; i < n; i++) {
      arr.add(new ArrayList<>());
    }
    for (int i = 0; i < edges.length; i++) {
      int u = edges[i][0];
      int v = edges[i][1];
      arr.get(u).add(v);
      arr.get(v).add(u);
    }

    Queue<Integer> queue = new ArrayDeque<>();
    boolean[] visited = new boolean[n];

    queue.add(source);
    visited[source] = true;

    while (!queue.isEmpty()) {

      int temp = queue.poll();

      for (int neighbor : arr.get(temp)) {
        if (destination == neighbor) {
          return true;
        }
        if (!visited[neighbor]) {
          visited[neighbor] = true;
          queue.add(neighbor);
        }
      }
    }

    return false;
  }

  /*
   https://leetcode.com/problems/number-of-islands/
   for this we don;t need visited as we have the char we can check that and base n=ont this onlu we can return only if
   it is 1 we process and all the 1 we see we make thme X
  */
  public int numIslands(char[][] grid) {
    int length = grid.length;
    int weidth = grid[0].length;

    int ans = 0;

    for (int i = 0; i < length; i++) {
      for (int j = 0; j < weidth; j++) {
        if (grid[i][j] == '1') {
          ans++;
          bfs(grid, i, j, length, weidth);
        }
      }
    }
    return ans;
  }

  public void bfs(char[][] grid, int sr, int sc, int length, int weidth) {

    if (sr < 0 || sr >= length || sc < 0 || sc >= weidth) {
      return;
    }
    if (grid[sr][sc] == '0' || grid[sr][sc] == 'X') return;

    grid[sr][sc] = 'X';
    bfs(grid, sr + 1, sc, length, weidth);
    bfs(grid, sr - 1, sc, length, weidth);
    bfs(grid, sr, sc + 1, length, weidth);
    bfs(grid, sr, sc - 1, length, weidth);
  }

  /*
     https://leetcode.com/problems/rotting-oranges/
     for this we check the if all the 4 path have the orage and mark them rotting
     and then check if all done else return -1
  */
  public int orangesRotting(int[][] grid) {
    Deque<position> integerDeque = new ArrayDeque<>();

    int length = grid.length;
    int weidth = grid[0].length;

    for (int i = 0; i < length; i++) {
      for (int j = 0; j < weidth; j++) {
        if (grid[i][j] == 2) {
          integerDeque.offer(new position(i, j));
        }
      }
    }

    int[][] directions = {
      {-1, 0},
      {1, 0},
      {0, -1},
      {0, 1}
    };

    int counter = 0;
    while (!integerDeque.isEmpty()) {

      int size = integerDeque.size();

      for (int i = 0; i < size; i++) {
        position temp = integerDeque.poll();

        int x = temp.x;
        int y = temp.y;

        for (int[] dir : directions) {

          int nx = x + dir[0];
          int ny = y + dir[1];

          if (nx >= 0 && nx < length && ny >= 0 && ny < weidth && grid[nx][ny] == 1) {
            integerDeque.offer(new position(nx, ny));
          }
        }
      }
      counter++;
    }
    return counter;
  }



  static void main() {}
}
