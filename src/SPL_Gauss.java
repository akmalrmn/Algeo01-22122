import java.util.Scanner;

import Matrix.MatrixADT;

public class SPL_Gauss {
  static Scanner scanner = new Scanner(System.in);
  static void performInconsistentV1(MatrixADT matrix){
    for (int k = 0; k < matrix.getRows(); k++) {
      int idxMax = k;
      double valueMax = Math.abs(matrix.getElmt(k, k));

      for (int i = k + 1; i < matrix.getRows(); i++) if (Math.abs(matrix.getElmt(i, k)) > valueMax) {
        valueMax = matrix.getElmt(i, k);
        idxMax = i;
      }

      if (idxMax != k) swap_row(matrix, k, idxMax);

      if (valueMax == 0) continue;

      matrix.displayMatrix();
      System.out.println();

      for (int i = k + 1; i < matrix.getRows(); i++) {
        if (matrix.getElmt(i, k) != valueMax){
          double f = matrix.getElmt(i, k) / matrix.getElmt(k, k);

          for (int j = k + 1; j <= matrix.getRows(); j++){
            double temp = matrix.getElmt(i, j) - matrix.getElmt(k,j) * f;
            matrix.setElmt(i, j, temp);
          }
        } else {
          for (int j = k + 1; j <= matrix.getRows(); j++){
            double temp = matrix.getElmt(i, j) - matrix.getElmt(k,j);
            matrix.setElmt(i, j, temp);
          }
        }

        matrix.setElmt(i, k, 0); 
        }
    }

    matrix.displayMatrix();
    System.out.println();

    for (int k = 0; k < matrix.getRows(); k++){
      double divider = 0;
    
      for (int j = 0; j < matrix.getCols(); j++){
        if (matrix.getElmt(k, j) != 0 && divider == 0){
          divider = matrix.getElmt(k, j);
        }

        if (divider != 0){
          double temp = matrix.getElmt(k, j) / divider;
          matrix.setElmt(k, j, temp);
        }
      }
    }

    inconsistentSolution(matrix);
  }

  static void inconsistentSolution(MatrixADT matrix){
    int kolom = matrix.getCols();
    int row = matrix.getRows();

    boolean[] solusi = new boolean[kolom - 1];

    for(int i = 0; i < kolom - 1; i++){
      solusi[i] = false;
    }
    
    // int pointerAlphabet = 0;
    // char[] alphabet = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};

    for (int i = row - 1; i >= 0; i--){
      for (int j = i; j < kolom - 1; j++){
        boolean flag = false;
        if (matrix.getElmt(i, j) != 0){
          flag = true;

          if(solusi[j]) continue;
          
          if ( matrix.getElmt(i, kolom -1) != 0 ){
            System.out.printf("x%d = %.6f ", j + 1, matrix.getElmt(i, kolom -1));
          } else {
            if (j == kolom - 2) {
              System.out.printf("x%d = %.6f ", j + 1, matrix.getElmt(i, kolom - 1)); 
            } else {
              System.out.printf("x%d = 0.00 ", j + 1);
            }       
          }

          for(int k = j + 1; k < kolom - 1; k++){
            if(matrix.getElmt(i, k) == 1){
              System.out.printf("- x%d ", k + 1);
            } else if (matrix.getElmt(i, k) > 0){
              System.out.printf("- %.6fx%d ", matrix.getElmt(i, k), k + 1);
            } else if (matrix.getElmt(i, k) < 0) {
              System.out.printf("+ %.6fx%d ", Math.abs(matrix.getElmt(i, k)), k + 1);
            }
          }

          solusi[j] = true;
          System.out.println();
        } 

        if (flag){
          break;
        }
      }
    }

    for(int i = 0; i < kolom - 1; i++){
      if(!solusi[i]){
        System.out.printf("x%d = bil. real\n", i + 1);
      }
    }

  }

