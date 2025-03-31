package animals;

import java.util.Objects;

public class Cat implements Animal, Actions {
    private String name, type, sound, eyeColor;

    public Cat(String name, String type, String sound, String eyeColor) {
        this.name = name;
        this.type = type;
        this.sound = sound;
        this.eyeColor = eyeColor;
    }

    @Override
    public void say() {
        System.out.println(name + " meows: " + sound);
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
    public String getEyeColor() { return eyeColor; }
    public void setEyeColor(String eyeColor) { this.eyeColor = eyeColor; }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Cat cat = (Cat) obj;
        return Objects.equals(name, cat.name) && Objects.equals(type, cat.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, type);
    }

    @Override
    public String toString() {
        return "Cat{name='" + name + "', type='" + type + "', sound='" + sound + "', eyeColor='" + eyeColor + "'}";
    }
}
