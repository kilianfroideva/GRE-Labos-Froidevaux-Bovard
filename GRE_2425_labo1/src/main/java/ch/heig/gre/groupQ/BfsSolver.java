package ch.heig.gre.groupQ;

import ch.heig.gre.graph.Graph;
import ch.heig.gre.graph.VertexLabelling;
import ch.heig.gre.maze.MazeSolver;

import java.util.*;

public final class BfsSolver implements MazeSolver {
  @Override
  public List<Integer> solve(Graph graph, int source, int destination, VertexLabelling<Integer> treatments) {
    // Initialize
    Queue<Integer> queue = new LinkedList<>();
    Map<Integer, Integer> parentMap = new HashMap<>();
    Set<Integer> visited = new HashSet<>();

    // Start from source
    queue.add(source);
    visited.add(source);
    treatments.setLabel(source, 1);

    // BFS loop
    boolean found = false;
    while (!queue.isEmpty() && !found) {
      int current = queue.poll();
      int currentDistance = treatments.getLabel(current);

      // Check all neighbors
      for (int neighbor : graph.neighbors(current)) {
        if (!visited.contains(neighbor)) {
          visited.add(neighbor);
          queue.offer(neighbor);
          parentMap.put(neighbor, current);
          treatments.setLabel(neighbor, currentDistance + 1);

          // If destination reached, stop searching
          if (neighbor == destination) {
            found = true;
            break;
          }
        }
      }
    }

    // Reconstruct path
    List<Integer> path = new ArrayList<>();
    if (visited.contains(destination)) {
      int current = destination;
      while (current != source) {
        path.addFirst(current);
        current = parentMap.get(current);
      }
      path.addFirst(source);
    }

    return path;
  }

}
