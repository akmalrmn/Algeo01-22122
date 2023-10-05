import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Determinan_ReduksiBaris {
      private static String fileName = "";

      static double performOBE(Matrix matrix) {
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

        return determinan;
      }

      static void swap_row(Matrix matrix, int i, int j) {
        for (int k = 0; k < matrix.getCols(); k++) {
          double temp = matrix.getElmt(i, k);
          matrix.setElmt(i, k, matrix.getElmt(j, k));
          matrix.setElmt(j, k, temp);
        }
      }

      static Matrix inputFile(String namaFile){

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

          Matrix matrix = new Matrix(numRows, numCols);
          
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
          Matrix matrix = new Matrix(0, 0);

          System.out.println("An error occurred.");
          e.printStackTrace();

          return matrix;
        }
        
      }
      
      static void outputFile(Double answer){
            if (fileName.length() == 0){
                fileName = "Solusi_Determinan_Reduksi_InputKeyboard.txt";
            }

            try {
                File myObj = new File("./test/output/Solusi_Determinan_Reduksi_" + fileName);
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
                BufferedWriter bw = new BufferedWriter(new FileWriter("./test/output/Solusi_Determinan_Reduksi_" + fileName));

                bw.write("Determinan dari matriks tersebut = " + answer + "\n");

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
            Matrix matrix = inputFile(fileName);
            System.out.println();

            if (matrix.getRows() != matrix.getCols()){
                System.out.println("Matriks bukan merupakan matriks persegi.");
            } else {
                Double determinant = performOBE(matrix);
                System.out.printf("Determinan dari matriks tersebut = %.6f\n", determinant);

                System.out.println("\nApakah ingin menyimpan solusi dalam file?\n1. Yes\n2. No");
                input = scanner.nextInt();

                if (input == 1){
                    outputFile(determinant);
                }
            }

        } else if (input == 2)  {
            System.out.println("Masukkan nilai m: ");
            int m = scanner.nextInt();
            System.out.println("Masukkan nilai n: ");
            int n = scanner.nextInt();

            System.out.print("Masukkan matriks: \n");
            Matrix matrix = new Matrix(m, n);
            matrix.readMatrix(scanner);

            if (m != n ){
                System.out.println("Matriks bukan merupakan matriks persegi.");
            } else {
                Double determinant = performOBE(matrix);
                System.out.printf("Determinan dari matriks tersebut = %.6f\n", determinant);

                System.out.println("\nApakah ingin menyimpan solusi dalam file?\n1. Yes\n2. No");
                input = scanner.nextInt();

                if (input == 1){
                    outputFile(determinant);
                }
            }
        }

        scanner.close();
        fileName = "";
      }
    
}
