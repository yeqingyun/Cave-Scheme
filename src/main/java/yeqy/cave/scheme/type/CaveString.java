package yeqy.cave.scheme.type;

/**
 * Created by yeqy on 2018/1/26.
 */
public class CaveString extends BaseType {
    private java.lang.String value;

    public CaveString(java.lang.String value) {
        this.value = value;
    }

    public java.lang.String getValue() {
        return value;
    }

    @Override
    public java.lang.String toString() {
        return value;
    }
}
