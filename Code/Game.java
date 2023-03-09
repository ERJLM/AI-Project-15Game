import java.util.Stack;

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

   boolean isCycle(Board node){
    ArrayDeque<String> reached = new ArrayDeque<String>();
    Stack<String> path = new Stack<String>();
    return(isCycle1(reached, path, node));
   }

   boolean isCycle1(ArrayDeque<String> reached,Stack<String> path , Board node){

    //If the node is already in the path, there is a cycle.
    if(path.contains(node.toString())){
      return true;
    }
    //If the node has already been visited and it is not in the path, there is no cycle.
    if(reached.contains(node.toString())){
      return false;
    }

    path.add(node.toString());
    reached.add(node.toString());

    // Check all the neighbors of the current node
    for (Board neighbor : node.expand()) {
        
            // Recursively check if the neighbor node contains a cycle
            if (isCycle1(reached, path, neighbor)) {
                return true;
            }
    }
    path.remove(node.toString());
    return false;
   }
  
  
  Board searchDfs(int l){
    double start = System.currentTimeMillis();
    Stack<Board> frontier = new Stack<Board>();
    Board result = null;
    int space = 0;
    frontier.push(Board);
    while(!(frontier.isEmpty())){
     Board node = frontier.pop();
     if(node.equals(Goal)){
       double end = System.currentTimeMillis();
       double elapsedTime = end - start;
       System.out.println("Solution found ");
       System.out.println("Nodes generated: " + space);
       System.out.println("Depth: " + node.getG());
       System.out.println("Time: " + (elapsedTime/1000) + " seconds");
        return node;
     }
     if(node.getG() > l){
       System.out.println("May have a solution deeper than the limit");
       result = null;
       return result;
     }
     else if(!isCycle(node)){
       for (Board neighbor : node.expand()) {
             frontier.push(neighbor);
      }
     }
 }
 System.out.println("Solution not found");
 return result;
}

Board searchIdfs(){
  double start = System.currentTimeMillis();
  Stack<Board> frontier = new Stack<Board>();
  int space = 0;
    Board result = null;
    frontier.push(Board);
    while(!(frontier.isEmpty())){
     Board node = frontier.pop();
     if(node.equals(Goal)){
       double end = System.currentTimeMillis();
       double elapsedTime = end - start;
       System.out.println("Solution found ");
       System.out.println("Nodes generated: " + space);
       System.out.println("Depth: " + node.getG());
       System.out.println("Time: " + (elapsedTime/1000) + " seconds");
        return node;
     }

     else if(!isCycle(node)){
       for (Board neighbor : node.expand()) {
             frontier.push(neighbor);
      }
     }
 }
 System.out.println("Solution not found");
 return result;
}

    Board searchAStar(){
      double start = System.currentTimeMillis();
      PriorityQueue<Board> frontier = new PriorityQueue<>(Comparator.comparingInt(B -> B.getG() + B.ManhattanDist(Goal)));
      frontier.add(Board);
      int space = 1; //Number of nodes added to frontier
      ArrayDeque<String> reached = new ArrayDeque<String>();
      while (!frontier.isEmpty()) {
        // Remove the node with the lowest cost plus heuristic value from the frontier
        Board node = frontier.poll();

        // Check if the current node is the goal node
        if (node.equals(Goal)) {
            double end = System.currentTimeMillis();
            double elapsedTime = end - start;
            System.out.println("Solution found!");
            System.out.println("Nodes generated: " + space);
            System.out.println("Depth: " + node.getG());
            System.out.println("Time: " + (elapsedTime/1000) + " seconds");
            return node;
        }
        reached.add(node.toString());
        
        for (Board neighbor : node.expand()) {
          if (!reached.contains(neighbor.toString())) {
              frontier.add(neighbor);
              space++;
          }
        }
      }
      System.out.println("Solution not found");
      return null;
    }

   
    Board searchAStar2(){
      double start = System.currentTimeMillis();
      PriorityQueue<Board> frontier = new PriorityQueue<>(Comparator.comparingInt(B -> B.getG() + B.heuristic2(Goal)));
      frontier.add(Board);
      int space = 1; //Number of nodes added to frontier
      ArrayDeque<String> reached = new ArrayDeque<String>();
      while (!frontier.isEmpty()) {
        // Remove the node with the lowest cost plus heuristic value from the frontier
        Board node = frontier.poll();

        // Check if the current node is the goal node
        if (node.equals(Goal)) {
            double end = System.currentTimeMillis();
            double elapsedTime = end - start;
            System.out.println("Solution found!");
            System.out.println("Nodes generated: " + space);
            System.out.println("Depth: " + node.getG());
            System.out.println("Time: " + (elapsedTime/1000) + " seconds");
            return node;
        }
        reached.add(node.toString());
        
        for (Board neighbor : node.expand()) {
          if (!reached.contains(neighbor.toString())) {
              frontier.add(neighbor);
              space++;
          }
        }
      }
      System.out.println("Solution not found");
      return null;
    }

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
                System.out.println("Nodes generated: " + space);
                System.out.println("Depth: " + node.getG());
                System.out.println("Time: " + (elapsedTime/1000) + " seconds");
                return node;
            }

            // Add the current node to the explored set
            reached.add(node.toString());

            for (Board neighbor : node.expand()) {
              if (!reached.contains(neighbor.toString())) {
                  frontier.add(neighbor);
                  space++;
              }
          }
      }

      System.out.println("Solution not found");
      return null;
  }

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
              System.out.println("Nodes generated: " + space);
              System.out.println("Depth: " + node.getG());
              System.out.println("Time: " + (elapsedTime/1000) + " seconds");
              return node;
          }

          // Add the current node to the explored set
          reached.add(node.toString());

          for (Board neighbor : node.expand()) {
            if (!reached.contains(neighbor.toString())) {
                frontier.add(neighbor);
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
           for(Board x : node.expand()){
           if(x.equals(Goal)){
            double end = System.currentTimeMillis();
            double elapsedTime = end - start;
            System.out.println("Solution found");
            System.out.println("Nodes generated: " + space);
            System.out.println("Depth: " + x.getG());
            System.out.println("Time: " + (elapsedTime/1000) + " seconds");
             return x;
           }
          
           if(!(reached.contains(x.toString()))){
            reached.add(x.toString());
            frontier.add(x);
            space++;
           }
        }
        }
        System.out.println("Solution not found");
        return null;
    }


}
