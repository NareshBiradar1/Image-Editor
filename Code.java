import java.io.File;
import java.lang.*;
import java.util.*;
import java.awt.*;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.awt.image.BufferedImage;

public class Code {

    // FUNCTION TO INCREASE BRIGHTNESS OF IMAGE

    public static BufferedImage changeBrightness(BufferedImage input, int a) {
        int height = input.getHeight();
        int width = input.getWidth();
        BufferedImage output = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                Color pixel = new Color(input.getRGB(j, i));
                int red = pixel.getRed();
                int green = pixel.getGreen();
                int blue = pixel.getBlue();
                red += (a * red) / 100;
                green += (a * green) / 100;
                blue += (a * blue) / 100;
                if (red > 255)
                    red = 255;
                if (green > 255)
                    green = 255;
                if (blue > 255)
                    blue = 255;
                if (red < 0)
                    red = 0;
                if (green < 0)
                    green = 0;
                if (blue < 0)
                    blue = 0;
                Color newpixel = new Color(red, green, blue);
                output.setRGB(j, i, newpixel.getRGB());
            }
        }
        return output;
    }

    // FUNCTION TO CREATE PRINTPIXELS

    public static void printpixels(BufferedImage inputImage) {
        int height = inputImage.getHeight();
        int width = inputImage.getWidth();
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                Color pixelvalue = new Color(inputImage.getRGB(j, i));
                System.out.print(
                        "[" + pixelvalue.getRed() + " " + pixelvalue.getGreen() + " " + pixelvalue.getBlue() + " ]");
            }
        }
    }

    // FUNCTION TO CONVERT IMAGE TO GRAY-IMAGE

    public static BufferedImage converttogray(BufferedImage input) {
        int height = input.getHeight();
        int width = input.getWidth();
        BufferedImage output = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_GRAY);
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                output.setRGB(j, i, input.getRGB(j, i));
            }
        }
        return output;
    }
    // FUNCTION TO REOTATE IMAGE TOWARDS RIGHT

    public static BufferedImage rightRotate(BufferedImage input) {
        int height = input.getHeight();
        int width = input.getWidth();
        BufferedImage output = new BufferedImage(height, width, BufferedImage.TYPE_3BYTE_BGR);
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                output.setRGB(i, j, input.getRGB(j, i));
            }
        }
        int cols = output.getWidth() - 1;
        for (int i = 0; i < output.getHeight(); i++) {
            for (int j = 0; j < (output.getWidth() / 2); j++) {
                Color pixel = new Color(output.getRGB(j, i));
                output.setRGB(j, i, output.getRGB(cols - j, i));
                output.setRGB(cols - j, i, pixel.getRGB());
            }
        }
        return output;
    }
    // FUNCTION TO ROTATE IMAGE TOWARDS LEFT

    public static BufferedImage leftRotate(BufferedImage input) {
        int height = input.getHeight();
        int width = input.getWidth();
        BufferedImage output = new BufferedImage(height, width, BufferedImage.TYPE_3BYTE_BGR);
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                output.setRGB(i, j, input.getRGB(j, i));
            }
        }
        int rows = output.getHeight() - 1;
        for (int j = 0; j < output.getWidth(); j++) {
            for (int i = 0; i < output.getHeight() / 2; i++) {
                Color pixel = new Color(output.getRGB(j, i));
                output.setRGB(j, i, output.getRGB(j, rows - i));
                output.setRGB(j, rows - i, pixel.getRGB());
            }
        }
        return output;
    }

    // FUNCTION TO VERTICALLY INVERT THE IMAGE

    public static BufferedImage verticallyInvert(BufferedImage input) {
        int height = input.getHeight();
        int width = input.getWidth();
        BufferedImage output = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
        for (int j = 0; j < width; j++) {
            for (int i = 0; i < height; i++) {
                output.setRGB(j, height - 1 - i, input.getRGB(j, i));
            }
        }
        return output;
    }

    // FUNCTION TO HORIZANTALLY INVERT THE IMAGE

    public static BufferedImage horizantallyInvert(BufferedImage input) {
        int height = input.getHeight();
        int width = input.getWidth();
        BufferedImage output = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                output.setRGB(width - 1 - j, i, input.getRGB(j, i));
            }
        }
        return output;
    }

    // FUNCTION TO BLURR THE IMAGE
    public static BufferedImage blurrImage(BufferedImage input, int pixel) {
        int height = input.getHeight();
        int width = input.getWidth();
        BufferedImage ans = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
        for (int i = 0; i < (height / pixel); i++) {
            for (int j = 0; j < (width / pixel); j++) {
                int red = 0;
                int blue = 0;
                int green = 0;
                for (int x = i * pixel; x < (i + 1) * pixel; x++) {
                    for (int y = j * pixel; y < (j + 1) * pixel; y++) {
                        Color pixelValue = new Color(input.getRGB(y, x));
                        red += pixelValue.getRed();
                        green += pixelValue.getGreen();
                        blue += pixelValue.getBlue();
                    }
                }

                red /= (pixel * pixel);
                green /= (pixel * pixel);
                blue /= (pixel * pixel);

                for (int x = i * pixel; x < (i + 1) * pixel; x++) {
                    for (int y = j * pixel; y < (j + 1) * pixel; y++) {
                        Color newPixel = new Color(red, green, blue);
                        ans.setRGB(y, x, newPixel.getRGB());

                    }
                }
            }

        }
        return ans;
    }

    // FUNCTION TO BLUE-FILTER THE IMAGE

    public static BufferedImage blueFilter(BufferedImage input) {
        int height = input.getHeight();
        int width = input.getWidth();
        BufferedImage output = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                Color pixel = new Color(input.getRGB(j, i));
                int red = pixel.getRed();
                int green = pixel.getGreen();
                int blue = pixel.getBlue();
                red = 0;
                green = 0;

                pixel = new Color(red, green, blue);
                output.setRGB(j, i, pixel.getRGB());
            }
        }
        return output;
    }

    // FUNCTION TO RED-FILTER THE IMAGE

    public static BufferedImage redFilter(BufferedImage input) {
        int height = input.getHeight();
        int width = input.getWidth();
        BufferedImage output = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                Color pixel = new Color(input.getRGB(j, i));
                int red = pixel.getRed();
                int green = pixel.getGreen();
                int blue = pixel.getBlue();
                blue = 0;
                green = 0;

                pixel = new Color(red, green, blue);
                output.setRGB(j, i, pixel.getRGB());
            }
        }
        return output;
    }

    // FUNCTION TO GREEN-FILTER THE IMAGE

    public static BufferedImage greenFilter(BufferedImage input) {
        int height = input.getHeight();
        int width = input.getWidth();
        BufferedImage output = new BufferedImage(width, height,
                BufferedImage.TYPE_3BYTE_BGR);
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                Color pixel = new Color(input.getRGB(j, i));
                int red = pixel.getRed();
                int green = pixel.getGreen();
                int blue = pixel.getBlue();
                red = 0;
                blue = 0;

                Color pixelValue = new Color(red, green, blue);
                output.setRGB(j, i, pixelValue.getRGB());
            }
        }
        return output;
    }

    // FUNCTION TO CROP-IMAGE FROM TOP

    public static BufferedImage cropTop(BufferedImage input, int pixels) {
        int height = input.getHeight();
        int width = input.getWidth();
        BufferedImage output = new BufferedImage(width, height - pixels, BufferedImage.TYPE_3BYTE_BGR);
        for (int i = pixels; i < height; i++) {
            for (int j = 0; j < width; j++) {
                output.setRGB(j, i - pixels, input.getRGB(j, i));
            }

        }
        return output;
    }

    // FUNCTION TO CROP-IMAGE FROM BOTTOM

    public static BufferedImage cropBottom(BufferedImage input, int pixels) {
        int height = input.getHeight();
        int width = input.getWidth();
        BufferedImage output = new BufferedImage(width, height - pixels, BufferedImage.TYPE_3BYTE_BGR);
        for (int i = 0; i < height - pixels; i++) {
            for (int j = 0; j < width; j++) {
                output.setRGB(j, i, input.getRGB(j, i));
            }

        }
        return output;
    }

    // FUNCTION TO CROP-IMAGE FROM LEFT

    public static BufferedImage cropLeft(BufferedImage input, int pixels) {
        int height = input.getHeight();
        int width = input.getWidth();
        BufferedImage output = new BufferedImage(width - pixels, height, BufferedImage.TYPE_3BYTE_BGR);
        for (int i = 0; i < height; i++) {
            for (int j = pixels; j < width; j++) {
                output.setRGB(j - pixels, i, input.getRGB(j, i));
            }

        }
        return output;
    }

     // FUNCTION TO CROP-IMAGE FROM RIGHT
    public static BufferedImage cropRight(BufferedImage input, int pixels) {
        int height = input.getHeight();
        int width = input.getWidth();
        BufferedImage output = new BufferedImage(width - pixels, height, BufferedImage.TYPE_3BYTE_BGR);
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width - pixels; j++) {
                output.setRGB(j, i, input.getRGB(j, i));
            }

        }
        return output;
    }

     // FUNCTION TO CROP-IMAGE VERTICALLY

    public static BufferedImage cropVertical(BufferedImage input, int pixels) {
        int height = input.getHeight();
        int width = input.getWidth();
        BufferedImage output = new BufferedImage(width, height - (2 * pixels), BufferedImage.TYPE_3BYTE_BGR);
        for (int i = pixels; i < height - pixels; i++) {
            for (int j = 0; j < width - pixels; j++) {
                output.setRGB(j, i - pixels, input.getRGB(j, i));
            }

        }
        return output;
    }

    // FUNCTION TO CROP-IMAGE HORIZANTALLY
    public static BufferedImage cropHorizontal(BufferedImage input, int pixels) {
        int height = input.getHeight();
        int width = input.getWidth();
        BufferedImage output = new BufferedImage(width - (2 * pixels), height, BufferedImage.TYPE_3BYTE_BGR);
        for (int i = 0; i < height; i++) {
            for (int j = pixels; j < width - pixels; j++) {
                output.setRGB(j - pixels, i, input.getRGB(j, i));
            }

        }
        return output;
    }

    // FUNCTION TO CROP-IMAGE FROM ALL DIRECTIOS
    public static BufferedImage cropAll(BufferedImage input, int pixels) {
        int height = input.getHeight();
        int width = input.getWidth();
        BufferedImage output = new BufferedImage(width - (2 * pixels), height - (2 * pixels),
                BufferedImage.TYPE_3BYTE_BGR);
        for (int i = pixels; i < height - pixels; i++) {
            for (int j = pixels; j < width - pixels; j++) {
                output.setRGB(j - pixels, i - pixels, input.getRGB(j, i));
            }

        }
        return output;
    }

     // FUNCTION FOR COLOUR INVERSION

    public static BufferedImage invertColors(BufferedImage input) {
        int height = input.getHeight();
        int width = input.getWidth();
        BufferedImage output = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                Color pixel = new Color(input.getRGB(j, i));
                int red = pixel.getRed();
                int blue = pixel.getBlue();
                int green = pixel.getGreen();
                int finalred = 255 - red;
                int finalblue = 255 - blue;
                int finalgreen = 255 - green;
                pixel = new Color(finalred, finalblue, finalgreen);
                output.setRGB(j, i, pixel.getRGB());
            }
        }
        return output;
    }

   

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("Enter the path of image file");
        String s = sc.nextLine();

        File inputFile = new File(s);
        try {
            BufferedImage inputImage = ImageIO.read(inputFile);
            System.out.println("Height of Image is" + inputImage.getHeight());
            System.out.println("Width of Image is" + inputImage.getWidth());
            System.out.println(
                    "enter 1 to print pixels \nenter 2 to get grayimage \nenter 3 to increase brightness \nenter 4 to rotate the image\n enter 5 to vertically invert the Image \n enter 6 to horizantally invert the image \n enter 7 to blurr the image \n enter 8 to apply colour filter to image\n press 9 to crop the Image \n press 10 for Colour Inversion");
            int todo = sc.nextInt();
            if (todo == 1)
                printpixels(inputImage);

            else if (todo == 2) {
                BufferedImage outputImage = converttogray(inputImage);
                File output = new File("output.jpg");
                ImageIO.write(outputImage, "jpg", output);
            } else if (todo == 3) {
                System.out.println("By how much percent you want to change the brightness");
                int a = sc.nextInt();
                BufferedImage brighterimage = changeBrightness(inputImage, a);
                File output = new File("output.jpg");
                ImageIO.write(brighterimage, "jpg", output);
            }

            else if (todo == 4) {
                System.out.println("enter 1 to rotate towards right and enter 2 to rotate towards left");
                int x = sc.nextInt();
                if (x == 1) {
                    BufferedImage outputImage = rightRotate(inputImage);
                    File output = new File("output.jpg");
                    ImageIO.write(outputImage, "jpg", output);

                } else if (x == 2) {
                    BufferedImage outputImage = leftRotate(inputImage);
                    File output = new File("output.jpg");
                    ImageIO.write(outputImage, "jpg", output);
                }
            } else if (todo == 5) {
                BufferedImage outputImage = verticallyInvert(inputImage);
                File output = new File("output.jpg");
                ImageIO.write(outputImage, "jpg", output);
            } else if (todo == 6) {
                BufferedImage outputImage = horizantallyInvert(inputImage);
                File output = new File("output.jpg");
                ImageIO.write(outputImage, "jpg", output);
            } else if (todo == 7) {
                System.out.println("by how much pixels , you want to blurr the image");
                int k = sc.nextInt();
                BufferedImage outputImage = blurrImage(inputImage, k);
                File output = new File("output.jpg");
                ImageIO.write(outputImage, "jpg", output);
            } else if (todo == 8) {
                System.out.println("Press 1 for REd-Filter \n Press 2 for BLUE-Filter \n Press 3 for Green-Filter ");
                int x = sc.nextInt();
                switch (x) {
                    case 1: {
                        BufferedImage outputImage = redFilter(inputImage);
                        File output = new File("output.jpg");
                        ImageIO.write(outputImage, "jpg", output);
                    }
                        break;
                    case 2: {
                        BufferedImage outputImage = blueFilter(inputImage);
                        File output = new File("output.jpg");
                        ImageIO.write(outputImage, "jpg", output);
                    }
                        break;
                    case 3: {
                        BufferedImage outputImage = greenFilter(inputImage);
                        File output = new File("output.jpg");
                        ImageIO.write(outputImage, "jpg", output);
                    }
                        break;

                    default:
                        System.out.println("invalid input");
                        break;
                }
            } else if (todo == 9) {
                System.out.println(
                        "press 1 to crop from top\n pree 2 to crop from bottom \n press 3 to crop from left\n press 4 to crop from right\n press 5 to crop from top and bottom\n press 6 to crop from left and right \n press 7 to crop from all directions");
                int x = sc.nextInt();
                switch (x) {
                    case 1: {
                        System.out.println("By how many pixels you want to crop");
                        int p = sc.nextInt();
                        BufferedImage outputImage = cropTop(inputImage, p);
                        File output = new File("output.jpg");
                        ImageIO.write(outputImage, "jpg", output);
                    }
                        break;
                    case 2: {
                        System.out.println("By how many pixels you want to crop");
                        int p = sc.nextInt();
                        BufferedImage outputImage = cropBottom(inputImage, p);
                        File output = new File("output.jpg");
                        ImageIO.write(outputImage, "jpg", output);
                    }
                        break;
                    case 3: {
                        System.out.println("By how many pixels you want to crop");
                        int p = sc.nextInt();
                        BufferedImage outputImage = cropLeft(inputImage, p);
                        File output = new File("output.jpg");
                        ImageIO.write(outputImage, "jpg", output);
                    }
                        break;
                    case 4: {
                        System.out.println("By how many pixels you want to crop");
                        int p = sc.nextInt();
                        BufferedImage outputImage = cropRight(inputImage, p);
                        File output = new File("output.jpg");
                        ImageIO.write(outputImage, "jpg", output);
                    }
                        break;
                    case 5: {
                        System.out.println("By how many pixels you want to crop");
                        int p = sc.nextInt();
                        BufferedImage outputImage = cropVertical(inputImage, p);
                        File output = new File("output.jpg");
                        ImageIO.write(outputImage, "jpg", output);
                    }
                        break;
                    case 6: {
                        System.out.println("By how many pixels you want to crop");
                        int p = sc.nextInt();
                        BufferedImage outputImage = cropHorizontal(inputImage, p);
                        File output = new File("output.jpg");
                        ImageIO.write(outputImage, "jpg", output);
                    }
                        break;
                    case 7: {
                        System.out.println("By how many pixels you want to crop");
                        int p = sc.nextInt();
                        BufferedImage outputImage = cropAll(inputImage, p);
                        File output = new File("output.jpg");
                        ImageIO.write(outputImage, "jpg", output);
                    }
                        break;
                }
            } else if (todo == 10) {
                BufferedImage outputImage = invertColors(inputImage);
                File output = new File("output.jpg");
                ImageIO.write(outputImage, "jpg", output);
            }

        }

        catch (Exception e) {
            // TODO: handle exception
        }
    }
}