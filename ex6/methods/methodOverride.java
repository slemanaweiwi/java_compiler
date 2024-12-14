package oop.ex6.methods;

import oop.ex6.main.sJavaExceptions;

public class methodOverride extends sJavaExceptions {
    private static final String OVERRIDE_METHOD_ERR = "Error occurred after trying to override a method";

    public methodOverride(){
        System.err.println(OVERRIDE_METHOD_ERR);
    }
}
