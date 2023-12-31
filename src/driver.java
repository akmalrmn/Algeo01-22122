import java.util.Scanner;
import IO.*;
import Matrix.MatrixADT;

public class driver {
  public static void main(String[] args) throws InterruptedException {
    Scanner scanner = new Scanner(System.in);
    MatrixADT matriks = new MatrixADT(25, 25);
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
              "7. Image scaler\n" +
              "8. Keluar\n");

      // input pilihan
      Thread.sleep(1000);
      System.out.print("Masukkan nomor program yang ingin dijalankan: ");
      int masukan;
      try {
        masukan = scanner.nextInt();
      } catch (Exception e) {
        System.out.println("Input tidak valid");
        scanner.nextLine();
        continue;
      }
      Thread.sleep(1000);

      // masuk ke pilihan
      if (masukan == 8) {
        System.out.println("\nTerima kasih telah menggunakan program ini! ");
        break;
      }

      else if (masukan == 1) {
        System.out.println(
            "\n1. Metode eliminasi Gauss\n" +
                "2. Metode eliminasi Gauss-Jordan\n" +
                "3. Metode matriks balikan\n" +
                "4. Kaidah cramer\n" +
                "Tekan angka lain untuk kembali ke menu awal\n");
        Thread.sleep(1000);

        // input pilihan
        System.out.print("Masukkan nomor program yang ingin dijalankan: ");
        int masukan2;
        try {
          masukan2 = scanner.nextInt();
          scanner.nextLine();
        } catch (Exception e) {
          System.out.println("Input tidak valid");
          scanner.nextLine();
          continue;
        }
        // masuk ke pilihan
        switch (masukan2) {
          case 1:
            matriks = text.pilihan1();
            Thread.sleep(1000);
            SPL_Gauss.performElimination(matriks);

            // kembali ke menu awal
            Thread.sleep(1000);
            System.out.println("\nTekan enter untuk kembali ke menu awal");
            scanner.nextLine();
            break;
          case 2:
            matriks = text.pilihan1();
            Thread.sleep(1000);
            SPL_GaussJ.SPLGaussJordan(matriks);
            // fungsi gauss-jordan

            // kembali ke menu awal
            Thread.sleep(1000);
            System.out.println("\nTekan enter untuk kembali ke menu awal");
            scanner.nextLine();
            break;
          case 3:
            matriks = text.pilihan1();
            Thread.sleep(1000);
            SPL_Balikan.findSolution(matriks);
            // fungsi matriks balikan

            // kembali ke menu awal
            Thread.sleep(1000);
            System.out.println("\nTekan enter untuk kembali ke menu awal");
            scanner.nextLine();
            break;
          case 4:
            matriks = text.pilihan1();
            Thread.sleep(1000);
            SPL_Cramer.cramer(matriks);

            // kembali ke menu awal
            Thread.sleep(1000);
            System.out.println("\nTekan enter untuk kembali ke menu awal");
            scanner.nextLine();
            break;
          default:
            System.out.println("Input tidak valid");
        }
      } else if (masukan == 2) {
        System.out.println(
            "\n1. Determinan dengan reduksi baris\n" +
                "2. Determinan dengan ekspansi kofaktor\n" +
                "Tekan angka lain untuk kembali ke menu awal\n");
        Thread.sleep(1000);

        // input pilihan
        System.out.print("Masukkan nomor program yang ingin dijalankan: ");
        int masukan2;
        try {
          masukan2 = scanner.nextInt();
          scanner.nextLine();
        } catch (Exception e) {
          System.out.println("Input tidak valid");
          scanner.nextLine();
          continue;
        }

        // masuk ke pilihan
        switch (masukan2) {
          case 1:
            matriks = text.pilihan1();
            Thread.sleep(1000);
            Determinan_ReduksiBaris.performOBE(matriks);

            // kembali ke menu awal
            Thread.sleep(1000);
            System.out.println("\nTekan enter untuk kembali ke menu awal");
            break;
          case 2:
            matriks = text.pilihan1();
            Thread.sleep(1000);
            Determinan_EK.main(matriks);
            

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
                "2. Matriks balikan dengan reduksi baris\n" +
                "Tekan angka lain untuk kembali ke menu awal\n");
        Thread.sleep(1000);

        // input pilihan
        System.out.print("Masukkan nomor program yang ingin dijalankan: ");
        int masukan2;
        try {
          masukan2 = scanner.nextInt();
          scanner.nextLine();
        } catch (Exception e) {
          System.out.println("Input tidak valid");
          scanner.nextLine();
          continue;
        }

        // masuk ke pilihan
        switch (masukan2) {
          case 1:
            matriks = text.pilihan1();
            Thread.sleep(1000);
            matriks = Balikan_Adjoin.adjoin(matriks);

            // kembali ke menu awal
            Thread.sleep(1000);
            text.matrixToFile(matriks);
            System.out.println("\nTekan enter untuk kembali ke menu awal");
            break;
          case 2:
            matriks = text.pilihan1();
            Thread.sleep(1000);
            matriks = Balikan_GaussJ.printInversGJ(matriks);

            // kembali ke menu awal
            Thread.sleep(1000);
            text.matrixToFile(matriks);
            System.out.println("\nTekan enter untuk kembali ke menu awal");
            break;
          default:
            System.out.println("Input tidak valid");
        }
        scanner.nextLine();
      } else if (masukan == 4) {
        Interpolasi_Polinom.main(args);
      } else if (masukan == 5) {
        matriks = MatrixADT.matriksBicubic();
        String ulang = "y";

        while (ulang.equals("y")) {
          System.out.print("\nMasukkan nilai yang ingin dicari: \nx: ");
          double x, y;
          try {
            x = scanner.nextDouble();
            System.out.print("\ny: ");
            y = scanner.nextDouble();
            scanner.nextLine();
          } catch (Exception e) {
            System.out.println("Input tidak valid");
            scanner.nextLine();
            continue;
          }
          System.out.println();

          double hasil = BicubicSI.bicubic(matriks, x, y);
          System.out.println("\nNilai dari f(" + x + ", " + y + ") adalah: " + hasil);
          // kembali ke menu awal
          Thread.sleep(1000);
          System.out.println("\nApakah ingin mencari nilai lain? (y/n)");
          ulang = scanner.nextLine();
        }
      } else if (masukan == 6) {

      } else if (masukan == 7) {
        System.out.println();
        scanner.nextLine();
        ImageScaler.Scaling();
        Thread.sleep(1000);
        System.out.println("\nTekan enter untuk kembali ke menu awal");
        scanner.nextLine();
      } else {
        System.out.println("Input tidak valid");
      }
    }
    scanner.close();
  }
}