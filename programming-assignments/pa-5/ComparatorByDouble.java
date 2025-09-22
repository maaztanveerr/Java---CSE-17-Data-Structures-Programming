import java.util.Comparator;

public class ComparatorByDouble implements Comparator<Number> {

    public int compare(Number obj, Number obj2) {
        Double first = obj.doubleValue();
        Double second = obj2.doubleValue();
        return first.compareTo(second);
    }
}