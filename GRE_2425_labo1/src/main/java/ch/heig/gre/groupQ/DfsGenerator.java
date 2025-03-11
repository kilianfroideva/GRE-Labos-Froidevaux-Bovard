package ch.heig.gre.groupQ;

import ch.heig.gre.graph.Edge;
import ch.heig.gre.maze.MazeBuilder;
import ch.heig.gre.maze.MazeGenerator;
import ch.heig.gre.maze.Progression;

import java.util.*;

// TODO: renommer le package (Shift + F6) selon la lettre attribuée à votre groupe


public final class DfsGenerator implements MazeGenerator {
  /**
   * Performs a depth-first search traversal starting from a given vertex.
   * Builds a maze by removing walls between vertices as they are discovered.
   *
   * @param builder The maze builder containing topology and progression information
   * @param from The starting vertex for the DFS traversal
   */
  private void DFS_neigbors(MazeBuilder builder, int from) {
    int size = builder.topology().nbVertices();

    // Use of BitSet for better memory complexity (a boolean array is equivalent as an int array)
    BitSet discovered = new BitSet(size);
    Stack<Integer> vertexStack = new Stack<>();

    // Parent serves only to remove wall between current and parent if we "jump" while backtracking the DFS
    Stack<Integer> parentStack = new Stack<>();

    // Push initial vertex
    vertexStack.push(from);
    parentStack.push(-1);
    builder.progressions().setLabel(from, Progression.PROCESSING);

    while (!vertexStack.isEmpty()) {
      int current = vertexStack.pop();
      int parent = parentStack.pop();

      // If pop from the stack, the vertice is being processed
      builder.progressions().setLabel(current, Progression.PROCESSED);

      // If already discovered, skip processing (could be case if a vertex is pushed to time in the stack and the one on top, the last one, was already processed)
      if (discovered.get(current)) {continue;}

      // current vertex was always NOT discovered before from here
      // Mark as discovered
      discovered.set(current);

      // Remove wall between current and parent
      if (parent != -1) {
        builder.removeWall(current, parent);
      }

      // Shuffle the neighbors to walk randomly in the labyrinth
      List<Integer> neighbors = builder.topology().neighbors(current);
      Collections.shuffle(neighbors);

      // For each neighbor being not discovered, push into the stack and apply the tag will be discovered
      for (Integer neighbor : neighbors) {
        if (!discovered.get(neighbor)) {
          vertexStack.push(neighbor);
          parentStack.push(current);
          builder.progressions().setLabel(neighbor, Progression.PROCESSING);
        }
      }
    }
  }


  private void DFS_backtrack(MazeBuilder builder, int from) {
    // Note that the use of Set isnt optimal for time complexity

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
  public void generate(MazeBuilder builder, int from) {

    // Choose which algorithm to use
    boolean DFS_neighbor_algorithm = true;

    if(DFS_neighbor_algorithm) {
      DFS_neigbors(builder, from);
    } else {
      DFS_backtrack(builder, from);
    }
  }

  @Override
  public boolean requireWalls() {
    // Walls everywhere as default
    return true;
  }
}
