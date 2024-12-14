package oop.ex6.methods;

import oop.ex6.main.sJavaExceptions;

public class UndeclaredMethod extends sJavaExceptions {

    private static final String MISSING_RETURN = "Error occurred calling an undeclared method";


    public UndeclaredMethod(){
        System.err.println(MISSING_RETURN);
    }
}
