import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class IteratorDemo {
    public static void main(String[] args) {
        // TODO: wrap List.of(...) in new ArrayList<>(...) so removal is allowed
        List<String> titles = new ArrayList<>(List.of("Deprecated Three Body Problem", "I who have never known men",
                                                "Deprecated Alchemised", "Color of Law", "Deprecated A Guardian and a Thief"));


        // TODO: obtain an Iterator<String> from titles
        Iterator<String> iterator = titles.iterator();

        // TODO: loop while iterator.hasNext()
        while (iterator.hasNext()) {
            String title = iterator.next();

            if (title.startsWith("Deprecated")) {
                // TODO: remove through the iterator (not titles.remove)
                iterator.remove();
            }
        }

        System.out.println("Remaining: " + titles);
    }
}