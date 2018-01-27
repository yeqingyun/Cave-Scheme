package yeqy.cave.scheme.keyword;

import yeqy.cave.scheme.exception.MethodIsNotDefinedException;
import yeqy.cave.scheme.exception.ParameterException;
import yeqy.cave.scheme.exception.SyntaxException;
import yeqy.cave.scheme.structure.Environment;
import yeqy.cave.scheme.structure.SExpression;
import yeqy.cave.scheme.type.CaveFunction;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yeqy on 2018/1/27.
 */
public class Lambda {
    public static CaveFunction apply(SExpression exp, Environment env) throws InvocationTargetException, ParameterException, MethodIsNotDefinedException, IllegalAccessException, SyntaxException {
        List<String> params = new ArrayList<>();
        for (SExpression var : exp.getChildren().get(1).getChildren()) {
            params.add(var.getValue());
        }

        return new CaveFunction(exp.getChildren().get(2), params, new Environment(env));
    }

    public static Object deepClone(Object src) {
        Object o = null;
        try {
            if (src != null) {
                ByteArrayOutputStream babs = new ByteArrayOutputStream();
                ObjectOutputStream oos = new ObjectOutputStream(babs);
                oos.writeObject(src);
                oos.close();
                ByteArrayInputStream baas = new ByteArrayInputStream(babs
                        .toByteArray());
                ObjectInputStream ois = new ObjectInputStream(baas);
                o = ois.readObject();
                ois.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return o;
    }
}
