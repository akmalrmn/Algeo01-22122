import Matrix.Matrix;

public class Determinan_EK {
    public static double determinant(Matrix matrix) {
        int n = matrix.getCols();
        double det = 0;
        if (n == 1) {
            det = matrix.data[0][0];
        } else if (n == 2) {
            det = matrix.data[0][0] * matrix.data[1][1] - matrix.data[0][1] * matrix.data[1][0];
        } else {
            Matrix submatrix = new Matrix(n - 1, n - 1);
            for (int j = 0; j < n; j++) {
                for (int i = 1; i < n; i++) {
                    for (int k = 0; k < n; k++) {
                        // mengisi submatrix dengan elemen matrix yang tidak berada di baris dan kolom
                        // utama
                        if (k < j) {
                            submatrix.data[i - 1][k] = matrix.data[i][k];
                        } else if (k > j) {
                            submatrix.data[i - 1][k - 1] = matrix.data[i][k];
                        }
                    }
                }
                // setiap elemen dibagi dengan determinan submatrix dan dikalikan dengan (-1)^j
                det += Math.pow(-1, j) * matrix.data[0][j] * determinant(submatrix);
            }
        }

        return det;
    }
}