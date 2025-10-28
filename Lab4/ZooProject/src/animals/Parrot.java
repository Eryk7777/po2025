package animals;

public class Parrot extends Animal {
    public Parrot(String name) {
        super(name, 2);
    }

    @Override
    public String getDescription() {
        return "Parrot named " + name + " with " + legs + " legs";
    }

    @Override
    public void makeSound() {
        System.out.println("Squawk!");
    }
}
