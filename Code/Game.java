import java.io.IOException;
import java.util.Stack;
import java.util.HashSet;
import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.Comparator;

public class Game{
   private Board Board, Goal;

    Game(int[] Bi, int[] Bf){
          Board = new Board(Bi);
          Goal = new Board(Bf);
    }

    Game(Board Bi, Board Bf){
        Board = Bi;
        Goal = Bf;
  }

   

   boolean isCycle(Board B){
     Board node = B;
     if(node.getParent() == null) return false;
     node = node.getParent();
     while(node != null){
     
        if((node).equals(B)){
          return true;
        }
        node = node.getParent();
     }
    return false;
   }


    /**
     * Depth First Search or DFS
     * <p>
     * The algorithm starts at the root node (selecting some arbitrary node as the root node in the case of a graph) and explores as far as possible along each branch before backtracking.
     * Description Source: Wikipedia.
     */
    Board searchDfs(){
          double start = System.currentTimeMillis();
          Stack<Board> frontier = new Stack<Board>();
          Board result = null;
          frontier.push(Board);
          int space = 1;
          Board node = Board;
          while(!(frontier.isEmpty())){
           node = frontier.pop();
           if(node.equals(Goal)){
             double end = System.currentTimeMillis();
             double elapsedTime = end - start;
             System.out.println("Solution found ");
             System.out.println("Space: " + space);
             System.out.println("Depth: " + node.getG());
             System.out.println("Time: " + (elapsedTime/1000) + " seconds");
              return node;
           }

            if(!isCycle(node)){

             for (Board child : node.expand()) {
                   frontier.push(child);
                   space++;
            }
           }
        }
        System.out.println("Solution not found");
        return result;
    }


    /**
     * AStar Search with the Manhattan Distance heuristic
     * <p>
     * A* algorithm only finds the shortest path from a specified source to a specified goal, and not the shortest-path tree from a specified source to all possible goals.
     * Manhattan Distance: The distance between two points measured along axes at right angles.
     * Description Source: Wikipedia.
     */
    Board searchAStar(){
      double start = System.currentTimeMillis();
      PriorityQueue<Board> frontier = new PriorityQueue<>(Comparator.comparingInt(B -> B.getG() + B.ManhattanDist(Goal)));
      frontier.add(Board);
      int space = 1; //Number of nodes added to frontier
      HashSet<String> reached = new HashSet<String>();
      while (!frontier.isEmpty()) {
        // Remove the node with the lowest f value from the frontier.
        Board node = frontier.poll();

        // Check if the current node is the goal node.
        if (node.equals(Goal)) {
            double end = System.currentTimeMillis();
            double elapsedTime = end - start;
            System.out.println("Solution found!");
            System.out.println("Space: " + space);
            System.out.println("Depth: " + node.getG());
            System.out.println("Time: " + (elapsedTime/1000) + " seconds");
            return node;
        }
        reached.add(node.toString());
        
        for (Board child : node.expand()) {
          if (!reached.contains(child.toString())) {
              frontier.add(child);
              space++;
          }
        }
      }
      System.out.println("Solution not found");
      return null;
    }


    /**
     * AStar Search with the heuristic that uses the summation of pieces out of place
     * <p>
     * A* algorithm only finds the shortest path from a specified source to a specified goal, and not the shortest-path tree from a specified source to all possible goals.
     * Description Source: Wikipedia.
     */
    Board searchAStar2(){
      double start = System.currentTimeMillis();
      PriorityQueue<Board> frontier = new PriorityQueue<>(Comparator.comparingInt(B -> B.getG() + B.heuristic2(Goal)));
      frontier.add(Board);
      int space = 1; //Number of nodes added to frontier
      HashSet<String> reached = new HashSet<String>();
      while (!frontier.isEmpty()) {
        // Remove the node with the lowest f value from the frontier
        Board node = frontier.poll();

        // Check if the current node is the goal node
        if (node.equals(Goal)) {
            double end = System.currentTimeMillis();
            double elapsedTime = end - start;
            System.out.println("Solution found!");
            System.out.println("Space: " + space);
            System.out.println("Depth: " + node.getG());
            System.out.println("Time: " + (elapsedTime/1000) + " seconds");
            return node;
        }
        reached.add(node.toString());
        
        for (Board child : node.expand()) {
          if (!reached.contains(child.toString())) {
              frontier.add(child);
              space++;
          }
        }
      }
      System.out.println("Solution not found");
      return null;
    }


