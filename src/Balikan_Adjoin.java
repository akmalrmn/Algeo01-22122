import Matrix.Matrix;

public class Balikan_Adjoin {
  public static void adjoin(Matrix matrix) {

    int n = matrix.getCols();
    Matrix submatrix = new Matrix(n - 1, n - 1);
    Matrix matrixkofaktor = new Matrix(n, n);
    Matrix matrixhasil = new Matrix(n, n);
    double det = Determinan_EK.determinant(matrix);

    if (det != 0) {
      // kofaktor
      for (int barisutama = 0; barisutama < n; barisutama++) {
        for (int kolomutama = 0; kolomutama < n; kolomutama++) {
          for (int baris = 0; baris < n; baris++) {
            for (int kolom = 0; kolom < n; kolom++) {
              // mengisi submatrix dengan elemen matrix yang tidak berada di baris dan kolom utama
              if ((baris > barisutama) && (kolom > kolomutama)) {
                submatrix.data[baris - 1][kolom - 1] = matrix.data[baris][kolom];
              } else if ((baris > barisutama) && (kolom < kolomutama)) {
                submatrix.data[baris - 1][kolom] = matrix.data[baris][kolom];
              } else if ((baris < barisutama) && (kolom > kolomutama)) {
                submatrix.data[baris][kolom - 1] = matrix.data[baris][kolom];
              } else if ((baris < barisutama) && (kolom < kolomutama)) {
                submatrix.data[baris][kolom] = matrix.data[baris][kolom];
              }
            }
          }
          // setiap elemen dibagi dengan det (determinan)
          int a = barisutama + kolomutama;
          matrixkofaktor.data[barisutama][kolomutama] = (double) (Math.pow(-1, a)
              * Determinan_EK.determinant(submatrix) * 1 / det);
        }
      }

      // adjoin
      int indeks = 1;
      while (indeks < n) {
        matrixhasil.data[indeks - 1][indeks - 1] = matrixkofaktor.data[indeks - 1][indeks - 1];
        matrixhasil.data[0][indeks] = matrixkofaktor.data[indeks][0];
        matrixhasil.data[indeks][0] = matrixkofaktor.data[0][indeks];
        matrixhasil.data[n - 1][indeks] = matrixkofaktor.data[indeks][n - 1];
        matrixhasil.data[indeks][n - 1] = matrixkofaktor.data[n - 1][indeks];
        indeks += 1;
      }
    }
    // print hasil
    System.out.println();
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
        System.out.print(matrixhasil.data[i][j] + " ");
      }
      System.out.println();
    }
  }
}
