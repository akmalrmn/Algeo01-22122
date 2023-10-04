import Matrix.*;

public class BicubicSI {
  // fungsi utama
  public static double bicubic(Matrix m, double x, double y) {
    Matrix xMatrix = matrixX();
    Matrix koef = koefisien(m, xMatrix);
    double hasil = 0;
    int row = 0;

    for (int i = 0; i < 4; i++) {
      for (int j = 0; j < 4; j++) {
        hasil += Math.pow(x, i) * Math.pow(y, j) * koef.getElmt(row, 0);
        row++;
      }
    }
    return hasil;
  }

  // mencari nilai koefisien
  public static Matrix koefisien(Matrix m, Matrix xMatrix) {
    return MatrixOperation.multiplyMatrix(inversematrixX(), matrixY(m));
  }

  // mengubah matriks 4x4 menjadi 16x1
  public static Matrix matrixY(Matrix m) {
    int numRow = 0;
    Matrix mtrx = new Matrix(16, 1);

    for (int i = 0; i < m.getRows(); i++) {
      for (int j = 0; j < m.getCols(); j++) {
        mtrx.setElmt(numRow, 0, m.getElmt(i, j));
        numRow++;
      }
    }

    return mtrx;
  }

  // membuat matriks 16x16 yang seperti di spek tugas
  public static Matrix matrixX() {
    Matrix koef = new Matrix(16, 16); // matrix variable dari tiap fungsi
    int row = 0, col = 0;

    for (int x = -1; x < 3; x++) {
      for (int y = -1; y < 3; y++) {
        col = 0;
        for (int i = 0; i < 4; i++) {
          for (int j = 0; j < 4; j++) {
            koef.setElmt(row, col, Math.pow(x, i) * Math.pow(y, j));
            col++;
          }
        }
        row++;
      }
    }
    return koef;
  }

  // melakukan inverse pada matrixX agar bisa dikali dengan matrixY
  public static Matrix inversematrixX() {
    return MatrixOperation.inversGaussJ(matrixX());
  }

}