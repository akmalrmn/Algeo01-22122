import Matrix.*;
import java.text.DecimalFormat;

public class Balikan_GaussJordan {
  public static void BalikanGaussJordan(Matrix matrix) {
    Matrix inverse = MatrixOperation.inversGaussJ(matrix);

    System.out.println("Inverse matriks: ");
    printMatrix(inverse);
  }

  // Fungsi mengeprint matriks
  public static void printMatrix(Matrix matrix) {
    int n = matrix.getRows();
    DecimalFormat df = new DecimalFormat("0.000");

    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
        System.out.print(df.format(matrix.data[i][j]) + "\t");
      }
      System.out.println();
    }
  }
}
