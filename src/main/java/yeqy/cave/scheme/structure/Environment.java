package yeqy.cave.scheme.structure;

import yeqy.cave.scheme.type.BaseType;

import java.util.HashMap;
import java.util.Map;


/**
 * Created by yeqy on 2018/1/26.
 */
public class Environment {
    private Environment parent;
    private Map<String, BaseType> vars = new HashMap<>();

    public Environment(Environment parent) {
        this.parent = parent;
        this.vars = vars;
    }

    public Environment() {
    }

    public Environment(Map<String, BaseType> vars) {
        this.vars = vars;
    }

    public void setVar(String var, BaseType value) {
        vars.put(var, value);
    }

    public BaseType findVar(String var) {
        BaseType data = findCurrentVar(var);
        if (data != null) {
            return data;
        } else {
            if (this.parent != null)
                return parent.findVar(var);
            else
                return null;
        }
    }

    public BaseType findCurrentVar(String var) {
        if (vars.containsKey(var)) {
            return vars.get(var);
        }
        return null;
    }

    public Environment getParent() {
        return parent;
    }

    public void setParent(Environment parent) {
        this.parent = parent;
    }

    public Map<String, BaseType> getVars() {
        return vars;
    }

    public void setVars(Map<String, BaseType> vars) {
        this.vars = vars;
    }
}
