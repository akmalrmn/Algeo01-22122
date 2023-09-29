public class Determinan_EK {
    public static double determinant(double[][] matrix) {
        int n = matrix.length;
        double det = 0;
        if (n == 1) {
            det = matrix[0][0];
        } else if (n == 2) {
            det = matrix[0][0] * matrix[1][1] - matrix[0][1] * matrix[1][0];
        } else {
            double[][] submatrix = new double[n - 1][n - 1];
            for (int j = 0; j < n; j++) {
                for (int i = 1; i < n; i++) {
                    for (int k = 0; k < n; k++) {
                        // mengisi submatrix dengan elemen matrix yang tidak berada di baris dan kolom utama
                        if (k < j) {
                            submatrix[i - 1][k] = matrix[i][k];
                        } else if (k > j) {
                            submatrix[i - 1][k - 1] = matrix[i][k];
                        }
                    }
                }
                // setiap elemen dibagi dengan determinan submatrix dan dikalikan dengan (-1)^j
                det += Math.pow(-1, j) * matrix[0][j] * determinant(submatrix);
            }
        }
        return det;
    }
}