package com.line.render;

import com.line.object.*;

import java.awt.*;

public class Renderer {
    private int width;
    private int height;
    private Image image;
    private ReadOnlyColor currentColor;
    private double x;
    private double y;
    public Renderer(int width, int height) {
        this.width = width;
        this.height = height;
        image = new Image(width, height);
        currentColor = new ReadOnlyColor(255, 255, 255);
        clear(0, 0, 0);
    }
    public void clear(Color color) {
        clear(color.getRed(), color.getGreen(), color.getGreen());
    }
    public void clear(int r, int g, int b) {
        ReadOnlyColor roc = new ReadOnlyColor(r, g, b);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                image.setRGB(x, y, roc);
            }
        }
    }
    public void clear(int rgb) {
        clear((rgb & 0xFF0000) >> 16, (rgb & 0xFF00) >> 8, rgb & 0xFF);
    }
    public Image getImage() {
        return image;
    }
    public void setColor(Color color) {
        setColor(color.getRed(), color.getGreen(), color.getBlue());
    }
    public void setColor(int rgb) {
        setColor((rgb & 0xFF0000) >> 16, (rgb & 0xFF00) >> 8, rgb & 0xFF);
    }
    public void setColor(int r, int g, int b) {
        currentColor = new ReadOnlyColor(r, g, b);
    }
    public ReadOnlyColor getColor() {
        return currentColor;
    }
    public void setAbsolutePosition(double x, double y) {
        this.x = x;
        this.y = y;
    }
    public void setAbsolutePosition(Vector vector) {
        setAbsolutePosition(vector.x, vector.y);
    }
    public void setRelativePosition(double x, double y) {
        setAbsolutePosition(this.x + x, this.y + y);
    }
    public void setRelativePosition(Vector vector) {
        setRelativePosition(vector.x, vector.y);
    }
    public void drawLine(double x, double y) {
        double width = Math.abs(x - this.x);
        double height = Math.abs(y - this.y);
        double minX = Math.min(x, this.x);
        double minY = Math.min(y, this.y);
        double pixX = minX;
        double pixY = minY;
        boolean invert = false;
        if (x <= this.x && y > this.y) invert = true;
        if (x > this.x && y <= this.y) invert = true;
        if (width >= height) {
            double step = height / width;
            for (int i = 0; i < width; i++) {
                double destX = invert ? width - 1 - i + minX : pixX;
                double destY = pixY;
                if (destX >= 0 && destY >= 0 && destX < this.width && destY < this.height) image.setRGB((int)destX, (int)destY, currentColor);
                pixX++;
                pixY += step;
            }
        }
        else {
            double step = width / height;
            for (int i = 0; i < height; i++) {
                double destX = pixX;
                double destY = invert ? height - 1 - i + minY : pixY;
                if (destX >= 0 && destY >= 0 && destX < this.width && destY < this.height) image.setRGB((int)destX, (int)destY, currentColor);
                pixX += step;
                pixY++;
            }
        }
    }
    public void drawLine(Vector destination) {
        drawLine(destination.x, destination.y);
    }
    public void drawObject(LineObject object) {
        for (Line line : object.getLines()) {
            setAbsolutePosition(line.vector1);
            drawLine(line.vector2);
        }
    }
    public void drawObject(LineObject object, double offsetX, double offsetY) {
        object.move(offsetX, offsetY);
        drawObject(object);
        object.move(-offsetX, -offsetY);
    }
    public double getPositionX() {
        return x;
    }
    public double getPositionY() {
        return y;
    }
    public Vector getPosition() {
        return new Vector(x, y);
    
}
