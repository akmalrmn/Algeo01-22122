public class SPL_GaussJordan {
    public static void SPLGaussJordan(Matrix matrix) {
        int rows = matrix.getRows();
        int cols = matrix.getCols();
        Matrix reducedMatrix = new Matrix(rows, cols);

        // Mengisi reducedMatrix dengan data awal matriks
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                reducedMatrix.setElmt(i, j, matrix.getElmt(i, j));
            }
        }

        // Membuat Matriks Tereduksi
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (j != i) {
                    double ratio = reducedMatrix.getElmt(j, i) / reducedMatrix.getElmt(i, i);

                    for (int k = 0; k < cols; k++) {
                        reducedMatrix.setElmt(j, k, reducedMatrix.getElmt(j, k) - (ratio * reducedMatrix.getElmt(i, k)));
                    }
                }
            }
        }

        // Memeriksa apakah matriks memiliki solusi unik
        boolean hasUniqueSolution = checkUniqueSolution(reducedMatrix);

        if (hasUniqueSolution) {
            // Menampilkan hasil dari variabel
            System.out.println("\nHasil SPL:");
            for (int i = 0; i < rows; i++) {
                System.out.println("Nilai variabel : " + reducedMatrix.getElmt(i, cols - 1) / reducedMatrix.getElmt(i, i));
            }

            // Menampilkan matriks tereduksi ke layar
            System.out.println("\nMatriks tereduksi: ");
            reducedMatrix.displayMatrix();
        } else {
            System.out.println("Sistem persamaan tidak memiliki solusi unik.");
        }
    }

    // Fungsi mengecek apakah matriks memiliki solusi unik  
    public static boolean checkUniqueSolution(Matrix matrix) {
        int rows = matrix.getRows();

        for (int i = 0; i < rows; i++) {
            if (matrix.data[i][i] == 0) {
                return false; // Matriks memiliki elemen diagonal nol, tidak ada solusi unik.
            }
        }

        return true; // Semua elemen diagonal non-nol, ada solusi unik.
    }

    // Fungsi menampilkan matriks tereduksi ke layar
    public static void printReducedMatrix(Matrix matrix) {
        int rows = matrix.getRows();
        int cols = matrix.getCols();

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols - 1; j++) {
                System.out.print(matrix.data[i][j] + "\t");
            }
            System.out.println("| " + matrix.data[i][cols - 1]);
        }
    }
}