package oop.ex6.methods;

import oop.ex6.main.sJavaExceptions;

public class WrongParameterType extends sJavaExceptions {

    private static final String MISSING_RETURN = "Error occurred calling a method with wrong parameter/s types";


    public WrongParameterType(){
        System.err.println(MISSING_RETURN);
    }
}
