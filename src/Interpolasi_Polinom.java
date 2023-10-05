import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import Matrix.MatrixADT;

public class Interpolasi_Polinom {
    static Scanner scanner = new Scanner(System.in);

    private static String fileName = "";

    static void backSubstitute(MatrixADT matrix) {
        int numRows = matrix.getRows();
        int lastCols = matrix.getCols() - 1;
    
        for (int i = numRows-1; i >= 0; i--) {
            double factor = matrix.getElmt(i, i);
    
            matrix.setElmt(i, i, 1);
            matrix.setElmt(i, lastCols, matrix.getElmt(i, lastCols) / factor);
    
            double temp = matrix.getElmt(i, lastCols);
    
            for(int j = i - 1; j >= 0; j--){
                double factor2 = matrix.getElmt(j, i);
    
                matrix.setElmt(j, i, 0);
                matrix.setElmt(j, lastCols, matrix.getElmt(j, lastCols) - factor2 * temp);
            }
        }
    }

    static void swap_row(MatrixADT matrix, int i, int j) {
        for (int k = 0; k < matrix.getCols(); k++) {
          double temp = matrix.getElmt(i, k);
          matrix.setElmt(i, k, matrix.getElmt(j, k));
          matrix.setElmt(j, k, temp);
        }
      }

