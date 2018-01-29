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

        functions.put(Constant.plus, "yeqy.cave.scheme.function.NumberFunction.plus");
        functions.put(Constant.reduce, "yeqy.cave.scheme.function.NumberFunction.reduce");
        functions.put(Constant.multiply, "yeqy.cave.scheme.function.NumberFunction.multiply");
        functions.put(Constant.divide, "yeqy.cave.scheme.function.NumberFunction.divide");
        functions.put(Constant.equal, "yeqy.cave.scheme.function.BooleanFunction.equal");
        functions.put(Constant.equals, "yeqy.cave.scheme.function.BooleanFunction.equals");
        functions.put(Constant.and, "yeqy.cave.scheme.function.BooleanFunction.and");
        functions.put(Constant.or, "yeqy.cave.scheme.function.BooleanFunction.or");
        functions.put(Constant.not, "yeqy.cave.scheme.function.BooleanFunction.not");
        functions.put(Constant.more_than, "yeqy.cave.scheme.function.BooleanFunction.moreThan");
        functions.put(Constant.less_than, "yeqy.cave.scheme.function.BooleanFunction.lessThan");

    }
}
