package oop.ex6.statements;

import oop.ex6.main.VariableDetails;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StatementParser {
    private final static String STATEMENTS_SIGNATURE_REG = "\\s*(?:if|while)\\s*\\((\\s*(?:[_a-zA-Z]\\w*(?:\\s*(?:&&|\\|\\|)\\s*[_a-zA-Z]\\w*)*)\\s*)\\)\\s*\\{";
    private final static Pattern statementPattern = Pattern.compile(STATEMENTS_SIGNATURE_REG);


    public static ArrayList<VariableDetails> checkIfStatement(String line) {
        Matcher statementMatcher = statementPattern.matcher(line);
        boolean isStatement = statementMatcher.matches();
        if (!isStatement) {
            return null;
        }
        VariableDetails variableDetails;
        ArrayList<VariableDetails> variableArray = new ArrayList<>();
        //it's a Statement !
        if (statementMatcher.group(1).isEmpty()) {
            return variableArray;
        }
        String newLine = statementMatcher.group(1).replaceAll("(&&|\\|\\|)", ",");
        String[] strList = newLine.split(",");
        for (String str : strList) {
            if (!str.equals("false") && !str.equals("true")) {
                variableDetails = new VariableDetails(str, null, null, false, false,
                        false, false);
                variableArray.add(variableDetails);
            }

        }
        return variableArray;

    }
}
