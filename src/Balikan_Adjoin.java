import Matrix.MatrixADT;

public class Balikan_Adjoin {
  public static MatrixADT adjoin(MatrixADT matrix) {

    int n = matrix.getCols();
    MatrixADT submatrix = new MatrixADT(n - 1, n - 1);
    MatrixADT matrixkofaktor = new MatrixADT(n, n);
    MatrixADT matrixhasil = new MatrixADT(n, n);
    double det = Determinan_EK.determinant(matrix);

    if (det != 0) {
      // kofaktor
      for (int barisutama = 0; barisutama < n; barisutama++) {
        for (int kolomutama = 0; kolomutama < n; kolomutama++) {
          for (int baris = 0; baris < n; baris++) {
            for (int kolom = 0; kolom < n; kolom++) {
              // mengisi submatrix dengan elemen matrix yang tidak berada di baris dan kolom
              // utama
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
      for (int i = 0; i < matrixkofaktor.getCols(); i++) {
        for (int j = 0; j < matrixkofaktor.getRows(); j++) {
          matrixhasil.data[j][i] = matrixkofaktor.data[i][j];
        }
      }
    }
    // print hasil
    System.out.println();
    matrixhasil.displayMatrix();
    return matrixhasil;
  }
}
