package oop.ex6.methods;

import oop.ex6.main.sJavaExceptions;

public class WrongParameterTypeCount extends sJavaExceptions {

    private static final String MISSING_RETURN = "Error occurred called method with wrong parameters count";


    public WrongParameterTypeCount(){
        System.err.println(MISSING_RETURN);
    }
}
