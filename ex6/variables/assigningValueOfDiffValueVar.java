package oop.ex6.variables;

import oop.ex6.main.sJavaExceptions;

public class assigningValueOfDiffValueVar extends sJavaExceptions {
    private static final String WRONG_TYPE_MASSAGE = "Error occurred after trying to assign a specific type variable" +
            "with a different type value,the to variables should have the same type.";


    public assigningValueOfDiffValueVar(){
        System.err.println(WRONG_TYPE_MASSAGE);
    }
}
