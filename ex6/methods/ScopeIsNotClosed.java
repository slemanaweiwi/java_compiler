package oop.ex6.methods;

import oop.ex6.main.sJavaExceptions;

public class ScopeIsNotClosed extends sJavaExceptions {

    private static final String MISSING_RETURN = "Error occurred scope is not closed (missing '}')";

    public ScopeIsNotClosed(){
        System.err.println(MISSING_RETURN);
    }
}
