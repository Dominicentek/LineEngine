package com.line.render;

import java.awt.Color;

public class ReadOnlyColor {
    private int r = 0;
    private int g = 0;
    private int b = 0;
    ReadOnlyColor(int r, int g, int b) {
        this.r = r;
        this.g = g;
        this.b = b;
    }
    public int getR() {
        return r;
    }
    public int getG() {
        return g;
    }
    public int getB() {
        return b;
    }
    public int getRGB() {
        return r * 65536 + g * 256 + b;
    }
    public Color toAWTColor() {
        return new Color(r, g, b);
    }
}
