package org.acme.common;

public class Coord2D<X,Y> {

    private final X x;
    private final Y y;

    public Coord2D(X x, Y y) {
        if (x == null || y == null) throw new IllegalArgumentException("Coord2D cannot have null coordinates");

        this.x = x;
        this.y = y;
    }

    public X getx() {
        return x;
    }

    public Y gety() {
        return y;
    }

    @Override
    public int hashCode() {
        return x.hashCode() ^ y.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Coord2D)) return false;
        Coord2D<Integer, Integer> coord2Do = (Coord2D) o;
        return this.x.equals(coord2Do.getx()) &&
                this.y.equals(coord2Do.gety());
    }
}