package Graph.DFS;

import java.util.*;

class Employee {
  public int id;
  public int importance;
  public List<Integer> subordinates;
}

public class DFS_solution {

  int ans = 0;
  List<List<Integer>> result = new ArrayList<>();
  int[] dr = {1, -1, 0, 0}; // down, up
  int[] dc = {0, 0, 1, -1}; // right, left
  int longCycle = Integer.MIN_VALUE;

  /*
     https://leetcode.com/problems/find-if-path-exists-in-graph/
     NOte -: first we will have the List of List for egde and note
             then we have the visiterd as array for easy access
             then we travel in dfs as the recussion so we have the vissited and make sure target come and we get the data
  */

  public boolean validPath(int n, int[][] edges, int source, int destination) {
    List<List<Integer>> graph = new ArrayList<>();
    for (int i = 0; i < n; i++) graph.add(new ArrayList<>());

    for (int[] edge : edges) {
      graph.get(edge[0]).add(edge[1]);
      graph.get(edge[1]).add(edge[0]);
    }
    boolean[] visited = new boolean[n];
    return dfs(visited, graph, source, destination);
  }

  public boolean dfs(boolean[] visited, List<List<Integer>> graph, int source, int destination) {

    if (source == destination) return true;
    visited[source] = true;

    for (int node : graph.get(source)) {
      if (!visited[node]) {
        if (dfs(visited, graph, node, destination)) return true;
      }
    }
    return false;
  }

  /*
   https://leetcode.com/problems/flood-fill/
   Note -: for this we satrt  at sc,sr and then we fill all the (1,0)(-1,0)(0,1)(0,-1) and till all the match color till we have the
           end of all connect and check boundry also
           we can remove the visited array as we ahve a match of color so we can remove if need
           **IMP**
           The starting pixel is already colored with 0, which is the same as the target color. Therefore, no changes are made to the image.
  */

  public int[][] floodFill(int[][] image, int sr, int sc, int color) {

    int length = image.length;
    int weigth = image[0].length;
    boolean[][] visited = new boolean[length][weigth];

    dfs(image, sr, sc, color, visited, length, weigth, image[sr][sc]);

    return image;
  }

  public void dfs(
      int[][] image,
      int sr,
      int sc,
      int color,
      boolean[][] visited,
      int length,
      int weight,
      int match) {
    if (sr < 0 || sr >= length || sc < 0 || sc >= weight) return;

    if (visited[sr][sc]) return;
    if (match != image[sr][sc]) return;
    visited[sr][sc] = true;
    image[sr][sc] = color;
    dfs(image, sr + 1, sc, color, visited, length, weight, match);
    dfs(image, sr - 1, sc, color, visited, length, weight, match);
    dfs(image, sr, sc + 1, color, visited, length, weight, match);
    dfs(image, sr, sc - 1, color, visited, length, weight, match);
  }

  // https://leetcode.com/problems/island-perimeter/
  public int islandPerimeter(int[][] grid) {
    int length = grid.length;
    int weigth = grid[0].length;
    boolean[][] visited = new boolean[length][weigth];

    int ans = 0;
    for (int i = 0; i < length; i++) {
      for (int j = 0; j < weigth; j++) {
        if (grid[i][j] != 0 && !visited[i][j]) {
          ans += dfs(grid, visited, i, j, length, weigth);
        }
      }
    }
    return ans;
  }

  public int dfs(int[][] image, boolean[][] visited, int sr, int sc, int length, int weidth) {

    if (sc < 0 || sc >= weidth || sr < 0 || sr >= length) {
      return 1;
    }
    if (image[sr][sc] == 0) {
      return 1;
    }
    if (visited[sr][sc]) return 0;

    visited[sr][sc] = true;
    System.out.println("visited" + visited);
    return dfs(image, visited, sr, sc - 1, length, weidth)
        + dfs(image, visited, sr, sc + 1, length, weidth)
        + dfs(image, visited, sr + 1, sc, length, weidth)
        + dfs(image, visited, sr - 1, sc, length, weidth);
  }

  /*
          https://leetcode.com/problems/minimum-depth-of-binary-tree/description/
          for this we ahve to check left and right and pick the min
          for the lowers we pass the top if we see the null return 0;
          IMP** -> we need to return Int MAX and if left and right 0 then we return 1
                   and for the root null we return 0
  */

