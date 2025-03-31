import animals.*;

import java.util.HashSet;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        Set<Animal> animals = new HashSet<>();
        animals.add(new Dog("Rex", "Labrador", "Woof", 30));
        animals.add(new Cat("Whiskers", "Persian", "Meow", "Green"));
        animals.add(new Horse("Spirit", "Mustang", "Neigh", 400, 60));
        animals.add(new Dog("Rex", "Labrador", "Woof", 30)); // Дубликат
        animals.add(new Cat("Mittens", "Siamese", "Meow", "Blue"));
        animals.add(new Horse("Thunder", "Arabian", "Neigh", 380, 55));

        for (Animal animal : animals) {
            System.out.println(animal);
            animal.say();
        }
    }
}

