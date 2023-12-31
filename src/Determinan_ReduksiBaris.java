import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import Matrix.MatrixADT;

public class Determinan_ReduksiBaris {
  static Scanner scanner = new Scanner(System.in);
  static int count = 1;
  static String fileName = "Solusi_DeterminanReduksi_Problem";

  static double performOBE(MatrixADT matrix) {
    if (matrix.getRows() != matrix.getCols()) {
      System.out.println("Matriks bukan merupakan matriks persegi.");
      return 0;
    } else {
      double determinan = 1;

      for (int k = 0; k <= matrix.getLastIdxRow(); k++) {
        int idxMax = k;
        double valueMax = matrix.getElmt(k, k);

        for (int i = k + 1; i <= matrix.getLastIdxRow(); i++) {
          if (Math.abs(matrix.getElmt(i, k)) > valueMax) {
            valueMax = matrix.getElmt(i, k);
            idxMax = i;
          }
        }

        if (matrix.getElmt(k, idxMax) == 0) {
          return 0;
        }

        if (idxMax != k) {
          swap_row(matrix, k, idxMax);
          determinan *= -1;
        }

        for (int i = k + 1; i <= matrix.getLastIdxRow(); i++) {
          double divider = matrix.getElmt(i, k) / matrix.getElmt(k, k);

          for (int j = k + 1; j <= matrix.getLastIdxCol(); j++) {
            double temp = matrix.getElmt(i, j) - matrix.getElmt(k, j) * divider;
            matrix.setElmt(i, j, temp);
          }

          matrix.setElmt(i, k, 0);
        }
      }

      for (int i = 0; i <= matrix.getLastIdxRow(); i++) {
        determinan *= matrix.getElmtDiagonal(i);
      }

      System.out.printf("Determinan dari matriks tersebut = %.6f\n", determinan);

      System.out.println("\nApakah ingin menyimpan solusi dalam file?\n1. Yes\n2. No");
      int input = scanner.nextInt();

      while (input != 1 && input != 2) {
        System.out.print("Input tidak valid\n");
        input = scanner.nextInt();
      }

      if (input == 1) {
        scanner.nextLine();
        System.out.print("Masukkan nama file (tanpa txt): ");
        String filename = scanner.nextLine();
        outputFile(determinan, filename);
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

  static void outputFile(Double answer, String fileName) {
    try {
      File myObj = new File("./test/output/" + fileName + ".txt");
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
      FileWriter fw = new FileWriter("./test/output/" + fileName + ".txt");
      fw.write("Determinan dari matriks tersebut = " + answer + "\n");
      fw.close();
      System.out.println("Content written to file.");
    } catch (IOException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
  }

}