package yeqy.cave.scheme.Function;

import yeqy.cave.scheme.exception.TypeErrorException;
import yeqy.cave.scheme.type.BaseType;
import yeqy.cave.scheme.type.Number;

/**
 * Created by yeqy on 2018/1/26.
 */
public class NumberFunction {
    public static Number plus(BaseType... args) throws TypeErrorException {
        Number result = new Number();
        for (BaseType arg : args) {
            if (!(arg instanceof Number)) {
                throw new TypeErrorException();
            }
            result.plus((Number) arg);
        }
        return result;
    }

    public static Number reduce(BaseType... args) throws TypeErrorException {
        if (!(args[0] instanceof Number))
            throw new TypeErrorException();
        else {
            Number result = (Number) args[0];
            for (int i = 1; i < args.length; i++) {
                result.reduce(((Number) (args[i])));
            }
            return result;
        }
    }

    public static Number divide(BaseType... args) throws TypeErrorException {
        if (!(args[0] instanceof Number))
            throw new TypeErrorException();
        else {
            Number result = (Number) args[0];
            for (int i = 1; i < args.length; i++) {
                result.divide(((Number) (args[i])));
            }
            return result;
        }
    }

    public static Number multiply(BaseType... args) throws TypeErrorException {
        Number result = new Number(1);
        for (BaseType arg : args) {
            if (!(arg instanceof Number)) {
                throw new TypeErrorException();
            }
            result.multiply((Number) arg);
        }
        return result;
    }
}
