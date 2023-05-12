import java.util.HashSet;

public class EvenSet extends HashSet<Integer> {
    /* TODO - Task 7 */

    @Override
    public boolean add(Integer integer) {
        if (integer % 2 == 0)
            return super.add(integer);
        else
            return false;
    }
}
