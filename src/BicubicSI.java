import Matrix.*;

public class BicubicSI {
  // fungsi utama
  public static double bicubic(MatrixADT m, double x, double y) {
    MatrixADT xMatrix = matrixX();
    MatrixADT koef = koefisien(m, xMatrix);
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
  public static MatrixADT koefisien(MatrixADT m, MatrixADT xMatrix) {
    return MatrixADT.multiplyMatrix(inversematrixX(), matrixY(m));
  }

  // mengubah matriks 4x4 menjadi 16x1
  public static MatrixADT matrixY(MatrixADT m) {
    int numRow = 0;
    MatrixADT mtrx = new MatrixADT(16, 1);

    for (int i = 0; i < m.getRows(); i++) {
      for (int j = 0; j < m.getCols(); j++) {
        mtrx.setElmt(numRow, 0, m.getElmt(i, j));
        numRow++;
      }
    }

    return mtrx;
  }

  // membuat matriks 16x16 yang seperti di spek tugas
  public static MatrixADT matrixX() {
    MatrixADT koef = new MatrixADT(16, 16); // matrix variable dari tiap fungsi
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
  public static MatrixADT inversematrixX() {
    return Balikan_GaussJ.inversGaussJ(matrixX());
  }
}
