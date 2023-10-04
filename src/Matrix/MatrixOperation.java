package Matrix;

public class MatrixOperation {
  public static Matrix inversGaussJ(Matrix m) {
    int i, j, col = m.getCols();
    Matrix mTemp = m.copyMatrix();

    mTemp.setCol(col * 2);
    for (i = 0; i < mTemp.getRows(); i++) {
      for (j = m.getCols(); j < mTemp.getCols(); j++) {
        if (i + m.getCols() == j) {
          mTemp.setElmt(i, j, 1);
        }
      }
    }

    mTemp = OBE.gaussJordan(mTemp);

    Matrix mOut = new Matrix(m.numRows, m.numCols);
    for (i = 0; i < mOut.numRows; i++) {
      for (j = 0; j < mOut.numCols; j++) {
        mOut.setElmt(i, j, mTemp.getElmt(i, j));
      }
    }
    for (i = 0; i < mOut.numRows; i++) {
      if (mOut.isRowZero(i)) {
        return null;
      }
    }

    return mTemp.copy(0, col, col, col);
  }

  public static Matrix multiplyMatrix(Matrix m1, Matrix m2) {
    Matrix mtrx = new Matrix(16, 1);
    double result;
    for (int i = 0; i < 16; i++) {
      for (int j = 0; j < 1; j++) {
        result = 0;
        for (int k = 0; k < 16; k++) {
          result += m1.data[i][k] * m2.data[k][j];
        }
        mtrx.data[i][j] = result;
      }
    }
    return mtrx;
  }
}