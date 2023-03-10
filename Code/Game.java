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

    //AStar Search with the Manhattan Distance heuristic
    Board searchAStar(){
      double start = System.currentTimeMillis();
      PriorityQueue<Board> frontier = new PriorityQueue<>(Comparator.comparingInt(B -> B.getG() + B.ManhattanDist(Goal)));
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
 
    //AStar Search with the heuristic that uses the summmation of pieces out of place 
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
   
    //Greedy Search with the Manhattan Distance heuristic
    Board searchGreedy(){
      double start = System.currentTimeMillis();
      PriorityQueue<Board> frontier = new PriorityQueue<>(Comparator.comparingInt(n -> n.ManhattanDist(Goal)));
      frontier.add(Board);
      int space = 1; //Number of nodes added to frontier
      ArrayDeque<String> reached = new ArrayDeque<String>();

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

  //Greedy Search with the heuristic that uses the summmation of pieces out of place 
  Board searchGreedy2(){
    double start = System.currentTimeMillis();
    PriorityQueue<Board> frontier = new PriorityQueue<>(Comparator.comparingInt(n -> n.heuristic2(Goal)));
    frontier.add(Board);
    int space = 1; //Number of nodes added to frontier
    ArrayDeque<String> reached = new ArrayDeque<String>();

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

    Board searchBfs(){
        double start = System.currentTimeMillis();
        Board node = Board;
        if (node.equals(Goal)){
             System.out.println("Solution found");
             return node;
        }
        ArrayDeque<Board> frontier = new ArrayDeque<Board>();
        ArrayDeque<String> reached = new ArrayDeque<String>();
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


//Deep limited search
Board searchDls(int l) {
  double startTime = System.currentTimeMillis();
  int depthLimit = 0;
  while (depthLimit <= l) {
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
          if (node.getG() < depthLimit) {
              for (Board child : node.expand()) {
                  if (!reached.contains(child.toString())) {
                      reached.add(child.toString());
                      frontier.push(child);
                      space++;
                  } 
              }
          }
      }
      depthLimit++;
  }
  
  return null;
}
}
