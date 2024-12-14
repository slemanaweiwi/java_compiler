package oop.ex6.variables;

import oop.ex6.main.sJavaExceptions;

public class wrongAssignedType extends sJavaExceptions {
    private static final String WRONG_ASSIGNED_TYPE ="Error occurred while trying to assign a wrong type  " ;

    public wrongAssignedType(){
        System.err.println(WRONG_ASSIGNED_TYPE);
    }
}