    /**
     * Greedy Search with the Manhattan Distance heuristic
     * <p>
     * Greedy algorithm: follows the problem-solving heuristic of making the locally optimal choice at each stage.
     * Manhattan Distance: The distance between two points measured along axes at right angles.
     * Description Source: Wikipedia.
     */
    //Greedy Search with the Manhattan Distance heuristic
    Board searchGreedy(){
      double start = System.currentTimeMillis();
      PriorityQueue<Board> frontier = new PriorityQueue<>(Comparator.comparingInt(n -> n.ManhattanDist(Goal)));
      frontier.add(Board);
      int space = 1; //Number of nodes added to frontier
      HashSet<String> reached = new HashSet<String>();

      while(!frontier.isEmpty()){
        Board node = frontier.poll();

            // Check if the current node is the goal node
            if (node.equals(Goal)) {
                double end = System.currentTimeMillis();
                double elapsedTime = end - start;
                System.out.println("Solution found!");
                System.out.println("Space: " + space);
                System.out.println("Depth: " + node.getG());
                System.out.println("Time: " + (elapsedTime/1000) + " seconds");
                return node;
            }

            // Add the current node to the explored set.
            reached.add(node.toString());

            for (Board child : node.expand()) {
              if (!reached.contains(child.toString())) {
                  frontier.add(child);
                  space++;
              }
          }
      }

      System.out.println("Solution not found");
      return null;
  }


    /**
     * Greedy Search with the heuristic that uses the summation of pieces out of place
     * <p>
     * Greedy algorithm: follows the problem-solving heuristic of making the locally optimal choice at each stage.
     * Description Source: Wikipedia.
     */
    Board searchGreedy2(){
        double start = System.currentTimeMillis();
        PriorityQueue<Board> frontier = new PriorityQueue<>(Comparator.comparingInt(n -> n.heuristic2(Goal)));
        frontier.add(Board);
        int space = 1; //Number of nodes added to frontier
        HashSet<String> reached = new HashSet<String>();

        while(!frontier.isEmpty()){
          Board node = frontier.poll();

              // Check if the current node is the goal node
              if (node.equals(Goal)) {
                  double end = System.currentTimeMillis();
                  double elapsedTime = end - start;
                  System.out.println("Solution found!");
                  System.out.println("Space: " + space);
                  System.out.println("Depth: " + node.getG());
                  System.out.println("Time: " + (elapsedTime/1000) + " seconds");
                  return node;
              }

              // Add the current node to the explored set
              reached.add(node.toString());

              for (Board child : node.expand()) {
                if (!reached.contains(child.toString())) {
                    frontier.add(child);
                    space++;
                }
            }
    }

    System.out.println("Solution not found");
    return null;
}


    /**
     * Breadth-first search or BFS
     * <p>
     * The algorithm starts at the tree root and explores all nodes at the present depth prior to moving on to the nodes at the next depth level.
     * Description Source: Wikipedia.
     */
    Board searchBfs(){
        double start = System.currentTimeMillis();
        Board node = Board;
        if (node.equals(Goal)){
             System.out.println("Solution found");
             return node;
        }
        ArrayDeque<Board> frontier = new ArrayDeque<Board>();
        HashSet<String> reached = new HashSet<String>();
        frontier.add(node);
        int space = 1; //Number of nodes added to frontier
        reached.add(node.toString());
        while(!(frontier.isEmpty())){
           node = frontier.remove();
           for(Board child : node.expand()){
           if(child.equals(Goal)){
            double end = System.currentTimeMillis();
            double elapsedTime = end - start;
            System.out.println("Solution found");
            System.out.println("Space: " + space);
            System.out.println("Depth: " + child.getG());
            System.out.println("Time: " + (elapsedTime/1000) + " seconds");
             return child;
           }
          
           if(!(reached.contains(child.toString()))){
            reached.add(child.toString());
            frontier.add(child);
            space++;
           }
        }
        }
        System.out.println("Solution not found");
        return null;
    }

    /**
     * Iterative deepening depth-first search or IDS or IDDFS
     * <p>
     * IDDFS is optimal like breadth-first search, but uses much less memory; at each iteration, it visits the nodes in the search tree in the same order as depth-first search, but the cumulative order in which nodes are first visited is effectively breadth-first.
     * Description Source: Wikipedia.
     */
    Board searchIdfs() {
       int maxDepth = (int)Double.POSITIVE_INFINITY;
      for (int depth = 0; depth <= maxDepth; depth++) {
        Board result = searchDls(depth);
        if (result != null) {
          return result;
        }
      }
      System.out.println("Solution not found");
      return null;
    }

    /**
     * Complement of the IDDFS
     * <p>
     * Depth-limited search can solve the drawback of the infinite path in the Depth-first search.
     * Description Source: Wikipedia.
     */
    //Deep limited search
    Board searchDls(int l) {
      double startTime = System.currentTimeMillis();
      int depth = 0;
      while (depth <= l) {
          Board node = Board;
          HashSet<String> reached = new HashSet<String>();
          Stack<Board> frontier = new Stack<Board>();
          frontier.push(node);
          reached.add(node.toString());
          int space = 1;
          while (!frontier.isEmpty()) {
              node = frontier.pop();
              if (node.equals(Goal)) {
                  double elapsedTime = (System.currentTimeMillis() - startTime) / 1000.0;
                  System.out.println("Solution found");
                  System.out.println("Space: " + space);
                  System.out.println("Depth: " + node.getG());
                  System.out.println("Time: " + elapsedTime + " seconds");
                  return node;
              }
              if (node.getG() < depth) {
                  for (Board child : node.expand()) {
                      if (!reached.contains(child.toString())) {
                          reached.add(child.toString());
                          frontier.push(child);
                          space++;
                      }
                  }
              }
          }
          depth++;
      }

      return null;
    }
}
