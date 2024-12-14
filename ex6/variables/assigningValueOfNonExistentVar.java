package oop.ex6.variables;

import oop.ex6.main.sJavaExceptions;

public class assigningValueOfNonExistentVar extends sJavaExceptions {
    private static final String NON_EXISTENT_VAR = "Error occurred because of assigning value to non existent var. ";


    public assigningValueOfNonExistentVar(){
        System.err.println(NON_EXISTENT_VAR);
    }
}
