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

//    public MapDirection next(){
//        return switch (this){
//            case NORTH -> EAST;
//            case WEST -> NORTH;
//            case SOUTH -> WEST;
//            case EAST -> SOUTH;
//        };
//    }
//
//    public MapDirection previous(){
//        return switch (this){
//            case EAST -> NORTH;
//            case NORTH -> WEST;
//            case WEST -> SOUTH;
//            case SOUTH -> EAST;
//        };
//    }

    Vector2d toUnitVector(){
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

