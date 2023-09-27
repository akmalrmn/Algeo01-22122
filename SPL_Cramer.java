public class SPL_Cramer {
  public static void cramer(float[][] matrix) {
    int jmlhbaris = matrix.length; 
    int jmlhkolom = matrix[0].length;
    float[] hasil = new float[jmlhkolom - 1];
    float[][] submatrix = new float[jmlhbaris][jmlhkolom - 1];

    // menghitung determinan matriks utama
    for (int i = 0; i < jmlhbaris; i++) {
      for (int j = 0; j < jmlhkolom - 1; j++) {
        submatrix[i][j] = matrix[i][j];
      }
    }

    float det = Determinan_EK.determinant(submatrix);

    // menghitung determinan dari submatrix
    if (det != 0) {
      for (int j = 0; j < jmlhkolom - 1; j++) {
        // memasukkan semua elemen matrix (kecuali kolom terakhir) ke submatrix
        for (int k = 0; k < jmlhbaris; k++) {
          for (int l = 0; l < jmlhkolom - 1; l++) {
            submatrix[k][l] = matrix[k][l];
          }
        }
        // memasukkan 1 kolom matrix ke submatrix
        for (int i = 0; i < jmlhbaris; i++) {
          submatrix[i][j] = matrix[i][jmlhkolom - 1];
        }
        hasil[j] = Determinan_EK.determinant(submatrix) / det;
      }
    } else {
      // masukkin fungsi gauss
    }
    // print hasil
    System.out.println();
    for (int i = 1; i < jmlhbaris+1; i++){
      System.out.println("X" + i + ": " + hasil[i-1]);
    }
  }
}
