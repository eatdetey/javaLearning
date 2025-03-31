package animals;

import java.util.Objects;

public class Dog implements Animal, Actions {
    private String name, type, sound;
    private double weight;

    public Dog(String name, String type, String sound, double weight) {
        this.name = name;
        this.type = type;
        this.sound = sound;
        this.weight = weight;
    }

    @Override
    public void say() {
        System.out.println(name + " barks: " + sound);
    }

    @Override
    public void run() {
        System.out.println(name + " is running.");
    }

    @Override
    public void doCommand(String command) {
        System.out.println(name + " executes command: " + command);
    }

    @Override
    public void eat() {
        System.out.println(name + " is eating.");
    }

    @Override
    public void sleep() {
        System.out.println(name + " is sleeping.");
    }

    // Getters and setters
    @Override
    public String getName() { return name; }
    @Override
    public void setName(String name) { this.name = name; }
    @Override
    public String getType() { return type; }
    @Override
    public void setType(String type) { this.type = type; }
    @Override
    public String getSound() { return sound; }
    @Override
    public void setSound(String sound) { this.sound = sound; }
    public double getWeight() { return weight; }
    public void setWeight(double weight) { this.weight = weight; }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Dog dog = (Dog) obj;
        return Objects.equals(name, dog.name) && Objects.equals(type, dog.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, type);
    }

    @Override
    public String toString() {
        return "Dog{name='" + name + "', type='" + type + "', sound='" + sound + "', weight=" + weight + "}";
    }
}
