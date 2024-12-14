package oop.ex6.variables;

import oop.ex6.main.sJavaExceptions;

public class initFinalWithoutValue extends sJavaExceptions {
    private static final String FINAL_SYNTAX_ERR = "Error occurred final variable declaration without assignment";

    public initFinalWithoutValue(){
        System.err.println(FINAL_SYNTAX_ERR);
    }
}
