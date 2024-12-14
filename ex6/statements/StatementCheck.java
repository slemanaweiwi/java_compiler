package oop.ex6.statements;

import oop.ex6.main.DataType;
import oop.ex6.main.FileParser;
import oop.ex6.main.Sjavac;
import oop.ex6.main.sJavaExceptions;
import oop.ex6.variables.NonExistentConditionVar;
import oop.ex6.main.VariableDetails;
import oop.ex6.variables.initFinalVarWithoutValue;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * this class is in charge the logic of statements
 */
public class StatementCheck {

    /**
     * checking the everything is valid logic wise in the if/while statement
     * @param fileParser line
     * @param scope current local scope
     * @param statementCheck details of the statement
     * @param varsScopes all the scopes so far
     * @throws sJavaExceptions
     * @throws initFinalVarWithoutValue
     */
    public static void statementCheck(FileParser fileParser, int scope,
                                      ArrayList<VariableDetails> statementCheck,
                                      ArrayList<HashMap<String, VariableDetails>> varsScopes)
            throws sJavaExceptions, initFinalVarWithoutValue {
        for (var cond : statementCheck) {

            VariableDetails condDataBaseDetails = Sjavac.searchVariableScope(scope,cond.getName());

            if(condDataBaseDetails == null) {

                //exception : non existent variable inside if/while statement
                throw new NonExistentConditionVar();
            }

            if(!condDataBaseDetails.isInit()) {

                //exception : not assigned variable inside if/while statement
                throw new UnInitCondition();
            }
            if(condDataBaseDetails.getVarType() == DataType.CHAR ||
                    condDataBaseDetails.getVarType() == DataType.STRING) {

                //exception : not boolean condition
                throw new NotBoolCondition();
            }
        }

        Sjavac.currLine++;
        varsScopes.add(new HashMap<>());
        Sjavac.scanScope(fileParser, scope +1,true);
    }

}
