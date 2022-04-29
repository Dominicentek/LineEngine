package com.line.object;

public class Line {
    public Vector vector1 = new Vector();
    public Vector vector2 = new Vector();
    public Line(int x1, int y1, int x2, int y2) {
        vector1.x = x1;
        vector1.y = y1;
        vector2.x = x2;
        vector2.y = y2;
    }
    public Line(Vector vector1, Vector vector2) {
        this.vector1 = vector1;
        this.vector2 = vector2;
    }
    public Line() {}
}
