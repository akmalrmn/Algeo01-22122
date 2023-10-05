import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import Matrix.MatrixADT;

public class Determinan_EK {
    public static Scanner scan = new Scanner(System.in);

    public static void main(MatrixADT m) {
        double det = detCofactor(m);
        System.out.printf("Determinan dari matriks tersebut = %.6f\n", det);

        System.out.println("\nApakah ingin menyimpan solusi dalam file?\n1. Yes\n2. No");
        int input = scan.nextInt();

        while (input != 1 && input != 2) {
            System.out.print("Input tidak valid\n");
            input = scan.nextInt();
        }

        if (input == 1) {
            scan.nextLine();
            System.out.print("Masukkan nama file (tanpa txt): ");
            String filename = scan.nextLine();
            outputFile(det, filename);
        }
        System.out.println("Determinan dari matriks tersebut = " + det);

    }

    public static double detCofactor(MatrixADT M) { // Mengembalikan nilai determinan dengan metode kofaktor
        int sign;
        int m;
        double det;
        MatrixADT mTemp = new MatrixADT(M.numRows - 1, M.numCols - 1);

        sign = 1;
        det = 0;
        if (M.numRows != 1) {
            for (m = 0; m < M.numRows; m++) {
                mTemp = MatrixADT.minorMatrix(M, 0, m);
                det += (detCofactor(mTemp) * M.data[0][m] * sign);
                sign *= (-1);
            }
        } else {
            det = M.data[0][0];
        }
        return det;
    }

    static void outputFile(Double answer, String fileName) {
        try {
            File myObj = new File("./test/output/" + fileName + ".txt");
            if (myObj.createNewFile()) {
                System.out.println("File created: " + myObj.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        try {
            FileWriter fw = new FileWriter("./test/output/" + fileName + ".txt");
            fw.write("Determinan dari matriks tersebut = " + answer + "\n");
            fw.close();
            System.out.println("Content written to file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}