import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) {
//        List<Integer> numbers = new ArrayList<Integer>(Arrays.asList(1,4,4,2,3,4,5,6,4,7,8));

        List<Integer> numbers = Collections.EMPTY_LIST;
        Collections.addAll(numbers = new ArrayList<Integer>(), 1, 2, 3, 4, 5, 6, 7, 8, 4, 4, 4, 4);

        ListModifier modifier = new ListModifier();
        System.out.println(modifier.removeInRange(numbers, 4, 0, numbers.size()));
    }
}