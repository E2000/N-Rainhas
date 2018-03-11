import java.util.Scanner;

/* 
Objetivo: 
Inserir n rainhas num tabuleiro de xadrez sem que nenhuma se ataque
*/

public class Main {
  
  private static int n;
  private static int[][] tab;
  /* 
  Matriz bidimensional para o tabuleiro de xadrez
  
  Funcionamento do tabuleiro
  0 = não há nenhuma rainha nesta posição
  1 = há uma rainha nesta posição
  */
  
  public static void main(String[] args) {
    System.out.print("Escolha o valor de n (Recomendado: 8)\n>> ");
    n = new Scanner(System.in).nextInt();
    tab = new int[n][n];
    inicializaTabuleiro();
    resolver();
  }
  
  public static void inicializaTabuleiro() {
    for (int x = 0; x < n; x++) {
      for (int y = 0; y < n; y++) {
        tab[x][y] = 0;
      }
    }
    // Inicializando todas as posições com 0
  }
  
  public static void desenharTabuleiro() {
    for (int y = 0; y < n; y++) {
      for (int x = 0; x < n; x++) {
        System.out.print(tab[x][y] == 0 ? " - " : " X ");
      }
      System.out.println();
    }
  }
  
  public static void resolver() {
    boolean resolvido = false;
    int maiornumRainhas = 0;
    int[] jogadaAnterior = new int[n];
    int numCombinacoes = 0;
    //Vetor que indica qual o último y usado em determinada coluna
    for (int i = 0; i < n; i++)
      jogadaAnterior[i] = 0;
    while (!resolvido) {
      inicializaTabuleiro();
      int numRainhas = 0;
      for (int x = 0; x < n; x++) {
        //Percorrer colunas para encontrar y certo a inserir
        int r = (int) (Math.random() * 2);
        int y = jogadaAnterior[x] < n-1 ? jogadaAnterior[x] + r : 0;
        int tentativas = 0;
        while (!posLivre(x, y)) {
          if (tentativas == n) 
            break;
          if (y < n-1) y++;
          else y = 0;
          tentativas++;
        }
        if (posLivre(x, y)) {
          tab[x][y] = 1;
          numRainhas++;
          if (numRainhas == n) {
            desenharTabuleiro();
            resolvido = true;
          }
          jogadaAnterior[x] = y;
        }
      }
      numCombinacoes++;
    }
    System.out.println("número de combinações necessárias: " + numCombinacoes);
  }
  
  public static boolean posLivre(int posX, int posY) {
    // Verifica se é possível inserir uma rainha nesta posição sem atacar ninguém
    
    int x, y;
    
    for (x = 0; x < n; x++) {
      if (tab[x][posY] == 1) {
        return false;
      }
    }
    // Checando horizontal
    
    for (y = 0; y < n; y++) {
      if (tab[posX][y] == 1) {
        return false;
      }
    }
    // Checando vertical
    
    x = posX; y = posY;
    while (x > 0 && y > 0) {
      x--;
      y--;
      if (tab[x][y] == 1) {
        return false;
      }
    }
    // Checando diagonal 1
    
    x = posX; y = posY;
    while (x < (n-1) && y < (n-1)) {
      x++;
      y++;
      if (tab[x][y] == 1) {
        return false;
      }
    }
    // Checando diagonal 2
    
    x = posX; y = posY;
    while (x > 0 && y < (n-1)) {
      x--;
      y++;
      if (tab[x][y] == 1) {
        return false;
      }
    }
    // Checando diagonal 3
    
    x = posX; y = posY;
    while (x < (n-1) && y > 0) {
      x++;
      y--;
      if (tab[x][y] == 1) {
        return false;
      }
    }
    // Checando diagonal 4
    
    return true;
  }
  
}





