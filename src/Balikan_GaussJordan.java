import java.util.Scanner;
import java.text.DecimalFormat;

public class Balikan_GaussJordan {
  public static void BalikanGaussJordan(Matrix matrix) {
    double[][] inverse = findInverse(matrix);

    System.out.println("Inverse matriks : ");
    printMatrix(inverse);
  }

  // Fungsi Inverse
  public static double[][] findInverse(Matrix matrix) {
    int n = matrix.getRows();
    double[][] augmentedMatrix = new double[n][2*n];

    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
        augmentedMatrix[i][j] = matrix.data[i][j];
      }
    }

    for (int i = 0; i < n; i++) {
      augmentedMatrix[i][i + n] = 1.0;
    }

    for (int i = 0; i < n; i++) {
      double pivot = augmentedMatrix[i][i];
      for (int j = 0; j < 2*n; j++) {
        augmentedMatrix[i][j] /= pivot;
      }

      for (int k = 0; k < n; k++) {
        if (k != i) {
          double factor = augmentedMatrix[k][i];
          for (int j = 0; j < 2*n; j++) {
            augmentedMatrix[k][j] -= factor * augmentedMatrix[i][j];
          }
        }
      }
    }

    double[][] inverse = new double[n][n];
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
        inverse[i][j] = augmentedMatrix[i][j+n];
      }
    }

    return inverse;
  }

  // Fungsi mengeprint matriks
  public static void printMatrix(double[][] matrix) {
    int n = matrix.length;
    DecimalFormat df = new DecimalFormat("0.00");

    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
        System.out.print(df.format(matrix[i][j]) + "\t");
      }
      System.out.println();
    }
  }
}
