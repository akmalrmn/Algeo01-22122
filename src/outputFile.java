import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class outputFile {
    public static void createFile(Matrix matrix, String fileName) {
        try {
            File myObj = new File("./test/output/" + fileName);
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
            BufferedWriter bw = new BufferedWriter(new FileWriter("./test/output/" + fileName));
            for (int i = 0; i < matrix.getRows(); i++) {
                for (int j = 0; j < matrix.getCols(); j++) {
                    if (j == matrix.getLastIdxCol()){
                        bw.write(matrix.getElmt(i, j) + "");
                    } else{
                        bw.write(matrix.getElmt(i, j) + " ");
                    }
                }
                bw.newLine();
            }
            bw.flush();
            bw.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
  }

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    Matrix matrix = new Matrix(3, 4);
    matrix.readMatrix(scanner);

    createFile(matrix, "haha.txt");

    scanner.close();
  }
}
