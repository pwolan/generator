package org.example.helpers;

public record Vector2d(int x, int y) {

    @Override
    public boolean equals(Object other) {
        if (this == other)
            return true;
        if (!(other instanceof Vector2d that))
            return false;
        return that.x == x && that.y == y;
    }

    @Override
    public String toString() {
        return "(" + x + "," + y + ")";
    }


    public Vector2d add(Vector2d other) {
        int xx = other.x + x;
        int yy = other.y + y;
        return new Vector2d(xx, yy);
    }

    public boolean inBoundaries(Vector2d boundary){
        return x > 0 && y > 0 && x < boundary.x() && y < boundary.y();
    }
}

//    boolean precedes(Vector2d other) {
//        return other.x <= x && other.y <= y;
//    }
//
//    boolean follows(Vector2d other) {
//        return other.x >= x && other.y >= y;
//    }
//
//    Vector2d upperRight(Vector2d other) {
//        int xx = Math.max(other.x, x);
//        int yy = Math.max(other.y, y);
//        Vector2d out = new Vector2d(xx, yy);
//        return out;
//    }
//
//    Vector2d lowerLeft(Vector2d other) {
//        int xx = Math.min(other.x, x);
//        int yy = Math.min(other.y, y);
//        Vector2d out = new Vector2d(xx, yy);
//        return out;
//    }
//

//
//    Vector2d subtract(Vector2d other) {
//        int xx = x - other.x;
//        int yy = y - other.y;
//        return new Vector2d(xx, yy);
//    }
//
//    Vector2d opposite() {
//        return new Vector2d(-x, -y);
//    }