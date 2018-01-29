package yeqy.cave.scheme.type;

/**
 * Created by yeqy on 2018/1/26.
 */
public class Number extends BaseType {
    private long value;

    public Number(long value) {
        this.value = value;
    }

    public Number() {
    }

    public void plus(long value) {
        this.value += value;
    }

    public void plus(Number value) {
        this.value += value.getValue();
    }

    public void reduce(long value) {
        this.value -= value;
    }

    public void reduce(Number value) {
        this.value -= value.getValue();
    }

    public void divide(long value) {
        this.value /= value;
    }

    public void divide(Number value) {
        this.value /= value.getValue();
    }

    public void multiply(long value) {
        this.value *= value;
    }

    public void multiply(Number value) {
        this.value *= value.getValue();
    }

    public long getValue() {
        return value;
    }

    @Override
    public java.lang.String toString() {
        return java.lang.String.valueOf(value < 0 ? 0 : value);
    }
}
