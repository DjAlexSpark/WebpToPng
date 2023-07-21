package com.alexsoft.converter.webptopng;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Test2 {
    public static void main(String[] args) {
        try {
            BufferedImage imageToRead = ImageIO.read(new File("sample1.webp"));
            BufferedImage transparentImage =
                    new BufferedImage(imageToRead.getWidth(), imageToRead.getHeight(), BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2d = transparentImage.createGraphics();
            g2d.drawImage(imageToRead, 0, 0, null);
            g2d.dispose();
            ImageIO.write(transparentImage, "png", new File("sample1.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
