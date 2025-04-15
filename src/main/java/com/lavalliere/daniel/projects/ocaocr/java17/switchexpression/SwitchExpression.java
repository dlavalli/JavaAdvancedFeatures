package com.lavalliere.daniel.projects.ocaocr.java17.switchexpression;

public class SwitchExpression {

    public void standardCompare() {
        // The normal way to implement a switch
        Direction d = Direction.NORTH;
        int numLetters = 0;

        switch(d) {
            case NORTH:
            case SOUTH:
                numLetters = 5;
                break;
            case EAST:
            case WEST:
                numLetters = 4;
                break;
            default:
                numLetters = 0;
                break;
        }
    }

    public void compareWithSwitchExpression( Direction d) {

        // No break required with arrows
        int numLetter1 = switch(d) {
            case NORTH, SOUTH -> 5;
            case EAST, WEST -> 4;
            default -> 0;
        };

        // If you want to use  :  instead of  ->  you need to use yield
        // yield prevents fallthrough so no beak required
        int numLetter2 = switch(d) {
            case NORTH, SOUTH : yield 5;
            case EAST, WEST : yield 4;
            default : yield 0;
        };
    }
}