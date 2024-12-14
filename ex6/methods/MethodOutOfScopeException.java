package oop.ex6.methods;

import oop.ex6.main.sJavaExceptions;

public class MethodOutOfScopeException extends sJavaExceptions {
    private static final String SCOPE_ERR = "Error occurred declaring a method within an interior scope";


    public MethodOutOfScopeException(){
        System.err.println(SCOPE_ERR);
    }
}
