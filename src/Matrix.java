import java.util.Scanner;

public class Matrix {
    public double[][] data;
    public int numRows;
    public int numCols;

    public Matrix(int nRows, int nCols) {
        numRows = nRows;
        numCols = nCols;
        data = new double[nRows][nCols];
    }

    public int getRows() {
        return numRows;
    }

    public int getCols() {
        return numCols;
    }

    public double getElmt(int i, int j) {
        return data[i][j];
    }
    
    public void setElmt(int i, int j, double value) {
        data[i][j] = value;
    }

    public boolean isMatrixIdxValid(int i, int j) {
        return (i >= 0 && i < numRows && j >= 0 && j < numCols);
    }

    public int getLastIdxRow() {
        return numRows - 1;
    }

    public int getLastIdxCol() {
        return numCols - 1;
    }

    public boolean isIdxEff(int i, int j) {
        return (i >= 0 && i < numRows && j >= 0 && j < numCols);
    }

    public double getElmtDiagonal(int i) {
        return data[i][i];
    }

    public void copyMatrix(Matrix mIn) {
        numRows = mIn.numRows;
        numCols = mIn.numCols;
        data = new double[numRows][numCols];

        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                data[i][j] = mIn.data[i][j];
            }
        }
    }

    public void readMatrix(int nRow, int nCol) {
        numRows = nRow;
        numCols = nCol;
        data = new double[numRows][numCols];

        Scanner scanner = new Scanner(System.in);
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                data[i][j] = scanner.nextDouble();
            }
        }

        scanner.close();
    }

    public void displayMatrix() {
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                System.out.printf("%.2f",data[i][j]);

                if (j != numCols - 1) {
                    System.out.print(" ");
                }
            }

            if (i != numRows - 1) {
                System.out.println();
            }
        }
        System.out.println();
    }
}
