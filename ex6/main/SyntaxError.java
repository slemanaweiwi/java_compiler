package oop.ex6.main;

public class SyntaxError extends sJavaExceptions {

    private static final String NOT_INITIALIZED_PARAMETERS = "Error occurred syntax error";

    public SyntaxError(){
        System.err.println(NOT_INITIALIZED_PARAMETERS);
    }
}
