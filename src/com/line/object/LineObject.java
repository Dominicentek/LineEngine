package com.line.object;

import java.util.ArrayList;

public class LineObject {
    private ArrayList<Line> lines = new ArrayList<>();
    public void addLine(Line line) {
        lines.add(line);
    }
    public void removeLine(Line line) {
        lines.remove(line);
    }
    public void removeAll() {
        lines.clear();
    }
    public int getLineAmount() {
        return lines.size();
    }
    public Line[] getLines() {
        Line[] lines = new Line[this.lines.size()];
        for (int i = 0; i < lines.length; i++) {
            lines[i] = this.lines.get(i);
        }
        return lines;
    }
    public void move(double x, double y) {
        for (Line line : lines) {
            line.vector1.x += x;
            line.vector1.y += y;
            line.vector2.x += x;
            line.vector2.y += y;
        }
    }
    public void scale(double scale) {
        Vector topLeft = getTopLeft();
        Vector bottomRight = getBottomRight();
        double offsetX = ((bottomRight.x - topLeft.x) * (scale - 1)) / 2;
        double offsetY = ((bottomRight.y - topLeft.y) * (scale - 1)) / 2;
        for (Line line : lines) {
            line.vector1.x = map(line.vector1.x, topLeft.x, bottomRight.x, topLeft.x - offsetX, bottomRight.x + offsetX);
            line.vector1.y = map(line.vector1.y, topLeft.y, bottomRight.y, topLeft.y - offsetY, bottomRight.y + offsetY);
            line.vector2.x = map(line.vector2.x, topLeft.x, bottomRight.x, topLeft.x - offsetX, bottomRight.x + offsetX);
            line.vector2.y = map(line.vector2.y, topLeft.y, bottomRight.y, topLeft.y - offsetY, bottomRight.y + offsetY);
        }
    }
    public void rotate(double angle) {
        Vector center = getCenter();
        rotate(angle, center.x, center.y);
    }
    public void rotate(double angle, Vector origin) {
        rotate(angle, origin.x, origin.y);
    }
    public void rotate(double angle, double originX, double originY) {
        for (Line line : lines) {
            line.vector1 = rotateVector(line.vector1, originX, originY, angle);
            line.vector2 = rotateVector(line.vector2, originX, originY, angle);
        }
    }
    public Vector getCenter() {
        Vector topLeft = getTopLeft();
        Vector bottomRight = getBottomRight();
        return new Vector((bottomRight.x - topLeft.x) / 2 + topLeft.x, (bottomRight.y - topLeft.y) / 2 + topLeft.y);
    }
    public Vector getTopLeft() {
        Vector vector = new Vector(Integer.MAX_VALUE, Integer.MAX_VALUE);
        for (Line line : lines) {
            if (line.vector1.x < vector.x) vector.x = line.vector1.x;
            if (line.vector1.y < vector.y) vector.y = line.vector1.y;
            if (line.vector2.x < vector.x) vector.x = line.vector2.x;
            if (line.vector2.y < vector.y) vector.y = line.vector2.y;
        }
        return vector;
    }
    public Vector getBottomRight() {
        Vector vector = new Vector(Integer.MIN_VALUE, Integer.MIN_VALUE);
        for (Line line : lines) {
            if (line.vector1.x > vector.x) vector.x = line.vector1.x;
            if (line.vector1.y > vector.y) vector.y = line.vector1.y;
            if (line.vector2.x > vector.x) vector.x = line.vector2.x;
            if (line.vector2.y > vector.y) vector.y = line.vector2.y;
        }
        return vector;
    }
    private double map(double x, double srcMin, double srcMax, double dstMin, double dstMax) {
        return (x - srcMin) / (srcMax - srcMin) * (dstMax - dstMin) + dstMin;
    }
    private Vector rotateVector(Vector vector, double originX, double originY, double angle) {
        Vector rotated = new Vector(vector.x, vector.y);
        double rad = Math.toRadians(angle);
        double cos = Math.cos(rad);
        double sin = Math.sin(rad);
        rotated.x -= originX;
        rotated.y -= originY;
        double rotatedX = rotated.x * cos - rotated.y * sin;
        double rotatedY = rotated.x * sin + rotated.y * cos;
        rotated.x = rotatedX + originX;
        rotated.y = rotatedY + originY;
        return rotated;
    }
}
