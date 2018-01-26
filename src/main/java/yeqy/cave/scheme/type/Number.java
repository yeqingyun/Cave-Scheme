package yeqy.cave.scheme.type;

/**
 * Created by yeqy on 2018/1/26.
 */
public class Number extends BaseType {
    private int value;

    public Number(int value) {
        this.value = value;
    }

    public Number() {
    }

    public void plus(int value) {
        this.value += value;
    }

    public void plus(Number value) {
        this.value += value.getValue();
    }

    public void reduce(int value) {
        this.value -= value;
    }

    public void reduce(Number value) {
        this.value -= value.getValue();
    }

    public void divide(int value) {
        this.value /= value;
    }

    public void divide(Number value) {
        this.value /= value.getValue();
    }

    public void multiply(int value) {
        this.value *= value;
    }

    public void multiply(Number value) {
        this.value *= value.getValue();
    }

    public int getValue() {
        return value;
    }

    @Override
    public java.lang.String toString() {
        return java.lang.String.valueOf(value);
    }
}
