package yeqy.cave.scheme.keyword;

import yeqy.cave.scheme.exception.MethodIsNotDefinedException;
import yeqy.cave.scheme.exception.ParameterErrorException;
import yeqy.cave.scheme.structure.Environment;
import yeqy.cave.scheme.structure.SExpression;
import yeqy.cave.scheme.type.BaseType;
import yeqy.cave.scheme.type.Boolean;
import yeqy.cave.scheme.type.CaveString;
import yeqy.cave.scheme.type.Number;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLSyntaxErrorException;

public class If {
    public static BaseType _if(SExpression exp, Environment env) throws ParameterErrorException, IllegalAccessException, MethodIsNotDefinedException, InvocationTargetException, SQLSyntaxErrorException {
        if(exp.getChildren().size() != 4) {
            throw new ParameterErrorException("Exception: invalid syntax " + exp);
        } else {
            BaseType result = exp.getChildren().get(1).eval(env);
            if(result instanceof Boolean){
                if(((Boolean)result).isValue()){
                    return exp.getChildren().get(2).eval(env);
                } else {
                    return exp.getChildren().get(3).eval(env);
                }
            } else if(result instanceof Number) {
                if(((Number)result).getValue()>0){
                    return exp.getChildren().get(2).eval(env);
                } else {
                    return exp.getChildren().get(3).eval(env);
                }
            } else if(result instanceof CaveString) {
                String str = ((CaveString)result).getValue();
                if(str.length() > 0){
                    return exp.getChildren().get(2).eval(env);
                } else {
                    return exp.getChildren().get(3).eval(env);
                }
            } else {
                return exp.getChildren().get(2).eval(env);
            }
        }
    }
}
