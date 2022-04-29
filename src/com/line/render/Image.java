package com.line.render;

import java.awt.image.BufferedImage;

public class Image {
    private ReadOnlyColor[][] colorData;
    public Image(int width, int height) {
        colorData = new ReadOnlyColor[width][height];
    }
    void setRGB(int x, int y, ReadOnlyColor color) {
        colorData[x][y] = color;
    }
    public ReadOnlyColor getRGB(int x, int y) {
        return colorData[x][y];
    }
    public BufferedImage toBufferedImage() {
        BufferedImage img = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x < getWidth(); x++) {
            for (int y = 0; y < getHeight(); y++) {
                img.setRGB(x, y, colorData[x][y].getRGB());
            }
        }
        return img;
    }
    public int getWidth() {
        return colorData.length;
    }
    public int getHeight() {
        return colorData[0].length;
    }
}
