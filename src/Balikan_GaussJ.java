import Matrix.MatrixADT;
import Matrix.OBE;

public class Balikan_GaussJ {
  public static MatrixADT inversGaussJ(MatrixADT m) {
    int i, j, col = m.getCols();
    MatrixADT mTemp = m.copyMatrix();

    mTemp.setCol(col * 2);
    for (i = 0; i < mTemp.getRows(); i++) {
      for (j = m.getCols(); j < mTemp.getCols(); j++) {
        if (i + m.getCols() == j) {
          mTemp.setElmt(i, j, 1);
        }
      }
    }

    mTemp = OBE.gaussJordan(mTemp);

    MatrixADT mOut = new MatrixADT(m.numRows, m.numCols);
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

  public static MatrixADT printInversGJ(MatrixADT m) {
    m = inversGaussJ(m);
    m.displayMatrix();
    return m;
  }
}
