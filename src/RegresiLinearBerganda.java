import java.util.Scanner;
import java.text.DecimalFormat;
import java.util.InputMismatchException;

public class RegresiLinearBerganda {
    public static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        DecimalFormat df = new DecimalFormat("#.#######");
        DecimalFormat dfAns= new DecimalFormat("#.##");

        try {
            System.out.print("Masukkan jumlah data: ");
            int numData = scanner.nextInt();
            System.out.print("Masukkan jumlah variabel independen (termasuk konstanta): ");
            int numVariables = scanner.nextInt();

            double[][] dataMatrix = new double[numData][numVariables + 1];
            double[][] sumMatrix = new double[numVariables + 1][numVariables + 2];

            System.out.println();
            System.out.println("Masukkan data: ");
            for (int i = 0; i < numData; i++) {
                for (int j = 0; j < numVariables + 1; j++) {
                    dataMatrix[i][j] = scanner.nextDouble();
                }
            }

            double sum = 0;
            double temp1 = 1;
            double temp2 = 1;
            for (int i = 0; i < sumMatrix.length; i++) {
                for (int j = 0; j < sumMatrix[0].length; j++) {
                    sum = 0;
                    for (int k = 0; k < numData - 1; k++) {
                        if (i > 0) {
                            temp1 = dataMatrix[k][i - 1];
                        }
                        if (j > 0) {
                            temp2 = dataMatrix[k][j - 1];
                        }
                        sum += (temp1 * temp2);
                    }
                    sumMatrix[i][j] = sum;
                }
            }

            // Membuat augmented matrix
            double[][] augmentedMatrix = new double[numVariables + 1][numVariables + 2];
            for (int i = 0; i < augmentedMatrix.length; i++) {
                for (int j = 0; j < augmentedMatrix[0].length - 1; j++) {
                    augmentedMatrix[i][j] = sumMatrix[i][j];
                }
            }
            for (int i = 0; i < augmentedMatrix.length; i++) {
                augmentedMatrix[i][augmentedMatrix[0].length - 1] = sumMatrix[i][sumMatrix[0].length - 1];
            }

            // Melakukan eliminasi Gauss-Jordan
            for (int i = 0; i < numVariables + 1; i++) {
                // Mencari pivot
                double pivot = augmentedMatrix[i][i];
                int pivotRow = i;
                for (int j = i + 1; j < numVariables + 1; j++) {
                    if (Math.abs(augmentedMatrix[j][i]) > Math.abs(pivot)) {
                        pivot = augmentedMatrix[j][i];
                        pivotRow = j;
                    }
                }

                // Menukar baris jika pivot tidak berada di diagonal utama
                if (pivotRow != i) {
                    double[] temp = augmentedMatrix[i];
                    augmentedMatrix[i] = augmentedMatrix[pivotRow];
                    augmentedMatrix[pivotRow] = temp;
                }

                // Mengeliminasi elemen di bawah pivot
                for (int j = i + 1; j < numVariables + 1; j++) {
                    double factor = augmentedMatrix[j][i] / augmentedMatrix[i][i];
                    for (int k = i; k < numVariables + 2; k++) {
                        augmentedMatrix[j][k] -= factor * augmentedMatrix[i][k];
                    }
                }

                // Mengeliminasi elemen di atas pivot
                for (int j = i - 1; j >= 0; j--) {
                    double factor = augmentedMatrix[j][i] / augmentedMatrix[i][i];
                    for (int k = i; k < numVariables + 2; k++) {
                        augmentedMatrix[j][k] -= factor * augmentedMatrix[i][k];
                    }
                }
            }

            // Menampilkan hasil koefisien
            System.out.println("\nHasil koefisien: ");
            double[] coef = new double[numVariables + 1];
            for (int i = 0; i < numVariables + 1; i++) {
                coef[i] = augmentedMatrix[i][numVariables + 1] / augmentedMatrix[i][i];
                System.out.println("x" + i + " = " + df.format(coef[i]));
            }

            // Menampilkan persamaan regresi
            System.out.print("\nPersamaan regresi: ");
            System.out.print(df.format(coef[0]));
            for (int i = 1; i < numVariables + 1; i++) {
                System.out.print(" + " + df.format(coef[i]) + "x" + i);
            }

            // Memasukkan nilai x1, x2, ..., xn
            System.out.println("\nMasukkan nilai x1, x2, ..., xn: ");
            double[] x = new double[numVariables];
            for (int i = 0; i < numVariables; i++) {
                x[i] = scanner.nextDouble();
            }

            // Menghitung nilai y
            double y = coef[0];
            for (int i = 1; i < numVariables + 1; i++) {
                y += coef[i] * x[i - 1];
            }
            System.out.println("\nNilai y: " + dfAns.format(y));
        } catch (InputMismatchException e) {
            System.out.println("Error: Input tidak valid. Pastikan input adalah bilangan.");
            
        }
    }
}