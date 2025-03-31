import java.util.List;

public class ListModifier {
    public List<Integer> removeInRange(List<Integer> list, int element, int start, int end) {
        if (list == null || start < 0 || end > list.size() || start >= end) {
            throw new IllegalArgumentException("Invalid range or list is null");
        }

        for (int i = start; i < end; i++) {
            if (i >= list.size()) break;
            if (list.get(i) == element) {
                list.remove(i);
                i--;
                end--;
            }
        }
        return list;
    }
}
