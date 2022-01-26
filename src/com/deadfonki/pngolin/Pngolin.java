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

public class Pngolin {

    public static void Encode(String input, String outputDir, String output, String content) {
        try {
            String filename = input;
            File inFile = new File(filename);

            BufferedImage initImage = null;

            initImage = ImageIO.read(inFile);


            String path = outputDir;
            String fileName = output;
            String message = content;

            String bitMsg = Encode.encodeMessage(message);
            BufferedImage newImage = Encode.encodeImage(bitMsg, initImage);

            File dir = new File(path);
            if (dir.exists() && dir.isDirectory() && dir.canWrite()) {
                File finalImage = new File(dir, fileName);
                finalImage.createNewFile();
                ImageIO.write(newImage, "png", finalImage);
            } else {
                throw new IOException("invalid");
            }
        } catch (IOException e) {
            System.out.println("File not found; " + e.getMessage());
        }
    }

    public static String Decode(String file) {
        try {
            String filePath = file;

            File outFile = new File(filePath);
            BufferedImage image = null;

            image = ImageIO.read(outFile);

            String bitMessage = Decode.decodeMessage(image);
            return Decode.getMessage(bitMessage);
        } catch (IOException e) {
            System.out.println("File not found; " + e.getMessage());
        }
        return "failed";
    }
}