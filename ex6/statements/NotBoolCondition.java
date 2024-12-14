package oop.ex6.statements;

import oop.ex6.main.sJavaExceptions;

public class NotBoolCondition extends sJavaExceptions {

    private static final String MISSING_RETURN = "Error occurred not boolean type condition ";


    public NotBoolCondition(){
        System.err.println(MISSING_RETURN);
    }
}