  /*
      public int minDepth(TreeNode root) {
          if(root ==null )return 0;
          return bfs(root);
      }

      public int bfs(TreeNode root) {
          if (root == null)
              return Integer.MAX_VALUE;

          if (root.left == null && root.right == null)
              return 1;

          return 1 + Math.min(bfs(root.left), bfs(root.right));
      }
  */

  /*
     https://leetcode.com/problems/employee-importance/
     int his approc i have the visted and graph that are use less as i have alreday emply so i try another approch
  */

  public int getImportance(List<Employee> employees, int id) {
    int n = employees.size();
    List<List<Integer>> graph = new ArrayList<>();
    Map<Integer, Integer> valueMap = new HashMap<>();

    for (int i = 0; i < n; i++) graph.add(new ArrayList<>());

    for (Employee note : employees) {
      for (int edge : note.subordinates) graph.get(note.id).add(edge);
      valueMap.put(note.id, note.importance);
    }
    System.out.println(graph);
    boolean[] visted = new boolean[n];
    dfs(graph, visted, id, valueMap);

    return ans;
  }

  public void dfs(
      List<List<Integer>> graph, boolean[] visted, int start, Map<Integer, Integer> valueMap) {

    visted[start] = true;
    ans += valueMap.get(start);
    for (int node : graph.get(start)) {
      if (visted[node]) {
        dfs(graph, visted, node, valueMap);
      }
    }
  }

  public int getImportanceII(List<Employee> employees, int id) {
    Map<Integer, Employee> valueMap = new HashMap<>();

    for (Employee emp : employees) {
      valueMap.put(emp.id, emp);
    }

    return dfs(valueMap, id);
  }

  public int dfs(Map<Integer, Employee> valueMap, int id) {
    Employee employee = valueMap.get(id);
    int total = employee.importance;
    for (int node : employee.subordinates) {
      total += dfs(valueMap, node);
    }
    return total;
  }

  /*
     https://leetcode.com/problems/all-paths-from-source-to-target/

     for this we do thee DFS and then we have to add the note from start and then all the respective node
     add them to temp and and once we reach to destination we add the temp result
     and after that we remove the value and travel all the other node it a DFS+ BackTracking

     chatgpt -> no need of the visited and there a diffren way to do it
  */

  public List<List<Integer>> allPathsSourceTarget(int[][] node) {

    List<List<Integer>> graph = new ArrayList<>();
    int size = node.length;

    for (int i = 0; i < size; i++) graph.add(new ArrayList<>());

    for (int i = 0; i < size; i++) {
      for (int j : node[i]) {
        graph.get(i).add(j);
      }
    }

    boolean[] visted = new boolean[size];
    dfs(graph, 0, size - 1, visted, new ArrayList<>());
    return result;
  }

  public void dfs(
      List<List<Integer>> graph, int sr, int dest, boolean[] visted, List<Integer> temp) {
    temp.add(sr);
    if (sr == dest) {
      result.add(new ArrayList<>(temp));
      return;
    }

    visted[sr] = true;
    for (int node : graph.get(sr)) {
      if (!visted[node]) {

        dfs(graph, node, dest, visted, temp);
        temp.remove(temp.size() - 1);
        visted[node] = false;
      }
    }
  }

  /*
   https://leetcode.com/problems/detect-cycles-in-2d-grid/
   for this we have to travel the matric from n,m time and the we have to check if the old matric is match the char to new one
   if not then we ahve to return false directly and we have the basic edge case

   i did the mistake by ging the bfs and i should do the dfs we have to travel in 1 direct foirst and then we have to go for all direct
   and if we fail then we go for the 1-1 in all direct once it fail
  */

  public boolean containsCycle(char[][] grid) {

    int length = grid.length;
    int weidth = grid[0].length;

    boolean[][] visted = new boolean[length][weidth];

    for (int i = 0; i < length; i++) {
      for (int j = 0; j < weidth; j++) {
        if (!visted[i][j]) {
          boolean ans = dfs(grid, visted, i, j, -1, -1, grid[i][j]);
          if (ans) return true;
        }
      }
    }
    return false;
  }

