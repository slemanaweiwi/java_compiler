package oop.ex6.methods;

import oop.ex6.main.sJavaExceptions;

public class MethodHasNotExistentParameter extends sJavaExceptions {

    private static final String NOT_EXISTENT_PARAMETER ="Error occurred after calling" +
            " a method with not existed variable";

    public MethodHasNotExistentParameter(){
        System.err.println(NOT_EXISTENT_PARAMETER);
    }
}
