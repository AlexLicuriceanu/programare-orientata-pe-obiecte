import java.util.LinkedHashSet;

public class LinkedEvenSet extends LinkedHashSet<Integer> {
    /* TODO - Task 7 */

    @Override
    public boolean add(Integer integer) {
        if (integer % 2 == 0)
            return super.add(integer);
        else
            return false;
    }
}

