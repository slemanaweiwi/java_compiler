package oop.ex6.variables;

import oop.ex6.main.CheckValidSyntax;
import oop.ex6.main.DataType;
import oop.ex6.main.VariableDetails;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * checks All the requirements for a legal Variable
 */
public class VariableCheck {
    /**
     * checks the scope of the current variable .
     * @param currScope
     * @param varName
     * @param varsScopes
     * @return
     */
    public static VariableDetails searchVariableScope(int currScope, String varName,
                                                      ArrayList<HashMap<String, VariableDetails>> varsScopes) {

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

    /**
     * checks if the variable is legal due to the requirements ,  if not we will throw an exception .
     * @param scope
     * @param variableDetails
     * @param varsScopes
     * @throws assigningFinalVariable
     * @throws doubleInitVar
     * @throws initFinalWithoutValue
     * @throws initVarWithoutType
     * @throws wrongAssignedType
     * @throws assigningValueOfNonExistentVar
     * @throws assigningValueOfNonInitVar
     * @throws assigningValueOfDiffValueVar
     */
    public static void variableCheck(int scope, VariableDetails variableDetails,
                                     ArrayList<HashMap<String, VariableDetails>> varsScopes)
            throws assigningFinalVariable, doubleInitVar, initFinalWithoutValue,
            initVarWithoutType,
            wrongAssignedType,
            assigningValueOfNonExistentVar,
            assigningValueOfNonInitVar,
            assigningValueOfDiffValueVar {
        // check if variable is in hashmap
        VariableDetails varDataBaseDetails = searchVariableScope(scope,variableDetails.getName(),varsScopes);



        //if variable is init before
        if (varDataBaseDetails != null){
            // if variable is final and being assigned
            if (varDataBaseDetails.isFinal() && variableDetails.isBeingAssigned()) {
                throw new assigningFinalVariable();
            }
            // if variable has datatype
            if (variableDetails.getVarType()!=null){
                throw new doubleInitVar() ;
            }


        }

        //if variable is not init before
        else {
            // if variable is final and not being assigned
            if (variableDetails.isFinal() && !variableDetails.isBeingAssigned()) {
                throw new initFinalWithoutValue();
            }
            //if var doesn't have data type
            if (variableDetails.getVarType() == null){
                throw new initVarWithoutType();
            }

            varsScopes.get(scope).put(variableDetails.getName(),variableDetails);


        }


        // right value checks ( if assigned)
        if (variableDetails.isBeingAssigned()) {


            DataType rightVarDataType;
            //if being assigned to a constant
            if (variableDetails.isAssignedToConstant()) {

                rightVarDataType = CheckValidSyntax.checkConstantType(variableDetails.getAssignedVarName());

                if(!CheckValidSyntax.checkTypeCompatibility(variableDetails.getVarType(), rightVarDataType)) {
                    throw new wrongAssignedType();
                };

            }//if being assigned to a var
            else {
                String rightVarName = variableDetails.getAssignedVarName();
                VariableDetails rightVarDetails = searchVariableScope(scope,rightVarName,varsScopes);


                // if right var does not exist
                if(rightVarDetails == null) {
                    throw new assigningValueOfNonExistentVar();
                }

                //if right var is not init
                if(!rightVarDetails.isInit()) {

                    throw new assigningValueOfNonInitVar();
                }

                rightVarDataType = rightVarDetails.getVarType();

                // wrong data type
                if(rightVarDetails.getVarType() != variableDetails.getVarType()) {

                    throw new assigningValueOfDiffValueVar();
                }

            }

            //if it's a new value initialization
            if(varDataBaseDetails == null) {

                varsScopes.get(scope).put(variableDetails.getName(),variableDetails);

                //if the value exists before and not assigned then we assign it to its new value
            }else {

                if(!varDataBaseDetails.isInit()) {

                    varDataBaseDetails.setInit(true);
                }
            }
        }
    }

}