  static void performInconsistent(MatrixADT matrix){
    int rowNow = 0;
    for (int k = 0; k < matrix.getCols(); k++) {
      if (rowNow >= matrix.getRows()) break;

      int idxMax = rowNow;
      double valueMax = Math.abs(matrix.getElmt(rowNow, k));

      for (int i = rowNow + 1; i < matrix.getRows(); i++){
        if (Math.abs(matrix.getElmt(i, k)) > valueMax) {
          valueMax = matrix.getElmt(i, k);
          idxMax = i;
        }
      }

      if (idxMax != rowNow){
        swap_row(matrix, rowNow, idxMax);
      }

      if (valueMax == 0) continue;

      for (int i = rowNow + 1; i < matrix.getRows(); i++) {
        if (matrix.getElmt(i, k) != valueMax){
          double f = matrix.getElmt(i, k) / matrix.getElmt(rowNow, k);

          for (int j = k + 1; j <= matrix.getLastIdxCol(); j++){
            double temp = matrix.getElmt(i, j) - matrix.getElmt(rowNow,j) * f;
            matrix.setElmt(i, j, temp);
          }
        } else {
          for (int j = k + 1; j <= matrix.getLastIdxCol(); j++){
            double temp = matrix.getElmt(i, j) - matrix.getElmt(rowNow,j);
            matrix.setElmt(i, j, temp);
          }
        }

        matrix.setElmt(i, k, 0); 
      }

      rowNow++;
    }

    for (int k = 0; k < matrix.getRows(); k++){
      double divider = 0;
    
      for (int j = 0; j < matrix.getCols(); j++){
        if (matrix.getElmt(k, j) != 0 && divider == 0){
          divider = matrix.getElmt(k, j);
        }

        if (divider != 0){
          double temp = matrix.getElmt(k, j) / divider;
          matrix.setElmt(k, j, temp);
        }
      }
    }

    matrix.displayMatrix();

    inconsistentSolution(matrix);
  }

  static void performElimination(MatrixADT matrix) {
    int singular_flag = forwardElim(matrix);

    if (singular_flag != -1) {
      if (matrix.getElmt(singular_flag, matrix.getLastIdxCol()) != 0){
        System.out.print("SPL tersebut tidak memiliki solusi.\n");
      } else {
        System.out.print("SPL tersebut memiliki solusi parametrik.\n");
        performInconsistent(matrix);
      }

    } else {
       consistentSolution(matrix);
    }
  }

  static void swap_row(MatrixADT matrix, int i, int j) {
    for (int k = 0; k <= matrix.getLastIdxCol(); k++) {
      double temp = matrix.getElmt(i, k);
      matrix.setElmt(i, k, matrix.getElmt(j, k));
      matrix.setElmt(j, k, temp);
    }
  }

  static int forwardElim(MatrixADT matrix) {
    for (int k = 0; k < matrix.getRows(); k++) {
      int idxMax = k;
      double valueMax = Math.abs(matrix.getElmt(k, k));

      for (int i = k + 1; i < matrix.getRows(); i++){
        if (Math.abs(matrix.getElmt(i, k)) > valueMax) {
          valueMax = matrix.getElmt(i, k);
          idxMax = i;
        }
      }

      if (matrix.getElmt(idxMax, k) == 0) return k;

      if (idxMax != k) swap_row(matrix, k, idxMax);

      for (int i = k + 1; i < matrix.getRows(); i++) {
        double f = matrix.getElmt(i, k) / matrix.getElmt(k, k);

        for (int j = k + 1; j <= matrix.getRows(); j++){
          double temp = matrix.getElmt(i, j) - matrix.getElmt(k,j) * f;

          if(Math.abs(temp) < 1e-15) temp = 0;

          matrix.setElmt(i, j, temp);
        }
        matrix.setElmt(i, k, 0);
      }

    }
    return -1;
  }

  public static void consistentSolution(MatrixADT matrix) {
    int row = matrix.getRows();
    int lastIdxCol = matrix.getLastIdxCol();
    MatrixADT solution = new MatrixADT(matrix.getRows(), 1);

    for (int i = row - 1; i >= 0; i--){
        solution.setElmt(i, 0, matrix.getElmt(i, lastIdxCol));
        for (int j = i + 1; j < lastIdxCol; j++){
            double temp = solution.getElmt(i, 0) - matrix.getElmt(i, j) * solution.getElmt(j, 0);
            solution.setElmt(i, 0, temp);
        }

        double temp = solution.getElmt(i, 0) / matrix.getElmtDiagonal(i);
        solution.setElmt(i, 0, temp);
    }
 
    System.out.printf("\nSolusi dari SPL:\n");
    for (int i = 0; i < row; i++){
       System.out.printf("x%d = %.6f\n", i+1, solution.getElmt(i, 0));
    }
  }

  public static void main(String[] args) {
    int m = scanner.nextInt();
    int n = scanner.nextInt();

    MatrixADT matrix = new MatrixADT(m, n);
    matrix.readMatrix(m,n);

    if (m != n -1){
      performInconsistent(matrix);
    } else {
      performElimination(matrix);
    }
  }
}
