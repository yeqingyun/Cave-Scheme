package yeqy.cave.scheme.type;

/**
 * Created by yeqy on 2018/1/26.
 */
public class Boolean extends BaseType {
    private boolean value;

    public Boolean(boolean value) {
        this.value = value;
    }

    public boolean isValue() {
        return value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
