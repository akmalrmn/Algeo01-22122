import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files

public class inputFile {
  public static void main(String[] args) {
    int numRows = 0;
    int numCols = 0;

    try {
      File myObj = new File("test/data uji.txt");
      Scanner sizeFinder = new Scanner(myObj);
      Scanner myReader = new Scanner(myObj);

      String dataTemp = sizeFinder.nextLine();

      for (int j = 0; j < dataTemp.length(); j++){
        if (dataTemp.charAt(j) == ' '){
            numCols++;
        }
      }
      numCols++;

      while(sizeFinder.hasNextLine()){
        sizeFinder.nextLine();
        numRows++;
      }
      numRows++;

      sizeFinder.close();

      System.out.println(numCols);
      System.out.println(numRows);

      Matrix matrix = new Matrix(numRows, numCols);
      
      int i = 0;
      while (myReader.hasNextLine()) {
        String[] line = myReader.nextLine().trim().split(" ");

        for (int j = 0; j < numCols; j++) {
            // System.out.println(Double.parseDouble(line[j]));
            matrix.setElmt(i, j, Double.parseDouble(line[j]));
        }
        i++;
      }

      matrix.displayMatrix();
      
      myReader.close();
    } catch (FileNotFoundException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
  }
}