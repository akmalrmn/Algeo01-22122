import java.util.Scanner;

public class Interpolasi_Polinom {

    static void backSubstitute(Matrix matrix) {
        int numRows = matrix.getRows();
        int lastCols = matrix.getCols() - 1;
    
        for (int i = numRows-1; i >= 0; i--) {
            double factor = matrix.getElmt(i, i);
    
            matrix.setElmt(i, i, 1);
            matrix.setElmt(i, lastCols, matrix.getElmt(i, lastCols) / factor);
    
            double temp = matrix.getElmt(i, lastCols);
    
            for(int j = i - 1; j >= 0; j--){
                double factor2 = matrix.getElmt(j, i);
    
                matrix.setElmt(j, i, 0);
                matrix.setElmt(j, lastCols, matrix.getElmt(j, lastCols) - factor2 * temp);
            }
        }
    }

    static void swap_row(Matrix matrix, int i, int j) {
        for (int k = 0; k < matrix.getCols(); k++) {
          double temp = matrix.getElmt(i, k);
          matrix.setElmt(i, k, matrix.getElmt(j, k));
          matrix.setElmt(j, k, temp);
        }
      }

    static void performElimination(Matrix matrix){
        for (int k = 0; k <= matrix.getLastIdxRow(); k++) {
            int idxMax = k;
            double valueMax = matrix.getElmt(k, k);
  
            for (int i = k + 1; i <= matrix.getLastIdxRow(); i++){
              if (Math.abs(matrix.getElmt(i, k)) > Math.abs(valueMax)) {
                valueMax = matrix.getElmt(i, k);
                idxMax = i;
              }
            }
    
            if (idxMax != k){
              swap_row(matrix, k, idxMax);
            }
      
            for (int i = k + 1; i <= matrix.getLastIdxRow(); i++) {
              double divider = matrix.getElmt(i, k) / matrix.getElmt(k, k);
  
              for (int j = k + 1; j <= matrix.getLastIdxCol(); j++){
                double temp = matrix.getElmt(i, j) - matrix.getElmt(k,j) * divider;
                matrix.setElmt(i, j, temp);
              }
      
              matrix.setElmt(i, k, 0);
            }
          }
    }

    static void createAugmented(Matrix matrix, Matrix matrixPoint){
        int n = matrix.getRows() - 1;

        for(int i = 0; i <= matrix.getLastIdxRow(); i++){
            for(int j = 0; j <= matrix.getLastIdxCol(); j++){
                if (j == matrix.getLastIdxCol()){
                    matrix.setElmt(i, j, matrixPoint.getElmt(i, 1));
                } else if ( j == matrix.getLastIdxCol() - 1) {
                    matrix.setElmt(i, j, 1);
                } else {
                    double temp = Math.pow(matrixPoint.getElmt(i, 0), n - j);
                    matrix.setElmt(i, j, temp);
                }
            }
        }
    }

    static void findAnswer(Matrix matrix, double x){
        int pangkat = matrix.getRows() - 1;
        int idxLastCol = matrix.getLastIdxCol();
        System.out.print("f(x) = ");
        for( int i = 0; i <= matrix.getLastIdxRow(); i++){
            double element = matrix.getElmt(i, idxLastCol);
            if (i == matrix.getLastIdxRow()){
                if (element > 0){
                     System.out.printf("+ %.4f ", Math.abs(element), pangkat--);
                } else if (element < 0) {
                    System.out.printf("- %.4f ", Math.abs(element), pangkat--);
                } else {
                    pangkat--;
                }
            } else if ( i == matrix.getLastIdxRow() - 1){
                if (element > 0){
                    if (i == 0){
                        System.out.printf("%.4fx ", element);
                    } else {
                        System.out.printf("+ %.4fx ", element);
                    }
                    pangkat--;
                } else if (element < 0) {
                    System.out.printf("- %.4fx^%d ", Math.abs(element), pangkat--);
                } else{
                    pangkat--;
                }
            }
            else {
                if (element > 0){
                    if (i == 0){
                        System.out.printf("%.4fx^%d ", element, pangkat--);
                    } else {
                        System.out.printf("+ %.4fx^%d ", element, pangkat--);
                    }
                } else if (element < 0) {
                    System.out.printf("- %.4fx^%d ", Math.abs(element), pangkat--);
                } else {
                    pangkat--;
                }
            }
        }

        double temp = 0;
        int lastIdx = matrix.getLastIdxCol();
        int n = matrix.getRows() - 1;

        for (int i = 0; i <= matrix.getLastIdxRow(); i++){
            temp += matrix.getElmt(i, lastIdx) * Math.pow(x, n - i);
        }

        System.out.printf(", f(%f) = %f.", x, temp);
    }
    
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        Matrix matrixPoint;
        int n = scanner.nextInt();
        double x = scanner.nextDouble();

        matrixPoint = new Matrix(n  + 1, 2);
        matrixPoint.readMatrix(scanner);

        Matrix matrix = new Matrix(n + 1, n + 2);

        createAugmented(matrix, matrixPoint);

        performElimination(matrix);
        backSubstitute(matrix);

        findAnswer(matrix, x);
        scanner.close();
    }
}
