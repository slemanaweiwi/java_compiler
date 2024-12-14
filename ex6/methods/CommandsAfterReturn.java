package oop.ex6.methods;

import oop.ex6.main.sJavaExceptions;

public class CommandsAfterReturn extends sJavaExceptions {

    private static final String MISSING_RETURN = "Error occurred no commands are allowed after return statement ";


    public CommandsAfterReturn(){
        System.err.println(MISSING_RETURN);
    }
}
