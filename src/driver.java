import java.util.Scanner;

import Matrix.Matrix;

public class driver {
  public static void main(String[] args) throws InterruptedException {
    Scanner scanner = new Scanner(System.in);
    Matrix matriks = new Matrix(25, 25);
    while (true) {
      // tampilan menu
      Thread.sleep(1000);
      System.out.println(
          "\nMENU\n" +
              "1. Sistem Persamaaan Linier\n" +
              "2. Determinan\n" +
              "3. Matriks balikan\n" +
              "4. Interpolasi Polinom\n" +
              "5. Interpolasi Bicubic Spline\n" +
              "6. Regresi linier berganda\n" +
              "7. Keluar\n");

      // input pilihan
      Thread.sleep(1000);
      System.out.print("Masukkan nomor program yang ingin dijalankan: ");
      int masukan = scanner.nextInt();
      Thread.sleep(1000);

      // masuk ke pilihan
      if (masukan == 7) {
        System.out.println("Terima kasih telah menggunakan program ini");
        break;
      }

      else if (masukan == 1) {
        System.out.println(
            "\n1. Metode eliminasi Gauss\n" +
                "2. Metode eliminasi Gauss-Jordan\n" +
                "3. Metode matriks balikan\n" +
                "4. Kaidah cramer\n");
        Thread.sleep(1000);

        // input pilihan
        System.out.print("Masukkan nomor program yang ingin dijalankan: ");
        int masukan2 = scanner.nextInt();
        scanner.nextLine();

        // masuk ke pilihan
        matriks = matriks.matriksSPL();
        Thread.sleep(1000);
        switch (masukan2) {
          case 1:
            Gauss.performElimination(matriks);

            // kembali ke menu awal
            Thread.sleep(1000);
            System.out.println("\nTekan enter untuk kembali ke menu awal");
            break;
          case 2:
            // fungsi gauss-jordan

            // kembali ke menu awal
            Thread.sleep(1000);
            System.out.println("\nTekan enter untuk kembali ke menu awal");
            break;
          case 3:
            // fungsi matriks balikan

            // kembali ke menu awal
            Thread.sleep(1000);
            System.out.println("\nTekan enter untuk kembali ke menu awal");
            break;
          case 4:
            SPL_Cramer.cramer(matriks);

            // kembali ke menu awal
            Thread.sleep(1000);
            System.out.println("\nTekan enter untuk kembali ke menu awal");
            break;
          default:
            System.out.println("Input tidak valid");
        }
        scanner.nextLine();
      } else if (masukan == 2) {
        System.out.println(
            "\n1. Determinan dengan reduksi baris\n" +
                "2. Determinan dengan ekspansi kofaktor\n");
        Thread.sleep(1000);

        // input pilihan
        System.out.print("Masukkan nomor program yang ingin dijalankan: ");
        int masukan2 = scanner.nextInt();
        scanner.nextLine();

        // masuk ke pilihan
        Thread.sleep(1000);
        matriks = matriks.matrikspersegi();
        switch (masukan2) {
          case 1:
            // fungsi reduksi baris

            // kembali ke menu awal
            Thread.sleep(1000);
            System.out.println("\nTekan enter untuk kembali ke menu awal");
            break;
          case 2:
            System.out.println("\nDeterminan: " + Determinan_EK.determinant(matriks));

            // kembali ke menu awal
            Thread.sleep(1000);
            System.out.println("\nTekan enter untuk kembali ke menu awal");
            break;
          default:
            System.out.println("Input tidak valid");
        }
        scanner.nextLine();
      } else if (masukan == 3) {
        System.out.println(
            "\n1. Matriks balikan dengan adjoin\n" +
                "2. Matriks balikan dengan reduksi baris\n");
        Thread.sleep(1000);

        // input pilihan
        System.out.print("Masukkan nomor program yang ingin dijalankan: ");
        int masukan2 = scanner.nextInt();
        scanner.nextLine();

        // masuk ke pilihan
        Thread.sleep(1000);
        matriks = matriks.matrikspersegi();
        switch (masukan2) {
          case 1:
            Balikan_Adjoin.adjoin(matriks);

            // kembali ke menu awal
            Thread.sleep(1000);
            System.out.println("\nTekan enter untuk kembali ke menu awal");
            break;
          case 2:
            // fungsi reduksi baris

            // kembali ke menu awal
            Thread.sleep(1000);
            System.out.println("\nTekan enter untuk kembali ke menu awal");
            break;
          default:
            System.out.println("Input tidak valid");
        }
        scanner.nextLine();
      } else if (masukan == 4) {

      } else if (masukan == 5) {
        System.out.print("\nMasukkan nilai yang ingin dicari: \nx:");
        double x = scanner.nextDouble();
        System.out.print("y:");
        double y = scanner.nextDouble();
        System.out.println();

        matriks = Matrix.matriksBicubic();
        double hasil = BicubicSI.bicubic(matriks, x, y);
        System.out.println("\nNilai dari f(" + x + ", "+ y +") adalah: " + hasil);
        // kembali ke menu awal
        scanner.nextLine();
        Thread.sleep(1000);
        System.out.println("\nTekan enter untuk kembali ke menu awal");
        scanner.nextLine();
      } else if (masukan == 6) {

      } else {
        System.out.println("Input tidak valid");
      }
    }
    scanner.close();
  }

}
