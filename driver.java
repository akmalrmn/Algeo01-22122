import java.util.Scanner;

public class driver {
  public static void main(String[] args) throws InterruptedException {
    Scanner scanner = new Scanner(System.in);
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
        float[][] matriks;
        Thread.sleep(1000);
        switch (masukan2) {
          case 1:
            matriks = matriksSPL();
            // fungsi gauss

            // kembali ke menu awal
            Thread.sleep(1000);
            System.out.println("\nTekan enter untuk kembali ke menu awal");
            scanner.nextLine();
            break;
          case 2:
            matriks = matriksSPL();
            // fungsi gauss-jordan

            // kembali ke menu awal
            Thread.sleep(1000);
            System.out.println("\nTekan enter untuk kembali ke menu awal");
            scanner.nextLine();
            break;
          case 3:
            matriks = matriksSPL();
            // fungsi matriks balikan

            // kembali ke menu awal
            Thread.sleep(1000);
            System.out.println("\nTekan enter untuk kembali ke menu awal");
            scanner.nextLine();
            break;
          case 4:
            matriks = matriksSPL();
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
                "2. Determinan dengan ekspansi kofaktor\n");
        Thread.sleep(1000);

        // input pilihan
        System.out.print("Masukkan nomor program yang ingin dijalankan: ");
        int masukan2 = scanner.nextInt();
        scanner.nextLine();

        // masuk ke pilihan
        Thread.sleep(1000);
        float[][] matriks = matrikspersegi();
        switch (masukan2) {
          case 1:
            // fungsi reduksi baris

            // kembali ke menu awal
            Thread.sleep(1000);
            System.out.println("\nTekan enter untuk kembali ke menu awal");
            scanner.nextLine();
            break;
          case 2:
            System.out.println("\nDeterminan: " + Determinan_EK.determinant(matriks));

            // kembali ke menu awal
            Thread.sleep(1000);
            System.out.println("\nTekan enter untuk kembali ke menu awal");
            scanner.nextLine();
            break;
          default:
            System.out.println("Input tidak valid");
        }
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
        float[][] matriks = matrikspersegi();
        switch (masukan2) {
          case 1:
            Balikan_Adjoin.adjoin(matriks);

            // kembali ke menu awal
            Thread.sleep(1000);
            System.out.println("\nTekan enter untuk kembali ke menu awal");
            scanner.nextLine();
            break;
          case 2:
            // fungsi reduksi baris

            // kembali ke menu awal
            Thread.sleep(1000);
            System.out.println("\nTekan enter untuk kembali ke menu awal");
            scanner.nextLine();
            break;
          default:
            System.out.println("Input tidak valid");
        }
      } else {
        System.out.println("Input tidak valid");
      }
    }
    scanner.close();
  }

  public static float[][] matrikspersegi() {
    Scanner scanner = new Scanner(System.in);
    // Membaca sie matrix
    System.out.print("\nn: ");
    int n = scanner.nextInt();

    // Membuat matrix
    float[][] matrix = new float[n][n];

    // Membaca komponen matrix
    System.out.println("\nMasukkan matrix:");
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
        matrix[i][j] = scanner.nextInt();
      }
    }
    return matrix;
  }

  public static float[][] matriksSPL() {
    Scanner scanner = new Scanner(System.in);

    // Membaca size matrix
    System.out.print("\nn: ");
    int n = scanner.nextInt();
    System.out.print("m: ");
    int m = scanner.nextInt();

    // membuat matrix
    float[][] matrix = new float[n][m];

    // Membaca komponen matrix
    System.out.println("\nMasukkan matrix:");
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < m; j++) {
        matrix[i][j] = scanner.nextInt();
      }
    }
    return matrix;
  }
}
