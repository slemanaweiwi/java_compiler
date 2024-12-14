package oop.ex6.main;

public class IllegalComment extends sJavaExceptions {

    private static final String MISSING_RETURN = "Error occurred illegal comment only (//comment) format is allowed";


    public IllegalComment(){
        System.err.println(MISSING_RETURN);
    }
}
