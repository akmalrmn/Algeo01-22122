package IO;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import Matrix.*;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedWriter;

public class Parser {
    public static MatrixADT FiletoMatrix() {
        int numRows = 0;
        int numCols = 0;

        Scanner scanner = new Scanner(System.in);
        File myObj = null;

        while (myObj == null || !myObj.exists()) {
            System.out.print("\nMasukkan nama file: ");
            String filename = scanner.nextLine();
            myObj = new File("test/" + filename);

            if (!myObj.exists()) {
                System.out.println("File tidak ditemukan.");
            }
        }

        try {
            Scanner sizeFinder = new Scanner(myObj);
            Scanner myReader = new Scanner(myObj);

            String dataTemp = sizeFinder.nextLine();

            for (int j = 0; j < dataTemp.length(); j++) {
                if (dataTemp.charAt(j) == ' ') {
                    numCols++;
                }
            }
            numCols++;

            while (sizeFinder.hasNextLine()) {
                sizeFinder.nextLine();
                numRows++;
            }
            numRows++;

            sizeFinder.close();

            MatrixADT matrix = new MatrixADT(numRows, numCols);

            int i = 0;
            while (myReader.hasNextLine()) {
                String[] line = myReader.nextLine().trim().split(" ");

                for (int j = 0; j < numCols; j++) {
                    matrix.setElmt(i, j, Double.parseDouble(line[j]));
                }
                i++;
            }

            myReader.close();
            return matrix;
        } catch (FileNotFoundException e) {
            System.out.println("Error: File tidak ditemukan.");
            e.printStackTrace();
        }
        scanner.close();
        return null;
    }

    public static void createFile(MatrixADT matrix, String fileName) {
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
                    if (j == matrix.getLastIdxCol()) {
                        bw.write(matrix.getElmt(i, j) + "");
                    } else {
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

}
