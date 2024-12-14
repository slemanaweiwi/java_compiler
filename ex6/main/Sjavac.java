package oop.ex6.main;

import oop.ex6.methods.*;
import oop.ex6.statements.StatementCheck;
import oop.ex6.statements.StatementParser;
import oop.ex6.variables.VariableCheck;
import oop.ex6.variables.VariableParser;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class Sjavac {

    private static int VALID_CODE = 0;
    private static int ILLEGAL_CODE = 1;
    private static int IO_ERROR = 2;

    private static String IO_ERROR_MSG = "failed to open/read the provided file path";


    public static int currLine = 0;

    public final static HashMap<String, DataType> stringToDataType = new HashMap<>();

    public static ArrayList<HashMap<String,VariableDetails>> varsScopes;

    public static void main(String[] args) {


        try (var bufferedReader = new BufferedReader(new FileReader(args[0]))) {
            var fileParser = new FileParser(bufferedReader);

            stringToDataType.put("String", DataType.STRING);
            stringToDataType.put("int", DataType.INT);
            stringToDataType.put("double", DataType.DOUBLE);
            stringToDataType.put("char", DataType.CHAR);
            stringToDataType.put("boolean", DataType.BOOLEAN);

            currLine = 0;
            varsScopes = new ArrayList<>();
            MethodCheck.declaredMethods = new HashMap<>();
            MethodCheck.calledMethods = new HashMap<>();
            varsScopes.add(new HashMap<>());
            scanScope(fileParser,0,false);

            MethodCheck.checkIfMethodCallsAreValid();
            System.out.println(VALID_CODE);



        } catch (IOException e) {
            System.out.println(IO_ERROR);
            System.err.println(IO_ERROR_MSG);

        }catch (sJavaExceptions e) {
            System.out.println(ILLEGAL_CODE);
        }




    }









    public static void scanScope(FileParser fileParser,
                                  int scope,
                                  boolean isInStatement) throws sJavaExceptions {
        boolean returnFound = false;
        for (; Sjavac.currLine<fileParser.codeLines.size();Sjavac.currLine++) {


            if (fileParser.codeLines.get(Sjavac.currLine).startsWith("/*")) {

                throw new IllegalComment();
            }

            VariableDetails variableDetails = VariableParser.checkValidVariableInit
                    (fileParser.codeLines.get(Sjavac.currLine));
            ArrayList<VariableDetails> multipleVarInit = VariableParser.
                    checkMultipleVariableInit(fileParser.codeLines.get(Sjavac.currLine));
            ArrayList<VariableDetails> multipleVarAssign = VariableParser.
                    checkMultipleVariableAssign(fileParser.codeLines.get(Sjavac.currLine));

            MethodDetails methodDetails = MethodParser.checkValidMethodInit
                    (fileParser.codeLines.get(Sjavac.currLine));
            MethodDetails methodCalledDetails = MethodParser.checkIfMethodCall
                    (fileParser.codeLines.get(Sjavac.currLine));
            ArrayList<VariableDetails> statementCheck = StatementParser.checkIfStatement
                    (fileParser.codeLines.get(Sjavac.currLine));


            if(variableDetails != null) {


                VariableCheck.variableCheck(scope, variableDetails,varsScopes);



                // multiple variable init in the same line
            }else if(multipleVarInit != null) {

                for (var var : multipleVarInit) {

                    VariableCheck.variableCheck(scope,var,varsScopes);

                }


                // multiple variables assign  in the same line
            }else if(multipleVarAssign != null) {

                for (var var : multipleVarAssign) {

                    VariableCheck.variableCheck(scope,var,varsScopes);

                }

            }

            //if it's a method declaration
            else if (methodDetails != null) {

                MethodCheck.methodDeclareCheck(fileParser, scope, methodDetails,varsScopes);


                // if this is a method call
             }else if(methodCalledDetails != null) {

                MethodCheck.methodCallCheck(scope, methodCalledDetails);
            }

            else if(statementCheck != null) {

                StatementCheck.statementCheck(fileParser, scope, statementCheck,varsScopes);
            }



            else if (fileParser.codeLines.get(Sjavac.currLine).replaceAll("\\s+","").equals("return;")) {

                returnFound = true;

                try {
                    if ((!fileParser.codeLines.get(Sjavac.currLine+1).trim().equals("}"))&&!isInStatement) {

                        throw new CommandsAfterReturn();
                    }
                }catch (Exception e)
                {
                    throw new ScopeIsNotClosed();

                }
                // getting out of a method/statement
            }else if (fileParser.codeLines.get(Sjavac.currLine).replaceAll("\\s+","").equals("}")) {


                if(!returnFound && !isInStatement) {
                    throw new MissingReturnStatement();
                }


                if(varsScopes.isEmpty()) {
                    throw new ScopeIsNotClosed();
                }
                varsScopes.remove(varsScopes.get(scope));

                return;

            } else {

                throw new SyntaxError();
            }


        }

    }



    /**
     * This function searches for a variable with a given name within a certain scope and
     * returns the details of the variable if found.
     *
     * @param currScope the current scope in which to search for the variable
     * @param varName the name of the variable to search for
     * @return the details of the variable if found, or null if the variable is not found
     */
    public static VariableDetails searchVariableScope(int currScope, String varName) {

        // Starting from the current scope, iterate backwards through all scopes
        for (int i = currScope; i >= 0; i--) {

            // Check if the current scope contains a variable with the given name
            if (varsScopes.get(i).containsKey(varName)) {

                return varsScopes.get(i).get(varName);

            }
        }

        // If the variable is not found in any scope, return null
        return null;
    }

}
