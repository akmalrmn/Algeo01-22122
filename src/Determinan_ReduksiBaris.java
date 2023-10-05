import java.util.Scanner;

import Matrix.MatrixADT;

public class Determinan_ReduksiBaris {
  Scanner scanner = new Scanner(System.in);

  static void performOBE(MatrixADT matrix) {
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
        System.out.println(0);
        ;
      }

      else {
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
    }
    System.out.println(determinan);
  }

  static void swap_row(MatrixADT matrix, int i, int j) {
    for (int k = 0; k < matrix.getCols(); k++) {
      double temp = matrix.getElmt(i, k);
      matrix.setElmt(i, k, matrix.getElmt(j, k));
      matrix.setElmt(j, k, temp);
    }
  }

}