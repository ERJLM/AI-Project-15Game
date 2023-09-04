# Versão do Java

``` OpenJDK - version 17 ```

# Descrição do jogo
O "15 Game" também conhecido é um famoso quebra-cabeça numérico que consiste em uma matriz de 4x4 com 15 peças numeradas de 1 a 15, além de um espaço vazio "0". O  objetivo do jogo é rearranjar as peças numeradas, movendo-as horizontal ou verticalmente, para colocá-las em ordem numérica crescente, com a peça vazia ocupando o último espaço. O desafio está em encontrar a sequência correta de movimentos para resolver o quebra-cabeça e atingir o estado final correto.

# Manual de Instruções para correr o programa:

- A classe Board(ficheiro “Board.java”) representa as configurações do jogo, e a classe Game(ficheiro “Game.java”), representa o jogo
e tem todos os métodos de pesquisa necessários.
- O ficheiro “Main.java” contém a função main, e é o ficheiro que usaremos 
para compilar o programa.

No ficheiro “Main.java” temos tudo pronto para começar a fazer as pesquisas.


# Vejamos como se faz cada pesquisa:

Seja G uma variável da classe Game

- Pesquisa iterativa em profundidade:

   ```G.searchIdfs();```

- Pesquisa em profundidade limitada(pesquisa auxiliar utilizada para a pesquisa iterativa em profundidade):

   ```G.searchDls(l); // l é o limite de profundidade```

- Pesquisa A Estrela(com a heurística Manhattan Distance):

   ```G.searchAStar(); ```

- Pesquisa A Estrela(com a heurística que usa a soma das peças fora do lugar):

   ```G.searchAStar2(); ```

- Pesquisa em profundidade:

   ```G.searchDfs();```

- Pesquisa Gulosa(com heurística Manhattan Distance):

   ```G.searchGreedy(); ```

- Pesquisa Gulosa(com a heurística que usa a soma das peças fora do lugar):

  ``` G.searchGreedy2();    ```

- Pesquisa em Largura:

   ```G.searchBfs();```

Sendo que nós criamos um menu iterativo, não será necessário fazer as pesquisas manualmente, basta escrever “help” depois de compilar, e seguir as instruções.