    static void performElimination(MatrixADT matrix){
        for (int k = 0; k <= matrix.getLastIdxRow(); k++) {
            int idxMax = k;
            double valueMax = matrix.getElmt(k, k);
  
            for (int i = k + 1; i <= matrix.getLastIdxRow(); i++){
              if (Math.abs(matrix.getElmt(i, k)) > Math.abs(valueMax)) {
                valueMax = matrix.getElmt(i, k);
                idxMax = i;
              }
            }
    
            if (idxMax != k){
              swap_row(matrix, k, idxMax);
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
    }

    static void createAugmented(MatrixADT matrix, MatrixADT matrixPoint){
        int n = matrix.getRows() - 1;

        for(int i = 0; i <= matrix.getLastIdxRow(); i++){
            for(int j = 0; j <= matrix.getLastIdxCol(); j++){
                if (j == matrix.getLastIdxCol()){
                    matrix.setElmt(i, j, matrixPoint.getElmt(i, 1));
                } else if ( j == matrix.getLastIdxCol() - 1) {
                    matrix.setElmt(i, j, 1);
                } else {
                    double temp = Math.pow(matrixPoint.getElmt(i, 0), n - j);
                    matrix.setElmt(i, j, temp);
                }
            }
        }
    }

    static void findAnswer(MatrixADT matrix, double x){
        int pangkat = matrix.getRows() - 1;
        int idxLastCol = matrix.getLastIdxCol();
        System.out.print("f(x) = ");
        for( int i = 0; i <= matrix.getLastIdxRow(); i++){
            double element = matrix.getElmt(i, idxLastCol);
            if (i == matrix.getLastIdxRow()){
                if (element > 0){
                     System.out.printf("+ %.4f ", Math.abs(element), pangkat--);
                } else if (element < 0) {
                    System.out.printf("- %.4f ", Math.abs(element), pangkat--);
                } else {
                    pangkat--;
                }
            } else if ( i == matrix.getLastIdxRow() - 1){
                if (element > 0){
                    if (i == 0){
                        System.out.printf("%.4fx ", element);
                    } else {
                        System.out.printf("+ %.4fx ", element);
                    }
                    pangkat--;
                } else if (element < 0) {
                    System.out.printf("- %.4fx^%d ", Math.abs(element), pangkat--);
                } else{
                    pangkat--;
                }
            }
            else {
                if (element > 0){
                    if (i == 0){
                        System.out.printf("%.4fx^%d ", element, pangkat--);
                    } else {
                        System.out.printf("+ %.4fx^%d ", element, pangkat--);
                    }
                } else if (element < 0) {
                    System.out.printf("- %.4fx^%d ", Math.abs(element), pangkat--);
                } else {
                    pangkat--;
                }
            }
        }

        double temp = 0;
        int lastIdx = matrix.getLastIdxCol();
        int n = matrix.getRows() - 1;

        for (int i = 0; i <= matrix.getLastIdxRow(); i++){
            temp += matrix.getElmt(i, lastIdx) * Math.pow(x, n - i);
        }

        System.out.printf(", f(%f) = %f.", x, temp);

        System.out.println("\nApakah ingin menyimpan solusi dalam file?\n1. Yes\n2. No");
        int input = scanner.nextInt();

        if (input == 1){
            outputFile(matrix, temp, x);
        }
    }
    
    static MatrixADT inputFile(String namaFile){
        int numRows = 0;
        int numCols = 2;

        try {
        File myObj = new File("./test/" + namaFile);
        Scanner sizeFinder = new Scanner(myObj);
        Scanner myReader = new Scanner(myObj);

        while(sizeFinder.hasNextLine()){
            sizeFinder.nextLine();
            numRows++;
        }

        numRows--;

        sizeFinder.close();

        MatrixADT matrix = new MatrixADT(numRows, numCols);
        
        int i = 0;
        while (myReader.hasNextLine() && i < numRows) {
            String[] line = myReader.nextLine().trim().split(" ");

            for (int j = 0; j < numCols; j++) {
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
    
    static Double inputNumber(String fileName, int numRows){
        try {
            File myObj = new File("./test/" + fileName);
            Scanner myReader = new Scanner(myObj);

            int i = numRows;
            while ( i > 0 ){
                myReader.nextLine();
                i--;
            }

            String[] line = myReader.nextLine().trim().split(" ");
            
            double number = Double.parseDouble(line[0]);

            myReader.close();

            return number;

        } catch (FileNotFoundException e) {

            System.out.println("An error occurred.");
            e.printStackTrace();

            return 0.0;
        }
    }

    static void outputFile(MatrixADT matrix, double answer, double inputNum){
        if (fileName.length() == 0){
            fileName = "Solusi_Interpolasi_.txt";
        }

        try {
            File myObj = new File("./test/output/Solusi_Interpolasi_" + fileName);
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
            BufferedWriter bw = new BufferedWriter(new FileWriter("./test/output/Solusi_Interpolasi_" + fileName));

            int pangkat = matrix.getRows() - 1;
            int idxLastCol = matrix.getLastIdxCol();
            bw.write("f(x) = ");
            for( int i = 0; i <= matrix.getLastIdxRow(); i++){
                double element = matrix.getElmt(i, idxLastCol);
                if (i == matrix.getLastIdxRow()){
                    if (element > 0){
                        bw.write(String.format("+ %.4f ", Math.abs(element), pangkat--));
                    } else if (element < 0) {
                        bw.write(String.format("- %.4f ", Math.abs(element), pangkat--));
                    } else {
                        pangkat--;
                    }
                } else if ( i == matrix.getLastIdxRow() - 1){
                    if (element > 0){
                        if (i == 0){
                            bw.write(String.format("%.4fx ", element));
                        } else {
                            bw.write(String.format("+ %.4fx ", element));
                        }
                        pangkat--;
                    } else if (element < 0) {
                        bw.write(String.format("- %.4fx^%d ", Math.abs(element), pangkat--));
                    } else{
                        pangkat--;
                    }
                }
                else {
                    if (element > 0){
                        if (i == 0){
                            bw.write(String.format("%.4fx^%d ", element, pangkat--));
                        } else {
                            bw.write(String.format("+ %.4fx^%d ", element, pangkat--));
                        }
                    } else if (element < 0) {
                        bw.write(String.format("- %.4fx^%d ", Math.abs(element), pangkat--));
                    } else {
                        pangkat--;
                    }
                }
            }

            bw.write(String.format("\nf(%f) = %f", inputNum , answer));

            bw.flush();
            bw.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
    public static void main(String[] args){

        System.out.println("Apakah ingin memasukkan input dari file?\n1. Yes\n2. No");

        int input = scanner.nextInt();
        
        if (input == 1){
            System.out.println("Masukkan nama file: ");
            fileName = scanner.next();
            MatrixADT matrixPoint = inputFile(fileName);
            System.out.println();

            MatrixADT matrix = new MatrixADT(matrixPoint.getRows(), matrixPoint.getRows() + 1);
            Double inputNum = inputNumber(fileName, matrixPoint.getRows());

            createAugmented(matrix, matrixPoint);

            performElimination(matrix);
            backSubstitute(matrix);

            findAnswer(matrix, inputNum);
        } else if (input == 2)  {
            MatrixADT matrixPoint;
            System.out.println("Masukkan nilai N: ");
            int n = scanner.nextInt();

            matrixPoint = new MatrixADT(n  + 1, 2);
            matrixPoint.readMatrix(n  + 1, 2);

            MatrixADT matrix = new MatrixADT(n + 1, n + 2);

            createAugmented(matrix, matrixPoint);

            performElimination(matrix);
            backSubstitute(matrix);

            System.out.println("Masukkan nilai x: ");
            double x = scanner.nextDouble();

            findAnswer(matrix, x);
        }
        fileName = "";
    }
}