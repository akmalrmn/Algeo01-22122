import java.util.Scanner;

public class Interpolasi_Polinom {
    static void createAugmented(Matrix matrix, Matrix matrixPoint) {
        int n = matrix.getRows() - 1;

        for (int i = 0; i <= matrix.getLastIdxRow(); i++) {
            for (int j = 0; j <= matrix.getLastIdxCol(); j++) {
                if (j == matrix.getLastIdxCol()) {
                    matrix.setElmt(i, j, matrixPoint.getElmt(i, 1));
                } else {
                    double temp = Math.pow(matrixPoint.getElmt(i, 0), n - j);
                    matrix.setElmt(i, j, temp);
                }
            }
        }
    }

    static double findAnswer(Matrix matrix, double x) {
        double temp = 0;
        int lastIdx = matrix.getLastIdxCol();
        int n = matrix.getRows() - 1;

        for (int i = 0; i <= matrix.getLastIdxRow(); i++) {
            temp += matrix.getElmt(i, lastIdx) * Math.pow(x, n - i);
        }

        return temp;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Matrix matrixPoint, matrix;
        int n = scanner.nextInt();
        double x = scanner.nextDouble();

        matrixPoint = new Matrix(n + 1, 2);
        matrixPoint.readMatrix(n + 1, 2);

        matrix = new Matrix(n + 1, n + 2);

        createAugmented(matrix, matrixPoint);

        Gauss.performElimination(matrix);
        Gauss.backSubstitute(matrix);

        // matrix.displayMatrix();

        System.out.printf("%f\n", findAnswer(matrix, x));
        scanner.close();
    }
}
