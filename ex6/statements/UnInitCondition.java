package oop.ex6.statements;

import oop.ex6.main.sJavaExceptions;

public class UnInitCondition extends sJavaExceptions {

    private static final String MISSING_RETURN = "Error occurred not assigned condition inside an if/while statement ";


    public UnInitCondition(){
        System.err.println(MISSING_RETURN);
    }
}
