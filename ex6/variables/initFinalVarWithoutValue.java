package oop.ex6.variables;

import oop.ex6.main.sJavaExceptions;

public class initFinalVarWithoutValue extends sJavaExceptions {

    private static final String SCOPE_ERR = "initFinalVarWithoutValue";


    public initFinalVarWithoutValue(){
        System.err.println(SCOPE_ERR);
    }
}
