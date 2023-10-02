import java.io.*;
import java.util.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class ScaleImage {
    static Scanner sc = new Scanner(System.in);

    public static void Scaling() {
        String fileName, fileOuput, imgPath;
        int i, j;

        BufferedImage img = null;

        System.out.print("Masukkan Nama File Gambar (berserta formatnya): ");
        fileName = sc.nextLine();
        imgPath = imagePath(fileName);
        File imageFile = new File(imgPath);

        System.out.print("Masukkan faktor skala: ");
        int scaleFactor = sc.nextInt();

        try {
            img = ImageIO.read(imageFile);
            int w = img.getWidth(), h = img.getHeight();
            BufferedImage scaledImg = new BufferedImage(w * scaleFactor, h * scaleFactor, BufferedImage.TYPE_INT_RGB);

            // Use BicubicSI to scale the image
            BicubicSI bicubicSI = new BicubicSI();
            for (i = 0; i < w * scaleFactor; i++) {
                for (j = 0; j < h * scaleFactor; j++) {
                    double x = (double) i / scaleFactor;
                    double y = (double) j / scaleFactor;
                    int rgb = bicubicSI.interpolate(img, x, y);
                    scaledImg.setRGB(i, j, rgb);
                }
            }

            System.out.print("Masukkan Nama File Output (berserta formatnya): ");
            System.out.println();
            fileOuput = sc.next();
            printImage(fileOuput, scaledImg);
            System.out.println("Image Scaling - done");
        } catch (IOException e) {
            System.out.println("Image tidak ditemukan");
        }
    }

    // =========== FUNCTION ===============
    public static String imagePath(String fileName) {
        String filePath, currentPath;
        currentPath = System.getProperty("user.dir");
        if (currentPath.contains("src")) {
            filePath = currentPath + fileName;
        } else {
            filePath = currentPath + "\\test\\" + fileName;
        }
        return filePath;
    }

    public static void printImage(String fileName, BufferedImage img) {
        try {
            String outputPath = imagePath(fileName);
            File outputFile = new File(outputPath);

            ImageIO.write(img, "jpg", outputFile);

            System.out.println("Writing complete.");
        } catch (IOException e) {
            System.out.println("Error: " + e);
        }
    }

    public static void main(String[] args) {
        Scaling();
    }
}