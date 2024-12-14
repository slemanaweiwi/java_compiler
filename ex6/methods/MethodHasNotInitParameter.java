package oop.ex6.methods;

import oop.ex6.main.sJavaExceptions;

public class MethodHasNotInitParameter extends sJavaExceptions {
    private static final String NOT_INITIALIZED_PARAMETERS = "Error occurred after calling a method with " +
            "unassigned variable ";

    public MethodHasNotInitParameter(){
        System.err.println(NOT_INITIALIZED_PARAMETERS);
    }
}