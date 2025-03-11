package ch.heig.gre.groupQ;

import ch.heig.gre.graph.Edge;
import ch.heig.gre.graph.Graph;
import ch.heig.gre.graph.VertexLabelling;
import ch.heig.gre.maze.MazeSolver;

import java.util.*;

public final class BfsSolver implements MazeSolver {
  @Override
    public List<Integer> solve(Graph graph, int source, int destination, VertexLabelling<Integer> treatments) {
        List<Integer> solutions = new ArrayList<>();

        // We already found the path
        if (source == destination) {
            return solutions;
        }

        Queue<Integer> queue = new LinkedList<>();
        int[] discoveredFrom = new int[graph.nbVertices()];
        Arrays.fill(discoveredFrom, -1);

        queue.add(source);
        discoveredFrom[source] = source;

        int numberTreated = 0;
        boolean found = false;

        while (!queue.isEmpty() && !found) {
            int current = queue.poll();
            treatments.setLabel(current, numberTreated++);

            // Treat all neighbors
            for (int neighbor : graph.neighbors(current)) {

                // If no wall bewteen neighbor and current vertex AND the neighbor not already discovered
                if (graph.areAdjacent(neighbor, current) && discoveredFrom[neighbor] == -1) {

                    // From where it was discovered to find the solution path at the end
                    discoveredFrom[neighbor] = current;

                    // If the neighbor is the destination, it was found
                    if (neighbor == destination) {
                        found = true;
                        break;
                    }

                    // Add to the queue to being treated
                    queue.add(neighbor);

                }
            }
        }

        if (!found) {
            // No path found, returns an empty solutions list
            return solutions;
        }

        // Backtrack to find the path and then reverse
        for (int at = destination; at != source; at = discoveredFrom[at]) {
            solutions.add(at);
        }
        solutions.add(source);
        Collections.reverse(solutions);

        return solutions;
    }
}
