package oop.ex6.variables;

import oop.ex6.main.sJavaExceptions;

public class NonExistentConditionVar extends sJavaExceptions {

    private static final String MISSING_RETURN = "Error occurred non existent variable as" +
            " a condition inside an if/while statement";


    public NonExistentConditionVar(){
        System.err.println(MISSING_RETURN);
    }
}
