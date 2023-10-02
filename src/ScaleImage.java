import java.io.*;
import java.util.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class ScaleImage {
    static Scanner sc = new Scanner(System.in);

    public static void Scaling() {
        String fileName, fileOuput, imgPath;
        int i, j, k, l;

        BufferedImage img = null;

        System.out.println("Masukkan Nama File Gambar (berserta formatnya): ");
        fileName = sc.nextLine();
        imgPath = imagePath(fileName);
        File imageFile = new File(imgPath);

        System.out.println("Masukkan faktor skala: ");
        int scaleFactor = sc.nextInt();

        try {
            img = ImageIO.read(imageFile);
            int w = img.getWidth(), h = img.getHeight();
            BufferedImage scaledImg = new BufferedImage(w * scaleFactor, h * scaleFactor, BufferedImage.TYPE_INT_RGB);

            for (i = 0; i < w; i++) {
                for (j = 0; j < h; j++) {
                    for (k = 0; k < scaleFactor; k++) {
                        for (l = 0; l < scaleFactor; l++) {
                            scaledImg.setRGB(i * scaleFactor + k, j * scaleFactor + l, img.getRGB(i, j));
                        }
                    }
                }
            }

            System.out.println("Masukkan Nama File Output (berserta formatnya): ");
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
