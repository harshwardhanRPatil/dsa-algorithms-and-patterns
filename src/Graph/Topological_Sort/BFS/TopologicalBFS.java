package Graph.Topological_Sort.BFS;

import java.util.*;
import java.util.concurrent.DelayQueue;

public class TopologicalBFS {

  public ArrayList<Integer> topoSort(int V, int[][] edges) {
    // code here

    int size = V;
    int[] indegree = new int[size];

    List<List<Integer>> graph = new ArrayList<>();

    for (int i = 0; i < V; i++) {
      graph.add(new ArrayList<>());
    }

    for (int[] edge : edges) {
      graph.get(edge[0]).add(edge[1]);
      indegree[edge[1]]++;
    }

    ArrayDeque<Integer> queue = new ArrayDeque<>();
    for (int i = 0; i < size; i++) {
      if (indegree[i] == 0) {
        queue.add(i);
      }
    }
    ArrayList<Integer> result = new ArrayList<>();

    while (!queue.isEmpty()) {
      int temp = queue.pop();

      result.add(temp);

      for (int node : graph.get(temp)) {
        indegree[node]--;
        if (indegree[node] == 0) {
          queue.add(node);
        }
      }
    }
    return result;
  }

  /*
     https://leetcode.com/problems/course-schedule/description/
     first we need to check if we have a cycle then only we check if we can have take all the curse
     DAG only can have the topology sort
  */
  public boolean canFinish(int numCourses, int[][] prerequisites) {

    List<List<Integer>> graph = new ArrayList<>();

    for (int i = 0; i < numCourses; i++) {
      graph.add(new ArrayList<>());
    }

    int[] indegree = new int[numCourses];

    for (int[] course : prerequisites) {
      graph.get(course[1]).add(course[0]);
      indegree[course[0]]++;
    }

    Deque<Integer> queue = new ArrayDeque<>();

    for (int i = 0; i < numCourses; i++) {
      if (indegree[i] == 0) {
        queue.offer(i);
      }
    }
    int count = 0;

    while (!queue.isEmpty()) {
      int temp = queue.poll();
      count++;
      for (int node : graph.get(temp)) {
        indegree[node]--;
        if (indegree[node] == 0) {
          queue.offer(node);
        }
      }
    }

    return count == numCourses;
  }

  /*
     https://leetcode.com/problems/course-schedule-ii/
     we follow the same logic and then we will just need to add the print the data
     and have the count logic to make sure we comple all course
  */

  public int[] findOrder(int numCourses, int[][] prerequisites) {
    List<List<Integer>> graph = new ArrayList<>();

    for (int i = 0; i < numCourses; i++) {
      graph.add(new ArrayList<>());
    }

    int[] indegree = new int[numCourses];

    for (int[] course : prerequisites) {
      graph.get(course[1]).add(course[0]);
      indegree[course[0]]++;
    }

    Deque<Integer> queue = new ArrayDeque<>();

    for (int i = 0; i < numCourses; i++) {
      if (indegree[i] == 0) {
        queue.offer(i);
      }
    }
    int[] courseOrder = new int[numCourses];
    int index = 0;
    int count = 0;
    while (!queue.isEmpty()) {
      int temp = queue.poll();
      courseOrder[index] = temp;
      index++;
      count++;

      for (int node : graph.get(temp)) {
        indegree[node]--;
        if (indegree[node] == 0) {
          queue.offer(node);
        }
      }
    }

    return count == numCourses ? courseOrder : new int[0];
  }

  // https://leetcode.com/problems/largest-color-value-in-a-directed-graph/
  /*
     for this the approch is that we have the aply table for n node and have the size is 26
     it look like n*26
     so when we process the 1 0 indegrree node
     if the dependen become 0 we add that in the queue
     and after the every call we make the call that for the see if the curent is max or the indegree is max and uopdate the value


     Note -: we can have the array and it work after then teh other i have a DP approch and and checn with chatgpt and it also suggest taht so we can use this appch
     and + it work fin as we have the easy element the check point
  */

  public int largestPathValue(String colors, int[][] edges) {
    int ans = 0;
    int n = colors.length();
    List<List<Integer>> graph = new ArrayList<>();
    List<Integer> indegree = new ArrayList<>(Collections.nCopies(n, 0));
    List<List<Integer>> current = new ArrayList<>();

    for (int i = 0; i < n; i++) {
      current.add(new ArrayList<>(Collections.nCopies(26, 0)));
    }

    for (int i = 0; i < n; i++) {
      graph.add(new ArrayList<>());
    }
    for (int i = 0; i < edges.length; i++) {
      int v = edges[i][0];
      int u = edges[i][1];
      graph.get(u).add(v);
      indegree.set(v, indegree.get(v) + 1);
    }
    int process = 0;

    Queue<Integer> queue = new ArrayDeque<>();
    for (int i = 0; i < n; i++) {
      if (indegree.get(i) == 0) {
        queue.add(i);
      }
    }

    while (queue.size() > 0) {
      int f = queue.peek();
      process++;
      queue.remove();
      current.get(f).set(colors.charAt(f) - 'a', current.get(f).get(colors.charAt(f) - 'a') + 1);
      ans = Math.max(ans, current.get(f).get(colors.charAt(f) - 'a'));
      for (int i : graph.get(f)) {
          indegree.set(i, indegree.get(i) - 1);

        if (indegree.get(i) == 0) {
          queue.add(i);
        }
        for (int j = 0; j < 26; j++) {
          current.get(i).set(j, Math.max(current.get(f).get(j), current.get(i).get(j)));
        }
      }
    }

    return process == n ? ans : -1;
  }

  static void main() {}
}
