package Matrix;

public class OBE {
  public static Matrix gaussJordan(Matrix m) {
    // Perform Gauss-Jordan elimination
    Matrix m1 = m.copyMatrix();
    for (int i = 0; i < m1.getRows(); i++) {
      // Find the pivot element
      double pivot = m1.data[i][i];

      // Divide the row by the pivot element
      for (int j = 0; j < m1.getCols(); j++) {
        m1.data[i][j] /= pivot;
      }

      // Subtract the pivot row from all other rows
      for (int j = 0; j < m1.getRows(); j++) {
        if (j != i) {
          double factor = m1.data[j][i];
          for (int k = 0; k < m1.getCols(); k++) {
            m1.data[j][k] -= factor * m1.data[i][k];
          }
        }
      }
    }

    return m1;
  }
}
