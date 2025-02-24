package ch.heig.gre.groupQ;

import ch.heig.gre.maze.MazeBuilder;
import ch.heig.gre.maze.MazeGenerator;

import java.util.Stack;
import java.util.Random;

// TODO: renommer le package (Shift + F6) selon la lettre attribuée à votre groupe


public final class DfsGenerator implements MazeGenerator {

  /*
  private void BFS_Init()

  private void BFS(MazeBuilder builder, int from, Stack<Integer> beingTreated){
    Stack<Integer> beingTreated = new Stack<>();
    beingTreated.add(from);

    Random rand = new Random();
    int next = builder.topology().neighbors(from).get(rand.nextInt(builder.topology().neighbors(from).size()));

    builder.removeWall(from,next);

    BFS()

  }

  */

  @Override
  public void generate(MazeBuilder builder, int from) {
    //choose a random neighbor from from to continue BFS, erase the wall and continue.

    //throw new UnsupportedOperationException("Not implemented yet");
  }

  @Override
  public boolean requireWalls() {
    return true;
    //throw new UnsupportedOperationException("Not implemented yet");
  }
}
