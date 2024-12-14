package oop.ex6.methods;

import oop.ex6.main.DataType;
import oop.ex6.main.MethodDetails;
import oop.ex6.main.Sjavac;
import oop.ex6.main.VariableDetails;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MethodParser {


    private final static String METHOD_CALL_REG = "([a-zA-Z]\\w*)\\s*\\(\\s*" +
            "((\\d+\\.\\d*|\\d+|[_a-zA-Z]\\w*|'.?'|\".*\")\\s*" +
            "(\\s*,\\s*(\\d+\\.\\d*|\\d+|[_a-zA-Z]\\w*|'.?'|\".*\"))*)?\\s*\\)\\s*;";

    private final static String METHOD_SIGNATURE_REG = "void\\s+([a-zA-Z]+[a-zA-Z0-9_]*)\\s*" +
            "\\(\\s*((?:final\\s*)?\\s*(int|double|char|boolean|String)\\s+[_a-zA-Z]+[a-zA-Z0-9_]" +
            "*(\\s*,\\s*((final\\s*)?\\s*int|double|char|boolean|String)\\s+[_a-zA-Z]+[a-zA-Z0-9_]*)*)?\\s*\\)\\s*\\{";


    private final static Pattern methodInitPattern = Pattern.compile(METHOD_SIGNATURE_REG);

    private final static Pattern methodCallPattern = Pattern.compile(METHOD_CALL_REG);


    /**
     * check a valid syntax of declaring a method
     * @param line line
     * @return the details of the method if the syntax was valid and null otherwise
     */
    public static MethodDetails checkValidMethodInit(String line) {

        Matcher methodInitMatcher = methodInitPattern.matcher(line);

        boolean isMethodInit = methodInitMatcher.matches();

        if (!isMethodInit) {
            return null;
        }
        ArrayList<VariableDetails> parameters = new ArrayList<>();

        if (methodInitMatcher.group(2) == null) {

            return new MethodDetails(methodInitMatcher.group(1),parameters);
        }

        String methodName = methodInitMatcher.group(1);
        String methodPars = methodInitMatcher.group(2);
        String[] methodParsList = methodPars.split(",");

        for (String s : methodParsList) {

            String[] paramDetails = s.trim().split(" ");
            boolean isFinal;
            DataType varType;
            String varName;
            if (paramDetails.length == 3) {
                isFinal = true;
                varType = Sjavac.stringToDataType.get(paramDetails[1]);
                varName = paramDetails[2].trim();

            } else {
                isFinal = false;
                varType = Sjavac.stringToDataType.get(paramDetails[0]);
                varName = paramDetails[1];
            }

            VariableDetails variableDetails = new VariableDetails(varName, varType, null,
                    false, isFinal, false, false);

            parameters.add(variableDetails);

        }

        return new MethodDetails(methodName, parameters);
    }

    /**
     * checka valid syntax of a calling a method
     * @param line line
     * @return the details of the method if the syntax was valid and null otherwise
     */
    public static MethodDetails checkIfMethodCall(String line) {

        Matcher methodCallMatcher = methodCallPattern.matcher(line);

        if (!methodCallMatcher.matches()) {

            return null;
        }
        ArrayList<VariableDetails> parameters = new ArrayList<>();

        if(methodCallMatcher.group(2) == null) {
            return new MethodDetails(methodCallMatcher.group(1),parameters);
        }
        String methodName = methodCallMatcher.group(1);
        String methodPars = methodCallMatcher.group(2);
        String[] methodParsList = methodPars.split(",");

        for (String par : methodParsList) {

            par = par.replaceAll("\\s*", "");
            VariableDetails variableDetails = new VariableDetails(par, null,
                    null, false, false, false, false);

            parameters.add(variableDetails);


        }

        return new MethodDetails(methodName, parameters);
    }


}