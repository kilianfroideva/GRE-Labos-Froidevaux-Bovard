package ch.heig.gre.groupQ;

import ch.heig.gre.graph.Edge;
import ch.heig.gre.maze.MazeBuilder;
import ch.heig.gre.maze.MazeGenerator;
import ch.heig.gre.maze.Progression;

import java.util.*;

// TODO: renommer le package (Shift + F6) selon la lettre attribuée à votre groupe


public final class DfsGenerator implements MazeGenerator {

  private void DFS(MazeBuilder builder, int from) {
    int size = builder.topology().nbVertices();
    boolean[] discovered = new boolean[size];
    Stack<int[]> stack = new Stack<>();

    stack.push(new int[]{from, -1});

    while (!stack.isEmpty()) {
      int[] pair = stack.pop();
      int current = pair[0];
      int parent = pair[1];

      builder.progressions().setLabel(current, Progression.PROCESSING);

      if (!discovered[current]) {
        discovered[current] = true;

        if (parent != -1) {
          builder.removeWall(current, parent);
        }

        List<Integer> neighbors = builder.topology().neighbors(current);
        Collections.shuffle(neighbors);

        for (Integer neighbor : neighbors) {
          if (!discovered[neighbor]) {
            stack.push(new int[]{neighbor, current});
            builder.progressions().setLabel(neighbor, Progression.PENDING);
          }
        }
      }

      builder.progressions().setLabel(current, Progression.PROCESSED);
    }
  }

  @Override
  public void generate(MazeBuilder builder, int from) {

    DFS(builder, from);
    //throw new UnsupportedOperationException("Not implemented yet");
  }

  @Override
  public boolean requireWalls() {
    return true;
    //throw new UnsupportedOperationException("Not implemented yet");
  }
}
