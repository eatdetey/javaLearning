import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

enum GrapeColor {
    RED, GREEN, BLUE, BLACK, WHITE
}

enum StorageDuration {
    WEEK, MONTH, LONG_TERM
}

enum GrapeType {
    TABLE, WINE, COGNAC
}

public class GrapeFarm {
    private List<Grape> grapes;

    public GrapeFarm() {
        grapes = new ArrayList<>();
    }

    public void addGrape(Grape grape) {
        grapes.add(grape);
    }

    public List<Grape> filterGrapes(Predicate<Grape> predicate) {
        List<Grape> result = new ArrayList<>();
        for (Grape grape: grapes) {
            if (predicate.test(grape)) {
                result.add(grape);
            }
        }
        return result;
    }

    public List<Grape> sortByPrice(List<Grape> grapeList) {
        return grapeList.stream()
                .sorted(Comparator.comparingDouble(Grape::getPrice))
                .collect(Collectors.toList());
    }

    public static class GrapePredicateType implements Predicate<Grape> {
        @Override
        public boolean test(Grape grape) {
            return grape.getType() == GrapeType.WINE;
        }
    }

    public static class GrapePredicateColor implements Predicate<Grape> {
        @Override
        public boolean test(Grape grape) {
            return grape.getColor() == GrapeColor.WHITE;
        }
    }

    public static void main(String[] args) {
        GrapeFarm farm = new GrapeFarm();

        farm.addGrape(new Grape("Изабелла", GrapeColor.BLACK, StorageDuration.WEEK, GrapeType.WINE, 150.0));
        farm.addGrape(new Grape("Мускат", GrapeColor.WHITE, StorageDuration.MONTH, GrapeType.TABLE, 200.0));
        farm.addGrape(new Grape("Каберне", GrapeColor.RED, StorageDuration.LONG_TERM, GrapeType.WINE, 180.0));
        farm.addGrape(new Grape("Кишмиш", GrapeColor.GREEN, StorageDuration.WEEK, GrapeType.TABLE, 220.0));
        farm.addGrape(new Grape("Алиготе", GrapeColor.WHITE, StorageDuration.MONTH, GrapeType.COGNAC, 190.0));
        farm.addGrape(new Grape("Шардоне", GrapeColor.GREEN, StorageDuration.LONG_TERM, GrapeType.WINE, 210.0));

        System.out.println("\n=== Винный виноград (используя предикат) ===");
        List<Grape> wineGrapes = farm.filterGrapes(new GrapePredicateType());
        wineGrapes.forEach(System.out::println);

        System.out.println("\n=== Белый виноград (используя предикат) ===");
        List<Grape> whiteGrapes = farm.filterGrapes(new GrapePredicateColor());
        whiteGrapes.forEach(System.out::println);

        System.out.println("\n=== Зеленый виноград (используя лямбду) ===");
        List<Grape> greenGrapes = farm.filterGrapes(g -> g.getColor() == GrapeColor.GREEN);
        greenGrapes.forEach(System.out::println);

        System.out.println("\n=== Винный виноград, отсортированный по цене ===");
        List<Grape> sortedWineGrapes = farm.sortByPrice(wineGrapes);
        sortedWineGrapes.forEach(System.out::println);

        System.out.println("\n=== Виноград длительного хранения ===");
        List<Grape> longTermGrapes = farm.filterGrapes(g -> g.getStorage() == StorageDuration.LONG_TERM);
        longTermGrapes.forEach(System.out::println);

        System.out.println("\n=== Столовый виноград, отсортированный по цене ===");
        List<Grape> tableGrapes = farm.filterGrapes(g -> g.getType() == GrapeType.TABLE);
        List<Grape> sortedTableGrapes = farm.sortByPrice(tableGrapes);
        sortedTableGrapes.forEach(System.out::println);
    }
}