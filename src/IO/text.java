package IO;
import java.util.Scanner;

import Matrix.*;

public class text {
  static Scanner scanner = new Scanner(System.in);
  public static MatrixADT pilihan1(){
    MatrixADT matriks = new MatrixADT(0, 0);
    System.out.print("\n1. Input melalu file\n2. Input melalui keyboard\nMasukkan pilihan: ");
    int pilihan = scanner.nextInt();
    if (pilihan == 1){
      matriks = Parser.FiletoMatrix();
    }
    else if (pilihan == 2){
      matriks = matriks.matrikspersegi();
    } 
    return matriks;
  }
}
