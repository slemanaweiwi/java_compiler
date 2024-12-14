package oop.ex6.methods;

import oop.ex6.main.*;
import oop.ex6.variables.wrongAssignedType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/**
 * this class is in charge of the logic of the methods declarations/calls
 */
public class MethodCheck {


    public static HashMap<String, MethodDetails> declaredMethods;
    public static HashMap<String, MethodDetails> calledMethods;


    /**
     * saves the details of the declared method in the database
     * @param fileParser lines
     * @param scope current scope
     * @param methodDetails details of the declared method
     * @param varsScopes current scopes
     * @throws sJavaExceptions
     */
    public static void methodDeclareCheck
            (FileParser fileParser, int scope, MethodDetails methodDetails,
             ArrayList<HashMap<String, VariableDetails>> varsScopes)
            throws sJavaExceptions {
        if(scope != 0) {
            throw new MethodOutOfScopeException();
        }

        var paramsHashSet = new HashSet<String>();

        if(declaredMethods.containsKey(methodDetails.getName())) {

            throw new methodOverride();
        }
        declaredMethods.put(methodDetails.getName(), methodDetails);
        varsScopes.add(new HashMap<>());
        for (var param : methodDetails.getParameters()) {

            if(paramsHashSet.contains(param.getName())) {
                throw new ParametersWithIdenticalName();
            }
            paramsHashSet.add(param.getName());
            varsScopes.get(scope +1).put(param.getName(),param);
        }

        Sjavac.currLine++;
        Sjavac.scanScope(fileParser, scope +1,false);
    }


    /**
     * this method saves the methods details when they got called so we can check if these method actually exist
     * @param scope local scope that the method was called from
     * @param methodCalledDetails the details of the called method
     * @param scope
     * @param methodCalledDetails
     * @throws methodCallFromGlobalScope
     * @throws MethodHasNotExistentParameter
     * @throws MethodHasNotInitParameter
     */
    public static void methodCallCheck(
            int scope, MethodDetails methodCalledDetails)
            throws methodCallFromGlobalScope, MethodHasNotExistentParameter, MethodHasNotInitParameter {
        if(scope == 0) {
            throw new methodCallFromGlobalScope();

        }

        //updating the types of the given vars from the database
        for (var parm : methodCalledDetails.getParameters()) {

            DataType parmType;
            if(CheckValidSyntax.checkIfConstant(parm.getName())) {
                parmType = CheckValidSyntax.checkConstantType(parm.getName());
            }else {

                VariableDetails parmDataBaseDetails = Sjavac.searchVariableScope(scope,parm.getName());
                if(parmDataBaseDetails ==null) {
                    throw new MethodHasNotExistentParameter();
                }
                if (!parmDataBaseDetails.isInit()) {
                    throw new MethodHasNotInitParameter();
                }
                parmType = parmDataBaseDetails.getVarType();
            }

            parm.setVarType(parmType);
        }
        calledMethods.put(methodCalledDetails.getName(), methodCalledDetails);
    }


    /**
     * this method checks if all the method that were called are actually declared with the correct parameters
     * @throws UndeclaredMethod
     * @throws WrongParameterTypeCount
     * @throws WrongParameterType
     * @throws wrongAssignedType
     */
    public static void checkIfMethodCallsAreValid()
            throws UndeclaredMethod, WrongParameterTypeCount, WrongParameterType, wrongAssignedType {
        for (var methodName : calledMethods.keySet()) {

            if(!declaredMethods.containsKey(methodName)) {
                //exception : calling an undeclared method
                throw new UndeclaredMethod();
            }

            var calledMethodDataBaseDetails = declaredMethods.get(methodName);
            var calledMethod = calledMethods.get(methodName);

            if(calledMethod.getParameters().size() != calledMethodDataBaseDetails.getParameters().size()) {
                //exception : called method with wrong parameters number
                throw new WrongParameterTypeCount();
            }

            for (int i = 0; i < calledMethod.getParameters().size(); i++) {

                if(!CheckValidSyntax.checkTypeCompatibility(
                        calledMethodDataBaseDetails.getParameters().get(i).getVarType(),
                        calledMethod.getParameters().get(i).getVarType())){

                    //exception : method call with invalid parameter/s type
                    throw new WrongParameterType();
                }

            }


        }
    }


}
