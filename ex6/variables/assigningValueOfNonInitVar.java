package oop.ex6.variables;

import oop.ex6.main.sJavaExceptions;

public class assigningValueOfNonInitVar extends sJavaExceptions {
    private static final String NOT_INITIALIZED_ASSIGNMENT = "Error occurred while trying to assign a variable " +
            "a value of a unassigned var";

    public assigningValueOfNonInitVar(){
        System.err.println(NOT_INITIALIZED_ASSIGNMENT);
    }
}
