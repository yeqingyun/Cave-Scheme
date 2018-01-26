package yeqy.cave.scheme.analyze;


import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by yeqy on 2018/1/26.
 */
public class Feature {

    public static final Set<Constant> keywords = new HashSet<>();
    public static final Map<Constant, String> functions = new HashMap<>();

    static {
        keywords.add(Constant._if);
        keywords.add(Constant.define);
        keywords.add(Constant.lambda);

        functions.put(Constant.plus, "yeqy.cave.scheme.Function.NumberFunction.plus");
        functions.put(Constant.reduce, "yeqy.cave.scheme.Function.NumberFunction.reduce");
        functions.put(Constant.multiply, "yeqy.cave.scheme.Function.NumberFunction.multiply");
        functions.put(Constant.divide, "yeqy.cave.scheme.Function.NumberFunction.divide");

    }

}
