import java.util.Scanner;

public class Matrix {
    public double[][] data;
    int numRows;
    int numCols;
    Scanner scanElmt = new Scanner(System.in);

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
        return this.data[i][i];
    }

    public void displayMatrix() {
        for (int i = 0; i < this.numRows; i++) {
            for (int j = 0; j < this.numCols; j++) {
                System.out.printf("%.2f", data[i][j]);

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

    public Matrix matrikspersegi() {
        // Membaca size matrix
        System.out.print("\nn: ");
        int baris = scanElmt.nextInt();

        // Membuat matrix
        Matrix mOut = new Matrix(baris, baris);

        // Membaca komponen matrix
        System.out.println("\nMasukkan matrix:");
        for (int i = 0; i < baris; i++) {
            for (int j = 0; j < baris; j++) {
                mOut.data[i][j] = scanElmt.nextDouble();
            }
        }
        return mOut;
    }

    public Matrix matriksSPL() {
        // Membaca size matrix
        System.out.print("\nn: ");
        this.numRows = scanElmt.nextInt();
        System.out.print("m: ");
        this.numCols = scanElmt.nextInt();

        // membuat matrix
        Matrix mOut = new Matrix(this.numRows, this.numCols);

        // Membaca komponen matrix
        System.out.println("\nMasukkan matrix:");
        for (int i = 0; i < this.numRows; i++) {
            for (int j = 0; j < this.numCols; j++) {
                mOut.data[i][j] = scanElmt.nextDouble();
            }
        }
        return mOut;
    }

    public void readMatrix(int i, int j) {
    }
}
