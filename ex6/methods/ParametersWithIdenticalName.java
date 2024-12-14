package oop.ex6.methods;

import oop.ex6.main.sJavaExceptions;

public class ParametersWithIdenticalName extends sJavaExceptions {
    private static final String IDENTICAL_PARAMETERS = "Error occurred after declaring a parameters with the same name";

    public ParametersWithIdenticalName(){
        System.err.println(IDENTICAL_PARAMETERS);
    }
}

