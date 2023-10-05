package IO;

import java.util.Scanner;
import Matrix.*;

public class text {
  static Scanner scanner = new Scanner(System.in);

  public static MatrixADT pilihan1() {
    MatrixADT matriks = new MatrixADT(0, 0);
    System.out.print("\n1. Input melalu file\n2. Input melalui keyboard\nMasukkan pilihan: ");
    int pilihan;
    try {
      pilihan = scanner.nextInt();
    } catch (Exception e) {
      System.out.println("Input tidak valid");
      scanner.nextLine();
      return pilihan1();
    }
    if (pilihan == 1) {
      matriks = Parser.FiletoMatrix();
    } else if (pilihan == 2) {
      matriks = matriks.matriksSPL();
    } else {
      System.out.println("Input tidak valid");
      return pilihan1();
    }
    return matriks;
  }

  public static void matrixToFile(MatrixADT matriks) {
    System.out.println("\nApakah ingin menyimpan solusi dalam file?\n1. Yes\n2. No");
    int input;
    try {
      input = scanner.nextInt();
    } catch (Exception e) {
      System.out.println("Input tidak valid");
      scanner.nextLine();
      return;
    }
    if (input == 1) {
      scanner.nextLine();
      System.out.print("Masukkan nama file: ");
      String namaFile = scanner.nextLine();
      Parser.createFile(matriks, namaFile);
    } else if (input != 2) {
      System.out.println("Input tidak valid");
      matrixToFile(matriks);
    }
  }
}