package oop.ex6.main;


public class VariableDetails {


    private final String name;
    private DataType varType;
    private  boolean isInit;
    private final boolean isFinal;
    private final String assignedVarName;
    private final boolean isBeingAssigned;
    private final boolean assignedToConstant;

    public VariableDetails(String name, DataType varType, String assignedVarName,
                           boolean isInit, boolean isFinal, boolean isBeingAssigned, boolean assignedToConstant) {
        this.name = name;
        this.varType = varType;
        this.isInit = isInit;
        this.isFinal = isFinal;
        this.assignedVarName = assignedVarName;
        this.isBeingAssigned = isBeingAssigned;
        this.assignedToConstant = assignedToConstant;
    }

    public String getName() {
        return name;
    }

    public DataType getVarType() {
        return varType;
    }

    public boolean isInit() {
        return isInit;
    }

    public boolean isFinal() {
        return isFinal;
    }

    public String getAssignedVarName() {
        return assignedVarName;
    }

    public boolean isBeingAssigned() {
        return isBeingAssigned;
    }

    public boolean isAssignedToConstant() {
        return assignedToConstant;
    }

    public void setInit(boolean init) {
        isInit = init;
    }

    public void setVarType(DataType varType) {
        this.varType = varType;
    }
}