  public boolean dfs(
      char[][] grid, boolean[][] visted, int sr, int sc, int parentX, int parentY, char target) {
    visted[sr][sc] = true;
    for (int i = 0; i < 4; i++) {
      int nr = sr + dr[i];
      int nc = sc + dc[i];

      if (nr < 0 || nr >= grid.length || nc < 0 || nc >= grid[0].length) {
        continue;
      }

      if (grid[nr][nc] != target) continue;
      if ((visted[nr][nc] && (nr != parentX || nc != parentY))) {
        return true;
      }

      if (!visted[nr][nc]) {
        if (dfs(grid, visted, nr, nc, sr, sc, target)) return true;
      }
    }
    return false;
  }

  // https://www.geeksforgeeks.org/problems/detect-cycle-in-an-undirected-graph/1
  public boolean isCycle(int V, int[][] edges) {
    // Code here

    List<List<Integer>> graph = new ArrayList<>();

    HashSet<Integer> edge = new HashSet<>();
    int maxVale = Integer.MIN_VALUE;

    for (int[] i : edges) {
      edge.add(i[0]);
      edge.add(i[1]);
      maxVale = Math.max(maxVale, Math.max(i[0], i[1]));
    }

    for (int i = 0; i <= maxVale; i++) {
      graph.add(new ArrayList<>());
    }

    // 3️⃣ now add edges
    for (int[] e : edges) {
      graph.get(e[0]).add(e[1]);
      graph.get(e[1]).add(e[0]);
    }
    boolean[] visited = new boolean[maxVale + 1];
    int[] parent = new int[maxVale + 1];

    for (int i : edge) {
      if (!visited[i]) {
        parent[i] = -1;
        if (dfs(graph, visited, parent, i)) {
          return true;
        }
      }
    }
    return false;
  }

  public boolean dfs(List<List<Integer>> graph, boolean[] visted, int[] parent, int sc) {

    visted[sc] = true;

    for (int i : graph.get(sc)) {
      if (!visted[i]) {
        parent[i] = sc;
        if (dfs(graph, visted, parent, i)) return true;
      } else if (visted[i] && parent[sc] != i) {
        return true;
      }
    }
    return false;
  }

  /*
  https://www.geeksforgeeks.org/problems/detect-cycle-in-a-directed-graph/1
    for the direct graph we need to solve using the state as when we iuse the visted it not tell where the elemtn is proces or not
    when we  use the state we have 0 not satrt, 1 visted , 2 complete proces
    if we come back to same note and we see it 1 then we need know it still in proec and we can say it in  loop
   */
  public boolean isCyclic(int V, int[][] edges) {

    List<List<Integer>> graph = new ArrayList<>();
    if (edges.length == 0) return false;

    HashSet<Integer> edge = new HashSet<>();
    int maxVale = Integer.MIN_VALUE;

    for (int[] i : edges) {
      edge.add(i[0]);
      edge.add(i[1]);
      maxVale = Math.max(maxVale, Math.max(i[0], i[1]));
    }

    for (int i = 0; i <= maxVale; i++) {
      graph.add(new ArrayList<>());
    }

    for (int[] e : edges) {
      graph.get(e[0]).add(e[1]);
    }
    int[] visited = new int[maxVale + 1];

    for (int i : edge) {
      if (visited[i] == 0) {
        if (dfs(graph, visited, i, -1)) {
          return true;
        }
      }
    }
    return false;
  }

  public boolean dfs(List<List<Integer>> graph, int[] visited, int sc, int parent) {

    visited[sc] = 1;

    for (int edge : graph.get(sc)) {
      if (visited[edge] == 0) {
        if (dfs(graph, visited, edge, sc)) return true;
      } else if (visited[edge] == 1) {
        return true;
      }
    }
    visited[sc] = 2;
    return false;
  }

  /*
          https://leetcode.com/problems/keys-and-rooms/
  */

  public boolean canVisitAllRooms(List<List<Integer>> rooms) {
    int size = rooms.size();

    boolean[] visited = new boolean[size];

    dfs(rooms, visited, 0);
    for (int i = 0; i < size; i++) {
      if (!visited[i]) {
        return false;
      }
    }
    return true;
  }

