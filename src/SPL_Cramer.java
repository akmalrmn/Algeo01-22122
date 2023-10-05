import Matrix.MatrixADT;

public class SPL_Cramer {
  public static void cramer(MatrixADT matrix) {
    int jmlhbaris = matrix.getRows();
    int jmlhkolom = matrix.getCols();
    double[] hasil = new double[jmlhkolom - 1];
    MatrixADT submatrix = new MatrixADT(jmlhbaris, jmlhkolom - 1);

    // menghitung determinan matriks utama
    for (int i = 0; i < jmlhbaris; i++) {
      for (int j = 0; j < jmlhkolom - 1; j++) {
        submatrix.data[i][j] = matrix.data[i][j];
      }
    }
    double det = Determinan_EK.determinant(submatrix);

    // menghitung determinan dari submatrix
    if (det != 0) {
      for (int j = 0; j < jmlhkolom - 1; j++) {
        // memasukkan semua elemen matrix (kecuali kolom terakhir) ke submatrix
        for (int k = 0; k < jmlhbaris; k++) {
          for (int l = 0; l < jmlhkolom - 1; l++) {
            submatrix.data[k][l] = matrix.data[k][l];
          }
        }
        // memasukkan 1 kolom matrix ke submatrix
        for (int i = 0; i < jmlhbaris; i++) {
          submatrix.data[i][j] = matrix.data[i][jmlhkolom - 1];
        }
        hasil[j] = Determinan_EK.determinant(submatrix) / det;
      }
      // print hasil
      System.out.println();
      for (int i = 1; i < jmlhkolom; i++) {
        System.out.println("X" + i + ": " + hasil[i - 1]);
      }
    } else {
      System.out.println("\nDeterminan dari matriks adalah 0, tidak bisa menggunakan Cramer untuk menemukan solusi.");
    }
  }
}
