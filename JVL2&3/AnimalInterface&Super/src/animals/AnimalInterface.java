package animals;

public interface AnimalInterface {
    String getName();
    void setName(String name);
    String getType();
    void setType(String type);
    String getSound();
    void setSound(String sound);
    void say();
    void run();
    void doCommand(String command);
    void eat();
    void sleep();
}
