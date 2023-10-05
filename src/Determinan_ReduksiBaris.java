import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import Matrix.MatrixADT;

public class Determinan_ReduksiBaris {
      static Scanner scanner = new Scanner(System.in);
      static int count = 1;
      static String fileName = "Solusi_DeterminanReduksi_Problem";

      static double performOBE(MatrixADT matrix) {
        if (matrix.getRows() != matrix.getCols()){
            System.out.println("Matriks bukan merupakan matriks persegi.");
            return 0;
        } else {
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
            determinan *= -1;
          }
    
          for (int i = k + 1; i <= matrix.getLastIdxRow(); i++) {
            double divider = matrix.getElmt(i, k) / matrix.getElmt(k, k);

            for (int j = k + 1; j <= matrix.getLastIdxCol(); j++){
              double temp = matrix.getElmt(i, j) - matrix.getElmt(k,j) * divider;
              matrix.setElmt(i, j, temp);
            }
    
            matrix.setElmt(i, k, 0);
          }
        }

        for(int i = 0; i <= matrix.getLastIdxRow(); i++){
            determinan *= matrix.getElmtDiagonal(i);
        }

        System.out.printf("Determinan dari matriks tersebut = %.6f\n", determinan);

        System.out.println("\nApakah ingin menyimpan solusi dalam file?\n1. Yes\n2. No");
        int input = scanner.nextInt();

        if (input == 1){
            outputFile(determinan);
        }

        return determinan;
        }
      }

      static void swap_row(MatrixADT matrix, int i, int j) {
        for (int k = 0; k < matrix.getCols(); k++) {
          double temp = matrix.getElmt(i, k);
          matrix.setElmt(i, k, matrix.getElmt(j, k));
          matrix.setElmt(j, k, temp);
        }
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
      
      static void outputFile(Double answer){
            try {
                File myObj = new File("./test/output/" + fileName + count + ".txt");
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
                BufferedWriter bw = new BufferedWriter(new FileWriter("./test/" + fileName + count));

                bw.write("Determinan dari matriks tersebut = " + answer + "\n");

                bw.flush();
                bw.close();
            } catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
        }
    
}