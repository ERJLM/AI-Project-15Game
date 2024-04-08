# Java version

``` OpenJDK - version 17 ```

# Game description
The also known "15 Game" is a famous numerical puzzle that consists of a 4x4 matrix with 15 pieces numbered from 1 to 15, plus an empty space "0". The objective of the game is to rearrange the numbered pieces, moving them horizontally or vertically, to place them in ascending numerical order, with the empty piece occupying the last space. The challenge is in finding the correct sequence of movements to solve the puzzle and reach the correct final state.

# Instruction manual for running the program:

- The Board class (“Board.java” file) represents the game settings, and the Game class (“Game.java” file) represents the game
and has all the necessary research methods.
- The “Main.java” file contains the main function, and is the file we will use
to compile the program.

In the “Main.java” file we have everything ready to start searching.


# Let's see how each search is carried out:

Let G be a variable of the Game class

- Iterative in-depth search:

   ```G.searchIdfs();```

- Limited depth search (auxiliary search used for iterative depth search):

   ```G.searchDls(l); // l is the depth limit```

- Search The Star (with the Manhattan Distance heuristic):

   ```G.searchAStar(); ```

- Search The Star (with the heuristic that uses the sum of out-of-place pieces):

   ```G.searchAStar2(); ```

- In-depth search:

   ```G.searchDfs();```

- Greedy Search (with Manhattan Distance heuristic):

   ```G.searchGreedy(); ```

- Greedy Search (with the heuristic that uses the sum of out-of-place pieces):

  ``` G.searchGreedy2(); ```

- Breadth Search:

   ```G.searchBfs();```

Since we created an iterative menu, it will not be necessary to do the searches manually, just write “help” after compiling, and follow the instructions.
