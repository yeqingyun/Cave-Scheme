package yeqy.cave.scheme.keyword;

import yeqy.cave.scheme.exception.MethodIsNotDefinedException;
import yeqy.cave.scheme.exception.ParameterErrorException;
import yeqy.cave.scheme.structure.Environment;
import yeqy.cave.scheme.structure.SExpression;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLSyntaxErrorException;

public class Define {

    public static void define(SExpression exp, Environment env) throws SQLSyntaxErrorException, InvocationTargetException, ParameterErrorException, MethodIsNotDefinedException, IllegalAccessException {
         if(exp.getChildren().size() > 3)
             throw new SQLSyntaxErrorException("Exception: invalid syntax " + exp);
         else {
             String var = exp.getChildren().get(1).getValue();
             //String value = exp.getChildren().get(2).getValue();
             env.setVar(var,exp.getChildren().get(2).eval(env));
         }
    }
}
