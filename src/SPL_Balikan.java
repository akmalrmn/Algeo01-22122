import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import Matrix.MatrixADT;

public class SPL_Balikan {
    private static String fileName = "";

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

    static void swap_row(MatrixADT matrix, int i, int j) {
        for (int k = 0; k < matrix.getCols(); k++) {
          double temp = matrix.getElmt(i, k);
          matrix.setElmt(i, k, matrix.getElmt(j, k));
          matrix.setElmt(j, k, temp);
        }
      }
    
    static void backSubstitute(MatrixADT matrix, MatrixADT matrixIdentity) {
        int numRows = matrix.getRows();

        for (int i = numRows-1; i >= 0; i--) {
            double factor = matrix.getElmt(i, i);

            matrix.setElmt(i, i, matrix.getElmt(i, i) / factor);

            for (int k = 0; k < numRows; k++){
                matrixIdentity.setElmt(i, k, matrixIdentity.getElmt(i, k) / factor);
            }


            for(int j = i - 1; j >= 0; j--){
                double factor2 = matrix.getElmt(j, i);

                for(int k = 0; k < numRows; k++){
                    double temp2 = matrixIdentity.getElmt(j, k) - matrixIdentity.getElmt(i, k) * factor2;
                    matrixIdentity.setElmt(j, k, temp2);
                }

                matrix.setElmt(j, i, 0);
            }
        }
    }

    static double performOBE(MatrixADT matrix, MatrixADT matrixIdentity) {
        double determinan = 1;

        for (int k = 0; k <= matrix.getLastIdxRow(); k++) {
          int idxMax = k;
          double valueMax = matrix.getElmt(k, k);

          for (int i = k + 1; i <= matrix.getLastIdxRow(); i++){
            if (Math.abs(matrix.getElmt(i, k)) > valueMax) {
                valueMax = matrix.getElmt(i, k);
                idxMax = i;
            }
          }
    
          if (matrix.getElmt(k, idxMax) == 0){
            return 0;
          }
    
          if (idxMax != k){
            swap_row(matrix, k, idxMax);
            swap_row(matrixIdentity, k, idxMax);
            determinan *= -1;
          }
    
          for (int i = k + 1; i <= matrix.getLastIdxRow(); i++) {
            double divider = matrix.getElmt(i, k) / matrix.getElmt(k, k);

            for(int l = 0; l <= matrixIdentity.getLastIdxCol(); l++){
                double temp2 = matrixIdentity.getElmt(i, l) - matrixIdentity.getElmt(k, l) * divider;
                matrixIdentity.setElmt(i, l, temp2);
            }

            for (int j = k + 1; j <= matrix.getLastIdxCol(); j++){
              double temp = matrix.getElmt(i, j) - matrix.getElmt(k,j) * divider;

              if (temp < 1e-15){
                temp = 0;
              }

              matrix.setElmt(i, j, temp);
            }
    
            matrix.setElmt(i, k, 0);
          }
        }

        for(int i = 0; i <= matrix.getLastIdxRow(); i++){
            determinan *= matrix.getElmtDiagonal(i);
        }

        return determinan;
      }
    
    public static void findSolution(MatrixADT matrix){
        MatrixADT matrixIdentity = new MatrixADT(matrix.getRows(), matrix.getCols() - 1);
        matrixIdentity.createIdentity();

        MatrixADT komponenB = new MatrixADT(matrix.getRows(), 1);
        MatrixADT komponenA = new MatrixADT(matrix.getRows(), matrix.getCols() - 1);

        for (int i = 0; i < komponenA.getRows(); i++){
            for(int j = 0; j < komponenA.getCols(); j++){
                komponenA.setElmt(i, j, matrix.getElmt(i, j));
            }
        }
        for (int i = 0; i < komponenB.getRows(); i++){
            komponenB.setElmt(i, 0, matrix.getElmt(i, matrix.getLastIdxCol()));
        }

        if(performOBE(komponenA, matrixIdentity) == 0){
            System.out.println("SPL tidak bisa diselesaikan dengan metode Inverse.");
        } else {
            backSubstitute(komponenA, matrixIdentity);

            MatrixADT answer = matrixIdentity.multiplyMatrix1(komponenB);
            
            for (int i = 0; i < komponenB.getRows(); i++){
                System.out.printf("Nilai x%d = %.2f\n", i + 1, answer.getElmt(i, 0));
            }

            Scanner scanner = new Scanner(System.in);
            System.out.println("\nApakah ingin menyimpan solusi dalam file?\n1. Yes\n2. No");
            int input = scanner.nextInt();

            if (input == 1){
                outputFile(komponenB, answer);
            }

            scanner.close();
        }
    }

    static void outputFile(MatrixADT komponenB, MatrixADT answer){
        if (fileName.length() == 0){
            fileName = "Solusi_SPL_Inverse_InputKeyboard.txt";
        }

        try {
            File myObj = new File("./test/output/Solusi_SPL_Inverse_" + fileName);
            if (myObj.createNewFile()) {
              System.out.println("File created: " + myObj.getName());
            } else {
              System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("./test/output/Solusi_SPL_Inverse_" + fileName));

            bw.write("Solusi dari SPL tersebut:\n");
            for (int i = 0; i < komponenB.getRows(); i++){
                bw.write(String.format("Nilai x%d = %.2f\n", i + 1, answer.getElmt(i, 0)));
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
                System.out.println("SPL tidak bisa dicari dengan metode Inverse.");
            } else {
                findSolution(matrix);
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
                System.out.println("SPL tidak bisa dicari dengan metode Inverse.");
            } else {
                findSolution(matrix);
            }
        }

        scanner.close();
        fileName = "";

        // Scanner scanner = new Scanner(System.in);
        // int m, n;
        // m = scanner.nextInt();
        // n = scanner.nextInt();

        // Matrix matrix = new Matrix(m, n);
        // matrix.readMatrix(scanner);

        // if (m != n - 1){
        //     System.out.println("SPL tidak bisa dicari dengan metode Inverse.");
        // } else {
        //     findSolution(matrix);
        // }

        // scanner.close();
    }
}