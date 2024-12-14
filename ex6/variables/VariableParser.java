package oop.ex6.variables;

import oop.ex6.main.CheckValidSyntax;
import oop.ex6.main.DataType;
import oop.ex6.main.Sjavac;
import oop.ex6.main.VariableDetails;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Parse  the Variables if they are in the correct form and returns the right variable details for it .
 */
public class VariableParser {
    private final static String ASSIGN_VAR = "(final )?(boolean|int|char|double|String)?" +
            " (_\\w+|[a-zA-Z]\\w*) ?= ?('.?'|\".*\"|(?:false|true)|[+-]?\\d\\.?\\d*?|(?:_\\w+|[a-zA-Z]\\w*)) ?;";
    private final static String NO_ASSIGN_VAR = "(final )?(boolean|int|char|double|String)?" +
            " (_\\w+|[a-zA-Z]\\w*);";//without assignment


    private final static String MULTIPLE_VAR_INIT_REG = "(final )?\\s*(int|double|char|boolean|String)\\s+" +
            "([_a-zA-Z]\\w*((?:\\s*=\\s*(?:\\d+\\.\\d*|\\d+|'.?'|\".*\"))?)(?:\\s*,\\s*" +
            "[_a-zA-Z]+(?:\\s*=\\s*(?:\\d+\\.\\d*|\\d+|'.?'|\".*\"))?)*\\s*?);";


    private final static String MULTIPLE_VAR_ASSIGN_REG = "(?:[_a-zA-Z]\\w*)\\s*=\\s*" +
            "(?:\\d+.\\d*|\\d+|'.?'|\".*\"|[_a-zA-Z]\\w)\\s*" +
            "(?:\\s*,\\s*(?:[_a-zA-Z]\\w*)\\s*=\\s*(?:\\d+.\\d*|\\d+|'.?'|\".*\"|[_a-zA-Z]\\w))*;";


    private final static Pattern multipleInitPattern = Pattern.compile(MULTIPLE_VAR_INIT_REG);
    private final static Pattern multipleAssignPattern = Pattern.compile(MULTIPLE_VAR_ASSIGN_REG);
    private final static Pattern assignVarPattern = Pattern.compile(ASSIGN_VAR);
    private final static Pattern noAssignVarPattern = Pattern.compile(NO_ASSIGN_VAR);


    /**
     * checks if the initializing the variable is legal
     * @param line String
     * @return VariableDetails
     */
    public static VariableDetails checkValidVariableInit(String line) {


        Matcher assignMatcher = assignVarPattern.matcher(line);
        Matcher noAssignMatcher = noAssignVarPattern.matcher(line);
        boolean isBeingAssigned = assignMatcher.matches();
        boolean notBeingAssigned = noAssignMatcher.matches();


        if (!isBeingAssigned && !notBeingAssigned) {

            return null;
        }
        VariableDetails variableDetails;
        if (isBeingAssigned) {

            variableDetails = new VariableDetails(assignMatcher.group(3),
                    Sjavac.stringToDataType.get(assignMatcher.group(2)),
                    assignMatcher.group(4),
                    true,
                    assignMatcher.group(1) != null,
                    true,
                    CheckValidSyntax.checkIfConstant(assignMatcher.group(4)));
        }
        // no assign
        else {

            variableDetails = new VariableDetails(noAssignMatcher.group(3),
                    Sjavac.stringToDataType.get(noAssignMatcher.group(2)),
                    null,
                    false,
                    noAssignMatcher.group(1) != null,
                    false,
                    false);
        }
        return variableDetails;

    }

    /**
     * checks if the variable was initialized more than  one time .
     * @param line
     * @return
     */
    public static ArrayList<VariableDetails> checkMultipleVariableInit(String line) {

        Matcher multipleAssignMatcher = multipleInitPattern.matcher(line);
        boolean isMultipleInit = multipleAssignMatcher.matches();
        if (!isMultipleInit) {
            return null;
        }
        VariableDetails variableDetails;
        ArrayList<VariableDetails> variableArray = new ArrayList<>();
        //its an multiple init variable
        boolean isFinal = multipleAssignMatcher.group(1) != null;
        DataType type = Sjavac.stringToDataType.get(multipleAssignMatcher.group(2));
        String[] strList = multipleAssignMatcher.group(3).split(",");
        //go over all the sub strings in the multiple assign variable and add them if they are legal
        for (String s : strList) {
            if (s.contains("=")) {
                var rightVar = s.substring(s.indexOf("=") + 1);
                variableDetails = new VariableDetails(s.substring(0, s.indexOf("=")).trim(), type,
                        rightVar, true, isFinal, true, CheckValidSyntax.checkIfConstant(rightVar));
                variableArray.add(variableDetails);
            } else {
                variableDetails = new VariableDetails(s.trim(), type, null, false,
                        isFinal, false, CheckValidSyntax.checkIfConstant(s));
                variableArray.add(variableDetails);
            }

        }
        return variableArray;

    }

    /**
     *
     * @param line String .
     * @return ArrayList of the given Variable Details .
     */
    public static ArrayList<VariableDetails> checkMultipleVariableAssign(String line) {
        Matcher multipleAssignMatcher = multipleAssignPattern.matcher(line);
        boolean isMultipleAssign = multipleAssignMatcher.matches();
        VariableDetails variableDetails;
        if (!isMultipleAssign) {
            return null;
        }
        ArrayList<VariableDetails> variableArray = new ArrayList<>();
        //its an multiple assign variable
        String[] strList = multipleAssignMatcher.group(0).split(",");
        //go over all the sub strings in the multiple assign variable and add them if they are legal
        for (String s : strList) {
            var rightVar = s.substring(s.indexOf("=") + 1);
            variableDetails = new VariableDetails(s.substring(0, s.indexOf("=")), null,rightVar,true,
                    false,true,CheckValidSyntax.checkIfConstant(rightVar) ) ;
            variableArray.add(variableDetails) ;
        }
        return variableArray ;


    }
}
