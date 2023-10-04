import java.io.*;
import java.util.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import Matrix.*;

public class ImageScaler {
    static Scanner sc = new Scanner(System.in);

    public static void Scaling() {
        String fileName, fileOuput, imgPath;
        int i, j, k, l;

        BufferedImage img = null;
        BufferedImage doubleImg = null; // hasil akhir
        BufferedImage imgTemp = null; // buat temp interpolasi
        System.out.print("Masukkan Nama File Gambar (berserta formatnya): ");
        fileName = sc.nextLine();
        imgPath = imagePath(fileName);
        File imageFile = new File(imgPath);

        try {
            img = ImageIO.read(imageFile);
            int w = img.getWidth(), h = img.getHeight();
            System.out.print("Masukkan faktor scaling: ");
            float factor = sc.nextFloat();
            System.out.println("scaling the image..\n");
            doubleImg = new BufferedImage((int) (w * factor), (int) (h * factor), BufferedImage.TYPE_INT_RGB);
            imgTemp = new BufferedImage(w + 4, h + 4, BufferedImage.TYPE_INT_RGB);
            // ngisi gambar temporary dengan image normal dengan bingkai atas
            for (i = 0; i < w; i++) {
                for (j = 0; j < h; j++) {
                    imgTemp.setRGB(i + 2, j + 2, img.getRGB(i, j));
                }
            }
            for (i = 0; i < w + 4; i++) {
                for (j = 0; j < h + 4; j++) {
                    if (i == 1 && j > 1 && j < h + 3) {
                        imgTemp.setRGB(i, j, imgTemp.getRGB(i + 1, j));
                    } else if (i == 1 && j == 1) {
                        imgTemp.setRGB(i, j, imgTemp.getRGB(i + 1, j + 1));
                    } else if (i > w + 2 && j > 1 && j < h + 3) {
                        imgTemp.setRGB(i, j, imgTemp.getRGB(i - 1, j));
                    } else if (i == w + 3 && j == 1) {
                        imgTemp.setRGB(i, j, imgTemp.getRGB(i - 1, j + 1));
                    } else if (i == 1 && j == h + 3) {
                        imgTemp.setRGB(i, j, imgTemp.getRGB(i + 1, j - 1));
                    } else if (i == 1 && j == h + 4) {
                        imgTemp.setRGB(i, j, imgTemp.getRGB(i + 1, j - 2));
                    } else if (i > 1 && j == 1 && i < w + 3) {
                        imgTemp.setRGB(i, j, imgTemp.getRGB(i, j + 1));
                    } else if (i == w + 3 && j == h + 3) {
                        imgTemp.setRGB(i, j, imgTemp.getRGB(i - 1, j - 1));
                    } else if (i == w + 3 && j == h + 4) {
                        imgTemp.setRGB(i, j, imgTemp.getRGB(i - 1, j - 2));
                    } else if (i == w + 4 && j > 0) {
                        imgTemp.setRGB(i, j, imgTemp.getRGB(i - 1, j));
                    } else if (i > 1 && j == h + 4) {
                        imgTemp.setRGB(i, j, imgTemp.getRGB(i, j - 1));
                    }
                }
            }

            for (i = 0; i < w; i++) {
                for (j = 0; j < h; j++) {
                    int scaledX = (int) (i * factor);
                    int scaledY = (int) (j * factor);
                    if (scaledX >= doubleImg.getWidth() || scaledY >= doubleImg.getHeight()) {
                        continue;
                    }
                    doubleImg.setRGB(scaledX, scaledY, img.getRGB(i, j));

                    Matrix mBic = new Matrix(4, 4);
                    int mRow = 0, mCol, elmt;
                    for (k = i - 1; k < i + 3; k++) {
                        mCol = 0;
                        for (l = j - 1; l < j + 3; l++) {
                            elmt = rgbGreen(imgTemp.getRGB(k + 2, l + 2));
                            mBic.setElmt(mRow, mCol, elmt);
                            mCol++;
                        }
                        mRow++;
                    }

                    Matrix mFunc = BicubicSI.matrixY(mBic);

                    int px = img.getRGB(i, j);
                    int alpha = rgbAlpha(px);
                    // interpolasi
                    double rTopCorner, lBotCorner, rBotCorner;
                    rTopCorner = BicubicSI.bicubic(mFunc, 0.5, 0);
                    lBotCorner = BicubicSI.bicubic(mFunc, 0, 0.5);
                    rBotCorner = BicubicSI.bicubic(mFunc, 0.5, 0.5);

                    int rTCVal = ((int) alpha << 24) | ((int) rTopCorner << 16) | ((int) rTopCorner << 8)
                            | ((int) rTopCorner);
                    int lBCVal = ((int) alpha << 24) | ((int) lBotCorner << 16) | ((int) lBotCorner << 8)
                            | ((int) lBotCorner);
                    int rBCVal = ((int) alpha << 24) | ((int) rBotCorner << 16) | ((int) rBotCorner << 8)
                            | ((int) rBotCorner);

                    if (scaledX + 1 < doubleImg.getWidth() && scaledY < doubleImg.getHeight()) {
                        doubleImg.setRGB(scaledX + 1, scaledY, rTCVal);
                    }
                    if (scaledX < doubleImg.getWidth() && scaledY + 1 < doubleImg.getHeight()) {
                        doubleImg.setRGB(scaledX, scaledY + 1, lBCVal);
                    }
                    if (scaledX + 1 < doubleImg.getWidth() && scaledY + 1 < doubleImg.getHeight()) {
                        doubleImg.setRGB(scaledX + 1, scaledY + 1, rBCVal);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Image tidak ditemukan");
        }

        System.out.print("Masukkan Nama File Output (berserta formatnya): ");
        sc.nextLine();
        fileOuput = sc.nextLine();
        printImage(fileOuput, doubleImg);
        System.out.println("\nImage Scaling - done");
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

        } catch (IOException e) {
            System.out.println("Error: " + e);
        }
    }

    public static int rgbAlpha(int pixel) {
        return (pixel >> 24) & 0xff;
    }

    public static int rgbGreen(int pixel) {
        return (pixel >> 8) & 0xff;
    }
}