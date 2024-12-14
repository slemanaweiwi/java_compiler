package oop.ex6.methods;

import oop.ex6.main.sJavaExceptions;

public class methodCallFromGlobalScope extends sJavaExceptions {
    private static final String GLOBAL_CALL_ERR = "Error occurred after calling a function from the global scope ," +
            "you should call it inside of anther method!";

    public methodCallFromGlobalScope(){
        System.err.println(GLOBAL_CALL_ERR);
    }
}
