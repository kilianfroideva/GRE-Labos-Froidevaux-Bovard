package ch.heig.gre.groupQ;

import ch.heig.gre.maze.MazeBuilder;
import ch.heig.gre.maze.MazeGenerator;
import ch.heig.gre.maze.Progression;

import java.util.*;

// TODO: renommer le package (Shift + F6) selon la lettre attribuée à votre groupe


public final class DfsGenerator implements MazeGenerator {

  @Override
  public void generate(MazeBuilder builder, int from) {
    //Initialize
    Set<Integer> visited = new HashSet<>();
    Stack<Integer> dfsStack = new Stack<>();

    // Start from from
    dfsStack.push(from);
    visited.add(from);
    builder.progressions().setLabel(from, Progression.PROCESSING);

    // DFS loop
    while (!dfsStack.isEmpty()) {
      int current = dfsStack.peek();
      List<Integer> neighbors = builder.topology().neighbors(current);
      Collections.shuffle(neighbors);
      boolean foundUnvisitedNeighbor = false;
      for (int neighbor : neighbors) {
        // Check all neighbors and remove walls
        if (!visited.contains(neighbor)) {
          builder.progressions().setLabel(neighbor, Progression.PENDING);
          builder.removeWall(current, neighbor);

          dfsStack.push(neighbor);
          visited.add(neighbor);
          builder.progressions().setLabel(neighbor, Progression.PROCESSING);

          foundUnvisitedNeighbor = true;
          break;
        }
      }

      // If no unvisited neighbor, mark as processed
      if (!foundUnvisitedNeighbor) {
        builder.progressions().setLabel(current, Progression.PROCESSED);
        dfsStack.pop();

      }
    }
  }


  @Override
  public boolean requireWalls() {
    return true;
  }

}