  public void dfs(List<List<Integer>> rooms, boolean[] visted, int sc) {
    visted[sc] = true;

    for (int node : rooms.get(sc)) {
      if (!visted[node]) {
        dfs(rooms, visted, node);
      }
    }
  }

  /*
     as we can see there only wat a path not lead to a  terminal node is it comtain a cycle so we need to find the cluc  that it
  */

  public List<Integer> eventualSafeNodes(int[][] edges) {
    int size = edges.length;
    List<Integer> temp = new ArrayList<>();
    List<Integer> ans = new ArrayList<>();
    int[] visited = new int[size];
    List<List<Integer>> graph = new ArrayList<>();

    for (int i = 0; i <= size; i++) {
      graph.add(new ArrayList<>());
    }
    for (int e = 0; e < size; e++) {
      if (edges[e].length > 0) {
        for (int i : edges[e]) {
          graph.get(e).add(i);
        }
      }
    }
    for (int i = 0; i < size; i++) {
      if (visited[i] == 0 && !temp.contains(i)) {
        if (dfs(graph, visited, i, -1)) {
          getVisitedValues(temp, visited);
          Arrays.fill(visited, 0);
        }
      }
    }
    for (int i = 0; i < size; i++) {
      if (!temp.contains(i)) { // check in list
        ans.add(i);
      }
    }
    return ans;
  }

  public List<Integer> getVisitedValues(List<Integer> values, int[] visited) {
    for (int i = 0; i < visited.length; i++) {
      if (visited[i] == 1) {
        values.add(i);
      }
    }
    return values;
  }

  public int longestCycle(int[] edges) {

    int size = edges.length;
    int[] visited = new int[size];
    int[] temp = new int[size];

    for (int i = 0; i < size; i++) {
      if (visited[i] == 0 && edges[i] != -1) {
        dfs(edges, visited, i, 0, temp);
      }
    }
    return longCycle != Integer.MIN_VALUE ? longCycle : -1;
  }

  public void dfs(int[] graph, int[] visited, int sc, int parent, int[] temp) {
    parent++;
    visited[sc] = 1;
    temp[sc] = parent;

    if (graph[sc] != -1) {
      if (visited[graph[sc]] == 0) {
        dfs(graph, visited, graph[sc], parent, temp);
      } else if (temp[graph[sc]] != 0) {
        int currCycle = temp[sc] - temp[graph[sc]] + 1;
        longCycle = Math.max(longCycle, currCycle);
      }
    }
    // here we not need the visited 0 as we ahve the 1-1 maping at max so that why if we visited
    // the1 flow then it not need to have any more
    // visited[sc] = 0;
    // nodes in current DFS path
    /*
    [-1,4,-1,2,0,4] is u run the code for this and u see the issue as
    for this we not put temp[sc]=0 so when 1 run done it have 4 =2 and when we ty for the
    5 it go to 4 as see 4 alreday mark as temp and it consider as cycle so we need to make the temo 0 as
    back make sure no paret for that now it tell that it visted but not from the current flow
    */
    temp[sc] = 0;
  }

  // https://leetcode.com/problems/01-matrix/submissions/2000707964/

  public int[][] updateMatrix(int[][] mat) {
    int length = mat.length;
    int weidth = mat[0].length;

    int[][] result = new int[length][weidth];

    for (int i = 0; i < length; i++) {
      Arrays.fill(result[i], Integer.MAX_VALUE);
    }
    int[][] directions = {
      {-1, 0},
      {1, 0},
      {0, -1},
      {0, 1}
    };

    for (int i = 0; i < length; i++) {
      for (int j = 0; j < weidth; j++) {
        boolean[][] visited = new boolean[length][weidth];

        result[i][j] = dfs(mat, result, i, j, directions, length, weidth, visited);
      }
    }

    return result;
  }

