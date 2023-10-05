import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import Matrix.MatrixADT;

public class SPL_Gauss {
  public static String fileName = "";

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
    inconsistentSolution(matrix);
  }

  static void performElimination(MatrixADT matrix) {
    int singular_flag = forwardElim(matrix);

    if (singular_flag != -1) {
      if (matrix.getElmt(singular_flag, matrix.getLastIdxCol()) != 0){
        System.out.print("SPL tersebut tidak memiliki solusi.\n");
      } else {
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

  static MatrixADT inputFile(String namaFile){

    int numRows = 0;
    int numCols = 0;

    try {
      File myObj = new File("./test/" + namaFile);
      Scanner sizeFinder = new Scanner(myObj);
      Scanner myReader = new Scanner(myObj);

      String dataTemp = sizeFinder.nextLine();

      for (int j = 0; j < dataTemp.length(); j++){
        if (dataTemp.charAt(j) == ' '){
            numCols++;
        }
      }
      numCols++;

      while(sizeFinder.hasNextLine()){
        sizeFinder.nextLine();
        numRows++;
      }
      numRows++;

      sizeFinder.close();

      MatrixADT matrix = new MatrixADT(numRows, numCols);
      
      int i = 0;
      while (myReader.hasNextLine()) {
        String[] line = myReader.nextLine().trim().split(" ");

        for (int j = 0; j < numCols; j++) {
            // System.out.println(Double.parseDouble(line[j]));
            matrix.setElmt(i, j, Double.parseDouble(line[j]));
        }
        i++;
      }
      myReader.close();

      return matrix;

    } catch (FileNotFoundException e) {
      MatrixADT matrix = new MatrixADT(0, 0);

      System.out.println("An error occurred.");
      e.printStackTrace();

      return matrix;
    }
    
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

    Scanner scanner = new Scanner(System.in);
    System.out.println("\nApakah ingin menyimpan solusi dalam file?\n1. Yes\n2. No");
    int input = scanner.nextInt();

    if (input == 1){
      outputFileConsistent(matrix);
    }

    scanner.close();
  }

  static void inconsistentSolution(MatrixADT matrix){
    System.out.print("SPL tersebut memiliki solusi parametrik.\n\n");
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

    Scanner scanner = new Scanner(System.in);
    System.out.println("Apakah ingin menyimpan solusi dalam file?\n1. Yes\n2. No\n");
    int input = scanner.nextInt();

    if (input == 1){
      outputFileInconsistent(matrix);
    }

    scanner.close();
  }

  static void outputFileInconsistent(MatrixADT matrix){
    if (fileName.length() == 0){
      fileName = "SPL_Gauss_KeyboardInput.txt";
    }

    try {
            File myObj = new File("./test/output/Solusi_" + fileName);
            if (myObj.createNewFile()) {
              System.out.println("File dibuat: " + myObj.getName());
            } else {
              System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("./test/output/Solusi_" + fileName));

            bw.write("Matriks hasil eliminasi:\n");
            for (int i = 0; i < matrix.getRows(); i++) {
                for (int j = 0; j < matrix.getCols(); j++) {
                    if (j == matrix.getLastIdxCol()){
                        bw.write(matrix.getElmt(i, j) + "");
                    } else{
                        bw.write(matrix.getElmt(i, j) + " ");
                    }
                }
                bw.newLine();
            }

            bw.write("SPL tersebut memiliki solusi parametrik.\n\n");
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
                    bw.write(String.format("x%d = %.6f ", j + 1, matrix.getElmt(i, kolom -1)));
                  } else {
                    if (j == kolom - 2) {
                      bw.write(String.format("x%d = %.6f ", j + 1, matrix.getElmt(i, kolom - 1)));
                    } else {
                      bw.write(String.format("x%d = 0.00 ", j + 1));
                    }       
                  }

                  for(int k = j + 1; k < kolom - 1; k++){
                    if(matrix.getElmt(i, k) == 1){
                      bw.write(String.format("- x%d ", k + 1));
                    } else if (matrix.getElmt(i, k) > 0){
                      bw.write(String.format("- %.6fx%d ", matrix.getElmt(i, k), k + 1));
                    } else if (matrix.getElmt(i, k) < 0) {
                      bw.write(String.format("+ %.6fx%d ", Math.abs(matrix.getElmt(i, k)), k + 1));
                    }
                  }

                  solusi[j] = true;
                  bw.newLine();
                } 

                if (flag){
                  break;
                }
              }
            }

            for(int i = 0; i < kolom - 1; i++){
              if(!solusi[i]){
                bw.write(String.format("x%d = bil. real\n", i + 1));
              }
            }

            bw.flush();
            bw.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
  }

  static void outputFileConsistent(MatrixADT matrix){
    if (fileName.length() == 0){
      fileName = "SPL_Gauss_KeyboardInput.txt";
    }

    try {
            File myObj = new File("./test/output/Solusi_" + fileName);
            if (myObj.createNewFile()) {
              System.out.println("File dibuat: " + myObj.getName());
            } else {
              System.out.println("File already exists. Akan dilakukan replace.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("./test/output/Solusi_" + fileName));

            bw.write("Matriks hasil eliminasi:\n");
            for (int i = 0; i < matrix.getRows(); i++) {
                for (int j = 0; j < matrix.getCols(); j++) {
                    if (j == matrix.getLastIdxCol()){
                        bw.write(matrix.getElmt(i, j) + "");
                    } else{
                        bw.write(matrix.getElmt(i, j) + " ");
                    }
                }
                bw.newLine();
            }

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
        
            bw.write("\nSolusi dari SPL:\n");
            for (int i = 0; i < row; i++){
              bw.write(String.format("x%d = %.6f\n", i+1, solution.getElmt(i, 0)));
            }
            bw.flush();
            bw.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
  }

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    System.out.println("Apakah ingin memasukkan input dari file?\n1. Yes\n2. No");

    int input = scanner.nextInt();
    
    if (input == 1){
      System.out.println("Masukkan nama file: ");
      fileName = scanner.next();
      MatrixADT matrix = inputFile(fileName);
      System.out.println();

      if (matrix.getRows() != matrix.getCols() -1){
        performInconsistent(matrix);
      } else {
        performElimination(matrix);
      }

    } else if (input == 2)  {
      System.out.println("Masukkan nilai m: ");
      int m = scanner.nextInt();
      System.out.println("Masukkan nilai n: ");
      int n = scanner.nextInt();

      System.out.print("Masukkan matriks: \n");
      MatrixADT matrix = new MatrixADT(m, n);
      matrix.readMatrix(m,n);

      if (m != n -1){
        performInconsistent(matrix);
      } else {
        performElimination(matrix);
      }
    }

    scanner.close();
    fileName = "";
  }
}