package yeqy.cave.scheme.structure;

import yeqy.cave.scheme.analyze.Constant;
import yeqy.cave.scheme.analyze.Feature;
import yeqy.cave.scheme.exception.MethodIsNotDefinedException;
import yeqy.cave.scheme.exception.ParameterException;
import yeqy.cave.scheme.exception.SyntaxException;
import yeqy.cave.scheme.paser.Apply;
import yeqy.cave.scheme.paser.TokenPaser;
import yeqy.cave.scheme.type.BaseType;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * scheme S-Expression
 */
public class SExpression {
    private SExpression parent;
    private String value = "";
    private List<SExpression> children;


    public SExpression(SExpression parent, String value) {
        this.parent = parent;
        this.value = value;
        this.children = new ArrayList<>();
    }

    public SExpression(SExpression parent) {
        this.parent = parent;
        this.children = new ArrayList<>();
    }

    public SExpression(String[] tokens) {
        this.children = new ArrayList<>();
        SExpression parent = this;

        for (int i = 0, count = 0; i < tokens.length; i++) {
            String token = tokens[i];
            if (Constant.left_braces.getToken().equals(token)) {
                count++;
                if (i == 0) {
                    continue;
                } else {
                    int nextRightBraces = TokenPaser.nextBraces(tokens, count, i);
                    parent.addChild(new SExpression(Arrays.copyOfRange(tokens, i, nextRightBraces + 1)));
                    i = nextRightBraces - 1;
                }
            } else if (Constant.right_braces.getToken().equals(token)) {
                continue;
            } else if (token.length() > 0) {
                parent.addChild(new SExpression(parent, token));
            }
        }
    }

    public void addChild(SExpression child) {
        this.children.add(child);
    }

    public BaseType eval(Environment env) throws IllegalAccessException, MethodIsNotDefinedException, InvocationTargetException, ParameterException, SyntaxException {
        if (this.children.size() == 0) {
            return Apply.signleEvalApply(this, env);
        } else if (this.children.size() == 1 && this.value == "") {
            return Apply.signleEvalApply(this.children.get(0), env);
        } else {
            if ("".equals(this.children.get(0).value)) {
                return Apply.lambdaApply(this, env);
            } else if (Feature.keywords.contains(Constant.chooseKey(this.children.get(0).value))) {
                return Apply.keyWordsApply(this, env);
            } else {
                return Apply.functionApply(this, env);
            }
        }
    }


    @Override
    public String toString() {
        if (children.size() == 0) {
            return value;
        } else {
            StringBuilder sb = new StringBuilder("(" + value);
            for (SExpression sExpression : children) {
                sb.append(sExpression.toString()).append(" ");
            }
            return sb.deleteCharAt(sb.length() - 1).append(")").toString();
        }
    }

    public SExpression getParent() {
        return parent;
    }

    public void setParent(SExpression parent) {
        this.parent = parent;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public List<SExpression> getChildren() {
        return children;
    }

    public void setChildren(List<SExpression> children) {
        this.children = children;
    }
}

