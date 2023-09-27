public class Balikan_Adjoin {
  public static void adjoin(float[][] matrix) {

    int n = matrix.length;
    float[][] submatrix = new float[n - 1][n - 1];
    float[][] matrixkofaktor = new float[n][n];
    float[][] matrixhasil = new float[n][n];
    float det = Determinan_EK.determinant(matrix);

    if (det != 0) {
      // kofaktor
      for (int barisutama = 0; barisutama < n; barisutama++) {
        for (int kolomutama = 0; kolomutama < n; kolomutama++) {
          for (int baris = 0; baris < n; baris++) {
            for (int kolom = 0; kolom < n; kolom++) {
              // mengisi submatrix dengan elemen matrix yang tidak berada di baris dan kolom utama
              if ((baris > barisutama) && (kolom > kolomutama)) {
                submatrix[baris - 1][kolom - 1] = matrix[baris][kolom];
              } else if ((baris > barisutama) && (kolom < kolomutama)) {
                submatrix[baris - 1][kolom] = matrix[baris][kolom];
              } else if ((baris < barisutama) && (kolom > kolomutama)) {
                submatrix[baris][kolom - 1] = matrix[baris][kolom];
              } else if ((baris < barisutama) && (kolom < kolomutama)) {
                submatrix[baris][kolom] = matrix[baris][kolom];
              }
            }
          }
          // setiap elemen dibagi dengan det (determinan)
          int a = barisutama + kolomutama;
          matrixkofaktor[barisutama][kolomutama] = (float) (Math.pow(-1, a)
              * Determinan_EK.determinant(submatrix) * 1 / det);
        }
      }

      // adjoin
      int indeks = 1;
      while (indeks < n) {
        matrixhasil[indeks - 1][indeks - 1] = matrixkofaktor[indeks - 1][indeks - 1];
        matrixhasil[0][indeks] = matrixkofaktor[indeks][0];
        matrixhasil[indeks][0] = matrixkofaktor[0][indeks];
        matrixhasil[n - 1][indeks] = matrixkofaktor[indeks][n - 1];
        matrixhasil[indeks][n - 1] = matrixkofaktor[n - 1][indeks];
        indeks += 1;
      }
    }
    // print hasil
    System.out.println();
    for (int i = 0; i<n; i++){
      for (int j = 0; j<n; j++){
        System.out.print(matrixhasil[i][j] + " ");
      }
      System.out.println();
    }
  }
}
