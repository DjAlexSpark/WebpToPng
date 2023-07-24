package com.alexsoft.converter.webptopng;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Test {
    public static void main(String[] args) {

        if (Files.exists(Path.of(""))){
            System.out.println(true);
        }

//        try {
//            BufferedImage imageToRead = (ImageIO.read(new File("sample1.webp")));
//            ImageIO.write(imageToRead, "png", new File("sample1.png"));
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }

    }
}
