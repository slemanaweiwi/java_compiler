package oop.ex6.main;

import java.util.ArrayList;

public class MethodDetails {


    private final String name;
    private final ArrayList<VariableDetails> parameters;

    public MethodDetails(String name, ArrayList<VariableDetails> parameters) {


        this.name = name;
        this.parameters = parameters;
    }

    public String getName() {
        return name;
    }

    public ArrayList<VariableDetails> getParameters() {
        return parameters;
    }
}
