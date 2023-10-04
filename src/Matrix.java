import java.util.Scanner;

public class Matrix {
    public double[][] data;
    public int numRows;
    public int numCols;

    public Matrix(int nRows, int nCols) {
        this.numRows = nRows;
        this.numCols = nCols;
        this.data = new double[nRows][nCols];
    }

    public int getRows() {
        return this.numRows;
    }

    public int getCols() {
        return this.numCols;
    }

    public double getElmt(int i, int j) {
        return this.data[i][j];
    }

    public void setElmt(int i, int j, double value) {
        this.data[i][j] = value;
    }

    public boolean isMatrixIdxValid(int i, int j) {
        return (i >= 0 && i < this.numRows && j >= 0 && j < this.numCols);
    }

    public int getLastIdxRow() {
        return this.numRows - 1;
    }

    public int getLastIdxCol() {
        return this.numCols - 1;
    }

    public boolean isIdxEff(int i, int j) {
        return (i >= 0 && i < this.numRows && j >= 0 && j < this.numCols);
    }

    public double getElmtDiagonal(int i) {
        return data[i][i];
    }


    public void displayMatrix() {
        for (int i = 0; i < this.numRows; i++) {
            for (int j = 0; j < this.numCols; j++) {
                System.out.printf("%.2f", this.data[i][j]);

                if (j != this.numCols - 1) {
                    System.out.print(" ");
                }
            }

            if (i != this.numRows - 1) {
                System.out.println();
            }
        }
        System.out.println();
    }

    public void readMatrix(Scanner scanner2){
        // Scanner scanner = new Scanner(System.in);
        int i, j;

        for(i = 0; i < this.numRows; i++){
            for (j = 0; j < this.numCols; j++){
                data[i][j] = scanner2.nextDouble();
            }
        }
    }

    public void createIdentity(){
        int Length = this.numRows;
        for(int i = 0; i < Length; i++){
            for(int j = 0; j < Length; j++){
                if (j == i){
                    this.data[i][j] = 1;
                } else {
                    this.data[i][j] = 0;
                }
            }
        }
    }

    public Matrix multiplyMatrix(Matrix matrix2){
        Matrix matrixAns = new Matrix(this.numRows, matrix2.getCols());

        double jumlah = 0;
        for (int i = 0; i < this.numRows; i++){
            for (int j = 0; j < matrix2.getCols(); j++){
                for (int k = 0; k < matrix2.getRows(); k++){
                    jumlah = jumlah + this.data[i][k] * matrix2.getElmt(k, j);
                }
                matrixAns.setElmt(i, j, jumlah);
                jumlah = 0; 
            }
        }
        return matrixAns;
    }
}
