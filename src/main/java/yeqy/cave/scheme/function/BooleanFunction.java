package yeqy.cave.scheme.function;

import yeqy.cave.scheme.exception.TypeErrorException;
import yeqy.cave.scheme.type.BaseType;
import yeqy.cave.scheme.type.Boolean;
import yeqy.cave.scheme.type.CaveString;
import yeqy.cave.scheme.type.Number;

public class BooleanFunction {
    public static Boolean equal(BaseType... args) throws TypeErrorException {
        if(args[0] instanceof Number){
            int value = ((Number)(args[0])).getValue();
            for(BaseType arg:args){
                if(!(arg instanceof Number)) {
                    throw new TypeErrorException(arg+" is not a number");
                }
                if(value != ((Number) arg).getValue()){
                    return new Boolean(false);
                }
            }
            return new Boolean(true);
        } else if(args[0] instanceof CaveString) {
            String value = ((CaveString)(args[0])).getValue();
            for(BaseType arg:args){
                if(!(arg instanceof CaveString)) {
                    throw new TypeErrorException(arg+" is not a strirng");
                }
                if(value != ((CaveString) arg).getValue()){
                    return new Boolean(false);
                }
            }
            return new Boolean(true);
        }
        return null;
    }

    public static Boolean equals(BaseType... args) throws TypeErrorException {
        if(args[0] instanceof Number){
            int value = ((Number)(args[0])).getValue();
            for(BaseType arg:args){
                if(!(arg instanceof Number)) {
                    throw new TypeErrorException(arg+" is not a number");
                }
                if(value != ((Number) arg).getValue()){
                    return new Boolean(false);
                }
            }
            return new Boolean(true);
        } else if(args[0] instanceof CaveString) {
            String value = ((CaveString)(args[0])).getValue();
            for(BaseType arg:args){
                if(!(arg instanceof CaveString)) {
                    throw new TypeErrorException(arg+" is not a string");
                }
                if(value.equals(((CaveString) arg).getValue())){
                    return new Boolean(false);
                }
            }
            return new Boolean(true);
        }
        return null;
    }

    public static Boolean and(BaseType... args) throws TypeErrorException {
        if(args[0] instanceof Boolean){
            for(BaseType arg:args) {
                if(!((Boolean)arg).isValue()){
                    return new Boolean(false);
                }
            }
            return new Boolean(true);
        }
        return new Boolean(false);
    }

    public static Boolean or(BaseType... args) throws TypeErrorException {
        if(args[0] instanceof Boolean){
            for(BaseType arg:args) {
                if(((Boolean)arg).isValue()){
                    return new Boolean(true);
                }
            }
            return new Boolean(false);
        }
        return new Boolean(false);
    }

    public static Boolean not(BaseType... args) throws TypeErrorException {
        return new Boolean(!((Boolean)args[0]).isValue());
    }
}
