package oop.ex6.variables;

import oop.ex6.main.sJavaExceptions;

public class doubleInitVar extends sJavaExceptions {
    private static final String DOUBLE_INITIALIZING_ERR = "Error occurred while trying to Initialize a variable that " +
            "is already initialized is the same scope.";

    public doubleInitVar(){
        System.err.println(DOUBLE_INITIALIZING_ERR);
    }
}
