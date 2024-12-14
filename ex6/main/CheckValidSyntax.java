package oop.ex6.main;

public class CheckValidSyntax {


    public static boolean checkIfConstant(String value) {

        if (value.contains("'") || value.contains("\"")) {
            return true;
        }

        if (value.equals("true") || value.equals("false")) {

            return true;
        }

        try {
            Double.parseDouble(value);
        } catch (Exception e) {

            return false;
        }

        return true;
    }

    public static DataType checkConstantType(String value) {

        if (value.contains("'")) {
            return DataType.CHAR;
        }
        if (value.contains("\"")) {
            return DataType.STRING;
        }
        if (value.equals("true") || value.equals("false")) {

            return DataType.BOOLEAN;
        }

        if (value.contains(".")) {

            return DataType.DOUBLE;
        }

        return DataType.INT;
    }

    public static boolean checkTypeCompatibility
            (DataType variableType, DataType otherVarType) {
        if (variableType == DataType.BOOLEAN) {

            if (otherVarType != DataType.BOOLEAN &&
                    otherVarType != DataType.DOUBLE && otherVarType != DataType.INT) {
                return false;
            }
        }
        if (variableType == DataType.DOUBLE) {


            if (otherVarType != DataType.DOUBLE && otherVarType != DataType.INT) {
                return false;
            }
        }
        if (variableType == DataType.INT) {

            if (otherVarType != DataType.INT) {
                return false;
            }
        }
        if (variableType == DataType.CHAR) {

            if (otherVarType != DataType.CHAR) {
                return false;
            }
        }
        if (variableType == DataType.STRING) {

            if (otherVarType != DataType.STRING) {
                return false;
            }
        }

    return true;
    }

}
