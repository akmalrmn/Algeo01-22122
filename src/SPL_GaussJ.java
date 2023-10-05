import Matrix.*;

public class SPL_GaussJ {
  public static void SPLGaussJordan(MatrixADT matrix) {
    int rows = matrix.getRows();
    int cols = matrix.getCols();
    MatrixADT reducedMatrix = OBE.gaussJordan(matrix);

    // Memeriksa apakah matriks memiliki solusi unik
    boolean hasUniqueSolution = checkUniqueSolution(reducedMatrix);

    if (hasUniqueSolution) {
      // Menampilkan hasil dari variabel
      System.out.println("\nHasil SPL:");
      for (int i = 0; i < rows; i++) {
        System.out.println(
            "Nilai variabel x-" + (i + 1) + ": " + reducedMatrix.getElmt(i, cols - 1) / reducedMatrix.getElmt(i, i));
      }
    } else {
      System.out.println("Sistem persamaan tidak memiliki solusi unik.");
    }
  }

  // Fungsi mengecek apakah matriks memiliki solusi unik
  public static boolean checkUniqueSolution(MatrixADT matrix) {
    int rows = matrix.getRows();

    for (int i = 0; i < rows; i++) {
      if (matrix.data[i][i] == 0) {
        return false; // Matriks memiliki elemen diagonal nol, tidak ada solusi unik.
      }
    }

    return true; // Semua elemen diagonal non-nol, ada solusi unik.
  }
}