  public int dfs(
      int[][] mat,
      int[][] result,
      int sr,
      int sc,
      int[][] directions,
      int length,
      int weidth,
      boolean[][] visited) {
    if (sr >= length || sr < 0 || sc >= weidth || sc < 0) return Integer.MAX_VALUE;

    if (mat[sr][sc] == 0) {
      return 0;
    }

    if (visited[sr][sc]) {
      return Integer.MAX_VALUE;
    }

    if (result[sr][sc] != Integer.MAX_VALUE) {
      return result[sr][sc];
    }

    visited[sr][sc] = true;
    int temp = Integer.MAX_VALUE;
    for (int[] dir : directions) {

      int nx = sr + dir[0];
      int ny = sc + dir[1];
      temp = Math.min(temp, dfs(mat, result, nx, ny, directions, length, weidth, visited));
    }
    visited[sr][sc] = false;
    if (temp != Integer.MAX_VALUE) {
      result[sr][sc] = temp + 1;
    }
    return result[sr][sc];
  }

  /*
    https://leetcode.com/problems/surrounded-regions/
  */
  public void solve(char[][] board) {
    int length = board.length;
    int weidth = board[0].length;

    int[][] result = new int[length][weidth];

    for (int i = 0; i < length; i++) {
      Arrays.fill(result[i], -1);
    }

    int[][] directions = {
      {-1, 0},
      {1, 0},
      {0, -1},
      {0, 1}
    };
    boolean[][] visited = new boolean[length][weidth];

    for (int i = 0; i < length; i++) {
      for (int j = 0; j < weidth; j++) {

        if (board[i][j] == 'O') {
          boolean safe = dfs(board, i, j, directions, length, weidth, result, visited);
          result[i][j] = 1;
          if (!safe) {
            board[i][j] = 'X';
          }
        }
      }
    }
  }

  public boolean dfs(
      char[][] mat,
      int sr,
      int sc,
      int[][] directions,
      int length,
      int weidth,
      int[][] result,
      boolean[][] visited) {

    if (sr >= length || sr < 0 || sc >= weidth || sc < 0) return true;

    if (mat[sr][sc] == 'X') {
      return false;
    }
    if (result[sr][sc] != -1) {
      return result[sr][sc] == 1;
    }
    if (visited[sr][sc]) {
      return false;
    }
    boolean temp = false;
    visited[sr][sc] = true;

    for (int[] dir : directions) {

      int nx = sr + dir[0];
      int ny = sc + dir[1];
      temp = temp || dfs(mat, nx, ny, directions, length, weidth, result, visited);
    }
    result[sr][sc] = temp ? 1 : 0;

    return temp;
  }

  // https://leetcode.com/problems/number-of-enclaves/
  public int numEnclaves(int[][] grid) {
    int length = grid.length;
    int weidth = grid[0].length;

    int[][] result = new int[length][weidth];

    for (int i = 0; i < length; i++) {
      Arrays.fill(result[i], -1);
    }

    int[][] directions = {
      {-1, 0},
      {1, 0},
      {0, -1},
      {0, 1}
    };
    boolean[][] visited = new boolean[length][weidth];

    for (int i = 0; i < length; i++) {
      for (int j = 0; j < weidth; j++) {

        if (grid[i][j] == 1) {
          boolean unSafe = dfs(grid, i, j, directions, length, weidth, result, visited);
          result[i][j] = 1;
          if (unSafe) {
            grid[i][j] = 2;
          }
        }
      }
    }
    int ans = 0;
    for (int i = 0; i < length; i++) {
      for (int j = 0; j < weidth; j++) {
        if (grid[i][j] == 1) ans++;
      }
    }
    return ans;
  }

  public boolean dfs(
      int[][] mat,
      int sr,
      int sc,
      int[][] directions,
      int length,
      int weidth,
      int[][] result,
      boolean[][] visited) {

    if (sr >= length || sr < 0 || sc >= weidth || sc < 0) return true;

    if (mat[sr][sc] == 0) {
      return false;
    }
    if (result[sr][sc] != -1) {
      return result[sr][sc] == 1;
    }
    if (visited[sr][sc]) {
      return false;
    }
    boolean temp = false;
    visited[sr][sc] = true;

    for (int[] dir : directions) {

      int nx = sr + dir[0];
      int ny = sc + dir[1];
      temp = temp || dfs(mat, nx, ny, directions, length, weidth, result, visited);
    }
    result[sr][sc] = temp ? 1 : 0;

    return temp;
  }

  static void main() {}
}
