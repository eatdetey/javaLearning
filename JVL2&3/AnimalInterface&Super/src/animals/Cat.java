package animals;

public class Cat extends Animal implements AnimalInterface {
    private String eyeColor;

    public Cat(String name, String type, String sound, String eyeColor) {
        super(name, type, sound);
        this.eyeColor = eyeColor;
    }

    @Override
    public void say() {
        System.out.println(name + " meows: " + sound);
    }

    public String getEyeColor() {
        return eyeColor;
    }

    public void setEyeColor(String eyeColor) {
        this.eyeColor = eyeColor;
    }

    @Override
    public String toString() {
        return super.toString() + ", Eye Color: " + eyeColor;
    }
}
