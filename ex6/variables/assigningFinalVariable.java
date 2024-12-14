package oop.ex6.variables;

import oop.ex6.main.sJavaExceptions;

public class assigningFinalVariable extends sJavaExceptions {
    private static final String FINAL_VARIABLE_ASSIGN = "Error occurred while trying to assign a final variable after" +
            " declaration.";

    public assigningFinalVariable(){
        System.err.println(FINAL_VARIABLE_ASSIGN);
    }
}
