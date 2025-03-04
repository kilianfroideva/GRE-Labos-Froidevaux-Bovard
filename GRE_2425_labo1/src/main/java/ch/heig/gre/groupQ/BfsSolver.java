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
            for (int neighbor : graph.neighbors(current)) {
                if (graph.areAdjacent(neighbor, current) && discoveredFrom[neighbor] == -1) {
                    discoveredFrom[neighbor] = current;
                    queue.add(neighbor);
                    if (neighbor == destination) {
                        found = true;
                        break;
                    }
                }
            }
        }

        if (!found) {
            // No path found
            return solutions;
        }

        // Backtrack to find the path
        for (int at = destination; at != source; at = discoveredFrom[at]) {
            solutions.add(at);
        }
        solutions.add(source);
        Collections.reverse(solutions);

        System.out.println("Solved with " + numberTreated + " vertices");
        return solutions;
    }
}
