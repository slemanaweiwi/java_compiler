package oop.ex6.variables;

import oop.ex6.main.sJavaExceptions;

public class initVarWithoutType extends sJavaExceptions {
    private static final String NOT_INITIALIZED_VALUE = "ERROR occurred because the new declared variable has no type";


    public initVarWithoutType(){
        System.err.println(NOT_INITIALIZED_VALUE);
    }
}
