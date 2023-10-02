package Matrix;

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

    public void readMatrix(int row, int col) {
        double elmt;
        int i, j;
        System.out.println("Masukkan Matriks, pisahkan baris dengan newline dan kolom dengan spasi");
        Scanner scanElmt = new Scanner(System.in);
        this.numRows = row;
        this.numCols = col;
        for (i = 0; i < row; i++) {
            for (j = 0; j < col; j++) {
                elmt = scanElmt.nextDouble();
                this.data[i][j] = elmt;
            }
        }
    }

    public void readMatrix1() {
        double elmt;
        int i, j;
        System.out.println("Masukkan Matriks!");
        Scanner scanElmt = new Scanner(System.in);
        this.numRows = 4;
        this.numCols = 4;
        for (i = 0; i < 4; i++) {
            for (j = 0; j < 4; j++) {
                elmt = scanElmt.nextDouble();
                this.data[i][j] = elmt;
            }
        }
    }

    public static Matrix matriksBicubic(){
        Matrix hasil = new Matrix(20, 20);
        Matrix mOut1 = new Matrix(4, 4);
        mOut1.readMatrix1();
        hasil = mOut1.copyMatrix();

        return hasil;
    }

    public Matrix copyMatrix() {
        Matrix mOut = new Matrix(this.numRows, this.numCols);
        int i, j;
        mOut.numRows = this.numRows;
        mOut.numCols = this.numCols;
        for (i = 0; i < this.numRows; i++) {
            for (j = 0; j < this.numCols; j++) {
                mOut.data[i][j] = this.data[i][j];
            }
        }
        return mOut;
    }


    public void setCol(int col) {
        this.resize(this.numRows, col);
    }

    public void resize(int row, int col) {
        double[][] mtrx = new double[row][col];
        int Row1, Col1;
        if (row < this.numRows) {
            Row1 = row;
        } else {
            Row1 = this.numRows;
        }
        if (col < this.numCols) {
            Col1 = col;
        } else {
            Col1 = this.numCols;
        }
        int i, j;

        for (i = 0; i < Row1; i++) {
            for (j = 0; j < Col1; j++) {
                mtrx[i][j] = this.data[i][j];
            }
        }
        this.numRows = row;
        this.numCols = col;
        this.data = mtrx;
    }

    

    public Matrix copy(int Row1, int Row2, int Col1, int Col2) {
        int i, j;
        Matrix mOut = new Matrix(Row2, Col2);
        for (i = 0; i < Row2; i++) {
            for (j = 0; j < Col2; j++) {
                mOut.setElmt(i, j, this.getElmt(i + Row1, j + Col1));
            }
        }
        return mOut;
    }

    public boolean isRowZero(int idxRow) {
        int j;
        boolean val;

        j = 0;
        val = true;
        for (j = 0; j < this.numCols; j++) {
            if (getElmt(idxRow, j) != 0) {
                val = false;
                break;
            }
        }
        return val;
    }

    
}
