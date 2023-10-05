import java.util.Scanner;
import IO.Parser;
import Matrix.MatrixADT;

public class SPL_Balikan {
  static Scanner scanner = new Scanner(System.in);
  static String fileName = "Solusi_SPL_Inverse_Problem";
  static int count = 0;

  static void swap_row(MatrixADT matrix, int i, int j) {
    for (int k = 0; k < matrix.getCols(); k++) {
      double temp = matrix.getElmt(i, k);
      matrix.setElmt(i, k, matrix.getElmt(j, k));
      matrix.setElmt(j, k, temp);
    }
  }

  static void backSubstitute(MatrixADT matrix, MatrixADT matrixIdentity) {
    int numRows = matrix.getRows();

    for (int i = numRows - 1; i >= 0; i--) {
      double factor = matrix.getElmt(i, i);

      matrix.setElmt(i, i, matrix.getElmt(i, i) / factor);

      for (int k = 0; k < numRows; k++) {
        matrixIdentity.setElmt(i, k, matrixIdentity.getElmt(i, k) / factor);
      }

      for (int j = i - 1; j >= 0; j--) {
        double factor2 = matrix.getElmt(j, i);

        for (int k = 0; k < numRows; k++) {
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

      for (int i = k + 1; i <= matrix.getLastIdxRow(); i++) {
        if (Math.abs(matrix.getElmt(i, k)) > valueMax) {
          valueMax = matrix.getElmt(i, k);
          idxMax = i;
        }
      }

      if (matrix.getElmt(idxMax, k) == 0) {
        boolean all_zero = true;
        int idxAllZero = -1;

        for (int i = 0; i < matrix.getRows(); i++) {
          all_zero = true;

          for (int j = 0; j < matrix.getCols() - 1; j++) {
            if (matrix.getElmt(i, j) != 0) {
              all_zero = false;
              continue;
            }
          }

          if (matrix.getElmt(i, matrix.getLastIdxCol()) == 0 && all_zero) {
            idxAllZero = i;
          } else if (matrix.getElmt(i, matrix.getLastIdxCol()) != 0 && all_zero) {

            return i;
          }

        }

        return idxAllZero;
      }

      if (idxMax != k) {
        swap_row(matrix, k, idxMax);
        swap_row(matrixIdentity, k, idxMax);
        determinan *= -1;
      }

      for (int i = k + 1; i <= matrix.getLastIdxRow(); i++) {
        double divider = matrix.getElmt(i, k) / matrix.getElmt(k, k);

        for (int l = 0; l <= matrixIdentity.getLastIdxCol(); l++) {
          double temp2 = matrixIdentity.getElmt(i, l) - matrixIdentity.getElmt(k, l) * divider;
          matrixIdentity.setElmt(i, l, temp2);
        }

        for (int j = k + 1; j <= matrix.getLastIdxCol(); j++) {
          double temp = matrix.getElmt(i, j) - matrix.getElmt(k, j) * divider;

          if (Math.abs(temp) < 1e-15) {
            temp = 0;
          }

          matrix.setElmt(i, j, temp);
        }

        matrix.setElmt(i, k, 0);
      }
    }

    for (int i = 0; i <= matrix.getLastIdxRow(); i++) {
      determinan *= matrix.getElmtDiagonal(i);
    }

    return determinan;
  }

  public static void findSolution(MatrixADT matrix) {
    if (matrix.getRows() != matrix.getCols() - 1) {
      System.out.println("SPL tidak bisa dicari dengan metode Inverse.");
      return;
    }

    MatrixADT matrixIdentity = new MatrixADT(matrix.getRows(), matrix.getCols() - 1);
    matrixIdentity.createIdentity();

    MatrixADT komponenB = new MatrixADT(matrix.getRows(), 1);
    MatrixADT komponenA = new MatrixADT(matrix.getRows(), matrix.getCols() - 1);

    for (int i = 0; i < komponenA.getRows(); i++) {
      for (int j = 0; j < komponenA.getCols(); j++) {
        komponenA.setElmt(i, j, matrix.getElmt(i, j));
      }
    }
    for (int i = 0; i < komponenB.getRows(); i++) {
      komponenB.setElmt(i, 0, matrix.getElmt(i, matrix.getLastIdxCol()));
    }

    if (performOBE(komponenA, matrixIdentity) == 0) {
      System.out.println("SPL tidak bisa diselesaikan dengan metode Inverse.");
    } else {
      backSubstitute(komponenA, matrixIdentity);

      MatrixADT answer = matrixIdentity.multiplyMatrix1(komponenB);

      for (int i = 0; i < komponenB.getRows(); i++) {
        System.out.printf("Nilai x%d = %.2f\n", i + 1, answer.getElmt(i, 0));
      }

      System.out.println("\nApakah ingin menyimpan solusi dalam file?\n1. Yes\n2. No");
      int input = scanner.nextInt();

      while (input != 1 && input != 2) {
        System.out.print("Input tidak valid\n");
        input = scanner.nextInt();
      }

      if (input == 1) {
        Parser.outputFileMatrix(komponenB, answer);
      }

    }
  }
}