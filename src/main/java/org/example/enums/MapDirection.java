package org.example.enums;


import org.example.helpers.Vector2d;


public enum MapDirection {
    NORTH,
    NORTHWEST,
    WEST,
    SOUTHWEST,
    SOUTH,
    SOUTHEAST,
    EAST,
    NORTHEAST;
    private static final MapDirection[] values = values();

    public MapDirection rotate(int gene){
        int newDirectionInt = (this.ordinal() + gene) % 8;
        return MapDirection.values[newDirectionInt];
    }

    public Vector2d toUnitVector(){
        return switch (this){
            case NORTH -> new Vector2d(0,1);
            case NORTHWEST -> new Vector2d(-1, 1);
            case WEST -> new Vector2d(-1, 0);
            case SOUTHWEST -> new Vector2d(-1, -1);
            case SOUTH -> new Vector2d(0,-1);
            case SOUTHEAST -> new Vector2d(1, -1);
            case EAST -> new Vector2d(1,0);
            case NORTHEAST -> new Vector2d(1, 1);
        };
    }
    public static MapDirection getRandom(){
        MapDirection[] directions = MapDirection.values;
        int n = (int)(Math.random()*8);
        return directions[n];
    }
    @Override
    public String toString() {
        return switch (this){
            case NORTH -> "Pn";
            case NORTHWEST -> "Pn-Z";
            case WEST -> "Zach";
            case SOUTHWEST -> "Pd-Z";
            case SOUTH -> "Pd";
            case SOUTHEAST -> "Pd-W";
            case EAST -> "Wsch";
            case NORTHEAST -> "Pn-W";
        };
    }


}

