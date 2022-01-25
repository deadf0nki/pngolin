package com.deadfonki.pngolin;

import java.awt.Color;
import java.awt.Image;
import java.awt.image.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

import javax.imageio.ImageIO;

public class Main {

    public static void main (String[] args) {

        Scanner input = new Scanner(System.in);



            System.out.print("Please choose an option to either encode or decode an image: \n" +
                    "To encode, type and enter 1 \n" +
                    "To decode, type and enter 2 \n");

            String option = input.nextLine().toUpperCase();


            try {
                if (option.equals("1")) {
                    System.out.println("Please provide full path to file, including file name:");

                    String filename = input.nextLine();
                    File inFile = new File(filename);

                    BufferedImage initImage = null;

                    initImage = ImageIO.read(inFile);

                    System.out.println("Please enter full path to directory you wish to save file in: ");
                    String path = input.nextLine();
                    System.out.println("Please enter the name of the output file you wish to save image as:");
                    String fileName = input.nextLine();
                    System.out.println("Please enter the message you wish to encode:");
                    String message = input.nextLine();

                    String bitMsg = Encode.encodeMessage(message);
                    BufferedImage newImage = Encode.encodeImage(bitMsg,initImage);

                    File dir = new File(path);
                    if (dir.exists() && dir.isDirectory() && dir.canWrite()) {
                        File finalImage = new File(dir, fileName);
                        finalImage.createNewFile();
                        ImageIO.write(newImage,"png",finalImage);
                        System.out.println("New image saved!");
                    } else {
                        throw new IOException("invalid");
                    }

                } else if (option.equals("2")) {
                    System.out.println("Please enter the full path of the image file you wish to decode:");
                    String filePath = input.nextLine();

                    File outFile = new File(filePath);
                    BufferedImage image = null;

                    image = ImageIO.read(outFile);

                    String bitMessage = Decode.decodeMessage(image);
                    System.out.println(Decode.getMessage(bitMessage));
                } else {
                    System.out.println("Command unknown, please try again");
                }
            } catch ( IOException e ) {
                System.out.println("File not found; " + e.getMessage());
            }

            System.out.println("Exiting program...");
            input.close();

    }
}