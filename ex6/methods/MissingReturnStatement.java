package oop.ex6.methods;

import oop.ex6.main.sJavaExceptions;

public class MissingReturnStatement extends sJavaExceptions {
    private static final String MISSING_RETURN = "Error occurred because the method has no return statement ";


    public MissingReturnStatement(){
        System.err.println(MISSING_RETURN);
    }
}
